package cz.hlubyluk.adventofcode2018.day;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://adventofcode.com/2018/day/4
 *
 * @author HlubyLuk
 */
public class Day4 implements IDay4 {
  private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().appendLiteral('[')
      .appendValue(ChronoField.YEAR).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral('-')
      .appendValue(ChronoField.DAY_OF_MONTH).appendLiteral(' ').appendValue(ChronoField.HOUR_OF_DAY).appendLiteral(':')
      .appendValue(ChronoField.MINUTE_OF_HOUR).appendLiteral(']').toFormatter();

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    int id = -1, index = -1, max = -1;

    for (Map.Entry<Integer, int[]> entry : this.shared().entrySet()) {
      int key = entry.getKey();
      int[] value = entry.getValue();

      int count = 0;

      for (int j = 0; j < value.length; j += 1) {
        count += value[j];
      }

      if (max < count) {
        int val = -1;

        max = count;
        id = key;

        for (int j = 0; j < value.length; j += 1) {
          if (val < value[j]) {
            index = j;
            val = value[j];
          }
        }
      }
    }

    return String.valueOf(id * index);

  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    int id = -1, index = -1, value = -1;

    for (Map.Entry<Integer, int[]> entry : this.shared().entrySet()) {
      int[] tmp = entry.getValue();

      for (int j = 0; j < tmp.length; j += 1) {
        if (value < tmp[j]) {
          id = entry.getKey();
          index = j;
          value = tmp[j];
        }
      }
    }

    return String.valueOf(id * index);
  }

  private static class RecordFactory {
    private RecordFactory() {
    }

    private static Record create(String input) {
      Record tmp = null;

      String[] split = input.split(" ", 3);
      String time = String.format("%s %s", split[0], split[1]);

      if (split[2].startsWith("Guard")) {
        tmp = new Guard(time, Integer.valueOf(split[2].split(" ")[1].replaceAll("#", "")));
      } else if (split[2].startsWith("falls")) {
        tmp = new Sleep(time);
      } else if (split[2].startsWith("wakes")) {
        tmp = new Wake(time);
      } else {
        throw new IllegalArgumentException("Unreadable line");
      }

      return tmp;
    }
  }

  private interface IType {
    int SLEEP = -1, WAKE = -2;

    int getType();
  }

  private interface Rec<T> extends Comparable<T> {
    LocalDateTime getTime();

    int getType();

    default String getText() {
      return String.format("%s, %5d", this.getTime(), this.getType());
    }
  }

  private static abstract class Record implements Rec<Record> {
    private final LocalDateTime time;

    private Record(String input) {
      this.time = LocalDateTime.parse(input, Day4.FORMATTER);
    }

    @Override
    public LocalDateTime getTime() {
      return this.time;
    }

    @Override
    public int compareTo(Record o) {
      return this.time.compareTo(o.time);
    }
  }

  private static class Guard extends Record {
    private final int id;

    private Guard(String input, int id) {
      super(input);

      this.id = id;
    }

    @Override
    public int getType() {
      return id;
    }
  }

  private static class Sleep extends Record {
    private Sleep(String input) {
      super(input);
    }

    @Override
    public int getType() {
      return IType.SLEEP;
    }
  }

  private static class Wake extends Record {
    private Wake(String input) {
      super(input);
    }

    @Override
    public int getType() {
      return IType.WAKE;
    }
  }

  private Map<Integer, int[]> shared() {
    Set<Record> records = new TreeSet<>();

    for (String line : IDay4.INPUT.split("\n")) {
      records.add(RecordFactory.create(line));
    }

//    records.stream().forEach(x -> System.out.println(x.getText()));

    int[] row = null;
    Map<Integer, int[]> guards = new HashMap<>();
    Record s = null, w = null;

    for (Record record : records) {
      if (record.getType() != IType.SLEEP && record.getType() != IType.WAKE) {
        Guard guard = (Guard) record;

        if (guards.containsKey(guard.id)) {
          row = guards.get(guard.id);
        } else {
          row = new int[60];
        }

        guards.put(guard.id, row);
      } else if (record.getType() == IType.SLEEP) {
        s = record;
      } else if (record.getType() == IType.WAKE) {
        w = record;
      }

      if (row != null && s != null && w != null) {
        for (int i = s.time.getMinute(); i < w.time.getMinute(); i += 1) {
          row[i] += 1;
        }

        s = w = null;
      }
    }

    return guards;
  }
}

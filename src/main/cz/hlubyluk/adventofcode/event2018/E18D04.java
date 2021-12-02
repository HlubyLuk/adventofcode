package main.cz.hlubyluk.adventofcode.event2018;

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
public class E18D04 implements IE18D04 {
  private static class Guard extends Record {
    private final int id;

    private Guard(final String input, final int id) {
      super(input);

      this.id = id;
    }

    @Override
    public int getType() {
      return this.id;
    }
  }

  private interface IType {
    int SLEEP = -1, WAKE = -2;

    int getType();
  }

  private interface Rec<T> extends Comparable<T> {
    LocalDateTime getTime();

    int getType();
  }

  private static abstract class Record implements Rec<Record> {
    private final LocalDateTime time;

    private Record(final String input) {
      this.time = LocalDateTime.parse(input, E18D04.FORMATTER);
    }

    @Override
    public int compareTo(final Record o) {
      return this.time.compareTo(o.time);
    }

    @Override
    public LocalDateTime getTime() {
      return this.time;
    }
  }

  private static class RecordFactory {
    private static Record create(final String input) {
      Record tmp = null;

      final String[] split = input.split(" ", 3);
      final String time = String.format("%s %s", split[0], split[1]);

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

    private RecordFactory() {
    }
  }

  private static class Sleep extends Record {
    private Sleep(final String input) {
      super(input);
    }

    @Override
    public int getType() {
      return IType.SLEEP;
    }
  }

  private static class Wake extends Record {
    private Wake(final String input) {
      super(input);
    }

    @Override
    public int getType() {
      return IType.WAKE;
    }
  }

  private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().appendLiteral('[')
      .appendValue(ChronoField.YEAR).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral('-')
      .appendValue(ChronoField.DAY_OF_MONTH).appendLiteral(' ').appendValue(ChronoField.HOUR_OF_DAY).appendLiteral(':')
      .appendValue(ChronoField.MINUTE_OF_HOUR).appendLiteral(']').toFormatter();

//  @Override
//  public String getTag() {
//    return "2018 Day 4";
//  }

  private Map<Integer, int[]> shared() {
    final Set<Record> records = new TreeSet<>();

    for (final String line : IE18D04.INPUT.split("\n")) {
      records.add(RecordFactory.create(line));
    }

//    records.stream().forEach(x -> System.out.println(x.getText()));

    int[] row = null;
    final Map<Integer, int[]> guards = new HashMap<>();
    Record s = null, w = null;

    for (final Record record : records) {
      if (record.getType() != IType.SLEEP && record.getType() != IType.WAKE) {
        final Guard guard = (Guard) record;

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

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    int id = -1, index = -1, max = -1;

    for (final Map.Entry<Integer, int[]> entry : this.shared().entrySet()) {
      final int key = entry.getKey();
      final int[] value = entry.getValue();

      int count = 0;

      for (final int element : value) {
        count += element;
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
   * @see main.cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    int id = -1, index = -1, value = -1;

    for (final Map.Entry<Integer, int[]> entry : this.shared().entrySet()) {
      final int[] tmp = entry.getValue();

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
}

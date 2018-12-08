package cz.hlubyluk.adventofcode2018.day;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
  private static final String DOT = "............................................................";
//  private static final String TEN = "000000000011111111112222222222333333333344444444445555555555";
//  private static final String ONE = "012345678901234567890123456789012345678901234567890123456789";

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    Set<R> records = new TreeSet<>();

    for (String item : Day4.INPUT.split("\n")) {
      records.add(new R(item));
    }

    Map<Integer, Integer> cache = new TreeMap<>();
    Map<Integer, List<StringBuilder>> maxs = new TreeMap<>();
    StringBuilder row = null;

    R guard = null;
    R sleep = null;
    R wake = null;
    for (R item : records) {
      if (item.id != Integer.MIN_VALUE) {
        if (row != null && guard != null) {
          int cached = cache.getOrDefault(guard.id, 0);
          cache.put(guard.id, cached + row.toString().replaceAll("\\.", "").length());
        }

        guard = item;

        row = new StringBuilder(Day4.DOT);
      } else if (item.record.startsWith("falls")) {
        sleep = item;
      } else if (item.record.startsWith("wakes")) {
        wake = item;
      }

      if (guard != null && sleep != null && wake != null) {
        int i;
        for (i = sleep.time.getMinute(); i < wake.time.getMinute(); i += 1) {
          row.replace(i, i + 1, "#");
        }

        List<StringBuilder> get = maxs.getOrDefault(guard.id, new ArrayList<>());
        get.add(row);

        maxs.put(guard.id, get);

        sleep = wake = null;
      }
    }

    List<Entry<Integer, Integer>> collect = cache.entrySet().stream().sorted(Map.Entry.comparingByValue())
        .collect(Collectors.toList());
    Entry<Integer, Integer> entry = collect.get(collect.size() - 1);
    Integer key = entry.getKey();

//    System.out.println(Day4.TEN);
//    System.out.println(Day4.ONE);
//    maxs.get(key).stream().forEach(x -> System.out.printf("%s %d%n", x, x.toString().replaceAll("\\.", "").length()));

    int[] counts = new int[Day4.DOT.length()];

    for (StringBuilder item : maxs.get(key)) {
      for (int i = 0; i < counts.length; i += 1) {
        if (item.toString().charAt(i) == '#') {
          counts[i] += 1;
        }
      }
    }

    int max = -1, index = -1;

    for (int i = 0; i < counts.length; i += 1)
      if (max < counts[i]) {
        max = counts[i];
        index = i;
      }

    return String.valueOf(index * key.intValue());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    Set<Record> records = new TreeSet<>();

    for (String line : Day4.INPUT.split("\n")) {
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
          row = guard.counts;
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

//    guards.entrySet().stream().sorted(Map.Entry.comparingByKey())
//        .forEach(x -> System.out.printf("%5d, %s%n", x.getKey(), Arrays.toString(x.getValue())));

    int id = -1, index = -1, value = -1;

    for (int i : guards.keySet()) {
      int[] tmp = guards.get(i);

      for (int j = 0; j < tmp.length; j += 1) {
        if (value < tmp[j]) {
          id = i;
          index = j;
          value = tmp[j];
        }
      }
    }

    return String.valueOf(id * index);
  }

  private static class R implements Comparable<R> {
    private final LocalDateTime time;
    private final String record;
    private final int id;

    private R(String input) {
      String[] split = input.split(" ", 3);

      this.time = LocalDateTime.parse(String.format("%s %s", split[0], split[1]), Day4.FORMATTER);

      if (split[2].startsWith("Guard")) {
        this.record = null;
        this.id = Integer.parseInt(split[2].split(" ")[1].replaceAll("#", ""));
      } else {
        this.record = split[2];
        this.id = Integer.MIN_VALUE;
      }
    }

    @Override
    public int compareTo(R o) {
      return this.time.compareTo(o.time);
    }

    @Override
    public String toString() {
      return "Record [time=" + time + ", record=" + record + ", id=" + id + "]";
    }
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
    public final int[] counts = new int[60];

    private Guard(String input, int id) {
      super(input);

      this.id = id;
    }

    @Override
    public int getType() {
      return id;
    }

    @Override
    public String getText() {
      return String.format("%s %s", super.getText(), Arrays.toString(this.counts));
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
}

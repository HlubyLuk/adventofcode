package main.cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://adventofcode.com/2015/day/14
 *
 * @author HlubyLuk
 */
public class E15D14 implements IE15D14 {
  private static class Mapper {
    private static final Parser PARSER = new Parser();
    static {
      Mapper.PARSER.parse();
    }

    public int part1() {
      int max = Integer.MIN_VALUE;

      final Set<Reindeer> reindeers = Mapper.PARSER.getReindeers();
      for (final Reindeer reindeer : reindeers) {
        max = Math.max(max, reindeer.computeDistance(IE15D14.SECONDS));
      }

      return max;
    }

    public int part2() {
      final Map<Reindeer, Integer> leaderBoard = new HashMap<>();

      for (int currentSecond = 1; currentSecond <= IE15D14.SECONDS; currentSecond += 1) {
        final TreeMap<Integer, List<Reindeer>> round = new TreeMap<>();

        for (final Reindeer reindeer : Mapper.PARSER.getReindeers()) {
          final int distance = reindeer.computeDistance(currentSecond);

          final List<Reindeer> get = round.getOrDefault(distance, new ArrayList<>());
          get.add(reindeer);

          round.put(distance, get);
        }

        for (final Reindeer reindeer : round.lastEntry().getValue()) {
          final int points = leaderBoard.getOrDefault(reindeer, 0) + 1;

          leaderBoard.put(reindeer, points);
        }
      }

      return leaderBoard.entrySet().stream().sorted(Map.Entry.comparingByValue())
          .reduce((a, b) -> a.getValue() < b.getValue() ? b : a).orElseGet(() -> null).getValue();
    }
  }

  private static class Parser {
    private static final Pattern PATTERN = Pattern
        .compile("^(.*) can fly (.*) km/s for (.*) seconds, but then must rest for (.*) seconds.$");
    private final Set<Reindeer> reindeers = new HashSet<>();

    public Parser() {
    }

    public Set<Reindeer> getReindeers() {
      return this.reindeers;
    }

    public void parse() {
      final Scanner sc = new Scanner(IE15D14.INPUT);
      while (sc.hasNext()) {
        final String line = sc.nextLine();
        final Matcher m = Parser.PATTERN.matcher(line);

        if (m.find()) {
          final String name = m.group(1);
          final Integer speed = Integer.valueOf(m.group(2));
          final Integer time = Integer.valueOf(m.group(3));
          final Integer rest = Integer.valueOf(m.group(4));

          final Reindeer reindeer = new Reindeer(name, speed, time, rest);
          this.reindeers.add(reindeer);
        }
      }
      sc.close();
    }
  }

  private static class Reindeer {
    private final String name;
    private final int speed, time, rest;

    public Reindeer(final String name, final int speed, final int time, final int rest) {
      this.name = name;
      this.speed = speed;
      this.time = time;
      this.rest = rest;
    }

    public int computeDistance(final int time) {
      final int count = time / (this.time + this.rest);
      final int rest = time % (this.time + this.rest);

      return count * this.speed * this.time + this.speed * Math.min(this.time, rest);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Reindeer)) {
        return false;
      }
      final Reindeer other = (Reindeer) obj;
      if (this.name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!this.name.equals(other.name)) {
        return false;
      }
      if (this.rest != other.rest) {
        return false;
      }
      if (this.speed != other.speed) {
        return false;
      }
      if (this.time != other.time) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (this.name == null ? 0 : this.name.hashCode());
      result = prime * result + this.rest;
      result = prime * result + this.speed;
      result = prime * result + this.time;
      return result;
    }

    @Override
    public String toString() {
      return "Reindeer [name=" + this.name + ", speed=" + this.speed + ", time=" + this.time + ", rest=" + this.rest
          + "]";
    }
  }

  private static final Mapper MAPPER = new Mapper();

//  /*
//   * (non-Javadoc)
//   *
//   * @see main.cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 14";
//  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(2655, E15D14.MAPPER.part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(1059, E15D14.MAPPER.part2());
  }
}

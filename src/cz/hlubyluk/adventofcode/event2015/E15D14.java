package cz.hlubyluk.adventofcode.event2015;

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

import cz.hlubyluk.adventofcode.event2015.input.IE15D14;

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

      Set<Reindeer> reindeers = Mapper.PARSER.getReindeers();
      for (Reindeer reindeer : reindeers) {
        max = Math.max(max, reindeer.computeDistance(IE15D14.SECONDS));
      }

      return max;
    }

    public int part2() {
      Map<Reindeer, Integer> leaderBoard = new HashMap<>();

      for (int currentSecond = 1; currentSecond <= IE15D14.SECONDS; currentSecond += 1) {
        TreeMap<Integer, List<Reindeer>> round = new TreeMap<>();

        for (Reindeer reindeer : Mapper.PARSER.getReindeers()) {
          int distance = reindeer.computeDistance(currentSecond);

          List<Reindeer> get = round.getOrDefault(distance, new ArrayList<>());
          get.add(reindeer);

          round.put(distance, get);
        }

        for (Reindeer reindeer : round.lastEntry().getValue()) {
          int points = leaderBoard.getOrDefault(reindeer, 0) + 1;

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
      Scanner sc = new Scanner(IE15D14.INPUT);
      while (sc.hasNext()) {
        String line = sc.nextLine();
        Matcher m = Parser.PATTERN.matcher(line);

        if (m.find()) {
          String name = m.group(1);
          Integer speed = Integer.valueOf(m.group(2));
          Integer time = Integer.valueOf(m.group(3));
          Integer rest = Integer.valueOf(m.group(4));

          Reindeer reindeer = new Reindeer(name, speed, time, rest);
          this.reindeers.add(reindeer);
        }
      }
      sc.close();
    }
  }

  private static class Reindeer {
    private final String name;
    private final int speed, time, rest;

    public Reindeer(String name, int speed, int time, int rest) {
      this.name = name;
      this.speed = speed;
      this.time = time;
      this.rest = rest;
    }

    public int computeDistance(int time) {
      int count = time / (this.time + this.rest);
      int rest = time % (this.time + this.rest);

      return count * this.speed * this.time + this.speed * Math.min(this.time, rest);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Reindeer)) {
        return false;
      }
      Reindeer other = (Reindeer) obj;
      if (name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!name.equals(other.name)) {
        return false;
      }
      if (rest != other.rest) {
        return false;
      }
      if (speed != other.speed) {
        return false;
      }
      if (time != other.time) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + rest;
      result = prime * result + speed;
      result = prime * result + time;
      return result;
    }

    @Override
    public String toString() {
      return "Reindeer [name=" + name + ", speed=" + speed + ", time=" + time + ", rest=" + rest + "]";
    }
  }

  private static final Mapper MAPPER = new Mapper();

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 14";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(2655, E15D14.MAPPER.part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(1059, E15D14.MAPPER.part2());
  }
}

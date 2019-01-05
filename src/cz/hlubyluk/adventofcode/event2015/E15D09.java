package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import cz.hlubyluk.adventofcode.Utils;
import cz.hlubyluk.adventofcode.event2015.input.IE15D09;

/**
 * https://adventofcode.com/2015/day/9
 *
 * @author HlubyLuk
 */
public class E15D09 implements IE15D09 {

  private static class CB implements Utils.Generator.Permutation<City> {
    private interface F {
      int f(int a, int b);
    }

    private final Map<Pair, Integer> cache;
    private final F lambda;

    private int result;

    private CB(final Map<Pair, Integer> cache, final F lambda, final int init) {
      this.cache = cache;
      this.lambda = lambda;
      this.result = init;
    }

    @Override
    public void compute(final List<City> list) {
      int current = 0;

      for (int i = 1; i < list.size(); i += 1) {
        current += this.cache.get(Pair.create(list.get(i - 1), list.get(i)));
      }

      this.result = this.lambda.f(this.result, current);
    }

    public int getResult() {
      return this.result;
    }
  }

  private static class City implements Comparable<City> {
    private final String name;

    public City(final String name) {
      this.name = name;
    }

    @Override
    public int compareTo(final City o) {
      return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof City)) {
        return false;
      }
      final City other = (City) obj;
      if (this.name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!this.name.equals(other.name)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (this.name == null ? 0 : this.name.hashCode());
      return result;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  private static class Distance {
    private final int d;
    private final City f, t;

    public Distance(final City from, final City to, final int distance) {
      this.f = from;
      this.t = to;
      this.d = distance;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Distance)) {
        return false;
      }
      final Distance other = (Distance) obj;
      if (this.d != other.d) {
        return false;
      }
      if (this.f == null) {
        if (other.f != null) {
          return false;
        }
      } else if (!this.f.equals(other.f)) {
        return false;
      }
      if (this.t == null) {
        if (other.t != null) {
          return false;
        }
      } else if (!this.t.equals(other.t)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.d;
      result = prime * result + (this.f == null ? 0 : this.f.hashCode());
      result = prime * result + (this.t == null ? 0 : this.t.hashCode());
      return result;
    }

    @Override
    public String toString() {
      return "Distance [f=" + this.f + ", t=" + this.t + ", d=" + this.d + "]";
    }
  }

  private static class Pair implements Comparable<Pair> {
    public static Pair create(final City a, final City b) {
      return new Pair(a, b);
    }

    private final City a, b;

    private Pair(final City a, final City b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public int compareTo(final Pair o) {
      final int x = this.a.compareTo(o.a) * 100 + this.b.compareTo(o.b);

      return x < 0 ? -1 : x == 0 ? 0 : 1;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Pair)) {
        return false;
      }
      final Pair other = (Pair) obj;
      if (this.a == null) {
        if (other.a != null) {
          return false;
        }
      } else if (!this.a.equals(other.a)) {
        return false;
      }
      if (this.b == null) {
        if (other.b != null) {
          return false;
        }
      } else if (!this.b.equals(other.b)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (this.a == null ? 0 : this.a.hashCode());
      result = prime * result + (this.b == null ? 0 : this.b.hashCode());
      return result;
    }

    @Override
    public String toString() {
      return "Pair [a=" + this.a + ", b=" + this.b + "]";
    }
  }

  private static class Parser {
    private final Set<City> cities = new TreeSet<>();
    private final Set<Distance> distances = new HashSet<>();

    public Parser() {
    }

    public Set<City> getCities() {
      return this.cities;
    }

    public Set<Distance> getDistances() {
      return this.distances;
    }

    public void parse() {

      final Scanner scLine = new Scanner(IE15D09.INPUT);
      while (scLine.hasNextLine()) {
        City from = null, to = null;
        int distance = Integer.MIN_VALUE;

        int counter = 0;

        final Scanner scWords = new Scanner(scLine.nextLine());
        while (scWords.hasNext()) {
          switch (counter) {
          case 0:
            from = new City(scWords.next());
            break;

          case 2:
            to = new City(scWords.next());
            break;

          case 4:
            distance = scWords.nextInt();
            break;

          default:
            scWords.next();
          }

          counter += 1;
        }
        scWords.close();

        this.distances.add(new Distance(from, to, distance));
        this.cities.add(from);
        this.cities.add(to);
      }
      scLine.close();
    }
  }

  private static class Solver {
    private static final Parser PARSER = new Parser();
    static {
      Solver.PARSER.parse();
    }

    public Solver() {
    }

    private Map<Pair, Integer> buildCache() {
      final Map<Pair, Integer> map = new TreeMap<>();

      for (final City city : Solver.PARSER.getCities()) {
        Solver.PARSER.getDistances().stream().filter(x -> x.f.equals(city) || x.t.equals(city)).forEach(x -> {
          final City other = city.equals(x.f) ? x.t : x.f;
          map.put(Pair.create(city, other), x.d);
        });
      }

      return map;
    }

    public int part1() {
      final CB lambda = new CB(this.buildCache(), Math::min, Integer.MAX_VALUE);
      Utils.Generator.heapPermutation(new ArrayList<>(Solver.PARSER.getCities()), lambda);
      return lambda.getResult();
    }

    public int part2() {
      final CB lambda = new CB(this.buildCache(), Math::max, Integer.MIN_VALUE);
      Utils.Generator.heapPermutation(new ArrayList<>(Solver.PARSER.getCities()), lambda);
      return lambda.getResult();
    }
  }

  private static final Solver SOLVER = new Solver();

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 9";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
//    int result = new Solver().part1();
//
//    if (result != 207) {
//      throw new RuntimeException(String.format("Result %d is wrong!!!", result));
//    }
//
//    return String.valueOf(result);
    return this.result(207, E15D09.SOLVER.part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
//    int result = new Solver().part2();
//
//    if (result != 804) {
//      throw new RuntimeException(String.format("Result %d is wrong!!!", result));
//    }
//
//    return String.valueOf(result);
    return this.result(804, E15D09.SOLVER.part2());
  }
}

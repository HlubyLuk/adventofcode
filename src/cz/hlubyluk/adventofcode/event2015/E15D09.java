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

  private static class City implements Comparable<City> {
    private final String name;

    public City(String name) {
      this.name = name;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof City)) {
        return false;
      }
      City other = (City) obj;
      if (name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!name.equals(other.name)) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return this.name;
    }

    @Override
    public int compareTo(City o) {
      return this.name.compareTo(o.name);
    }
  }

  private static class Distance {
    private final City f, t;
    private final int d;

    public Distance(City from, City to, int distance) {
      this.f = from;
      this.t = to;
      this.d = distance;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + d;
      result = prime * result + ((f == null) ? 0 : f.hashCode());
      result = prime * result + ((t == null) ? 0 : t.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Distance)) {
        return false;
      }
      Distance other = (Distance) obj;
      if (d != other.d) {
        return false;
      }
      if (f == null) {
        if (other.f != null) {
          return false;
        }
      } else if (!f.equals(other.f)) {
        return false;
      }
      if (t == null) {
        if (other.t != null) {
          return false;
        }
      } else if (!t.equals(other.t)) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "Distance [f=" + f + ", t=" + t + ", d=" + d + "]";
    }
  }

  private static class Parser {
    private final Set<City> cities = new TreeSet<>();
    private final Set<Distance> distances = new HashSet<>();

    public Parser() {
    }

    public void parse() {

      Scanner scLine = new Scanner(IE15D09.INPUT);
      while (scLine.hasNextLine()) {
        City from = null, to = null;
        int distance = Integer.MIN_VALUE;

        int counter = 0;

        Scanner scWords = new Scanner(scLine.nextLine());
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

    public Set<City> getCities() {
      return cities;
    }

    public Set<Distance> getDistances() {
      return distances;
    }
  }

  private static class Solver {
    private static final Parser PARSER = new Parser();
    static {
      Solver.PARSER.parse();
    }

    public Solver() {
    }

    public int part1() {
      CB lambda = new CB(this.buildCache(), Math::min, Integer.MAX_VALUE);
      Utils.Generator.heapPermutation(new ArrayList<>(Solver.PARSER.getCities()), lambda);
      return lambda.getResult();
    }

    public int part2() {
      CB lambda = new CB(this.buildCache(), Math::max, Integer.MIN_VALUE);
      Utils.Generator.heapPermutation(new ArrayList<>(Solver.PARSER.getCities()), lambda);
      return lambda.getResult();
    }

    private Map<Pair, Integer> buildCache() {
      Map<Pair, Integer> map = new TreeMap<>();

      for (City city : Solver.PARSER.getCities()) {
        Solver.PARSER.getDistances().stream().filter(x -> x.f.equals(city) || x.t.equals(city)).forEach(x -> {
          City other = city.equals(x.f) ? x.t : x.f;
          map.put(Pair.create(city, other), x.d);
        });
      }

      return map;
    }
  }

  private static class CB implements Utils.Generator.Permutation<City> {
    private int result;
    private final Map<Pair, Integer> cache;
    private final F lambda;

    private CB(Map<Pair, Integer> cache, F lambda, int init) {
      this.cache = cache;
      this.lambda = lambda;
      this.result = init;
    }

    public int getResult() {
      return this.result;
    }

    @Override
    public void compute(List<City> list) {
      int current = 0;

      for (int i = 1; i < list.size(); i += 1) {
        current += this.cache.get(Pair.create(list.get(i - 1), list.get(i)));
      }

      this.result = this.lambda.f(this.result, current);
    }

    private interface F {
      int f(int a, int b);
    }
  }

  private static class Pair implements Comparable<Pair> {
    private final City a, b;

    public static Pair create(City a, City b) {
      return new Pair(a, b);
    }

    private Pair(City a, City b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((a == null) ? 0 : a.hashCode());
      result = prime * result + ((b == null) ? 0 : b.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Pair)) {
        return false;
      }
      Pair other = (Pair) obj;
      if (a == null) {
        if (other.a != null) {
          return false;
        }
      } else if (!a.equals(other.a)) {
        return false;
      }
      if (b == null) {
        if (other.b != null) {
          return false;
        }
      } else if (!b.equals(other.b)) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "Pair [a=" + a + ", b=" + b + "]";
    }

    @Override
    public int compareTo(Pair o) {
      int x = this.a.compareTo(o.a) * 100 + this.b.compareTo(o.b);

      return x < 0 ? -1 : x == 0 ? 0 : 1;
    }
  }

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
    int result = new Solver().part1();

    if (result != 207) {
      throw new RuntimeException(String.format("Result %d is wrong!!!", result));
    }

    return String.valueOf(result);
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    int result = new Solver().part2();

    if (result != 804) {
      throw new RuntimeException(String.format("Result %d is wrong!!!", result));
    }

    return String.valueOf(result);
  }
}

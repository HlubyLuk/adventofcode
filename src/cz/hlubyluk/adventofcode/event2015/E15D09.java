package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
    public Solver() {
    }

    public int solve() {
      int min = Integer.MAX_VALUE;

      Parser parser = new Parser();
      parser.parse();

      Map<Pair, Integer> map = new TreeMap<>();
      Set<Distance> distances = parser.getDistances();
      Set<City> cities = parser.getCities();

      for (City city : cities) {
        distances.stream().filter(x -> x.f.equals(city) || x.t.equals(city)).forEach(x -> {
          City other = city.equals(x.f) ? x.t : x.f;
          map.put(Pair.create(city, other), x.d);
        });
      }

      for (City city : cities) {
        List<City> into = new ArrayList<>();
        into.add(city);
        List<Integer> units = new ArrayList<>();

        this.s(city, map.entrySet(), into, units);

        Integer current = units.stream().reduce((x, y) -> x + y).orElseGet(() -> Integer.MAX_VALUE);

        min = Math.min(min, current);
      }

      return min;
    }

    private void s(City city, Set<Entry<Pair, Integer>> entries, List<City> into, List<Integer> units) {
      Entry<Pair, Integer> next = entries.stream()
          .filter(x -> city.equals(x.getKey().a) && !into.contains(x.getKey().b))
          .reduce((x, y) -> x.getValue() < y.getValue() ? x : y).orElseGet(() -> null);

      if (next != null) {
        into.add(next.getKey().b);
        units.add(next.getValue());

        this.s(next.getKey().b, entries, into, units);
      }
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
    return String.valueOf(new Solver().solve());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }
}

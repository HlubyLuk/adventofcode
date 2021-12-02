/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.cz.hlubyluk.adventofcode.IDay;
import main.cz.hlubyluk.adventofcode.Utils;
import main.cz.hlubyluk.adventofcode.Utils.Same;

/**
 * https://adventofcode.com/2015/day/13
 *
 * @author HlubyLuk
 */
public class E15D13 implements IE15D13 {
  private static class Mapper {
    private static final Parser PARSER = new Parser();
    static {
      Mapper.PARSER.parse();
    }

    public Map<Same<Name>, Integer> cache() {
      final Map<Same<Name>, Integer> cache = new HashMap<>();

      final Set<Relation> relations = Mapper.PARSER.getRelations();
      for (final Relation rel : relations) {
        cache.put(new Same<>(rel.a, rel.b), rel.unit);
      }

      return cache;
    }

    public int map1() {
      final Map<Same<Name>, Integer> cache = this.cache();
      return this.solveMax(cache, Mapper.PARSER.getNames());
    }

    public int map2() {
      final Map<Same<Name>, Integer> cache = this.cache();
      final List<Name> names = Mapper.PARSER.getNames();

      final Name me = new Name("me");
      for (final Name name : names) {
        cache.put(new Same<>(me, name), 0);
        cache.put(new Same<>(name, me), 0);
      }
      names.add(me);

      return this.solveMax(cache, names);
    }

    private int solveMax(final Map<Same<Name>, Integer> cache, final List<Name> names) {
      final Max l = new Max(cache);
      Utils.Generator.heapPermutation(names, l);
      return l.getMax();
    }
  }

  private static class Max implements Utils.Generator.Permutation<Name> {
    private final Map<Same<Name>, Integer> cache;
    private int max = IDay.NOT_IMPLEMENT;

    public Max(final Map<Same<Name>, Integer> cache) {
      this.cache = cache;
    }

    @Override
    public void compute(final List<Name> list) {
      int units = 0;
      final int size = list.size();
      for (int i = 1; i <= size; i += 1) {
        final int indexA = (i - 1) % size;
        final int indexB = i % size;

        final Name a = list.get(indexA);
        final Name b = list.get(indexB);

        final Same<Name> one = new Same<>(b, a);
        final Same<Name> two = new Same<>(a, b);

        final int getOne = this.cache.get(one);
        final int getTwo = this.cache.get(two);

        units += getOne + getTwo;
      }

      this.max = Math.max(this.max, units);
    }

    public int getMax() {
      return this.max;
    }
  }

  private static class Name implements Comparable<Name> {
    private final String name;

    public Name(final String name) {
      this.name = name;
    }

    @Override
    public int compareTo(final Name o) {
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
      if (!(obj instanceof Name)) {
        return false;
      }
      final Name other = (Name) obj;
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

  private static class Parser {
    private static final String GAIN = "gain", LOSE = "lose";
    private static final Pattern LINE = Pattern
        .compile("^(.*) would (.*) (.*) happiness units by sitting next to (.*).$");
    private final Set<Name> names = new HashSet<>();
    private final Set<Relation> relations = new HashSet<>();

    public Parser() {
    }

    public List<Name> getNames() {
      return new ArrayList<>(this.names);
    }

    public Set<Relation> getRelations() {
      return this.relations;
    }

    public void parse() {
      final Scanner sc = new Scanner(IE15D13.INPUT);
      while (sc.hasNext()) {
        final String line = sc.nextLine();

        final Matcher matcher = Parser.LINE.matcher(line);
        if (matcher.find()) {
          final Name nameA = new Name(matcher.group(1));

          this.names.add(nameA);

          final Name nameB = new Name(matcher.group(4));

          final boolean gain = Parser.GAIN.equals(matcher.group(2));
          final boolean lose = Parser.LOSE.equals(matcher.group(2));
          final String sign = gain ? "+" : lose ? "-" : "?";

          final int unit = Integer.valueOf(String.format("%s%s", sign, matcher.group(3)));

          this.relations.add(new Relation(nameA, nameB, unit));
        }
      }
      sc.close();
    }
  }

  private static class Relation {
    private final Name a, b;
    private final int unit;

    public Relation(final Name a, final Name b, final int unit) {
      this.a = a;
      this.b = b;
      this.unit = unit;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Relation)) {
        return false;
      }
      final Relation other = (Relation) obj;
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
      if (this.unit != other.unit) {
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
      result = prime * result + this.unit;
      return result;
    }

    @Override
    public String toString() {
      return "Relation [a=" + this.a + ", b=" + this.b + ", unit=" + this.unit + "]";
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
//    return "2015 day 13";
//  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
//    int result = new Mapper().map1();
//
//    if (result != 709) {
//      throw new RuntimeException("Wrong!!!");
//    }
//
//    return String.valueOf(result);
    return this.result(709, E15D13.MAPPER.map1());
  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
//    int result = new Mapper().map2();
//
//    if (result != 668) {
//      throw new RuntimeException("Wrong!!!");
//    }
//
//    return String.valueOf(result);
    return this.result(668, E15D13.MAPPER.map2());
  }
}

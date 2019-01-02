/**
 * 
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.IDay;
import cz.hlubyluk.adventofcode.Utils;
import cz.hlubyluk.adventofcode.Utils.Same;
import cz.hlubyluk.adventofcode.event2015.input.IE15D13;

/**
 * https://adventofcode.com/2015/day/13
 *
 * @author HlubyLuk
 */
public class E15D13 implements IE15D13 {
  private static class Relation {
    private final Name a, b;
    private final int unit;

    public Relation(Name a, Name b, int unit) {
      this.a = a;
      this.b = b;
      this.unit = unit;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((a == null) ? 0 : a.hashCode());
      result = prime * result + ((b == null) ? 0 : b.hashCode());
      result = prime * result + unit;
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
      if (!(obj instanceof Relation)) {
        return false;
      }
      Relation other = (Relation) obj;
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
      if (unit != other.unit) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "Relation [a=" + a + ", b=" + b + ", unit=" + unit + "]";
    }
  }

  private static class Name implements Comparable<Name> {
    private final String name;

    public Name(String name) {
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
      if (!(obj instanceof Name)) {
        return false;
      }
      Name other = (Name) obj;
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
    public int compareTo(Name o) {
      return this.name.compareTo(o.name);
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

    public void parse() {
      Scanner sc = new Scanner(IE15D13.INPUT);
      while (sc.hasNext()) {
        String line = sc.nextLine();

        Matcher matcher = Parser.LINE.matcher(line);
        if (matcher.find()) {
          Name nameA = new Name(matcher.group(1));

          this.names.add(nameA);

          Name nameB = new Name(matcher.group(4));

          boolean gain = Parser.GAIN.equals(matcher.group(2));
          boolean lose = Parser.LOSE.equals(matcher.group(2));
          String sign = gain ? "+" : lose ? "-" : "?";

          int unit = Integer.valueOf(String.format("%s%s", sign, matcher.group(3)));

          this.relations.add(new Relation(nameA, nameB, unit));
        }
      }
      sc.close();
    }

    public List<Name> getNames() {
      return new ArrayList<>(this.names);
    }

    public Set<Relation> getRelations() {
      return relations;
    }
  }

  private static class Mapper {
    private static final Parser PARSER = new Parser();
    static {
      Mapper.PARSER.parse();
    }

    public Map<Same<Name>, Integer> cache() {
      Map<Same<Name>, Integer> cache = new HashMap<>();

      Set<Relation> relations = Mapper.PARSER.getRelations();
      for (Relation rel : relations) {
        cache.put(new Same<E15D13.Name>(rel.a, rel.b), rel.unit);
      }

      return cache;
    }

    public int map1() {
      Map<Same<Name>, Integer> cache = this.cache();
      return this.solveMax(cache, Mapper.PARSER.getNames());
    }

    public int map2() {
      Map<Same<Name>, Integer> cache = this.cache();
      List<Name> names = Mapper.PARSER.getNames();

      Name me = new Name("me");
      for (Name name : names) {
        cache.put(new Same<E15D13.Name>(me, name), 0);
        cache.put(new Same<E15D13.Name>(name, me), 0);
      }
      names.add(me);

      return this.solveMax(cache, names);
    }

    private int solveMax(Map<Same<Name>, Integer> cache, List<Name> names) {
      Max l = new Max(cache);
      Utils.Generator.heapPermutation(names, l);
      return l.getMax();
    }
  }

  private static class Max implements Utils.Generator.Permutation<Name> {
    private int max = IDay.NOT_IMPLEMENT;
    private final Map<Same<Name>, Integer> cache;

    public Max(Map<Same<Name>, Integer> cache) {
      this.cache = cache;
    }

    @Override
    public void compute(List<Name> list) {
      int units = 0;
      int size = list.size();
      for (int i = 1; i <= size; i += 1) {
        int indexA = (i - 1) % size;
        int indexB = i % size;

        Name a = list.get(indexA);
        Name b = list.get(indexB);

        Same<Name> one = new Same<>(b, a);
        Same<Name> two = new Same<>(a, b);

        int getOne = this.cache.get(one);
        int getTwo = this.cache.get(two);

        units += getOne + getTwo;
      }

      this.max = Math.max(this.max, units);
    }

    public int getMax() {
      return max;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 13";
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    int result = new Mapper().map1();

    if (result != 709) {
      throw new RuntimeException("Wrong!!!");
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
    int result = new Mapper().map2();

    if (result != 668) {
      throw new RuntimeException("Wrong!!!");
    }

    return String.valueOf(result);
  }
}

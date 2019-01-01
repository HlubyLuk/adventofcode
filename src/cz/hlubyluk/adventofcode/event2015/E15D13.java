/**
 * 
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.IDay;
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
    private final Set<Name> names = new TreeSet<>();
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

    public Set<Name> getNames() {
      return names;
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

    public int map() {
      Map<Same<Name>, Integer> cache = new HashMap<>();

      Set<Relation> relations = Mapper.PARSER.getRelations();
      for (Relation rel : relations) {
        cache.put(new Same<E15D13.Name>(rel.a, rel.b), rel.unit);
      }

      int max = IDay.NOT_IMPLEMENT;

      List<Name> names = new ArrayList<>(Mapper.PARSER.getNames());

      do {
        int units = 0;
        int size = names.size();
        for (int i = 1; i <= size; i += 1) {
          int indexA = (i - 1) % size;
          int indexB = i % size;
//          int indexC = (i + 1) % size;

          Name a = names.get(indexA);
          Name b = names.get(indexB);
//          Name c = names.get(indexC);

          Same<Name> left = new Same<>(b, a);
          Same<Name> right = new Same<>(a, b);

          Integer getA = cache.get(left);
          Integer getC = cache.get(right);

          units += getA + getC;

        }

        max = Math.max(max, units);
      } while (E15D13.nextPermutation(names));

      return max;
    }
  }

  /* Generic list version */
  public static <T extends Comparable<? super T>> boolean nextPermutation(List<T> array) {
    // Find non-increasing suffix
    int i = array.size() - 1;
    while (i > 0 && array.get(i - 1).compareTo(array.get(i)) >= 0)
      i--;
    if (i <= 0)
      return false;

    // Find successor to pivot
    int j = array.size() - 1;
    while (array.get(j).compareTo(array.get(i - 1)) <= 0)
      j--;
    Collections.swap(array, i - 1, j);

    // Reverse suffix
    Collections.reverse(array.subList(i, array.size()));
    return true;
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
    // Wrong!!!
    // 234
    // 347
    return String.valueOf(new Mapper().map());
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

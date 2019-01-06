/**
 *
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.event2015.input.IE15D15;

/**
 * https://adventofcode.com/2015/day/15
 *
 * @author HlubyLuk
 *
 */
public class E15D15 implements IE15D15 {
  private static class Ingredient {
    private final String name;
    private final int capacity, durability, flavor, texture, calories;

    public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
      this.name = name;
      this.capacity = capacity;
      this.durability = durability;
      this.flavor = flavor;
      this.texture = texture;
      this.calories = calories;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + calories;
      result = prime * result + capacity;
      result = prime * result + durability;
      result = prime * result + flavor;
      result = prime * result + texture;
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
      if (!(obj instanceof Ingredient)) {
        return false;
      }
      Ingredient other = (Ingredient) obj;
      if (calories != other.calories) {
        return false;
      }
      if (capacity != other.capacity) {
        return false;
      }
      if (durability != other.durability) {
        return false;
      }
      if (flavor != other.flavor) {
        return false;
      }
      if (texture != other.texture) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "Ingredient [capacity=" + capacity + ", durability=" + durability + ", flavor=" + flavor + ", texture="
          + texture + ", calories=" + calories + "]";
    }
  }

  private static class Parser {
    private static final Pattern PATTERN = Pattern.compile(
        "^(\\w*): capacity (-?\\d*), durability (-?\\d*), flavor (-?\\d*), texture (-?\\d*), calories (-?\\d*)$");
    private final List<Ingredient> ingredients = new ArrayList<>();

    public Parser() {
    }

    public List<Ingredient> getIngredients() {
      return ingredients;
    }

    public void parse() {
      Scanner sc = new Scanner(IE15D15.INPUT);
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        Matcher matcher = Parser.PATTERN.matcher(line);

        if (matcher.find()) {
          String name = matcher.group(1);
          int capacity = Integer.valueOf(matcher.group(2));
          int durability = Integer.valueOf(matcher.group(3));
          int flavor = Integer.valueOf(matcher.group(4));
          int texture = Integer.valueOf(matcher.group(5));
          int calories = Integer.valueOf(matcher.group(6));

          this.ingredients.add(new Ingredient(name, capacity, durability, flavor, texture, calories));
        }
      }
      sc.close();
    }
  }

  private static class Solver {
    private static final int SPOONS = 100;
    private static final Parser PARSER = new Parser();
    static {
      Solver.PARSER.parse();
    }

    public Solver() {
    }

    public long part1() {
      long max = 0;
      List<Ingredient> list = Solver.PARSER.getIngredients();

      for (int a = 0; a <= Solver.SPOONS; a += 1) {
//        int b = Solver.SPOONS - a;
        for (int b = 0; b <= Solver.SPOONS - a; b += 1) {
          for (int c = 0; c <= Solver.SPOONS - a - b; c += 1) {
            int d = Solver.SPOONS - a - b - c;

            int capacity = 0, durability = 0, flavor = 0, texture = 0;

            Ingredient i0 = list.get(0);
            Ingredient i1 = list.get(1);
            Ingredient i2 = list.get(2);
            Ingredient i3 = list.get(3);

            capacity = Math.max(i0.capacity * a + i1.capacity * b + i2.capacity * c + i3.capacity * d, 0);
            durability = Math.max(i0.durability * a + i1.durability * b + i2.durability * c + i3.durability * d, 0);
            flavor = Math.max(i0.flavor * a + i1.flavor * b + i2.flavor * c + i3.flavor * d, 0);
            texture = Math.max(i0.texture * a + i1.texture * b + i2.texture * c + i3.texture * d, 0);

            max = Math.max(max, capacity * durability * flavor * texture);
          }
        }
      }

      return max;
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
    return "2015 day 15";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(222870, E15D15.SOLVER.part1());
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

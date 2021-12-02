/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://adventofcode.com/2015/day/15
 *
 * @author HlubyLuk
 *
 */
public class E15D15 implements IE15D15 {
  private static class Ingredient {
    private final int capacity, durability, flavor, texture, calories;

    public Ingredient(final String name, final int capacity, final int durability, final int flavor, final int texture,
        final int calories) {
      this.capacity = capacity;
      this.durability = durability;
      this.flavor = flavor;
      this.texture = texture;
      this.calories = calories;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Ingredient)) {
        return false;
      }
      final Ingredient other = (Ingredient) obj;
      if (this.calories != other.calories) {
        return false;
      }
      if (this.capacity != other.capacity) {
        return false;
      }
      if (this.durability != other.durability) {
        return false;
      }
      if (this.flavor != other.flavor) {
        return false;
      }
      if (this.texture != other.texture) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.calories;
      result = prime * result + this.capacity;
      result = prime * result + this.durability;
      result = prime * result + this.flavor;
      result = prime * result + this.texture;
      return result;
    }

    @Override
    public String toString() {
      return "Ingredient [capacity=" + this.capacity + ", durability=" + this.durability + ", flavor=" + this.flavor
          + ", texture=" + this.texture + ", calories=" + this.calories + "]";
    }
  }

  private static class Parser {
    private static final Pattern PATTERN = Pattern.compile(
        "^(\\w*): capacity (-?\\d*), durability (-?\\d*), flavor (-?\\d*), texture (-?\\d*), calories (-?\\d*)$");
    private final List<Ingredient> ingredients = new ArrayList<>();

    public Parser() {
    }

    public List<Ingredient> getIngredients() {
      return this.ingredients;
    }

    public void parse() {
      final Scanner sc = new Scanner(IE15D15.INPUT);
      while (sc.hasNextLine()) {
        final String line = sc.nextLine();
        final Matcher matcher = Parser.PATTERN.matcher(line);

        if (matcher.find()) {
          final String name = matcher.group(1);
          final int capacity = Integer.valueOf(matcher.group(2));
          final int durability = Integer.valueOf(matcher.group(3));
          final int flavor = Integer.valueOf(matcher.group(4));
          final int texture = Integer.valueOf(matcher.group(5));
          final int calories = Integer.valueOf(matcher.group(6));

          this.ingredients.add(new Ingredient(name, capacity, durability, flavor, texture, calories));
        }
      }
      sc.close();
    }
  }

  private static class Solver {
    private static final Parser PARSER = new Parser();
    private static final int SPOONS = 100, CALORIES = 500;
    static {
      Solver.PARSER.parse();
    }
    List<Ingredient> ingredients = Solver.PARSER.getIngredients();

    public Solver() {
    }

    private long cookie(final int a, final int b, final int c, final int d) {
      final Ingredient i0 = this.ingredients.get(0);
      final Ingredient i1 = this.ingredients.get(1);
      final Ingredient i2 = this.ingredients.get(2);
      final Ingredient i3 = this.ingredients.get(3);

      int capacity = 0, durability = 0, flavor = 0, texture = 0;

      capacity = Math.max(i0.capacity * a + i1.capacity * b + i2.capacity * c + i3.capacity * d, 0);
      durability = Math.max(i0.durability * a + i1.durability * b + i2.durability * c + i3.durability * d, 0);
      flavor = Math.max(i0.flavor * a + i1.flavor * b + i2.flavor * c + i3.flavor * d, 0);
      texture = Math.max(i0.texture * a + i1.texture * b + i2.texture * c + i3.texture * d, 0);

      return capacity * durability * flavor * texture;
    }

    public long part1() {
      long max = 0;

      for (int a = 0; a <= Solver.SPOONS; a += 1) {
        for (int b = 0; b <= Solver.SPOONS - a; b += 1) {
          for (int c = 0; c <= Solver.SPOONS - a - b; c += 1) {
            final int d = Solver.SPOONS - a - b - c;

            max = Math.max(max, this.cookie(a, b, c, d));
          }
        }
      }

      return max;
    }

    public long part2() {
      long max = 0;

      for (int a = 0; a <= Solver.SPOONS; a += 1) {
        for (int b = 0; b <= Solver.SPOONS - a; b += 1) {
          for (int c = 0; c <= Solver.SPOONS - a - b; c += 1) {
            final int d = Solver.SPOONS - a - b - c;

            final Ingredient i0 = this.ingredients.get(0);
            final Ingredient i1 = this.ingredients.get(1);
            final Ingredient i2 = this.ingredients.get(2);
            final Ingredient i3 = this.ingredients.get(3);

            if (i0.calories * a + i1.calories * b + i2.calories * c + i3.calories * d == Solver.CALORIES) {
              max = Math.max(max, this.cookie(a, b, c, d));
            }
          }
        }
      }

      return max;
    }
  }

  private static final Solver SOLVER = new Solver();

//  /*
//   * (non-Javadoc)
//   *
//   * @see main.cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 15";
//  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(222870, E15D15.SOLVER.part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(117936, E15D15.SOLVER.part2());
  }
}

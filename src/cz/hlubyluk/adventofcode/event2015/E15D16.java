/**
 *
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.IDay;
import cz.hlubyluk.adventofcode.Utils.Same;

/**
 * https://adventofcode.com/2015/day/16
 *
 * @author HlubyLuk
 */
public class E15D16 implements IE15D16 {
  private static class Analyze {
    private final List<Same<String>> values = new ArrayList<>();

    public Analyze() {
    }

    public void add(final String property, final String result) {
      this.values.add(new Same<>(property, result));
    }

    @Override
    public String toString() {
      return "Analyze [values=" + this.values + "]";
    }
  }

  private static class Analyze2 {
    public interface Fact {
      int check(String fact, int count);
    }

    private final Fact akitas = (f, c) -> "akitas".equals(f) && c == 0 ? 1 : 0;
    private final Fact cars = (f, c) -> "cars".equals(f) && c == 2 ? 1 : 0;
    private final Fact cats = (f, c) -> "cats".equals(f) && c > 7 ? 1 : 0;
    private final Fact children = (f, c) -> "children".equals(f) && c == 3 ? 1 : 0;
    private final Fact goldfish = (f, c) -> "goldfish".equals(f) && c < 5 ? 1 : 0;
    private final Fact perfumes = (f, c) -> "perfumes".equals(f) && c == 1 ? 1 : 0;
    private final Fact pomeranians = (f, c) -> "pomeranians".equals(f) && c < 3 ? 1 : 0;
    private final Fact samoyeds = (f, c) -> "samoyeds".equals(f) && c == 2 ? 1 : 0;
    private final Fact trees = (f, c) -> "trees".equals(f) && c > 3 ? 1 : 0;

    private final Fact vizslas = (f, c) -> "vizslas".equals(f) && c == 0 ? 1 : 0;

    public int analyze(final Same<String> property) {
      return this.children.check(property.a, Integer.valueOf(property.b))
          + this.cats.check(property.a, Integer.valueOf(property.b))
          + this.samoyeds.check(property.a, Integer.valueOf(property.b))
          + this.pomeranians.check(property.a, Integer.valueOf(property.b))
          + this.akitas.check(property.a, Integer.valueOf(property.b))
          + this.vizslas.check(property.a, Integer.valueOf(property.b))
          + this.goldfish.check(property.a, Integer.valueOf(property.b))
          + this.trees.check(property.a, Integer.valueOf(property.b))
          + this.cars.check(property.a, Integer.valueOf(property.b))
          + this.perfumes.check(property.a, Integer.valueOf(property.b));
    }
  }

  private static class Parser {
    private static final Pattern ANALYZE = Pattern.compile("^(.*): (.*)$");
    private static final Pattern AUNT = Pattern.compile("^Sue (.*): (.*): (.*), (.*): (.*), (.*): (.*)$");
    private final Analyze analyze = new Analyze();
    private final List<Sue> aunts = new ArrayList<>();

    public Parser() {
    }

    public void parse() {
      final Scanner scAunts = new Scanner(IE15D16.INPUT);
      while (scAunts.hasNextLine()) {
        final String lineAunt = scAunts.nextLine();
        final Matcher matcherAunt = Parser.AUNT.matcher(lineAunt);

        if (matcherAunt.find()) {
          final int number = Integer.valueOf(matcherAunt.group(1));

          final Sue sueAunt = new Sue(number);
          sueAunt.add(matcherAunt.group(2), matcherAunt.group(3));
          sueAunt.add(matcherAunt.group(4), matcherAunt.group(5));
          sueAunt.add(matcherAunt.group(6), matcherAunt.group(7));

          this.aunts.add(sueAunt);
        }
      }
      scAunts.close();

      final Scanner scAnalyze = new Scanner(IE15D16.ANALYZE);
      while (scAnalyze.hasNextLine()) {
        final String lineAnalyze = scAnalyze.nextLine();
        final Matcher matcherAnalyze = Parser.ANALYZE.matcher(lineAnalyze);

        if (matcherAnalyze.find()) {
          this.analyze.add(matcherAnalyze.group(1), matcherAnalyze.group(2));
        }
      }
      scAnalyze.close();
    }

    @Override
    public String toString() {
      final int maxLen = 10;
      return "Parser [aunts=" + (this.aunts != null ? this.aunts.subList(0, Math.min(this.aunts.size(), maxLen)) : null)
          + ",\n\nanalyze=" + this.analyze + "]";
    }
  }

  private static class Solver {
    private static final Analyze2 A = new Analyze2();
    private static final Parser PARSER = new Parser();
    static {
      Solver.PARSER.parse();
    }

    public Solver() {
    }

    public int part1() {
      for (final Sue sue : Solver.PARSER.aunts) {
        if (Solver.PARSER.analyze.values.containsAll(sue.properties)) {
          return sue.number;
        }
      }

      return IDay.NOT_IMPLEMENT;
    }

    public int part2() {
      for (final Sue sue : Solver.PARSER.aunts) {
        int count = 0;

        for (final Same<String> property : sue.properties) {
          count += Solver.A.analyze(property);
        }

        if (count == 3) {
          return sue.number;
        }
      }

      return IDay.NOT_IMPLEMENT;
    }
  }

  private static class Sue {
    private final int number;
    private final List<Same<String>> properties = new ArrayList<>();

    public Sue(final int number) {
      this.number = number;
    }

    public void add(final String property, final String result) {
      this.properties.add(new Same<>(property, result));
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Sue)) {
        return false;
      }
      final Sue other = (Sue) obj;
      if (this.number != other.number) {
        return false;
      }
      if (this.properties == null) {
        if (other.properties != null) {
          return false;
        }
      } else if (!this.properties.equals(other.properties)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.number;
      result = prime * result + (this.properties == null ? 0 : this.properties.hashCode());
      return result;
    }

    @Override
    public String toString() {
      return "Sue [number=" + this.number + ", properties=" + this.properties + "]";
    }
  }

  private static final Solver SOLVER = new Solver();

//  /*
//   * (non-Javadoc)
//   *
//   * @see cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 16";
//  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(40, E15D16.SOLVER.part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(241, E15D16.SOLVER.part2());
  }
}

/**
 * 
 */
package cz.hlubyluk.adventofcode.event2015.input;

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
  private static class Sue {
    private final int number;
    private final List<Same<String>> properties = new ArrayList<>();

    public Sue(int number) {
      this.number = number;
    }

    public void add(String property, String result) {
      this.properties.add(new Same<String>(property, result));
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + number;
      result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
      if (!(obj instanceof Sue)) {
        return false;
      }
      Sue other = (Sue) obj;
      if (number != other.number) {
        return false;
      }
      if (properties == null) {
        if (other.properties != null) {
          return false;
        }
      } else if (!properties.equals(other.properties)) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "Sue [number=" + number + ", properties=" + properties + "]";
    }
  }

  private static class Analyze {
    private final List<Same<String>> values = new ArrayList<>();

    public Analyze() {
    }

    public void add(String property, String result) {
      this.values.add(new Same<>(property, result));
    }

    @Override
    public String toString() {
      return "Analyze [values=" + values + "]";
    }
  }

  private static class Parser {
    private static final Pattern AUNT = Pattern.compile("^Sue (.*): (.*): (.*), (.*): (.*), (.*): (.*)$");
    private static final Pattern ANALYZE = Pattern.compile("^(.*): (.*)$");
    private final List<Sue> aunts = new ArrayList<>();
    private final Analyze analyze = new Analyze();

    public Parser() {
    }

    public void parse() {
      Scanner scAunts = new Scanner(IE15D16.INPUT);
      while (scAunts.hasNextLine()) {
        String lineAunt = scAunts.nextLine();
        Matcher matcherAunt = Parser.AUNT.matcher(lineAunt);

        if (matcherAunt.find()) {
          int number = Integer.valueOf(matcherAunt.group(1));

          Sue sueAunt = new Sue(number);
          sueAunt.add(matcherAunt.group(2), matcherAunt.group(3));
          sueAunt.add(matcherAunt.group(4), matcherAunt.group(5));
          sueAunt.add(matcherAunt.group(6), matcherAunt.group(7));

          this.aunts.add(sueAunt);
        }
      }
      scAunts.close();

      Scanner scAnalyze = new Scanner(IE15D16.ANALYZE);
      while (scAnalyze.hasNextLine()) {
        String lineAnalyze = scAnalyze.nextLine();
        Matcher matcherAnalyze = Parser.ANALYZE.matcher(lineAnalyze);

        if (matcherAnalyze.find()) {
          this.analyze.add(matcherAnalyze.group(1), matcherAnalyze.group(2));
        }
      }
      scAnalyze.close();
    }

    @Override
    public String toString() {
      final int maxLen = 10;
      return "Parser [aunts=" + (aunts != null ? aunts.subList(0, Math.min(aunts.size(), maxLen)) : null)
          + ",\n\nanalyze=" + analyze + "]";
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
      Sue result = null;

      for (Sue sue : Solver.PARSER.aunts) {
        if (Solver.PARSER.analyze.values.containsAll(sue.properties)) {
          result = sue;
        }
      }

      return result == null ? IDay.NOT_IMPLEMENT : result.number;
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
    return "2015 day 16";
  }

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
    // TODO Auto-generated method stub
    return null;
  }

}

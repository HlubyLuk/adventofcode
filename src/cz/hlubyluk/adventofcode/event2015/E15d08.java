package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.IDay;
import cz.hlubyluk.adventofcode.event2015.input.IE15D08;

/**
 * https://adventofcode.com/2015/day/8
 *
 * @author HlubyLuk
 */
public class E15D08 implements IE15D08 {

  private static class Line {
    private static final Pattern PART1 = Pattern.compile(
        "(" + Pattern.quote("\\") + "x[0-9a-f]{2})|(" + Pattern.quote("\\\"") + ")|(" + Pattern.quote("\\\\") + ")");
    private final int line, part1;
    private final int part2;

    public Line(String line) {
      this.line = line.length();
      this.part1 = Line.PART1.matcher(line).replaceAll("R").replaceAll("\"", "").length();
      this.part2 = line.replaceAll("^\"|\"$|\\\\|\\\"", "\\\\\"").length() + 2;
    }

    public int diff1() {
      return this.line - this.part1;
    }

    public int diff2() {
      return this.part2 - this.line;
    }
  }

  private static class Parser {
    public Parser() {
    }

    public List<Line> parseInput() {
      List<Line> lines = new ArrayList<>();

      Scanner sc = new Scanner(IE15D08.INPUT);
      while (sc.hasNextLine()) {
        lines.add(new Line(sc.nextLine()));
      }
      sc.close();

      return lines;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 8";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return String.valueOf(new Parser().parseInput().stream().map(mapper -> mapper.diff1()).reduce(Integer::sum)
        .orElseGet(() -> IDay.NOT_IMPLEMENT));
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return String.valueOf(new Parser().parseInput().stream().map(mapper -> mapper.diff2()).reduce(Integer::sum)
        .orElseGet(() -> IDay.NOT_IMPLEMENT));
  }
}

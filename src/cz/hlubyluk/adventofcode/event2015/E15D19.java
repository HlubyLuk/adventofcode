/**
 *
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.Utils.Same;
import cz.hlubyluk.adventofcode.event2015.input.IE15D19;

/**
 * https://adventofcode.com/2015/day/19
 *
 * @author HlubyLuk
 *
 */
public class E15D19 implements IE15D19 {
  private static class Parser {
    private static final Pattern PATTERN = Pattern.compile("^(.*) => (.*)$");
    private String molecules;
    private final List<Same<String>> replacesments = new ArrayList<>();

    private Parser() {
    }

    private void parse() {
      final Scanner sc = new Scanner(IE15D19.INPUT);
      while (sc.hasNextLine()) {
        final String line = sc.nextLine();

        final Matcher matcher = Parser.PATTERN.matcher(line);

        if (matcher.find()) {
          this.replacesments.add(new Same<>(matcher.group(1), matcher.group(2)));
        } else {
          this.molecules = line;
        }
      }
      sc.close();
    }
  }

  private static class Solver {
    private Solver() {
    }

    private int part1() {
      final Set<String> buffer = new HashSet<>();

      for (final Same<String> same : E15D19.PARSER.replacesments) {
        final Pattern pattern = Pattern.compile(same.a);
        int start = 0;

        final Matcher matcher = pattern.matcher(E15D19.PARSER.molecules);
        while (matcher.find(start)) {
          start = matcher.start() + 1;

          final StringBuilder b = new StringBuilder(E15D19.PARSER.molecules).replace(matcher.start(), matcher.end(),
              same.b);
          buffer.add(b.toString());
        }
      }

      return buffer.size();
    }
  }

  private static final Parser PARSER = new Parser();
  private static final Solver SOLVER = new Solver();

  static {
    E15D19.PARSER.parse();
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 19";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(509, E15D19.SOLVER.part1());
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

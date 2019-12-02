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

    private int part2() {
      int count = 0;

      String molecules = E15D19.PARSER.molecules;

      while (!"e".equals(molecules)) {
        for (final Same<String> item : E15D19.PARSER.replacesments) {
          final Matcher matcher = Pattern.compile(item.b).matcher(molecules);

          if (matcher.find()) {
            molecules = matcher.replaceFirst(item.a);

            count += 1;
          }
        }
      }

      return count;
    }
  }

  private static final Parser PARSER = new Parser();
  private static final Solver SOLVER = new Solver();

  static {
    E15D19.PARSER.parse();
  }

//  /*
//   * (non-Javadoc)
//   *
//   * @see cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 19";
//  }

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
    return this.result(195, E15D19.SOLVER.part2());
  }
}

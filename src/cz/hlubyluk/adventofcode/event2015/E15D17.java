/**
 *
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * https://adventofcode.com/2015/day/17
 *
 * @author HlubyLuk
 */
public class E15D17 implements IE15D17 {
  private static class Parser {
    private final List<Integer> containers = new ArrayList<>();

    private Parser() {
    }

    private void parse() {
      final Scanner sc = new Scanner(IE15D17.INPUT);
      while (sc.hasNext()) {
        this.containers.add(sc.nextInt());
      }
      sc.close();
    }
  }

  private static class Solver {
    private static final Parser PARSER = new Parser();
    static {
      Solver.PARSER.parse();
    }

    private Solver() {
    }

    private int part1() {
      Integer counter = 0;

      final int pow = (int) Math.pow(2, Solver.PARSER.containers.size());

      for (int i = 1; i < pow; i += 1) {
        int count = 0;

        final String code = Integer.toBinaryString(pow | i);
        for (int j = 1; j < code.length(); j += 1) {
          if (code.charAt(j) == '1') {
            count += Solver.PARSER.containers.get(j - 1);
          }
        }

        if (count == IE15D17.LITERS) {
          counter += 1;
        }
      }

      return counter;
    }

    private int part2() {
      final TreeMap<Integer, Integer> cache = new TreeMap<>();

      final int pow = (int) Math.pow(2, Solver.PARSER.containers.size());

      for (int i = 1; i < pow; i += 1) {
        int liters = 0, count = 0;

        final String code = Integer.toBinaryString(pow + i);
        for (int j = 1; j < code.length(); j += 1) {
          if (code.charAt(j) == '1') {
            liters += Solver.PARSER.containers.get(j - 1);
            count += 1;
          }
        }

        if (IE15D17.LITERS == liters) {
          cache.put(count, cache.getOrDefault(count, 0) + 1);
        }
      }

      return cache.firstEntry().getValue();
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
//    return "2015 day 17";
//  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(1304, E15D17.SOLVER.part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(18, E15D17.SOLVER.part2());
  }
}

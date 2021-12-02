/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2015;

import java.util.Scanner;

/**
 * https://adventofcode.com/2015/day/18
 *
 * @author HlubyLuk
 *
 */
public class E15D18 implements IE15D18 {
  private static class Parser {
    private static final char[][] MATRIX = new char[E15D18.EDGE][E15D18.EDGE];

    private Parser() {
    }

    private void parse() {
      final Scanner sc = new Scanner(IE15D18.INPUT);
      for (int i = 1; sc.hasNextLine(); i += 1) {
        int j = 1;

        for (final char cell : sc.nextLine().toCharArray()) {
          Parser.MATRIX[i][j++] = cell;
        }
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

    private int count(final char[][] current) {
      int count = 0;

      for (final char[] row : current) {
        for (final char cell : row) {
          if (cell == '#') {
            count += 1;
          }
        }
      }

      return count;
    }

    private int neighbors(final char[][] current, final int y, final int x) {
      int neighbors = 0;

      for (int a = -1; a < 2; a += 1) {
        for (int b = -1; b < 2; b += 1) {
          if (a == 0 && b == 0) {
            continue;
          } else if (current[y + a][x + b] == '#') {
            neighbors += 1;
          }
        }
      }

      return neighbors;
    }

    private char[][] nextStep(char[][] current, final char[][] next) {
      for (int y = 1; y < E15D18.EDGE - 1; y += 1) {
        for (int x = 1; x < E15D18.EDGE - 1; x += 1) {
          final int neighbors = this.neighbors(current, y, x);

          if (current[y][x] == '#') {
            if (neighbors == 2 || neighbors == 3) {
              next[y][x] = '#';
            } else {
              next[y][x] = '.';
            }
          } else if (neighbors == 3) {
            next[y][x] = '#';
          } else {
            next[y][x] = '.';
          }
        }
      }

      current = next;
      return current;
    }

    private int part1(final int steps) {
      char[][] current = E15D18.Parser.MATRIX;

      for (int s = 0; s < steps; s += 1) {
        current = this.nextStep(current, new char[E15D18.EDGE][E15D18.EDGE]);
      }

      return this.count(current);
    }

    private int part2(final int steps) {
      char[][] current = E15D18.Parser.MATRIX;
      this.stuckOn(current);

      for (int s = 0; s < steps; s += 1) {
        current = this.nextStep(current, new char[E15D18.EDGE][E15D18.EDGE]);

        this.stuckOn(current);
      }

      return this.count(current);
    }

    private void stuckOn(final char[][] current) {
      current[1][1] = '#';
      current[1][E15D18.EDGE - 2] = '#';
      current[E15D18.EDGE - 2][1] = '#';
      current[E15D18.EDGE - 2][E15D18.EDGE - 2] = '#';
    }
  }

  private static final int EDGE = IE15D18.SIZE + 2;

  private static final Solver SOLVER = new Solver();

//  /*
//   * (non-Javadoc)
//   *
//   * @see main.cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 18";
//  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(821, E15D18.SOLVER.part1(IE15D18.SIZE));
  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(886, E15D18.SOLVER.part2(IE15D18.SIZE));
  }

}

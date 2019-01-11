/**
 * 
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.Scanner;

import cz.hlubyluk.adventofcode.event2015.input.IE15D18;

/**
 * https://adventofcode.com/2015/day/18
 * 
 * @author HlubyLuk
 *
 */
public class E15D18 implements IE15D18 {
  private static final Solver SOLVER = new Solver();
  private static final int EDGE = IE15D18.SIZE + 2;

  private static class Solver {
    private static final Parser PARSER = new Parser();
    static {
      Solver.PARSER.parse();
    }

    private Solver() {
    }

    private int part1(int steps) {
      char[][] current = E15D18.Parser.MATRIX;

      for (int s = 0; s < steps; s += 1) {
        char[][] next = new char[E15D18.EDGE][E15D18.EDGE];

        for (int y = 1; y < E15D18.EDGE - 1; y += 1) {
          for (int x = 1; x < E15D18.EDGE - 1; x += 1) {
            int count = 0;

            for (int a = -1; a < 2; a += 1) {
              for (int b = -1; b < 2; b += 1) {
                if (a == 0 && b == 0) {
                  continue;
                } else if (current[y + a][x + b] == '#') {
                  count += 1;
                }
              }
            }

            if (current[y][x] == '#') {
              if (count == 2 || count == 3) {
                next[y][x] = '#';
              } else {
                next[y][x] = '.';
              }
            } else if (count == 3) {
              next[y][x] = '#';
            } else {
              next[y][x] = '.';
            }
          }
        }

        current = next;
      }

      int count = 0;
      for (char[] row : current) {
        for (char cell : row) {
          if (cell == '#') {
            count += 1;
          }
        }
      }

      return count;
    }
  }

  private static class Parser {
    private static final char[][] MATRIX = new char[E15D18.EDGE][E15D18.EDGE];

    private Parser() {
    }

    private void parse() {
      Scanner sc = new Scanner(IE15D18.INPUT);
      for (int i = 1; sc.hasNextLine(); i += 1) {
        int j = 1;

        for (char cell : sc.nextLine().toCharArray()) {
          Parser.MATRIX[i][j++] = cell;
        }
      }
      sc.close();
    }
  }

  private static void print(char[][] matrix) {
    for (char[] row : matrix) {
      for (char cell : row) {
        System.out.printf("%c ", cell);
      }

      System.out.println();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 18";
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(821, E15D18.SOLVER.part1(IE15D18.SIZE));
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

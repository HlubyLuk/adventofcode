/**
 * 
 */
package cz.hlubyluk.adventofcode.event2015;

import cz.hlubyluk.adventofcode.event2015.input.IE15D10;

/**
 * https://adventofcode.com/2015/day/10
 *
 * @author HlubyLuk
 */
public class E15D10 implements IE15D10 {

  private static class Solver {
    public Solver() {
    }

    public String lookandsay(String number) {
      StringBuilder result = new StringBuilder();

      char repeat = number.charAt(0);
      number = number.substring(1) + " "; // Space terminated.
      int times = 1;

      for (char actual : number.toCharArray()) {
        if (actual != repeat) {
          result.append(times).append(repeat);
          times = 1;
          repeat = actual;
        } else {
          times += 1;
        }
      }

      return result.toString();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 10";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    Solver solver = new Solver();

    String las = IE15D10.INPUT;
    for (int i = 0; i < 40; i += 1) {
      las = solver.lookandsay(las);
    }

    return String.valueOf(las.length());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    Solver solver = new Solver();

    String las = IE15D10.INPUT;
    for (int i = 0; i < 50; i += 1) {
      las = solver.lookandsay(las);
    }

    return String.valueOf(las.length());
  }
}

/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import cz.hlubyluk.adventofcode.IDay;

/**
 * https://adventofcode.com/2019/day/2
 * 
 * @author hlubyluk
 *
 */
public class E19D02 implements IE19D02 {

  /**
   * Default constructor.
   */
  public E19D02() {
  }

  @Override
  public String solveFirst() {
    return this.result(3706713, this.solve(12, 2));
  }

  @Override
  public String solveSecond() {
    int max = 100;
    int result = 19690720;

    for (int i = 0; i < max; i += 1) {
      for (int j = 0; j < max; j += 1) {
        int solve = this.solve(i, j);
        if (solve == result) {
          return this.result(8609, 100 * i + j);
        }
      }
    }

    return null;
  }

  private int solve(int a, int b) {
    String input = IE19D02.INPUT;
    String[] splited = input.split(",");

    int[] codes = new int[splited.length];
    for (int i = 0; i < splited.length; i += 1) {
      codes[i] = Integer.valueOf(splited[i]);
    }
    codes[1] = a;
    codes[2] = b;

    new IntcodeUtils.IntComputer().solve(codes, IDay.NOT_IMPLEMENT);

    return codes[0];
  }
}

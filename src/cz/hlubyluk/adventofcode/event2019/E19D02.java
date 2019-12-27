/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.Arrays;

import cz.hlubyluk.adventofcode.IDay;

/**
 * https://adventofcode.com/2019/day/2
 * 
 * @author hlubyluk
 *
 */
public class E19D02 implements IE19D02 {
  private static final IntComputer COMPUTER = new IntComputer();
  private static int[] DATA = null;

  static {
    String[] splited = IE19D02.INPUT.split(",");
    E19D02.DATA = new int[splited.length];
    for (int i = 0; i < splited.length; i += 1) {
      E19D02.DATA[i] = Integer.valueOf(splited[i]);
    }
  }

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
    E19D02.DATA[1] = a;
    E19D02.DATA[2] = b;

    final int[] copy = Arrays.copyOf(E19D02.DATA, E19D02.DATA.length);
    E19D02.COMPUTER.solve(copy, IDay.NOT_IMPLEMENT);

    return copy[0];
  }
}

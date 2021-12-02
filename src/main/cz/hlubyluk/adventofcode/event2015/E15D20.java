/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2015;

/**
 * https://adventofcode.com/2015/day/20
 *
 * @author HlubyLuk
 *
 */
public class E15D20 implements IE15D20 {

//  /*
//   * (non-Javadoc)
//   *
//   * @see main.cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 20";
//  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    final int max = IE15D20.INPUT / 10;
    final int[] houses = new int[max];
    int last = Integer.MAX_VALUE;

    for (int i = 1; i < max; i += 1) {
      for (int j = i; j < max; j += i) {
        if ((houses[j] += i) >= max && j < last) {
          last = j;
        }
      }
    }

    return this.result(786240, last);
  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    final int max = IE15D20.INPUT / 10;
    final int[] houses = new int[max];
    int last = Integer.MAX_VALUE;

    for (int i = 1; i < max; i += 1) {
      for (int j = i, k = 0; j < max && k < 50; j += i, k += 1) {
        if ((houses[j] += i * 11) >= IE15D20.INPUT && j < last) {
          last = j;
        }
      }
    }

    return this.result(831600, last);
  }
}

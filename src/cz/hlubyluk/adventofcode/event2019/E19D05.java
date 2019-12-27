/**
 *
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.Arrays;

/**
 * @author hlubyluk
 *
 */
public class E19D05 implements IE19D05 {
  private static final IntComputer COMPUTER = new IntComputer();
  private static int[] DATA = null;

  static {
    var splits = IE19D05.INPUT.split(",");
    E19D05.DATA = new int[splits.length];

    for (int i = 0; i < splits.length; i += 1) {
      E19D05.DATA[i] = Integer.valueOf(splits[i]);
    }
  }

  @Override
  public String solveFirst() {
    E19D05.COMPUTER.solve(Arrays.copyOf(E19D05.DATA, E19D05.DATA.length), 1);

    return this.result(13547311, E19D05.COMPUTER.outputs.stream().filter(i -> i != 0).findFirst().get());
  }

  @Override
  public String solveSecond() {
    E19D05.COMPUTER.solve(Arrays.copyOf(E19D05.DATA, E19D05.DATA.length), 5);

    return this.result(236453, E19D05.COMPUTER.outputs.get(E19D05.COMPUTER.outputs.size() - 1));
  }
}

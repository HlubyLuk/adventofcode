/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

/**
 * @author hlubyluk
 *
 */
public class E19D05 implements IE19D05 {

  @Override
  public String solveFirst() {
//    int i = 0;
//
//    while (true && i < E19D05.OP_CODES.length) {
//      String tmp = String.format("%05d", E19D05.OP_CODES[i]);
//
//      int modeA = Character.digit(tmp.charAt(0), 10);
//      int modeB = Character.digit(tmp.charAt(1), 10);
//      int modeC = Character.digit(tmp.charAt(2), 10);
//      int opCode = Integer.valueOf(tmp.substring(3, tmp.length()));
//    }

    var splits = IE19D05.INPUT.split(",");
    var data = new int[splits.length];

    for (int i = 0; i < splits.length; i += 1) {
      data[i] = Integer.valueOf(splits[i]);
    }
    final IntcodeUtils.IntComputer computer = new IntcodeUtils.IntComputer();
    computer.solve(data, 1);

    return this.result(13547311, computer.outputs.stream().filter(i -> i != 0).findFirst().get());
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }
}

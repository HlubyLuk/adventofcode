/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

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
    var computer = Computer2019.with(IE19D02.INPUT);
    computer.setValueIntoAdress(1, 12);
    computer.setValueIntoAdress(2, 2);
    computer.solve();

    return this.result(3706713, computer.getValueAtAgress(0));
  }

  @Override
  public String solveSecond() {
    int max = 100;
    int result = 19690720;

    for (int i = 0; i < max; i += 1) {
      for (int j = 0; j < max; j += 1) {
        var computer = Computer2019.with(IE19D02.INPUT);
        computer.setValueIntoAdress(1, i);
        computer.setValueIntoAdress(2, j);
        computer.solve();

        if (computer.getValueAtAgress(0) == result) {
          return this.result(8609, 100 * i + j);
        }
      }
    }

    return null;
  }
}

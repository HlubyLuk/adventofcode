/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2019;

/**
 * @author hlubyluk
 *
 */
public class E19D09 implements IE19D09 {

  /**
   *
   */
  public E19D09() {
  }

  /**
   * 209, 34463338, 3429606717
   */
  @Override
  public String solveFirst() {
    final var computer = Computer2019.with(IE19D09.INPUT, 1);
    computer.solve();

    return this.result("3429606717", String.valueOf(computer.getDiagnosticCode()));
  }

  @Override
  public String solveSecond() {
    final var computer = Computer2019.with(IE19D09.INPUT, 2);
    computer.solve();

    return this.result("33679", String.valueOf(computer.getDiagnosticCode()));
  }
}

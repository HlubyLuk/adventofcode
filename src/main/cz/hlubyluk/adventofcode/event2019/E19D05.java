/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2019;

/**
 * @author hlubyluk
 *
 */
public class E19D05 implements IE19D05 {

  @Override
  public String solveFirst() {
    var computer = Computer2019.with(IE19D05.INPUT, 1);
    computer.solve();

    return this.result(13547311, computer.getDiagnosticCode());
  }

  @Override
  public String solveSecond() {
    var computer = Computer2019.with(IE19D05.INPUT, 5);
    computer.solve();

    return this.result(236453, computer.getDiagnosticCode());
  }
}

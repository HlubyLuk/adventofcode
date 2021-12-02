/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2019;

import java.util.Arrays;
import java.util.List;

import main.cz.hlubyluk.adventofcode.Utils;

/**
 * @author hlubyluk
 *
 */
public class E19D07 implements IE19D07 {

  @Override
  public String solveFirst() {
    var phases = Arrays.asList(0, 1, 2, 3, 4);
    var lambda = new Highest();

    Utils.Generator.heapPermutation(phases, lambda);

    return this.result(95757, lambda.getAccumulator());
  }

  @Override
  public String solveSecond() {
    return null;
  }

  private static abstract class Perm implements Utils.Generator.Permutation<Integer> {
    protected long accumulator = -1;

    /**
     * @return the accumulator
     */
    public long getAccumulator() {
      return this.accumulator;
    }
  }

  private static final class Highest extends Perm {

    /**
     * Constructor.
     */
    private Highest() {
      super();
    }

    @Override
    public void compute(List<Integer> list) {
      var lastOutput = 0L;

      for (var phase : list) {
        var computer = Computer2019.with(INPUT, phase);
        computer.setNextInput(lastOutput);
        computer.solve();

        final var diagnosticCode = computer.getDiagnosticCode();
        lastOutput = diagnosticCode;

        this.accumulator = Math.max(this.accumulator, diagnosticCode);
      }
    }
  }
}

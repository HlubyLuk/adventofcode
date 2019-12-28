/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.Arrays;
import java.util.List;

import cz.hlubyluk.adventofcode.Utils;

/**
 * @author hlubyluk
 *
 */
public class E19D07 implements IE19D07 {
  private static int[] DATA = null;

  static {
    var splits = IE19D07.INPUT.split(",");
    E19D07.DATA = new int[splits.length];

    for (int i = 0; i < splits.length; i += 1) {
      E19D07.DATA[i] = Integer.valueOf(splits[i]);
    }
  }

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

  private static final class Highest implements Utils.Generator.Permutation<Integer> {
    private int accumulator = -1;

    /**
     * Constructor.
     */
    private Highest() {
      super();
    }

    /**
     * @return the accumulator
     */
    public int getAccumulator() {
      return this.accumulator;
    }

    @Override
    public void compute(List<Integer> list) {
      int lastOutut = 0;
      final IntComputer computer = new IntComputer();
      final int[] copy = Arrays.copyOf(E19D07.DATA, E19D07.DATA.length);

      for (int i = 0; i < list.size(); i += 1) {
        computer.solve(copy, list.get(i), lastOutut);
        lastOutut = computer.outputs.get(computer.outputs.size() - 1);
      }

      this.accumulator = Math.max(this.accumulator,
          computer.outputs.stream().max(Integer::compare).orElseGet(() -> -1));
    }

    @Override
    public String toString() {
      return "Highest [accumulator=" + this.accumulator + "]";
    }
  }
}

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

//  @Override
//  public String getTag() {
//    return this.getClass().getSimpleName();
//  }

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

  private void caseOne(int[] opCodes, int i) {
    opCodes[opCodes[i + 3]] = opCodes[opCodes[i + 1]] + opCodes[opCodes[i + 2]];
  }

  private void caseTwo(int[] opCodes, int i) {
    opCodes[opCodes[i + 3]] = opCodes[opCodes[i + 1]] * opCodes[opCodes[i + 2]];
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

    for (int i = 0;; i += 4) {
      int code = codes[i];

      switch (code) {
      case 1:
        this.caseOne(codes, i);
        break;
      case 2:
        this.caseTwo(codes, i);
        break;
      case 99:
        return codes[0];
      }
    }
  }
}

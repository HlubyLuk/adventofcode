/**
 * 
 */
package event2019;

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
  public String getTag() {
    return this.getClass().getSimpleName();
  }

  @Override
  public String solveFirst() {
    String input = IE19D02.INPUT;
    String[] splited = input.split(",");
    splited[1] = "12";
    splited[2] = "2";

    int[] codes = new int[splited.length];
    for (int i = 0; i < splited.length; i += 1) {
      codes[i] = Integer.valueOf(splited[i]);
    }

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
        return String.valueOf(codes[0]);
      }
    }
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  private void caseOne(int[] opCodes, int i) {
    opCodes[opCodes[i + 3]] = opCodes[opCodes[i + 1]] + opCodes[opCodes[i + 2]];
  }

  private void caseTwo(int[] opCodes, int i) {
    opCodes[opCodes[i + 3]] = opCodes[opCodes[i + 1]] * opCodes[opCodes[i + 2]];
  }
}

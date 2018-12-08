package cz.hlubyluk.adventofcode2018.day;

/**
 * https://adventofcode.com/2018/day/5
 *
 * @author HlubyLuk
 */
public class Day5 implements IDay5 {
  private static final char ZERO = 'A';
  private static final char ALPHABET_LENGHT = 'a' - Day5.ZERO;

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    StringBuilder tmp = new StringBuilder(Day5.INPUT);
    boolean run = true;
    int j = 0;

    while (run) {
      run = false;

      for (int i = j < 0 ? 0 : j; i < tmp.length() - 1;) {
        char current = tmp.charAt(i);
        char next = tmp.charAt(i + 1);

        if (current + Day5.ALPHABET_LENGHT == next || current - Day5.ALPHABET_LENGHT == next) {
          tmp.delete(i, i + 2);
//          tmp.deleteCharAt(i + 1).deleteCharAt(i);

          j = i - 1;

          run = true;
          break;
        } else {
          i += 1;
        }
      }
    }

    return String.valueOf(tmp.length());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

}

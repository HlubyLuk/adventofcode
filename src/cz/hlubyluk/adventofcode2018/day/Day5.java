package cz.hlubyluk.adventofcode2018.day;

/**
 * https://adventofcode.com/2018/day/5
 *
 * @author HlubyLuk
 */
public class Day5 implements IDay5 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return String.valueOf(this.shared(IDay5.INPUT).length());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    String ret = null;

    for (char a = 'A'; a <= 'Z'; a += 1) {
      StringBuilder tmp = this.shared(IDay5.INPUT.replaceAll(String.format("[%c%c]", a, a + Day5.ALPHABET_LENGHT), ""));

      if (ret == null || ret.length() > tmp.length()) {
        ret = tmp.toString();
      }
    }

    return String.valueOf(ret.length());
  }

  private StringBuilder shared(String input) {
    StringBuilder builder = new StringBuilder(input);

    boolean run = true;
    int j = 0;

    while (run) {
      run = false;

      for (int i = j < 0 ? 0 : j; i < builder.length() - 1;) {
        char current = builder.charAt(i);
        char next = builder.charAt(i + 1);

        if (current + Day5.ALPHABET_LENGHT == next || current - Day5.ALPHABET_LENGHT == next) {
          builder.delete(i, i + 2);
//          builder.deleteCharAt(i + 1).deleteCharAt(i);

          j = i - 1;

          run = true;
          break;
        } else {
          i += 1;
        }
      }
    }

    return builder;
  }
}

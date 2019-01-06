/**
 *
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.Utils;
import cz.hlubyluk.adventofcode.event2015.input.IE15D11;

/**
 * https://adventofcode.com/2015/day/11
 *
 * @author HlubyLuk
 *
 */
public class E15D11 implements IE15D11 {

  private static class Generator {
    public Generator() {
    }

    public String next(final String input) {
      final StringBuilder builder = new StringBuilder();

      int shift = 1;

      final char[] arr = input.toCharArray();
      for (int i = arr.length - 1; i >= 0; i -= 1) {
        char append = (char) (arr[i] + shift);

        if (append == 'z' + 1 && i == 0) {
          builder.append('a');
        }

        if (append == 'z' + 1) {
          append = 'a';
          shift = 1;
        } else {
          shift = 0;
        }

        builder.append(append);
      }

      return builder.reverse().toString();
    }
  }

  private static class Password {
    private static final Pattern RESTRICTION = Utils.restriction("i|o|l");
    private final String password;

    public Password(final String password) {
      this.password = password;
    }

    private boolean doubleCharacter() {
      return Utils.DOUBLE_CHARACTER_WITHOUT_OVERLAP.matcher(this.password).find();
    }

    public boolean isCorrect() {
      return this.restriction() && this.doubleCharacter() && this.straight();
    }

    private boolean restriction() {
      return Password.RESTRICTION.matcher(this.password).find();
    }

    private boolean straight() {
      final char[] arr = this.password.toCharArray();
      for (int i = 2; i < arr.length; i += 1) {
        final int a = arr[i - 2];
        final int b = arr[i - 1];
        final int c = arr[i];

        if (a + 1 == b && b + 1 == c) {
          return true;
        }
      }

      return false;
    }

    @Override
    public String toString() {
      return "Password [password=" + this.password + "]";
    }
  }

  private static final Generator GENERATOR = new Generator();

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 11";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    String next = IE15D11.INPUT;

//    Generator generator = new Generator();
    do {
      next = E15D11.GENERATOR.next(next);
    } while (!new Password(next).isCorrect());

//    if (!"hepxxyzz".equals(next)) {
//      throw new RuntimeException("Wrong!!!");
//    }

//    return next;
    return this.result("hepxxyzz", next);
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    String next = this.solveFirst();

//    Generator generator = new Generator();
    do {
      next = E15D11.GENERATOR.next(next);
    } while (!new Password(next).isCorrect());

//    if (!"heqaabcc".equals(next)) {
//      throw new RuntimeException("Wrong!!!");
//    }

//    return next;
    return this.result("heqaabcc", next);
  }
}

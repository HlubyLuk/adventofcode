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

  private static class Password {
    private static final Pattern RESTRICTION = Utils.restriction("i|o|l");
    private final String password;

    public Password(String password) {
      this.password = password;
    }

    public boolean isCorrect() {
      return this.restriction() && this.doubleCharacter() && this.straight();
    }

    private boolean restriction() {
      return Password.RESTRICTION.matcher(this.password).find();
    }

    private boolean doubleCharacter() {
      return Utils.DOUBLE_CHARACTER_WITHOUT_OVERLAP.matcher(this.password).find();
    }

    private boolean straight() {
      char[] arr = this.password.toCharArray();
      for (int i = 2; i < arr.length; i += 1) {
        int a = arr[i - 2];
        int b = arr[i - 1];
        int c = arr[i];

        if (a + 1 == b && b + 1 == c) {
          return true;
        }
      }

      return false;
    }

    @Override
    public String toString() {
      return "Password [password=" + password + "]";
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "Event 2015 day 11";
  }

  private static class Generator {
    public Generator() {
    }

    public String next(String input) {
      StringBuilder builder = new StringBuilder();

      int shift = 1;

      char[] arr = input.toCharArray();
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

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    String next = IE15D11.INPUT;

    Generator generator = new Generator();
    do {
      next = generator.next(next);
    } while (!new Password(next).isCorrect());

    if (!"hepxxyzz".equals(next)) {
      throw new RuntimeException("Wrong!!!");
    }

    return next;
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    String next = this.solveFirst();

    Generator generator = new Generator();
    do {
      next = generator.next(next);
    } while (!new Password(next).isCorrect());

    if (!"heqaabcc".equals(next)) {
      throw new RuntimeException("Wrong!!!");
    }

    return next;
  }
}

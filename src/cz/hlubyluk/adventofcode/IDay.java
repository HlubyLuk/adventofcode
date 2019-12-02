package cz.hlubyluk.adventofcode;

/**
 * Day abstraction.
 *
 * @author HlubyLuk
 */
public interface IDay {
  char ALPHABET_LENGHT = 'a' - IDay.ZERO_CHARACTER;
  /** Default value. */
  int NOT_IMPLEMENT = Integer.MIN_VALUE;
  char ZERO_CHARACTER = 'A';

//  /**
//   * Get day tag.
//   *
//   * @return name for puzzle.
//   */
//  String getTag();

  /**
   * Solve first part.
   *
   * @return value.
   */
  String solveFirst();

  /**
   * Solve second part.
   *
   * @return value.
   */
  String solveSecond();

  default String result(final int expected, final int result) {
    if (expected != result) {
      throw new RuntimeException("Wrong!!! " + result);
    }

    return String.valueOf(result);
  }

  default String result(final int expected, final long result) {
    if (expected != result) {
      throw new RuntimeException("Wrong!!! " + result);
    }

    return String.valueOf(result);
  }

  default String result(final String expected, final String result) {
    if (!expected.equals(result)) {
      throw new RuntimeException("Wrong!!! " + result);
    }

    return result;
  }
}

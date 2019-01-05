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

  /**
   * Get day tag.
   *
   * @return name for puzzle.
   */
  String getTag();

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

  default String result(String expected, String result) {
    if (!expected.equals(result)) {
      throw new RuntimeException("Wrong!!!");
    }

    return result;
  }

  default String result(int expected, int result) {
    if (expected != result) {
      throw new RuntimeException("Wrong!!!");
    }

    return String.valueOf(result);
  }
}

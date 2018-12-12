package cz.hlubyluk.adventofcode2018.day;

/**
 * Day abstraction.
 *
 * @author HlubyLuk
 */
public interface IDay {
  /**
   * Default value.
   */
  int NOT_IMPLEMENT = Integer.MIN_VALUE;
  char ZERO_CHARACTER = 'A';
  char ALPHABET_LENGHT = 'a' - IDay.ZERO_CHARACTER;

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
}

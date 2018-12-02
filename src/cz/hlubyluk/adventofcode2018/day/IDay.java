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

  /**
   * Solve first part.
   *
   * @return value.
   */
  int solveFirst();

  /**
   * Solve second part.
   *
   * @return value.
   */
  int solveSecond();
}

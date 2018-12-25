package cz.hlubyluk.adventofcode.event2015;

import cz.hlubyluk.adventofcode.event2015.input.IE15D06;

/**
 * https://adventofcode.com/2015/day/6
 *
 * @author HlubyLuk
 */
public class E15D06 implements IE15D06 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 6";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return new Mapper().solvePartFirst();
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return new Mapper().solvePartSecond();
  }
}

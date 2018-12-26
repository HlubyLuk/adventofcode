package cz.hlubyluk.adventofcode.event2015;

import cz.hlubyluk.adventofcode.IDay;
import cz.hlubyluk.adventofcode.event2015.input.IE15D08;

/**
 * https://adventofcode.com/2015/day/8
 *
 * @author HlubyLuk
 */
public class E15D08 implements IE15D08 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 8";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return String.valueOf(new Parser().parseInput().stream().map(mapper -> mapper.diff1()).reduce(Integer::sum)
        .orElseGet(() -> IDay.NOT_IMPLEMENT));
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return String.valueOf(new Parser().parseInput().stream().map(mapper -> mapper.diff2()).reduce(Integer::sum)
        .orElseGet(() -> IDay.NOT_IMPLEMENT));
  }
}

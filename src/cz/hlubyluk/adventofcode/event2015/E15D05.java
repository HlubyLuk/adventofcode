package cz.hlubyluk.adventofcode.event2015;

import cz.hlubyluk.adventofcode.event2015.input.IE15D05;

/**
 * @author HlubyLuk
 */
public class E15D05 implements IE15D05 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 5";
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return String.valueOf(new Parser().parseInput().stream().filter(x -> x.analyze()).count());
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return null;
  }
}

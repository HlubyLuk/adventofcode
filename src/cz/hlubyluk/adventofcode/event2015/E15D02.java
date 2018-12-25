package cz.hlubyluk.adventofcode.event2015;

import cz.hlubyluk.adventofcode.event2015.input.IE15D02;

/**
 * @author HlubyLuk
 */
public class E15D02 implements IE15D02 {

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 2";
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return String.valueOf(
        new Parser().parseBoxes().stream().map(box -> box.totalPaper()).reduce(Integer::sum).orElseGet(() -> 0));
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return String.valueOf(
        new Parser().parseBoxes().stream().map(box -> box.totalRibbon()).reduce(Integer::sum).orElseGet(() -> 0));
  }
}

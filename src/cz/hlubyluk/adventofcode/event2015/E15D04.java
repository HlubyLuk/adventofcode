package cz.hlubyluk.adventofcode.event2015;

import cz.hlubyluk.adventofcode.Utils;
import cz.hlubyluk.adventofcode.event2015.input.IE15D04;

/**
 * @author HlubyLuk
 */
public class E15D04 implements IE15D04 {

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 4";
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    for (int i = 0;; i += 1) {
      String md5 = Utils.MD5(String.format("%s%d", IE15D04.INPUT, i));
      if (md5.startsWith("00000")) {
        return String.valueOf(i);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    for (int i = 0;; i += 1) {
      String md5 = Utils.MD5(String.format("%s%d", IE15D04.INPUT, i));
      if (md5.startsWith("000000")) {
        return String.valueOf(i);
      }
    }
  }
}

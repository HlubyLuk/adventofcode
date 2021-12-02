package main.cz.hlubyluk.adventofcode.event2015;

import main.cz.hlubyluk.adventofcode.Utils;

/**
 * @author HlubyLuk
 */
public class E15D04 implements IE15D04 {

//  /*
//   * (non-Javadoc)
//   *
//   * @see main.cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 4";
//  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    for (int i = 0;; i += 1) {
      final String md5 = Utils.MD5(String.format("%s%d", IE15D04.INPUT, i));
      if (md5.startsWith("00000")) {
        return this.result(254575, i); // String.valueOf(i);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    for (int i = 0;; i += 1) {
      final String md5 = Utils.MD5(String.format("%s%d", IE15D04.INPUT, i));
      if (md5.startsWith("000000")) {
        return this.result(1038736, i); // String.valueOf(i);
      }
    }
  }
}

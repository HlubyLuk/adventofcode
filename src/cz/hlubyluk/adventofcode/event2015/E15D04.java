package cz.hlubyluk.adventofcode.event2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
      String md5 = this.MD5(String.format("%s%d", IE15D04.INPUT, i));
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
      String md5 = this.MD5(String.format("%s%d", IE15D04.INPUT, i));
      if (md5.startsWith("000000")) {
        return String.valueOf(i);
      }
    }
  }

  public String MD5(String md5) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
    }
    return null;
  }
}

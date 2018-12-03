package cz.hlubyluk.adventofcode2018.day;

import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://adventofcode.com/2018/day/2
 *
 * @author HlubyLuk
 */
public final class Day2 implements IDay2 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public int solveFirst() {
    int a = 0, b = 0;
    List<String> tokens = Arrays.asList(Day2.INPUT.split("\n"));

    for (String token : tokens) {
      boolean addA = false, addB = false;
      String x = token;
      while (!x.isEmpty()) {
        int lenghtX = x.length();
        String y = x.replaceAll(String.valueOf(x.charAt(0)), "");
        int diff = lenghtX - y.length();
        if (diff > 1) {
          if (!addA && diff == 2) {
            a += 1;
            addA = true;
          }

          if (!addB && diff == 3) {
            b += 1;
            addB = true;
          }
        }
        x = y;
      }
    }

    return a * b;
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public int solveSecond() {
    // TODO Auto-generated method stub
    return 0;
  }

}

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
      Map<Character, Integer> cache = new HashMap<>();

      StringCharacterIterator it = new StringCharacterIterator(token);
      for (Character c = it.first(); c != StringCharacterIterator.DONE; c = it.next()) {
        int get = cache.getOrDefault(c, 0);
        cache.put(c, get + 1);
      }

      for (Iterator<Entry<Character, Integer>> itr = cache.entrySet().iterator(); itr.hasNext();) {
        Entry<Character, Integer> next = itr.next();
        if (next.getValue() != 2 && next.getValue() != 3) {
          itr.remove();
        }
      }
      boolean addA = false, addB = false;

      for (Entry<Character, Integer> item : cache.entrySet()) {
        if (item.getValue() == 2 && !addA) {
          addA = true;
          a += 1;
        }

        if (item.getValue() == 3 && !addB) {
          b += 1;
        }
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

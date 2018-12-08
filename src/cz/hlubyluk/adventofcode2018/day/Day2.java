package cz.hlubyluk.adventofcode2018.day;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
  public String solveFirst() {
    int a = 0, b = 0;
    List<String> tokens = Arrays.asList(IDay2.INPUT.split("\n"));

    for (String token : tokens) {
      Map<Character, Integer> cache = new HashMap<>();

      StringCharacterIterator it = new StringCharacterIterator(token);
      for (Character c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
        int get = cache.getOrDefault(c, 0);
        cache.put(c, get + 1);
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

    return String.valueOf(a * b);
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    String a = null;
    int position = Integer.MIN_VALUE;

    List<String> tokens = Arrays.asList(IDay2.INPUT.split("\n"));

    int size = tokens.size();
    for (int k = 0; k < size; k += 1) {
      String token = tokens.get(k);

      for (int j = k + 1; j < size; j += 1) {
        String item = tokens.get(j);

        if (token.length() == item.length()) {
          List<Integer> cache = new ArrayList<>();

          for (int i = 0; i < token.length(); i += 1) {
            char charA = token.charAt(i);
            char charB = item.charAt(i);

            if (charA != charB) {
              cache.add(i);
            }
          }

          if (cache.size() == 1) {
            a = token;
            position = cache.get(0);
          }
        }
      }
    }

    return String.format("%s%s", a.substring(0, position), a.substring(position + 1));
  }
}

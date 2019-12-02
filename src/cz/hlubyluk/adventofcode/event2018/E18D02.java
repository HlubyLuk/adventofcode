package cz.hlubyluk.adventofcode.event2018;

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
public final class E18D02 implements IE18D02 {

  @Override
  public String getTag() {
    return "2018 Day 2";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    int a = 0, b = 0;
    final List<String> tokens = Arrays.asList(IE18D02.INPUT.split("\n"));

    for (final String token : tokens) {
      final Map<Character, Integer> cache = new HashMap<>();

      final StringCharacterIterator it = new StringCharacterIterator(token);
      for (Character c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
        final int get = cache.getOrDefault(c, 0);
        cache.put(c, get + 1);
      }

      boolean addA = false;
      final boolean addB = false;

      for (final Entry<Character, Integer> item : cache.entrySet()) {
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

    final List<String> tokens = Arrays.asList(IE18D02.INPUT.split("\n"));

    final int size = tokens.size();
    for (int k = 0; k < size; k += 1) {
      final String token = tokens.get(k);

      for (int j = k + 1; j < size; j += 1) {
        final String item = tokens.get(j);

        if (token.length() == item.length()) {
          final List<Integer> cache = new ArrayList<>();

          for (int i = 0; i < token.length(); i += 1) {
            final char charA = token.charAt(i);
            final char charB = item.charAt(i);

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

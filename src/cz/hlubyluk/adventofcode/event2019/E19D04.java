/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.IDay;

/**
 * @author hlubyluk
 *
 */
public class E19D04 implements IDay {

  private static final Pattern PATTERN = Pattern.compile("(\\d)\\1+");

  @Override
  public String solveFirst() {
    List<Integer> numbers = new ArrayList<>();

    for (int a = 1; a <= 9; a += 1) {
      for (int b = a; b <= 9; b += 1) {
        for (int c = b; c <= 9; c += 1) {
          for (int d = c; d <= 9; d += 1) {
            for (int e = d; e <= 9; e += 1) {
              for (int f = e; f <= 9; f += 1) {
                String number = String.format("%d%d%d%d%d%d", a, b, c, d, e, f);
                int tmp = Integer.valueOf(number);
                if (240298 <= tmp && tmp <= 784956 && E19D04.PATTERN.matcher(number).find()) {
                  numbers.add(tmp);
                }
              }
            }
          }
        }
      }
    }

    return this.result(1150, numbers.size());
  }

  /**
   * 470, 692, 80
   */
  @Override
  public String solveSecond() {
    Set<Integer> numbers = new HashSet<>();

    for (int a = 1; a <= 9; a += 1) {
      for (int b = a; b <= 9; b += 1) {
        for (int c = b; c <= 9; c += 1) {
          for (int d = c; d <= 9; d += 1) {
            for (int e = d; e <= 9; e += 1) {
              for (int f = e; f <= 9; f += 1) {
                String number = String.format("%d%d%d%d%d%d", a, b, c, d, e, f);
                int tmp = Integer.valueOf(number);

                if (240298 <= tmp && tmp <= 784956) {
                  Matcher matcher = E19D04.PATTERN.matcher(number);

                  int start = 0;
                  while (matcher.find(start)) {
                    start = matcher.end();
                    if (matcher.toMatchResult().group().length() == 2) {
                      numbers.add(tmp);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return this.result(748, numbers.size());
  }
}

package cz.hlubyluk.adventofcode.event2018;

import java.util.ArrayList;
import java.util.List;

public class E18D14 implements IE18D14 {

  private char[] add(final char a, final char b) {
    final int c = a - '0' + b - '0';

    return String.valueOf(c).toCharArray();
  }

  @Override
  public String getTag() {
    return "2018 Day14";
  }

  private int index(final List<Character> scores, final int idx) {
    return (idx + Integer.valueOf(String.valueOf(scores.get(idx))) + 1) % scores.size();
  }

  @Override
  public String solveFirst() {
    int first = 0, second = 1;
    final List<Character> scores = new ArrayList<>();
    scores.add('3');
    scores.add('7');

    final Integer stop = Integer.valueOf(String.valueOf(IE18D14.INPUT));
    for (int i = 0; i < stop + 10; i += 1) {
      final Character a = scores.get(first);
      final Character b = scores.get(second);
      final char[] add = this.add(a, b);

      for (final char c : add) {
        scores.add(c);
      }

      first = this.index(scores, first);
      second = this.index(scores, second);
    }

    final StringBuilder builder = new StringBuilder();
    for (int i = stop; i < stop + 10; i += 1) {
      builder.append(scores.get(i));
    }

    return builder.toString();
  }

  @Override
  public String solveSecond() {
    return null;
  }
}

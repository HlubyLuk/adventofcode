package cz.hlubyluk.adventofcode.event2018;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2018/day/1
 *
 * @author HlubyLuk
 */
public final class E18D01 implements IE18D01 {

  @Override
  public String getTag() {
    return "2018 Day 1";
  }

  @Override
  public String solveFirst() {
    int tmp = 0;

    for (final String item : IE18D01.INPUT.split("\n")) {
      tmp += Integer.valueOf(item);
    }

    return String.valueOf(tmp);
  }

  @Override
  public String solveSecond() {
    int tmp = 0;
    final Set<Integer> cache = new HashSet<>();

    final String[] split = IE18D01.INPUT.split("\n");
    final List<Integer> freqs = Arrays.asList(split).stream().map(x -> Integer.valueOf(x)).collect(Collectors.toList());

    for (final Integer item : freqs) {
      tmp += item;
      cache.add(tmp);
    }

    final int size = freqs.size();
    for (int i = 0;; i += 1) {
      final int get = Integer.valueOf(freqs.get(i % size));
      tmp += get;

      if (cache.contains(tmp)) {
        break;
      }
    }

    return String.valueOf(tmp);
  }
}

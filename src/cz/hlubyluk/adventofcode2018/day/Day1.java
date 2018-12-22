package cz.hlubyluk.adventofcode2018.day;

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
public final class Day1 implements IDay1 {

  @Override
  public String solveFirst() {
    int tmp = 0;

    for (String item : IDay1.INPUT.split("\n")) {
      tmp += Integer.valueOf(item);
    }

    return String.valueOf(tmp);
  }

  @Override
  public String solveSecond() {
    int tmp = 0;
    Set<Integer> cache = new HashSet<>();

    String[] split = IDay1.INPUT.split("\n");
    List<Integer> freqs = Arrays.asList(split).stream().map(x -> Integer.valueOf(x)).collect(Collectors.toList());

    for (Integer item : freqs) {
      tmp += item;
      cache.add(tmp);
    }

    int size = freqs.size();
    for (int i = 0;; i += 1) {
      int get = Integer.valueOf(freqs.get(i % size));
      tmp += get;

      if (cache.contains(tmp)) {
        break;
      }
    }

    return String.valueOf(tmp);
  }

  @Override
  public String getTag() {
    return "2018 Day 1";
  }
}

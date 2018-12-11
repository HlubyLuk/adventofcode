package cz.hlubyluk.adventofcode2018.day;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://adventofcode.com/2018/day/7
 *
 * @author HlubyLuk
 */
public class Day7 implements IDay7 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    Set<Instruction> instructions = new TreeSet<>();
    for (String line : IDay7.INPUT.split("\n")) {
      instructions.add(new Instruction(line));
    }

    Map<Character, Set<Character>> cache = new TreeMap<>();
    for (Instruction instruction : instructions) {
      cache.put(instruction.from, new TreeSet<>());
      cache.put(instruction.to, new TreeSet<>());
    }
    for (Instruction instruction : instructions) {
      cache.get(instruction.to).add(instruction.from);
    }

    StringBuilder buffer = new StringBuilder();
    char first = ' ';
    while (!cache.isEmpty()) {
      Set<Entry<Character, Set<Character>>> entries = cache.entrySet();

      for (Iterator<Entry<Character, Set<Character>>> iterator = entries.iterator(); iterator.hasNext();) {
        Entry<Character, Set<Character>> entry = iterator.next();

        if (entry.getValue().isEmpty()) {
          first = entry.getKey();
          buffer.append(first);
          iterator.remove();
          break;
        }
      }

      for (Entry<Character, Set<Character>> entry : entries) {
        entry.getValue().remove(first);
      }
    }

    return buffer.toString();
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  private static class Instruction implements Comparable<Instruction> {
    private char from, to;

    public Instruction(String line) {
      super();

      String[] split = line.split(" ");

      this.from = split[1].charAt(0);
      this.to = split[7].charAt(0);
    }

    @Override
    public String toString() {
      return String.format("Instruction [%c ~> %c]", this.from, this.to);
    }

    @Override
    public int compareTo(Instruction o) {
      return Long.compare(this.from * 1000 + this.to, o.from * 1000 + o.to);
    }
  }
}

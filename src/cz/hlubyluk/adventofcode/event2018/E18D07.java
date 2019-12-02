package cz.hlubyluk.adventofcode.event2018;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://adventofcode.com/2018/day/7
 *
 * @author HlubyLuk
 */
public class E18D07 implements IE18D07 {

  private static class Instruction implements Comparable<Instruction> {
    private final char from, to;

    public Instruction(final String line) {
      super();

      final String[] split = line.split(" ");

      this.from = split[1].charAt(0);
      this.to = split[7].charAt(0);
    }

    @Override
    public int compareTo(final Instruction o) {
      return Long.compare(this.from * 1000 + this.to, o.from * 1000 + o.to);
    }

    @Override
    public String toString() {
      return String.format("Instruction [%c ~> %c]", this.from, this.to);
    }
  }

  private static class Node implements Comparable<Node> {
    private final char data;
    private final Set<Character> parents = new TreeSet<>();
    private int seconds;

    public Node(final char data) {
      super();
      this.seconds = 1 + data - 'A' + 60;
      this.data = data;
    }

    @Override
    public int compareTo(final Node o) {
      return Character.compare(this.data, o.data);
    }
  }

//  @Override
//  public String getTag() {
//    return "2018 Day 7";
//  }

  private Set<Node> shared() {
    final Set<Instruction> instructions = new TreeSet<>();
    for (final String line : IE18D07.INPUT.split("\n")) {
      instructions.add(new Instruction(line));
    }

    final Set<Node> cache = new TreeSet<>();

    for (final Instruction instruction : instructions) {
      cache.add(new Node(instruction.from));
      cache.add(new Node(instruction.to));
    }

    for (final Instruction instruction : instructions) {
      for (final Node node : cache) {
        if (node.data == instruction.to) {
          node.parents.add(instruction.from);
        }
      }
    }

    return cache;
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    final Set<Node> cache = this.shared();
    final StringBuilder buffer = new StringBuilder();
    char first = ' ';
    while (!cache.isEmpty()) {
      for (final Iterator<Node> iterator = cache.iterator(); iterator.hasNext();) {
        final Node entry = iterator.next();

        if (entry.parents.isEmpty()) {
          first = entry.data;
          buffer.append(first);
          iterator.remove();
          break;
        }
      }

      for (final Node node : cache) {
        node.parents.remove(first);
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
    int seconds = 0;
    final int worker = 5;

    final Set<Node> cache = this.shared();
    final Set<Node> work = new TreeSet<>();
    final StringBuilder buffer = new StringBuilder();
    while (!cache.isEmpty() || !work.isEmpty()) {
      for (final Iterator<Node> iterator = cache.iterator(); iterator.hasNext();) {
        final Node entry = iterator.next();

        if (entry.parents.isEmpty() && work.size() < worker) {
          work.add(entry);
          iterator.remove();
        }
      }

      for (final Iterator<Node> it = work.iterator(); it.hasNext();) {
        final Node node = it.next();
        node.seconds -= 1;

        if (node.seconds == 0) {
          buffer.append(node.data);
          it.remove();

          for (final Node c : cache) {
            c.parents.remove(node.data);
          }
        }
      }

      seconds += 1;
    }

    return String.valueOf(seconds);
  }
}

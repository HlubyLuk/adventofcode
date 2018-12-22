package cz.hlubyluk.adventofcode2018.day;

import java.util.Iterator;
import java.util.Set;
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
    Set<Node> cache = shared();
    StringBuilder buffer = new StringBuilder();
    char first = ' ';
    while (!cache.isEmpty()) {
      for (Iterator<Node> iterator = cache.iterator(); iterator.hasNext();) {
        Node entry = iterator.next();

        if (entry.parents.isEmpty()) {
          first = entry.data;
          buffer.append(first);
          iterator.remove();
          break;
        }
      }

      for (Iterator<Node> it = cache.iterator(); it.hasNext();) {
        Node node = it.next();
        node.parents.remove(first);
      }
    }

    return buffer.toString();
  }

  private Set<Node> shared() {
    Set<Instruction> instructions = new TreeSet<>();
    for (String line : IDay7.INPUT.split("\n")) {
      instructions.add(new Instruction(line));
    }

    Set<Node> cache = new TreeSet<>();

    for (Instruction instruction : instructions) {
      cache.add(new Node(instruction.from));
      cache.add(new Node(instruction.to));
    }

    for (Instruction instruction : instructions) {
      for (Node node : cache) {
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
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    int seconds = 0;
    int worker = 5;

    Set<Node> cache = shared();
    Set<Node> work = new TreeSet<>();
    StringBuilder buffer = new StringBuilder();
    while (!cache.isEmpty() || !work.isEmpty()) {
      for (Iterator<Node> iterator = cache.iterator(); iterator.hasNext();) {
        Node entry = iterator.next();

        if (entry.parents.isEmpty() && work.size() < worker) {
          work.add(entry);
          iterator.remove();
        }
      }

      for (Iterator<Node> it = work.iterator(); it.hasNext();) {
        Node node = it.next();
        node.seconds -= 1;

        if (node.seconds == 0) {
          buffer.append(node.data);
          it.remove();

          for (Node c : cache) {
            c.parents.remove(node.data);
          }
        }
      }

      seconds += 1;
    }

    return String.valueOf(seconds);
  }

  private static class Node implements Comparable<Node> {
    private final char data;
    private final Set<Character> parents = new TreeSet<>();
    private int seconds;

    public Node(char data) {
      super();
      this.seconds = 1 + data - 'A' + 60;
      this.data = data;
    }

    @Override
    public int compareTo(Node o) {
      return Character.compare(this.data, o.data);
    }
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

  @Override
  public String getTag() {
    return "2018 Day 7";
  }
}

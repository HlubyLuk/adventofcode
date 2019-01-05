package cz.hlubyluk.adventofcode.event2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 implements IDay8 {

  private static class Node {
    private final List<Node> childers = new ArrayList<>();
    private final List<Integer> metadatas = new ArrayList<>();

    public Node() {
      super();
    }

    public int dataValue() {
      int sum = 0;

      if (!this.childers.isEmpty()) {
        final int size = this.childers.size();
        for (final int metadata : this.metadatas) {
          if (0 < metadata && metadata <= size) {
            sum += this.childers.get(metadata - 1).dataValue();
          }
        }
      } else {
        for (final int metadata : this.metadatas) {
          sum += metadata;
        }
      }

      return sum;
    }

    public int metadataSum() {
      int sum = 0;

      for (final Integer metadata : this.metadatas) {
        sum += metadata;
      }

      for (final Node node : this.childers) {
        sum += node.metadataSum();
      }

      return sum;
    }

    @Override
    public String toString() {
      return "Node [childers=" + this.childers + ", metadatas=" + this.metadatas + "]";
    }
  }

  @Override
  public String getTag() {
    return "2018 Day 8";
  }

  private Node parse(final Iterator<Integer> it) {
    final int data = it.next();
    final int metadata = it.next();

    final Node node = new Node();

    for (int i = 0; i < data; i += 1) {
      node.childers.add(this.parse(it));
    }

    for (int i = 0; i < metadata; i += 1) {
      node.metadatas.add(it.next());
    }

    return node;
  }

  @Override
  public String solveFirst() {
    final List<Integer> map = Arrays.asList(IDay8.INPUT.split(" ")).stream().map(Integer::valueOf)
        .collect(Collectors.toList());

    return String.valueOf(this.parse(map.iterator()).metadataSum());
  }

  @Override
  public String solveSecond() {
    final List<Integer> map = Arrays.asList(IDay8.INPUT.split(" ")).stream().map(Integer::valueOf)
        .collect(Collectors.toList());

    return String.valueOf(this.parse(map.iterator()).dataValue());
  }
}

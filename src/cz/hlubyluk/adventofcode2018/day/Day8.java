package cz.hlubyluk.adventofcode2018.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 implements IDay8 {

  @Override
  public String solveFirst() {
    List<Integer> map = Arrays.asList(IDay8.INPUT.split(" ")).stream().map(Integer::valueOf)
        .collect(Collectors.toList());

    return String.valueOf(this.parse(map.iterator()).metadataSum());
  }

  private Node parse(Iterator<Integer> it) {
    Integer data = it.next();
    int metadata = it.next();

    Node node = new Node(data);

    for (int i = 0; i < data; i += 1) {
      node.childers.add(this.parse(it));
    }

    for (int i = 0; i < metadata; i += 1) {
      node.metadatas.add(it.next());
    }

    return node;
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  private static class Node {
    private final int data;
    private final List<Node> childers = new ArrayList<>();
    private final List<Integer> metadatas = new ArrayList<>();

    public Node(int data) {
      super();
      this.data = data;
    }

    public int metadataSum() {
      int sum = 0;

      for (Integer metadata : this.metadatas) {
        sum += metadata;
      }

      for (Node node : this.childers) {
        sum += node.metadataSum();
      }

      return sum;
    }
  }
}

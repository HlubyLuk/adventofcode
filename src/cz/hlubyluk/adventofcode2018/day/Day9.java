package cz.hlubyluk.adventofcode2018.day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day9 implements IDay9 {

  @Override
  public String solveFirst() {
    return String.valueOf(this.parse().whoWin());
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  private Game parse() {
    int players = 0, points = 0, c = 0;

    Scanner scanner = new Scanner(IDay9.INPUT);
    while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        switch (c % 2) {
        case 0:
          players = scanner.nextInt();
          break;

        case 1:
          points = scanner.nextInt();
          break;
        }

        c += 1;
      } else {
        scanner.next();
      }
    }
    scanner.close();

    return new Game(players, points);
  }

  private static class Game {
    private final int players, mirable;

    public Game(int players, int mirable) {
      super();
      this.players = players;
      this.mirable = mirable;
    }

    public int whoWin() {
      Map<Integer, Integer> map = new HashMap<>();

      List<Integer> list = new ArrayList<>();
      list.add(0);

      int index = 1;
      for (int mirable = 1; mirable <= this.mirable; mirable += 1) {
        if (mirable % 23 == 0) {
          index -= 7;

          if (index < 1) {
            index += list.size();
          }

          int key = mirable % this.players;
          map.put(key, map.getOrDefault(key, 0) + mirable + list.remove(index));
        } else {
          index += 2;
          if (index > list.size()) {
            index = 1;
          }
          list.add(index, mirable);
        }

//        System.out.printf("[%d] ", mirable % this.players);
//        list.stream().forEach(x -> System.out.printf("%02d ", x));
//        System.out.println();
      }

      return map.values().stream().reduce(Integer::max).orElseGet(() -> 0);
    }

    @Override
    public String toString() {
      return "Game [player=" + players + ", score=" + mirable + "]";
    }
  }
}

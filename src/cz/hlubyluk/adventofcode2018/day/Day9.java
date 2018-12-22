package cz.hlubyluk.adventofcode2018.day;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class Day9 implements IDay9 {

  @Override
  public String solveFirst() {
    Game game = this.parse();
    return String.valueOf(new Solver(game.players).play(game.mirable));
  }

  @Override
  public String solveSecond() {
    Game game = this.parse();
    return String.valueOf(new Solver(game.players).play(game.mirable * 100));
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

    @Override
    public String toString() {
      return "Game [player=" + players + ", score=" + mirable + "]";
    }
  }

  public static class Solver {
    private final int players;

    public Solver(int players) {
      super();
      this.players = players;
    }

    public long play(int mirables) {
      Map<Integer, Long> sc = new HashMap<>();
      Deque<Integer> deque = new LinkedBlockingDeque<Integer>();
      deque.add(0);

      for (int i = 1; i <= mirables; i += 1) {
        if (i % 23 != 0) {
          Solver.rotate(deque, -2);
          deque.add(i);
        } else {
          Solver.rotate(deque, 6);
          Integer removed = deque.removeFirst();

          int key = i % this.players;

          sc.put(key, sc.getOrDefault(key, 0L) + i + removed);
        }
      }

      return sc.values().stream().reduce(Math::max).orElseGet(() -> 0L);
    }

    private static void rotate(Deque<Integer> deque, int count) {
      for (int i = 0; i < Math.abs(count); i += 1) {
        if (0 < count) {
          deque.addLast(deque.removeFirst());
        } else {
          deque.addFirst(deque.removeLast());
        }
      }
    }
  }
}

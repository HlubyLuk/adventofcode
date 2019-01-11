package cz.hlubyluk.adventofcode.event2018;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class Day9 implements IDay9 {

  private static class Game {
    private final int players, mirable;

    public Game(final int players, final int mirable) {
      super();
      this.players = players;
      this.mirable = mirable;
    }

    @Override
    public String toString() {
      return "Game [player=" + this.players + ", score=" + this.mirable + "]";
    }
  }

  public static class Solver {
    private static void rotate(final Deque<Integer> deque, final int count) {
      for (int i = 0; i < Math.abs(count); i += 1) {
        if (0 < count) {
          deque.addLast(deque.removeFirst());
        } else {
          deque.addFirst(deque.removeLast());
        }
      }
    }

    private final int players;

    public Solver(final int players) {
      super();
      this.players = players;
    }

    public long play(final int mirables) {
      final Map<Integer, Long> sc = new HashMap<>();
      final Deque<Integer> deque = new LinkedBlockingDeque<>();
      deque.add(0);

      for (int i = 1; i <= mirables; i += 1) {
        if (i % 23 != 0) {
          Solver.rotate(deque, -2);
          deque.add(i);
        } else {
          Solver.rotate(deque, 6);
          final Integer removed = deque.removeFirst();

          final int key = i % this.players;

          sc.put(key, sc.getOrDefault(key, 0L) + i + removed);
        }
      }

      return sc.values().stream().reduce(Math::max).orElseGet(() -> 0L);
    }
  }

  @Override
  public String getTag() {
    return "2018 Day 9";
  }

  private Game parse() {
    int players = 0, points = 0, c = 0;

    final Scanner scanner = new Scanner(IDay9.INPUT);
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

  @Override
  public String solveFirst() {
    final Game game = this.parse();
    return String.valueOf(new Solver(game.players).play(game.mirable));
  }

  @Override
  public String solveSecond() {
    final Game game = this.parse();
    return String.valueOf(new Solver(game.players).play(game.mirable * 100));
  }
}

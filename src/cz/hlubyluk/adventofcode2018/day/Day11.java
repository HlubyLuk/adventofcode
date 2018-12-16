package cz.hlubyluk.adventofcode2018.day;

public class Day11 implements IDay11 {

  private static final int EDGE = 301;

  @Override
  public String solveFirst() {
    int s = IDay11.INPUT;
    int mx = 0, my = 0, mm = 0;

    for (int y = 1; y < Day11.EDGE; y += 1) {
      for (int x = 1; x < Day11.EDGE; x += 1) {
        int sum = 0;

        for (int a = y; a - y < 3; a += 1) {
          for (int b = x; b - x < 3; b += 1) {
            sum += this.powerLevel(b + 1, a + 1, s);
          }
        }

        if (mm < sum) {
          mm = sum;
          mx = x;
          my = y;
        }
      }
    }

    return String.format("%d,%d", mx, my);
  }

  private int powerLevel(int x, int y, int serialNumber) {
    int r = x + 10;
    int p = r * y;
    p += serialNumber;
    p *= r;
    p /= 100;
    p %= 10;
    p -= 5;

    return p;
  }

  @Override
  public String solveSecond() {
    int s = IDay11.INPUT;
    int mx = 0, my = 0, mm = 0, me = 0;

    int[][] powerLevels = new int[Day11.EDGE + 1][Day11.EDGE + 1];
    for (int y = 1; y < Day11.EDGE; y += 1) {
      for (int x = 1; x < Day11.EDGE; x += 1) {
        powerLevels[y][x] = this.powerLevel(x, y, s);
      }
    }

    int[][] sums = new int[Day11.EDGE + 1][Day11.EDGE + 1];
    for (int y = 1; y < Day11.EDGE; y += 1) {
      for (int x = 1; x < Day11.EDGE; x += 1) {
        sums[y][x] = powerLevels[y][x] - sums[y - 1][x - 1] + sums[y][x - 1] + sums[y - 1][x];
      }
    }

    for (int e = 1; e < Day11.EDGE; e += 1) {
      for (int y = 1; y < Day11.EDGE - e; y += 1) {
        for (int x = 1; x < Day11.EDGE - e; x += 1) {
          int value = sums[y][x] + sums[y + e][x + e] - sums[y][x + e] - sums[y + e][x];

          if (mm < value) {
            mm = value;
            mx = x + 1;
            my = y + 1;
            me = e;
          }
        }
      }
    }

    if (!"232,289,8".equals(String.format("%d,%d,%d", mx, my, me))) {
      System.err.println("KO!!! " + String.format("%d,%d,%d", mx, my, me));
    }

    return String.format("%d,%d,%d", mx, my, me);
  }
}

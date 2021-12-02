package main.cz.hlubyluk.adventofcode.event2018;

public class E18D11 implements IE18D11 {

  private static final int EDGE = 301;

//  @Override
//  public String getTag() {
//    return "2018 Day 11";
//  }

  private int powerLevel(final int x, final int y, final int serialNumber) {
    final int r = x + 10;
    int p = r * y;
    p += serialNumber;
    p *= r;
    p /= 100;
    p %= 10;
    p -= 5;

    return p;
  }

  @Override
  public String solveFirst() {
    final int s = IE18D11.INPUT;
    int mx = 0, my = 0, mm = 0;

    for (int y = 1; y < E18D11.EDGE; y += 1) {
      for (int x = 1; x < E18D11.EDGE; x += 1) {
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

  @Override
  public String solveSecond() {
    final int s = IE18D11.INPUT;
    int mx = 0, my = 0, mm = 0, me = 0;

    final int[][] powerLevels = new int[E18D11.EDGE + 1][E18D11.EDGE + 1];
    for (int y = 1; y < E18D11.EDGE; y += 1) {
      for (int x = 1; x < E18D11.EDGE; x += 1) {
        powerLevels[y][x] = this.powerLevel(x, y, s);
      }
    }

    final int[][] sums = new int[E18D11.EDGE + 1][E18D11.EDGE + 1];
    for (int y = 1; y < E18D11.EDGE; y += 1) {
      for (int x = 1; x < E18D11.EDGE; x += 1) {
        sums[y][x] = powerLevels[y][x] - sums[y - 1][x - 1] + sums[y][x - 1] + sums[y - 1][x];
      }
    }

    for (int e = 1; e < E18D11.EDGE; e += 1) {
      for (int y = 1; y < E18D11.EDGE - e; y += 1) {
        for (int x = 1; x < E18D11.EDGE - e; x += 1) {
          final int value = sums[y][x] + sums[y + e][x + e] - sums[y][x + e] - sums[y + e][x];

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

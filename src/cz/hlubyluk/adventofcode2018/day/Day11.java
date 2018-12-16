package cz.hlubyluk.adventofcode2018.day;

public class Day11 implements IDay11 {

  private static final int EDGE = 300;

  @Override
  public String solveFirst() {
    int s = IDay11.INPUT;
    int mx = 0, my = 0, mm = 0;

    for (int y = 1; y <= Day11.EDGE; y += 1) {
      for (int x = 1; x <= Day11.EDGE; x += 1) {
        int sum = 0;

        for (int a = y; a - y < 3; a += 1) {
          for (int b = x; b - x < 3; b += 1) {
            sum += powerLevel(b, a, s);
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

    int[][] grid = new int[Day11.EDGE + 1][Day11.EDGE + 1];
    for (int y = 1; y <= Day11.EDGE; y += 1) {
      for (int x = 1; x <= Day11.EDGE; x += 1) {
        grid[y][x] = this.powerLevel(x, y, s);
      }
    }

    for (int e = 1; e < Day11.EDGE / 3; e += 1) {
      for (int y = 1; y <= Day11.EDGE; y += 1) {
        for (int x = 1; x <= Day11.EDGE; x += 1) {
          int sum = 0;

          for (int a = y; a - y < e && a < Day11.EDGE; a += 1) {
            for (int b = x; b - x < e && b < Day11.EDGE; b += 1) {
              sum += grid[a][b];
            }
          }

          if (mm < sum) {
            mm = sum;
            mx = x;
            my = y;
            me = e;
          }
        }
      }
    }

    return String.format("%d,%d,%d", mx, my, me);
  }
}

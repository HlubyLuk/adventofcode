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
    // TODO Auto-generated method stub
    return null;
  }
}

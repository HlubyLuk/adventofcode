package cz.hlubyluk.adventofcode.event2015;

import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

import cz.hlubyluk.adventofcode.event2015.input.IE15D03;

/**
 * @author HlubyLuk
 */
public class E15D03 implements IE15D03 {

  @Override
  public String getTag() {
    return "2015 day 3";
  }

  @Override
  public String solveFirst() {
    Map<Point, Integer> houses = new HashMap<>();
    houses.put(new Point(0, 0), 1);

    int x = 0, y = 0;

    StringCharacterIterator it = new StringCharacterIterator(IE15D03.INPUT);
    for (char c = it.first(); it.current() != StringCharacterIterator.DONE; c = it.next()) {
      switch (c) {
      case '^':
        y += 1;
        break;
      case 'v':
        y -= 1;
        break;
      case '>':
        x += 1;
        break;
      case '<':
        x -= 1;
        break;
      }

      Point point = new Point(x, y);

      houses.put(point, houses.getOrDefault(point, 0) + 1);
    }

    return String.valueOf(houses.size());
  }

  @Override
  public String solveSecond() {
    Map<Point, Integer> houses = new HashMap<>();
    houses.put(new Point(0, 0), 1);

    int xS = 0, yS = 0, xR = 0, yR = 0;

    StringCharacterIterator it = new StringCharacterIterator(IE15D03.INPUT);
    for (char santa = it.first(), robot = it.next(); it.current() != StringCharacterIterator.DONE; santa = it
        .next(), robot = it.next()) {
      switch (santa) {
      case '^':
        yS += 1;
        break;

      case 'v':
        yS -= 1;
        break;

      case '>':
        xS += 1;
        break;

      case '<':
        xS -= 1;
        break;
      }
      Point pointSanta = new Point(xS, yS);
      houses.put(pointSanta, houses.getOrDefault(pointSanta, 0) + 1);

      switch (robot) {
      case '^':
        yR += 1;
        break;

      case 'v':
        yR -= 1;
        break;

      case '>':
        xR += 1;
        break;

      case '<':
        xR -= 1;
        break;
      }
      Point pointRobot = new Point(xR, yR);
      houses.put(pointRobot, houses.getOrDefault(pointRobot, 0) + 1);
    }

    return String.valueOf(houses.size());
  }

  private static class Point {
    private final int x, y;

    private Point(int x, int y) {
      super();
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Point other = (Point) obj;
      if (x != other.x)
        return false;
      if (y != other.y)
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Point [x=" + x + ", y=" + y + "]";
    }
  }
}

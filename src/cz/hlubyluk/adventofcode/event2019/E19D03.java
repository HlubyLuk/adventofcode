/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hlubyluk
 */
public class E19D03 implements IE19D03 {

  private static final Pattern PATTER = Pattern.compile("(\\w)(\\d+)");
  private static final List<Pair> F = new ArrayList<>();
  private static final List<Pair> S = new ArrayList<>();

  static {
    for (String item : IE19D03.F.split(",")) {
      Matcher matcher = E19D03.PATTER.matcher(item);
      if (matcher.find()) {
        E19D03.F.add(new Pair(matcher.group(1), Integer.valueOf(matcher.group(2))));
      } else {
        System.out.println(String.format("Matcher miss %s", item));
      }
    }

    for (String item : IE19D03.S.split(",")) {
      Matcher matcher = E19D03.PATTER.matcher(item);
      if (matcher.find()) {
        E19D03.S.add(new Pair(matcher.group(1), Integer.valueOf(matcher.group(2))));
      } else {
        System.out.println(String.format("Matcher miss %s", item));
      }
    }
  }

  @Override
  public String solveFirst() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  private static class Pair {
    private final String direction;
    private final int steps;

    /**
     * @param direction
     * @param steps
     */
    private Pair(String direction, int steps) {
      super();
      this.direction = direction;
      this.steps = steps;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((direction == null) ? 0 : direction.hashCode());
      result = prime * result + steps;
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
      Pair other = (Pair) obj;
      if (direction == null) {
        if (other.direction != null)
          return false;
      } else if (!direction.equals(other.direction))
        return false;
      if (steps != other.steps)
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Pair [direction=" + direction + ", steps=" + steps + "]";
    }
  }

  private static class Coordinate {
    private final int x, y;

    /**
     * @param x
     * @param y
     */
    private Coordinate(int x, int y) {
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
      Coordinate other = (Coordinate) obj;
      if (x != other.x)
        return false;
      if (y != other.y)
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Coordinate [x=" + x + ", y=" + y + "]";
    }
  }
}

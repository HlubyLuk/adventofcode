package cz.hlubyluk.adventofcode2018.day;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2018/day/6
 *
 * @author HlubyLuk
 */
public class Day6 implements IDay6 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    String[] rows = IDay6.INPUT.split("\n");
    int maxX = 0, maxY = 0;

    Set<Point> interferences = new HashSet<>(rows.length);
    for (int i = 0; i < rows.length; i += 1) {
      String[] split = rows[i].split(", ");

      Point point = new Point(Integer.valueOf(split[0]), Integer.valueOf(split[1]), i);
      interferences.add(point);

      if (maxX < point.x) {
        maxX = point.x + 1;
      }

      if (maxY < point.y) {
        maxY = point.y + 1;
      }
    }

    Set<Point> points = new LinkedHashSet<>();
    for (int y = 0; y < maxY; y += 1) {
      for (int x = 0; x < maxX; x += 1) {
        points.add(new Point(x, y));
      }
    }

    Matrix matrix = new Matrix(maxX, maxY);
    for (Point point : points) {
      int min = Integer.MAX_VALUE;

      for (Point interference : interferences) {
        if (point.equals(interference)) {
          point.setWhat(interference.what);
        } else {
          int distance = point.manhattanDisntance(interference);

          if (distance == min) {
            point.setWhat(-1);
          } else if (distance < min) {
            min = distance;

            point.setWhat(interference.what);
          }
        }
      }

      matrix.setPoint(point);
    }

    Set<Integer> edge = new HashSet<>();
    for (Point point : points) {
      if (point.y == 0 || point.y == maxY - 1 || point.x == 0 || point.x == maxX - 1) {
        edge.add(point.what);
      }
    }

    Map<Integer, Integer> counter = new HashMap<>();
    for (Point point : points) {
      if (!edge.contains(point.what)) {
        counter.put(point.what, counter.getOrDefault(point.what, 1) + 1);
      }
    }

    return String.valueOf(counter.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList())
        .get(counter.size() - 1).getValue());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    String[] rows = IDay6.INPUT.split("\n");
    int maxX = 0, maxY = 0;

    Set<Point> interferences = new HashSet<>(rows.length);
    for (int i = 0; i < rows.length; i += 1) {
      String[] split = rows[i].split(", ");

      Point point = new Point(Integer.valueOf(split[0]), Integer.valueOf(split[1]), i);
      interferences.add(point);

      if (maxX < point.x) {
        maxX = point.x + 1;
      }

      if (maxY < point.y) {
        maxY = point.y + 1;
      }
    }

    int counter = 0;
    for (int y = 0; y < maxY; y += 1) {
      for (int x = 0; x < maxX; x += 1) {
        int distance = 0;
        Point point = new Point(x, y);

        for (Point interfence : interferences) {
          distance += point.manhattanDisntance(interfence);
        }

        if (distance < 10000) {
          counter += 1;
        }
      }
    }

    return String.valueOf(counter);
  }

  private static class Matrix {
    private int[][] matrix;

    public Matrix(int x, int y) {
      this.matrix = new int[y][x];
    }

    public void setPoint(Point point) {
      this.matrix[point.y][point.x] = point.what;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder("Matrix [\n");

      for (int[] row : this.matrix) {
        for (int cell : row) {
          builder.append(cell);
        }
        builder.append('\n');
      }
      builder.append(']');

      return builder.toString();
    }
  }

  private static class Point {
    private final int x, y;
    private int what = -1;

    public Point(int x, int y) {
      super();
      this.x = x;
      this.y = y;
    }

    public Point(int x, int y, int what) {
      super();
      this.x = x;
      this.y = y;
      this.what = what;
    }

    public void setWhat(int what) {
      this.what = what;
    }

    public int manhattanDisntance(Point o) {
      return Math.abs(o.x - this.x) + Math.abs(o.y - this.y);
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
      return "Point [what=" + what + "]";
    }
  }

  @Override
  public String getTag() {
    return "2018 Day 6";
  }
}

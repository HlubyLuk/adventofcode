package cz.hlubyluk.adventofcode.event2018;

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

  private static class Matrix {
    private final int[][] matrix;

    public Matrix(final int x, final int y) {
      this.matrix = new int[y][x];
    }

    public void setPoint(final Point point) {
      this.matrix[point.y][point.x] = point.what;
    }

    @Override
    public String toString() {
      final StringBuilder builder = new StringBuilder("Matrix [\n");

      for (final int[] row : this.matrix) {
        for (final int cell : row) {
          builder.append(cell);
        }
        builder.append('\n');
      }
      builder.append(']');

      return builder.toString();
    }
  }

  private static class Point {
    private int what = -1;
    private final int x, y;

    public Point(final int x, final int y) {
      super();
      this.x = x;
      this.y = y;
    }

    public Point(final int x, final int y, final int what) {
      super();
      this.x = x;
      this.y = y;
      this.what = what;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (this.getClass() != obj.getClass()) {
        return false;
      }
      final Point other = (Point) obj;
      if (this.x != other.x) {
        return false;
      }
      if (this.y != other.y) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.x;
      result = prime * result + this.y;
      return result;
    }

    public int manhattanDisntance(final Point o) {
      return Math.abs(o.x - this.x) + Math.abs(o.y - this.y);
    }

    public void setWhat(final int what) {
      this.what = what;
    }

    @Override
    public String toString() {
      return "Point [what=" + this.what + "]";
    }
  }

  @Override
  public String getTag() {
    return "2018 Day 6";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    final String[] rows = IDay6.INPUT.split("\n");
    int maxX = 0, maxY = 0;

    final Set<Point> interferences = new HashSet<>(rows.length);
    for (int i = 0; i < rows.length; i += 1) {
      final String[] split = rows[i].split(", ");

      final Point point = new Point(Integer.valueOf(split[0]), Integer.valueOf(split[1]), i);
      interferences.add(point);

      if (maxX < point.x) {
        maxX = point.x + 1;
      }

      if (maxY < point.y) {
        maxY = point.y + 1;
      }
    }

    final Set<Point> points = new LinkedHashSet<>();
    for (int y = 0; y < maxY; y += 1) {
      for (int x = 0; x < maxX; x += 1) {
        points.add(new Point(x, y));
      }
    }

    final Matrix matrix = new Matrix(maxX, maxY);
    for (final Point point : points) {
      int min = Integer.MAX_VALUE;

      for (final Point interference : interferences) {
        if (point.equals(interference)) {
          point.setWhat(interference.what);
        } else {
          final int distance = point.manhattanDisntance(interference);

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

    final Set<Integer> edge = new HashSet<>();
    for (final Point point : points) {
      if (point.y == 0 || point.y == maxY - 1 || point.x == 0 || point.x == maxX - 1) {
        edge.add(point.what);
      }
    }

    final Map<Integer, Integer> counter = new HashMap<>();
    for (final Point point : points) {
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
    final String[] rows = IDay6.INPUT.split("\n");
    int maxX = 0, maxY = 0;

    final Set<Point> interferences = new HashSet<>(rows.length);
    for (int i = 0; i < rows.length; i += 1) {
      final String[] split = rows[i].split(", ");

      final Point point = new Point(Integer.valueOf(split[0]), Integer.valueOf(split[1]), i);
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
        final Point point = new Point(x, y);

        for (final Point interfence : interferences) {
          distance += point.manhattanDisntance(interfence);
        }

        if (distance < 10000) {
          counter += 1;
        }
      }
    }

    return String.valueOf(counter);
  }
}

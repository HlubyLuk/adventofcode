/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import main.cz.hlubyluk.adventofcode.Utils.Point;

/**
 * @author hlubyluk
 *
 */
public class E19D10 implements IE19D10 {

  private static final char ASTEROID = '#';
  private static final List<Point> ASTEROIDS = new ArrayList<>();

  static {
    final var rows = IE19D10.INPUT.split("\n");
    for (var y = 0; y < rows.length; y += 1) {
      final var row = rows[y];
      for (var x = 0; x < row.length(); x += 1) {
        if (E19D10.ASTEROID == row.charAt(x)) {
          E19D10.ASTEROIDS.add(new Point(x, y));
        }
      }
    }
  }

  /**
   *
   */
  public E19D10() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public String solveFirst() {
    var max = -1;

    for (final var a : E19D10.ASTEROIDS) {
      final var buffer = new HashSet<Double>();

      for (final var b : E19D10.ASTEROIDS) {
        if (a.equals(b)) {
          continue;
        }

        final var x = b.x - a.x;
        final var y = b.y - a.y;

        final var atan2 = Math.atan2(y, x);
//        final var deg = Math.toDegrees(atan2);

        buffer.add(atan2);
      }

      final var size = buffer.size();
      if (size > max) {
        max = size;
      }
    }

    return this.result(284, max);
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }
}

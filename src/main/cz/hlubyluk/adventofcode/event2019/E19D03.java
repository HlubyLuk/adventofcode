/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.cz.hlubyluk.adventofcode.Utils.Pair;
import main.cz.hlubyluk.adventofcode.Utils.Point;

/**
 * @author hlubyluk
 */
public class E19D03 implements IE19D03 {

  private static final Pattern PATTER = Pattern.compile("(\\w)(\\d+)");
  private static final List<Set<Point>> POINTS = new ArrayList<>();

  static {
    for (String wire : IE19D03.INPUT.split("\n")) {
      Set<Pair<String, Integer>> tmp = new LinkedHashSet<>();

      for (String step : wire.split(",")) {
        Matcher matcher = E19D03.PATTER.matcher(step);
        if (matcher.find()) {
          tmp.add(new Pair<String, Integer>(matcher.group(1), Integer.valueOf(matcher.group(2))));
        }
      }

      E19D03.POINTS.add(E19D03.pairToPoint(tmp));
    }
  }

  private static Set<Point> pairToPoint(Set<Pair<String, Integer>> wire) {
    Set<Point> points = new LinkedHashSet<>();
    int x = 0, y = 0;

    for (Pair<String, Integer> item : wire) {
      int iteration = 0;
      do {
        switch (item.a) {
        case "L":
          x -= 1;
          break;
        case "R":
          x += 1;
          break;
        case "U":
          y += 1;
          break;
        case "D":
          y -= 1;
          break;

        default:
          throw new IllegalArgumentException("Unexpected value: " + item.a);
        }

        iteration += 1;
        points.add(new Point(x, y));
      } while (iteration < item.b);
    }

    return points;
  }

  private static int cmpManhattan(Point a, Point b) {
    int x = Math.abs(a.x) + Math.abs(a.y);
    int y = Math.abs(b.x) + Math.abs(b.y);

    return Integer.compare(x, y);
  }

  @Override
  public String solveFirst() {
    int result = E19D03.POINTS.get(0).stream().filter(i -> E19D03.POINTS.get(1).contains(i))
        .sorted(E19D03::cmpManhattan).limit(1).map(i -> Math.abs(i.x) + Math.abs(i.y)).findFirst().orElseGet(null);

    return this.result(709, result);
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }
}

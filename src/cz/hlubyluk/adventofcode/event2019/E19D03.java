/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.Utils.Pair;
import cz.hlubyluk.adventofcode.Utils.Point;

/**
 * @author hlubyluk
 */
public class E19D03 implements IE19D03 {

  private static final Pattern PATTER = Pattern.compile("(\\w)(\\d+)");
  private static final List<Pair<String, Integer>> F = new ArrayList<>();
  private static final List<Pair<String, Integer>> S = new ArrayList<>();

  static {
    for (String item : IE19D03.F.split(",")) {
      Matcher matcher = E19D03.PATTER.matcher(item);
      if (matcher.find()) {
        E19D03.F.add(new Pair<>(matcher.group(1), Integer.valueOf(matcher.group(2))));
      } else {
        System.out.println(String.format("Matcher miss %s", item));
      }
    }

    for (String item : IE19D03.S.split(",")) {
      Matcher matcher = E19D03.PATTER.matcher(item);
      if (matcher.find()) {
        E19D03.S.add(new Pair<>(matcher.group(1), Integer.valueOf(matcher.group(2))));
      } else {
        System.out.println(String.format("Matcher miss %s", item));
      }
    }
  }

  @Override
  public String solveFirst() {
    Map<Point, Integer> points = new HashMap<>();

    Point point = new Point(0, 0);
    for (Pair<String, Integer> pair : E19D03.F) {
      point = this.solve(points, point, pair);
    }

    point = new Point(0, 0);
    for (Pair<String, Integer> pair : E19D03.S) {
      point = this.solve(points, point, pair);
    }

    Set<Entry<Point, Integer>> set = points.entrySet();
    int max = set.stream().max(Map.Entry.comparingByValue()).get().getValue();
    int minX = Math.abs(set.stream().min(new PointCmpByX()).get().getKey().x);
    int minY = Math.abs(set.stream().min(new PointCmpByY()).get().getKey().y);

    set.stream().filter(i -> i.getValue() == max).map(i -> new Point(i.getKey().x + minX, i.getKey().y + minY))
        .sorted(new ManhattanCmp()).forEach(System.out::println);

    return null;
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  private Point solve(Map<Point, Integer> points, Point start, Pair<String, Integer> pair) {
    Point tmp = null;

    for (int i = 1; i <= pair.b; i += 1) {
      switch (pair.a) {
      case "L":
        tmp = new Point(start.x - i, start.y);
        break;
      case "U":
        tmp = new Point(start.x, start.y + i);
        break;
      case "R":
        tmp = new Point(start.x + i, start.y);
        break;
      case "D":
        tmp = new Point(start.x, start.y - i);
        break;
      default:
        throw new IllegalArgumentException("Unknown direction!!!");
      }

      points.put(tmp, points.getOrDefault(tmp, 0) + 1);
    }

    return tmp;
  }

  private static class ManhattanCmp implements Comparator<Point> {

    private ManhattanCmp() {
    }

    @Override
    public int compare(Point a, Point b) {
      return Integer.compare(Math.abs(a.x) + Math.abs(a.y), Math.abs(b.x) + Math.abs(b.y));
    }
  }

  private static class PointCmpByX implements Comparator<Map.Entry<Point, Integer>> {

    private PointCmpByX() {
    }

    @Override
    public int compare(Entry<Point, Integer> o1, Entry<Point, Integer> o2) {
      return Integer.compare(o1.getKey().x, o2.getKey().x);
    }
  }

  private static class PointCmpByY implements Comparator<Map.Entry<Point, Integer>> {

    private PointCmpByY() {
    }

    @Override
    public int compare(Entry<Point, Integer> o1, Entry<Point, Integer> o2) {
      return Integer.compare(o1.getKey().y, o2.getKey().y);
    }
  }
}

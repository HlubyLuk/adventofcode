/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.Utils.Pair;
import cz.hlubyluk.adventofcode.Utils.Point;

/**
 * @author hlubyluk
 */
public class E19D03 implements IE19D03 {

  private static final Pattern PATTER = Pattern.compile("(\\w)(\\d+)");
  private static final List<List<Pair<String, Integer>>> X = new ArrayList<>();

  private static void parser(String[] input) {
    List<Pair<String, Integer>> tmp = new ArrayList<>();

    for (String item : input) {
      Matcher matcher = E19D03.PATTER.matcher(item);
      if (matcher.find()) {
        tmp.add(new Pair<>(matcher.group(1), Integer.valueOf(matcher.group(2))));
      } else {
        System.out.println(String.format("Matcher miss %s", item));
      }
    }

    E19D03.X.add(tmp);
  }

  static {
    E19D03.parser(IE19D03.F.split(","));
    E19D03.parser(IE19D03.S.split(","));
  }

  /**
   * 2682, 7439, 24, 184
   */
  @Override
  public String solveFirst() {
    return null;
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  private Point nextPoint(Point start, Pair<String, Integer> pair) {
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
    }

    return tmp;
  }
}

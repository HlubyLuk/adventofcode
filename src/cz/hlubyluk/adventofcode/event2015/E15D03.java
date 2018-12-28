package cz.hlubyluk.adventofcode.event2015;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

import cz.hlubyluk.adventofcode.Utils;
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
    Map<Utils.Point, Integer> houses = new HashMap<>();
    houses.put(new Utils.Point(0, 0), 1);

    int x = 0, y = 0;

    StringCharacterIterator it = new StringCharacterIterator(IE15D03.INPUT);
    for (char c = it.first(); it.current() != CharacterIterator.DONE; c = it.next()) {
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

      Utils.Point point = new Utils.Point(x, y);

      houses.put(point, houses.getOrDefault(point, 0) + 1);
    }

    return String.valueOf(houses.size());
  }

  @Override
  public String solveSecond() {
    Map<Utils.Point, Integer> houses = new HashMap<>();
    houses.put(new Utils.Point(0, 0), 1);

    int xS = 0, yS = 0, xR = 0, yR = 0;

    StringCharacterIterator it = new StringCharacterIterator(IE15D03.INPUT);
    for (char santa = it.first(), robot = it.next(); it.current() != CharacterIterator.DONE; santa = it
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
      Utils.Point pointSanta = new Utils.Point(xS, yS);
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
      Utils.Point pointRobot = new Utils.Point(xR, yR);
      houses.put(pointRobot, houses.getOrDefault(pointRobot, 0) + 1);
    }

    return String.valueOf(houses.size());
  }
}

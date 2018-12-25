package cz.hlubyluk.adventofcode.event2015;

import java.text.StringCharacterIterator;

import cz.hlubyluk.adventofcode.IDay;
import cz.hlubyluk.adventofcode.event2015.input.IE15D01;

public class E15D01 implements IE15D01 {

  @Override
  public String getTag() {
    return "2015 day 1";
  }

  @Override
  public String solveFirst() {
    int floor = 0;

    StringCharacterIterator it = new StringCharacterIterator(IE15D01.INPUT);
    for (char c = it.first(); c != StringCharacterIterator.DONE; c = it.next()) {
      if (c == '(') {
        floor += 1;
      } else if (c == ')') {
        floor -= 1;
      }
    }

    return String.valueOf(floor);
  }

  @Override
  public String solveSecond() {
    int floor = 0;

    StringCharacterIterator it = new StringCharacterIterator(IE15D01.INPUT);
    for (char c = it.first(); c != StringCharacterIterator.DONE; c = it.next()) {
      if (c == '(') {
        floor += 1;
      } else if (c == ')') {
        floor -= 1;
      }

      if (floor == -1) {
        return String.valueOf(it.getIndex() + 1);
      }
    }

    return String.valueOf(IDay.NOT_IMPLEMENT);
  }
}

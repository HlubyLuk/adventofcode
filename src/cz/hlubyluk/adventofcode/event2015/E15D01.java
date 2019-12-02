package cz.hlubyluk.adventofcode.event2015;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import cz.hlubyluk.adventofcode.IDay;

public class E15D01 implements IE15D01 {

//  @Override
//  public String getTag() {
//    return "2015 day 1";
//  }

  @Override
  public String solveFirst() {
    int floor = 0;

    final StringCharacterIterator it = new StringCharacterIterator(IE15D01.INPUT);
    for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
      if (c == '(') {
        floor += 1;
      } else if (c == ')') {
        floor -= 1;
      }
    }

    return this.result(138, floor); // String.valueOf(floor);
  }

  @Override
  public String solveSecond() {
    int floor = 0, result = IDay.NOT_IMPLEMENT;

    final StringCharacterIterator it = new StringCharacterIterator(IE15D01.INPUT);
    for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
      if (c == '(') {
        floor += 1;
      } else if (c == ')') {
        floor -= 1;
      }

      if (floor == -1) {
        result = it.getIndex() + 1;
        break;
//        return String.valueOf(it.getIndex() + 1);
      }
    }

    return this.result(1771, result);
  }
}

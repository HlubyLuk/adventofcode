package cz.hlubyluk.adventofcode;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import cz.hlubyluk.adventofcode.event2015.*;

/**
 * Advent of Code is an Advent calendar of small programming puzzles for a
 * variety of skill sets and skill levels that can be solved in any programming
 * language you like. People use them as a speed contest, interview prep,
 * company training, university coursework, practice problems, or to challenge
 * each other.
 *
 * You don't need a computer science background to participate - just a little
 * programming knowledge and some problem solving skills will get you pretty
 * far. Nor do you need a fancy computer; every problem has a solution that
 * completes in at most 15 seconds on ten-year-old hardware.
 *
 * @author HlubyLuk
 */
public final class Main {

  /**
   * Main function.
   *
   * @param args
   */
  public static void main(String[] args) {
    List<IDay> days = new ArrayList<>();
    days.add(new E15D01());
    days.add(new E15D02());
    days.add(new E15D03());
    days.add(new E15D04());
    days.add(new E15D05());
    days.add(new E15D06());
    days.add(new E15D07());
    days.add(new E15D08());
    days.add(new E15D09());
    days.add(new E15D10());
    days.add(new E15D11());
    days.add(new E15D12());
    days.add(new E15D13());

    Instant tmp = null;
    Instant start = Instant.now();

    for (IDay day : days) {
      tmp = Instant.now();
      String firstResult = day.solveFirst();
      Duration firstBetween = Duration.between(tmp, Instant.now());

      tmp = Instant.now();
      String secondResult = day.solveSecond();
      Duration secondBetween = Duration.between(tmp, Instant.now());

      System.out.printf("%s%n\tpart 1 result %26s, duration %7d%n\tpart 2 result %26s, duration %7d%n", day.getTag(),
          firstResult, firstBetween.toMillis(), secondResult, secondBetween.toMillis());
    }

    System.out.printf("Total %d millis.%n", Duration.between(start, Instant.now()).toMillis());
  }
}

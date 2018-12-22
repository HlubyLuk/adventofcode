package cz.hlubyluk.adventofcode2018;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import cz.hlubyluk.adventofcode2018.day.Day1;
import cz.hlubyluk.adventofcode2018.day.Day10;
import cz.hlubyluk.adventofcode2018.day.Day11;
import cz.hlubyluk.adventofcode2018.day.Day2;
import cz.hlubyluk.adventofcode2018.day.Day3;
import cz.hlubyluk.adventofcode2018.day.Day4;
import cz.hlubyluk.adventofcode2018.day.Day5;
import cz.hlubyluk.adventofcode2018.day.Day6;
import cz.hlubyluk.adventofcode2018.day.Day7;
import cz.hlubyluk.adventofcode2018.day.Day8;
import cz.hlubyluk.adventofcode2018.day.Day9;

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
    days.add(new Day1());
    days.add(new Day2());
    days.add(new Day3());
    days.add(new Day4());
    days.add(new Day5());
    days.add(new Day6());
    days.add(new Day7());
    days.add(new Day8());
    days.add(new Day9());
    days.add(new Day10());
    days.add(new Day11());

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

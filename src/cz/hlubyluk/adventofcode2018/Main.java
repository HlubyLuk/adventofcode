package cz.hlubyluk.adventofcode2018;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import cz.hlubyluk.adventofcode2018.day.Day1;
import cz.hlubyluk.adventofcode2018.day.IDay;

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
    Instant start = Instant.now();

    List<IDay> days = new ArrayList<>();
    days.add(new Day1());

    for (int i = 0; i < days.size(); i += 1) {
      IDay day = days.get(i);

      System.out.printf("Day %d, first is %d, second is %d %d millis.%n", i + 1, day.solveFirst(), day.solveSecond(),
          Duration.between(start, Instant.now()).toMillis());
    }

    System.out.printf("Total %d millis.%n", Duration.between(start, Instant.now()).toMillis());
  }
}

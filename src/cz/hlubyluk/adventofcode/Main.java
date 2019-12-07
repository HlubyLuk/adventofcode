package cz.hlubyluk.adventofcode;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import cz.hlubyluk.adventofcode.event2019.E19D03;
import cz.hlubyluk.adventofcode.event2019.E19D04;

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
  private static final List<IDay> DAYS = new ArrayList<>();

  static {
//    Main.DAYS.add(new E15D01());
//    Main.DAYS.add(new E15D02());
//    Main.DAYS.add(new E15D03());
//    Main.DAYS.add(new E15D04());
//    Main.DAYS.add(new E15D05());
//    Main.DAYS.add(new E15D06());
//    Main.DAYS.add(new E15D07());
//    Main.DAYS.add(new E15D08());
//    Main.DAYS.add(new E15D09());
//    Main.DAYS.add(new E15D10());
//    Main.DAYS.add(new E15D11());
//    Main.DAYS.add(new E15D12());
//    Main.DAYS.add(new E15D13());
//    Main.DAYS.add(new E15D14());
//    Main.DAYS.add(new E15D15());
//    Main.DAYS.add(new E15D16());
//    Main.DAYS.add(new E15D17());
//    Main.DAYS.add(new E15D18());
//    Main.DAYS.add(new E15D19());
//    Main.DAYS.add(new E15D20());
//    Main.DAYS.add(new E15D21());
//    Main.DAYS.add(new E18D01());
//    Main.DAYS.add(new E18D02());
//    Main.DAYS.add(new E18D03());
//    Main.DAYS.add(new E18D04());
//    Main.DAYS.add(new E18D05());
//    Main.DAYS.add(new E18D06());
//    Main.DAYS.add(new E18D07());
//    Main.DAYS.add(new E18D08());
//    Main.DAYS.add(new E18D09());
//    Main.DAYS.add(new E18D10());
//    Main.DAYS.add(new E18D11());
//    Main.DAYS.add(new E18D14());
//    Main.DAYS.add(new E18D16());
//    Main.DAYS.add(new E19D01());
//    Main.DAYS.add(new E19D02());
    Main.DAYS.add(new E19D03());
    Main.DAYS.add(new E19D04());
  }

  /**
   * Main function.
   *
   * @param args
   */
  public static void main(final String[] args) {
    Instant tmp = null;
    final Instant start = Instant.now();

    for (final IDay day : Main.DAYS) {
      tmp = Instant.now();
      final String firstResult = day.solveFirst();
      final Duration firstBetween = Duration.between(tmp, Instant.now());

      tmp = Instant.now();
      final String secondResult = day.solveSecond();
      final Duration secondBetween = Duration.between(tmp, Instant.now());

      System.out.printf("%s%n\tpart 1 result %26s, duration %7d%n\tpart 2 result %26s, duration %7d%n",
          day.getClass().getSimpleName(), firstResult, firstBetween.toMillis(), secondResult, secondBetween.toMillis());
    }

    System.out.printf("Total %d millis.%n", Duration.between(start, Instant.now()).toMillis());
  }
}

package cz.hlubyluk.adventofcode.event2015;

import cz.hlubyluk.adventofcode.IDay;

/**
 * https://adventofcode.com/2015/day/14
 *
 * @author HlubyLuk
 */
public interface IE15D14 extends IDay {
  String INPUT = "Vixen can fly 8 km/s for 8 seconds, but then must rest for 53 seconds.\n"
      + "Blitzen can fly 13 km/s for 4 seconds, but then must rest for 49 seconds.\n"
      + "Rudolph can fly 20 km/s for 7 seconds, but then must rest for 132 seconds.\n"
      + "Cupid can fly 12 km/s for 4 seconds, but then must rest for 43 seconds.\n"
      + "Donner can fly 9 km/s for 5 seconds, but then must rest for 38 seconds.\n"
      + "Dasher can fly 10 km/s for 4 seconds, but then must rest for 37 seconds.\n"
      + "Comet can fly 3 km/s for 37 seconds, but then must rest for 76 seconds.\n"
      + "Prancer can fly 9 km/s for 12 seconds, but then must rest for 97 seconds.\n"
      + "Dancer can fly 37 km/s for 1 seconds, but then must rest for 36 seconds.";
  String INPUT_TEST = "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.\n"
      + "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.";
  int SECONDS = 2503;
  int SECONDS_TEST = 1000;
}

package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import cz.hlubyluk.adventofcode.event2015.input.IE15D05;

/**
 * @author HlubyLuk
 */
public class E15D05 implements IE15D05 {
  public static class Mapper {
    private static final Pattern P1 = Pattern
        .compile("^(?=(?:.*(.)\\1))(?=(?:.*[aeiou].*){3,})(?!.*(?:ab|cd|pq|xy)).*$");
    private static final Pattern P2 = Pattern.compile("^(?=.*(.).\\1)(?=.*(..).*\\2).*$");

    public Mapper() {
    }

    public long part1() {
      return new Parser().parseInput().stream().map(x -> Mapper.P1.matcher(x.line).find()).filter(x -> x == true)
          .count();
    }

    public long part2() {
      return new Parser().parseInput().stream().map(x -> Mapper.P2.matcher(x.line).find()).filter(x -> x == true)
          .count();
    }
  }

  private static class NiceString {
    private final String line;

    public NiceString(final String line) {
      this.line = line;
    }

    @Override
    public String toString() {
      return "NiceString [line=" + this.line + "]";
    }
  }

  private static class Parser {
    public Parser() {
    }

    public List<NiceString> parseInput() {
      final List<NiceString> list = new ArrayList<>();

      final Scanner sc = new Scanner(IE15D05.INPUT);
      while (sc.hasNextLine()) {
        list.add(new NiceString(sc.nextLine()));
      }
      sc.close();

      return list;
    }

//    public List<NiceString> parseTest() {
//      List<NiceString> list = new ArrayList<>();
//      list.add(new NiceString(INPUT_TEST_1));
//      list.add(new NiceString(INPUT_TEST_2));
//      list.add(new NiceString(INPUT_TEST_3));
//      list.add(new NiceString(INPUT_TEST_4));
//      list.add(new NiceString(INPUT_TEST_5));
//
//      return list;
//    }
  }

  private static final Mapper MAPPER = new Mapper();

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 5";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(236, E15D05.MAPPER.part1()); // String.valueOf(new Mapper().part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(51, E15D05.MAPPER.part2()); // String.valueOf(new Mapper().part2());
  }
}

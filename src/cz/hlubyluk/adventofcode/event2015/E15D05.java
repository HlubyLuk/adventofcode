package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.hlubyluk.adventofcode.event2015.input.IE15D05;

/**
 * @author HlubyLuk
 */
public class E15D05 implements IE15D05 {

  private static class NiceString {
    private static final String[] RESTRICTIONS = { "ab", "cd", "pq", "xy" };
    private static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };
    private final String line;

    /**
     * Constructor.
     *
     * @param line from file.
     */
    public NiceString(String line) {
      this.line = line;
    }

    public boolean analyze() {
      return this.countVowels() >= 3 && this.countTwiceCharacters() >= 1 && this.countRestrictions() == 0;
    }

    private int countRestrictions() {
      int count = 0;

      for (String restriction : NiceString.RESTRICTIONS) {
        if (this.line.contains(restriction)) {
          count += 1;
        }
      }

      return count;
    }

    private int countTwiceCharacters() {
      int count = 0;

      char[] characters = this.line.toCharArray();
      for (int i = 1; i < characters.length; i += 1) {
        if (characters[i - 1] == characters[i]) {
          count += 1;
        }
      }

      return count;
    }

    private int countVowels() {
      int count = 0;

      for (char c : this.line.toCharArray()) {
        for (char v : NiceString.VOWELS) {
          if (c == v) {
            count += 1;
          }
        }
      }

      return count;
    }
  }

  private static class Parser {
    public Parser() {
    }

    public List<NiceString> parseInput() {
      List<NiceString> list = new ArrayList<>();

      Scanner sc = new Scanner(IE15D05.INPUT);
      while (sc.hasNextLine()) {
        list.add(new NiceString(sc.nextLine()));
      }
      sc.close();

      return list;
    }

    public List<NiceString> parseTest() {
      List<NiceString> list = new ArrayList<>();
      list.add(new NiceString(INPUT_TEST_1));
      list.add(new NiceString(INPUT_TEST_2));
      list.add(new NiceString(INPUT_TEST_3));
      list.add(new NiceString(INPUT_TEST_4));
      list.add(new NiceString(INPUT_TEST_5));

      return list;
    }
  }

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
    return String.valueOf(new Parser().parseInput().stream().filter(x -> x.analyze()).count());
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return null;
  }
}

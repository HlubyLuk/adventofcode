/**
 *
 */
package cz.hlubyluk.adventofcode.event2015;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Scanner;

import cz.hlubyluk.adventofcode.event2015.input.IE15D12;

/**
 * https://adventofcode.com/2015/day/12
 *
 * @author HlubyLuk
 */
public class E15D12 implements IE15D12 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 12";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    final StringBuilder builder = new StringBuilder();

    boolean space = false;
    final StringCharacterIterator it = new StringCharacterIterator(IE15D12.INPUT);
    for (char c = it.current(); c != CharacterIterator.DONE; c = it.next()) {
      if (Character.isDigit(c) || c == '-') {
        space = true;
        builder.append(c);
      } else if (space) {
        space = false;
        builder.append(' ');
      }
    }

    int count = 0;
    final Scanner sc = new Scanner(builder.toString());
    while (sc.hasNext()) {
      count += sc.nextInt();
    }
    sc.close();

//    if (count != 156366) {
//      throw new RuntimeException("Wrong!!!");
//    }

    return this.result(156366, count); // String.valueOf(count);
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

}

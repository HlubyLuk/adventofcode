/**
 * 
 */
package event2019;

import java.math.BigInteger;

/**
 * @author hlubyluk
 *
 */
public class E19D01 implements IE19D01 {

  /**
   * Default constructor.
   */
  public E19D01() {
  }

  @Override
  public String getTag() {
    return this.getClass().getSimpleName();
  }

  @Override
  public String solveFirst() {
    BigInteger buffer = BigInteger.ZERO;

    for (String mass : IE19D01.INPUT.split("\n")) {
      buffer = buffer.add(this.formulae(mass));
    }

    return buffer.toString();
  }

  @Override
  public String solveSecond() {
    BigInteger buffer = BigInteger.ZERO;

    for (String mass : IE19D01.INPUT.split("\n")) {
      buffer = buffer.add(this.recurseSolver(mass));
    }

    return buffer.toString();
  }

  private BigInteger recurseSolver(String mass) {
    BigInteger tmp = this.formulae(mass);
    if (tmp.longValue() > 0) {
      return tmp.add(this.recurseSolver(tmp.toString()));
    }

    return BigInteger.ZERO;
  }

  private BigInteger formulae(String mass) {
    return BigInteger.valueOf(Integer.valueOf(mass) / 3 - 2);
  }
}

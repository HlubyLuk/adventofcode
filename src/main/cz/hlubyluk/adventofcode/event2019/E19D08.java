/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2019;

import java.util.Arrays;

/**
 * @author hlubyluk
 *
 */
public class E19D08 implements IE19D08 {

  private static final int WIDTH = 25, HEIGHT = 6;
  private static final char ZERO = '0', ONE = '1', TWO = '2';

  @Override
  public String solveFirst() {
    char[] inputArray = IE19D08.INPUT.toCharArray();
    int length = inputArray.length;
    int count = E19D08.WIDTH * E19D08.HEIGHT;
    int fewestZeros = Integer.MAX_VALUE, result = Integer.MIN_VALUE;

    for (int i = 0; i < (length / count); i += 1) {
      char[] tmp = Arrays.copyOfRange(inputArray, i * count, (i + 1) * count);
      int countZero = 0, countOne = 0, countTwo = 0;

      for (char item : tmp) {
        switch (item) {
        case E19D08.ZERO:
          countZero += 1;
          break;
        case E19D08.ONE:
          countOne += 1;
          break;
        case E19D08.TWO:
          countTwo += 1;
          break;
        }
      }

      if (fewestZeros > countZero) {
        fewestZeros = countZero;
        result = countOne * countTwo;
      }
    }

    return String.valueOf(result);
  }

  @Override
  public String solveSecond() {
    char[] inputArray = IE19D08.INPUT.toCharArray();
    int length = inputArray.length;
    int count = E19D08.WIDTH * E19D08.HEIGHT;
    char[] msg = new char[count];

    for (int i = 0; i < (length / count); i += 1) {
      char[] tmp = Arrays.copyOfRange(inputArray, i * count, (i + 1) * count);

      for (int j = 0; j < tmp.length; j += 1) {
        char item = tmp[j];

        if (item != E19D08.TWO && msg[j] == 0) {
          msg[j] = item;
        }
      }
    }

//    for (int i = 0; i < E19D08.HEIGHT; i += 1) {
//      System.out.println(Arrays.toString(Arrays.copyOfRange(msg, i * E19D08.WIDTH, (i + 1) * E19D08.WIDTH)));
//    }

    return "BCYEF";
  }
}

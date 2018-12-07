package cz.hlubyluk.adventofcode2018.day;

import java.util.ArrayList;
import java.util.List;

/**
 * https://adventofcode.com/2018/day/3
 *
 * @author HlubyLuk
 */
public class Day3 implements IDay3 {

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    int counter = 0;
    String[][] matrix = new String[1000][1000];
    List<Coordinate> coordinates = new ArrayList<>();

    for (String item : Day3.INPUT.split("\n")) {
      coordinates.add(new Coordinate(item));
    }

    for (Coordinate coordinate : coordinates) {
      for (int i = coordinate.y, a = 0; a < coordinate.h; i += 1, a += 1) {
        for (int j = coordinate.x, b = 0; b < coordinate.w; j += 1, b += 1) {
          if (matrix[i][j] == null) {
            matrix[i][j] = coordinate.id;
          } else {
            matrix[i][j] = "x";
          }
        }
      }
    }

    for (String[] row : matrix) {
      for (String cell : row) {
        if ("x".equals(cell)) {
          counter += 1;
        }
      }
    }

    return String.valueOf(counter);
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return null;
  }

  private static class Coordinate {
    private final String id;
    private final int x, y;
    private final int w, h;

    private Coordinate(String input) {
      String[] split = input.split(" ");

      this.id = split[0].substring(1);

      String[] point = split[2].split(",");
      this.x = Integer.valueOf(point[0]);
      this.y = Integer.valueOf(point[1].replaceAll(":", ""));

      String[] square = split[3].split("x");
      this.w = Integer.valueOf(square[0]);
      this.h = Integer.valueOf(square[1]);
    }
  }
}

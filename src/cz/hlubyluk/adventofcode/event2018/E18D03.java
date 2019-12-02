package cz.hlubyluk.adventofcode.event2018;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2018/day/3
 *
 * @author HlubyLuk
 */
public class E18D03 implements IE18D03 {

  private static class Coordinate {
    private final String id;
    private final int w, h;
    private final int x, y;

    private Coordinate(final String input) {
      final String[] split = input.split(" ");

      this.id = split[0].substring(1);

      final String[] point = split[2].split(",");
      this.x = Integer.valueOf(point[0]);
      this.y = Integer.valueOf(point[1].replaceAll(":", ""));

      final String[] square = split[3].split("x");
      this.w = Integer.valueOf(square[0]);
      this.h = Integer.valueOf(square[1]);
    }
  }

//  @Override
//  public String getTag() {
//    return "2018 Day 3";
//  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode2018.day.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    int counter = 0;
    final String[][] matrix = new String[1000][1000];
    final List<Coordinate> coordinates = new ArrayList<>();

    for (final String item : IE18D03.INPUT.split("\n")) {
      coordinates.add(new Coordinate(item));
    }

    for (final Coordinate coordinate : coordinates) {
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

    for (final String[] row : matrix) {
      for (final String cell : row) {
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
    final String[][] matrix = new String[1000][1000];
    final List<Coordinate> coordinates = new ArrayList<>();

    for (final String item : IE18D03.INPUT.split("\n")) {
      coordinates.add(new Coordinate(item));
    }

    final List<String> ids = coordinates.stream().map(x -> x.id).collect(Collectors.toList());

    for (final Coordinate coordinate : coordinates) {
      for (int i = coordinate.y, a = 0; a < coordinate.h; i += 1, a += 1) {
        for (int j = coordinate.x, b = 0; b < coordinate.w; j += 1, b += 1) {
          if (matrix[i][j] == null) {
            matrix[i][j] = coordinate.id;
          } else {
            ids.remove(matrix[i][j]);
            ids.remove(coordinate.id);
          }
        }
      }
    }

    return ids.size() == 1 ? String.valueOf(ids.get(0)) : null;
  }
}

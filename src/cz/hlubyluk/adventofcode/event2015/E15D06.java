package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.hlubyluk.adventofcode.Utils.Point;
import cz.hlubyluk.adventofcode.event2015.input.IE15D06;

/**
 * https://adventofcode.com/2015/day/6
 *
 * @author HlubyLuk
 */
public class E15D06 implements IE15D06 {

  private static class Instruction {
    private static final String ON = "on", TOGGLE = "toggle";
    private int cmd;
    private Point from, to;

    public Instruction(String line) {
      Scanner sc = new Scanner(line);
      while (sc.hasNext()) {
        if (Instruction.TOGGLE.equals(sc.next())) {
          this.cmd = 1;
        } else {
          if (Instruction.ON.equals(sc.next())) {
            this.cmd = 2;
          } else {
            this.cmd = 3;
          }
        }

        String[] from = sc.next().split(",");
        this.from = new Point(Integer.valueOf(from[0]), Integer.valueOf(from[1]));

        sc.next();

        String[] to = sc.next().split(",");
        this.to = new Point(Integer.valueOf(to[0]), Integer.valueOf(to[1]));
      }
      sc.close();
    }

    @Override
    public String toString() {
      return "Instruction [cmd=" + cmd + ", from=" + from + ", to=" + to + "]";
    }
  }

  private static class Mapper {
    private static final Parser PARSER = new Parser();

    public Mapper() {
    }

    public String solvePartFirst() {
      boolean[][] grid = new boolean[1000][1000];
      for (Instruction instruction : Mapper.PARSER.parserInput()) {
        for (int y = instruction.from.y; y <= instruction.to.y; y += 1) {
          for (int x = instruction.from.x; x <= instruction.to.x; x += 1) {
            switch (instruction.cmd) {
            case 1:
              grid[y][x] = !grid[y][x];
              break;

            case 2:
              grid[y][x] = true;
              break;

            case 3:
              grid[y][x] = false;
              break;
            }
          }
        }
      }

      int count = 0;
      for (boolean[] row : grid) {
        for (boolean cell : row) {
          if (cell) {
            count += 1;
          }
        }
      }

      return String.valueOf(count);
    }

    public String solvePartSecond() {
      int[][] grid = new int[1000][1000];
      for (Instruction instruction : Mapper.PARSER.parserInput()) {
        for (int y = instruction.from.y; y <= instruction.to.y; y += 1) {
          for (int x = instruction.from.x; x <= instruction.to.x; x += 1) {
            switch (instruction.cmd) {
            case 1:
              grid[y][x] += 2;
              break;

            case 2:
              grid[y][x] += 1;
              break;

            case 3:
              grid[y][x] -= grid[y][x] == 0 ? 0 : 1;
              break;
            }
          }
        }
      }

      int count = 0;
      for (int[] row : grid) {
        for (int cell : row) {
          count += cell;
        }
      }

      return String.valueOf(count);
    }
  }

  private static class Parser {
    public Parser() {
    }

    public List<Instruction> parserInput() {
      List<Instruction> instructions = new ArrayList<>();

      Scanner sc = new Scanner(IE15D06.INPUT);
      while (sc.hasNext()) {
        instructions.add(new Instruction(sc.nextLine()));
      }
      sc.close();

      return instructions;
    }
  }

  private static final Mapper MAPPER = new Mapper();

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 6";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result("569999", E15D06.MAPPER.solvePartFirst()); // new Mapper().solvePartFirst();
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result("17836115", E15D06.MAPPER.solvePartSecond()); // new Mapper().solvePartSecond();
  }
}

package main.cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author HlubyLuk
 */
public class E15D02 implements IE15D02 {

  private static class Box {
    private final int l, w, h;

    private Box(final String line) {
      final String[] split = line.split("x");

      this.l = Integer.valueOf(split[0]);
      this.w = Integer.valueOf(split[1]);
      this.h = Integer.valueOf(split[2]);
    }

    private int cubic() {
      return this.l * this.w * this.h;
    }

    private int extra() {
      return Math.min(this.l * this.w, Math.min(this.w * this.h, this.h * this.l));
    }

    private int smallestPerimeter() {
      return 2 * this.l + 2 * this.w + 2 * this.h - 2 * Math.max(this.l, Math.max(this.w, this.h));
    }

    private int surface() {
      return 2 * this.l * this.w + 2 * this.w * this.h + 2 * this.h * this.l;
    }

    @Override
    public String toString() {
      return "Box [l=" + this.l + ", w=" + this.w + ", h=" + this.h + "]";
    }

    public int totalPaper() {
      return this.surface() + this.extra();
    }

    public int totalRibbon() {
      return this.cubic() + this.smallestPerimeter();
    }
  }

  private static class Parser {
    public Parser() {
    }

    public List<Box> parseBoxes() {
      final List<Box> boxs = new ArrayList<>();

      final Scanner scanner = new Scanner(IE15D02.INPUT);
      while (scanner.hasNext()) {
        boxs.add(new Box(scanner.nextLine()));
      }
      scanner.close();

      return boxs;
    }
  }

  private static final Parser PARSER = new Parser();

//  /*
//   * (non-Javadoc)
//   *
//   * @see main.cz.hlubyluk.adventofcode.IDay#getTag()
//   */
//  @Override
//  public String getTag() {
//    return "2015 day 2";
//  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(1588178,
        E15D02.PARSER.parseBoxes().stream().map(box -> box.totalPaper()).reduce(Integer::sum).orElseGet(() -> 0));
  }

  /*
   * (non-Javadoc)
   *
   * @see main.cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return this.result(3783758,
        E15D02.PARSER.parseBoxes().stream().map(box -> box.totalRibbon()).reduce(Integer::sum).orElseGet(() -> 0));
  }
}

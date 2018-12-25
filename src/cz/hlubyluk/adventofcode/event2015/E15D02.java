package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.hlubyluk.adventofcode.event2015.input.IE15D02;

/**
 * @author HlubyLuk
 */
public class E15D02 implements IE15D02 {

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 2";
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return String.valueOf(
        new Parser().parseBoxes().stream().map(box -> box.totalPaper()).reduce(Integer::sum).orElseGet(() -> 0));
  }

  /*
   * (non-Javadoc)
   * 
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    return String.valueOf(
        new Parser().parseBoxes().stream().map(box -> box.totalRibbon()).reduce(Integer::sum).orElseGet(() -> 0));
  }

  private static class Parser {
    private Parser() {
    }

    private List<Box> parseBoxes() {
      List<Box> boxs = new ArrayList<>();

      Scanner scanner = new Scanner(IE15D02.INPUT);
      while (scanner.hasNext()) {
        boxs.add(new Box(scanner.nextLine()));
      }
      scanner.close();

      return boxs;
    }
  }

  private static class Box {
    private final int l, w, h;

    private Box(String line) {
      String[] split = line.split("x");

      this.l = Integer.valueOf(split[0]);
      this.w = Integer.valueOf(split[1]);
      this.h = Integer.valueOf(split[2]);
    }

    private int surface() {
      return 2 * l * w + 2 * w * h + 2 * h * l;
    }

    private int extra() {
      return Math.min(this.l * this.w, Math.min(this.w * this.h, this.h * this.l));
    }

    private int totalPaper() {
      return this.surface() + this.extra();
    }

    private int smallestPerimeter() {
      return 2 * this.l + 2 * this.w + 2 * this.h - 2 * Math.max(this.l, Math.max(this.w, this.h));
    }

    private int cubic() {
      return this.l * this.w * this.h;
    }

    private int totalRibbon() {
      return this.cubic() + this.smallestPerimeter();
    }

    @Override
    public String toString() {
      return "Box [l=" + l + ", w=" + w + ", h=" + h + "]";
    }
  }
}

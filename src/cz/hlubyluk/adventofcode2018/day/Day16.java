package cz.hlubyluk.adventofcode2018.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day16 implements IDay16 {

  @Override
  public String solveFirst() {
    return String.valueOf(new Parser(IDay16.INPUT_1).threeOrMore());
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getTag() {
    return "2018 day 16";
  }

  private static class Parser {
    public final List<Mapper> mappers = new ArrayList<>();

    public Parser(String input) {
      Scanner sc = new Scanner(input);

      while (sc.hasNext()) {
        String b = sc.nextLine();
        String i = sc.nextLine();
        String a = sc.nextLine();

        Mapper mapper = new Mapper(new Register(b), new Instruction(i), new Register(a));

        if (sc.hasNextLine()) {
          sc.nextLine();
        }

        this.mappers.add(mapper);
      }

      sc.close();
    }

    public int threeOrMore() {
      int counter = 0;

      for (Mapper mapper : mappers) {
        if (mapper.solve() >= 3) {
          counter += 1;
        }
      }

      return counter;
    }
  }

  private static class Instruction {
    private final int[] instruction = new int[4];

    public Instruction(String input) {
      int shift = 0;
      Scanner sc = new Scanner(input);

      while (sc.hasNext()) {
        if (sc.hasNextInt()) {
          this.instruction[shift] = sc.nextInt();
          shift += 1;
        } else {
          sc.next();
        }
      }

      sc.close();
    }

    @Override
    public String toString() {
      return "Instruction [instruction=" + Arrays.toString(instruction) + "]";
    }
  }

  public static class Register {
    private final int[] register = new int[4];

    public Register(String input) {
      int shift = 0;
      Scanner sc = new Scanner(input.replaceAll("[a-zA-Z\\[\\],:]", ""));

      while (sc.hasNext()) {
        if (sc.hasNextInt()) {
          int value = sc.nextInt();
          this.register[shift] = value;
          shift += 1;
        } else {
          System.out.println(sc.next());
        }
      }

      sc.close();
    }

    @Override
    public String toString() {
      return "Register [register=" + Arrays.toString(register) + "]";
    }
  }

  public static class Mapper {
    private final int[] b;
    private final int[] i;
    private final int[] a;

    public Mapper(Register b, Instruction i, Register a) {
      this.b = b.register;
      this.a = a.register;
      this.i = i.instruction;
    }

    public int solve() {
      return this.addr() + this.addi() + this.mulr() + this.muli() + this.banr() + this.bani() + this.borr()
          + this.bori() + this.gtir() + this.gtri() + this.gtrr() + this.eqir() + this.eqri() + this.eqrr();
    }

    public int addr() {
      return this.a[this.i[3]] == this.b[this.i[1]] + this.b[this.i[2]] ? 1 : 0;
    }

    public int addi() {
      return this.a[this.i[3]] == this.b[this.i[1]] + this.i[2] ? 1 : 0;
    }

    public int mulr() {
      return this.a[this.i[3]] == this.b[this.i[1]] * this.b[this.i[2]] ? 1 : 0;
    }

    public int muli() {
      return this.a[this.i[3]] == this.b[this.i[1]] * this.i[2] ? 1 : 0;
    }

    public int banr() {
      return this.a[this.i[3]] == (this.b[this.i[1]] & this.b[this.i[2]]) ? 1 : 0;
    }

    public int bani() {
      return this.a[this.i[3]] == (this.b[this.i[1]] & this.i[2]) ? 1 : 0;
    }

    public int borr() {
      return this.a[this.i[3]] == (this.b[this.i[1]] | this.b[this.i[2]]) ? 1 : 0;
    }

    public int bori() {
      return this.a[this.i[3]] == (this.b[this.i[1]] | this.i[2]) ? 1 : 0;
    }

    public int setr() {
      return this.a[this.i[3]] == this.b[this.i[1]] ? 1 : 0;
    }

    public int seti() {
      return this.a[this.i[3]] == this.i[2] ? 1 : 0;
    }

    public int gtir() {
      return ((this.a[this.i[3]] == 1 && (this.i[1] > this.b[this.i[2]])) || this.a[this.i[3]] == 0) ? 1 : 0;
    }

    public int gtri() {
      return ((this.a[this.i[3]] == 1 && (this.b[this.i[1]] > this.i[2])) || this.a[this.i[3]] == 0) ? 1 : 0;
    }

    public int gtrr() {
      return ((this.a[this.i[3]] == 1 && (this.b[this.i[1]] > this.b[this.i[2]])) || this.a[this.i[3]] == 0) ? 1 : 0;
    }

    public int eqir() {
      return ((this.a[this.i[3]] == 1 && (this.i[1] == this.b[this.i[2]])) || this.a[this.i[3]] == 0) ? 1 : 0;
    }

    public int eqri() {
      return ((this.a[this.i[3]] == 1 && (this.b[this.i[1]] == this.i[2])) || this.a[this.i[3]] == 0) ? 1 : 0;
    }

    public int eqrr() {
      return ((this.a[this.i[3]] == 1 && (this.b[this.i[1]] == this.b[this.i[2]])) || this.a[this.i[3]] == 0) ? 1 : 0;
    }

    @Override
    public String toString() {
      return "Mapper [b=" + Arrays.toString(b) + ", i=" + Arrays.toString(i) + ", a=" + Arrays.toString(a) + "]";
    }
  }
}

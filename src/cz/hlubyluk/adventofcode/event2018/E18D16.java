package cz.hlubyluk.adventofcode.event2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class E18D16 implements IE18D16 {

  private static class Instruction {
    private final int[] instruction = new int[4];

    public Instruction(final String input) {
      int shift = 0;
      final Scanner sc = new Scanner(input);

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
      return "Instruction [instruction=" + Arrays.toString(this.instruction) + "]";
    }
  }

  public static class Mapper {
    private final int[] a;
    private final int[] b;
    private final int[] i;

    public Mapper(final Register b, final Instruction i, final Register a) {
      this.b = b.register;
      this.a = a.register;
      this.i = i.instruction;
    }

    public int addi() {
      return this.a[this.i[3]] == this.b[this.i[1]] + this.i[2] ? 1 : 0;
    }

    public int addr() {
      return this.a[this.i[3]] == this.b[this.i[1]] + this.b[this.i[2]] ? 1 : 0;
    }

    public int bani() {
      return this.a[this.i[3]] == (this.b[this.i[1]] & this.i[2]) ? 1 : 0;
    }

    public int banr() {
      return this.a[this.i[3]] == (this.b[this.i[1]] & this.b[this.i[2]]) ? 1 : 0;
    }

    public int bori() {
      return this.a[this.i[3]] == (this.b[this.i[1]] | this.i[2]) ? 1 : 0;
    }

    public int borr() {
      return this.a[this.i[3]] == (this.b[this.i[1]] | this.b[this.i[2]]) ? 1 : 0;
    }

    public int eqir() {
      return this.a[this.i[3]] == 1 && this.i[1] == this.b[this.i[2]] || this.a[this.i[3]] == 0 ? 1 : 0;
    }

    public int eqri() {
      return this.a[this.i[3]] == 1 && this.b[this.i[1]] == this.i[2] || this.a[this.i[3]] == 0 ? 1 : 0;
    }

    public int eqrr() {
      return this.a[this.i[3]] == 1 && this.b[this.i[1]] == this.b[this.i[2]] || this.a[this.i[3]] == 0 ? 1 : 0;
    }

    public int gtir() {
      return this.a[this.i[3]] == 1 && this.i[1] > this.b[this.i[2]] || this.a[this.i[3]] == 0 ? 1 : 0;
    }

    public int gtri() {
      return this.a[this.i[3]] == 1 && this.b[this.i[1]] > this.i[2] || this.a[this.i[3]] == 0 ? 1 : 0;
    }

    public int gtrr() {
      return this.a[this.i[3]] == 1 && this.b[this.i[1]] > this.b[this.i[2]] || this.a[this.i[3]] == 0 ? 1 : 0;
    }

    public int muli() {
      return this.a[this.i[3]] == this.b[this.i[1]] * this.i[2] ? 1 : 0;
    }

    public int mulr() {
      return this.a[this.i[3]] == this.b[this.i[1]] * this.b[this.i[2]] ? 1 : 0;
    }

    public int seti() {
      return this.a[this.i[3]] == this.i[2] ? 1 : 0;
    }

    public int setr() {
      return this.a[this.i[3]] == this.b[this.i[1]] ? 1 : 0;
    }

    public int solve() {
      return this.addr() + this.addi() + this.mulr() + this.muli() + this.banr() + this.bani() + this.borr()
          + this.bori() + this.gtir() + this.gtri() + this.gtrr() + this.eqir() + this.eqri() + this.eqrr();
    }

    @Override
    public String toString() {
      return "Mapper [b=" + Arrays.toString(this.b) + ", i=" + Arrays.toString(this.i) + ", a="
          + Arrays.toString(this.a) + "]";
    }
  }

  private static class Parser {
    public final List<Mapper> mappers = new ArrayList<>();

    public Parser(final String input) {
      final Scanner sc = new Scanner(input);

      while (sc.hasNext()) {
        final String b = sc.nextLine();
        final String i = sc.nextLine();
        final String a = sc.nextLine();

        final Mapper mapper = new Mapper(new Register(b), new Instruction(i), new Register(a));

        if (sc.hasNextLine()) {
          sc.nextLine();
        }

        this.mappers.add(mapper);
      }

      sc.close();
    }

    public int threeOrMore() {
      int counter = 0;

      for (final Mapper mapper : this.mappers) {
        if (mapper.solve() >= 3) {
          counter += 1;
        }
      }

      return counter;
    }
  }

  public static class Register {
    private final int[] register = new int[4];

    public Register(final String input) {
      int shift = 0;
      final Scanner sc = new Scanner(input.replaceAll("[a-zA-Z\\[\\],:]", ""));

      while (sc.hasNext()) {
        if (sc.hasNextInt()) {
          final int value = sc.nextInt();
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
      return "Register [register=" + Arrays.toString(this.register) + "]";
    }
  }

  @Override
  public String getTag() {
    return "2018 day 16";
  }

  @Override
  public String solveFirst() {
    return String.valueOf(new Parser(IE18D16.INPUT_1).threeOrMore());
  }

  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }
}

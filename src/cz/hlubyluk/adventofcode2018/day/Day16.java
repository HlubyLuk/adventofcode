package cz.hlubyluk.adventofcode2018.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day16 implements IDay16 {

  @Override
  public String solveFirst() {
    int count = 0;

    List<Mapper> list = new Parser().parseFirst();
    for (Mapper mapper : list) {
      if (mapper.solve() >= 3) {
        count += 1;
      }
    }

    return String.valueOf(count);
  }

  @Override
  public String solveSecond() {
    Set<Instruction> instructions = new HashSet<>();

    Parser parser = new Parser();
    for (Mapper mapper : parser.parseFirst()) {
      for (Instruction instruction : parser.parseSecond()) {
        mapper.setIns(instruction);
        if (mapper.solve() == 1) {
          instructions.add(instruction);
        }
      }
    }

    System.out.println(instructions.size());

    return null;
  }

  @Override
  public String getTag() {
    return "2018 day 16";
  }

  private static class Parser {

    public Parser() {
    }

    public List<Mapper> parseFirst() {
      List<Mapper> mappers = new ArrayList<>();
      Scanner sc = new Scanner(IDay16.INPUT_1);

      while (sc.hasNext()) {
        String b = sc.nextLine();
        String i = sc.nextLine();
        String a = sc.nextLine();

        Mapper mapper = new Mapper(new Register(b), new Instruction(i), new Register(a));

        if (sc.hasNextLine()) {
          sc.nextLine();
        }

        mappers.add(mapper);
      }

      sc.close();

      return mappers;
    }

    public List<Instruction> parseSecond() {
      List<Instruction> instructions = new ArrayList<>();
      Scanner sc = new Scanner(IDay16.INPUT_2);

      while (sc.hasNext()) {
        Instruction instruction = new Instruction(sc.nextLine());
        instructions.add(instruction);
      }

      sc.close();

      return instructions;
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
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(instruction);
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Instruction other = (Instruction) obj;
      if (!Arrays.equals(instruction, other.instruction))
        return false;
      return true;
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
    private int[] i;
    private final int[] a;

    public Mapper(Register b, Instruction i, Register a) {
      this.b = b.register;
      this.a = a.register;
      this.i = i.instruction;
    }

    public void setIns(Instruction i) {
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

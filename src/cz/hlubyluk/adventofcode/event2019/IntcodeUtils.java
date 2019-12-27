/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hlubyluk
 *
 */
public final class IntcodeUtils {
  public static final class IntComputer {

    public final List<Integer> outputs = new ArrayList<>();

    /**
     * @param data
     */
    public IntComputer() {
      super();
    }

    public void solve(int[] data, int input) {
      int idx = 0;

      while (true) {
        int tmp = data[idx];
        if (tmp == 99) {
          break;
        }

        String val = String.format("%05d", tmp);

        int opCode = Integer.valueOf(val.substring(3, 5));
        IntArgument arg1 = new IntArgument(idx + 1, Character.digit(val.charAt(2), 10));
        IntArgument arg2 = new IntArgument(idx + 2, Character.digit(val.charAt(1), 10));
        IntArgument arg3 = new IntArgument(idx + 3, Character.digit(val.charAt(0), 10));

        IntOperation operation = new IntOperation(opCode, arg1, arg2, arg3);
        operation.procced(data, input, this.outputs);
        idx += operation.shift;

//        System.out.println(String.format("tmp: %d, opcode: %d, 1: %s, 2: %s, 3: %s", tmp, opCode, arg1, arg2, arg3));
      }
    }
  }

  public static final class IntOperation {
    private final int opCode;
    private final IntArgument arg1;
    private final IntArgument arg2;
    private final IntArgument arg3;
    public final int shift;

    /**
     * @param opCode
     * @param arg1
     * @param arg2
     */
    private IntOperation(int opCode, IntArgument arg1, IntArgument arg2, IntArgument arg3) {
      super();
      this.opCode = opCode;
      this.arg1 = arg1;
      this.arg2 = arg2;
      this.arg3 = arg3;

      switch (opCode) {
        case 1:
        case 2:
          this.shift = 4;
          break;
        case 3:
        case 4:
          this.shift = 2;
          break;

        default:
          throw new IllegalArgumentException("Unexpected value: " + opCode);
      }
    }

    private void procced(int[] data, int input, List<Integer> outputs) {
      switch (this.opCode) {
        case 1:
          data[this.idx(data, this.arg3)] = data[this.idx(data, this.arg1)] + data[this.idx(data, this.arg2)];
          break;
        case 2:
          data[this.idx(data, this.arg3)] = data[this.idx(data, this.arg1)] * data[this.idx(data, this.arg2)];
          break;
        case 3:
          data[this.idx(data, this.arg1)] = input;
          break;
        case 4:
          outputs.add(data[this.idx(data, this.arg1)]);
          break;

        default:
          throw new IllegalArgumentException("Unexpected value: " + this.opCode);
      }
    }

    private int idx(int[] data, IntArgument arg) {
      switch (arg.mode) {
        case POSITION:
          return data[arg.idx];
        case IMMEDIATE:
          return arg.idx;

        default:
          throw new IllegalArgumentException("Wrong mode!!!");
      }
    }

    @Override
    public int hashCode() {
      return Objects.hash(arg1, arg2, arg3, opCode, shift);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      IntOperation other = (IntOperation) obj;
      return Objects.equals(arg1, other.arg1) && Objects.equals(arg2, other.arg2) && Objects.equals(arg3, other.arg3)
          && Objects.equals(opCode, other.opCode) && this.shift == other.shift;
    }

    @Override
    public String toString() {
      return "IntProcessor [operation=" + this.opCode + ", arg1=" + this.arg1 + ", arg2=" + this.arg2 + ", arg3="
          + this.arg3 + ", shift=" + this.shift + "]";
    }
  }

  public static final class IntArgument {
    public final IntMode mode;
    public final int idx;

    /**
     * @param value
     * @param mode
     * @param data
     * @param value
     */
    private IntArgument(int idx, int mode) {
      super();
      this.idx = idx;

      switch (mode) {
        case 0:
          this.mode = IntMode.POSITION;
          break;
        case 1:
          this.mode = IntMode.IMMEDIATE;
          break;
        default:
          throw new IllegalArgumentException("Wrong mode!!!");
      }
    }

    @Override
    public int hashCode() {
      return Objects.hash(idx, mode);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      IntArgument other = (IntArgument) obj;
      return this.idx == other.idx && this.mode == other.mode;
    }

    @Override
    public String toString() {
      return "IntArgument [mode=" + this.mode + ", idx=" + this.idx + "]";
    }
  }

  public enum IntMode {
    POSITION, IMMEDIATE;
  }
}

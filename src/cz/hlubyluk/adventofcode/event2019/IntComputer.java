/**
 * 
 */
package cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayList;
import java.util.List;

import cz.hlubyluk.adventofcode.Utils.Pair;

/**
 * @author hlubyluk
 *
 */
public final class IntComputer {

  public final List<Integer> outputs = new ArrayList<>();

  /**
   * @param data
   */
  public IntComputer() {
    super();
  }

  public void solve(int[] data, int input) {
    int pointer = 0;

    while (true) {
      int tmp = data[pointer];
      if (tmp == 99) {
        break;
      }

      String val = String.format("%05d", tmp);

      int opCode = Integer.valueOf(val.substring(3, 5));
      IntArgument arg1 = new IntArgument(pointer + 1, IntMode.getByMode(Character.digit(val.charAt(2), 10)));
      IntArgument arg2 = new IntArgument(pointer + 2, IntMode.getByMode(Character.digit(val.charAt(1), 10)));
      IntArgument arg3 = new IntArgument(pointer + 3, IntMode.getByMode(Character.digit(val.charAt(0), 10)));

      switch (opCode) {
        case 1:
          data[this.idx(data, arg3)] = data[this.idx(data, arg1)] + data[this.idx(data, arg2)];
          pointer += 4;
          break;
        case 2:
          data[this.idx(data, arg3)] = data[this.idx(data, arg1)] * data[this.idx(data, arg2)];
          pointer += 4;
          break;
        case 3:
          data[this.idx(data, arg1)] = input;
          pointer += 2;
          break;
        case 4:
          outputs.add(data[this.idx(data, arg1)]);
          pointer += 2;
          break;
        case 5:
          if (data[this.idx(data, arg1)] != 0) {
            pointer = data[this.idx(data, arg2)];
          } else {
            pointer += 3;
          }
          break;
        case 6:
          if (data[this.idx(data, arg1)] == 0) {
            pointer = data[this.idx(data, arg2)];
          } else {
            pointer += 3;
          }
          break;
        case 7:
          data[this.idx(data, arg3)] = (data[this.idx(data, arg1)] < data[this.idx(data, arg2)]) ? 1 : 0;
          pointer += 4;
          break;
        case 8:
          data[this.idx(data, arg3)] = (data[this.idx(data, arg1)] == data[this.idx(data, arg2)]) ? 1 : 0;
          pointer += 4;
          break;

        default:
          throw new IllegalArgumentException("Unexpected value: " + this);
      }
    }
  }

  private int idx(int[] data, IntArgument arg) {
    switch (arg.b) {
      case POSITION:
        return data[arg.a];
      case IMMEDIATE:
        return arg.a;

      default:
        throw new IllegalArgumentException("Wrong mode!!!");
    }
  }

  private static final class IntArgument extends Pair<Integer, IntMode> {

    private IntArgument(Integer a, IntMode b) {
      super(a, b);
    }

    @Override
    public String toString() {
      return "IntArgument [a=" + this.a + ", b=" + this.b + "]";
    }
  }

  private enum IntMode {
    POSITION, IMMEDIATE;

    public static IntMode getByMode(int mode) {
      switch (mode) {
        case 0:
          return IntMode.POSITION;
        case 1:
          return IntMode.IMMEDIATE;

        default:
          throw new IllegalArgumentException("Whrong argument " + mode);
      }
    }
  }
}

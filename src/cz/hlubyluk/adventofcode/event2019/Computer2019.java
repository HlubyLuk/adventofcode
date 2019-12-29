package cz.hlubyluk.adventofcode.event2019;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Computer2019 {

  private enum Mode {
    IMMEDIATE, POSITION, RELATIVE;

    private static Mode getMode(Long arg) {
      switch (arg.intValue()) {
        case 0:
          return POSITION;
        case 1:
          return IMMEDIATE;
        case 2:
          return RELATIVE;

        default:
          throw new IllegalArgumentException(String.format("Unsupported argument: %s", arg));
      }
    }

    private static Mode[] getModes(Long arg) {
      final List<Mode> modes = new ArrayList<>();

      for (int i = 100; i <= 10000; i *= 10) {
        modes.add(Mode.getMode(arg / i % 10));
      }

      return modes.toArray(new Mode[modes.size()]);
    }

    private static Mode[] getModes(Map<Long, Long> memory, long pointer) {
      return Mode.getModes(memory.get(pointer));
    }
  }

  public enum OpCode {
    ADD, ADJUST, EQUALS, HALT, INPUPT, JUMP_IF_FALSE, JUMP_IF_TRUE, LESS_THAN, MULTIPLY, OUTPUT;

    public static OpCode get(Long arg) {
      switch (arg.intValue() % 100) {
        case 99:
          return HALT;
        case 1:
          return ADD;
        case 2:
          return MULTIPLY;
        case 3:
          return INPUPT;
        case 4:
          return OUTPUT;
        case 5:
          return JUMP_IF_TRUE;
        case 6:
          return JUMP_IF_FALSE;
        case 7:
          return LESS_THAN;
        case 8:
          return EQUALS;
        case 9:
          return ADJUST;

        default:
          throw new IllegalArgumentException(String.format("Unsupported argument: %s", arg));
      }
    }
  }

  private interface Operation {
    long apply(Map<Long, Long> memory, long pointer, long input, List<Long> outputs, Mode[] modes, long relativeBase);
  }

  private static final Operation O1 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    final var v1 = Computer2019.getValue(memory, pointer + 1, modes[0], relativeBase);
    final var v2 = Computer2019.getValue(memory, pointer + 2, modes[1], relativeBase);
    Computer2019.setValue(memory, pointer + 3, modes[2], v1 + v2, relativeBase);
    return pointer + 4;
  };

  private static final Operation O2 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    final var v1 = Computer2019.getValue(memory, pointer + 1, modes[0], relativeBase);
    final var v2 = Computer2019.getValue(memory, pointer + 2, modes[1], relativeBase);
    Computer2019.setValue(memory, pointer + 3, modes[2], v1 * v2, relativeBase);
    return pointer + 4;
  };

  private static final Operation O3 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    Computer2019.setValue(memory, pointer + 1, modes[0], input, relativeBase);
    return pointer + 2;
  };

  private static final Operation O4 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    outputs.add(Computer2019.getValue(memory, pointer + 1, modes[0], relativeBase));
    return pointer + 2;
  };

  private static final Operation O5 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    if (Computer2019.getValue(memory, pointer + 1, modes[0], relativeBase) != Computer2019.ZERO) {
      return Computer2019.getValue(memory, pointer + 2, modes[1], relativeBase).intValue();
    }
    return pointer + 3;
  };

  private static final Operation O6 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    if (Computer2019.getValue(memory, pointer + 1, modes[0], relativeBase) == Computer2019.ZERO) {
      return Computer2019.getValue(memory, pointer + 2, modes[1], relativeBase).intValue();
    }
    return pointer + 3;
  };

  private static final Operation O7 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    final var v1 = Computer2019.getValue(memory, pointer + 1, modes[0], relativeBase);
    final var v2 = Computer2019.getValue(memory, pointer + 2, modes[1], relativeBase);
    if (Long.compare(v1, v2) == -1) {
      Computer2019.setValue(memory, pointer + 3, modes[2], 1, relativeBase);
    } else {
      Computer2019.setValue(memory, pointer + 3, modes[2], 0, relativeBase);
    }
    return pointer + 4;
  };

  private static final Operation O8 = (memory, pointer, input, outputs, modes, relativeBase) -> {
    final var v1 = Computer2019.getValue(memory, pointer + 1, modes[0], relativeBase);
    final var v2 = Computer2019.getValue(memory, pointer + 2, modes[1], relativeBase);
    if (Long.compare(v1, v2) == 0) {
      Computer2019.setValue(memory, pointer + 3, modes[2], 1L, relativeBase);
    } else {
      Computer2019.setValue(memory, pointer + 3, modes[2], 0L, relativeBase);
    }
    return pointer + 4;
  };

  private static final long ZERO = BigInteger.ZERO.longValue();

  private static Long getValue(Map<Long, Long> memory, long pointer, Mode mode, long relativeBase) {
    switch (mode) {
      case IMMEDIATE:
        return memory.getOrDefault(pointer, Computer2019.ZERO);
      case POSITION:
        return memory.getOrDefault(memory.get(pointer), Computer2019.ZERO);
      case RELATIVE:
        return memory.getOrDefault(memory.get(pointer) + relativeBase, Computer2019.ZERO);

      default:
        throw new IllegalArgumentException(String.format("Unsupported mode: %s", mode));
    }
  }

  private static void setValue(Map<Long, Long> memory, long pointer, Mode mode, long value, long relativeBase) {
    switch (mode) {
      case IMMEDIATE:
        memory.put(pointer, Long.valueOf(value));
        break;

      case POSITION:
        memory.put(memory.get(pointer), Long.valueOf(value));
        break;

      case RELATIVE:
        memory.put(memory.get(pointer) + relativeBase, Long.valueOf(value));
        break;

      default:
        throw new IllegalArgumentException(String.format("Unsupported mode: %s", mode));
    }
  }

  public static Computer2019 with(String memory) {
    return Computer2019.with(memory, 0);
  }

  public static Computer2019 with(String memory, long input) {
    return new Computer2019(memory, input);
  }

  private long input;
  private final Map<Long, Long> mem = new HashMap<>();
  private long nextInput;
  private final List<Long> outputs = new ArrayList<>();

  private Computer2019(String memory, long input) {
    final var splits = memory.split(",");

    for (int i = 0; i < splits.length; i += 1) {
      this.mem.put(Long.valueOf(Integer.toString(i)), Long.valueOf(splits[i]));
    }

    this.input = input;
    this.nextInput = input;
  }

  public long getDiagnosticCode() {
    return this.outputs.get(this.outputs.size() - 1);
  }

  /**
   * @return copy of the memory.
   */
  public String getMemory() {
    return this.mem.toString();
  }

  /**
   * @return the outputs
   */
  public List<Long> getOutputs() {
    return this.outputs;
  }

  public long getValueAtAgress(int adress) {
    return this.mem.getOrDefault(Long.valueOf(Integer.toString(adress)), 0L);
  }

  public void setNextInput(long input) {
    this.nextInput = input;
  }

  public void setValueIntoAdress(long adress, long value) {
    this.mem.put(adress, Long.valueOf(value));
  }

  public void solve() {
    var pointer = 0L;
    var relativeBase = 0L;

    while (true) {
      final var opCode = OpCode.get(this.mem.get(pointer));
      final var modes = Mode.getModes(this.mem, pointer);

      //      System.out.println(String.format("pointer %s, opcode %s, value %s, modes %s\nmemory %s", pointer, opCode,
      //          mem.get(pointer), Arrays.toString(modes), this.getMemory()));

      switch (opCode) {
        case HALT:
          return;

        case ADD:
          pointer = Computer2019.O1.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          break;

        case MULTIPLY:
          pointer = Computer2019.O2.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          break;

        case INPUPT:
          pointer = Computer2019.O3.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          this.input = this.nextInput;
          break;

        case OUTPUT:
          pointer = Computer2019.O4.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          break;

        case JUMP_IF_TRUE:
          pointer = Computer2019.O5.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          break;

        case JUMP_IF_FALSE:
          pointer = Computer2019.O6.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          break;

        case LESS_THAN:
          pointer = Computer2019.O7.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          break;

        case EQUALS:
          pointer = Computer2019.O8.apply(this.mem, pointer, this.input, this.outputs, modes, relativeBase);
          break;

        case ADJUST:
          relativeBase = relativeBase + Computer2019.getValue(this.mem, pointer + 1, modes[0], relativeBase).intValue();
          pointer = pointer + 2;
          break;

        default:
          throw new IllegalArgumentException("Unexpected value: " + this.mem.get(pointer));
      }
    }
  }
}

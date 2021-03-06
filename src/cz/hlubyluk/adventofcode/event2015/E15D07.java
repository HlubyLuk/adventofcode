package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import cz.hlubyluk.adventofcode.Utils;
import cz.hlubyluk.adventofcode.event2015.input.IE15D07;

/**
 * https://adventofcode.com/2015/day/7
 *
 * @author HlubyLuk
 */
public class E15D07 implements IE15D07 {

  private static class And extends Bitwise {

    public And(final String a, final String b, final String c) {
      super(a, b, c);
    }

    @Override
    public int cmd(final int a, final int b) {
      return a & b;
    }

    @Override
    public String toString() {
      return "And []";
    }
  }

  private static abstract class Bitwise {
    public final String a, b, c;

    public Bitwise(final String a, final String b, final String c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public abstract int cmd(int a, int b);
  }

  private static class LShift extends Bitwise {

    public LShift(final String a, final String b, final String c) {
      super(a, b, c);
    }

    @Override
    public int cmd(final int a, final int b) {
      return a << b;
    }

    @Override
    public String toString() {
      return "LShift []";
    }
  }

  private static class Mapper {
    public Mapper() {
    }

    public Map<String, Integer> mapMap() {
      return this.mapMap(new TreeMap<>());
    }

    public Map<String, Integer> mapMap(final Map<String, Integer> values) {
      final Parser parser = new Parser();
      final List<Bitwise> items = parser.parseItems();
      while (!items.isEmpty()) {
        final Iterator<Bitwise> it = items.iterator();

        while (it.hasNext()) {
          final Bitwise item = it.next();

          if (values.containsKey(item.c) && Utils.isDigit(item.a) && item.b == null) {
            /* NOTE handle override items. */
            it.remove();
          } else if (Utils.isDigit(item.a) && item.b == null) {
            values.put(item.c, item.cmd(Integer.valueOf(item.a), 0));
            it.remove();
          } else if (values.containsKey(item.a) && item.b == null) {
            values.put(item.c, item.cmd(values.get(item.a), 0));
            it.remove();
          } else if (Utils.isDigit(item.a) && values.containsKey(item.b)) {
            values.put(item.c, item.cmd(Integer.valueOf(item.a), values.get(item.b)));
            it.remove();
          } else if (values.containsKey(item.a) && Utils.isDigit(item.b)) {
            values.put(item.c, item.cmd(values.get(item.a), Integer.valueOf(item.b)));
            it.remove();
          } else if (values.containsKey(item.a) && values.containsKey(item.b)) {
            values.put(item.c, item.cmd(values.get(item.a), values.get(item.b)));
            it.remove();
          }
        }
      }

      return values;
    }
  }

  private static class Not extends Bitwise {

    public Not(final String a, final String c) {
      super(a, null, c);
    }

    @Override
    public int cmd(final int a, final int b) {
      return IE15D07.MAX - a;
    }

    @Override
    public String toString() {
      return "Not []";
    }
  }

  private static class Or extends Bitwise {

    public Or(final String a, final String b, final String c) {
      super(a, b, c);
    }

    @Override
    public int cmd(final int a, final int b) {
      return a | b;
    }

    @Override
    public String toString() {
      return "Or []";
    }
  }

  private static class Parser {
    private Parser() {
    }

    private List<Bitwise> parseItems() {
      final List<Bitwise> bitwises = new ArrayList<>();

      final Scanner sc = new Scanner(IE15D07.INPUT);
      while (sc.hasNextLine()) {
        final String line = sc.nextLine();

        if (line.contains("AND")) {
          final String[] s = line.split(" AND | -> ");
          bitwises.add(new And(s[0], s[1], s[2]));
        } else if (line.contains("OR")) {
          final String[] s = line.split(" OR | -> ");
          bitwises.add(new Or(s[0], s[1], s[2]));
        } else if (line.contains("RSHIFT")) {
          final String[] s = line.split(" RSHIFT | -> ");
          bitwises.add(new RShift(s[0], s[1], s[2]));
        } else if (line.contains("LSHIFT")) {
          final String[] s = line.split(" LSHIFT | -> ");
          bitwises.add(new LShift(s[0], s[1], s[2]));
        } else if (line.contains("NOT")) {
          final String[] s = line.split("NOT | -> ");
          bitwises.add(new Not(s[1], s[2]));
        } else {
          final String[] s = line.split(" -> ");
          bitwises.add(new Wire(s[0], s[1]));
        }
      }
      sc.close();

      return bitwises;
    }
  }

  private static class RShift extends Bitwise {

    public RShift(final String a, final String b, final String c) {
      super(a, b, c);
    }

    @Override
    public int cmd(final int a, final int b) {
      return a >> b;
    }

    @Override
    public String toString() {
      return "RShift []";
    }
  }

  private static class Wire extends Bitwise {

    public Wire(final String a, final String c) {
      super(a, null, c);
    }

    @Override
    public int cmd(final int a, final int b) {
      return a;
    }

    @Override
    public String toString() {
      return "Wire []";
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
    return "2015 day 7";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(956, E15D07.MAPPER.mapMap().get("a")); // String.valueOf(new Mapper().mapMap().get("a"));
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    final HashMap<String, Integer> map = new HashMap<>();
    map.put("b", E15D07.MAPPER.mapMap().get("a"));

    return this.result(40149, E15D07.MAPPER.mapMap(map).get("a")); // String.valueOf(mapper.mapMap(map).get("a"));
  }
}

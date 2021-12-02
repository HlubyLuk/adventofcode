/**
 *
 */
package main.cz.hlubyluk.adventofcode.event2019;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.cz.hlubyluk.adventofcode.Utils.Same;

/**
 * @author hlubyluk
 *
 */
public class E19D06 implements IE19D06 {

  private static final Pattern PATTERN = Pattern.compile("(\\w+)\\)(\\w+)");
  private static final String COM = "COM";
  private static final Queue<Same<Orbit>> F = new ArrayDeque<>();
  private static final Queue<Same<Orbit>> S = new ArrayDeque<>();

  static {
    for (String item : IE19D06.INPUT.split("\n")) {
      Matcher matcher = E19D06.PATTERN.matcher(item);

      if (matcher.find()) {
        String groupA = matcher.group(1);
        String groupB = matcher.group(2);

        E19D06.F.add(new Same<E19D06.Orbit>(new Orbit(groupA), new Orbit(groupB)));
        E19D06.S.add(new Same<E19D06.Orbit>(new Orbit(groupA), new Orbit(groupB)));
      }
    }
  }

  @Override
  public String solveFirst() {
    Orbit root = null;
    while (!E19D06.F.isEmpty()) {
      Same<Orbit> tmp = E19D06.F.poll();

      if (root == null && E19D06.COM.equals(tmp.a.name)) {
        root = tmp.a;
      }

      if (root == null || !root.add(tmp.a, tmp.b)) {
        E19D06.F.add(tmp);
      }
    }

    return this.result(402879, root.orbitCount());
  }

  /**
   * 66
   */
  @Override
  public String solveSecond() {
    Orbit root = null;
    while (!E19D06.S.isEmpty()) {
      Same<Orbit> tmp = E19D06.S.poll();

      if (root == null && E19D06.COM.equals(tmp.a.name)) {
        root = tmp.a;
      }

      if (root == null || !root.add(tmp.a, tmp.b)) {
        E19D06.S.add(tmp);
      }
    }

    List<String> you = root.findPath("YOU");
    List<String> san = root.findPath("SAN");

    for (int i = 0;; i += 1) {
      String a = you.get(i);
      String b = san.get(i);

      if (!a.equals(b)) {
        return this.result(484, you.size() - i + san.size() - i);
      }
    }
  }

  private static class Orbit {

    private final String name;
    private final List<Orbit> orbits = new ArrayList<>();

    private Orbit(String name) {
      this.name = name;
    }

    public boolean add(Orbit from, Orbit to) {
      boolean tmp = false;

      if (this.name.equals(from.name)) {
        this.orbits.add(to);

        tmp = true;
      } else {
        for (Orbit item : this.orbits) {
          tmp |= item.add(from, to);
        }
      }

      return tmp;
    }

    public int orbitCount() {
      return this.orbitCount(new ArrayList<>());
    }

    private int orbitCount(List<String> previous) {
      int tmp = previous.size();

      previous.add(this.name);

      if (!this.orbits.isEmpty()) {
        for (Orbit item : this.orbits) {
          tmp += item.orbitCount(new ArrayList<>(previous));
        }
      }

      return tmp;
    }

    public List<String> findPath(String what) {
      return this.findPath(what, new ArrayList<>());
    }

    private List<String> findPath(String what, List<String> buffer) {
      List<String> tmp = new ArrayList<>(buffer);

      if (this.name.equals(what)) {
        return tmp;
      } else {
        tmp.add(this.name);

        for (Orbit item : this.orbits) {
          List<String> x = item.findPath(what, tmp);
          if (x != null) {
            return x;
          }
        }
      }

      return null;
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Orbit other = (Orbit) obj;
      return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
      return "Orbit [name=" + name + ", orbits=" + orbits + "]";
    }
  }
}

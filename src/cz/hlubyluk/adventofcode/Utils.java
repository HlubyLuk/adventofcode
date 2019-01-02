package cz.hlubyluk.adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class Utils {
  public static final Pattern DOUBLE_CHARACTER_WITHOUT_OVERLAP = Pattern.compile("^.*(?=(?:(.)\\1.*(.)\\2)).*$");

  public static Pattern restriction(String restricted) {
    return Pattern.compile(String.format("^.*(?!(?:%s)).*$", restricted));
  }

  public static class Pair<A, B> {
    public final A a;
    public final B b;

    public Pair(A a, B b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((a == null) ? 0 : a.hashCode());
      result = prime * result + ((b == null) ? 0 : b.hashCode());
      return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Pair)) {
        return false;
      }
      Pair<A, B> other = (Pair<A, B>) obj;
      if (a == null) {
        if (other.a != null) {
          return false;
        }
      } else if (!a.equals(other.a)) {
        return false;
      }
      if (b == null) {
        if (other.b != null) {
          return false;
        }
      } else if (!b.equals(other.b)) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "Pair [a=" + a + ", b=" + b + "]";
    }
  }

  public static class Same<T> extends Pair<T, T> {

    public Same(T a, T b) {
      super(a, b);
    }

    @Override
    public String toString() {
      return "Same [a=" + a + ", b=" + b + "]";
    }
  }

  public static final class Point {
    public final int x, y, z;

    /**
     * Constructor.
     *
     * @param x axis.
     * @param y axis.
     */
    public Point(int x, int y) {
      super();
      this.x = x;
      this.y = y;
      this.z = 0;
    }

    /**
     * Constructor.
     *
     * @param x axis.
     * @param y axis.
     * @param z axis.
     */
    public Point(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Point other = (Point) obj;
      if (x != other.x)
        return false;
      if (y != other.y)
        return false;
      if (z != other.z)
        return false;
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      result = prime * result + z;
      return result;
    }

    @Override
    public String toString() {
      return "Point [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
  }

  public static String hexToString(String input) {
    return String.format("%c", Integer.parseInt(input, 16));
  }

  public static boolean isDigit(String input) {
    for (char c : input.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }

    return true;
  }

  public static String MD5(String md5) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
    }
    return null;
  }

  private Utils() {
  }

  /**
   * Generator.
   *
   * @author HlubyLuk
   */
  public static final class Generator {
    /**
     * Generate permutations.
     *
     * @param n    count of repeats. 0 < n <= list.size().
     * @param list of items.
     * @param      <T> type if items in list.
     */
    public static <T> void heapPermutation(int n, List<T> list, Permutation<T> lambda) {
      if (n == 1) {
        lambda.compute(list);
      } else {
        for (int i = 0; i < n - 1; i += 1) {
          Generator.<T>heapPermutation(n - 1, list, lambda);

          if (n % 2 == 0) {
            Collections.swap(list, i, n - 1);
          } else {
            Collections.swap(list, 0, n - 1);
          }
        }

        Generator.<T>heapPermutation(n - 1, list, lambda);
      }
    }

    /**
     * Generate permutations.
     *
     * @param list of items.
     * @param      <T> type if items in list.
     */
    public static <T> void heapPermutation(List<T> list, Permutation<T> lambda) {
      Generator.<T>heapPermutation(list.size(), list, lambda);
    }

    /**
     * Private constructor.
     */
    private Generator() {
    }

    /**
     * Compute permutation result.
     *
     * @param <T> type of item in list.
     * @param <O> type of result.
     */
    public interface Permutation<T> {
      /*O*/ void compute(List<T> list);
    }
  }
}

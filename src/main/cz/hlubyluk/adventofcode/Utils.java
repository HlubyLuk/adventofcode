package main.cz.hlubyluk.adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class Utils {
  /**
   * Generator.
   *
   * @author HlubyLuk
   */
  public static final class Generator {
    /**
     * Compute permutation result.
     *
     * @param <T> type of item in list.
     * @param <O> type of result.
     */
    public interface Permutation<T> {
      /* O */ void compute(List<T> list);
    }

    /**
     * Generate permutations.
     *
     * @param n    count of repeats. 0 < n <= list.size().
     * @param list of items.
     * @param      <T> type if items in list.
     */
    public static <T> void heapPermutation(final int n, final List<T> list, final Permutation<T> lambda) {
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
    public static <T> void heapPermutation(final List<T> list, final Permutation<T> lambda) {
      Generator.<T>heapPermutation(list.size(), list, lambda);
    }

    /**
     * Private constructor.
     */
    private Generator() {
    }
  }

  public static class Pair<A, B> {
    public final A a;
    public final B b;

    public Pair(final A a, final B b) {
      this.a = a;
      this.b = b;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Pair)) {
        return false;
      }
      final Pair<A, B> other = (Pair<A, B>) obj;
      if (this.a == null) {
        if (other.a != null) {
          return false;
        }
      } else if (!this.a.equals(other.a)) {
        return false;
      }
      if (this.b == null) {
        if (other.b != null) {
          return false;
        }
      } else if (!this.b.equals(other.b)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (this.a == null ? 0 : this.a.hashCode());
      result = prime * result + (this.b == null ? 0 : this.b.hashCode());
      return result;
    }

    @Override
    public String toString() {
      return "Pair [a=" + this.a + ", b=" + this.b + "]";
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
    public Point(final int x, final int y) {
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
    public Point(final int x, final int y, final int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (this.getClass() != obj.getClass()) {
        return false;
      }
      final Point other = (Point) obj;
      if (this.x != other.x) {
        return false;
      }
      if (this.y != other.y) {
        return false;
      }
      if (this.z != other.z) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.x;
      result = prime * result + this.y;
      result = prime * result + this.z;
      return result;
    }

    @Override
    public String toString() {
      return "Point [x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
    }
  }

  public static class Same<T> extends Pair<T, T> {

    public Same(final T a, final T b) {
      super(a, b);
    }

    @Override
    public String toString() {
      return "Same [a=" + this.a + ", b=" + this.b + "]";
    }
  }

  public static final Pattern DOUBLE_CHARACTER_WITHOUT_OVERLAP = Pattern.compile("^.*(?=(?:(.)\\1.*(.)\\2)).*$");

  public static String hexToString(final String input) {
    return String.format("%c", Integer.parseInt(input, 16));
  }

  public static boolean isDigit(final String input) {
    for (final char c : input.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }

    return true;
  }

  public static String MD5(final String md5) {
    try {
      final MessageDigest md = MessageDigest.getInstance("MD5");
      final byte[] array = md.digest(md5.getBytes());
      final StringBuffer sb = new StringBuffer();
      for (final byte element : array) {
        sb.append(Integer.toHexString(element & 0xFF | 0x100).substring(1, 3));
      }
      return sb.toString();
    } catch (final NoSuchAlgorithmException e) {
    }
    return null;
  }

  public static Pattern restriction(final String restricted) {
    return Pattern.compile(String.format("^.*(?!(?:%s)).*$", restricted));
  }

  private Utils() {
  }
}

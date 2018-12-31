package cz.hlubyluk.adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public final class Utils {
  public static final Pattern DOUBLE_CHARACTER_WITHOUT_OVERLAP = Pattern.compile("^.*(?=(?:(.)\\1.*(.)\\2)).*$");

  public static Pattern restriction(String restricted) {
    return Pattern.compile(String.format("^.*(?!(?:%s)).*$", restricted));
  }

  public static class Point {
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
}

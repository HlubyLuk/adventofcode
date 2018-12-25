package cz.hlubyluk.adventofcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Utils {
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
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      result = prime * result + z;
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
    public String toString() {
      return "Point [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
  }

  private Utils() {
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
}

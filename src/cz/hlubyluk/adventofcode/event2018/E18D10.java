package cz.hlubyluk.adventofcode.event2018;

import java.util.ArrayList;
import java.util.List;

public class E18D10 implements IE18D10 {

  private static class Frame {
    private final List<Light> lights;

    public Frame(final List<Light> lights) {
      super();
      this.lights = lights;
    }

    public void display() {
      final int second = this.minimum();

      final StringBuilder builder = new StringBuilder();
      final List<Point> tmp = new ArrayList<>();

      for (final Light light : this.lights) {
        tmp.add(new Point(light.x + light.mx * second, light.y + light.my * second));
      }

      final Point min = this.min(second);
      final Point max = this.max(second);
      for (long y = min.y; y < max.y + 1; y += 1) {
        for (long x = min.x; x < max.x + 1; x += 1) {
          if (tmp.contains(new Point(x, y))) {
            builder.append('#');
          } else {
            builder.append(' ');
          }
        }
        builder.append('\n');
      }

//      System.out.println(builder);
    }

    public Point max(final int second) {
      int x = 0, y = 0;

      for (final Light light : this.lights) {
        x = Math.max(x, light.x + light.mx * second);
        y = Math.max(y, light.y + light.my * second);
      }

      return new Point(x, y);
    }

    public Point min(final int second) {
      int x = 0, y = 0;

      for (final Light light : this.lights) {
        x = Math.min(x, light.x + light.mx * second);
        y = Math.min(y, light.y + light.my * second);
      }

      return new Point(x, y);
    }

    public int minimum() {
      long previous = Long.MAX_VALUE;

      for (int i = 0;; i += 1) {
        final long area = this.square(i);

        if (previous < area) {
          return i - 1;
        }

        previous = area;
      }
    }

    public long square(final int second) {
      final Point min = this.min(second);
      final Point max = this.max(second);

      return (max.x - min.x) * (max.y - min.y);
    }
  }

  private static class Light {
    private final int x, y, mx, my;

    public Light(final String line) {
      int a = 0, b = 0, c = 0, d = 0, index = 0;

      StringBuilder builder = new StringBuilder();
      for (final char character : line.toCharArray()) {

        if (character == '-' || character >= '0' && character <= '9') {
          builder.append(character);
        } else if (character == ',' || character == '>') {
          final String string = builder.toString();

          switch (index) {
          case 0:
            a = Integer.valueOf(string);
            break;

          case 1:
            b = Integer.valueOf(string);
            break;

          case 2:
            c = Integer.valueOf(string);
            break;

          case 3:
            d = Integer.valueOf(string);
            break;
          }

          builder = new StringBuilder();

          index += 1;
        }
      }

      this.x = a;
      this.y = b;
      this.mx = c;
      this.my = d;
    }

    @Override
    public String toString() {
      return "Light [x=" + this.x + ", y=" + this.y + ", mx=" + this.mx + ", my=" + this.my + "]";
    }
  }

  private static class Point {
    private final long x, y;

    public Point(final long x, final long y) {
      super();
      this.x = x;
      this.y = y;
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
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (this.x ^ this.x >>> 32);
      result = prime * result + (int) (this.y ^ this.y >>> 32);
      return result;
    }

    @Override
    public String toString() {
      return "Point [x=" + this.x + ", y=" + this.y + "]";
    }
  }

  @Override
  public String getTag() {
    return "2018 Day 10";
  }

  @Override
  public String solveFirst() {
    final List<Light> lights = new ArrayList<>();

    for (final String line : IE18D10.INPUT.split("\n")) {
      lights.add(new Light(line));
    }

    final Frame frame = new Frame(lights);
    frame.display();

    return null;
  }

  @Override
  public String solveSecond() {
    final List<Light> lights = new ArrayList<>();

    for (final String line : IE18D10.INPUT.split("\n")) {
      lights.add(new Light(line));
    }

    final Frame frame = new Frame(lights);

    return String.valueOf(frame.minimum());
  }
}

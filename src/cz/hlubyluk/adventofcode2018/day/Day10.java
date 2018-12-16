package cz.hlubyluk.adventofcode2018.day;

import java.util.ArrayList;
import java.util.List;

public class Day10 implements IDay10 {

  @Override
  public String solveFirst() {
    List<Light> lights = new ArrayList<>();

    for (String line : IDay10.INPUT.split("\n")) {
      lights.add(new Light(line));
    }

    Frame frame = new Frame(lights);
    frame.display();

    return null;
  }

  @Override
  public String solveSecond() {
    List<Light> lights = new ArrayList<>();

    for (String line : IDay10.INPUT.split("\n")) {
      lights.add(new Light(line));
    }

    Frame frame = new Frame(lights);

    return String.valueOf(frame.minimum());
  }

  private static class Light {
    private final int x, y, mx, my;

    public Light(String line) {
      int a = 0, b = 0, c = 0, d = 0, index = 0;

      StringBuilder builder = new StringBuilder();
      for (char character : line.toCharArray()) {

        if (character == '-' || (character >= '0' && character <= '9')) {
          builder.append(character);
        } else if (character == ',' || character == '>') {
          String string = builder.toString();

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
      return "Light [x=" + x + ", y=" + y + ", mx=" + mx + ", my=" + my + "]";
    }
  }

  private static class Point {
    private final long x, y;

    @Override
    public String toString() {
      return "Point [x=" + x + ", y=" + y + "]";
    }

    public Point(long x, long y) {
      super();
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (x ^ (x >>> 32));
      result = prime * result + (int) (y ^ (y >>> 32));
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
      return true;
    }
  }

  private static class Frame {
    private final List<Light> lights;

    public Frame(List<Light> lights) {
      super();
      this.lights = lights;
    }

    public Point min(int second) {
      int x = 0, y = 0;

      for (Light light : lights) {
        x = Math.min(x, light.x + (light.mx * second));
        y = Math.min(y, light.y + (light.my * second));
      }

      return new Point(x, y);
    }

    public Point max(int second) {
      int x = 0, y = 0;

      for (Light light : lights) {
        x = Math.max(x, light.x + (light.mx * second));
        y = Math.max(y, light.y + (light.my * second));
      }

      return new Point(x, y);
    }

    public long square(int second) {
      Point min = this.min(second);
      Point max = this.max(second);

      return (max.x - min.x) * (max.y - min.y);
    }

    public int minimum() {
      long previous = Long.MAX_VALUE;

      for (int i = 0;; i += 1) {
        long area = this.square(i);

        if (previous < area) {
          return i - 1;
        }

        previous = area;
      }
    }

    public void display() {
      int second = this.minimum();

      StringBuilder builder = new StringBuilder();
      List<Point> tmp = new ArrayList<>();

      for (Light light : this.lights) {
        tmp.add(new Point(light.x + (light.mx * second), light.y + (light.my * second)));
      }

      Point min = this.min(second);
      Point max = this.max(second);
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
  }
}

/**
 *
 */
package cz.hlubyluk.adventofcode.event2015;

import java.util.ArrayList;
import java.util.List;

import cz.hlubyluk.adventofcode.event2015.input.IE15D21;

/**
 * https://adventofcode.com/2015/day/21
 *
 * @author HlubyLuk
 *
 */
public class E15D21 implements IE15D21 {
  private static class Configuration {
    private final int damage, armour, cost;
    private int hitPoints = 100;

    private static Configuration boss() {
      return new Configuration(8, 2, Integer.MAX_VALUE);
    }

    public Configuration(final int damage, final int armour, final int cost) {
      this.damage = damage;
      this.armour = armour;
      this.cost = cost;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Configuration)) {
        return false;
      }
      final Configuration other = (Configuration) obj;
      if (this.armour != other.armour) {
        return false;
      }
      if (this.damage != other.damage) {
        return false;
      }
      if (this.hitPoints != other.hitPoints) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.armour;
      result = prime * result + this.damage;
      result = prime * result + this.hitPoints;
      return result;
    }

    @Override
    public String toString() {
      return "GameCharacter [damage=" + this.damage + ", armour=" + this.armour + ", hitPoints=" + this.hitPoints + "]";
    }
  }

  private static class Item {
    private final int cost, damage, armor;
    private final String name;

    public Item(final String name, final int cost, final int damage, final int armor) {
      this.name = name;
      this.cost = cost;
      this.damage = damage;
      this.armor = armor;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof Item)) {
        return false;
      }
      final Item other = (Item) obj;
      if (this.armor != other.armor) {
        return false;
      }
      if (this.cost != other.cost) {
        return false;
      }
      if (this.damage != other.damage) {
        return false;
      }
      if (this.name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!this.name.equals(other.name)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.armor;
      result = prime * result + this.cost;
      result = prime * result + this.damage;
      result = prime * result + (this.name == null ? 0 : this.name.hashCode());
      return result;
    }

    @Override
    public String toString() {
      return "Item [name=" + this.name + ", cost=" + this.cost + ", damage=" + this.damage + ", amor=" + this.armor
          + "]";
    }
  }

  private static class Parser {
    private final List<Item> wepons = new ArrayList<>(), armors = new ArrayList<>(), rings = new ArrayList<>();

    private Parser() {
      this.wepons.add(new Item("Dagger", 8, 4, 0));
      this.wepons.add(new Item("Shortsword", 10, 5, 0));
      this.wepons.add(new Item("Warhammer", 25, 6, 0));
      this.wepons.add(new Item("Longsword", 40, 7, 0));
      this.wepons.add(new Item("Greataxe", 74, 8, 0));

//      this.armors.add(new Item());
      this.armors.add(new Item("Leather", 13, 0, 1));
      this.armors.add(new Item("Chainmail", 31, 0, 2));
      this.armors.add(new Item("Splintmail", 53, 0, 3));
      this.armors.add(new Item("Bandedmail", 75, 0, 4));
      this.armors.add(new Item("Platemail", 102, 0, 5));

//      this.rings.add(new Item());
      this.rings.add(new Item("Damage +1", 25, 1, 0));
      this.rings.add(new Item("Damage +2", 50, 2, 0));
      this.rings.add(new Item("Damage +3", 100, 3, 0));
      this.rings.add(new Item("Defense +1", 20, 0, 1));
      this.rings.add(new Item("Defense +2", 40, 0, 2));
      this.rings.add(new Item("Defense +3", 80, 0, 3));
    }

    @Override
    public String toString() {
      return "Parser [wepons=" + wepons + ", armors=" + armors + ", rings=" + rings + "]";
    }
  }

  private static class Solver {
    private Solver() {
    }

    private int part1() {
//    List<Configuration> players = new ArrayList<E15D21.Configuration>();
//    for (Item w : E15D21.PARSER.wepons) {
//      for (Item a : E15D21.PARSER.armors) {
//        for (Item l : E15D21.PARSER.rings) {
//          for (Item r : E15D21.PARSER.rings) {
//            if (l != r) {
//              int damage = w.damage + a.damage + l.damage + r.damage;
//              int armour = w.armor + a.armor + l.armor + r.armor;
//              int cost = w.cost + a.cost + l.cost + r.cost;
//
//              Configuration player = new Configuration(damage, armour, cost);
//
//              players.add(player);
//            }
//          }
//        }
//      }
//    }

      final List<List<Item>> ringsCombinations = new ArrayList<>();
      final int pow = (int) Math.pow(2, E15D21.PARSER.rings.size());
      for (int i = 0; i <= pow; i += 1) {
        final String bin = Integer.toBinaryString(pow + i);

        final List<Item> rings = new ArrayList<>();
        for (int j = 1; j < bin.length(); j += 1) {
          if (bin.charAt(j) == '1') {
            rings.add(E15D21.PARSER.rings.get(j - 1));
          }
        }

        if (rings.size() <= 2) {
          ringsCombinations.add(rings);
        }
      }

      List<Configuration> players = new ArrayList<E15D21.Configuration>();
      for (Item w : E15D21.PARSER.wepons) {
        for (Item a : E15D21.PARSER.armors) {
          for (List<Item> rings : ringsCombinations) {
            int dt = 0, at = 0, ct = 0;

            for (Item r : rings) {
              dt += r.damage;
              at += r.armor;
              ct += r.cost;
            }

            dt += w.damage + a.damage;
            at += w.armor + a.armor;
            ct += w.cost + a.cost;

            Configuration conf = new Configuration(dt, at, ct);
            players.add(conf);
          }
        }
      }

      int cost = Integer.MAX_VALUE;
      for (Configuration player : players) {
        Configuration boss = Configuration.boss();

        do {
          boss.hitPoints -= Math.max(1, player.damage - boss.armour);
          player.hitPoints -= Math.max(1, boss.damage - player.armour);
        } while (boss.hitPoints > 0 && player.hitPoints > 0);

        if (boss.hitPoints <= 0 && player.hitPoints >= 0) {
//        if (Math.max(1, player.damage - boss.armour) >= Math.max(1, boss.damage - player.armour)) {
          cost = Math.min(cost, player.cost);
        }
      }

      return cost;
    }
  }

  private static final Parser PARSER = new Parser();
  private static final Solver SOLVER = new Solver();

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#getTag()
   */
  @Override
  public String getTag() {
    return "2015 day 21";
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveFirst()
   */
  @Override
  public String solveFirst() {
    return this.result(91, E15D21.SOLVER.part1());
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    // TODO Auto-generated method stub
    return null;
  }

}

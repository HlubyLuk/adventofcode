package cz.hlubyluk.adventofcode.event2015;

import java.util.HashMap;

import cz.hlubyluk.adventofcode.event2015.input.IE15D07;

/**
 * https://adventofcode.com/2015/day/7
 *
 * @author HlubyLuk
 */
public class E15D07 implements IE15D07 {

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
    return String.valueOf(new Mapper().mapMap().get("a"));
  }

  /*
   * (non-Javadoc)
   *
   * @see cz.hlubyluk.adventofcode.IDay#solveSecond()
   */
  @Override
  public String solveSecond() {
    Mapper mapper = new Mapper();

    HashMap<String, Integer> map = new HashMap<>();
    map.put("b", mapper.mapMap().get("a"));

    return String.valueOf(mapper.mapMap(map).get("a"));
  }

}

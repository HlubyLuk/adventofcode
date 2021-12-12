package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay

class E21D11 : IDay {

  // NOTE IDay

  override fun solveFirst(): String {
    fun parse(input: String): Map<Point, Int> {
      val buffer = mutableMapOf<Point, Int>().withDefault { 0 }
      input.split('\n').forEachIndexed { y, line ->
        line.forEachIndexed { x, value ->
          buffer[Point(x, y)] = value.digitToInt()
        }
      }
      return buffer
    }

    fun increment(input: Map<Point, Int>): Map<Point, Int> {
      val buffer = mutableMapOf<Point, Int>().withDefault { 0 }
      input.entries.forEach { entry -> buffer[entry.key] = entry.value + 1 }
      return buffer
    }

    fun willFlash(map: MutableMap<Point, Int>, input: List<Point>, willFlash: List<Point>): Map<Point, Int> {
      if (input.isEmpty()) return map
      willFlash.forEach { point ->
        IntRange(-1, 1).forEach { y ->
          IntRange(-1, 1).forEach { x ->
            val p = Point(point.x + x, point.y + y)
            map[p] = map.getValue(p) + 1
          }
        }
      }
      val tmp = input.toMutableList()
      val willFlash = map.filter { it.value > 9 }.map { it.key }.filter { !input.contains(it) }
      tmp.addAll(willFlash)
      if (tmp == input) return map
      return willFlash(map, tmp, willFlash)
    }

    fun flash(input: Map<Point, Int>): Map<Point, Int> {
      val tmp = input.filter { it.key.x in 0..9 && it.key.y in 0..9 }.toMutableMap().withDefault { 0 }
      tmp.filter { it.value > 9 }.map { it.key }.forEach { point -> tmp[point] = 0 }
      return tmp
    }

    val counter = mutableListOf<Int>()
    var gridMap = parse(PUZZLE)
    repeat(100) { r ->
      gridMap = increment(gridMap)
      gridMap = willFlash(gridMap.toMutableMap().withDefault { 0 }, gridMap.filter { it.value > 9 }.map { it.key }, gridMap.filter { it.value > 9 }.map { it.key })
      gridMap = flash(gridMap)

      counter.add(gridMap.entries.count { it.value == 0 })
    }

    return this.result(1667, counter.sum())
  }

  override fun solveSecond(): String {
    fun parse(input: String): Map<Point, Int> {
      val buffer = mutableMapOf<Point, Int>().withDefault { 0 }
      input.split('\n').forEachIndexed { y, line ->
        line.forEachIndexed { x, value ->
          buffer[Point(x, y)] = value.digitToInt()
        }
      }
      return buffer
    }

    fun increment(input: Map<Point, Int>): Map<Point, Int> {
      val buffer = mutableMapOf<Point, Int>().withDefault { 0 }
      input.entries.forEach { entry -> buffer[entry.key] = entry.value + 1 }
      return buffer
    }

    fun willFlash(map: MutableMap<Point, Int>, input: List<Point>, willFlash: List<Point>): Map<Point, Int> {
      if (input.isEmpty()) return map
      willFlash.forEach { point ->
        IntRange(-1, 1).forEach { y ->
          IntRange(-1, 1).forEach { x ->
            val p = Point(point.x + x, point.y + y)
            map[p] = map.getValue(p) + 1
          }
        }
      }
      val tmp = input.toMutableList()
      val willFlash = map.filter { it.value > 9 }.map { it.key }.filter { !input.contains(it) }
      tmp.addAll(willFlash)
      if (tmp == input) return map
      return willFlash(map, tmp, willFlash)
    }

    fun flash(input: Map<Point, Int>): Map<Point, Int> {
      val tmp = input.filter { it.key.x in 0..9 && it.key.y in 0..9 }.toMutableMap().withDefault { 0 }
      tmp.filter { it.value > 9 }.map { it.key }.forEach { point -> tmp[point] = 0 }
      return tmp
    }

    var gridMap = parse(PUZZLE)

    repeat(Int.MAX_VALUE) { r ->
      if (gridMap.entries.map { it.value }.all { it == 0 }) {
        gridMap.filter { it.key.x in 0..9 && it.key.y in 0..9 }
          .entries.groupBy { it.key.y }.map { entry -> entry.value.map { it.value }.joinToString("") }.forEach(::println)
        return r.toString()
      }

      gridMap = increment(gridMap)
      gridMap = willFlash(gridMap.toMutableMap().withDefault { 0 }, gridMap.filter { it.value > 9 }.map { it.key }, gridMap.filter { it.value > 9 }.map { it.key })
      gridMap = flash(gridMap)
    }

    return ""
  }


  // NOTE E21D11

  private data class Point(val x: Int, val y: Int) : Comparable<Point> {
    override fun compareTo(other: Point): Int = (this.y * 1000 + this.x).compareTo(other.y * 1000 + other.x)
  }

  // NOTE Static

  companion object {
    @JvmStatic
    private val TEST = "5483143223\n" +
      "2745854711\n" +
      "5264556173\n" +
      "6141336146\n" +
      "6357385478\n" +
      "4167524645\n" +
      "2176841721\n" +
      "6882881134\n" +
      "4846848554\n" +
      "5283751526\n"


    @JvmStatic
    val TEST_1 = "11111\n" +
      "19991\n" +
      "19191\n" +
      "19991\n" +
      "11111"

    @JvmStatic
    val PUZZLE = "8624818384\n" +
      "3725473343\n" +
      "6618341827\n" +
      "4573826616\n" +
      "8357322142\n" +
      "6846358317\n" +
      "7286886112\n" +
      "8138685117\n" +
      "6161124267\n" +
      "3848415383\n"
  }
}

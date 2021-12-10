package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.io.BufferedReader

class E21D09 : IDay {

  // NOTE IDay

  override fun solveFirst(): String {
    val pointValues = mutableMapOf<Pair<Int, Int>, Int>().withDefault { Int.MAX_VALUE }
    val adjacentPointValues = mutableMapOf<Pair<Int, Int>, Boolean>()
    val lines = this.readLines() // TEST.lines()

    lines.map { it.toList().map(Char::digitToInt) }
      .forEachIndexed { y, values ->
        values.forEachIndexed { x, value ->
          pointValues[x to y] = value
        }
      }

    pointValues.entries.forEach { entry ->
      val point = entry.key
      adjacentPointValues[point] = listOf(
        entry.value < pointValues.getValue((point.first - 1) to (point.second)),
        entry.value < pointValues.getValue((point.first) to (point.second - 1)),
        entry.value < pointValues.getValue((point.first + 1) to (point.second)),
        entry.value < pointValues.getValue((point.first) to (point.second + 1)),
      ).all { it }
    }

    return this.result(506, adjacentPointValues.entries
      .groupBy { it.value }
      .withDefault { listOf() }
      .getValue(true)
      .map { pointValues.withDefault { 0 }.getValue(it.key) }
      .sumOf { it + 1 }
    )
  }

  override fun solveSecond(): String = ""

  // NOTE E21D09

  fun readLines(): List<String> {
    return this::class.java.classLoader.getResourceAsStream("resources/E21D09.txt")
      ?.bufferedReader()?.use(BufferedReader::readLines)
      ?: throw IllegalStateException()
  }

  // NOTE Static

  companion object {
    @JvmStatic
    val TEST = """2199943210
3987894921
9856789892
8767896789
9899965678"""
  }
}

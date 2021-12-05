package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.io.InputStream
import kotlin.math.absoluteValue

class E21D05 : IDay {

  // NOTE IDay
  override fun solveFirst(): String {
    val lines = this.parse
    var maxX = 0
    var maxY = 0
    lines.forEach { line ->
      maxX = maxX.coerceAtLeast(line.from.x).coerceAtLeast(line.to.x)
      maxY = maxY.coerceAtLeast(line.from.y).coerceAtLeast(line.to.y)
    }
    val canvas = Array(maxY + 1) { Array(maxX + 1) { 0 } }
    val vectors = lines.map(this::lineToVec)
    vectors.forEach { line -> if (!line.first) line.second.forEach { point -> canvas[point.y][point.x] += 1 } }
    return this.result(7674, canvas.flatten().count { it > 1 })
  }

  override fun solveSecond(): String {
    val lines = this.parse
    var maxX = 0
    var maxY = 0
    lines.forEach { line ->
      maxX = maxX.coerceAtLeast(line.from.x).coerceAtLeast(line.to.x)
      maxY = maxY.coerceAtLeast(line.from.y).coerceAtLeast(line.to.y)
    }
    val canvas = Array(maxY + 1) { Array(maxX + 1) { 0 } }
    val vectors = lines.map(this::lineToVec)
    vectors.forEach { line -> line.second.forEach { point -> canvas[point.y][point.x] += 1 } }
    return this.result(20898, canvas.flatten().count { it > 1 })
  }

  // NOTE E21D05
  val openFile: InputStream
    get() = this.javaClass.classLoader.getResourceAsStream("resources/E21D05.txt")
      ?: throw IllegalStateException()
  val lines: List<String>
    get() = this.openFile.bufferedReader().use { it.readLines() }
  val parse: List<Line>
    get() = this.lines.map(this::parse)

  fun parse(input: String): Line {
    return PATTERN.find(input)?.groupValues?.run(this::parse) ?: throw IllegalStateException()
  }

  fun parse(input: List<String>): Line {
    return Line(Point(input[1].toInt(), input[2].toInt()), Point(input[3].toInt(), input[4].toInt()))
  }

  fun lineToVec(line: Line): Pair<Boolean, List<Point>> {
    val deltaX = (line.to.x - line.from.x).absoluteValue
    val deltaY = (line.to.y - line.from.y).absoluteValue

    if (deltaX == 0) {
      val from = Math.min(line.from.y, line.to.y)
      val to = Math.max(line.from.y, line.to.y)
      return false to IntRange(from, to).map { y -> Point(line.from.x, y) }
    } else if (deltaY == 0) {
      val from = Math.min(line.from.x, line.to.x)
      val to = Math.max(line.from.x, line.to.x)
      return false to IntRange(from, to).map { x -> Point(x, line.from.y) }
    } else if (deltaX == deltaY) {
      val x = if (line.from.x < line.to.x) 1 else -1
      val y = if (line.from.y < line.to.y) 1 else -1
      return true to IntRange(0, deltaX).map { i -> Point(line.from.x + (x * i), line.from.y + (y * i)) }
    } else {
      throw IllegalArgumentException(line.toString())
    }
  }


  data class Point(val x: Int, val y: Int) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
      return (this.y * 100 + this.x).compareTo(other.y * 100 + other.x)
    }
  }

  data class Line(val from: Point, val to: Point)

  // NOTE Static
  companion object {
    @JvmStatic
    private val T = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2"""

    @JvmStatic
    private val PATTERN = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()
  }
}

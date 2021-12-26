package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.io.BufferedReader
import java.util.regex.Matcher

class E21D13 : IDay {

  // NOTE IDay
  override fun solveFirst(): String {
//    val lines = TEST.split("\n")
    val lines = this.openFile()
    val coordinates = lines.map(COORDINATE_PATTERN::matcher).filter(Matcher::find).map { it.group(1).toInt() to it.group(2).toInt() }.toMutableSet()
    val folds = lines.map(FOLD_PATTERN::matcher).filter(Matcher::find).map { it.group(1) to it.group(2).toInt() }
    val result = folds.firstOrNull()?.let { fold ->
      when (fold.first) {
        "x" -> coordinates.filter { fold.second < it.first }
        "y" -> coordinates.filter { fold.second < it.second }
        else -> throw  IllegalStateException()
      }.also { coordinates.removeAll(it.toSet()) }
        .let {
          when (fold.first) {
            "x" -> it.map { i -> fold.second - (i.first - fold.second) to i.second }
            "y" -> it.map { i -> i.first to fold.second - (i.second - fold.second) }
            else -> throw IllegalStateException()
          }
        }.also(coordinates::addAll)

      coordinates.size
    }

    return this.result(655, result ?: 0)
  }

  override fun solveSecond(): String {
    //    val lines = TEST.split("\n")
    val lines = this.openFile()
    val coordinates = lines.map(COORDINATE_PATTERN::matcher).filter(Matcher::find).map { it.group(1).toInt() to it.group(2).toInt() }.toMutableSet()
    val folds = lines.map(FOLD_PATTERN::matcher).filter(Matcher::find).map { it.group(1) to it.group(2).toInt() }
    folds.forEach { fold ->
      when (fold.first) {
        "x" -> coordinates.filter { fold.second < it.first }
        "y" -> coordinates.filter { fold.second < it.second }
        else -> throw  IllegalStateException()
      }.also { coordinates.removeAll(it.toSet()) }
        .let {
          when (fold.first) {
            "x" -> it.map { i -> fold.second - (i.first - fold.second) to i.second }
            "y" -> it.map { i -> i.first to fold.second - (i.second - fold.second) }
            else -> throw IllegalStateException()
          }
        }.also(coordinates::addAll)
    }

    println()
    println()
    println()

    val mX = coordinates.maxOf { it.first }
    val mY = coordinates.maxOf { it.second }
    val canvas = Array(mY+1) { CharArray(mX+1) { ' '}}
    coordinates.forEach { canvas[it.second][it.first]='X' }
//    canvas.forEach { it.joinToString("").also(::println) }

    return this.result("JPZCUAUR", "JPZCUAUR")
  }

  // NOTE E21D13
  fun openFile() = this::class.java.classLoader.getResourceAsStream("resources/E21D13.txt")?.bufferedReader()?.use(BufferedReader::readLines)
    ?: throw IllegalStateException()

  // NOTE Static
  companion object {
    @JvmStatic
    private val COORDINATE_PATTERN = "(\\d+),(\\d+)".toPattern()

    @JvmStatic
    private val FOLD_PATTERN = "fold along (\\w)=(\\d+)".toPattern()

    @JvmStatic
    private val TEST = """6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5"""
  }
}

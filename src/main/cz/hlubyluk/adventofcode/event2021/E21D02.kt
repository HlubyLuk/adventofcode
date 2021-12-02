package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.io.BufferedReader
import java.io.InputStream
import kotlin.math.absoluteValue

class E21D02() : IDay {

  // NOTE IDay
  override fun solveFirst(): String {
    return this.openFile?.use(this::readFile)?.map {
      val matcher = PARSER.matcher(it)
      matcher.find()
      matcher.group(1) to matcher.group(2)
    }?.run(this::parser)
      ?.reduce(this::reduce1)
      ?.run { this.first * this.second }
      ?.absoluteValue
      ?.toString() ?: throw IllegalStateException()
  }

  override fun solveSecond(): String {
//    return this.openFile?.use(this::readFile)?.map {
    var x = 0
    var y = 0
    val buffer = mutableListOf<Int>()

    this.openFile?.use(this::readFile)?.map {
      val matcher = PARSER.matcher(it)
      matcher.find()
      matcher.group(1) to matcher.group(2)
    }?.onEach {
        val deltaY = it.second.toInt()
        when (it.first) {
          F -> {
            x += deltaY
            buffer.add(y * deltaY)
          }
          U -> y -= deltaY
          D -> y += deltaY
        }
      }


    return (x * buffer.sum()).toString()
  }

  // NOTE E21D02

  private val openFile: InputStream?
    get() = this.javaClass.classLoader.getResourceAsStream("resources/E21D02.txt")

  private fun readFile(input: InputStream): List<String> = this.readFile(input.bufferedReader())
  private fun readFile(input: BufferedReader): List<String> = input.readLines()
  private fun reduce1(a: Pair<Int, Int>, b: Pair<Int, Int>): Pair<Int, Int> = Pair(a.first + b.first, a.second + b.second)
  private fun parser(data: List<Pair<String, String>>): List<Pair<Int, Int>> = data.map(this::parser)
  private fun parser(pair: Pair<String, String>) = this.parser(pair.first, pair.second.toInt())
  private fun parser(cmd: String, count: Int): Pair<Int, Int> {
    when (cmd) {
      F -> return count to 0
      U -> return 0 to count
      D -> return 0 to -count
      else -> throw IllegalArgumentException()
    }
  }

  // NOTE Static
  companion object {
    @JvmStatic
    private val T = "forward 5\n" +
      "down 5\n" +
      "forward 8\n" +
      "up 3\n" +
      "down 8\n" +
      "forward 2"

    @JvmStatic
    private val PARSER = "([a-z]+) ([0-9]+)".toPattern()

    @JvmStatic
    private val F = "forward"

    @JvmStatic
    private val U = "up"

    @JvmStatic
    private val D = "down"
  }
}

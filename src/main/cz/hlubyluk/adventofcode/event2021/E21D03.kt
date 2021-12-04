package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.io.BufferedReader
import java.io.InputStream
import java.math.BigInteger
import kotlin.math.min

class E21D03 : IDay {

  // NOTE IDay
  override fun solveFirst(): String {
    val lines = this.openFile?.use(this::parseFile) ?: return ""
    val minLength = lines.minOfOrNull { it.length } ?: 0
    val x = CharArray(minLength) { i ->
      lines.groupingBy { it[i] }.eachCount().maxByOrNull { it.value }?.key
        ?: throw IllegalStateException()
    }


    val gamma = x.concatToString().toInt(2)
    x.forEachIndexed { i, c -> x[i] = (c.code xor 1).toChar() }
    val epsilon = x.concatToString().toInt(2)

    return (gamma * epsilon).toString()
  }

  override fun solveSecond(): String {
    val lines = this.openFile?.use(this::parseFile) ?: throw IllegalStateException()
    val o2 = lines.toMutableList()
    val co2 = lines.toMutableList()
    for (i in 0 until (lines.minOfOrNull { it.length } ?: 0)) {
      val common = o2.groupingBy { it[i] }.eachCount().entries.maxWithOrNull(comparator)!!.key
      val uncommon = co2.groupingBy { it[i] }.eachCount().entries.minWithOrNull(comparator)!!.key
      o2.retainAll { it[i] == common }
      co2.retainAll { it[i] == uncommon }
    }
    return (o2.single().toInt(2) * co2.single().toInt(2)).toString()
  }

  // NOTE E21D03
  private val openFile: InputStream?
    get() = this.javaClass.classLoader.getResourceAsStream("resources/E21D03.txt")

  private fun parseFile(input: InputStream): List<String> = input.bufferedReader().run(this::parseFile)
  private fun parseFile(input: BufferedReader): List<String> = input.readLines()

  // NOTE Static
  companion object {
    @JvmStatic
    private val TAG = E21D03::class.java.simpleName

    private val String.parse: Int
      get() = this.toInt(2)

    @JvmStatic
    private val comparator: Comparator<Map.Entry<Char, Int>> = compareBy({ it.value }, { it.key })
  }
}

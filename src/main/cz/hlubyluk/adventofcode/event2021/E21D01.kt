package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay

class E21D01() : IDay {

  // NOTE IDay
  override fun solveFirst(): String {
    return this.loadData.run(this::compute)
  }

  override fun solveSecond(): String {
    return this.loadData.windowed(3).map { it.sum() }.run(this::compute)
  }

  // NOTE E21D01
  fun compute(data: List<Int>): String {
    return data.windowed(2).map { it.first() < it.last() }.count { it }.toString()
  }
  val loadData: List<Int>
    get() = this.javaClass.classLoader
      .getResourceAsStream("resources/E21D01.txt")
      ?.use { it.bufferedReader().readLines() }
      ?.map { it.toInt() } ?: throw IllegalStateException()

  // NOTE Static
  companion object {
    @JvmStatic
    private val CLAZZ = E21D01::class.java

    @JvmStatic
    private val TAG = CLAZZ.simpleName

    @JvmStatic
    private val TEST = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
  }
}

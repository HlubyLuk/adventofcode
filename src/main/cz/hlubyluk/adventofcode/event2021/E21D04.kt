package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.io.InputStream
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class E21D04 : IDay {

  // NOTE IDay
  override fun solveFirst(): String {
    val numbers = this.numbers?.split(',')?.map { i -> i.toInt() } ?: throw IllegalStateException()
    val lines = this.playCard ?: throw IllegalStateException()
    val cards = ArrayList<Card>()
    for (i in lines.indices step 6) {
      lines.subList(i + 1, i + 6).run(::Card).run(cards::add)
    }
    for (number in numbers) {
      for (card in cards) {
        val result = card.with(number)
        if (result != Int.MIN_VALUE) return result.toString()
      }
    }
    return ""
  }

  override fun solveSecond(): String {
    val numbers = this.numbers?.split(',')?.map { i -> i.toInt() } ?: throw IllegalStateException()
    val lines = this.playCard ?: throw IllegalStateException()
    val cards = ArrayList<Card>()
    for (i in lines.indices step 6) {
      lines.subList(i + 1, i + 6).run(::Card).run(cards::add)
    }
    var result = Int.MIN_VALUE
    for (number in numbers) {
      val iterator = cards.iterator()
      while (iterator.hasNext()) {
        val card = iterator.next()
        val tmp = card.with(number)
        if (tmp != Int.MIN_VALUE) {
          iterator.remove()
          result = tmp
          println("new tmp=$result")
        }
      }
    }
    return result.toString()
  }

  // NOTE E21D04
  private val openFile: InputStream?
    get() = this.javaClass.classLoader.getResourceAsStream("resources/E21D04.txt")
  private val numbers: String?
    get() = this.openFile?.bufferedReader()?.use { it.readLine() }
  private val playCard: List<String>?
    get() = this.openFile?.bufferedReader()?.use { it.readLines() }?.drop(1)

  private class Card(
    data: List<String>
  ) {
    val valueAndPoints = TreeMap<Int, Point>()
    val checkedPoint = TreeMap<Point, Boolean>()
    val cmp = Comparator<Point> { a, b -> (a.x * 100 + a.y).compareTo(b.x * 100 + b.y) }

    init {
      var counter = 0
      val scanner = data.joinToString(" ").run(::Scanner)
      while (scanner.hasNextInt()) {
        val nextInt = scanner.nextInt()
        this.valueAndPoints[nextInt] = Point(counter % 5, counter / 5)
        counter += 1
      }
    }

    fun with(number: Int): Int {
      val point = this.valueAndPoints[number] ?: return Int.MIN_VALUE
      this.checkedPoint[point] = true

      for (i in 0 until 5) {
        val x = listOf(
          this.checkedPoint[Point(0, i)],
          this.checkedPoint[Point(1, i)],
          this.checkedPoint[Point(2, i)],
          this.checkedPoint[Point(3, i)],
          this.checkedPoint[Point(4, i)]
        )

        if (x.all { it == true }) {
          return this.success() * number
        }

        val y = listOf(
          this.checkedPoint[Point(i, 0)],
          this.checkedPoint[Point(i, 1)],
          this.checkedPoint[Point(i, 2)],
          this.checkedPoint[Point(i, 3)],
          this.checkedPoint[Point(i, 4)]
        )
        if (y.all { it == true }) {
          return this.success() * number
        }
      }
      return Int.MIN_VALUE
    }

    private fun success(): Int {
      var buffer = 0
      val points = this.checkedPoint.keys
      this.valueAndPoints.forEach { entry ->
        if (!points.contains(entry.value)) {
          buffer += entry.key
        }
      }
      return buffer
    }
  }

  private data class Point(
    val x: Int,
    val y: Int
  ) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
      return (this.y * 100 + this.x).compareTo(other.y * 100 + other.x)
    }
  }

  // NOTE Static
  companion object {
    @JvmStatic
    private val TAG = E21D04::class.java.simpleName

    @JvmStatic
    private val T = """7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7"""
  }
}

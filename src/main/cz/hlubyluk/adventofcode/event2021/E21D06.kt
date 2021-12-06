package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.math.BigInteger
import java.util.*

class E21D06 : IDay {

  // NOTE IDay
  override fun solveFirst(): String {
    val items = IntRange(-1, 8).reversed()
    val fishes = TreeMap<Int, BigInteger>()
    I.split(',').map(this::map).forEach { fish ->
      fishes[fish] = (fishes.getOrElse(fish) { BigInteger.ZERO }).add(BigInteger.ONE)
    }
    for (day in IntRange(1, 80)) {
      val copy = HashMap(fishes)
      items.forEach { index ->
        fishes[index - 1] = copy.getOrElse(index) { BigInteger.ZERO }
      }
      val new = fishes.getOrElse(-1) { BigInteger.ZERO }
      fishes[6] = fishes.getOrElse(6) { BigInteger.ZERO } + new
      fishes[8] = new
    }
    return this.result(
      356190.toString(),
      fishes.entries.filter { it.key >= 0 }.fold(BigInteger.ZERO) { acc, a -> acc.add(a.value) }.toString()
    )
  }

  override fun solveSecond(): String {
    val items = IntRange(-1, 8).reversed()
    val fishes = TreeMap<Int, BigInteger>()
    I.split(',').map(this::map).forEach { fish ->
      fishes[fish] = (fishes.getOrElse(fish) { BigInteger.ZERO }).add(BigInteger.ONE)
    }
    for (day in IntRange(1, 256)) {
      val copy = HashMap(fishes)
      items.forEach { index ->
        fishes[index - 1] = copy.getOrElse(index) { BigInteger.ZERO }
      }
      val new = fishes.getOrElse(-1) { BigInteger.ZERO }
      fishes[6] = fishes.getOrElse(6) { BigInteger.ZERO } + new
      fishes[8] = new
    }

    return this.result(1617359101538.toString(), fishes.entries.filter { it.key >= 0 }.fold(BigInteger.ZERO) { acc, a -> acc.add(a.value) }.toString())
  }


  // NOTE E21D06
  fun map(input: String): Int = input.toInt()

  // NOTE Static
  companion object {
    @JvmStatic
    private val TEST = "3,4,3,1,2"

    @JvmStatic
    private val I = "1,2,5,1,1,4,1,5,5,5,3,4,1,2,2,5,3,5,1,3,4,1,5,2,5,1,4,1,2,2,1,5,1,1,1,2,4,3,4,2,2,4,5,4,1,2,3,5,3,4,1,1,2,2,1,3,3,2,3,2,1,2,2,3,1,1,2,5,1,2,1,1,3,1,1,5,5,4,1,1,5,1,4,3,5,1,3,3,1,1,5,2,1,2,4,4,5,5,4,4,5,4,3,5,5,1,3,5,2,4,1,1,2,2,2,4,1,2,1,5,1,3,1,1,1,2,1,2,2,1,3,3,5,3,4,2,1,5,2,1,4,1,1,5,1,1,5,4,4,1,4,2,3,5,2,5,5,2,2,4,4,1,1,1,4,4,1,3,5,4,2,5,5,4,4,2,2,3,2,1,3,4,1,5,1,4,5,2,4,5,1,3,4,1,4,3,3,1,1,3,2,1,5,5,3,1,1,2,4,5,3,1,1,1,2,5,2,4,5,1,3,2,4,5,5,1,2,3,4,4,1,4,1,1,3,3,5,1,2,5,1,2,5,4,1,1,3,2,1,1,1,3,5,1,3,2,4,3,5,4,1,1,5,3,4,2,3,1,1,4,2,1,2,2,1,1,4,3,1,1,3,5,2,1,3,2,1,1,1,2,1,1,5,1,1,2,5,1,1,4"
  }
}

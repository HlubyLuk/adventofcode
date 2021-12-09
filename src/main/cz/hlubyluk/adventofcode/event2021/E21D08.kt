package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay
import java.io.BufferedReader

class E21D08 : IDay {

  // NOTE IDay

  override fun solveFirst(): String {

    fun isOne(input: String): Boolean = input.length == 2
    fun isFour(input: String): Boolean = input.length == 4
    fun isSeven(input: String): Boolean = input.length == 3
    fun isEight(input: String): Boolean = input.length == 7
    fun isInstance(input: String): Boolean = isOne(input) || isFour(input) || isSeven(input) || isEight(input)

    val result = this.readFile().split('\n')
      .map(PATTERN::matcher)
      .flatMap { matcher ->
        val find = matcher.find()
        if (find) {
          listOf(
            matcher.group(1).run(::isInstance),
            matcher.group(2).run(::isInstance),
            matcher.group(3).run(::isInstance),
            matcher.group(4).run(::isInstance)
          )
        } else {
          listOf(false)
        }
      }.count { it }
    return this.result(390, result)
  }

  override fun solveSecond(): String {
    return ""
  }

  // NOTE E21D08

  fun readFile(): String {
    return this::class.java.classLoader.getResourceAsStream("resources/E21D08.txt")
      ?.bufferedReader()?.use(BufferedReader::readText) ?: throw IllegalStateException()
  }

  // NOTE Static

  companion object {

    @JvmStatic
    val PATTERN = " \\| (\\w+) (\\w+) (\\w+) (\\w+)".toPattern()

    @JvmStatic
    val TEST = """be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"""
  }
}

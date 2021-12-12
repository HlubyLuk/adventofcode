package main.cz.hlubyluk.adventofcode.event2021

import main.cz.hlubyluk.adventofcode.IDay

class E21D10 : IDay {

  // NOTE IDay

  override fun solveFirst(): String = ""

  override fun solveSecond(): String = ""

  // NOTE E21D10

  private val mapping = mapOf('(' to ')', '<' to '<', '[' to ']', '{' to '}')
  // NOTE Static

  companion object {
    private const val TEST =
      "[({(<(())[]>[[{[]{<()<>>\n" +
        "[(()[<>])]({[<{<<[]>>(\n" +
        "{([(<{}[<>[]}>{[]{[(<()>\n" +
        "(((({<>}<{<{<>}{[]{[]{}\n" +
        "[[<[([]))<([[{}[[()]]]\n" +
        "[{[{({}]{}}([{[{{{}}([]\n" +
        "{<[[]]>}<{[{[{[]{()[[[]\n" +
        "[<(<(<(<{}))><([]([]()\n" +
        "<{([([[(<>()){}]>(<<{{\n" +
        "<{([{{}}[<[[[<>{}]]]>[]]\n"
  }
}

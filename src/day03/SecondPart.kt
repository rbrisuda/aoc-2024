package day03

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    var sum = 0L

    val line = Path("src/day03/input").readLines().joinToString("")

    val dontLines = line.split("don't()")

    dontLines.forEachIndexed { i, subLine ->
        if (i == 0) {
            sum += evaluateLine(subLine)
        } else {
            val doLines = subLine.split("do()")
            doLines.forEachIndexed { j, subLineDo ->
                if (j != 0) {
                    sum += evaluateLine(subLineDo)
                }
            }
        }
    }
    println(sum)
}

val regexMul = Regex("mul\\(\\d+,\\d+\\)")
val regexMulNumbers = Regex("\\((\\d+),(\\d+)\\)")

private fun evaluateLine(line: String): Long {

    var sum = 0L
    val matches = regexMul.findAll(line)

    for (match in matches) {
        val match2 = regexMulNumbers.find(match.value)
        sum += match2!!.groupValues[1].toLong() * match2.groupValues[2].toLong()
    }
    return sum
}

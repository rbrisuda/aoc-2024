package day13

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


fun main() {
    val regexButton = """[X|Y]\+(\d+)""".toRegex()
    val regexPrize = """[X|Y]=(\d+)""".toRegex()

    val threeLines = mutableListOf<String>()
    var sum = 0L
    Path("src/day13/input").forEachLine {
        if (it != "") threeLines.add(it)
        if (threeLines.size == 3) {
            var matches = regexButton.findAll(threeLines[0]).toList()
            val aX = matches[0].groupValues[1].toLong()
            val ay = matches[1].groupValues[1].toLong()

            matches = regexButton.findAll(threeLines[1]).toList()
            val bX = matches[0].groupValues[1].toLong()
            val by = matches[1].groupValues[1].toLong()

            matches = regexPrize.findAll(threeLines[2]).toList()
            val x = matches[0].groupValues[1].toLong()
            val y = matches[1].groupValues[1].toLong()
            sum += findMinimumTokens(10000000000000 + x, 10000000000000 + y, aX, ay, bX, by)
            threeLines.clear()
        }

    }
    println(sum)
}

private fun findMinimumTokens(
    prizeX: Long,
    prizeY: Long,
    buttonAx: Long,
    buttonAy: Long,
    buttonBx: Long,
    buttonBy: Long
): Long {
    val tokens3 = (buttonBy.toDouble() * prizeX - buttonBx * prizeY) / (buttonBy * buttonAx - buttonBx * buttonAy)
    val tokens1 = (prizeY - (buttonAy * tokens3)) / buttonBy
    val tokens = tokens3 * 3 + tokens1
    if (tokens % 1 == 0.0) return tokens.toLong()
    return 0
}

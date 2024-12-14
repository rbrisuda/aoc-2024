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
            sum += findMinimumTokens(x, y, aX, ay, bX, by)
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
    var minTokens = Int.MAX_VALUE.toLong()
    for (tokens3 in 0..(prizeX / buttonAx)) {
        for (tokens1 in 0..(prizeY / buttonBy)) {
            val finalX = tokens3 * buttonAx + tokens1 * buttonBx
            val finalY = tokens3 * buttonAy + tokens1 * buttonBy
            if (finalX == prizeX && finalY == prizeY) {
                val tokens = tokens3 * 3 + tokens1
                if (tokens < minTokens) {
                    minTokens = tokens
                }
            }
        }
    }
    return if (minTokens == Int.MAX_VALUE.toLong()) 0 else minTokens
}


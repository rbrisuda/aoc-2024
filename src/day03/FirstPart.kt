package day03

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    var sum = 0L
    val regex = Regex("mul\\(\\d+,\\d+\\)")
    val regexNumbers = Regex("\\((\\d+),(\\d+)\\)")

    val line = Path("src/day03/input").readLines().joinToString("")

    val matches = regex.findAll(line)

    for (match in matches) {
        val match2 = regexNumbers.find(match.value)
        sum += match2!!.groupValues[1].toLong() * match2.groupValues[2].toLong()
    }
    println(sum)
}

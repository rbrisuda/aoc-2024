package day07

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


fun main() {
    var sum = 0L

    Path("src/day07/input").forEachLine {
        val r = it.split(": ")
        val targetResult = r[0].toLong()
        val numbers = r[1].split(" ").map { it.toLong() }
        if (checkTargetResult(targetResult, numbers, 1, numbers[0])) sum += targetResult
    }
    println(sum)
}


private fun checkTargetResult(
    targetResult: Long,
    numbers: List<Long>,
    i: Int = 1,
    currentResult: Long
): Boolean {
    if (i == numbers.size) {
        return targetResult == currentResult
    }
    return checkTargetResult(targetResult, numbers, i + 1, currentResult + numbers[i]) ||
            checkTargetResult(targetResult, numbers, i + 1, currentResult * numbers[i])
}

package day02

import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun main() {
    var count = 0

    Path("src/day02/input").forEachLine {
        val numbers = it.split(" ").map { it.toInt() }

        if (checkList(numbers)) {
            count++
        } else {
            var sub = false
            numbers.indices.forEach {
                val subList = numbers.filterIndexed { index, _ -> index != it }
                sub = sub || checkList(subList)
            }
            if (sub) count++
        }
    }
    println(count)
}

private fun checkList(numbers: List<Int>): Boolean {
    var sign = 0
    (0 until numbers.size - 1).forEach {
        val r = numbers[it + 1] - numbers[it]
        if (sign == 0) {
            if (r < 0) sign = -1
            if (r > 0) sign = 1
        }
        if (r * sign > 3 || r * sign <= 0) {
            return false
        }
    }
    return true
}

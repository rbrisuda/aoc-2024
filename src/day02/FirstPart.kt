package day02

import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun main() {
    var count = 0

    Path("src/day02/input").forEachLine {
        val numbers = it.split(" ").map { it.toInt() }

        var sign = 0
        var valid = true
        (0 until numbers.size - 1).forEach {
            val r = numbers[it + 1] - numbers[it]
            if (sign == 0) {
                if (r < 0) sign = -1
                if (r > 0) sign = 1
            }
            if (r * sign > 3 || r * sign <= 0) {
                valid = false
                return@forEach
            }
        }
        if (valid) {
            count++
        }
    }
    println(count)
}

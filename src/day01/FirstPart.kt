package day01

import kotlin.io.path.Path
import kotlin.io.path.forEachLine
import kotlin.math.abs

fun main() {
    val whitespaceRegex = "\\s+".toRegex()
    val listLeft = mutableListOf<Int>()
    val listRight = mutableListOf<Int>()

    Path("src/day01/input").forEachLine {
        val numbers = it.split(whitespaceRegex)
        listLeft.add(numbers[0].toInt())
        listRight.add(numbers[1].toInt())
    }
    listLeft.sort()
    listRight.sort()

    var result = 0L
    listLeft.forEachIndexed { i, left ->
        result += abs(listRight[i] - left)
    }
    println(result)
}

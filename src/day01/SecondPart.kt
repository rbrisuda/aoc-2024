package day01

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


fun main() {
    val whitespaceRegex = "\\s+".toRegex()
    val listLeft = mutableListOf<Int>()
    val mapRight = mutableMapOf<Int, Int>()

    Path("src/day01/input").forEachLine {
        val numbers = it.split(whitespaceRegex)
        val left = numbers[0].toInt()
        val right = numbers[1].toInt()

        listLeft.add(left)

        mapRight.computeIfPresent(right) { _, v -> v + 1 }
        mapRight.computeIfAbsent(right) { 1 }
    }

    var result = 0L
    listLeft.forEach { left ->
        result += left * mapRight.getOrDefault(left, 0)
    }
    println(result)
}

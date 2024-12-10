package day10

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


data class Count(var count: Int = 0)

fun main() {
    val matrix = mutableListOf<String>()
    val startingPositions = mutableListOf<Pair<Int, Int>>()
    var i = 0

    Path("src/day10/input").forEachLine { line ->
        var j = 0
        matrix.add(line)
        line.forEach {
            if (it == '0') {
                startingPositions.add(Pair(i, j))
            }
            j++
        }
        i++
    }
    var result = 0L
    startingPositions.forEach {
        val count = Count(0)
        traversePositions(it.first, it.second, 0, matrix, mutableSetOf(), count)
        result += count.count
    }
    println(result)
}

private fun traversePositions(
    i: Int,
    j: Int,
    position: Int,
    matrix: List<String>,
    visited: MutableSet<Pair<Int, Int>>,
    count: Count
) {
    if (position == 9) count.count++
    visited.add(Pair(i, j))
    if (i + 1 < matrix.size && !visited.contains(Pair(i + 1, j)) && matrix[i + 1][j].digitToInt() == position + 1) {
        traversePositions(i + 1, j, position + 1, matrix, visited, count)
    }
    if (j + 1 < matrix.size && !visited.contains(Pair(i, j + 1)) && matrix[i][j + 1].digitToInt() == position + 1) {
        traversePositions(i, j + 1, position + 1, matrix, visited, count)
    }
    if (i - 1 >= 0 && !visited.contains(Pair(i - 1, j)) && matrix[i - 1][j].digitToInt() == position + 1) {
        traversePositions(i - 1, j, position + 1, matrix, visited, count)
    }
    if (j - 1 >= 0 && !visited.contains(Pair(i, j - 1)) && matrix[i][j - 1].digitToInt() == position + 1) {
        traversePositions(i, j - 1, position + 1, matrix, visited, count)
    }
}

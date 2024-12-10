package day10

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


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
        traversePositions(it.first, it.second, 0, matrix, count)
        result += count.count
    }
    println(result)
}

private fun traversePositions(
    i: Int,
    j: Int,
    position: Int,
    matrix: List<String>,
    count: Count
) {
    if (position == 9) count.count++
    if (i + 1 < matrix.size && matrix[i + 1][j].digitToInt() == position + 1) {
        traversePositions(i + 1, j, position + 1, matrix, count)
    }
    if (j + 1 < matrix.size && matrix[i][j + 1].digitToInt() == position + 1) {
        traversePositions(i, j + 1, position + 1, matrix, count)
    }
    if (i - 1 >= 0 && matrix[i - 1][j].digitToInt() == position + 1) {
        traversePositions(i - 1, j, position + 1, matrix, count)
    }
    if (j - 1 >= 0 && matrix[i][j - 1].digitToInt() == position + 1) {
        traversePositions(i, j - 1, position + 1, matrix, count)
    }
}

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
        result += countTrails(it.first, it.second, 0, matrix)
    }
    println(result)
}

private fun countTrails(
    i: Int,
    j: Int,
    position: Int,
    matrix: List<String>,
): Int {
    if (position == 9) return 1
    var count = 0
    if (i + 1 < matrix.size && matrix[i + 1][j].digitToInt() == position + 1) {
        count += countTrails(i + 1, j, position + 1, matrix)
    }
    if (j + 1 < matrix.size && matrix[i][j + 1].digitToInt() == position + 1) {
        count += countTrails(i, j + 1, position + 1, matrix)
    }
    if (i - 1 >= 0 && matrix[i - 1][j].digitToInt() == position + 1) {
        count += countTrails(i - 1, j, position + 1, matrix)
    }
    if (j - 1 >= 0 && matrix[i][j - 1].digitToInt() == position + 1) {
        count += countTrails(i, j - 1, position + 1, matrix)
    }
    return count
}

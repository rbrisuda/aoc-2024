package day12

import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun main() {
    val map = mutableListOf<String>()

    Path("src/day12/input").forEachLine {
        map.add(it)
    }
    var result = 0

    val visited = Array(map.size) { BooleanArray(map.size) }
    for (row in 0..<map.size) {
        for (col in 0..<map.size) {
            if (!visited[row][col]) {
                val shape = mutableListOf<Pair<Int, Int>>()
                findShape(map, visited, row, col, map[row][col], shape)
                result += shape.size * calculateCorners(shape)
            }
        }
    }
    println(result)
}

private fun findShape(
    matrix: List<String>,
    visited: Array<BooleanArray>,
    i: Int,
    j: Int,
    plant: Char,
    shape: MutableList<Pair<Int, Int>>
) {
    if (i < 0 || j < 0 || i >= matrix.size || j >= matrix.size || visited[i][j] || matrix[i][j] != plant) {
        return
    }
    visited[i][j] = true
    shape.add(Pair(i, j))
    findShape(matrix, visited, i + 1, j, plant, shape)
    findShape(matrix, visited, i - 1, j, plant, shape)
    findShape(matrix, visited, i, j + 1, plant, shape)
    findShape(matrix, visited, i, j - 1, plant, shape)
}

fun calculateCorners(shape: List<Pair<Int, Int>>): Int {
    val maxX = shape.maxOf { it.first } + 3
    val maxY = shape.maxOf { it.second } + 3
    val matrix = Array(maxX) { IntArray(maxY) { 0 } }
    shape.forEach { (x, y) ->
        matrix[x + 1][y + 1] = 1
    }
    var corners = 0
    for (i in 0..<maxX - 1) {
        for (j in 0..<maxY - 1) {
            val grid = listOf(matrix[i][j], matrix[i][j + 1], matrix[i + 1][j], matrix[i + 1][j + 1])
            if (grid.count { it == 1 } % 2 != 0) {
                corners += 1
            } else if (grid == listOf(1, 0, 0, 1) || grid == listOf(0, 1, 1, 0)) {
                corners += 2
            }
        }
    }
    return corners
}

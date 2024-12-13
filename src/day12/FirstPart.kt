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
                result += shape.size * calculatePerimeter(shape.toSet())
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

private fun calculatePerimeter(shape: Set<Pair<Int, Int>>): Int {
    var perimeter = 0
    for (point in shape) {
        if (!shape.contains(Pair(point.first, point.second + 1))) perimeter++
        if (!shape.contains(Pair(point.first + 1, point.second))) perimeter++
        if (!shape.contains(Pair(point.first, point.second + -1))) perimeter++
        if (!shape.contains(Pair(point.first - 1, point.second))) perimeter++
    }
    return perimeter
}
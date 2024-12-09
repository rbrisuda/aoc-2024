package day08

import kotlin.io.path.Path
import kotlin.io.path.forEachLine
import kotlin.math.*


fun main() {
    val coordinates = mutableMapOf<Char, MutableList<Point>>()
    var i = 0
    var matrixSize = 0

    Path("src/day08/input").forEachLine {
        matrixSize = it.length
        it.forEachIndexed { j, c ->
            if (c != '.') {
                coordinates.computeIfPresent(c) { _, v ->
                    v.add(Point(i, j))
                    v
                }
                coordinates.computeIfAbsent(c) { mutableListOf(Point(i, j)) }
            }
        }
        i++
    }
    val antinodes = mutableSetOf<Point>()
    coordinates.forEach { _, points ->
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val a = points[i]
                val b = points[j]
                for (k in 0..matrixSize) {
                    val c = findPointByDistance(a, b, k * calculateDistance(a, b))
                    val c2 = findPointByDistance(a, b, -1 * k * calculateDistance(a, b))

                    if (inArea(c, matrixSize)) antinodes.add(c)
                    if (inArea(c2, matrixSize)) antinodes.add(c2)
                }
            }
        }
    }
    println(antinodes.count())
}

private fun inArea(c: Point, size: Int): Boolean {
    if (c.x < 0 || c.x >= size || c.y < 0 || c.y >= size) return false
    return true
}

private fun findPointByDistance(a: Point, b: Point, distance: Double): Point {
    val slope = (b.y.toDouble() - a.y) / (b.x - a.x);
    val theta = atan(slope)
    val cx = round(b.x + distance * cos(theta))
    val cy = round(b.y + distance * sin(theta))
    return Point(cx.toInt(), cy.toInt())
}

private fun calculateDistance(a: Point, b: Point): Double {
    return sqrt((b.x.toDouble() - a.x).pow(2) + (b.y.toDouble() - a.y).pow(2))
}

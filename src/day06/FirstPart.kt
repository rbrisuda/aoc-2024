package day06

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


fun main() {
    var i = 0
    var up: Pair<Int, Int>? = null
    var down: Pair<Int, Int>? = null
    var left: Pair<Int, Int>? = null
    var right: Pair<Int, Int>? = null
    val matrix: MutableList<String> = mutableListOf()

    Path("src/day06/input").forEachLine {
        matrix.add(it)
        if (it.indexOf(">") != -1) right = Pair(i, it.indexOf(">"))
        if (it.indexOf("<") != -1) left = Pair(i, it.indexOf("<"))
        if (it.indexOf("^") != -1) up = Pair(i, it.indexOf("^"))
        if (it.indexOf("v") != -1) down = Pair(i, it.indexOf("v"))
        i++
    }

    val size = matrix.size
    val unique: MutableSet<String> = mutableSetOf()
    var out = false

    while (true) {
        when {
            right != null -> {
                for (k in right!!.second..size) {
                    if (k == size) {
                        out = true
                        break
                    }
                    if (matrix[right!!.first][k] == '#') {
                        down = Pair(right!!.first, k - 1)
                        right = null
                        break
                    }
                    unique.add("${right!!.first}-$k")
                }
            }

            down != null -> {
                for (k in down!!.first..size) {
                    if (k == size) {
                        out = true
                        break
                    }
                    if (matrix[k][down!!.second] == '#') {
                        left = Pair(k - 1, down!!.second)
                        down = null
                        break
                    }
                    unique.add("$k-${down!!.second}")
                }
            }

            left != null -> {
                for (k in left!!.second downTo -1) {
                    if (k == -1) {
                        out = true
                        break
                    }
                    if (matrix[left!!.first][k] == '#') {
                        up = Pair(left!!.first, k + 1)
                        left = null
                        break
                    }
                    unique.add("${left!!.first}-$k")
                }
            }

            up != null -> {
                for (k in up!!.first downTo -1) {
                    if (k == -1) {
                        out = true
                        break
                    }
                    if (matrix[k][up!!.second] == '#') {
                        right = Pair(k + 1, up!!.second)
                        up = null
                        break
                    }
                    unique.add("$k-${up!!.second}")
                }
            }
        }
        if (out) {
            break
        }
    }
    println(unique.size)
}

package day06

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


fun main() {
    var i = 0
    var upFirst: Pair<Int, Int>? = null
    var downFirst: Pair<Int, Int>? = null
    var leftFirst: Pair<Int, Int>? = null
    var rightFirst: Pair<Int, Int>? = null
    val matrix: MutableList<String> = mutableListOf()

    Path("src/day06/input").forEachLine {
        matrix.add(it)
        if (it.indexOf(">") != -1) rightFirst = Pair(i, it.indexOf(">"))
        if (it.indexOf("<") != -1) leftFirst = Pair(i, it.indexOf("<"))
        if (it.indexOf("^") != -1) upFirst = Pair(i, it.indexOf("^"))
        if (it.indexOf("v") != -1) downFirst = Pair(i, it.indexOf("v"))
        i++
    }

    val size = matrix.size
    var stuckCount = 0

    for (ii in 0..<size) {
        for (jj in 0..<size) {
            var out = false
            val stuckSet = mutableSetOf<String>()
            var up = upFirst
            var down = downFirst
            var right = rightFirst
            var left = leftFirst

            while (true) {
                when {
                    right != null -> {
                        for (k in right!!.second..size) {
                            if (k == size) {
                                out = true
                                break
                            }
                            if (matrix[right!!.first][k] == '#' || (right!!.first == ii && k == jj)) {
                                val key = "r${right!!.first}-${k - 1}"
                                if (stuckSet.contains(key)) {
                                    stuckCount++
                                    out = true; break
                                }
                                stuckSet.add(key)
                                down = Pair(right!!.first, k - 1)
                                right = null
                                break
                            }
                        }
                    }

                    down != null -> {
                        for (k in down!!.first..size) {
                            if (k == size) {
                                out = true
                                break
                            }
                            if (matrix[k][down!!.second] == '#' || (k == ii && down!!.second == jj)) {
                                val key = "d${k - 1}-${down!!.second}"
                                if (stuckSet.contains(key)) {
                                    stuckCount++
                                    out = true; break
                                }
                                stuckSet.add(key)
                                left = Pair(k - 1, down!!.second)
                                down = null
                                break
                            }
                        }
                    }

                    left != null -> {
                        for (k in left!!.second downTo -1) {
                            if (k == -1) {
                                out = true
                                break
                            }
                            if (matrix[left!!.first][k] == '#' || (left!!.first == ii && k == jj)) {
                                val key = "l${left!!.first}-${k + 1}"
                                if (stuckSet.contains(key)) {
                                    stuckCount++
                                    out = true; break
                                }
                                stuckSet.add(key)
                                up = Pair(left!!.first, k + 1)
                                left = null
                                break
                            }
                        }
                    }

                    up != null -> {
                        for (k in up!!.first downTo -1) {
                            if (k == -1) {
                                out = true
                                break
                            }
                            if (matrix[k][up!!.second] == '#' || (k == ii && up!!.second == jj)) {
                                val key = "u${k + 1}-${up!!.second}"
                                if (stuckSet.contains(key)) {
                                    stuckCount++
                                    out = true; break
                                }
                                stuckSet.add(key)
                                right = Pair(k + 1, up!!.second)
                                up = null
                                break
                            }
                        }
                    }
                }
                if (out) {
                    break
                }
            }
        }
    }
    println(stuckCount)
}

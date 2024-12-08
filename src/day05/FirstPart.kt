package day05

import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun main() {
    val rules: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    var next = false
    var sum = 0

    Path("src/day05/input").forEachLine {
        if (it == "") {
            next = true
        }
        if (!next) {
            val numbers = it.split("|")
            val left = numbers[0].toInt()
            val right = numbers[1].toInt()
            rules.computeIfPresent(right) { _, v ->
                v.add(left)
                v
            }
            rules.computeIfAbsent(right) { mutableSetOf(left) }
        } else if (it != "") {
            val numbers = it.split(",").map { n -> n.toInt() }
            val notAllowed = mutableSetOf<Int>()
            var valid = true
            numbers.forEachIndexed { _, v ->
                if (notAllowed.contains(v)) {
                    valid = false
                    return@forEachIndexed
                }
                rules[v]?.toList()?.let { l -> notAllowed.addAll(l) }
            }
            if (valid) {
                sum += numbers[numbers.size / 2]
            }
        }
    }
    println(sum)
}

package day05

import kotlin.io.path.Path
import kotlin.io.path.forEachLine


fun main() {
    val rules: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    val rulesPair: MutableSet<String> = mutableSetOf()
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
            rulesPair.add("$right-$left")
        } else if (it != "") {
            val numbers = it.split(",").map { n -> n.toInt() }.toMutableList()
            val notAllowed = mutableSetOf<Int>()
            var valid = true
            numbers.forEachIndexed { _, v ->
                if (notAllowed.contains(v)) {
                    valid = false
                    return@forEachIndexed
                }
                rules[v]?.toList()?.let { l -> notAllowed.addAll(l) }
            }
            if (!valid) {
                numbers.sortWith { i, j -> if (rulesPair.contains("$i-$j")) 1 else -1 }
                sum += numbers[numbers.size / 2]
            }
        }
    }
    println(sum)
}

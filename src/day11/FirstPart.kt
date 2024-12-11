package day11

import kotlin.io.path.Path
import kotlin.io.path.forEachLine
import kotlin.io.path.readText
import kotlin.math.abs

fun main() {
    var stones = Path("src/day11/input").readText().split(" ")

    for (i in 0..<25) {
        val newStones = mutableListOf<String>()
        stones.forEach { stone ->
            if (stone == "0") {
                newStones.add("1")
            } else if (stone.length % 2 == 0) {
                newStones.add(stone.substring(0, stone.length / 2).trimStart('0').ifEmpty { "0" })
                newStones.add(stone.substring(stone.length / 2, stone.length).trimStart('0').ifEmpty { "0" })
            } else {
                newStones.add("${stone.toLong() * 2024}")
            }

        }
        stones = newStones
    }
    println(stones.size)
}

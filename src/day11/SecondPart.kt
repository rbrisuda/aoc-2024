package day11

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val line = Path("src/day11/input").readText().split(" ")

    var count = 0L
    line.forEach { stone ->
        count += calculateStones(stone, 75, mutableMapOf())
    }
    println(count)
}

private fun calculateStones(stone: String, blinks: Int, stonesMap: MutableMap<String, Long>): Long {
    val key = "$stone-$blinks"
    if (!stonesMap.contains(key)) {
        if (blinks == 0) return 1L
        val count =
            if (stone == "0") {
                calculateStones("1", blinks - 1, stonesMap)
            } else if (stone.length % 2 == 0) {
                val left = stone.substring(0, stone.length / 2).trimStart('0').ifEmpty { "0" }
                val right = stone.substring(stone.length / 2, stone.length).trimStart('0').ifEmpty { "0" }
                calculateStones(left, blinks - 1, stonesMap) + calculateStones(right, blinks - 1, stonesMap)
            } else {
                calculateStones("${stone.toLong() * 2024}", blinks - 1, stonesMap)
            }
        stonesMap[key] = count
        return count
    }
    return stonesMap[key] ?: 0
}

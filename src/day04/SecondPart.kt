package day04

import kotlin.io.path.Path
import kotlin.io.path.readLines


fun main() {
    val lines = Path("src/day04/input").readLines()
    val length = lines.size
    var countAll = 0

    for (i in 0..length - 3) {
        for (j in 0..length - 3) {
            if (lines[i][j] == 'M' || lines[i][j] == 'S') {
                countAll += countXmas(lines, i, j) + countXmasReversed(lines, i, j)
            }
        }
    }
    println(countAll)
}

private fun countXmas(lines: List<String>, i: Int, j: Int): Int {
    var count = 0;
    val line1 = "${lines[i][j]}-${lines[i + 2][j]}"
    val middle = "${lines[i + 1][j + 1]}"
    val line2 = "${lines[i][j + 2]}-${lines[i + 2][j + 2]}"

    if (line1 == "M-S" && middle == "A" && line2 == "M-S") {
        count++
    }
    if (line1 == "S-M" && middle == "A" && line2 == "S-M") {
        count++
    }
    return count
}

private fun countXmasReversed(lines: List<String>, i: Int, j: Int): Int {
    var count = 0;
    val line1 = "${lines[i][j]}-${lines[i][j + 2]}"
    val middle = "${lines[i + 1][j + 1]}"
    val line2 = "${lines[i + 2][j]}-${lines[i + 2][j + 2]}"

    if (line1 == "M-S" && middle == "A" && line2 == "M-S") {
        count++
    }
    if (line1 == "S-M" && middle == "A" && line2 == "S-M") {
        count++
    }
    return count
}

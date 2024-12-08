package day04

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val lines = Path("src/day04/input").readLines()

    val length = lines.size
    var countAll = 0
    for (i in 0..<length) {
        for (j in 0..<length) {
            if (lines[i][j] == 'X' || lines[i][j] == 'S') {
                countAll += countXmas(lines, i, j)
            }
        }
    }
    println(countAll)
}

private fun countXmas(lines: List<String>, i: Int, j: Int): Int {
    var count = 0;
    val vertical = "${lines[i][j]}${lines.gon(i + 1)?.gon(j)}${lines.gon(i + 2)?.gon(j)}${lines.gon(i + 3)?.gon(j)}"
    val horizontal = "${lines[i][j]}${lines.gon(i)?.gon(j + 1)}${lines.gon(i)?.gon(j + 2)}${lines.gon(i)?.gon(j + 3)}"
    val diagonal1 = "${lines[i][j]}${lines.gon(i + 1)?.gon(j + 1)}${lines.gon(i + 2)?.gon(j + 2)}${lines.gon(i + 3)?.gon(j + 3)}"
    val diagonal2 = "${lines[i][j]}${lines.gon(i + 1)?.gon(j - 1)}${lines.gon(i + 2)?.gon(j - 2)}${lines.gon(i + 3)?.gon(j - 3)}"

    if (vertical == "XMAS" || vertical == "SAMX") {
        count++
    }
    if (horizontal == "XMAS" || horizontal == "SAMX") {
        count++
    }
    if (diagonal1 == "XMAS" || diagonal1 == "SAMX") {
        count++
    }
    if (diagonal2 == "XMAS" || diagonal2 == "SAMX") {
        count++
    }

    return count
}

private fun List<String>.gon(i: Int) = getOrNull(i)
private fun String.gon(i: Int) = getOrNull(i)

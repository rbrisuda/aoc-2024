package day09

import kotlin.io.path.Path
import kotlin.io.path.forEachLine
import kotlin.io.path.readText
import kotlin.math.abs

fun main() {
    val input = Path("src/day09/input").readText().toCharArray()

    val freeSpaceIndexes = mutableListOf<Int>()
    val fileIds = mutableListOf<Int>()
    var fileId = 0
    val disk = mutableListOf<Int>()
    var diskPosition = 0

    input.forEachIndexed { i, c ->
        val block = c.digitToInt()
        if (i % 2 == 0) {
            for (k in 0..<block) {
                fileIds.add(fileId)
                disk.add(fileId)
                diskPosition++
            }
            fileId++
        } else {
            for (k in 0..<block) {
                freeSpaceIndexes.add(diskPosition)
                disk.add(-1)
                diskPosition++
            }
        }
    }

    var availableFreeSpaceIndex = 0
    for (i in fileIds.size - 1 downTo 0) {
        if (availableFreeSpaceIndex < freeSpaceIndexes.size) {
            disk[freeSpaceIndexes[availableFreeSpaceIndex]] = fileIds[i]
        } else {
            break
        }
        availableFreeSpaceIndex++
    }

    var result = 0L
    for (i in 0..<disk.size - availableFreeSpaceIndex) {
        result += i * disk[i]
    }
    println(result)
}

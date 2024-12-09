package day09

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val input = Path("src/day09/input").readText().toCharArray()

    val fileIdsIndexes: MutableMap<Int, MutableList<Int>> = sortedMapOf()
    var fileId = 0
    val disk = mutableListOf<Int>()
    var diskPosition = 0

    input.forEachIndexed { i, c ->
        val block = c.digitToInt()
        if (i % 2 == 0) {
            val positions = mutableListOf<Int>()
            for (k in 0..<block) {
                disk.add(fileId)
                positions.add(diskPosition)
                diskPosition++
            }
            fileIdsIndexes[fileId] = positions
            fileId++
        } else {
            for (k in 0..<block) {
                disk.add(-1)
                diskPosition++
            }
        }
    }

    for (uniqueFileId in fileIdsIndexes.keys.reversed()) {
        val uniqueFileIdIndexes = fileIdsIndexes[uniqueFileId]

        val blockSize = uniqueFileIdIndexes!!.size
        var startingFreeSpaceBlockIndex = -1
        var freeSpaceBlockSize = 0

        for (i in 0..uniqueFileIdIndexes[0]) {
            if (disk[i] == -1) {
                if (startingFreeSpaceBlockIndex == -1) startingFreeSpaceBlockIndex = i

                freeSpaceBlockSize++

                if (freeSpaceBlockSize == blockSize) {
                    for (j in startingFreeSpaceBlockIndex ..<startingFreeSpaceBlockIndex + blockSize) {
                        disk[j] = uniqueFileId
                    }
                    for (j in uniqueFileIdIndexes) {
                        disk[j] = -1
                    }
                    break
                }
            } else {
                startingFreeSpaceBlockIndex = -1
                freeSpaceBlockSize = 0
            }
        }
    }

    var result = 0L
    for (i in 0..<disk.size) {
        if (disk[i] != -1) result += i * disk[i]
    }
    println(result)
}

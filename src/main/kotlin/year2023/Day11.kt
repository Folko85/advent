package year2023

import java.io.File
import kotlin.math.abs

fun main() {


    fun expandGalaxy(array: Array<CharArray>): Array<CharArray> {
        var expandedArray: Array<CharArray> = array
        val horizonSpaces: MutableList<Int> = mutableListOf()
        for (i in array.indices) {
            if (array[i].all { it == '.' }) {
                horizonSpaces.add(i)
            }
        }
        horizonSpaces.sorted().reversed().forEach { index ->
            val spaceArray: CharArray = expandedArray[index]
            val downArray: Array<CharArray> = expandedArray.copyOfRange(0, index)
            val upArray: Array<CharArray> = expandedArray.copyOfRange(index, expandedArray.size)
            expandedArray = Array(expandedArray.size + 1) { CharArray(expandedArray[0].size) }
            for (i in expandedArray.indices)
                if (i < index) {
                    expandedArray[i] = downArray[i]
                } else if (i == index) {
                    expandedArray[i] = spaceArray
                } else {
                    expandedArray[i] = upArray[i - 1 - index]
                }
        }
        val revertedArray: Array<CharArray> = rotateCW(expandedArray)

        expandedArray = revertedArray

        val verticalSpaces: MutableList<Int> = mutableListOf()
        for (i in revertedArray.indices) {
            if (revertedArray[i].all { it == '.' }) {
                verticalSpaces.add(i)
            }
        }

        verticalSpaces.sorted().reversed().forEach { index ->
            val spaceArray: CharArray = expandedArray[index]
            val downArray: Array<CharArray> = expandedArray.copyOfRange(0, index)
            val upArray: Array<CharArray> = expandedArray.copyOfRange(index, expandedArray.size)
            expandedArray = Array(expandedArray.size + 1) { CharArray(expandedArray[0].size) }
            for (i in expandedArray.indices)
                if (i < index) {
                    expandedArray[i] = downArray[i]
                } else if (i == index) {
                    expandedArray[i] = spaceArray
                } else {
                    expandedArray[i] = upArray[i - 1 - index]
                }
        }

        return expandedArray
    }

    fun calculateDistance(expandedArray: Array<CharArray>): Long {
        val galaxies: MutableList<Pair<Int, Int>> = mutableListOf()
        for (i in expandedArray.indices) {
            for (j in expandedArray[i].indices) {
                if (expandedArray[i][j] == '#') {
                    galaxies.add(Pair(i, j))
                }
            }
        }

        var sum = 0L

        for (i in galaxies.indices) {
            val currentPair: Pair<Int, Int> = galaxies[i]
            for (j in i + 1..<galaxies.size) {
                val secondPair: Pair<Int, Int> = galaxies[j]
                sum += abs(secondPair.first - currentPair.first) + abs(secondPair.second - currentPair.second)
            }
        }

        return sum
    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_11_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array = Array(rows) { CharArray(cols) }
        for (i in 0..<rows) {
            array[i] = strings[i].toCharArray()
        }
        val expandedArray: Array<CharArray> = expandGalaxy(array)
        val result: Long = calculateDistance(expandedArray)
        println(result)
    }


    fun part2() {
        val strings: List<String> = File("src/main/resources/2023_day_10_input.txt").bufferedReader().readLines()


    }

    part1()
//    part2()

}

fun rotateCW(mat: Array<CharArray>): Array<CharArray> {
    val rowSize = mat.size
    val colSize = mat[0].size
    val ret = Array(colSize) { CharArray(rowSize) }
    for (r in 0..<rowSize) {
        for (c in 0..<colSize) {
            ret[c][rowSize - 1 - r] = mat[r][c]
        }
    }
    return ret
}





package year2023

import utils.Utils.rotateClockwise
import java.io.File
import java.math.BigInteger
import kotlin.math.abs

fun main() {


    fun calculateDistance(
        expandedArray: Array<CharArray>,
        horizontalRows: MutableList<Int>,
        verticalRows: MutableList<Int>,
        coefficient: Int
    ): BigInteger {
        val galaxies: MutableList<Pair<Int, Int>> = mutableListOf()
        for (i in expandedArray.indices) {
            for (j in expandedArray[i].indices) {
                if (expandedArray[i][j] == '#') {
                    galaxies.add(Pair(i, j))
                }
            }
        }

        var sum = BigInteger.ZERO

        for (i in galaxies.indices) {
            val currentPair: Pair<Int, Int> = galaxies[i]
            for (j in i + 1..<galaxies.size) {
                val secondPair: Pair<Int, Int> = galaxies[j]
                val rowsRange: IntProgression =
                    if (secondPair.first > currentPair.first) currentPair.first..secondPair.first else secondPair.first..currentPair.first
                val colsRange: IntProgression =
                    if (secondPair.second > currentPair.second) currentPair.second..secondPair.second else secondPair.second..currentPair.second
                val path: Int = abs(secondPair.first - currentPair.first) + abs(secondPair.second - currentPair.second)
                sum = sum.add(BigInteger(path.toString()))
                val horizontalSpaces: Long = horizontalRows.filter { it in rowsRange }.size * coefficient.toLong()
                sum = sum.add(BigInteger(horizontalSpaces.toString()))
                val verticalSpaces: Long = verticalRows.filter { it in colsRange }.size * coefficient.toLong()
                sum = sum.add(BigInteger(verticalSpaces.toString()))
            }
        }

        return sum
    }

    fun findEmptyRows(array: Array<CharArray>): MutableList<Int> {
        val spaces: MutableList<Int> = mutableListOf()
        for (i in array.indices) {
            if (array[i].all { it == '.' }) {
                spaces.add(i)
            }
        }
        return spaces
    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_11_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array = Array(rows) { CharArray(cols) }
        for (i in 0..<rows) {
            array[i] = strings[i].toCharArray()
        }
        val horizontalRows: MutableList<Int> = findEmptyRows(array)
        val rotatedArray: Array<CharArray> = rotateClockwise(array)
        val verticalRows: MutableList<Int> = findEmptyRows(rotatedArray)

        val result: BigInteger = calculateDistance(array, horizontalRows, verticalRows, 1)
        println(result)
    }


    fun part2() {
        val strings: List<String> = File("src/main/resources/2023_day_11_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array = Array(rows) { CharArray(cols) }
        for (i in 0..<rows) {
            array[i] = strings[i].toCharArray()
        }
        val horizontalRows: MutableList<Int> = findEmptyRows(array)
        val rotatedArray: Array<CharArray> = rotateClockwise(array)
        val verticalRows: MutableList<Int> = findEmptyRows(rotatedArray)

        val result: BigInteger = calculateDistance(array, horizontalRows, verticalRows, 999_999)
        println(result)


    }

    part1()
    part2()

}







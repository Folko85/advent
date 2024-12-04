package year2024

import java.io.File

fun main() {


    fun checkXmas(
        array: Array<Array<String>>,
        i: Int,
        j: Int,
        direction: Map.Entry<String, Pair<Int, Int>>
    ): Int {
        val oneX = i + direction.value.first
        val oneY = j + direction.value.second
        val twoX = oneX + direction.value.first
        val twoY = oneY + direction.value.second
        val endX = twoX + direction.value.first
        val endY = twoY + direction.value.second

        if (endX < 0 || endX >= array.size || endY < 0 || endY >= array[0].size) {
            return 0
        } else if (array[oneX][oneY] == "M" && array[twoX][twoY] == "A" && array[endX][endY] == "S") {
            return 1
        }
        return 0
    }

    fun part1() {
        val directions: Map<String, Pair<Int, Int>> =
            mapOf(
                "U" to Pair(0, -1),
                "D" to Pair(0, 1),
                "L" to Pair(-1, 0),
                "R" to Pair(1, 0),
                "UL" to Pair(-1, -1),
                "UR" to Pair(1, -1),
                "DL" to Pair(-1, 1),
                "DR" to Pair(1, 1)
            )
        val strings: List<String> = File("src/main/resources/year2024/day4_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array: Array<Array<String>> = Array(rows) { Array(cols) { "" } }
        for (i in 0..<rows) {
            val row: String = strings[i]
            for (j in 0..<cols) {
                array[i][j] = (row[j].toString())
            }
        }

        var sum = 0

        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (array[i][j] == "X") {
                    directions.forEach {
                        sum += checkXmas(array, i, j, it)
                    }
                }
            }
        }
        println(sum)


    }
    part1()

}





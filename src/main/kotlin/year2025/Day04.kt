package year2025

import java.io.File

fun main() {

    val directions: Set<Pair<Int, Int>> =
        setOf(
            Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0),
            Pair(-1, -1), Pair(1, 1), Pair(-1, 1), Pair(1, -1)
        )

    fun isPositionAvailable(i: Int, j: Int, array: Array<Array<String>>): Boolean {
        var counter = 0
        directions.forEach { direction ->
            val newI = i + direction.first
            val newJ = j + direction.second
            if (newI >= 0 && newI < array.size && newJ >= 0 && newJ < array[0].size) {
                counter += if (array[newI][newJ] == "@") 1 else 0
            }
        }
        return counter < 4
    }


    fun part1() {
        val strings: List<String> = File("src/main/resources/year2025/day4_input.txt").bufferedReader().readLines()
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
                if (array[i][j] == "@" && isPositionAvailable(i, j, array)) {
                    sum++
                }
            }
        }
        println(sum)

    }


    fun part2() {
    }

    part1()
    part2()


}





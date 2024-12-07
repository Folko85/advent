package year2024

import java.io.File

fun main() {

    val directions: Map<String, Pair<Int, Int>> =
        mapOf(">" to Pair(0, 1), "<" to Pair(0, -1), "v" to Pair(1, 0), "^" to Pair(-1, 0))
    val changeDirection: Map<String, String> = mapOf(">" to "v", "v" to "<", "<" to "^", "^" to ">")

    fun walkInDirection(startPoint: Pair<Int, Int>, direction: String, array: Array<Array<String>>) {
        var currentDirection = direction
        var currentPoint = startPoint
        while (true) {
            array[currentPoint.first][currentPoint.second] = "X"
            val shift: Pair<Int, Int> = directions[currentDirection]!!
            val newPoint: Pair<Int, Int> = Pair(currentPoint.first + shift.first, currentPoint.second + shift.second)
            if (newPoint.first < 0 || newPoint.first >= array.size || newPoint.second < 0 || newPoint.second >= array[0].size) {
                break
            } else if (array[newPoint.first][newPoint.second] == "#") {
                currentDirection = changeDirection[currentDirection]!!
            } else {
                currentPoint = newPoint
            }
        }

    }

    fun part1() {
        var startPoint = Pair(0, 0)
        val strings: List<String> = File("src/main/resources/year2024/day6_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array: Array<Array<String>> = Array(rows) { Array(cols) { "" } }
        for (i in 0..<rows) {
            val row: String = strings[i]
            for (j in 0..<cols) {
                array[i][j] = (row[j].toString())
                if (array[i][j] in directions.keys) {
                    startPoint = Pair(i, j)
                }
            }
        }

        val direction: String = array[startPoint.first][startPoint.second]

        walkInDirection(startPoint, direction, array)

        var sum = 0

        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (array[i][j] == "X") {
                    sum++
                }
            }
        }
        println(sum)
    }

    part1()

}





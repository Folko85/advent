package year2024

import java.io.File

fun main() {

    val directions: List<Pair<Int, Int>> =
        listOf(
            Pair(0, -1),
            Pair(0, 1),
            Pair(-1, 0),
            Pair(1, 0)
        )

    fun fillRoutes(array: Array<Array<Int>>, i: Int, j: Int, nextTop: Int, routes: MutableSet<Pair<Int, Int>>) {
        directions.forEach { direction ->
            run {
                val nextI: Int = i + direction.first
                val nextJ: Int = j + direction.second
                if (nextI < 0 || nextI >= array.size || nextJ < 0 || nextJ >= array[0].size) {
                    print("")
                } else if (nextTop == 9 && array[nextI][nextJ] == 9) {
                    routes.add(Pair(nextI, nextJ))
                } else if (array[nextI][nextJ] == nextTop) {
                    fillRoutes(array, nextI, nextJ, nextTop + 1, routes)
                }
            }
        }
    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/year2024/day10_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array: Array<Array<Int>> = Array(rows) { Array(cols) { 0 } }
        for (i in 0..<rows) {
            val row: String = strings[i]
            for (j in 0..<cols) {
                array[i][j] = (row[j].digitToInt())
            }
        }

        var sum = 0

        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (array[i][j] == 0) {
                    val routes: MutableSet<Pair<Int, Int>> = mutableSetOf()
                    fillRoutes(array, i, j, 1, routes)
                    sum += routes.size
                }
            }
        }
        println(sum)

    }

    part1()

}





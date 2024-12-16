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

    fun findArea(
        i: Int,
        j: Int,
        array: Array<Array<String>>,
        borderArray: Array<Array<Int>>,
        plants: MutableSet<Pair<Int, Int>>
    ) {
        if (plants.contains(Pair(i, j))) {
            return
        }
        plants.add(Pair(i, j))
        val plant = array[i][j]
        directions.forEach { direction ->
            run {
                val nextI: Int = i + direction.first
                val nextJ: Int = j + direction.second
                if (nextI < 0 || nextI >= array.size || nextJ < 0 || nextJ >= array[0].size) {
                    val currentBorder = borderArray[i][j]
                    borderArray[i][j] = currentBorder + 1
                } else if (array[nextI][nextJ] == plant) {
                    findArea(nextI, nextJ, array, borderArray, plants)
                } else {
                    val currentBorder = borderArray[i][j]
                    borderArray[i][j] = currentBorder + 1
                }
            }
        }
    }

    fun calculateArea(plants: MutableSet<Pair<Int, Int>>, borderArray: Array<Array<Int>>): Long {
        val square = plants.size
        var perimeter = 0L
        plants.forEach { plant ->
            run {
                perimeter += borderArray[plant.first][plant.second]
            }
        }
        return square * perimeter
    }

    fun part1() {
        val checked: MutableList<Pair<Int, Int>> = mutableListOf()
        val strings: List<String> = File("src/main/resources/year2024/day12_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array: Array<Array<String>> = Array(rows) { Array(cols) { "" } }
        val borderArray: Array<Array<Int>> = Array(rows) { Array(cols) { 0 } }
        for (i in 0..<rows) {
            val row: String = strings[i]
            for (j in 0..<cols) {
                array[i][j] = (row[j].toString())
            }
        }

        var sum = 0L

        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (!checked.contains(Pair(i, j))) {
                    val plants: MutableSet<Pair<Int, Int>> = mutableSetOf()
                    findArea(i, j, array, borderArray, plants)
                    checked.addAll(plants)
                    sum += calculateArea(plants, borderArray)
                }
            }
        }
        println(sum)
    }

    part1()


}





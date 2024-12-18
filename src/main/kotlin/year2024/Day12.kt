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

    val directionsMap: Map<String, Pair<Int, Int>> =
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

    fun checkCorner(
        horizontalPoint: Pair<Int, Int>,
        centerPoint: Pair<Int, Int>,
        verticalPoint: Pair<Int, Int>,
        letter: String,
        array: Array<Array<String>>
    ): Long {
        val centerOutside =
            centerPoint.first < 0 || centerPoint.first >= array.size || centerPoint.second < 0 || centerPoint.second >= array[0].size
        val horizontalOutside =
            horizontalPoint.first < 0 || horizontalPoint.first >= array.size || horizontalPoint.second < 0 || horizontalPoint.second >= array[0].size
        val verticalOutside =
            verticalPoint.first < 0 || verticalPoint.first >= array.size || verticalPoint.second < 0 || verticalPoint.second >= array[0].size
        if ((horizontalOutside || array[horizontalPoint.first][horizontalPoint.second] != letter)
            && (verticalOutside || array[verticalPoint.first][verticalPoint.second] != letter)
        ) {
            return 1
        }
        if ((centerOutside || array[centerPoint.first][centerPoint.second] != letter)
            && (!horizontalOutside && array[horizontalPoint.first][horizontalPoint.second] == letter)
            && (!verticalOutside && array[verticalPoint.first][verticalPoint.second] == letter)
        ) {
            return 1
        }
        return 0
    }

    fun checkPoint(point: Pair<Int, Int>, letter: String, array: Array<Array<String>>): Long {
        var result = 0L
        val upPoint = Pair(point.first + directionsMap["U"]!!.first, point.second + directionsMap["U"]!!.second)
        val downPoint = Pair(point.first + directionsMap["D"]!!.first, point.second + directionsMap["D"]!!.second)
        val leftPoint = Pair(point.first + directionsMap["L"]!!.first, point.second + directionsMap["L"]!!.second)
        val rightPoint = Pair(point.first + directionsMap["R"]!!.first, point.second + directionsMap["R"]!!.second)
        val upLeftPoint = Pair(point.first + directionsMap["UL"]!!.first, point.second + directionsMap["UL"]!!.second)
        val upRightPoint = Pair(point.first + directionsMap["UR"]!!.first, point.second + directionsMap["UR"]!!.second)
        val downLeftPoint = Pair(point.first + directionsMap["DL"]!!.first, point.second + directionsMap["DL"]!!.second)
        val downRightPoint = Pair(point.first + directionsMap["DR"]!!.first, point.second + directionsMap["DR"]!!.second)
        result += checkCorner(leftPoint, upLeftPoint, upPoint, letter, array)
        result += checkCorner(upPoint, upRightPoint, rightPoint, letter, array)
        result += checkCorner(rightPoint, downRightPoint, downPoint, letter, array)
        result += checkCorner(downPoint, downLeftPoint, leftPoint, letter, array)
        return result
    }

    fun calculateCounters(plants: MutableSet<Pair<Int, Int>>, array: Array<Array<String>>): Long {
        var corners = 0L
        plants.forEach {
            val letter = array[it.first][it.second]
            corners += checkPoint(it, letter, array)
        }
        return corners * plants.size
    }

    fun part2() {
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
                    sum += calculateCounters(plants, array)
                }
            }
        }
        println(sum)
    }

    part1()
    part2()


}





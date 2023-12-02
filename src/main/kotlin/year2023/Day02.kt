package year2023

import java.io.File

fun main() {

    val maxRedCubes = 12
    val maxGreenCubes = 13
    val maxBlueCubes = 14

    fun analizeString(input: String): Long {
        var isPossible = true
        val game: List<String> = input.split(":")
        val gameNumber: Long = game[0].trim().split(" ")[1].toLong()
        val rounds: List<String> = game[1].split(";")
        rounds.forEach { round ->
            val colors: List<String> = round.trim().split(",")
            colors.forEach { color ->
                val quantity: Int = color.filter { it.isDigit() }.toInt()
                if (color.contains("red") && quantity > maxRedCubes) {
                    isPossible = false
                } else if (color.contains("blue") && quantity > maxBlueCubes) {
                    isPossible = false
                } else if (color.contains("green") && quantity > maxGreenCubes) {
                    isPossible = false
                }
            }
        }
        return if (isPossible) gameNumber else 0
    }

    fun findPower(input: String): Long {
        var maxRedCubesLocal: Long = 0
        var maxGreenCubesLocal: Long = 0
        var maxBlueCubesLocal: Long = 0
        val game: List<String> = input.split(":")
        val rounds: List<String> = game[1].split(";")
        rounds.forEach { round ->
            val colors: List<String> = round.trim().split(",")
            colors.forEach { color ->
                val quantity: Long = color.filter { it.isDigit() }.toLong()
                if (color.contains("red")) {
                    maxRedCubesLocal = if (quantity > maxRedCubesLocal) quantity else maxRedCubesLocal
                } else if (color.contains("blue")) {
                    maxBlueCubesLocal = if (quantity > maxBlueCubesLocal) quantity else maxBlueCubesLocal
                } else if (color.contains("green")) {
                    maxGreenCubesLocal = if (quantity > maxGreenCubesLocal) quantity else maxGreenCubesLocal
                }
            }
        }
        return maxBlueCubesLocal * maxGreenCubesLocal * maxRedCubesLocal
    }

    fun part1() {
        var sum: Long = 0
        val strings: List<String> = File("src/main/resources/2023_day_2_input.txt").bufferedReader().readLines()
        strings.forEach {
            val value: Long = analizeString(it)
            sum += value
        }
        println(sum)
    }

    fun part2() {
        var sum: Long = 0
        val strings: List<String> = File("src/main/resources/2023_day_2_input.txt").bufferedReader().readLines()
        strings.forEach {
            val value: Long = findPower(it)
            sum += value
        }
        println(sum)
    }

    part1()
    part2()


}




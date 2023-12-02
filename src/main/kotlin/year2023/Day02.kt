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

    fun part1() {
        var sum: Long = 0
        val strings: List<String> = File("src/main/resources/2023_day_2_input.txt").bufferedReader().readLines()
        strings.forEach {
            val value: Long = analizeString(it)
            sum += value
        }
        println(sum)
    }

//    fun part2() {
//        val strings: List<String> = File("src/main/resources/2023_day_2_example2.txt").bufferedReader().readLines()
//
//    }

    part1()
//    part2()


}




package year2024

import java.io.File

fun main() {

    fun part1() {
        val multiply: Regex = "mul\\((\\d{1,3}),(\\d{1,3}\\))".toRegex()
        val digits: Regex = "\\d+".toRegex()
        val input: List<String> = File("src/main/resources/year2024/day3_input.txt").bufferedReader().readLines()
        var sum = 0L
        input.forEach { string ->
            multiply.findAll(string).forEach { matchResult ->
                run {
                    val multiplies: List<String> = matchResult.value.split(",".toRegex())
                    val numbers: List<Long> = multiplies.map { digits.find(it)!!.value.toLong() }
                    sum += numbers[0] * numbers[1]
                }
            }
        }
        println(sum)
    }


    part1()

}





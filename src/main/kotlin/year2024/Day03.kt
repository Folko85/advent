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

    fun part2() {
        val multiply: Regex = "mul\\((\\d{1,3}),(\\d{1,3}\\))|do\\(\\)|don't\\(\\)".toRegex()
        val digits: Regex = "\\d+".toRegex()
        val input: List<String> = File("src/main/resources/year2024/day3_input.txt").bufferedReader().readLines()
        var sum = 0L
        var enabled = true
        input.forEach { string ->
            multiply.findAll(string).forEach { matchResult ->
                run {
                    if (matchResult.value == "do()"){
                        enabled = true
                    } else if (matchResult.value == "don't()"){
                        enabled = false
                    } else {
                        val multiplies: List<String> = matchResult.value.split(",".toRegex())
                        val numbers: List<Long> = multiplies.map { digits.find(it)!!.value.toLong() }
                        if (enabled){
                            sum += numbers[0] * numbers[1]
                        }

                    }

                }
            }
        }
        println(sum)
    }


    part1()
    part2()

}





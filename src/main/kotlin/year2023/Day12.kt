package year2023

import java.io.File
import kotlin.math.pow

fun main() {

    fun toRegEx(ns: List<Int>): Regex {
        val hashes = ns.joinToString(separator = "([.]+)") { "([#]{$it})" }
        return Regex("([.]*)$hashes([.]*)")
    }

    fun getVariants(key: String): List<String> {
        val result: MutableList<String> = mutableListOf()
        val questionCount = key.filter { it == '?' }.length
        val questionsCounter = 2.0.pow(questionCount).toInt()

        val variants: MutableList<String> = mutableListOf()
        for (i in 0..<questionsCounter) {
            val variant = i.toString(2).padStart(questionCount, '0')
                .replace("0", ".").replace("1", "#")
            variants.add(variant)
        }

        variants.forEach { variant ->
            var charCounter = 0
            val resultString = CharArray(key.length)
            for (j in key.indices) {
                if (key[j] != '?') {
                    resultString[j] = key[j]
                } else {
                    resultString[j] = variant[charCounter]
                    charCounter++
                }
            }
            result.add(resultString.joinToString(""))
        }

        return result
    }

    fun calculateVariants(input: Map.Entry<String, List<Int>>): Int {
        val mask = toRegEx(input.value)
        val variants: List<String> = getVariants(input.key)
        return variants.count { it.matches(mask) }

    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_12_input.txt").bufferedReader().readLines()
        val map: MutableMap<String, List<Int>> = mutableMapOf()
        strings.forEach { string ->
            val value: List<String> = string.split("\\s+".toRegex())
            val numbers: List<Int> = value[1].split(",").map { it.trim().toInt() }
            map[value[0]] = numbers
        }
        var sum = 0L
        map.forEach {
            sum += calculateVariants(it)
        }

        println(sum)
    }

//    fun part2() {
//        val strings: List<String> = File("src/main/resources/2023_day_11_input.txt").bufferedReader().readLines()
//    }

    part1()
//    part2()

}






package year2024

import java.io.File
import java.math.BigInteger

fun main() {

    fun checkExpression(expectedResult: BigInteger, actualResult: BigInteger, parts: List<BigInteger>, iteration: Int): Boolean {
        if (iteration == parts.size || expectedResult < actualResult) return expectedResult == actualResult
        if (checkExpression(expectedResult, actualResult + parts[iteration], parts, iteration + 1)) {
            return true
        }
        if (checkExpression(expectedResult, actualResult * parts[iteration], parts, iteration + 1)) {
            return true
        }
        return false
    }

    fun advancedCheckExpression(
        expectedResult: BigInteger,
        actualResult: BigInteger,
        parts: List<BigInteger>,
        iteration: Int
    ): Boolean {
        if (iteration == parts.size || expectedResult < actualResult) return expectedResult == actualResult
        if (advancedCheckExpression(expectedResult, actualResult + parts[iteration], parts, iteration + 1)) {
            return true
        }
        if (advancedCheckExpression(expectedResult, actualResult * parts[iteration], parts, iteration + 1)) {
            return true
        }
        if (advancedCheckExpression(
                expectedResult,
                BigInteger(actualResult.toString() + parts[iteration].toString()),
                parts,
                iteration + 1
            )
        ) {
            return true
        }

        return false
    }


    fun part1() {
        val strings: List<String> = File("src/main/resources/year2024/day7_input.txt").bufferedReader().readLines()
        var sum = BigInteger.ZERO
        strings.forEach { string ->
            val splitString: List<String> = string.split(":".toRegex())
            val result: BigInteger = splitString[0].trim().toBigInteger()
            val parts: List<BigInteger> = splitString[1].trim().split("\\s+".toRegex()).map { it.trim().toBigInteger() }
            sum = if (checkExpression(result, parts[0], parts, 1)) sum.add(result) else sum.add(BigInteger.ZERO)
        }
        println(sum)
    }

    fun part2() {
        val strings: List<String> = File("src/main/resources/year2024/day7_input.txt").bufferedReader().readLines()
        var sum = BigInteger.ZERO
        strings.forEach { string ->
            val splitString: List<String> = string.split(":".toRegex())
            val result: BigInteger = splitString[0].trim().toBigInteger()
            val parts: List<BigInteger> = splitString[1].trim().split("\\s+".toRegex()).map { it.trim().toBigInteger() }
            sum = if (advancedCheckExpression(result, parts[0], parts, 1)) sum.add(result) else sum.add(BigInteger.ZERO)
        }
        println(sum)
    }


    part1()
    part2()

}





package year2025

import java.io.File

fun main() {

    fun part1() {
        var result = 0
        val batteries: List<String> = File("src/main/resources/year2025/day3_input.txt").bufferedReader().readLines()
        batteries.forEach { battery ->
            var firstDigit = 0
            var firstPosition = 0
            for (i in 0..<battery.length - 1) {
                val currentDigit = battery[i].toString().toInt()
                if (currentDigit > firstDigit) {
                    firstDigit = currentDigit
                    firstPosition = i
                }
            }
            var secondDigit = 0
            for (j in firstPosition + 1..<battery.length) {
                val currentDigit = battery[j].toString().toInt()
                secondDigit = if (currentDigit > secondDigit) currentDigit else secondDigit
            }
            result += firstDigit * 10 + secondDigit
        }
        println(result)
    }

    fun part2() {
        var result = 0L
        val batteries: List<String> = File("src/main/resources/year2025/day3_input.txt").bufferedReader().readLines()
        batteries.forEach { battery ->
            var firstDigit = 0
            var firstPosition = 0
            for (i in 0..<battery.length - 11) {
                val currentDigit = battery[i].toString().toInt()
                if (currentDigit > firstDigit) {
                    firstDigit = currentDigit
                    firstPosition = i
                }
            }
            var secondDigit = 0
            var secondPosition = firstPosition + 1
            for (j in firstPosition + 1..<battery.length - 10) {
                val currentDigit = battery[j].toString().toInt()
                if (currentDigit > secondDigit) {
                    secondDigit = currentDigit
                    secondPosition = j
                }
            }

            var thirdDigit = 0
            var thirdPosition = secondPosition + 1
            for (k in secondPosition + 1..<battery.length - 9) {
                val currentDigit = battery[k].toString().toInt()
                if (currentDigit > thirdDigit) {
                    thirdDigit = currentDigit
                    thirdPosition = k
                }
            }

            var fourDigit = 0
            var fourPosition = thirdPosition + 1
            for (l in thirdPosition + 1..<battery.length - 8) {
                val currentDigit = battery[l].toString().toInt()
                if (currentDigit > fourDigit) {
                    fourDigit = currentDigit
                    fourPosition = l
                }
            }

            var fiveDigit = 0
            var fivePosition = fourPosition + 1
            for (m in fourPosition + 1..<battery.length - 7) {
                val currentDigit = battery[m].toString().toInt()
                if (currentDigit > fiveDigit) {
                    fiveDigit = currentDigit
                    fivePosition = m
                }
            }

            var sixDigit = 0
            var sixPosition = fivePosition + 1
            for (n in fivePosition + 1..<battery.length - 6) {
                val currentDigit = battery[n].toString().toInt()
                if (currentDigit > sixDigit) {
                    sixDigit = currentDigit
                    sixPosition = n
                }
            }

            var sevenDigit = 0
            var sevenPosition = sixPosition + 1
            for (o in sixPosition + 1..<battery.length - 5) {
                val currentDigit = battery[o].toString().toInt()
                if (currentDigit > sevenDigit) {
                    sevenDigit = currentDigit
                    sevenPosition = o
                }
            }

            var eightDigit = 0
            var eightPosition = sevenPosition + 1
            for (p in sevenPosition + 1..<battery.length - 4) {
                val currentDigit = battery[p].toString().toInt()
                if (currentDigit > eightDigit) {
                    eightDigit = currentDigit
                    eightPosition = p
                }
            }

            var nineDigit = 0
            var ninePosition = eightPosition + 1
            for (q in eightPosition + 1..<battery.length - 3) {
                val currentDigit = battery[q].toString().toInt()
                if (currentDigit > nineDigit) {
                    nineDigit = currentDigit
                    ninePosition = q
                }
            }

            var tenDigit = 0
            var tenPosition = ninePosition + 1
            for (r in ninePosition + 1..<battery.length - 2) {
                val currentDigit = battery[r].toString().toInt()
                if (currentDigit > tenDigit) {
                    tenDigit = currentDigit
                    tenPosition = r
                }
            }

            var elevenDigit = 0
            var elevenPosition = tenPosition + 1
            for (s in tenPosition + 1..<battery.length - 1) {
                val currentDigit = battery[s].toString().toInt()
                if (currentDigit > elevenDigit) {
                    elevenDigit = currentDigit
                    elevenPosition = s
                }
            }

            var twelveDigit = 0
            for (t in elevenPosition + 1..<battery.length) {
                val currentDigit = battery[t].toString().toInt()
                twelveDigit = if (currentDigit > twelveDigit) currentDigit else twelveDigit
            }

            result += listOf(
                firstDigit,
                secondDigit,
                thirdDigit,
                fourDigit,
                fiveDigit,
                sixDigit,
                sevenDigit,
                eightDigit,
                nineDigit,
                tenDigit,
                elevenDigit,
                twelveDigit
            ).joinToString(separator = "").toLong()
        }
        println(result)
    }

    part1()
    part2()
}
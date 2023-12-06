package year2023

import java.io.File

fun main() {


    fun findRacesNumbers(race: Pair<Long, Long>): Long {
        val time: Long = race.first
        val record: Long = race.second
        var counter: Long = 0
        for (i in 1..<time) {
            val raceResult: Long = (time - i) * i
            if (raceResult > record) {
                counter++
            }
        }
        return counter
    }

    fun part1() {
        var sum = 1L
        val strings: List<String> = File("src/main/resources/2023_day_6_input.txt").bufferedReader().readLines()
        val times: List<String> = strings[0].split("\\s+".toRegex())
        val distances: List<String> = strings[1].split("\\s+".toRegex())
        val pairs: MutableList<Pair<Long, Long>> = mutableListOf()
        for (i in 1..<times.size) {
            pairs.add(Pair(times[i].toLong(), distances[i].toLong()))
        }
        pairs.forEach {
            val numbersRace: Long = findRacesNumbers(it)
            sum *= numbersRace
        }
        println(sum)
    }

//    fun part2() {
//        val strings: List<String> = File("src/main/resources/2023_day_5_input.txt").bufferedReader().readLines()
//
//    }

    part1()
//    part2()

}




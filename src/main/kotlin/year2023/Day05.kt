package year2023

import java.io.File

fun main() {

    fun findNext(listOfPair: MutableList<Pair<LongProgression, LongProgression>>, sourceIndex: Long): Long {
        var resultIndex: Long = -1L
        listOfPair.forEach { progressionPair ->
            if (progressionPair.first.contains(sourceIndex)) {
                var counter: Long = 0L
                for (i in progressionPair.first.first..progressionPair.first.last) {
                    if (i == sourceIndex) {
                        break
                    }
                    counter++
                }
                for (j in progressionPair.second.first..progressionPair.second.last) {
                    if (counter == 0L) {
                        resultIndex = j
                        break
                    }
                    counter--
                }
            }
        }
        if (resultIndex == -1L) {
            resultIndex = sourceIndex
        }
        return resultIndex
    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_5_input.txt").bufferedReader().readLines()
        var lowestSeed: Long = Long.MAX_VALUE
        var seeds: List<Long> = listOf()
        var currentMap: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        var seedToSoil: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        var soilToFertilizer: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        var fertilizerToWater: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        var waterToLight: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        var lightToTemperature: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        var temperatureToHumidity: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        var humidityToLocation: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        strings.forEach { string ->
            if (string.contains("seeds")) {
                seeds = string.split(":")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
            } else if (string.contains("seed-to-soil")) {
                currentMap = seedToSoil
            } else if (string.contains("soil-to-fertilizer")) {
                currentMap = soilToFertilizer
            } else if (string.contains("fertilizer-to-water")) {
                currentMap = fertilizerToWater
            } else if (string.contains("water-to-light")) {
                currentMap = waterToLight
            } else if (string.contains("light-to-temperature")) {
                currentMap = lightToTemperature
            } else if (string.contains("temperature-to-humidity")) {
                currentMap = temperatureToHumidity
            } else if (string.contains("humidity-to-location")) {
                currentMap = humidityToLocation
            } else if (string.isNotBlank()) {
                val progressionData: List<String> = string.trim().split("\\s+".toRegex())
                val sourceStart = progressionData[1].toLong()
                val targetStart = progressionData[0].toLong()
                val length = progressionData[2].toLong()
                val sourceProgression: LongProgression = sourceStart..<sourceStart + length
                val targetProgression: LongProgression = targetStart..<targetStart + length
                currentMap.add(Pair(sourceProgression, targetProgression))
            }
        }
        seeds.forEach { seed ->
            val soilIndex: Long = findNext(seedToSoil, seed)
            val fertilizerIndex: Long = findNext(soilToFertilizer, soilIndex)
            val waterIndex: Long = findNext(fertilizerToWater, fertilizerIndex)
            val lightIndex: Long = findNext(waterToLight, waterIndex)
            val temperatureIndex: Long = findNext(lightToTemperature, lightIndex)
            val humidityIndex: Long = findNext(temperatureToHumidity, temperatureIndex)
            val location: Long = findNext(humidityToLocation, humidityIndex)
            lowestSeed = if (lowestSeed < location) lowestSeed else location

        }
        println(lowestSeed)
    }

    fun part2() {

    }

    part1()
//    part2()

}




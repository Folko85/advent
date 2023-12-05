package year2023

import java.io.File

fun main() {

    fun findNext(listOfPair: MutableList<Pair<LongProgression, LongProgression>>, sourceIndex: Long): Long {
        var resultIndex: Long = -1L
        for (i in listOfPair.indices) {
            val progressionPair = listOfPair[i]
            if (progressionPair.first.first <= sourceIndex && sourceIndex <= progressionPair.first.last) {
                val counter = sourceIndex - progressionPair.first.first
                resultIndex = progressionPair.second.first + counter

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
        val seedToSoil: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val soilToFertilizer: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val fertilizerToWater: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val waterToLight: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val lightToTemperature: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val temperatureToHumidity: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val humidityToLocation: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
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
        val strings: List<String> = File("src/main/resources/2023_day_5_input.txt").bufferedReader().readLines()
        var lowestSeed: Long = Long.MAX_VALUE
        val seeds: MutableList<LongProgression> = mutableListOf()
        var currentMap: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val seedToSoil: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val soilToFertilizer: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val fertilizerToWater: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val waterToLight: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val lightToTemperature: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val temperatureToHumidity: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        val humidityToLocation: MutableList<Pair<LongProgression, LongProgression>> = mutableListOf()
        strings.forEach { string ->
            if (string.contains("seeds")) {
                val seedsData = string.split(":")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
                var startSeed: Long = 0
                var period: Long
                for (i in seedsData.indices) {
                    if (i % 2 == 0) {
                        startSeed = seedsData[i]
                    } else {
                        period = seedsData[i]
                        val progression: LongProgression = startSeed..<startSeed + period
                        seeds.add(progression)
                    }
                }

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
            for (i in seed.first..seed.last) {
                val soilIndex: Long = findNext(seedToSoil, i)
                val fertilizerIndex: Long = findNext(soilToFertilizer, soilIndex)
                val waterIndex: Long = findNext(fertilizerToWater, fertilizerIndex)
                val lightIndex: Long = findNext(waterToLight, waterIndex)
                val temperatureIndex: Long = findNext(lightToTemperature, lightIndex)
                val humidityIndex: Long = findNext(temperatureToHumidity, temperatureIndex)
                val location: Long = findNext(humidityToLocation, humidityIndex)
                lowestSeed = if (lowestSeed < location) lowestSeed else location
            }
        }
        println(lowestSeed)
    }

    part1()
    part2()

}




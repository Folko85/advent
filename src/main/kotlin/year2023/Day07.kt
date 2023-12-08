package year2023

import java.io.File
import java.util.stream.IntStream

fun main() {

    fun part1() {
        val map: Map<Char, Int> = mapOf(
            '2' to 1,
            '3' to 2,
            '4' to 3,
            '5' to 4,
            '6' to 5,
            '7' to 6,
            '8' to 7,
            '9' to 8,
            'T' to 9,
            'J' to 10,
            'Q' to 11,
            'K' to 12,
            'A' to 13
        )
        val strings: List<String> = File("src/main/resources/2023_day_7_input.txt").bufferedReader().readLines()
        val hands: List<Hand> = strings
            .map { l: String ->
                val cardsBid = l.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                Hand(cardsBid[0], analyzeHand(cardsBid[0]), cardsBid[1].toLong(), map)
            }
            .sorted()
            .toList()

        var bidSum: Long = 0
        for (i in hands.indices) {
            bidSum += hands[i].bid * (i + 1)
        }
        println(bidSum)
    }

    fun part2() {
        val map: Map<Char, Int> = mapOf(
            'J' to 0,
            '2' to 1,
            '3' to 2,
            '4' to 3,
            '5' to 4,
            '6' to 5,
            '7' to 6,
            '8' to 7,
            '9' to 8,
            'T' to 9,
            'Q' to 11,
            'K' to 12,
            'A' to 13
        )
        val invertMap: Map<Int, String> = mapOf(
            0 to "J",
            1 to "2",
            2 to "3",
            3 to "4",
            4 to "5",
            5 to "6",
            6 to "7",
            7 to "8",
            8 to "9",
            9 to "T",
            10 to "Q",
            11 to "K",
            12 to "A"
        )

        val strings: List<String> = File("src/main/resources/2023_day_7_input.txt").bufferedReader().readLines()
        val hands: List<Hand> = strings
            .map { l: String ->
                val cardsBid = l.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                Hand(cardsBid[0], findType(cardsBid[0], invertMap), cardsBid[1].toLong(), map)
            }
            .sorted()
            .toList()

        var bidSum: Long = 0
        for (i in hands.indices) {
            bidSum += hands[i].bid * (i + 1)
        }
        println(bidSum)
    }

    part1()
    part2()

}

fun analyzeHand(hand: String): HandType {
    val cardCounts: MutableMap<Char, Int> = HashMap()
    for (card in hand.toCharArray()) {
        cardCounts[card] = cardCounts.getOrDefault(card, 0) + 1
    }

    var threeOfKind = false
    var pair = false
    var pairs = 0

    for (count in cardCounts.values) {
        if (count == 5) {
            return HandType.FIVE_OF_A_KIND
        } else if (count == 4) {
            return HandType.FOUR_OF_A_KIND
        } else if (count == 3) {
            threeOfKind = true
        } else if (count == 2) {
            pair = true
            pairs++
        }
    }

    return if (threeOfKind && pair) {
        HandType.FULL_HOUSE
    } else if (threeOfKind) {
        HandType.THREE_OF_A_KIND
    } else if (pairs == 2) {
        HandType.TWO_PAIR
    } else if (pair) {
        HandType.ONE_PAIR
    } else {
        HandType.HIGH_CARD
    }
}

private fun findType(cards: String, invertMap: Map<Int, String>): HandType {
    return IntStream.rangeClosed(0, 12)
        .mapToObj { i: Int ->
            analyzeHand(
                cards.replace(
                    "J".toRegex(),
                    invertMap[i]!!
                )
            )
        }
        .min(Comparator.comparingInt { obj: HandType -> obj.ordinal })
        .orElse(HandType.HIGH_CARD)
}


enum class HandType {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD
}

internal data class Hand(val cards: String, val handType: HandType, val bid: Long, val map: Map<Char, Int>) : Comparable<Hand> {

    override fun compareTo(other: Hand): Int {
        val typeCompare = other.handType.ordinal.toLong().compareTo(handType.ordinal.toLong())

        if (typeCompare == 0) {
            for (i in 0..<other.cards.length) {
                val charCompare = map[cards[i]]!!.toLong().compareTo(map[other.cards[i]]!!.toLong())
                if (charCompare != 0) {
                    return charCompare
                }
            }
        }

        return typeCompare
    }
}

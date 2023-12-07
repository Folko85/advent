package year2023

import java.io.File

fun main() {

    val strings: List<String> = File("src/main/resources/2023_day_7_input.txt").bufferedReader().readLines()
    val hands: List<Hand> = strings
        .map { l: String ->
            val cardsBid = l.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            Hand(cardsBid[0], analyzeHand(cardsBid[0]), cardsBid[1].toLong())
        }
        .sorted()
        .toList()

    var bidSum: Long = 0
    for (i in hands.indices) {
        bidSum += hands[i].bid * (i + 1)
    }
    println(bidSum)

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


enum class HandType {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD
}

internal data class Hand(val cards: String, val handType: HandType, val bid: Long) : Comparable<Hand> {

    private val mutableMap: MutableMap<Char, Int> = HashMap(13)

    init {
        mutableMap['2'] = 1
        mutableMap['3'] = 2
        mutableMap['4'] = 3
        mutableMap['5'] = 4
        mutableMap['6'] = 5
        mutableMap['7'] = 6
        mutableMap['8'] = 7
        mutableMap['9'] = 8
        mutableMap['T'] = 9
        mutableMap['J'] = 10
        mutableMap['Q'] = 11
        mutableMap['K'] = 12
        mutableMap['A'] = 13
    }

    override fun compareTo(o: Hand): Int {
        val typeCompare = o.handType.ordinal.toLong().compareTo(handType.ordinal.toLong())

        if (typeCompare == 0) {
            for (i in 0..<o.cards.length) {
                val charCompare = mutableMap[cards[i]]!!.toLong().compareTo(mutableMap[o.cards[i]]!!.toLong())
                if (charCompare != 0) {
                    return charCompare
                }
            }
        }

        return typeCompare
    }
}

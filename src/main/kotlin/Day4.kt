fun main () {
    val overlaps = getOverlaps(readInput("day4/overlaps"))
    val overlapCount = overlaps.count {
        it.first.containsRange(it.second) || it.second.containsRange(it.first)
    }
    println(overlapCount)

    val overlaps2 = overlaps.count {
        it.first.overlapsRange(it.second)
    }
    println(overlaps2)

}

fun IntRange.containsRange(range: IntRange): Boolean {
    return this.first <= range.first && this.last >= range.last
}

fun IntRange.overlapsRange(range: IntRange): Boolean {
    return (this.last >= range.first && range.last >= this.first) ||
            (range.last >= this.first && this.last >= range.first)
}

fun getOverlaps(input: List<String>): List<Pair<IntRange, IntRange>> {
    return input.map {line ->
        val intRanges = line.split(",").map {
            val indexes = it.split("-")
            IntRange(indexes[0].toInt(), indexes[1].toInt())

        }
        Pair(intRanges[0], intRanges[1])
    }
}



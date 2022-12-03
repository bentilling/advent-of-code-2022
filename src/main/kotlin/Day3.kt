import kotlin.math.floor
import kotlin.math.round

const val LOWERCASE_A_ASCII = 97
const val LOWERCASE_Z_ASCII = 122
const val UPPERCASE_A_ASCII = 65
const val UPPERCASE_Z_ASCII = 90
const val BASE_PRIORITY_LOWERCASE = 1
const val BASE_PRIORITY_UPPERCASE = 27

fun main () {
    val rucksack = readInput("day3/rucksack")
//    val wrongItems = getWrongItemsTask1(rucksack)
//    println(getPriorities(wrongItems).sum())
    val groupItems = getMatchingItemTask2(rucksack)
    println(getPriorities(groupItems).sum())
}

fun getPriorityForLetter(char: Char): Int {

    val ascii = char.code
    val priority: Int
    if (ascii in UPPERCASE_A_ASCII..UPPERCASE_Z_ASCII) {
        priority = ascii - UPPERCASE_A_ASCII + BASE_PRIORITY_UPPERCASE
    } else if (ascii in LOWERCASE_A_ASCII..LOWERCASE_Z_ASCII) {
        priority = ascii - LOWERCASE_A_ASCII + BASE_PRIORITY_LOWERCASE
    } else {
        error("Invalid character")
    }
    println("$char:  $ascii -> $priority")
    return priority
}

fun getPriorities(wrongItems: List<Char>): List<Int> {
    return wrongItems.map { getPriorityForLetter(it) }
}

fun getWrongItemsTask1(rucksack: List<String>): List<Char> {
    return rucksack.map {
        val compartmentSize = it.length / 2
        val firstCompartment = it.slice(0 until compartmentSize)
        val secondCompartment =  it.slice(compartmentSize until it.length)
        findMatchingChar(firstCompartment, secondCompartment)
    }
}

fun getMatchingItemTask2(rucksack: List<String>): List<Char> {
    val groupedRucksack = rucksack.chunked(3)
    return groupedRucksack.map {group ->
        findMatchingChar(findMatchingString(group[0], group[1]),group[2])
    }
}

fun findMatchingChar(string1: String, string2: String): Char {
    val regex = Regex("[${string1}]")
    val foundString =  regex.find(string2)?.value ?: error("Could not find match")
    return foundString.toCharArray()[0]
}

fun findMatchingString(string1: String, string2: String): String {
    println(string1)
    println(string2)
    val regex = Regex("[${string1}]")
    val foundString =  regex.findAll(string2)
    println(foundString)
    return foundString.map { it.value }.toList().joinToString("")
}


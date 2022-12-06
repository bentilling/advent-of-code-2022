const val INPUT_FILE = "day1/elf-calories"

fun main() {
    val input = readInputAsString(INPUT_FILE)
    val inputTest = readInputAsString("$INPUT_FILE-test")

//    check(task1(inputTest) == 24000)
//    check(task2(inputTest) == 45000)

    println("Task 1: ${task1(input)}")
//    println("Task 2: ${task2(input)}")
}

private fun task1(input: String): Int {
    return parseInput(input).maxOf { it }
}

private fun task2(input: String): Int {
    return parseInput(input).sortedDescending().slice(0..2).sum()
}

private fun parseInput(input: String): List<Int> {
    val newline = System.lineSeparator()
    input.split("${newline}${newline}").map {
        println("---")
        println(it)
    }
    return input.split("\n\n").map {
        it.split("\n").map(String::toInt).sum()
    }
}

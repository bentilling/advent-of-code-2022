fun main() {
    val inputFile = "day6/signal"
    val input = readInputAsString(inputFile)
    val inputTest = readInputAsString("$inputFile-test")

    check(task1(inputTest) == 11)
    check(task2(inputTest) == 26)

    println("Task 1: ${task1(input)}")
    println("Task 2: ${task2(input)}")
}

private fun task1(input: String): Int {
    input.forEachIndexed { index, c ->
        if (index > 2) {
            if (input.slice(index-3..index).toSet().size == 4) {
                return index + 1
            }
        }
    }
    return -1
}

private fun task2(input: String): Int {
    input.forEachIndexed { index, c ->
        if (index > 12) {
            if (input.slice(index-13..index).toSet().size == 14) {
                return index + 1
            }
        }
    }
    return -1
}

private fun parseInput(input: String): String {
    return input
}
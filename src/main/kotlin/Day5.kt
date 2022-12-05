fun main() {
    val (stacks, instructions) = processInput("rearrangement")
//    println("-----Task1-----")
//    println("-----Before-----")
//    stacks.forEach { println(it) }
//    instructions.forEach { println(it) }
//    println("-----After-----")
//    runInstructionsTask1(stacks, instructions)
//    stacks.forEach { println(it) }
//    print(topOfStacks(stacks))
    println("-----Task2-----")
    println("-----Before-----")
    stacks.forEach { println(it) }
    instructions.forEach { println(it) }
    println("-----After-----")
    runInstructionsTask2(stacks, instructions)
    stacks.forEach { println(it) }
    print(topOfStacks(stacks))
}

fun topOfStacks(stacks: Array<ArrayDeque<Char>>): String {
    return stacks.map { it.last() }.joinToString("")
}

fun runInstructionsTask1(stacks: Array<ArrayDeque<Char>>, instructions: List<Instruction>) {
    instructions.forEach {
        moveItem(it.numberOfMoves, stacks[it.fromBucket-1], stacks[it.toBucket-1])
    }
}

fun runInstructionsTask2(stacks: Array<ArrayDeque<Char>>, instructions: List<Instruction>) {
    instructions.forEach {
        moveItems(it.numberOfMoves, stacks[it.fromBucket-1], stacks[it.toBucket-1])
    }
}

fun moveItem(numberOfTimes: Int, oldStack: ArrayDeque<Char>, newStack: ArrayDeque<Char>) {
    repeat(numberOfTimes) {
        newStack.addLast(oldStack.removeLast())
    }
}

fun moveItems(numberOfTimes: Int, oldStack: ArrayDeque<Char>, newStack: ArrayDeque<Char>) {
    val itemsToMove = mutableListOf<Char>()
    repeat(numberOfTimes) {itemsToMove.add(oldStack.removeLast())}
    newStack.addAll(itemsToMove.reversed())
}

fun processInput(fileName: String): Pair<Array<ArrayDeque<Char>>, List<Instruction>> {
    val input = readInput("day5/$fileName")
    var largestStackHeight = 0
    var numberOfStacks = 0
    input.forEachIndexed { index, s ->
        if (s.startsWith(" 1 ")) {
            largestStackHeight = index
            numberOfStacks = s.last { !it.isWhitespace() }.toString().toInt()
        }
    }
    val stacks = Array(numberOfStacks) {ArrayDeque<Char>()}
    input.filterIndexed { index, _ -> index < largestStackHeight }.map {
        it.filterIndexed { index, _ -> index % 4 == 1 }.forEachIndexed { index, c ->
            if (!c.isWhitespace()) {
                stacks[index].addFirst(c)
            }
        }
    }
    val instructions = input.filterIndexed { index, _ -> index > largestStackHeight + 1 }.map { it ->
//        val intInstructions = it.filter { c -> c.isDigit() }.map { it.toString().toInt() }
        println(it)
        val intInstructions = it.split("move ", " from ", " to ").filter { it.isNotEmpty() }.map { it.toInt() }
        println(intInstructions)
        Instruction(intInstructions[1],intInstructions[2],intInstructions[0])
    }
    return Pair(stacks, instructions)
}

class Instruction(val fromBucket: Int, val toBucket: Int, val numberOfMoves: Int) {
    override fun toString(): String {
        return "Instruction(fromBucket=$fromBucket, toBucket=$toBucket, numberOfMoves=$numberOfMoves)"
    }

}

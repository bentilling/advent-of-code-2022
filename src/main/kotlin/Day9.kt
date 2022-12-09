import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val inputFile = "day9/knots"
    val input = readInputAsList(inputFile)
    val inputTest = readInputAsList("$inputFile-test-1")

    val task1Test = task1(inputTest)
    println("task 1 test check: $task1Test")
    check(task1(inputTest) == 13)
    val task2Test = task2(inputTest)
    println("task 2 test check: $task2Test")
    check(task2(inputTest) == 1)

    println("task1: ${task1(input)}")
    println("task2: ${task2(input)}")

}
private fun task1(input: List<String>): Int {
    return getTailForRopeSize(2,input)
}

private fun task2(input: List<String>): Int {
    return getTailForRopeSize(10,input)
}

private fun getTailForRopeSize(size: Int, input: List<String>): Int {
    val visited = mutableSetOf(Coordinate())

    val coordinates = List(size) {Coordinate()}
    input.forEach { s ->
        val (direction, moves) = s.split(" ")
        repeat(moves.toInt()) {

            coordinates[0].moveCoordinate(direction)

            coordinates.forEachIndexed { index, coordinate ->
                if (index != 0) {
                    coordinates[index-1].followPositionForCoordinate(coordinate)
                }
            }
            visited.add(coordinates[size-1].copy())
        }
    }
    return visited.size
}

data class Coordinate(var x: Int = 0, var y: Int = 0) {
    fun moveCoordinate(direction: String) {
        when (direction) {
            "U" -> this.y++
            "D" -> this.y--
            "L" -> this.x--
            "R" -> this.x++
            else -> error("Invalid direction")
        }
    }

    fun followPositionForCoordinate(coordinate: Coordinate) {
        val x = this.x - coordinate.x
        val y = this.y - coordinate.y

        if (abs(x) > 1 && abs(y) > 1) {
            coordinate.x += x.sign
            coordinate.y += y.sign
        } else if (abs(x) > 1) {
            coordinate.x += x.sign
            coordinate.y = this.y
        } else if (abs(y) > 1) {
            coordinate.y += y.sign
            coordinate.x = this.x
        }
    }
}
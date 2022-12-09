fun main() {
    val inputFile = "day9/knots"
    val input = readInputAsList(inputFile)
    val inputTest = readInputAsList("$inputFile-test")

    check(task1(inputTest) == 13)
//    check(task2(inputTest) == 8)

    println(task1(input))
//    println(task2(input))

}
private fun task1(input: List<String>): Int {
    val visited = mutableSetOf(Coordinate())
    val rope = Rope()
    input.forEach {
//        println(it)
        val (direction, moves) = it.split(" ")
        repeat(moves.toInt()) {
            visited.add(when (direction) {
                "U" -> rope.moveUp()
                "D" -> rope.moveDown()
                "L" -> rope.moveLeft()
                "R" -> rope.moveRight()
                else -> error("Invalid direction")
            })
//            println(rope)
//            println(visited)
        }
    }
    return visited.size
}

private fun task2(input: List<String>): Int {
    return 0
}

data class Coordinate(var x: Int = 0, var y: Int = 0) {

    override fun toString(): String {
        return "Coordinate(x=$x, y=$y)"
    }
}

class Rope(val head: Coordinate = Coordinate(), val tail: Coordinate = Coordinate()) {
    fun moveUp(): Coordinate {
        if (tail.y < head.y) {
            tail.y = head.y
            tail.x = head.x
        }
        head.y++
        return tail.copy()
    }
    fun moveDown(): Coordinate {
        if (tail.y > head.y) {
            tail.y = head.y
            tail.x = head.x
        }
        head.y--
        return tail.copy()
    }
    fun moveRight(): Coordinate {
        if (tail.x < head.x) {
            tail.x = head.x
            tail.y = head.y
        }
        head.x++
        return tail.copy()
    }
    fun moveLeft(): Coordinate {
        if (tail.x > head.x) {
            tail.x = head.x
            tail.y = head.y
        }
        head.x--
        return tail.copy()
    }

    override fun toString(): String {
        return "Rope(head=$head, tail=$tail)"
    }
}
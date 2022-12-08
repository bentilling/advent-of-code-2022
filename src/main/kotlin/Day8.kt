fun main() {
    val inputFile = "day8/trees"
    val input = readInputAsList(inputFile)
    val inputTest = readInputAsList("$inputFile-test")

    check(task1(inputTest) == 21)
    check(task2(inputTest) == 8)

    println(task1(input))
    println(task2(input))

}
private fun task1(input: List<String>): Int {
    val visibleArray = get2DArray(input)
    println("Bool 2d array of size ${visibleArray.size}x${visibleArray[0].size}")

    var highest: Int
    for (i in input.indices) {
        highest = -1
        for (j in input[i].indices) {
            if (input[i][j].toString().toInt() > highest) {
                visibleArray[i][j] = true
                highest = input[i][j].toString().toInt()
            }
        }
        highest = -1
        for (j in input[i].indices.reversed()) {
            if (input[i][j].toString().toInt() > highest) {
                visibleArray[i][j] = true
                highest = input[i][j].toString().toInt()
            }
        }
    }
    for (i in input[0].indices) {
        highest = -1
        for (j in input.indices) {
            if (input[j][i].toString().toInt() > highest) {
                visibleArray[j][i] = true
                highest = input[j][i].toString().toInt()
            }
        }
        highest = -1
        for (j in input.indices.reversed()) {
            if (input[j][i].toString().toInt() > highest) {
                visibleArray[j][i] = true
                highest = input[j][i].toString().toInt()
            }
        }
    }
//    visibleArray.forEach { line ->
//        line.forEach {print("$it ") }
//        println("\n")
//    }
    println(visibleArray.sumOf { outer -> outer.count { it } })
    return visibleArray.sumOf { outer -> outer.count { it } }
}

private fun task2(input: List<String>): Int {
    var maxScore = 0
    input.forEachIndexed { row, s ->
        s.forEachIndexed { column, c ->
            val score = getScenicScore(row,column,input)
            if (score > maxScore) maxScore = score
        }
    }
    return maxScore
}
private fun visibleTreesInRow() {

}

private fun visibleTreesInColumn() {

}

private fun getScenicScore(row: Int, column: Int, input: List<String>): Int {
    var up = 0
    var down = 0
    var left = 0
    var right = 0
    var isBlocked = false
    val height = input[row][column].toString().toInt()

//    println("height: $height")

    for (i in row-1 downTo 0) {
        if (isBlocked) {
            break
        }
        if (input[i][column].toString().toInt() >= height) {
            isBlocked = true
        }
        left++
    }

    isBlocked = false
    for (i in row + 1 until input.size) {
        if (isBlocked) {
            break
        }
        if (input[i][column].toString().toInt() >= height) {
            isBlocked = true
        }
        right++
    }

    isBlocked = false
    for (j in column-1 downTo 0) {
        if (isBlocked) {
            break
        }
        if (input[row][j].toString().toInt() >= height) {
            isBlocked = true
        }
        up++
    }

    isBlocked = false
    for (j in column+1 until input[0].length) {
        if (isBlocked) {
            break
        }
        if (input[row][j].toString().toInt() >= height) {
            isBlocked = true
        }
        down++
    }

//    println("up: $up, down: $down, left: $left, right: $right")
    return up * down * left * right
}

//private fun get2DArray(input: List<String>): Array<BooleanArray> {
//    return Array(input.size) { BooleanArray(input[0].length) }
//}
//
//class Forest(input: List<String>) {
//    val forest = input.map { row -> row.map {it.toString().toInt()} }
//
//    fun traverseUp(point: Coordinate, f:(Coordinate) -> Unit) {
//
//    }
//
//    fun visibleFromEdge(row: Int, column: Int): Boolean {
//        return false
//    }
//}
//
//class Coordinate(val x: Int, val y: Int) {}
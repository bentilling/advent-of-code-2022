fun main() {
    val calorieSums = getCalorieSums(readInput("day1/elf-calories")).sortedDescending()
    println("task1: ${calorieSums.first()}")
    println("task2: ${calorieSums.slice(0..2).sum()}")
}

private fun getCalorieSums(calorieList: List<String>): List<Int> {
    val calorieSums = mutableListOf<Int>()
    var currentCalorieSum = 0
    for (calorie in calorieList) {
        if (calorie.isBlank()) {
            calorieSums.add(currentCalorieSum)
            currentCalorieSum = 0
        } else {
            currentCalorieSum += calorie.toInt()
        }
    }
    return calorieSums
}

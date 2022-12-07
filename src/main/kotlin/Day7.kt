fun main() {
    val inputFile = "day7/commands"
    val input = readInputAsList(inputFile)
    val inputTest = readInputAsList("$inputFile-test")

    val totalsTest = parseInputToNodes(inputTest).map {sum(it) }

    check(task1(totalsTest) == 95437)
    check(task2(totalsTest) == 24933642)

    val totals = parseInputToNodes(input).map {sum(it) }

    println(task1(totals))
    println(task2(totals))

}

fun task1(totals: List<Int>): Int {
    return totals.filter { it <= 100000 }.sum()
}

fun task2(totals: List<Int>): Int {
    val neededSpace = 30000000 - (70000000 - totals.max())
    return totals.filter { it >= neededSpace }.min()
}

fun parseInputToNodes(input: List<String>): List<Directory> {
    val root = Directory("/", null, mutableListOf())
    val directories = mutableListOf(root)
    var currentDirectory = root
    input.forEach {
        if (it == "\$ cd ..") {
            currentDirectory = currentDirectory.parent!!
        } else if (it.startsWith("\$ cd ")) {
            val directoryName = it.split("\$ cd ")[1]
            val existingDir = currentDirectory.children.find { dir ->
                dir.name == directoryName
            } ?: root
            currentDirectory = existingDir
        } else if (it.startsWith("dir")) {
            val directoryName = it.split("dir ")[1]
            val newDir = Directory(directoryName, currentDirectory, mutableListOf())
            directories.add(newDir)
            currentDirectory.children.add(newDir)
        } else if (!it.startsWith("\$")) {
            currentDirectory.size += it.split(" ")[0].toInt()
        }
    }
    return directories
}

class Directory(
    val name: String,
    val parent: Directory?,
    val children: MutableList<Directory>,
    var size: Int = 0) {}

fun sum(dir: Directory): Int {
    return if (dir.children.isEmpty()) {
        dir.size
    } else {
        dir.children.sumOf { sum(it) } + dir.size
    }
}
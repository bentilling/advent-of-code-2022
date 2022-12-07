import java.util.Dictionary

fun main() {
    val inputFile = "day7/commands"
    val input = readInputAsList(inputFile)
    val inputTest = readInputAsList("$inputFile-test")
    val totals = parseInputToNodes(input).map {sum(it) }
    val neededSpace = 30000000 - (70000000 - totals.max())
    println(totals.filter { it >= neededSpace }.min())
}

fun parseInputToNodes(input: List<String>): List<Directory> {
    val root = Directory("/", null, mutableListOf(), mutableListOf())
    val directories = mutableListOf(root)
    var currentDirectory = root
    input.forEach {
        println(it)
        if (it.contains("\$ cd ..")) {
            println("up from $currentDirectory to ${currentDirectory.parent!!}")
            currentDirectory = currentDirectory.parent!!
        } else if (it.contains("\$ cd ")) {
            val directoryName = it.split("\$ cd ")[1]
            val existingDir = currentDirectory.children.find { dir ->
                dir.name == directoryName
            } ?: root
            println("moving to directory: $existingDir")
            currentDirectory = existingDir
        } else if (it.startsWith("dir")) {
            val directoryName = it.split("dir ")[1]
            println("creating new directory: $directoryName")
            val newDir = Directory(
                directoryName,
                currentDirectory,
                mutableListOf(),
                mutableListOf()
            )
            directories.add(newDir)
            currentDirectory.children.add(newDir)
        } else if (!it.startsWith("\$")) {
            val fileParts = it.split(" ")
            println("adding file ${fileParts[0].toInt()}")
            currentDirectory.files.add(fileParts[0].toInt())
        }
    }
    return directories
}


class Directory(
    val name: String,
    val parent: Directory?,
    val children: MutableList<Directory>,
    val files: MutableList<Int>) {

    fun size() = this.files.sum()

    override fun toString(): String {
        return "Directory $name"
    }
}

fun sum(dir: Directory): Int {
    println(dir)
    return if (dir.children.isEmpty()) {
        println("$dir: no children, ${dir.size()}")
        dir.size()
    } else {
        println("$dir: children: ${dir.children} + ${dir.size()}")
        dir.children.sumOf { sum(it) } + dir.size()
    }
}
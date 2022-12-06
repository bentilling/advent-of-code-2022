import java.io.File

fun readInputAsList(name: String) =
    File("src/main/resources", "$name.txt").readLines()

fun readInputAsString(name: String) =
    File("src/main/resources", "$name.txt").readText()

import java.io.File

fun main() {
    val inputFile = "hz_all_nodes.log"
    val outputFile = "output.txt"
    File(outputFile).writeText("") // Clear output from previous runs
    val log = File(inputFile).readLines()
    val threadStrings = mutableMapOf<String, Int>()
    log.forEach { str ->
        val threadName = str.split(" ", "\t")[5]
        val curValue = threadStrings.getOrDefault(threadName, 0)
        threadStrings[threadName] = curValue + 1
    }
    val sortedThreads = threadStrings.toList().sortedByDescending { (_, count) -> count }
    for (i in 0..9) {
        File(outputFile).appendText("${sortedThreads[i].first} ${sortedThreads[i].second}\n")
    }
}
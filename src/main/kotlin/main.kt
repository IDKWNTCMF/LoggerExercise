import java.io.File

fun main(args : Array<String>) {
    val inputFile = if (args.isNotEmpty()) args[0] else "hz_all_nodes.log"
    val outputFile = if (args.size >= 2) args[1] else "output.txt"
    val cnt = if (args.size >= 3) args[2].toInt() else 10
    File(outputFile).writeText("") // Clear output from previous runs
    val log = File(inputFile).readLines()
    val threadStrings = mutableMapOf<String, Int>()
    log.forEach { str ->
        val threadName = str.split(" ", "\t")[5]
        val curValue = threadStrings.getOrDefault(threadName, 0)
        threadStrings[threadName] = curValue + 1
    }
    val sortedThreads = threadStrings.toList().sortedByDescending { (_, count) -> count }
    for (i in 0 until cnt) {
        File(outputFile).appendText("${sortedThreads[i].first} ${sortedThreads[i].second}\n")
    }
}
import java.io.File

fun main(vararg args: String) {
    val numbers = File("../source")
            .readLines()
            .map { it.toInt() }

    val ordered = numbers.toQuicksorted()

    val occurrences = ordered
            .fold(emptyMap<Int, Int>()) { acc, i ->
                val count = 1 + (acc[i] ?: 0)
                acc + mapOf(i to count)
            }
    occurrences.forEach(::println)
}

fun <T : Comparable<T>> List<T>.toQuicksorted(): List<T> =
        when {
            size < 2 -> this
            else -> first().let { pivot ->
                val (smaller, greater) = drop(1).partition { it <= pivot }
                smaller.toQuicksorted() + pivot + greater.toQuicksorted()
            }
        }

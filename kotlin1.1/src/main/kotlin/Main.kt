import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.io.File
import java.util.function.Function.identity
import java.util.stream.Collectors.counting
import java.util.stream.Collectors.groupingBy

fun main(vararg args: String) = runBlocking {
    val numbers = File("../source")
            .readLines()
            .map { it.toInt() }

    val ordered = quicksortAsync(numbers).await()

    val occurrences = ordered.stream()
            .collect(groupingBy(identity<Int>(), counting()))

    occurrences.forEach(::println)
}

suspend fun <T : Comparable<T>> quicksortAsync(list: List<T>): Deferred<List<T>> =
        async(CommonPool) {
            if (list.size <= 1) return@async list

            val pivot = list.first()
            val (smaller, greater) = list.drop(1).partition { it <= pivot }
            val smallerDeferred = quicksortAsync(smaller)
            val greaterDeferred = quicksortAsync(greater)
            return@async smallerDeferred.await() + pivot + greaterDeferred.await()
        }

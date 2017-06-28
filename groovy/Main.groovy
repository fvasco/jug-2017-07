import java.nio.file.FileSystems
import java.nio.file.Files
import java.util.stream.Collectors

def quicksort(list) {
    if (list.size() < 2) return list
    def pivot = list[0]
    def items = list.groupBy { it <=> pivot }.withDefault { [] }
    quicksort(items[-1]) + items[0] + quicksort(items[1])
}

def numbers =
        Files.lines(FileSystems.getDefault().getPath("..", "source"))
                .map { line -> Integer.valueOf(line) }
                .collect(Collectors.toList())

def ordered = quicksort(numbers)

def occurrences = new TreeMap().withDefault { 0 }
ordered.forEach({ i ->
    occurrences.put(i, 1 + occurrences[i])
})

occurrences.forEach { key, value ->
    println(key + "=" + value)
}

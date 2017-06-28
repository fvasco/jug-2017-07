import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String... args) throws FileNotFoundException, IOException {
        final List<Integer> numbers =
                Files.lines(FileSystems.getDefault().getPath("..", "source"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

        final List<Integer> ordered = quicksort(numbers);

        final Map<Integer, Integer> occurrences = new TreeMap<>();
        ordered.forEach((i) -> {
            final int count = 1 + occurrences.getOrDefault(i, 0);
            occurrences.put(i, count);
        });

        occurrences.forEach((key, value) -> System.out.println(key + "=" + value));
    }

    private static List<Integer> quicksort(final List<Integer> ints) {
        if (ints.size() < 2) return ints;
        final int pivot = ints.get(0);
        final List<Integer> unordered = ints.subList(1, ints.size());

        final List<Integer> low = new ArrayList<>(), high = new ArrayList<>();
        for (final int i : unordered) {
            if (i <= pivot)
                low.add(i);
            else
                high.add(i);
        }

        final List<Integer> result = new ArrayList<>(ints.size());
        result.addAll(quicksort(low));
        result.add(pivot);
        result.addAll(quicksort(high));
        return result;
    }
}

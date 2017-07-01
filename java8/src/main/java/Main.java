import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static collectors.OccurrenceCountInMap.toOccurrenceCountInMap;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String... args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        final List<Integer> numbers =
                Files.lines(FileSystems.getDefault().getPath("..", "source"))
                        .map(Integer::parseInt)
                        .collect(toList());

        final List<Integer> ordered = quicksort(numbers);

        final Map<Integer, Integer> occurrences = ordered.stream()
                .collect(toOccurrenceCountInMap());

        System.out.println(occurrences
                .entrySet()
                .stream()
                .map(e -> format("%d=%d", e.getKey(), e.getValue()))
                .collect(joining("\n")));
    }

    private List<Integer> quicksort(final List<Integer> ints) {
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

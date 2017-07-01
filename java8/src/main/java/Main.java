import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static collectors.OccurrenceCountInMap.toOccurrenceCountInMap;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String... args) throws IOException, ExecutionException, InterruptedException {
        new Main().run();
    }

    private void run() throws IOException, ExecutionException, InterruptedException {
        final List<Integer> numbers =
                Files.lines(FileSystems.getDefault().getPath("..", "source"))
                        .map(Integer::parseInt)
                        .collect(toList());

        final List<Integer> ordered = quicksortAsync(numbers).get();

        final Map<Integer, Integer> occurrences = ordered.stream()
                .collect(toOccurrenceCountInMap());

        System.out.println(occurrences
                .entrySet()
                .stream()
                .map(e -> format("%d=%d", e.getKey(), e.getValue()))
                .collect(joining("\n")));
    }

    private CompletableFuture<List<Integer>> quicksortAsync(final List<Integer> ints) {
        if (ints.size() < 2) return CompletableFuture.completedFuture(ints);

        final int pivot = ints.get(0);
        final List<Integer> unordered = ints.subList(1, ints.size());

        final List<Integer> smaller = unordered.stream().filter((i) -> i <= pivot).collect(toList());
        final CompletableFuture<List<Integer>> smallerFuture = quicksortAsync(smaller);

        final List<Integer> greater = unordered.stream().filter((i) -> i > pivot).collect(toList());
        final CompletableFuture<List<Integer>> greaterFuture = quicksortAsync(greater);

        return smallerFuture.thenCombineAsync(greaterFuture, (low, high) -> {
            final List<Integer> result = new ArrayList<>(ints.size());
            result.addAll(low);
            result.add(pivot);
            result.addAll(high);
            return result;
        });
    }
}

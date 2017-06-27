import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String... args) throws FileNotFoundException, IOException {
        final BufferedReader sourceReader = new BufferedReader(new FileReader(new File("../source")));

        final List<Integer> numbers = new ArrayList<>();
        String lines = sourceReader.readLine();
        while (lines != null) {
            numbers.add(Integer.parseInt(lines));
            lines = sourceReader.readLine();
        }

        final List<Integer> ordered = quicksort(numbers);

        final Map<Integer, Integer> occurrences = new TreeMap<>();
        ordered.forEach((i) -> {
            int count = 1 + occurrences.getOrDefault(i, 0);
            occurrences.put(i, count);
        });

        occurrences.forEach((key, value) -> System.out.println(key + "=" + value));
    }

    private static List<Integer> quicksort(List<Integer> ints) {
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

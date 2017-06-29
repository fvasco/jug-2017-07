package collectors;

import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class OccurrenceCountInMap<T>
        implements Collector<T, TreeMap<T, Integer>, TreeMap<T, Integer>> {

    @Override
    public Supplier<TreeMap<T, Integer>> supplier() {
        return TreeMap::new;
    }

    @Override
    public BiConsumer<TreeMap<T, Integer>, T> accumulator() {
        return (tm, i) -> {
            Integer occurrence = tm.getOrDefault(i, 0);
            tm.put(i, ++occurrence);
        };
    }

    @Override
    public BinaryOperator<TreeMap<T, Integer>> combiner() {
        return (tm1, tm2) -> {
            tm1.putAll(tm2);
            return tm1;
        };
    }

    @Override
    public Function<TreeMap<T, Integer>, TreeMap<T, Integer>> finisher() {
        return s -> s;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.EMPTY_SET;
    }

    public static <T> OccurrenceCountInMap<T> toOccurrenceCountInMap() {
        return new OccurrenceCountInMap<>();
    }


}

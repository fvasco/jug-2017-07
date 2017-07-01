package collectors;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class OccurrenceCountInMap<T>
        implements Collector<T, Map<T, Integer>, Map<T, Integer>> {

    @Override
    public Supplier<Map<T, Integer>> supplier() {
        return LinkedHashMap::new;
    }

    @Override
    public BiConsumer<Map<T, Integer>, T> accumulator() {
        return (tm, i) -> {
            Integer occurrence = 1 + tm.getOrDefault(i, 0);
            tm.put(i, occurrence);
        };
    }

    @Override
    public BinaryOperator<Map<T, Integer>> combiner() {
        return (tm1, tm2) -> {
            tm1.putAll(tm2);
            return tm1;
        };
    }

    @Override
    public Function<Map<T, Integer>, Map<T, Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }

    public static <T> OccurrenceCountInMap<T> toOccurrenceCountInMap() {
        return new OccurrenceCountInMap<>();
    }

}

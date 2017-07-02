package it.jugmilano.jug_2017_07.java8vanilla;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String... args) throws IOException {
        final List<Integer> numbers = Files.lines( FileSystems.getDefault().getPath( "..", "source" ) )
                .map( Integer::parseInt )
                .collect( toList() );
        
        final List<Integer> sorted = quicksort(numbers);
        
        final Map<Integer, Long> occurrences = sorted.stream().collect( groupingBy( Function.identity(), counting() ) );
        
        occurrences.entrySet().stream()
                .map( e -> format( "%d=%d", e.getKey(), e.getValue() ) )
                .forEach( System.out::println );
    }

	public static List<Integer> quicksort(List<Integer> numbers) {
        if (numbers.size() < 2) { return numbers; }

        final int pivot = numbers.get(0);
        final Map<Boolean, List<Integer>> smallerThanPivot = numbers.subList(1, numbers.size()).stream()
        	.collect( partitioningBy( x -> x <= pivot ) );

        final List<Integer> result = new ArrayList<>(numbers.size());
            result.addAll( quicksort( smallerThanPivot.get(true) ) );
            result.add(pivot);
            result.addAll( quicksort( smallerThanPivot.get(false) ) );
            
        return result;
	}
}

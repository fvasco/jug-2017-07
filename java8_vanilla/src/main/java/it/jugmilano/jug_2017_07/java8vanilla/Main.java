package it.jugmilano.jug_2017_07.java8vanilla;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String... args) throws IOException {
        final Stream<Integer> numbers = Files.lines( FileSystems.getDefault().getPath( "..", "source" ) )
                .map( Integer::parseInt );
        
        final Map<Integer, Long> occurrences = numbers.collect( groupingBy( Function.identity(), counting() ) );
        
        occurrences.entrySet().stream()
                .sorted( Comparator.comparing( Entry::getKey ) )          // explicit, although original order depend on natural ordering
                .map( e -> format( "%d=%d", e.getKey(), e.getValue() ) )
                .forEach( System.out::println );
    }
}

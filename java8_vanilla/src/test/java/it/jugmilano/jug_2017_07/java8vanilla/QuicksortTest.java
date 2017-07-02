package it.jugmilano.jug_2017_07.java8vanilla;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class QuicksortTest {

    @Test
    public void emptyTest() {
    	List<Integer> source = Collections.emptyList();
		List<Integer> sorted = Main.quicksort( source );
		
		assertThat( sorted, is( Collections.emptyList() ) );
    }
    
    @Test
    public void singletonTest() {
    	List<Integer> source = Arrays.asList( new Integer[]{1} );
		List<Integer> sorted = Main.quicksort( source );
		
		assertThat( sorted, is( source ) );
    }
    
    @Test
    public void variant1Test() {
    	List<Integer> source = Arrays.asList( new Integer[]{7, 9, 0, 3, 4, 1, 5, 8, 2, 6} );
		List<Integer> sorted = Main.quicksort( source );
		
		assertThat( sorted, is( source.stream().sorted().collect(toList()) ) );
    }
    
    @Test
    public void repeated1Test() {
    	List<Integer> source = Arrays.asList( new Integer[]{3, 2, 2, 3, 1, 3} );
		List<Integer> sorted = Main.quicksort( source );
		
		assertThat( sorted, is( source.stream().sorted().collect(toList()) ) );
    }
}

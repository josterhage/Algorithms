package com.system559.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SortTests {
    private final List<Integer> unsortedIntegers = Arrays.asList(23,14,5,234,111,98,43,29);
    private final List<Integer> sortedIntegers =   Arrays.asList(5,14,23,29,43,98,111,234);

    private final List<Character> unsortedCharacters = Arrays.asList('a','f','e','z','i','m');
    private final List<Character> sortedCharacters = Arrays.asList('a','e','f','i','m','z');

    @Test
    public void quickSortSortsShortIntegerArray() {
        assertEquals(sortedIntegers, Sort.quickSort(unsortedIntegers));
    }

    @Test
    public void quickSortSortCharacterArray() {
        assertEquals(sortedCharacters,Sort.quickSort(unsortedCharacters));
    }

    @Test
    public void bubbleSortShortIntegerArray() {
        assertEquals(sortedIntegers, Sort.bubbleSort(unsortedIntegers));
    }

    @Test
    public void bubbleSortShortCharacterArray() {
        assertEquals(sortedCharacters, Sort.bubbleSort(unsortedCharacters));
    }
}

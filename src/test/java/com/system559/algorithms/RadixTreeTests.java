package com.system559.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RadixTreeTests {
    List<String> mixedLengthWordList = Arrays.asList("a", "able", "aided","aim","I","boob");

    @Test
    public void addOneSingleLetterWord() {
        RadixTree testTree = new RadixTree();
        testTree.addWord("a");
        assertEquals(testTree.getStrings(), Collections.singletonList("a"));
    }

    @Test
    public void addOneMultiLetterWord() {
        RadixTree testTree = new RadixTree();
        testTree.addWord("able");
        assertEquals(testTree.getStrings(),Collections.singletonList("able"));
    }

    @Test
    public void addMultipleMixedLengthWords() {
        RadixTree testTree = new RadixTree();
        for(String word : mixedLengthWordList) {
            testTree.addWord(word);
        }

        assertEquals(Sort.quickSort(testTree.getStrings()),Sort.quickSort(mixedLengthWordList));
    }
}

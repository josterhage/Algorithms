package com.system559.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RadixTree {
    private final int LOCALE_SIZE = 52;

    private boolean isSingleLetterWord = false;

    private RadixTree[] nodes;

    private String text;

    public RadixTree() {
        text = "";
    }

    private RadixTree(String text) {
        this.text = text;
        if (text.length() == 1) {
            isSingleLetterWord = true;
        }
    }

    //getters and setters
    private int getIndex() {
        return getLetterIndex(text.charAt(0));
    }

    public void addWord(String word) {
        if (word == null || word.equals("") || word.equals(text) || word.length() == text.length()) {
            return;
        }

        int letterIndex = getLetterIndex(word.charAt(0));

        if (nodes == null) {
            nodes = new RadixTree[LOCALE_SIZE];
        }

        // root node
        if (text.equals("")) {
            if (nodes[letterIndex] == null) {
                nodes[letterIndex] = new RadixTree(word);
            } else {
                nodes[letterIndex].addWord(word);
            }
            return;
        }

        // child node
        if (text.length() == 1) {
            String childWord = word.substring(1);
            letterIndex = getLetterIndex(childWord.charAt(0));
            if (nodes[letterIndex] == null) {
                nodes[letterIndex] = new RadixTree(childWord);
            } else {
                nodes[letterIndex].addWord(childWord);
            }
            return;
        }

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == word.charAt(i)) {
                if(word.length() == 1) {
                    isSingleLetterWord = true;
                    breakOutSubTree(1);
                    return;
                }
                continue;
            }

            breakOutSubTree(i);

            String rightText = word.substring(i);
            RadixTree rightTree = new RadixTree(rightText);
            nodes[rightTree.getIndex()] = rightTree;
            return;
        }
    }

    private void breakOutSubTree(int index) {
        String newText = text.substring(index);
        text = text.substring(0,index);
        RadixTree newTree = new RadixTree(newText);
        for(int i = 0; i < LOCALE_SIZE; i++) {
            if(nodes[i] != null) {
                newTree.addChild(nodes[i]);
                nodes[i] = null;
            }
        }
        nodes[newTree.getIndex()] = newTree;
    }

    public List<String> getStrings() {
        if (nodes == null) {
            if(!text.equals("")) {
                return Collections.singletonList(text);
            }
            return null;
        }
        List<String> strings = new ArrayList<>();
        //root
        if (text.equals("")) {
            for (int i = 0; i < LOCALE_SIZE; i++) {
                if (nodes[i] != null) {
                    strings.addAll(nodes[i].getStrings());
                }
            }

            return strings;
        }

        if(isSingleLetterWord) {
            strings.add(text);
        }

        for(int i = 0; i < LOCALE_SIZE; i++) {
            if(nodes[i] != null) {
                List<String> interimStrings = nodes[i].getStrings();
                if(interimStrings == null) {
                    System.out.println(i);
                    continue;
                }
                strings.addAll(interimStrings.stream().map(str -> text + str).collect(Collectors.toList()));
            }
        }

        return strings;
    }

    private void addChild(RadixTree newChild) {
        if (nodes == null) {
            nodes = new RadixTree[LOCALE_SIZE];
        }

        //TODO: what if the node isn't null?
        if (nodes[newChild.getIndex()] == null) {
            nodes[newChild.getIndex()] = newChild;
        }
    }

    public static int getLetterIndex(char c) {
        int index = c > 96 ? c - 96 : c - 64;
        if (index < 0 || index > 51) {
            throw new IllegalArgumentException("This implementation of RadixTree only works with ASCII-encoded characters");
        }
        return index;
    }
}

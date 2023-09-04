package com.shulha;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 *
 *
 * Implement in single class.
 */

public class StringSorter {
    /**
     * The StringSorter class defines the contract for sorting strings based on
     * alphabetical order while skipping words that start with a specific prefix.
     *
     * Example:
     *  Input: ["apple", "banana", "grape", "avocado", "cherry"], Exception prefix: "a"
     *  Output: ["banana", "cherry", "grape", "avocado", "apple"]
     *
     *  Here, "banana", "cherry", and "grape" are sorted in alphabetical order
     *  whereas "avocado" and "apple" (that start with 'a') are sorted in reverse alphabetical order
     *  at the end of the list.
     */

    public List<String> sortStrings(final List<String> unsortedStrings, final String exceptionChar) {

        if (unsortedStrings == null || exceptionChar == null) {
            throw new NullPointerException("Our list and exception characters should not be null!");
        }

        final Comparator<String> stringComparator = String::compareToIgnoreCase;

        final Comparator<String> stringComparatorForExceptions =
                (firstWord, secondWord) -> secondWord.compareToIgnoreCase(firstWord);

        final List<String> sortedStrings = unsortedStrings.stream()
                .filter(word -> !word.toLowerCase().startsWith(exceptionChar.toLowerCase()))
                .sorted(stringComparator)
                .collect(Collectors.toList());

        final List<String> sortedInReverseOrderExceptionStrings = unsortedStrings.stream()
                .filter(word -> word.toLowerCase().startsWith(exceptionChar.toLowerCase()))
                .sorted(stringComparatorForExceptions)
                .collect(Collectors.toList());

//        Collections.reverse(sortedInReverseOrderExceptionStrings);

        sortedStrings.addAll(sortedInReverseOrderExceptionStrings);

        return sortedStrings;
    }

    public static void main(String[] args) {
        final StringSorter stringSorter = new StringSorter();
        final List<String> unsortedStrings = new LinkedList<>();

        unsortedStrings.add("apple");
        unsortedStrings.add("asparagus");
        unsortedStrings.add("artichoke");
        unsortedStrings.add("banana");
        unsortedStrings.add("grape");
        unsortedStrings.add("avocado");
        unsortedStrings.add("cherry");

        final List<String> sortedStrings = stringSorter.sortStrings(unsortedStrings, "a");
        System.out.println(sortedStrings);
    }
}

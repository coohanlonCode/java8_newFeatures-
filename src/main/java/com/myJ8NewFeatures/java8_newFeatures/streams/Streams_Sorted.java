package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Streams_Sorted {

    public static void main(String[] args) {

        List<String> myStateList = Arrays.asList("Virginia", "Texas", "Montana", "Pennsylvania", "Arkansas", "Ohio");
        System.out.println("\n\nDefault state list: \n" + myStateList);



        List<String> sortedByDefault = myStateList
                .stream()
                .sorted() // uses default algorithm for type, here it is alphabetical order
                .collect(Collectors.toList());

        System.out.println("\nSorted states by defautl no-arg sorted() (alphabetical order):\n" + sortedByDefault);



        // Comparator is a FUNCTIONAL INTERFACE, so we can make it a lambda later
        Comparator<String> shortToLongComparatorImpl = new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.length() - str2.length();  // str1 compared to str2.
            }
        };

        List<String> sortedShortestFirst = myStateList
                .stream()
                .sorted(shortToLongComparatorImpl) // uses custom Comparator functional interface lambda declaration.
                .collect(Collectors.toList());

        System.out.println("\nSorted by custom implemented comparator (short to long):\n" + sortedShortestFirst);



        List<String> sortedLongestFirst = myStateList
                .stream()
                .sorted((str1, str2) -> str2.length() - str1.length()) // Comparator functional interface lambda
                .collect(Collectors.toList());

        System.out.println("\nSorted by functional interface lambda impl (long to short):\n" + sortedLongestFirst);
    }
}
/*
STREAMS - SORTED

    - WHAT: Way to create a stream that sorted according to a natural order or by a customized order

    - WHY: Sort a set without having modified the original datastructure

    - SYNTAX:
        myList.stream.sorted()                                     // default, natural order for type
        myList.stream.sorted(Comparator<String>)                   // define a Comparator impl
        myList.stream.sorted( (obj1, obj2) -> logic comparing two) // lamba impl of Comparator
 */
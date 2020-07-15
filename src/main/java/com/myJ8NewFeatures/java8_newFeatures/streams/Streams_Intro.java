package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams_Intro {

    public static void main(String[] args) {

        List<Integer> myListOfVersions = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("Default in list: " + myListOfVersions);

        longFormatForEvenNumberedVersions(myListOfVersions);

        shortFormatForOddNumberedVersions(myListOfVersions);

        evenNumbersSquared(myListOfVersions);
    }

    public static void longFormatForEvenNumberedVersions(List<Integer> myIntList) {

        Stream<Integer> streamFromIntegerList = myIntList.stream();

        // This predicate's generic MUST match the stream's generic, if they are to be directly used together.
        Predicate<Integer> trueIfEvenPredicate = number -> number % 2 == 0;

        // Creates new stream populated of only the elements when this Predicate evaluates to true
        Stream<Integer> subsetStreamOfEvenIntegers = streamFromIntegerList.filter(trueIfEvenPredicate);

        // 'collect' converts from stream to collection via various static utility methods
        List<Integer> myEvenList = subsetStreamOfEvenIntegers.collect(Collectors.toList());

        System.out.println("\n(Long-form) Even numbers in list: " + myEvenList);
    }

    public static void shortFormatForOddNumberedVersions(List<Integer> myIntList) {

        List<Integer> myOddNumbers = myIntList.stream()
                .filter(intRecord -> intRecord % 2 == 1)
                .collect(Collectors.toList());

        System.out.println("(Short-form) Odd numbers in list: " + myOddNumbers);
    }

    private static void evenNumbersSquared(List<Integer> myIntList) {

        System.out.println("\n(evenSquaredNumbers) Initial Input: " + myIntList);

        System.out.println("Using streams to get evens and square them");
        myIntList.stream()
                .filter(number -> number % 2 == 0)                                       // return items that eval to true
                .map(evenNumber -> evenNumber * evenNumber)                          // apply to every item in stream
                .forEach(evenSquaredNumber -> System.out.println(evenSquaredNumber));// terminal op to output each
    }
}

/*
#14 STREAMS - Intro

    - WHAT: A way to take sequential parts of a collections and process them without the need for a 'for' loop

    - WHY: Clean way to integrate functions into collections.
        - Also to cut down on resources needed for a reserved 'for' loop.

    - SYNTAX:
            Collection<TypeInCollelction> myNewCollections = myOriginalCollection.stream().
                .myIntermediateOperation(myPredicateFunction)
                .terminalOperation(Collectors.staticMethodToDesiredCollection);

    - Made of 3 parts
        - 1 Source: creates a copy of the initial collection as a stream
        - 1..N Intermediate Operator: modifies the stream via a variety of operations, all return a stream
                that has a state that reflects the modifications
        - 1 Terminal Operator: converts stream to collection structure

    - Streams DO NOT Change the original data structure. They are used to create new data structures that are modified
 */
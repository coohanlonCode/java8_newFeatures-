package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams_Intro {


    public static void main(String[] args) {


        List<Integer> myListOfVersions = new ArrayList<>();

        myListOfVersions.add(1);
        myListOfVersions.add(2);
        myListOfVersions.add(3);
        myListOfVersions.add(4);
        myListOfVersions.add(5);
        myListOfVersions.add(6);
        myListOfVersions.add(7);

        System.out.println("Default in list: " + myListOfVersions);

        longFormatForEvenNumberedVersions(myListOfVersions);

        shortFormatForOddNumberedVersions(myListOfVersions);

    }

    public static void longFormatForEvenNumberedVersions(List<Integer> myIntList) {

        Stream<Integer> streamFromIntegerList = myIntList.stream();

        // This predicate's generic MUST match the stream's generic, if they are to be directly used.
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
}





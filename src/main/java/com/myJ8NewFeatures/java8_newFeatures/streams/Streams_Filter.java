package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams_Filter {

    public static void main(String[] args) {

        List<Integer> myIntegerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("Default integer list: " + myIntegerList);

        Predicate<Integer> evenIntegerPredicate = intRecord -> intRecord % 2 == 0;
        List<Integer> evenNumbers = myIntegerList
                .stream()
                .filter(evenIntegerPredicate) // passing in a declared Predicate
                .collect(Collectors.toList());

        System.out.println("\nFiltered down to even numbers only: "+ evenNumbers);
        System.out.println("\nState of original integer collection: " + myIntegerList); // does not change



        List<String> myStateList = Arrays.asList("Alabama", "Texas", "Montana", "Iowa", "Florida", "Arkansas", "Ohio");
        System.out.println("\n\nDefault state list: " + myStateList);

        List<String> aStates = myStateList
                .stream()
                .filter(stateName -> stateName.toUpperCase().startsWith("A")) // functional declaration of Predicate
                .collect(Collectors.toList());

        System.out.println("\nFiltered down to states starting with 'A': "+ aStates);
        System.out.println("\nState of original state collection: " + myStateList); // does not change
    }
}

/*
#15 STREAMS - FILTER

    - WHAT: Intermediate operation that accepts a functional Predicate and returns a new stream with
            elements from the original stream that evaluate to true under that Predicate

    - WHY: Functionally create a subset without having the original modified.

    - SYNTAX:	myCollection.stream().filter(myPredicate).myTerminalOp();
 */
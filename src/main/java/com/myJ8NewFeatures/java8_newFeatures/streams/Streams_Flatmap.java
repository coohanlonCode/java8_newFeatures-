package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Streams_Flatmap {

    public static void main(String[] args) {

        consolidateNestedCollections();

        System.out.println("\n-------");

        alternativeNotation();
    }

    private static void consolidateNestedCollections() {

        List<String> nameList = Arrays.asList("Alex", "Bob", "Connor");
        List<String> colorList = Arrays.asList("Blue", "Purple", "Brown");
        List<String> sportList = Arrays.asList("BJJ", "Judo", "Wrestling");

        List<List<String>> listOfLists = Arrays.asList(nameList, colorList, sportList);
        System.out.println("The listOfLists before flattening is :\n" + listOfLists);

        // flatMap to consolidate multiple collections into a single collection
        List<String> myNewList = listOfLists.stream()
                .flatMap(listRecord -> listRecord.stream())  // the lambda calls the list record to stream its contents
                .collect(Collectors.toList());

        System.out.println("\nThe new list AFTER flattening is : \n" + myNewList);
    }

    private static void alternativeNotation() {

        List<String> nameList = Arrays.asList("Xavier", "Yoel", "Zach");
        List<String> colorList = Arrays.asList("Yellow", "Oranger", "Green");

        List<List<String>> listOfLists2 = Arrays.asList(nameList, colorList);
        System.out.println("\n\nThe listOfLists2 before flattening is :\n" + listOfLists2);

        // flatMap to consolidate multiple collections into a single collection
        List<String> myNewList2 = listOfLists2.stream()
                .flatMap(Collection::stream)  // .flatMap(returnType::methodNameOfImpliedParam) using J8 doubleColon op
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\nThe new list2 AFTER flattening is : \n" + myNewList2);
    }
}
/*
STREAMS - FLATMAP

    - WHAT: Process everything in a collection, including collections of streams.

    - WHY: Used to consolidate (or 'flatten') multiple streams into a single stream. Convert collections of
    collections into a single collection.

        List<List<String>>  into   List<String>

    - SYNTAX:
        List<List<String>> listContainingList1 = new ..;
        listContainingList1
            .stream()
            .flatMap( myListRecord -> myListRecord.stream())

        List<List<String>> listContainingList2 = new ..;
        listContainingList2
            .stream()
            .flatMap( Collection::stream)
 */
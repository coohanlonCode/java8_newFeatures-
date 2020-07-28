package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams_Collect {

    public static void main(String[] args) {


        List<String> martialArts = Arrays.asList("Jiu Jitsu", "Jiu Jitsu", "Wrestling", "Sambo", "Judo", "Judo");

        Set<String> uniquesMartialArts = martialArts.stream().collect(Collectors.toSet());
        System.out.println("\n\nUnique martial Arts");
        uniquesMartialArts.forEach(System.out::println);

        List<String> listCopy = martialArts.stream().collect(Collectors.toList());
        System.out.println("\n\nCopy of list of martial Arts");
        listCopy.forEach(System.out::println);


    }
}
/*
    STREAMS - COLLECT

    - WHAT: A Java streams terminal method that provides mutable (creates new without modifiying original) set of static methods to create collections that can be assigned.

    - WHY: All Java8 streams need to have a terminal operator to assign any modifications to a stateful local variable.

    - SYNTAX: collection.stream().collect(myCollectStaticMethod)
 */
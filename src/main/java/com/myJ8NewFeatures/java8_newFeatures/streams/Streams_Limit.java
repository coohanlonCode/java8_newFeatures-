package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams_Limit {

    public static void main(String[] args) {

        List<Integer> myIntegerList = Arrays.asList(1, 22, 138, 412, 1738, 3008, 7, 1919, 1984, 8675309);
        System.out.println("\nDefault integer list: \n" + myIntegerList);

        List<Integer> firstFiveNumbers = myIntegerList
                .stream()
                .limit(5) // passing in a long to truncate the stream to this many items from the front
                .collect(Collectors.toList());

        System.out.println("\nLimited to first 5 records only: \n" + firstFiveNumbers);
        System.out.println("\nState of original integer collection: \n" + myIntegerList); // does not change


        List<String> marioKartFinishingOrder =
                Arrays.asList("Luigi", "Mario", "Bowser", "Peach", "Toad", "Wario", "Yoshi", "Donkey Kong");
        System.out.println("\n\n\nDefault finisher list: \n" + marioKartFinishingOrder);

        List<String> podiumFinishers = marioKartFinishingOrder
                .stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nLimited to first 3 for podium finishers: \n" + podiumFinishers);
        System.out.println("\nState of original finishing order: \n" + marioKartFinishingOrder); // does not change

    }
}
/*
STREAMS - LIMIT

    - WHAT: Intermediate operation that truncates a stream to include only the first X items.

    - WHY: Create a subset based on position without modifying the origina

    - SYNTAX:  myCollection.stream().limit(long).myTerminalAction()

 */
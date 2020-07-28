package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.List;

public class Streams_Peek {

    public static void main(String[] args) {

        List<Sport> sports = Arrays.asList(
                new Sport("Jiu Jitsu"),
                new Sport("Wrestling"),
                new Sport("Sambo"),
                new Sport("Judo"));

        sports.stream()
                .peek(record -> System.out.println(record)); // Does nothing bc only intermediary operation present


        sports.stream()
                .peek(record -> record.setName(record.getName().toUpperCase())) // intermediary op changes state
                .forEach(System.out::println); // terminal op outputs the current/updated state of each item
    }

    private static class Sport {
        private String name;

        public Sport(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Sport{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}

/*
STREAMS - PEEK

    - WHAT: Takes a look at the item that is "passing through" the stream, and can modify that item in a single

    - WHY: Exists primarily for debugging when you want to see the elements as tehy flow past a certain point in the pipeline.

    - SYNTAX:  colletion.stream().peek(record ->  doThingToRecord)
 */

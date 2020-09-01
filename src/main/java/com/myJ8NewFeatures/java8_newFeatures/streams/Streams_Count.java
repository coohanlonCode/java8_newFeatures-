package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Streams_Count {

    private int data;

    public static void main(String args[]) {

        int data= 0;
        System.out.println(data);

        // List<String> colors = Arrays.asList("White", "Blue", "Purple", "Brown", "Black");
        int i = 2;
        int j = 0;
        j = (i++ + i++) * --i;
        System.out.println("J is " + j);


    }
}

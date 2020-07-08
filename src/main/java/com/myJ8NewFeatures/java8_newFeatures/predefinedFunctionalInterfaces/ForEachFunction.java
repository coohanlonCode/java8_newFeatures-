package com.myJ8NewFeatures.java8_newFeatures.predefinedFunctionalInterfaces;

import java.util.Arrays;
import java.util.List;

public class ForEachFunction {

    final static List<Integer> NUMBER_LIST = Arrays.asList(1, 2, 3, 4, 5);

    final static List<String> SPORTS_LIST = Arrays.asList("Jui Jitsu", "Judo", "Wrestling", "Boxing", "Muy Thai");

    final static List<MovieInfo3> MOVIE_LIST = Arrays.asList(
            new MovieInfo3("Mad Max", "1979"),
            new MovieInfo3("The Road Warrior", "1981"),
            new MovieInfo3("Beyond Thunderdome", "1985"));

    public static void main(String[] args) {

        // Example 1: Explicit iterated item via parameter declaraion
        System.out.println("Print each item, explicit record parameter:");
        NUMBER_LIST.forEach(listRecord -> System.out.println(listRecord));  // 'for each item listRecord, do this.'


        // Example 2: Implicit iterated item via implied single-parameter method reference via double colon
        System.out.println("\nPrint each item, implicit record parameter:");
        SPORTS_LIST.forEach(System.out::println);
        // aka:   COLLECTION.forEach(className::methodDefinedWithSingleParameter);
        // aka:   myCollection.forEach( (Str myRecord) -> System.out.prinln(typeMatchesCollection theOnlyParamInSig));


        // Example 3: Update to objects made inside loop
        System.out.println("\nMovie list before modification in .forEach ");
        MOVIE_LIST.forEach(movieRecord -> System.out.println(movieRecord));

        System.out.println("\nModifying object attributes via .forEach\n");
        MOVIE_LIST.forEach(movieRecord -> {
            movieRecord.setTitle(movieRecord.getTitle().toUpperCase());
            movieRecord.setReleaseYear((movieRecord.getReleaseYear() + " AD"));
        });

        System.out.println("\nMovie list AFTER modification in .forEach ");
        MOVIE_LIST.forEach(movieRecord -> System.out.println(movieRecord));
    }
}

//@formatter:off
class MovieInfo3 {
    private String title;
    private String releaseYear;

    public MovieInfo3(String title, String releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }
    public String getTitle() {  return title;  }
    public void setTitle(String title) { this.title = title; }
    public String getReleaseYear() {  return releaseYear;  }
    public void setReleaseYear(String releaseYear) {  this.releaseYear = releaseYear;  }
    public String toString(){return String.format("The movie '%s' was released in %s", this.title, this.releaseYear);}
}
/*

PREDEFINED FUNCTIONAL INTERFACES - FOREACH FUNCTION

    - WHAT: function to shorthand to iterate each item in a collection (something that implements Collection<E>). This is intended to be a alternative to the enhanced for-each loop

    - WHY:
        1. Less code to write.
        2. Remove need to use an explict keyword for looping

    - SYNTAX:  List<type> myList = new ArrayList<>();  //List implements Collection<>

                myList.forEach( function logic to do to each thing)
 */
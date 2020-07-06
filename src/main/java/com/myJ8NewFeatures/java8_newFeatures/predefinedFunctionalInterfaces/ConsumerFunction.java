package com.myJ8NewFeatures.java8_newFeatures.predefinedFunctionalInterfaces;

import java.util.function.Consumer;

public class ConsumerFunction {

    public static void main(String[] args) {

        // Consumer<inputObjectType> myRefVar = inputParam -> logic
        // myRefVar.accept(inputObject);

        // Example 1: Utility purposes
        Consumer<String> writeToConsoleInCaps = inputText -> System.out.println(inputText.toUpperCase());
        writeToConsoleInCaps.accept("I wIlL bE iN aLl CaPsSsS");


        // Example 2: Change attributes via pass by Reference
        Consumer<MovieInfo2> capitalizeMovieTitle = inputMovie -> {
            inputMovie.setTitle(inputMovie.getTitle().toUpperCase());  // is void, so no 'return' for this multi-line
        };

        MovieInfo2 actionMovie = new MovieInfo2("Die Hard 2: Die harder", "1990");

        System.out.println("-----------");
        System.out.println(String.format("\nTitle before function: \n%s", actionMovie.getTitle()));

        System.out.println("\nRunning the 'capitalizeMovieTitle' function...\n");
        capitalizeMovieTitle.accept(actionMovie);

        System.out.println(String.format("Title AFTER function: \n%s", actionMovie.getTitle()));
    }
}

//@formatter:off
class MovieInfo2 {
    private String title;
    private String releaseYear;

    public MovieInfo2(String title, String releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }
    public String getTitle() {  return title;  }
    public void setTitle(String title) { this.title = title; }
    public String getReleaseYear() {  return releaseYear;  }
    public void setReleaseYear(String releaseYear) {  this.releaseYear = releaseYear;  }
}
/*
PREDEFINED FUNCTIONAL INTERFACES - Consumer FUNCTION

    - WHAT: takes a single input and return nothing/void. This is equivalent of a functional void method.

    - WHY:
        1. Save code
        2. Used to do utility tasks, such as logging, that do not return anything.
        3. To modify object by pass by reference

    - SYNTAX: Consumer<inputObjectType> myRefVar = inputParam -> logic
              myRefVar.accept(inputObject);



     -  Consumer<String> writeToConsoleInCaps = inputText -> System.out.println(inputText.toUpperCase());

        writeToConsoleInCaps.accept("I wIlL bE iN aLl CaPsSs");

        // is equivalent to calling this method
          void writeToConsoleInCaps(String inputText){
            System.out.println(inputText.toUpperCase());
         }

 */
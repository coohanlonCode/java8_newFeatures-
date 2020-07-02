package com.myJ8NewFeatures.java8_newFeatures;

import java.util.function.Function;

public class PredefinedFunctionalInterface_identity {

    public static void main(String[] args) {

        MovieInfo movie1 = new MovieInfo("The Matrix", "1999");
        MovieInfo movie2 = new MovieInfo("The Matrix", "1999");

        System.out.println(
                String.format("\nThe two objects are the same object in memory? %b", movie1 == movie2)); // Evals FALSE

        System.out.println(
                String.format("The two object are functionally equivalent? %b", movie1.equals(movie2)));
        // Evals FALSE,
        //  This is false because the .equals() and .hashcode() are not overridden in this current state. If they were,
        //  then these two objects would be functionally equivalent bc all of their attributes are identical.
        //
        //  Accordingly, since the JVM would then be able to determine that the objects are functionally equivalent,
        //  it would garbage collect the redundant object and point both references variables to the single object
        //  in memory.
        //
        //  But since they are not overridden, there are two objects instantiated in memory. Function.identity() is
        //  used to grab that EXACT object from the memory location that the provided reference variable points at.

        System.out.println("\nMOVIE1 MEMORY LOCATION = " + movie1.toString());
        System.out.println("MOVIE2 MEMORY LOCATION = " + movie2.toString());

        Function<MovieInfo, MovieInfo> identityFunction = Function.identity();


        MovieInfo identityReferenceTo_Movie1 = identityFunction.apply(movie1);
        System.out.println("\nidentityReferenceTo_Movie1   memoryLocation = "
                + identityReferenceTo_Movie1.toString());

        MovieInfo identityReferenceTo_Movie2 = identityFunction.apply(movie2);
        System.out.println("identityReferenceTo_Movie2   memoryLocation = "
                + identityReferenceTo_Movie2.toString());
    }
}

//@formatter:off
class MovieInfo {
    private String title;
    private String releaseYear;

    public MovieInfo(String title, String releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }
    public String getTitle() {  return title;  }
    public void setTitle(String title) { this.title = title; }
    public String getReleaseYear() {  return releaseYear;  }
    public void setReleaseYear(String releaseYear) {  this.releaseYear = releaseYear;  }
}
/*
  PREDEFINED FUNCTIONAL INTERFACE IDENTITY FUNCTION

    - WHAT: Static method that returns exactly what was passed in

    - WHY: When you need to return an EXACT instance of an object in a scenario where having a functionally equilvalent object is a no-go

    - Syntax:   Function.identity()
 */
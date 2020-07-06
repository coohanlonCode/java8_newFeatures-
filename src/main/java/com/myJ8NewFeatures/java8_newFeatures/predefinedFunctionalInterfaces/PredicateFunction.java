package com.myJ8NewFeatures.java8_newFeatures.predefinedFunctionalInterfaces;

import java.util.function.Predicate;

public class PredicateFunction {

    public static void main(String[] args) {

        //  Predicate<inputType> myVarName =  input ->  logic ;    // no ouput type provided bc its an implicit boolean
        //  boolean decisonResult = myVarName.test(singleInput);

        // EXAMPLE 1
        Predicate<Integer> isGreaterThan1000 = number -> number > 1000;
        boolean decisionResult1 = isGreaterThan1000.test(50);

        System.out.println(String.format("Is 50 greater than 1,000? : %b", decisionResult1)); // false
        System.out.println(String.format("Is 2,500 greater than 1,000? : %b", isGreaterThan1000.test(2500))); //true


        // EXAMPLE 2
        Predicate<String> stringBeginsWithC = inputString -> {
            boolean firstLetterIsC = false;

            if (inputString != null && inputString.length() > 0) {
                firstLetterIsC = inputString.toUpperCase().startsWith("C");
            }
            return firstLetterIsC;
        };

        System.out.println("\n");
        System.out.println("Does 'California' begin with C? : " + stringBeginsWithC.test("California"));    //true
        System.out.println("Does 'college' begin with C?    : " + stringBeginsWithC.test("college"));       //true

        System.out.println("Does 'Wisconsin' begin with C?  : " + stringBeginsWithC.test("Wisconsin"));     //false
        System.out.println("Does 'florida' begin with C?    : " + stringBeginsWithC.test("florida"));       //false

    }
}

/*
#10 PREDEFINED FUNCTIONAL INTERFACES - PREDICATE FUNCTION

    - WHAT: Function interface method that accepts some type and implicitly returns a boolean

    - WHY: Used to shorthand yes/no decisions so you have a reusable utility that doesnt need to be its own method.

    - SYNTAX:
        Predicate<inputType> myVarName =  input ->  logic ;
        boolean decisonResult = myVarName.test(singleInput);

 */
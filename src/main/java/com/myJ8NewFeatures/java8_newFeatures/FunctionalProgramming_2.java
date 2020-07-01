package com.myJ8NewFeatures.java8_newFeatures;

public class FunctionalProgramming_2 {

    public static final String OUTPUT_TEXT_PATTERN = "\nWhen %s %s %s, the result is %s";

    public static void main(String[] args) {

        final int numberOne = 30;
        final int numberTwo = 10;

        // Single-line, implied 'return'
        MyMathInterface additionLambda = (a, b) -> a + b;
        System.out.println(buildOutputString(numberOne, "adds", numberTwo, additionLambda));


        // Single-line, mandatory braces with mandatory 'return'
        MyMathInterface subtractionLambda = (a, b) -> { return a - b;};
        System.out.println(buildOutputString(numberOne, "subtracts", numberTwo, subtractionLambda));


        // Multi-line, mandatory braces with mandatory 'return'
        MyMathInterface multiplicationLambda = (a, b) -> {
            int subtractedTotal = a * b;
            return subtractedTotal;
        };
        System.out.println(buildOutputString(numberOne, "multiplies by", numberTwo, multiplicationLambda));


        // Single-line, implied 'return'
        MyMathInterface divisionLambda = (a, b) -> a / b;
        System.out.println(buildOutputString(numberOne, "divides by", numberTwo, divisionLambda));
    }

    private static String buildOutputString(int number1,
                                            String mathActionText,
                                            int number2,
                                            MyMathInterface lambdaExpression) {

        int lambdaResult = lambdaExpression.doThatMathThing(number1, number2);

        return String.format(OUTPUT_TEXT_PATTERN, number1, mathActionText, number2, lambdaResult);
    }
}

@FunctionalInterface
interface MyMathInterface { // NEED to have a functional interface if you're going to use lambda.
    public int doThatMathThing(int a, int b);
}

/*
FUNCTIONAL PROGRAMMING IN JAVA

    - In Java, a method can be passed to another method by using a Lambda
        - Ex: addTwoNumbers( (a,b) -> a +b );

    - Passing a function to a method is called FUNCTIONAL PROGRAMMING

    - Passing an object in a method is OBJECT ORIENTED PROGRAMMING

    - Functional programming is good for simple things that are so easy to do that having their own method
      would be unnecessary complications.
        - Save time and effort in TDD styled coding.

    - CREATE LAMBDA, ASSIGN LAMDA TO VARIBLE, and PASS THAT VARIABLE to a method that has that functional interface
        as a parameter type.
 */
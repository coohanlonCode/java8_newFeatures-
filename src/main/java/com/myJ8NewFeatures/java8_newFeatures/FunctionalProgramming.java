package com.myJ8NewFeatures.java8_newFeatures;

public class FunctionalProgramming {

    public static void main(String[] args) {

        final int numberOne = 19;
        final int numberTwo = 90;

        // Object-Oriented approach calls the defined method, it executes, it returns content, then
        // that content is output.
        String oopExecutedOutput = sumStringFromOopInts(numberOne, numberTwo);
        System.out.println(oopExecutedOutput);



        // CREATE LAMBDA, ASSIGN TO VARIBLE, PASS THAT VARIABLE
        FunctionalSumInterface lambdaWithRegularAddition = (a, b) -> {
            int sum = a + b;
            return "[from 'lambdaWithRegularAddition' var] I'm calculated in the lambda! Sum is " + sum;
        };
        String output1 = sumStringFromFunctionalProgramming(lambdaWithRegularAddition, numberOne, numberTwo);
        System.out.println(output1);


        FunctionalSumInterface lambdaImplWithExtraThousand = (a, b) -> {
            int sum = a + b + 1000;
            return "[from 'lambdaImplWithExtraThousand' var] Different lambda implementation adds another thousand! " +
                    "Sum this time is " + sum;
        };
        String output2 = sumStringFromFunctionalProgramming(lambdaImplWithExtraThousand, numberOne, numberTwo);
        System.out.println(output2);

    }


    public static String sumStringFromOopInts(int numberOne, int numberTwo) {
        int sum = numberOne + numberTwo;
        return "\nThis line of text is all from the 'sumStringFromOopInts(int,int)' method executing logic " +
                "exclusively from that method's code block and returning. Sum is " + sum + "\n\n";
    }

    public static String sumStringFromFunctionalProgramming(
            FunctionalSumInterface lambdaExpression,  // Method signature has functional interface as PARAM TYPE
            int firstNum,
            int secondNum) {

        return "This first line text from 'sumStringFromFunctionalProgramming(lambda,int, int)' method. \n" +

                "The next line of text is from running the functional interface method of the specific " +
                "lambda instance passed in: \n   "
                + lambdaExpression.determineFormattedSumString(firstNum, secondNum) + "\n\n";
    }
}

interface FunctionalSumInterface { // NEED to have a functional interface if you're going to use lambda.
    public String determineFormattedSumString(int a, int b);
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
package com.myJ8NewFeatures.java8_newFeatures;

public class LambdaExpressions {

    public static void main(String args[]) {

        // Lambda_A impl #1
        MyLambdaInterfaceA expressionForSum = (first, second) -> first + second; // single-line, no braces, no return

        System.out.println("Sum is " + expressionForSum.addIntsTogether(6, 10));
        System.out.println("Sum is " + expressionForSum.addIntsTogether(17, 38));
        System.out.println("Sum is " + expressionForSum.addIntsTogether(300, 50) + "\n");


        // Lambda_A impl #2
        MyLambdaInterfaceA expressionForSumPlus1000 = (first, second) -> { // multi-line, yes braces, yes return
            int normalSum = first + second;
            return normalSum + 1000;
        };



        System.out.println("Sum with extra is " + expressionForSumPlus1000.addIntsTogether(6, 10));
        System.out.println("Sum with extra  is " + expressionForSumPlus1000.addIntsTogether(17, 38));
        System.out.println("Sum with extra is " + expressionForSumPlus1000.addIntsTogether(300, 50) + "\n");

        // Lambda_B impl #1
        MyLambdaInterfaceB expressionFormalTitle = (name, xp) -> { // params match method signature in interface class
            return name + " has " + xp + " years formal experience.";
        };
        String ohagenProfile = expressionFormalTitle.getOfficerProfile("Chief O'Hagen", 25);
        System.out.println(ohagenProfile + "\n");



        // Lambda_B impl #2
        MyLambdaInterfaceB expressionVeryNotFormalTitle = (name, xp) -> {
            String informalTitleText = name + "'s number 1! " + name + "'s number! (" + xp + " 'years' experience)";
            return informalTitleText.toUpperCase();
        };

        String farvaProfile = expressionVeryNotFormalTitle.getOfficerProfile("Farva", 8);
        System.out.println(farvaProfile + "\n");

    }
}

interface MyLambdaInterfaceA {
    public int addIntsTogether(int number1, int number2);
}

interface MyLambdaInterfaceB {
    public String getOfficerProfile(String name, int yearsXp);
}

/*
LAMBDA EXPRESSIONS

	- WHAT: Lambda expressions are used as ANONYMOUS FUNCTIONS (meaning functions that do no have a declared name)
	    - They use a very scripting-like syntax
	    - Lambdas do not have a defined scope

	- WHY: Lambdas are valuable bc they allow you to make multiple implementations of one interface method in
	a single class/block.
	    - This gives you the power to create functionality that is basic without the need of
	      creating and managing a new class just to implement a single interface method.
	    - Less code bc you don't need to create another class to implement a one-off interface

	- Lambda parameters in the functional assignment line must match the parameter arguments in the interface
	file's method declaration

    - Lambdas can only be assigned to a functional interface (an interface with only one method)

    - Single line expressions do not need curly braces. If no curly braces are used, do NOT use 'return' keyword.

    -  If you use Multi-line expressions:
        - Must use curly braces
        - The 'return' keyword is mandatory

    - If you use the 'return' keyword:
        - Must use curly braces, whether or not it is single-line or multi-line

    - If you use CURLY BRACES:
        - 'return' must be used

    - Lambda expressions can be assigned to variables

    - Lambda expressions MUST end with a semi-colon

    - Syntax
        - Parentheses with 0..n parameters followed by a skinny arrow lastly followed by a single line or code block
          and ending in a semi-colon

        -  ( 0..n params)  -> /the goods/ ;

        - () -> System.out.println("Hello World");

        - (a, b) ->  a + b ;  // single line, 'return' is optional so its not used in this example

        - (a, b) ->  { return a + b}  ;  // single line, optional return is used with it's required braces

        - (a, b) ->  { int sum = a+b;
                       return sum; }   // multi line, optional return with it's required braces

        - (a, b) ->  a + b ;  // single line, no optional return
        - () -> System.out.println("Hello World");
        - () -> System.out.println("Hello World");
 */
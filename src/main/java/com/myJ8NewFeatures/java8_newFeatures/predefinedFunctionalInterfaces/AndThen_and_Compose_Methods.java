package com.myJ8NewFeatures.java8_newFeatures.predefinedFunctionalInterfaces;

import java.util.function.Function;

public class AndThen_and_Compose_Methods {

    public static void main(String[] args) {

        Function<String, String> capitalizeFunction = inputText -> inputText.toUpperCase();

        Function<String, String> addWelcomePrefix = myText -> "Welcome to " + myText + "!!!";

        // Baseline example regular calls to each of the above functions
        System.out.println(capitalizeFunction.apply("PenNSyLvAnIa"));
        System.out.println(addWelcomePrefix.apply("the party, pal"));
        System.out.println("\n\n");


        // ANDTHEN: executes the left-most function first then uses its result as the input to the other function.

        String andThenText = capitalizeFunction.andThen(addWelcomePrefix).apply("Ohio");
        System.out.println(andThenText);// Welcome to OHIO!!!
        //SAME AS (left function then right function)
        // String abc = capitalizeFunction.apply("Ohio")
        // String finalText = addWelcomePrefix.apply(abc);   // Welcome to OHIO!!!


        // COMPOSE: executes the RIGHT-most function first then uses its result as the input to the other function.

        String composeText = capitalizeFunction.compose(addWelcomePrefix).apply("my house");
        System.out.println(composeText);
        //SAME AS (RIGHT function then left function)
        // String abc = addWelcomePrefix.apply("my house");
        // String finalText = capitalizeFunction.apply(abc);   // WELCOME TO MY HOUSE!!!


        Function<Integer, Integer> addTen = number -> number + 10;

        Function<Integer, Integer> multiplyBy3 = number -> number * 3;

        Function<Integer, Integer> addTwenty = number -> number + 20;

        System.out.println(addTen.andThen(multiplyBy3).apply(4));  // 4 + 10 = 14; 14 * 3 = 42

        System.out.println(addTen.compose(multiplyBy3).apply(4)); // 4 * 3 = 12; 12 + 10 = 22


        // Multiple 'andThens' and 'compose' can be chained together

        //YES: chain many 'andThen'
        // 4 + 10 = 14;  14 * 3 = 42;  42 + 20 = 62
        Integer andThenChain = addTen.andThen(multiplyBy3).andThen(addTwenty).apply(4);
        System.out.println(andThenChain);

        //YES: chain many 'compose'
        //4 + 20 = 24; 24 * 3 = 72; 72 + 10 = 82
        Integer composeChain = addTen.compose(multiplyBy3).compose(addTwenty).apply(4);
        System.out.println(composeChain);

        //YES: chain both 'andThen' and 'compose'
        // addTen.andThen(multiplyBy3).compose(addTwenty).apply(4);
        Integer andThenAndComposeChain =
                addTen                          // 1st: 4 + 10 = 14
                        .andThen(multiplyBy3)   // 3rd: 34 * 3 = 102
                        .compose(addTwenty)     // 2nd: 14 + 20 = 34
                        .apply(4);
        System.out.println(andThenAndComposeChain);


        //NO!: Multiple 'andThens' and 'compose' can be nested or compilation errors occur. (Even if you use all
        // unique functions in order to avoid an infinite loop)
//        Integer nestedFailsToCompile =
//                addTen.compose(  multiplyBy3.compose(addTwenty).apply(9)  ).apply(4);   FAILS TO COMPILE

    }
}

/*
#8 PREDEFINED FUNCTIONAL INTERFACE ANDTHEN and COMPOSE

    - andThen and compose are basically the same thing but their execution order is opposite.
        - andThen and compose are default methods

    - WHAT: used to chain predefined functions together sequentially

    - WHY: easy way to adjust which equation runs first

    - andThen
        - It is a way of smashing multiple Functions together and running the first function then second function

        - Syntax:   function1.andThen(function2).apply(input);

        - Executes the left-most function first then uses its result as the input to the other function.

        - Ex:
                function1.andThen(method2).apply("Ohio")

            String xyz = function1.apply("Ohio")
            String finalText = method2.apply(xyz);

    - compose
        - It is a way of smashing multiple Functions together and running the SECOND function then FIRST function

        - Syntax:   function1.compose(function2).apply(input);

        - Executes the RIGHT-most function first then uses its result as the input to the other function.
            - (Opposite order than what andThen uses)

                function1.compose(method2).apply(input);

            - 1st. abc = method2.apply(input);
            - 2nd. xyz = function1.apply(abc)
            - 3rd. result is xyz
 */
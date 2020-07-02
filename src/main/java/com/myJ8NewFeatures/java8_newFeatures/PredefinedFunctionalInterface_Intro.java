package com.myJ8NewFeatures.java8_newFeatures;

import java.util.function.Function;

public class PredefinedFunctionalInterface_Intro {

    public static void main(String[] args) {

        // SYNTAX: Function<inputClass, outputClass> functionNameVar = inputVar -> (logic);
        // To execute:   functionNameVar.apply(thatSingleInput);

        // FIRST EXAMPLE
        Function<Integer, Integer> predefinedSquareFunc = inputNumber -> inputNumber * inputNumber;

        int fiveSquared = predefinedSquareFunc.apply(5); //use the '.apply(..)' to execute
        String squaredIntegerText1 = String.format("5 squared is %d", fiveSquared);
        System.out.println(squaredIntegerText1);

        String squaredIntegerText2 = String.format("7 squared is %d", predefinedSquareFunc.apply(7));
        System.out.println(squaredIntegerText2);

        System.out.println(String.format("12 squared is %d\n\n", predefinedSquareFunc.apply(12)));


        //SECOND EXAMPLE
        // The lambda can use all of the other multi-line, curly braces notation as other Lambdas
        Function<String, String> removeVowelsFunction = inputText -> {
            String noVowels = inputText.replaceAll("[aeiouAEIOU]", "");
            return noVowels;
        };

        String paNoVowels = removeVowelsFunction.apply("Pennsylvania");
        System.out.println(paNoVowels);

        String msNoVowels = removeVowelsFunction.apply("Mississippi");
        System.out.println(msNoVowels + "\n\n");


        // THIRD EXAMPLE
        Function<Integer, String> buildPhraseManyTimes = count -> {
            String outputText = "";
            int i = 0;
            while (i < count) {
                outputText += "Littering and... ";
                i++;
            }
            return outputText;
        };

        String repetativePhrase = buildPhraseManyTimes.apply(4);
        System.out.println(repetativePhrase);
    }
}

/*
#7 PREDEFINED FUNCTIONAL INTERFACE INTRO

    - WHAT: A "predefined functional interface"version of a functional interface that takes a single input
            parameter and returns a single output

    - WHY: Less methods. Saves code so you don't need to create a new method. Like other lambdas and functional
            interfaces, its less code you have to write so its easier to manage and has TDD time-saving implications.

    - SYNTAX:   Function <inputType, outputType> myVarName = exclusivelySoloParam -> logic
        - Generics here must be objects or primitive's Wrapper class (int => Integer)

    - The predefined function is accessed by using the '.apply(exclusivelySoloParam)' on the reference
            - Ex:
            Function <int, double> usdToRupee = usdAmt -> usdAmt * exchangeRate;
            double rupeeValForTenUsd = myVarName.apply(10);
*/
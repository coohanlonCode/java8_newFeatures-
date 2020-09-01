package com.myJ8NewFeatures.java8_newFeatures.examples;

import java.util.Set;
import java.util.TreeSet;

public class AllStringPermutations {

    private static Set<String> permutationsCollection;

    public static void main(String args[]) {

        permutationsCollection = new TreeSet<>();

        String wordToPermutate1 = "123";
        String wordToPermutate2 = "WXYZ";
        String wordToPermutate3 = "COLIN";
        String wordToPermutate4 = "COLIN2";

        String wordToPermutate = wordToPermutate4;

        permutate(wordToPermutate);

        System.out.println(permutationsCollection);
        System.out.printf("\nThere are %s possible permutations for the string '%s'\n",
                permutationsCollection.size(), wordToPermutate);
    }

    public static void permutate(String wordToPermutation) {
        permutate("", wordToPermutation);
    }

    private static void permutate(String knownPartOfPermutation, String wordToPermutate) {

        if (!wordToPermutate.isEmpty()) {

            String outputPermutation = knownPartOfPermutation + wordToPermutate;

            permutationsCollection.add(outputPermutation);

            String permutationIndex;
            String nextCharsInPerm;
            String everythingAfter;
            String currentWord;


            for (int i = 0; i < wordToPermutate.length(); i++) {

                permutationIndex = knownPartOfPermutation + wordToPermutate.charAt(i);

                nextCharsInPerm = wordToPermutate.substring(0, i);
                everythingAfter = wordToPermutate.substring(i + 1, wordToPermutate.length());

                currentWord = nextCharsInPerm + everythingAfter;

                permutate(permutationIndex, currentWord);
            }
        }
    }
}
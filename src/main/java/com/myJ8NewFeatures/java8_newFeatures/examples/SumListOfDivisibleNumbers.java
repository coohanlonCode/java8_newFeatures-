package com.myJ8NewFeatures.java8_newFeatures.examples;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class SumListOfDivisibleNumbers {

    /* QUESTION:
        If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
        The sum of these multiples is 23.

        Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the
        number passed in.

        Note: If the number is a multiple of both 3 and 5, only count it once.
     */

    public int solution(int number) {

        Set<Integer> allNumbers = new HashSet<>();

        while (0 < number--) {
            allNumbers.add(number);
        }

        return allNumbers.stream()
                .filter(i -> (i % 3 == 0 || i % 5 == 0))
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.summingInt(Integer::intValue));

    }

    public int nonJ8Solution(int number) {

        Set<Integer> underNumberDivFiveOrNine = new HashSet<Integer>();

        int i = 0;

        while (i < number) {

            if (i % 3 == 0 || i % 5 == 0) {
                underNumberDivFiveOrNine.add(i);
            }
            i++;
        }

        int total = 0;

        for (Integer myInt : underNumberDivFiveOrNine) {
            total += myInt;
        }

        return total;
    }
}

class SumListOfDivisibleNumbersTest {

    public static void main(String[] args) {

        SumListOfDivisibleNumbers utility = new SumListOfDivisibleNumbers();

        int solution = utility.solution(10);
        System.out.println("YES J8 solution = " + solution);
        assert (23 == solution);  // 3 + 5 + 6 + 9 = 23

        solution = utility.nonJ8Solution(10);
        System.out.println("Non J8 solution = " + solution);
        assert (23 == solution);  // 3 + 5 + 6 + 9 = 23

    }

}
package com.myJ8NewFeatures.java8_newFeatures;

public class DefaultInterfacing {

    public static void main(String args[]) {

        FirstClass myFirstClass = new FirstClass();
        int firstClassSum = myFirstClass.addTwoNumber(100, 200);
        System.out.println("Sum from the addTwoNumber(int, int) from the SecondClass = " + firstClassSum);
        System.out.println("\n");

        SecondClass mySecondClass = new SecondClass();
        int secondClassSum = mySecondClass.addTwoNumber(100, 200);
        System.out.println("Sum from the addTwoNumber(int, int) from the SecondClass = " + secondClassSum);
    }
}

interface MyInterfaceWithDefault {

    default public int addTwoNumber(int myFirst, int mySecond) {

        return myFirst + mySecond;
    }
}

class FirstClass implements MyInterfaceWithDefault {

    // You don't need to implement or override the interface's addTwoNumber(int, int) method bc the
    // class inherits the default implementation
}

class SecondClass implements MyInterfaceWithDefault {

    // You can override as normal it if you want
    public int addTwoNumber(int myFirst, int mySecond) {

        int sumFromDifferentBody = myFirst + mySecond;

        return sumFromDifferentBody;
    }
}

package com.myJ8NewFeatures.java8_newFeatures;

public class DefaultMethodInInterface {

    public static void main(String args[]) {

        FirstClassA myFirstClass = new FirstClassA();
        int firstClassSum = myFirstClass.addTwoNumber(100, 200);
        System.out.println("Sum from the addTwoNumber(int, int) from the SecondClass = " + firstClassSum);
        System.out.println("\n");

        SecondClassA mySecondClass = new SecondClassA();
        int secondClassSum = mySecondClass.addTwoNumber(100, 200);
        System.out.println("Sum from the addTwoNumber(int, int) from the SecondClass = " + secondClassSum);
    }
}

interface MyInterfaceWithDefault {

    default public int addTwoNumber(int myFirst, int mySecond) {

        return myFirst + mySecond;
    }
}

class FirstClassA implements MyInterfaceWithDefault {

    // You don't need to implement or override the interface's default addTwoNumber(int, int) method bc the
    // class inherits the default implementation
}

class SecondClassA implements MyInterfaceWithDefault {

    // You can override as normal it if you want
    public int addTwoNumber(int myFirst, int mySecond) {

        int sumFromDifferentBody = myFirst + mySecond;

        return sumFromDifferentBody;
    }
}

/*
DEFAULT INTERFACE METHODS

	- Allows you to add a shared method to an interface so all classes that are implementing that interface
	can have access to the code without needing to implement their own methods, but you can implement the method
	in a specific manner if the need/desire arises.

    - It is shared, identical functionality between two classes that do not share an inheritance structure
 */
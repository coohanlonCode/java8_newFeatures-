package com.myJ8NewFeatures.java8_newFeatures;

public class FunctionalInterfaces {
}

// SUCCESSFULLY compiles bc there is only 1 abstract method
@FunctionalInterface
interface MyFunctionalInterfaceA {
    public String doTheThing();
}

// When uncommented, FAILS compiles bc there would be MORE THAN 1 abstract methods
//@FunctionalInterface
interface MyNotFunctionalInterfaceB {

    public String doTheThing();

    public String doTheOtherThing();
}


// SUCCESSFULLY compiles bc there is only 1 abstract method. Default, static, & Object  methods are OK.
@FunctionalInterface
interface MyFunctionalInterfaceC {

    public String doTheThing();

    public default void doTheOtherThing() {
        System.out.println("DEFAULT methods are allowed!");
    }

    public static void doThatBrandNewThing() {
        System.out.println("Hey, hey STATIC methods are allowed!");
    }

    // These Object class methods are allowed
    public boolean equals(Object obj);
    public int hashCode();
    public String toString();

    // THESE OBJECT CLASS METHODS AREN'T ALLOWED FOR SOME REASON
    //    public Object clone();
    //    public void finalize();

    // THESE OBJECT CLASS METHODS AREN'T ALLOWED ONLY BC THEY ARE FINAL METHODS AND CAN NEVER BE EXTENDED
    //    Class<?> getClass();
    //    void notify();
    //    void notifyAll();
    //    void wait();
    //    void wait(long timeout);
    //    void wait(long timeout, int nanos)
}

/*
FUNCTIONAL INTERFACES

	- WHAT: A functional interface is an interface which ONLY has ONE abstact method.
        - Abstract method is a method that does not have an implemented body.

    - WHY: Functional interfaces are needed to support proper Lambda expression.

    - New '@FunctionalInterface' annotation exists in order to check whether or not that the interface is indeed a functional interface.
        - If it isn't a true functional interface, a compilation error occurs.
        - The annotation acts a checker in order to make sure it really acts as a functional interface

    - Functional interfaces ONLY have one abstract method/signature.

    - To create a Lambda expression, you REQUIRE an interface with ONE ABSTRACT METHOD

    - Functional interface are ALLOWED to have:
        - DEFAULT interface methods
        - STATIC interface
        - non-final methods/signatures from the OBJECT class
            - .equals(), .hashCode(), etc..
            - Everything in Java in an object so its okay bc these methods already exist.
 */
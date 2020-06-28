package com.myJ8NewFeatures.java8_newFeatures;

public class StaticMethodInInterface {

    public static void main(String args[]) {

        FirstClassB myFirstClass = new FirstClassB();

        System.out.printf("Via static interface method, " +
                "FirstClassB can get the quote '%s'\n\n", myFirstClass.getGoodQuote()); // WORKS


        FirstClassB mySecondClass = new FirstClassB();

        // Even though the class implements the interface with the static method, the static method is
        // part of the interface, not the class, so the class cannot refer to it

        // System.out.printf("Via static interface method, " +
        //        "SecondClassB can get the quote '%s'", mySecondClass.getQuote()); // DOES NOT WORK, COMPILE FAIL


        // In comparision, default methods can be overridden, so they are part of the call and
        // can be accessed this way.
        System.out.printf("Via DEFAULT interface method, " +
                "SecondClassB can get the quote '%s'\n\n", myFirstClass.returnQuoteViaDefault()); // WORKS
    }
}

interface MyInterfaceWithStatic {

    static public String getQuote() {
        return "Alright, meow, do you know why I pulled you over??";
    }

    default public String returnQuoteViaDefault() {
        return "What is so funny, right meow?";
    }
}

class FirstClassB implements MyInterfaceWithStatic {

    public String getGoodQuote() {

        // Class method must explicitly call out to the static interface method
        return MyInterfaceWithStatic.getQuote();
    }
}

class SecondClassB implements MyInterfaceWithStatic {
    // default methods can be implicitly accessed
}

/*
STATIC INTERFACE METHODS

    - A static method is a method defined in an interface with the 'static' keyword

    - Must have a method body and cannot be empty

    - Is used to provide a utility method in an interface

    - Is used to provide common functionality which can be reused in all implementing classes without needing to create individual implementation or worrying about the funcationality ever being overridden.

    - Static interface methods CANNOT BE OVERRIDDEN. The difference is Default interface methods are permitted to be overridden.

    - A single interface can have many static methods

    - Referenced code is called statically without an instantiated class being needed as the scope is granted by the implementation of the interface in the signautre
        - MyInterfaceThing.myStaticMethod()
 */
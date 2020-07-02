package com.myJ8NewFeatures.java8_newFeatures;

public class DoubleColonOperator {

    // Can refer to methods that are static
    static void staticOutputSomething() {
        System.out.println("\n\nThe static," +
                "\nsame return type," +
                "\nsame-name-as-functional-interface-method-name" +
                "\n'staticOutputSomething()' " +
                "\n  says, 'My sixth, maybe even seventh, sense...'");
    }

    static void staticOtherThingy() {
        System.out.println("\n\nThe static," +
                "\nsame return type," +
                "\nDIFFERENT-than-functional-interface-method-name" +
                "\n'staticOtherThingy()' " +
                "\n  says, 'Your suspension, continues!'");
    }

    // Can refer to methods that are non-static
    void outputSomething() {
        System.out.println("\n\nThe non-static," +
                "\nsame return type," +
                "\nsame-name-as-functional-interface-method-name" +
                "\n'outputSomething()' " +
                "\n  says, 'These boys get that syrup in 'em, they get all antsy in their pantsy'");
    }

    void somethingWayDifferent() {
        System.out.println("\n\nThe non-static," +
                "\nsame return type," +
                "\nDIFFERENT-than-functional-interface-method-name" +
                "\n'somethingWayDifferent()' " +
                "\n  says, 'I don't want a large Farva!'");
    }

    // When the return types don't match, that won't stop you.
    static String staticAndDifferentReturnType() {
        System.out.println("\n\nThe STATIC, " +
                "\nDIFFERENT-than-functional-interface-method-name" +
                "\n'DIFFERENT RETURN TYPE' " +
                "\n'notTheSameReturnType()' " +
                "\n  says, 'The snozzberries taste like snozzberries!'");

        return "This is returned but is NEVER actually used bc the " +
                "functional interface method is void, which gets prioritized over this declared String return type ";
    }

    String regularNotTheSameReturnType() {
        System.out.println("\n\nThe non-static, " +
                "\nDIFFERENT-than-functional-interface-method-name" +
                "\n'DIFFERENT RETURN TYPE' " +
                "\n'notTheSameReturnType()' " +
                "\n  says, 'You boys like Mexico?!'");

        return "This is returned but is NEVER actually used bc the " +
                "functional interface method is void, which gets prioritized over this declared String return type ";
    }

    public static void main(String[] args) {

        // If making a reference to a STATIC method
        // Interface interfaceVarName = ClassName::staticMethodNameToAssignToVariable;
        // interfaceVarName.functionalInterfaceMethodName(params)   // executes
        BuildSomeText1 fromStaticSameName1 = DoubleColonOperator::staticOutputSomething;
        fromStaticSameName1.outputSomething();

        BuildSomeText1 fromStaticOtherName2 = DoubleColonOperator::staticOtherThingy;
        fromStaticOtherName2.outputSomething();


        System.out.println("\n\n\nThe below examples are non-static and, accordingly, " +
                "required the creation of an DoubleColonOperator object.");
        DoubleColonOperator myClassInstance = new DoubleColonOperator();


        // If making a reference to a NON_STATIC, you need an instance
        // Interface interfaceVarName = localClassInstanceVarName::nonMethodNameToAssignToVariable;
        // interfaceVarName.functionalInterfaceMethodName(params)   // executes
        BuildSomeText1 fromNonStaticSameName3 = myClassInstance::outputSomething;
        fromNonStaticSameName3.outputSomething();

        BuildSomeText1 fromNonStaticDifferentName4 = myClassInstance::somethingWayDifferent;
        fromNonStaticDifferentName4.outputSomething();


        // If the method you are assigning to the functional interface/interface method has a different return type,
        // it doesn't matter bc the reference variable points to the interface method, so the return type
        // explicitly used in that interface method is what will pervail.
        BuildSomeText1 staticWithDifferentReturnType5 = DoubleColonOperator::staticAndDifferentReturnType;
        staticWithDifferentReturnType5.outputSomething();

        BuildSomeText1 nonStaticWithDifferentReturnType6 = myClassInstance::regularNotTheSameReturnType;
        nonStaticWithDifferentReturnType6.outputSomething();


        // Long-form, alternative notation
        System.out.println("\n\n(The following should be duplicate of the one above, just accessed by " +
                "the alternative syntax)");
        BuildSomeText1 nonStaticDifNameAltNotation = () -> myClassInstance.regularNotTheSameReturnType();
        nonStaticDifNameAltNotation.outputSomething();
    }
}

@FunctionalInterface
interface BuildSomeText1 {
    void outputSomething();
}

/*
DOUBLE COLON

    - Double colon ('::') is the METHOD REFERENCE

    - Used to allow one method to reference another method that has an identical parameter list.

    - Can refer to static and non-static methods

    - Acts as a lambda reference. Double colon can be viewed as an alternative syntaxt to lambda expression.
        Ex: the below two are equivalent
            MethodReference myRef = StaticMethodRefDemo::helloMethodReference
            MethodReference myRef = () -> StaticMethodRefDemo.helloMethodReference()

    - Double colon allows you to assign any method with identical parameters to a functional interface declaration.

    - Methods that refer to each other need:
        - Must assign to a functional interface
        - To have the exact same parameter declarations.

    - Methods that refer to each other DO NOT need:
        - To have the same method name.
        - To have the same return type

 */
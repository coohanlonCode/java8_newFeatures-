package com.myJ8NewFeatures.java8_newFeatures.predefinedFunctionalInterfaces;

import java.util.function.*;

public class SupplierFunction {

    public static void main(String[] args) {

        // Supplier<T> mySupplier = () -> do something and return;
        // T retrievedValue = mySupplier.get();

        // Supplier<whateverTypeYouWant>
        Supplier<Integer> getMaxClassSize = () -> 25;
        int lectureLimit  = getMaxClassSize.get();
        System.out.println(String.format("This class is limited to %d people.", lectureLimit));


        // NUMBER Supplier have shortcuts via subclasses
        IntSupplier niceWholeNumber = () -> 100;
        System.out.println(String.format("Get me a nice, whole integer : %d",
                niceWholeNumber.getAsInt()));

        LongSupplier reallyDetailedNumber = () -> 555L;
        System.out.println(String.format("Get me an long : %d",
                reallyDetailedNumber.getAsLong())); // method names are different in each

        DoubleSupplier someRandomRate = () -> 28.0151d;
        System.out.println(String.format("Get me a good APR on a Camaro  : %f", someRandomRate.getAsDouble()));

        BooleanSupplier decisionMade = () -> false;
        System.out.println(String.format("Mom always lets us eat candy for dinner : %b", decisionMade.getAsBoolean()));
    }
}
//TODO: use below object to nest multiple calls
//@formatter:off
class LectureInfo{
    private String professorName;
    private int classSize ;
    public LectureInfo(String professorName, int classSize) {
        this.professorName = professorName;
        this.classSize = classSize;
    }
    public String getProfessorName() { return professorName;   }
    public void setProfessorName(String professorName) {   this.professorName = professorName;  }
    public int getClassSize() { return classSize;  }
    public void setClassSize(int classSize) {   this.classSize = classSize;}
}

/*
#11 PREDEFINED FUNCTIONAL INTERFACES - SUPPLIER FUNCTION

    - WHAT: Takes no input and returns an ouput of a defined type.

    - WHY: 1. Saves space.
           2. You would want to use the method to replace all uses of the variable, bc if you use that
            variables a lot in that method and you need to change it, it can be safer to just change the declaration.
            - Ex: you have a method that uses the same variable like 6 times. Rather than having to adjust that ref
                6 times, you can use the Supplier. It would be safer than the potential accidentally of having that
                varible's state altered in some pass-by-reference.

    - SYNTAX:
        Supplier<T> mySupplier = () -> do somehting and return;
        T retrievedValue = mySupplier.get();

    - There are further subclasses as well
        - IntSupplier having one abstract method getAsInt()
        - LongSupplier having one abstract method getAsLong()
        - DoubleSupplier having one abstract method getAsDouble()
        - BooleanSupplier having one abstract method getAsBoolean()
 */
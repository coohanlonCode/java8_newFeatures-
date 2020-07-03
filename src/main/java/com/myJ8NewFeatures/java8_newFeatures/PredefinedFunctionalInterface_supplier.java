package com.myJ8NewFeatures.java8_newFeatures;

import java.util.function.*;

public class PredefinedFunctionalInterface_supplier {

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

    - WHY: saves space. I don't have a clear reason why to use this right now.

    - SYNTAX:
        Supplier<T> mySupplier = () -> do somehting and return;
        T retrievedValue = mySupplier.get();

    - There are further subclasses as well
        - IntSupplier having one abstract method getAsInt()
        - LongSupplier having one abstract method getAsLong()
        - DoubleSupplier having one abstract method getAsDouble()
        - BooleanSupplier having one abstract method getAsBoolean()
 */
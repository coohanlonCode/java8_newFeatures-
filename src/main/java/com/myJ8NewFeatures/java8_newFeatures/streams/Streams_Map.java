package com.myJ8NewFeatures.java8_newFeatures.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams_Map {

    public static void main(String[] args) {

        List<String> animalList = Arrays.asList("fOxEs", "deer", "ElK", "Bears", "SQUIRREL");
        System.out.println("\n\nState of original list before stream.map(capitalize): \n" + animalList); // NO all caps



        System.out.println("\n\nCapitalizing all names using stream.map stream function...");
        List<String> newList = animalList
                .stream()
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());

        System.out.println("\nState of new list collected from stream.map(capitalize): \n" + newList); // YES all caps
        System.out.println("\n\nState of original list AFTER stream.map(capitalize): \n" + animalList); // NO all caps



        System.out.println("\n\nCapitalizing all names in original list using list.forEach stream function...");
        animalList.forEach(name -> name.toUpperCase());

        System.out.println("\nState of new list collected from list.forEach(capitalize): \n" + newList); // YES all caps
    }
}
/*
STREAMS - MAP

			- WHAT: Applies the given function to every item in the stream and then a

			- WHY: Process everything in a collection and make a copy of the collection stream with those changes,
			    without directly modifying the original collection

			- SYNTAX:	myList
			                .stream
			                .map(functionToDoThingToEachElement)
			                .collect(Collectors.toList());

			- stream.map(..) is similar to collection.forEach(..)

				- They both process everything in the provided collection/strea,

				- stream.map(..) applies the changes to a new stream that can be collected. The original collection
				streamed is NOT modified.
				    - no side effects to original objects bc you are creating a new stream.

				- collection.forEach(..) applies the changes directly to the collection and YES THE OBJECTS in the
				colletion direclty have their state changed.
					- this can cause side effects, such as if applied to entity object using Spring Data JPA then db
					update processes would occur.
 */
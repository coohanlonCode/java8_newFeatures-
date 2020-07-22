 
 
- JAVA 8 Cheatsheet
	
	
	- New in Java 7 
	
		- #1 Multi-Catch
			- Allows multiple, differnt types of exceptions to be caught and processed the same way, in the same block.
			- Elimiantes the need to write multiple full blocks, eliminates redundancies
			- Use the '|' or single-pipe operator between the class type of exception, not a param
				- DO NOT USE '||' double-pipe
			- ONLY create 1 reference variable at the end that will represent the many exceptions.
				- giving each exception type a reference will cause compilation error
				
			
			- Example;  
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}
			
			
			- OLD:
			try{
				...
			} catch (Exception1 e1){   	// catch has parm reference name for each
				...
			} catch (Exception2 e2){ 	// catch has parm reference name for each
				...
			} catch (Exception2 e3){ 	// catch has parm reference name for each
				...
			}
			
			- NEW: 
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}
			
			
			
			
		- #2 Try-with-resources
			- Pros:
				- Fixes issues with being required to manually close resources, 
				- memory leak risk eliminated, 
				- no more need to manually check for NPE danger when closeing resources used, - eliminates need extra/redundant finally block
		
			- resources will automatically close at end of try block
				- helps with memory lead	 
			- Much shorted and easier to read
			- finally block is not needed to close the resources bc that is already taken care of.
			
			- seperate multiple resource with a semi-colon, except for the last one
			
			- if you try to close all of the resources using a finally block in additon to the try-with-resources, you will get a compilation error due to the resources not being in local scope. The resources are only in scope of the try block, not the finally block.
				- you can have a finally block, but you dont have scope reference to any of the resources used in the try
			
			- more enhancemnts to this are in java 9
		
		
			- OLD:
			
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				
				try {
					Class.forName(jdbcDriver);
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query)
				
					// do the stuff
				
				} catch (SqlException sqle){
					// print stacktrace
				} catch (ClassNotFoundException cnfe){
					// print stacktrace
				} finally {
					
					try{
					
						if(stmt != null) stmt.close(); // MANUALLY need to close stmt resource
					
					} catch (SqlException sqe){
						// print stacktrace
					}
					
					try{
					
						if(conn != null) conn.close(); // MANUALLT need to close conn resource
					
					} catch (SqlException sqe){
						// print stacktrace
					}
				}
				
				
				
			
			- NEW:
				try (Connection conn =  DriverManager.getConnection(url, user, password);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query)){
				
					// do the stuff
				
				} catch (SqlException |ClassNotFoundException excp){
					// print stacktrace
				} 
		
		
		- #3 The Diamond Operator <>
		
			- avoids redundancy with declaring generic types twice
				- the compiler will infer the generic type from the 
				- its bascially a blank generic
				
			-EX:
				OLD: List<String> myList = new ArrayList<String>();  
				NEW: List<String> myList = new ArrayList<>();
				
				// shit can get unnecessary complicated and the diamond cut this down
				OLD: List<Map<String, List<String>>> myList = new ArrayList<Map<String, List<String>>>();  
				NEW: List<Map<String, List<String>>> myList = new ArrayList<>();  
		
		
		
		- #4 Allows underscorces in numeric values

			- OLD: int creditCardNumer = 1234_56789;  // SYNTAX ERROR
			- NEW: int creditCardNumer = 1234_56789;  // Compiles successfully
			
			
			
			
		- #5 Strings in Switch Statements
		
			- only enums and integers could have been used in inputs case params for switch statements. Now strings are able to be used as well. 
		
			- OLD:
			
			if(param.equals("Java7")){
				...1
			}else if(param.equals("Java8")){
				...2
			}elseif(param.equals("Java9")){
				...3
			}elseif(param.equals("Java10")){
				...4
			}else{
				...5
			}
			
		
			- NEW: 
			
			switch(param){
				case "Java7":  
					...1;  
					break;
				case "Java8":  
					...2;  
					break;
				case "Java9":  
					...3;  
					break;
				case "Java10":  
					...4;  
					break;
				default: 
					...5;			
			}
			
		
		
		- #6 New File System Interactions
		
			- Why this was needed
				- Methods were not consisent with all operating systems (mac vs pc vs linux, etc)
				- Missing basica file operations
				- Limited support for fiel attributes
		
		
			- Old ways sucks bc:
				- required to use FileInputStream
				- needed to manually close the FileInputStream
				- lots of writing
		
			- OLD:
			
			private static void beforeJava7(){
			
				FileInputStream fis = null;
				String fileName = "D:/abc/xyz/myFile.txt";  //path to file on OS
				
				try{
				
				
					File file = new File(fileName);
					fis= new FileInputStream(file);

					int i;
					
					while( (i = fis.read() ) != -1  ){
						System.out.print( (char) i);
					}
				
				} catch (){
					// print stacktrace
				} catch (){
					// print stacktrace
				} finally{
				
					try{
						fis.close();
					} catch (IOException e){
						// print stacktrace
					}
				}
			}
			
			
			
			- NEW: 
			 - FileInputStream is not needed and you do not need to close the resources manually 
			
			private static void fromJava7(){
			
				String readContents = null;
				String inputFilePath = "D:/abc/xyz/myInputFile.txt";  //path to file on OS
				
				
				String writeContents = null;
				String outputFilePath = "D:/abc/xyz/myOutputFile.txt";  //path to file on OS
				
				
				try{
					// to Read from file
					Path readPath = Paths.get(fileName);
					readContents = new String(Files.readAllBytes(readPath));
					//
				
					// to Write to file
					Path writePath = Paths.get(outputFilePath);
					writeContents = "Text to output";
					Files.write(writePath , writeContents.getBytes());
					//
									
				} catch (IOException ioe){
					// print stacktrace
				}  
			}
		
	
	====================================
	====================================
	====================================
	====================================
	====================================


	- New in Java 8
	
		- New Features
			
			- Default methods in interfaces
			
			- Static methods in interfaces
			
			- ForEach Loop
			
			- Functional Interface
			
			- Lambda Expression
			
			- Predicate
			
			- Method references using the double colon (::)
			
			- Streams
		
		
			====================================
		
		- #1 Java Lambda Expression Introductions
		
			- way to present as Anonymous functions
				- anonymous = functions that do not have a name
				
			- Allows you to have multiple implementations of one interface method in a single class/block when you need to have some different functionality, but that functionality isnt so complicated that you need to create a new class to implement that interface.
			
			- Lambda parameters must in the functional assignemnt must match the parameter arguments in the interface method declaration. 
			
			
			- Benefits
				- dont need to create another class to implement. 
				- Less code needed
				
			
			- MUST connect to only 1interface that only has 1 method.
				-ex:
				interface AddInterfaceOne{  
					public int add( int a, int b);
				}
				
				public MyClass {
					
					public static void main(String[] args){
						
						AddInterfaceOne myThing = (a, b) -> a+b;
						
						int sum = myThing.add(10, 20);
					
					
					}
				}				
				
			- Very scripting-like syntax
			
			
			- lambdas do not have a defined scope
			
			- single line expressions do not need curly braces
				- must use curly braces when there are multiple lines/code block
				
			- BRACES REQUIRED when:
				- the 'return' keyword is used
				- multiple lines are used
			
			- 'return' keyword
				- single line lambdas have the 'return' keyword as optional. if using the 'return' keyword, then you must have curly braces around that one line or you will get compilation error.
				- multi line lambdas require the 'return' keyword. (can return null)
				
			- use a skinny arrow to delimit lambda params from lambda logic
			
			- LAMBDAS CAN BE ASSIGNED TO VARIABLES *****************************
				- like a functional expression call in javascritp
			
			- Lambda expression MUST end with a semi-colon
		
			- Syntax
				
				( /* params, 0..n, comma delimited */)  ->   //stuff to do with params;
				
				(a, b)  -> a+b
			
				() ->  System.out.println("Hello World");
				
		
			- Ex:
			
				- OLD: very typed and explicitly defined 
				
				public int addTwoNumbers(int a , int b){
					return a + b;
				}
				
				
				- As LAMBDA EXPRESSION
		
				MyInterface myInterface = (a, b) -> a + b;
		
		
		
		
		#2 DEFAULT Interface
			
			- Allows you to add a shared method to an interface so all classes that are implementing that interface can have access to the code without needing to implement their own methods
			
			- It is shared, identical functionality between two classes that do not share an inheirtance structure
			
			- This is new in Java 8. In Java 8 and later, Java interfaces can have implementations. Before Java 8, interfaces could only declare abstract method signatures and static contants 
			
			
			- NEW:
			
				public interface MyInterfaceWithDefault {
				
					default public int addTwoNumber(int myFirst, int mySecond){
						
						return myFirst + mySecond;
					}
				
				}
				
				public class FirstClass implements MyInterfaceWithDefault{
				
					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation
					
				}
				
				public class SecondClass implements MyInterfaceWithDefault{
				
				
					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){
						
						int sumFromDifferentBody = myFirst + mySecond;
						
						return sumFromDifferentBody;
					}
				}
		
		
		#3 STATIC Method
			
			- A static method is a method defined in an interface with the 'static' keyword
			
			- Must have a method body and cannot be empty
			
			- Is used to provide a utility method in an interface
		
			- Is used to provide common functionality which can be reused in all implementing classes without needing to create individual implementation or worrying about the funcationality ever being overridden.
		
			- Static interface methods CANNOT BE OVERRIDDEN. The difference is Default interface methods are permitted to be overridden. 
				
			- A single interface can have many static methods
			
			- Referenced code is called statically without an instantiated class being needed as the scope is granted by the implementation of the interface in the signautre
				- MyInterfaceThing.myStaticMethod()
				
		
			
			- NEW:
			
				public interface MyInterfaceWithDefault {
				
					 public static String getQuote(){
						
						return myFirst + mySecond;
					}
				
				}
				
				public class FirstClass implements MyInterfaceWithDefault{
				
					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation
					
				}
				
				public class SecondClass implements MyInterfaceWithDefault{
				
				
					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){
						
						int sumFromDifferentBody = myFirst + mySecond;
						
						return sumFromDifferentBody;
					}
				}
		
		
		
		
		#4 FUNCTIONAL Interface
			
			- WHAT: A functional interface is an interface which ONLY has ONE abstact method.
				- Abstract method is a method that does not have an implemented body.

			- WHY: Functional interfaces are needed to support proper Lambda expression.

			- New '@FunctionalInterface' annotation exists in order to check whether or not that the interface is indeed a functional interface.
				- If it isn't a true functional interface, a compilation error occurs.
				- The annotation acts a checker in order to make sure it really acts as a functional interface

			- Functional interfaces ONLY have one abstract method/signature.

			- Functional interface are ALLOWED to have:
				- DEFAULT interface methods
				- STATIC interface
				- non-final methods/signatures from the OBJECT class
					- .equals(), .hashCode(), etc..
					- Everything in Java in an object so its okay bc these methods already exist.


		#5 FUNCTIONAL PROGRAMMING IN JAVA

			- In Java, a method can be passed to another method by using a Lambda
				- Ex: addTwoNumbers( (a,b) -> a +b );

			- Passing a function to a method is called FUNCTIONAL PROGRAMMING

			- Passing an object in a method is OBJECT ORIENTED PROGRAMMING

			- Functional programming is good for simple things that are so easy to do that having their own method
			  would be unnecessary complications.
				- Save time and effort in TDD styled coding.

			- CREATE LAMBDA, ASSIGN LAMDA TO VARIBLE, and PASS THAT VARIABLE to a method that has that functional interface
				as a parameter type.


		#6 DOUBLE COLON

			- Double colon ('::') is the METHOD REFERENCE

			- Method reference can only be used with a functional interface

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



		#7 PREDEFINED FUNCTIONAL INTERFACE INTRO

			- WHAT: A "predefined functional interface"version of a functional interface that takes a single input parameter and returns a single output

			- WHY: Less methods. Saves code so you don't need to create a new method. Like other lambdas and functional interfaces, its less code you have to write so its easier to manage and has TDD time-saving implications.

			- SYNTAX:   Function <inputType, outputType> myVarName = exclusivelySoloParam -> /*logic*/
					-  Generics here must be objects or primitive's Wrapper class (int => Integer)

			- The predefined function is accessed by using the '.apply(exclusivelySoloParam)' on the reference
				- Ex:
				Function <int, double> usdToRupee = usdAmt -> usdAmt * exchangeRate;
				double rupeeValForTenUsd = myVarName.apply(10);



		#8 PREDEFINED FUNCTIONAL INTERFACES - ANDTHEN and COMPOSE

			- andThen and compose are basically the same thing but their execution order is opposite.
				- andThen and compose are default methods

			- WHAT: used to chain predefined functions together sequentially

			- WHY: easy way to adjust which equation runs first

			- andThen
				- It is a way of smashing multiple Functions together and running the first function then second function

				- Syntax:   function1.andThen(function2).apply(input);

				- Executes the left-most function first then uses its result as the input to the other function.

				- Ex:
						function1.andThen(method2).apply("Ohio")

					String xyz = function1.apply("Ohio")
					String finalText = method2.apply(xyz);

			- compose
				- It is a way of smashing multiple Functions together and running the SECOND function then FIRST function

				- Syntax:   function1.compose(function2).apply(input);

				- Executes the RIGHT-most function first then uses its result as the input to the other function.
					- (Opposite order than what andThen uses)

						function1.compose(method2).apply(input);

					- 1st. abc = method2.apply(input);
					- 2nd. xyz = function1.apply(abc)
					- 3rd. result is xyz


		#9 PREDEFINED FUNCTIONAL INTERFACES - IDENTITY FUNCTION

			- WHAT: Static method that returns exactly what was passed in

			- WHY: When you need to return an EXACT instance of an object in a scenario where having a functionally equilvalent object is a no-go

			- Syntax:   Function<TypeA, TypeA> myVarName = Function.identity(inputOfTypeA)




		#10 PREDEFINED FUNCTIONAL INTERFACES - PREDICATE FUNCTION

			- WHAT: Function interface method that accepts some type and implicitly returns a boolean

			- WHY: Used to shorthand yes/no decisions so you have a reusable utility that doesnt need to be its own method.

			- SYNTAX:
				Predicate<inputType> myVarName =  input ->  logic ;
				boolean decisonResult = myVarName.test(singleInput);



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

		#12 PREDEFINED FUNCTIONAL INTERFACES - Consumer FUNCTION

			- WHAT: takes a single input and return nothing/void. This is equivalent of a functional void method.

			- WHY:
				1. Save code
				2. Used to do utility tasks, such as logging, that do not return anything.
				3. To modify object by pass by reference

			- SYNTAX: Consumer<inputObjectType> myRefVar = inputParam -> logic
					  myRefVar.accept(inputObject);

		#13 PREDEFINED FUNCTIONAL INTERFACES - FOREACH FUNCTION

			- WHAT: function to shorthand to iterate each item in a collection (something that implements Collection<E>). This is intended to be a alternative to the enhanced for-each loop

			- WHY:
				1. Less code to write.

			- SYNTAX:  List<type> myList = new ArrayList<>();  //List implements Collection<>

						myList.forEach( function logic to do to each thing)


		#14 STREAMS - Intro

			- WHAT: A way to take sequential parts of a collections and process them without the need for a 'for' loop

			- WHY: Clean way to integrate functions into collections. Also to cut down on resources needed for a reserved 'for' loop.

			- SYNTAX:
					Collection<TypeInCollelction> myNewCollections = myOriginalCollection.stream().
						.myIntermediateOperation(myPredicateFunction)
						.terminalOperation(Collectors.staticMethodToDesiredCollection);

			- Made of 3 parts
				- 1 Source: creates a copy of the initial collection as a stream
				- 1..N Intermediate Operator: modifies the stream via a variety of operations, all return a stream that has a state that reflects the modifications
				- 1 Terminal Operator: converts stream to collection structure

			- Streams DO NOT Change the original data structure. They are used to create new data structures that are modified

		#15 XXXXXXXXXXXX

			- WHAT:

			- WHY:

			- SYNTAX:


		#666666 XXXXXXXXXXXX

		- WHAT:

		- WHY:

		- SYNTAX:

- JAVA 8 Cheatsheet


	- New in Java 7

		- #1 Multi-Catch
			- Allows multiple, differnt types of exceptions to be caught and processed the same way, in the same block.
			- Elimiantes the need to write multiple full blocks, eliminates redundancies
			- Use the '|' or single-pipe operator between the class type of exception, not a param
				- DO NOT USE '||' double-pipe
			- ONLY create 1 reference variable at the end that will represent the many exceptions.
				- giving each exception type a reference will cause compilation error


			- Example;
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}


			- OLD:
			try{
				...
			} catch (Exception1 e1){   	// catch has parm reference name for each
				...
			} catch (Exception2 e2){ 	// catch has parm reference name for each
				...
			} catch (Exception2 e3){ 	// catch has parm reference name for each
				...
			}

			- NEW:
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}




		- #2 Try-with-resources
			- Pros:
				- Fixes issues with being required to manually close resources,
				- memory leak risk eliminated,
				- no more need to manually check for NPE danger when closeing resources used, - eliminates need extra/redundant finally block

			- resources will automatically close at end of try block
				- helps with memory lead
			- Much shorted and easier to read
			- finally block is not needed to close the resources bc that is already taken care of.

			- seperate multiple resource with a semi-colon, except for the last one

			- if you try to close all of the resources using a finally block in additon to the try-with-resources, you will get a compilation error due to the resources not being in local scope. The resources are only in scope of the try block, not the finally block.
				- you can have a finally block, but you dont have scope reference to any of the resources used in the try

			- more enhancemnts to this are in java 9


			- OLD:

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					Class.forName(jdbcDriver);
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query)

					// do the stuff

				} catch (SqlException sqle){
					// print stacktrace
				} catch (ClassNotFoundException cnfe){
					// print stacktrace
				} finally {

					try{

						if(stmt != null) stmt.close(); // MANUALLY need to close stmt resource

					} catch (SqlException sqe){
						// print stacktrace
					}

					try{

						if(conn != null) conn.close(); // MANUALLT need to close conn resource

					} catch (SqlException sqe){
						// print stacktrace
					}
				}




			- NEW:
				try (Connection conn =  DriverManager.getConnection(url, user, password);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query)){

					// do the stuff

				} catch (SqlException |ClassNotFoundException excp){
					// print stacktrace
				}


		- #3 The Diamond Operator <>

			- avoids redundancy with declaring generic types twice
				- the compiler will infer the generic type from the
				- its bascially a blank generic

			-EX:
				OLD: List<String> myList = new ArrayList<String>();
				NEW: List<String> myList = new ArrayList<>();

				// shit can get unnecessary complicated and the diamond cut this down
				OLD: List<Map<String, List<String>>> myList = new ArrayList<Map<String, List<String>>>();
				NEW: List<Map<String, List<String>>> myList = new ArrayList<>();



		- #4 Allows underscorces in numeric values

			- OLD: int creditCardNumer = 1234_56789;  // SYNTAX ERROR
			- NEW: int creditCardNumer = 1234_56789;  // Compiles successfully




		- #5 Strings in Switch Statements

			- only enums and integers could have been used in inputs case params for switch statements. Now strings are able to be used as well.

			- OLD:

			if(param.equals("Java7")){
				...1
			}else if(param.equals("Java8")){
				...2
			}elseif(param.equals("Java9")){
				...3
			}elseif(param.equals("Java10")){
				...4
			}else{
				...5
			}


			- NEW:

			switch(param){
				case "Java7":
					...1;
					break;
				case "Java8":
					...2;
					break;
				case "Java9":
					...3;
					break;
				case "Java10":
					...4;
					break;
				default:
					...5;
			}



		- #6 New File System Interactions

			- Why this was needed
				- Methods were not consisent with all operating systems (mac vs pc vs linux, etc)
				- Missing basica file operations
				- Limited support for fiel attributes


			- Old ways sucks bc:
				- required to use FileInputStream
				- needed to manually close the FileInputStream
				- lots of writing

			- OLD:

			private static void beforeJava7(){

				FileInputStream fis = null;
				String fileName = "D:/abc/xyz/myFile.txt";  //path to file on OS

				try{


					File file = new File(fileName);
					fis= new FileInputStream(file);

					int i;

					while( (i = fis.read() ) != -1  ){
						System.out.print( (char) i);
					}

				} catch (){
					// print stacktrace
				} catch (){
					// print stacktrace
				} finally{

					try{
						fis.close();
					} catch (IOException e){
						// print stacktrace
					}
				}
			}



			- NEW:
			 - FileInputStream is not needed and you do not need to close the resources manually

			private static void fromJava7(){

				String readContents = null;
				String inputFilePath = "D:/abc/xyz/myInputFile.txt";  //path to file on OS


				String writeContents = null;
				String outputFilePath = "D:/abc/xyz/myOutputFile.txt";  //path to file on OS


				try{
					// to Read from file
					Path readPath = Paths.get(fileName);
					readContents = new String(Files.readAllBytes(readPath));
					//

					// to Write to file
					Path writePath = Paths.get(outputFilePath);
					writeContents = "Text to output";
					Files.write(writePath , writeContents.getBytes());
					//

				} catch (IOException ioe){
					// print stacktrace
				}
			}


	====================================
	====================================
	====================================
	====================================
	====================================


	- New in Java 8

		- New Features

			- Default methods in interfaces

			- Static methods in interfaces

			- ForEach Loop

			- Functional Interface

			- Lambda Expression

			- Predicate

			- Method references using the double colon (::)

			- Streams


			====================================

		- #1 Java Lambda Expression Introductions

			- way to present as Anonymous functions
				- anonymous = functions that do not have a name

			- Allows you to have multiple implementations of one interface method in a single class/block when you need to have some different functionality, but that functionality isnt so complicated that you need to create a new class to implement that interface.

			- Lambda parameters must in the functional assignemnt must match the parameter arguments in the interface method declaration.


			- Benefits
				- dont need to create another class to implement.
				- Less code needed


			- MUST connect to only 1interface that only has 1 method.
				-ex:
				interface AddInterfaceOne{
					public int add( int a, int b);
				}

				public MyClass {

					public static void main(String[] args){

						AddInterfaceOne myThing = (a, b) -> a+b;

						int sum = myThing.add(10, 20);


					}
				}

			- Very scripting-like syntax


			- lambdas do not have a defined scope

			- single line expressions do not need curly braces
				- must use curly braces when there are multiple lines/code block

			- BRACES REQUIRED when:
				- the 'return' keyword is used
				- multiple lines are used

			- 'return' keyword
				- single line lambdas have the 'return' keyword as optional. if using the 'return' keyword, then you must have curly braces around that one line or you will get compilation error.
				- multi line lambdas require the 'return' keyword. (can return null)

			- use a skinny arrow to delimit lambda params from lambda logic

			- LAMBDAS CAN BE ASSIGNED TO VARIABLES *****************************
				- like a functional expression call in javascritp

			- Lambda expression MUST end with a semi-colon

			- Syntax

				( /* params, 0..n, comma delimited */)  ->   //stuff to do with params;

				(a, b)  -> a+b

				() ->  System.out.println("Hello World");


			- Ex:

				- OLD: very typed and explicitly defined

				public int addTwoNumbers(int a , int b){
					return a + b;
				}


				- As LAMBDA EXPRESSION

				MyInterface myInterface = (a, b) -> a + b;




		#2 DEFAULT Interface

			- Allows you to add a shared method to an interface so all classes that are implementing that interface can have access to the code without needing to implement their own methods

			- It is shared, identical functionality between two classes that do not share an inheirtance structure

			- This is new in Java 8. In Java 8 and later, Java interfaces can have implementations. Before Java 8, interfaces could only declare abstract method signatures and static contants


			- NEW:

				public interface MyInterfaceWithDefault {

					default public int addTwoNumber(int myFirst, int mySecond){

						return myFirst + mySecond;
					}

				}

				public class FirstClass implements MyInterfaceWithDefault{

					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation

				}

				public class SecondClass implements MyInterfaceWithDefault{


					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){

						int sumFromDifferentBody = myFirst + mySecond;

						return sumFromDifferentBody;
					}
				}


		#3 STATIC Method

			- A static method is a method defined in an interface with the 'static' keyword

			- Must have a method body and cannot be empty

			- Is used to provide a utility method in an interface

			- Is used to provide common functionality which can be reused in all implementing classes without needing to create individual implementation or worrying about the funcationality ever being overridden.

			- Static interface methods CANNOT BE OVERRIDDEN. The difference is Default interface methods are permitted to be overridden.

			- A single interface can have many static methods

			- Referenced code is called statically without an instantiated class being needed as the scope is granted by the implementation of the interface in the signautre
				- MyInterfaceThing.myStaticMethod()



			- NEW:

				public interface MyInterfaceWithDefault {

					 public static String getQuote(){

						return myFirst + mySecond;
					}

				}

				public class FirstClass implements MyInterfaceWithDefault{

					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation

				}

				public class SecondClass implements MyInterfaceWithDefault{


					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){

						int sumFromDifferentBody = myFirst + mySecond;

						return sumFromDifferentBody;
					}
				}




		#4 FUNCTIONAL Interface

			- WHAT: A functional interface is an interface which ONLY has ONE abstact method.
				- Abstract method is a method that does not have an implemented body.

			- WHY: Functional interfaces are needed to support proper Lambda expression.

			- New '@FunctionalInterface' annotation exists in order to check whether or not that the interface is indeed a functional interface.
				- If it isn't a true functional interface, a compilation error occurs.
				- The annotation acts a checker in order to make sure it really acts as a functional interface

			- Functional interfaces ONLY have one abstract method/signature.

			- Functional interface are ALLOWED to have:
				- DEFAULT interface methods
				- STATIC interface
				- non-final methods/signatures from the OBJECT class
					- .equals(), .hashCode(), etc..
					- Everything in Java in an object so its okay bc these methods already exist.


		#5 FUNCTIONAL PROGRAMMING IN JAVA

			- In Java, a method can be passed to another method by using a Lambda
				- Ex: addTwoNumbers( (a,b) -> a +b );

			- Passing a function to a method is called FUNCTIONAL PROGRAMMING

			- Passing an object in a method is OBJECT ORIENTED PROGRAMMING

			- Functional programming is good for simple things that are so easy to do that having their own method
			  would be unnecessary complications.
				- Save time and effort in TDD styled coding.

			- CREATE LAMBDA, ASSIGN LAMDA TO VARIBLE, and PASS THAT VARIABLE to a method that has that functional interface
				as a parameter type.


		#6 DOUBLE COLON

			- Double colon ('::') is the METHOD REFERENCE

			- Method reference can only be used with a functional interface

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



		#7 PREDEFINED FUNCTIONAL INTERFACE INTRO

			- WHAT: A "predefined functional interface"version of a functional interface that takes a single input parameter and returns a single output

			- WHY: Less methods. Saves code so you don't need to create a new method. Like other lambdas and functional interfaces, its less code you have to write so its easier to manage and has TDD time-saving implications.

			- SYNTAX:   Function <inputType, outputType> myVarName = exclusivelySoloParam -> /*logic*/
					-  Generics here must be objects or primitive's Wrapper class (int => Integer)

			- The predefined function is accessed by using the '.apply(exclusivelySoloParam)' on the reference
				- Ex:
				Function <int, double> usdToRupee = usdAmt -> usdAmt * exchangeRate;
				double rupeeValForTenUsd = myVarName.apply(10);



		#8 PREDEFINED FUNCTIONAL INTERFACES - ANDTHEN and COMPOSE

			- andThen and compose are basically the same thing but their execution order is opposite.
				- andThen and compose are default methods

			- WHAT: used to chain predefined functions together sequentially

			- WHY: easy way to adjust which equation runs first

			- andThen
				- It is a way of smashing multiple Functions together and running the first function then second function

				- Syntax:   function1.andThen(function2).apply(input);

				- Executes the left-most function first then uses its result as the input to the other function.

				- Ex:
						function1.andThen(method2).apply("Ohio")

					String xyz = function1.apply("Ohio")
					String finalText = method2.apply(xyz);

			- compose
				- It is a way of smashing multiple Functions together and running the SECOND function then FIRST function

				- Syntax:   function1.compose(function2).apply(input);

				- Executes the RIGHT-most function first then uses its result as the input to the other function.
					- (Opposite order than what andThen uses)

						function1.compose(method2).apply(input);

					- 1st. abc = method2.apply(input);
					- 2nd. xyz = function1.apply(abc)
					- 3rd. result is xyz


		#9 PREDEFINED FUNCTIONAL INTERFACES - IDENTITY FUNCTION

			- WHAT: Static method that returns exactly what was passed in

			- WHY: When you need to return an EXACT instance of an object in a scenario where having a functionally equilvalent object is a no-go

			- Syntax:   Function<TypeA, TypeA> myVarName = Function.identity(inputOfTypeA)




		#10 PREDEFINED FUNCTIONAL INTERFACES - PREDICATE FUNCTION

			- WHAT: Function interface method that accepts some type and implicitly returns a boolean

			- WHY: Used to shorthand yes/no decisions so you have a reusable utility that doesnt need to be its own method.

			- SYNTAX:
				Predicate<inputType> myVarName =  input ->  logic ;
				boolean decisonResult = myVarName.test(singleInput);



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

		#12 PREDEFINED FUNCTIONAL INTERFACES - Consumer FUNCTION

			- WHAT: takes a single input and return nothing/void. This is equivalent of a functional void method.

			- WHY:
				1. Save code
				2. Used to do utility tasks, such as logging, that do not return anything.
				3. To modify object by pass by reference

			- SYNTAX: Consumer<inputObjectType> myRefVar = inputParam -> logic
					  myRefVar.accept(inputObject);

		#13 PREDEFINED FUNCTIONAL INTERFACES - FOREACH FUNCTION

			- WHAT: function to shorthand to iterate each item in a collection (something that implements Collection<E>). This is intended to be a alternative to the enhanced for-each loop

			- WHY:
				1. Less code to write.

			- SYNTAX:  List<type> myList = new ArrayList<>();  //List implements Collection<>

						myList.forEach( function logic to do to each thing)


		#14 STREAMS - Intro

			- WHAT: A way to take sequential parts of a collections and process them without the need for a 'for' loop

			- WHY: Clean way to integrate functions into collections. Also to cut down on resources needed for a reserved 'for' loop.

			- SYNTAX:
					Collection<TypeInCollelction> myNewCollections = myOriginalCollection.stream().
						.myIntermediateOperation(myPredicateFunction)
						.terminalOperation(Collectors.staticMethodToDesiredCollection);

			- Made of 3 parts
				- 1 Source: creates a copy of the initial collection as a stream
				- 1..N Intermediate Operator: modifies the stream via a variety of operations, all return a stream that has a state that reflects the modifications
				- 1 Terminal Operator: converts stream to collection structure

			- Streams DO NOT Change the original data structure. They are used to create new data structures that are modified

		#15 XXXXXXXXXXXX

			- WHAT:

			- WHY:

			- SYNTAX:


		#666666 XXXXXXXXXXXX

		- WHAT:

		- WHY:

		- SYNTAX:



- JAVA 8 Cheatsheet


	- New in Java 7

		- #1 Multi-Catch
			- Allows multiple, differnt types of exceptions to be caught and processed the same way, in the same block.
			- Elimiantes the need to write multiple full blocks, eliminates redundancies
			- Use the '|' or single-pipe operator between the class type of exception, not a param
				- DO NOT USE '||' double-pipe
			- ONLY create 1 reference variable at the end that will represent the many exceptions.
				- giving each exception type a reference will cause compilation error


			- Example;
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}


			- OLD:
			try{
				...
			} catch (Exception1 e1){   	// catch has parm reference name for each
				...
			} catch (Exception2 e2){ 	// catch has parm reference name for each
				...
			} catch (Exception2 e3){ 	// catch has parm reference name for each
				...
			}

			- NEW:
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}




		- #2 Try-with-resources
			- Pros:
				- Fixes issues with being required to manually close resources,
				- memory leak risk eliminated,
				- no more need to manually check for NPE danger when closeing resources used, - eliminates need extra/redundant finally block

			- resources will automatically close at end of try block
				- helps with memory lead
			- Much shorted and easier to read
			- finally block is not needed to close the resources bc that is already taken care of.

			- seperate multiple resource with a semi-colon, except for the last one

			- if you try to close all of the resources using a finally block in additon to the try-with-resources, you will get a compilation error due to the resources not being in local scope. The resources are only in scope of the try block, not the finally block.
				- you can have a finally block, but you dont have scope reference to any of the resources used in the try

			- more enhancemnts to this are in java 9


			- OLD:

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					Class.forName(jdbcDriver);
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query)

					// do the stuff

				} catch (SqlException sqle){
					// print stacktrace
				} catch (ClassNotFoundException cnfe){
					// print stacktrace
				} finally {

					try{

						if(stmt != null) stmt.close(); // MANUALLY need to close stmt resource

					} catch (SqlException sqe){
						// print stacktrace
					}

					try{

						if(conn != null) conn.close(); // MANUALLT need to close conn resource

					} catch (SqlException sqe){
						// print stacktrace
					}
				}




			- NEW:
				try (Connection conn =  DriverManager.getConnection(url, user, password);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query)){

					// do the stuff

				} catch (SqlException |ClassNotFoundException excp){
					// print stacktrace
				}


		- #3 The Diamond Operator <>

			- avoids redundancy with declaring generic types twice
				- the compiler will infer the generic type from the
				- its bascially a blank generic

			-EX:
				OLD: List<String> myList = new ArrayList<String>();
				NEW: List<String> myList = new ArrayList<>();

				// shit can get unnecessary complicated and the diamond cut this down
				OLD: List<Map<String, List<String>>> myList = new ArrayList<Map<String, List<String>>>();
				NEW: List<Map<String, List<String>>> myList = new ArrayList<>();



		- #4 Allows underscorces in numeric values

			- OLD: int creditCardNumer = 1234_56789;  // SYNTAX ERROR
			- NEW: int creditCardNumer = 1234_56789;  // Compiles successfully




		- #5 Strings in Switch Statements

			- only enums and integers could have been used in inputs case params for switch statements. Now strings are able to be used as well.

			- OLD:

			if(param.equals("Java7")){
				...1
			}else if(param.equals("Java8")){
				...2
			}elseif(param.equals("Java9")){
				...3
			}elseif(param.equals("Java10")){
				...4
			}else{
				...5
			}


			- NEW:

			switch(param){
				case "Java7":
					...1;
					break;
				case "Java8":
					...2;
					break;
				case "Java9":
					...3;
					break;
				case "Java10":
					...4;
					break;
				default:
					...5;
			}



		- #6 New File System Interactions

			- Why this was needed
				- Methods were not consisent with all operating systems (mac vs pc vs linux, etc)
				- Missing basica file operations
				- Limited support for fiel attributes


			- Old ways sucks bc:
				- required to use FileInputStream
				- needed to manually close the FileInputStream
				- lots of writing

			- OLD:

			private static void beforeJava7(){

				FileInputStream fis = null;
				String fileName = "D:/abc/xyz/myFile.txt";  //path to file on OS

				try{


					File file = new File(fileName);
					fis= new FileInputStream(file);

					int i;

					while( (i = fis.read() ) != -1  ){
						System.out.print( (char) i);
					}

				} catch (){
					// print stacktrace
				} catch (){
					// print stacktrace
				} finally{

					try{
						fis.close();
					} catch (IOException e){
						// print stacktrace
					}
				}
			}



			- NEW:
			 - FileInputStream is not needed and you do not need to close the resources manually

			private static void fromJava7(){

				String readContents = null;
				String inputFilePath = "D:/abc/xyz/myInputFile.txt";  //path to file on OS


				String writeContents = null;
				String outputFilePath = "D:/abc/xyz/myOutputFile.txt";  //path to file on OS


				try{
					// to Read from file
					Path readPath = Paths.get(fileName);
					readContents = new String(Files.readAllBytes(readPath));
					//

					// to Write to file
					Path writePath = Paths.get(outputFilePath);
					writeContents = "Text to output";
					Files.write(writePath , writeContents.getBytes());
					//

				} catch (IOException ioe){
					// print stacktrace
				}
			}


	====================================
	====================================
	====================================
	====================================
	====================================


	- New in Java 8

		- New Features

			- Default methods in interfaces

			- Static methods in interfaces

			- ForEach Loop

			- Functional Interface

			- Lambda Expression

			- Predicate

			- Method references using the double colon (::)

			- Streams


			====================================

		- #1 Java Lambda Expression Introductions

			- way to present as Anonymous functions
				- anonymous = functions that do not have a name

			- Allows you to have multiple implementations of one interface method in a single class/block when you need to have some different functionality, but that functionality isnt so complicated that you need to create a new class to implement that interface.

			- Lambda parameters must in the functional assignemnt must match the parameter arguments in the interface method declaration.


			- Benefits
				- dont need to create another class to implement.
				- Less code needed


			- MUST connect to only 1interface that only has 1 method.
				-ex:
				interface AddInterfaceOne{
					public int add( int a, int b);
				}

				public MyClass {

					public static void main(String[] args){

						AddInterfaceOne myThing = (a, b) -> a+b;

						int sum = myThing.add(10, 20);


					}
				}

			- Very scripting-like syntax


			- lambdas do not have a defined scope

			- single line expressions do not need curly braces
				- must use curly braces when there are multiple lines/code block

			- BRACES REQUIRED when:
				- the 'return' keyword is used
				- multiple lines are used

			- 'return' keyword
				- single line lambdas have the 'return' keyword as optional. if using the 'return' keyword, then you must have curly braces around that one line or you will get compilation error.
				- multi line lambdas require the 'return' keyword. (can return null)

			- use a skinny arrow to delimit lambda params from lambda logic

			- LAMBDAS CAN BE ASSIGNED TO VARIABLES *****************************
				- like a functional expression call in javascritp

			- Lambda expression MUST end with a semi-colon

			- Syntax

				( /* params, 0..n, comma delimited */)  ->   //stuff to do with params;

				(a, b)  -> a+b

				() ->  System.out.println("Hello World");


			- Ex:

				- OLD: very typed and explicitly defined

				public int addTwoNumbers(int a , int b){
					return a + b;
				}


				- As LAMBDA EXPRESSION

				MyInterface myInterface = (a, b) -> a + b;




		#2 DEFAULT Interface

			- Allows you to add a shared method to an interface so all classes that are implementing that interface can have access to the code without needing to implement their own methods

			- It is shared, identical functionality between two classes that do not share an inheirtance structure

			- This is new in Java 8. In Java 8 and later, Java interfaces can have implementations. Before Java 8, interfaces could only declare abstract method signatures and static contants


			- NEW:

				public interface MyInterfaceWithDefault {

					default public int addTwoNumber(int myFirst, int mySecond){

						return myFirst + mySecond;
					}

				}

				public class FirstClass implements MyInterfaceWithDefault{

					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation

				}

				public class SecondClass implements MyInterfaceWithDefault{


					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){

						int sumFromDifferentBody = myFirst + mySecond;

						return sumFromDifferentBody;
					}
				}


		#3 STATIC Method

			- A static method is a method defined in an interface with the 'static' keyword

			- Must have a method body and cannot be empty

			- Is used to provide a utility method in an interface

			- Is used to provide common functionality which can be reused in all implementing classes without needing to create individual implementation or worrying about the funcationality ever being overridden.

			- Static interface methods CANNOT BE OVERRIDDEN. The difference is Default interface methods are permitted to be overridden.

			- A single interface can have many static methods

			- Referenced code is called statically without an instantiated class being needed as the scope is granted by the implementation of the interface in the signautre
				- MyInterfaceThing.myStaticMethod()



			- NEW:

				public interface MyInterfaceWithDefault {

					 public static String getQuote(){

						return myFirst + mySecond;
					}

				}

				public class FirstClass implements MyInterfaceWithDefault{

					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation

				}

				public class SecondClass implements MyInterfaceWithDefault{


					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){

						int sumFromDifferentBody = myFirst + mySecond;

						return sumFromDifferentBody;
					}
				}




		#4 FUNCTIONAL Interface

			- WHAT: A functional interface is an interface which ONLY has ONE abstact method.
				- Abstract method is a method that does not have an implemented body.

			- WHY: Functional interfaces are needed to support proper Lambda expression.

			- New '@FunctionalInterface' annotation exists in order to check whether or not that the interface is indeed a functional interface.
				- If it isn't a true functional interface, a compilation error occurs.
				- The annotation acts a checker in order to make sure it really acts as a functional interface

			- Functional interfaces ONLY have one abstract method/signature.

			- Functional interface are ALLOWED to have:
				- DEFAULT interface methods
				- STATIC interface
				- non-final methods/signatures from the OBJECT class
					- .equals(), .hashCode(), etc..
					- Everything in Java in an object so its okay bc these methods already exist.


		#5 FUNCTIONAL PROGRAMMING IN JAVA

			- In Java, a method can be passed to another method by using a Lambda
				- Ex: addTwoNumbers( (a,b) -> a +b );

			- Passing a function to a method is called FUNCTIONAL PROGRAMMING

			- Passing an object in a method is OBJECT ORIENTED PROGRAMMING

			- Functional programming is good for simple things that are so easy to do that having their own method
			  would be unnecessary complications.
				- Save time and effort in TDD styled coding.

			- CREATE LAMBDA, ASSIGN LAMDA TO VARIBLE, and PASS THAT VARIABLE to a method that has that functional interface
				as a parameter type.


		#6 DOUBLE COLON

			- Double colon ('::') is the METHOD REFERENCE

			- Method reference can only be used with a functional interface

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



		#7 PREDEFINED FUNCTIONAL INTERFACE INTRO

			- WHAT: A "predefined functional interface"version of a functional interface that takes a single input parameter and returns a single output

			- WHY: Less methods. Saves code so you don't need to create a new method. Like other lambdas and functional interfaces, its less code you have to write so its easier to manage and has TDD time-saving implications.

			- SYNTAX:   Function <inputType, outputType> myVarName = exclusivelySoloParam -> /*logic*/
					-  Generics here must be objects or primitive's Wrapper class (int => Integer)

			- The predefined function is accessed by using the '.apply(exclusivelySoloParam)' on the reference
				- Ex:
				Function <int, double> usdToRupee = usdAmt -> usdAmt * exchangeRate;
				double rupeeValForTenUsd = myVarName.apply(10);



		#8 PREDEFINED FUNCTIONAL INTERFACES - ANDTHEN and COMPOSE

			- andThen and compose are basically the same thing but their execution order is opposite.
				- andThen and compose are default methods

			- WHAT: used to chain predefined functions together sequentially

			- WHY: easy way to adjust which equation runs first

			- andThen
				- It is a way of smashing multiple Functions together and running the first function then second function

				- Syntax:   function1.andThen(function2).apply(input);

				- Executes the left-most function first then uses its result as the input to the other function.

				- Ex:
						function1.andThen(method2).apply("Ohio")

					String xyz = function1.apply("Ohio")
					String finalText = method2.apply(xyz);

			- compose
				- It is a way of smashing multiple Functions together and running the SECOND function then FIRST function

				- Syntax:   function1.compose(function2).apply(input);

				- Executes the RIGHT-most function first then uses its result as the input to the other function.
					- (Opposite order than what andThen uses)

						function1.compose(method2).apply(input);

					- 1st. abc = method2.apply(input);
					- 2nd. xyz = function1.apply(abc)
					- 3rd. result is xyz


		#9 PREDEFINED FUNCTIONAL INTERFACES - IDENTITY FUNCTION

			- WHAT: Static method that returns exactly what was passed in

			- WHY: When you need to return an EXACT instance of an object in a scenario where having a functionally equilvalent object is a no-go

			- Syntax:   Function<TypeA, TypeA> myVarName = Function.identity(inputOfTypeA)




		#10 PREDEFINED FUNCTIONAL INTERFACES - PREDICATE FUNCTION

			- WHAT: Function interface method that accepts some type and implicitly returns a boolean

			- WHY: Used to shorthand yes/no decisions so you have a reusable utility that doesnt need to be its own method.

			- SYNTAX:
				Predicate<inputType> myVarName =  input ->  logic ;
				boolean decisonResult = myVarName.test(singleInput);



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

		#12 PREDEFINED FUNCTIONAL INTERFACES - Consumer FUNCTION

			- WHAT: takes a single input and return nothing/void. This is equivalent of a functional void method.

			- WHY:
				1. Save code
				2. Used to do utility tasks, such as logging, that do not return anything.
				3. To modify object by pass by reference

			- SYNTAX: Consumer<inputObjectType> myRefVar = inputParam -> logic
					  myRefVar.accept(inputObject);

		#13 PREDEFINED FUNCTIONAL INTERFACES - FOREACH FUNCTION

			- WHAT: function to shorthand to iterate each item in a collection (something that implements Collection<E>). This is intended to be a alternative to the enhanced for-each loop

			- WHY:
				1. Less code to write.

			- SYNTAX:  List<type> myList = new ArrayList<>();  //List implements Collection<>

						myList.forEach( function logic to do to each thing)


		#14 STREAMS - Intro

			- WHAT: A way to take sequential parts of a collections and process them without the need for a 'for' loop

			- WHY: Clean way to integrate functions into collections. Also to cut down on resources needed for a reserved 'for' loop.

			- SYNTAX:
					Collection<TypeInCollelction> myNewCollections = myOriginalCollection.stream().
						.myIntermediateOperation(myPredicateFunction)
						.terminalOperation(Collectors.staticMethodToDesiredCollection);

			- Made of 3 parts
				- 1 Source: creates a copy of the initial collection as a stream
				- 1..N Intermediate Operator: modifies the stream via a variety of operations, all return a stream that has a state that reflects the modifications
				- 1 Terminal Operator: converts stream to collection structure

			- Streams DO NOT Change the original data structure. They are used to create new data structures that are modified

		#15 XXXXXXXXXXXX

			- WHAT:

			- WHY:

			- SYNTAX:


		#666666 XXXXXXXXXXXX

		- WHAT:

		- WHY:

		- SYNTAX:



- JAVA 8 Cheatsheet


	- New in Java 7

		- #1 Multi-Catch
			- Allows multiple, differnt types of exceptions to be caught and processed the same way, in the same block.
			- Elimiantes the need to write multiple full blocks, eliminates redundancies
			- Use the '|' or single-pipe operator between the class type of exception, not a param
				- DO NOT USE '||' double-pipe
			- ONLY create 1 reference variable at the end that will represent the many exceptions.
				- giving each exception type a reference will cause compilation error


			- Example;
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}


			- OLD:
			try{
				...
			} catch (Exception1 e1){   	// catch has parm reference name for each
				...
			} catch (Exception2 e2){ 	// catch has parm reference name for each
				...
			} catch (Exception2 e3){ 	// catch has parm reference name for each
				...
			}

			- NEW:
			try{
				...
			} catch (Exception1 | Exception2 | Exception3 myExceptParamName){
				// catch has parm reference name ONLY ONCE AND AT THE END
				...
			}




		- #2 Try-with-resources
			- Pros:
				- Fixes issues with being required to manually close resources,
				- memory leak risk eliminated,
				- no more need to manually check for NPE danger when closeing resources used, - eliminates need extra/redundant finally block

			- resources will automatically close at end of try block
				- helps with memory lead
			- Much shorted and easier to read
			- finally block is not needed to close the resources bc that is already taken care of.

			- seperate multiple resource with a semi-colon, except for the last one

			- if you try to close all of the resources using a finally block in additon to the try-with-resources, you will get a compilation error due to the resources not being in local scope. The resources are only in scope of the try block, not the finally block.
				- you can have a finally block, but you dont have scope reference to any of the resources used in the try

			- more enhancemnts to this are in java 9


			- OLD:

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					Class.forName(jdbcDriver);
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query)

					// do the stuff

				} catch (SqlException sqle){
					// print stacktrace
				} catch (ClassNotFoundException cnfe){
					// print stacktrace
				} finally {

					try{

						if(stmt != null) stmt.close(); // MANUALLY need to close stmt resource

					} catch (SqlException sqe){
						// print stacktrace
					}

					try{

						if(conn != null) conn.close(); // MANUALLT need to close conn resource

					} catch (SqlException sqe){
						// print stacktrace
					}
				}




			- NEW:
				try (Connection conn =  DriverManager.getConnection(url, user, password);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query)){

					// do the stuff

				} catch (SqlException |ClassNotFoundException excp){
					// print stacktrace
				}


		- #3 The Diamond Operator <>

			- avoids redundancy with declaring generic types twice
				- the compiler will infer the generic type from the
				- its bascially a blank generic

			-EX:
				OLD: List<String> myList = new ArrayList<String>();
				NEW: List<String> myList = new ArrayList<>();

				// shit can get unnecessary complicated and the diamond cut this down
				OLD: List<Map<String, List<String>>> myList = new ArrayList<Map<String, List<String>>>();
				NEW: List<Map<String, List<String>>> myList = new ArrayList<>();



		- #4 Allows underscorces in numeric values

			- OLD: int creditCardNumer = 1234_56789;  // SYNTAX ERROR
			- NEW: int creditCardNumer = 1234_56789;  // Compiles successfully




		- #5 Strings in Switch Statements

			- only enums and integers could have been used in inputs case params for switch statements. Now strings are able to be used as well.

			- OLD:

			if(param.equals("Java7")){
				...1
			}else if(param.equals("Java8")){
				...2
			}elseif(param.equals("Java9")){
				...3
			}elseif(param.equals("Java10")){
				...4
			}else{
				...5
			}


			- NEW:

			switch(param){
				case "Java7":
					...1;
					break;
				case "Java8":
					...2;
					break;
				case "Java9":
					...3;
					break;
				case "Java10":
					...4;
					break;
				default:
					...5;
			}



		- #6 New File System Interactions

			- Why this was needed
				- Methods were not consisent with all operating systems (mac vs pc vs linux, etc)
				- Missing basica file operations
				- Limited support for fiel attributes


			- Old ways sucks bc:
				- required to use FileInputStream
				- needed to manually close the FileInputStream
				- lots of writing

			- OLD:

			private static void beforeJava7(){

				FileInputStream fis = null;
				String fileName = "D:/abc/xyz/myFile.txt";  //path to file on OS

				try{


					File file = new File(fileName);
					fis= new FileInputStream(file);

					int i;

					while( (i = fis.read() ) != -1  ){
						System.out.print( (char) i);
					}

				} catch (){
					// print stacktrace
				} catch (){
					// print stacktrace
				} finally{

					try{
						fis.close();
					} catch (IOException e){
						// print stacktrace
					}
				}
			}



			- NEW:
			 - FileInputStream is not needed and you do not need to close the resources manually

			private static void fromJava7(){

				String readContents = null;
				String inputFilePath = "D:/abc/xyz/myInputFile.txt";  //path to file on OS


				String writeContents = null;
				String outputFilePath = "D:/abc/xyz/myOutputFile.txt";  //path to file on OS


				try{
					// to Read from file
					Path readPath = Paths.get(fileName);
					readContents = new String(Files.readAllBytes(readPath));
					//

					// to Write to file
					Path writePath = Paths.get(outputFilePath);
					writeContents = "Text to output";
					Files.write(writePath , writeContents.getBytes());
					//

				} catch (IOException ioe){
					// print stacktrace
				}
			}


	====================================
	====================================
	====================================
	====================================
	====================================


	- New in Java 8

		- New Features

			- Default methods in interfaces

			- Static methods in interfaces

			- ForEach Loop

			- Functional Interface

			- Lambda Expression

			- Predicate

			- Method references using the double colon (::)

			- Streams


			====================================

		- #1 Java Lambda Expression Introductions

			- way to present as Anonymous functions
				- anonymous = functions that do not have a name

			- Allows you to have multiple implementations of one interface method in a single class/block when you need to have some different functionality, but that functionality isnt so complicated that you need to create a new class to implement that interface.

			- Lambda parameters must in the functional assignemnt must match the parameter arguments in the interface method declaration.


			- Benefits
				- dont need to create another class to implement.
				- Less code needed


			- MUST connect to only 1interface that only has 1 method.
				-ex:
				interface AddInterfaceOne{
					public int add( int a, int b);
				}

				public MyClass {

					public static void main(String[] args){

						AddInterfaceOne myThing = (a, b) -> a+b;

						int sum = myThing.add(10, 20);


					}
				}

			- Very scripting-like syntax


			- lambdas do not have a defined scope

			- single line expressions do not need curly braces
				- must use curly braces when there are multiple lines/code block

			- BRACES REQUIRED when:
				- the 'return' keyword is used
				- multiple lines are used

			- 'return' keyword
				- single line lambdas have the 'return' keyword as optional. if using the 'return' keyword, then you must have curly braces around that one line or you will get compilation error.
				- multi line lambdas require the 'return' keyword. (can return null)

			- use a skinny arrow to delimit lambda params from lambda logic

			- LAMBDAS CAN BE ASSIGNED TO VARIABLES *****************************
				- like a functional expression call in javascritp

			- Lambda expression MUST end with a semi-colon

			- Syntax

				( /* params, 0..n, comma delimited */)  ->   //stuff to do with params;

				(a, b)  -> a+b

				() ->  System.out.println("Hello World");


			- Ex:

				- OLD: very typed and explicitly defined

				public int addTwoNumbers(int a , int b){
					return a + b;
				}


				- As LAMBDA EXPRESSION

				MyInterface myInterface = (a, b) -> a + b;




		#2 DEFAULT Interface

			- Allows you to add a shared method to an interface so all classes that are implementing that interface can have access to the code without needing to implement their own methods

			- It is shared, identical functionality between two classes that do not share an inheirtance structure

			- This is new in Java 8. In Java 8 and later, Java interfaces can have implementations. Before Java 8, interfaces could only declare abstract method signatures and static contants


			- NEW:

				public interface MyInterfaceWithDefault {

					default public int addTwoNumber(int myFirst, int mySecond){

						return myFirst + mySecond;
					}

				}

				public class FirstClass implements MyInterfaceWithDefault{

					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation

				}

				public class SecondClass implements MyInterfaceWithDefault{


					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){

						int sumFromDifferentBody = myFirst + mySecond;

						return sumFromDifferentBody;
					}
				}


		#3 STATIC Method

			- A static method is a method defined in an interface with the 'static' keyword

			- Must have a method body and cannot be empty

			- Is used to provide a utility method in an interface

			- Is used to provide common functionality which can be reused in all implementing classes without needing to create individual implementation or worrying about the funcationality ever being overridden.

			- Static interface methods CANNOT BE OVERRIDDEN. The difference is Default interface methods are permitted to be overridden.

			- A single interface can have many static methods

			- Referenced code is called statically without an instantiated class being needed as the scope is granted by the implementation of the interface in the signautre
				- MyInterfaceThing.myStaticMethod()



			- NEW:

				public interface MyInterfaceWithDefault {

					 public static String getQuote(){

						return myFirst + mySecond;
					}

				}

				public class FirstClass implements MyInterfaceWithDefault{

					// doesnt need to implement or override the interface' addTwoNumber method bc the class inheirts the default implementation

				}

				public class SecondClass implements MyInterfaceWithDefault{


					// you can override it if you want
					public int addTwoNumber(int myFirst, int mySecond){

						int sumFromDifferentBody = myFirst + mySecond;

						return sumFromDifferentBody;
					}
				}




		#4 FUNCTIONAL Interface

			- WHAT: A functional interface is an interface which ONLY has ONE abstact method.
				- Abstract method is a method that does not have an implemented body.

			- WHY: Functional interfaces are needed to support proper Lambda expression.

			- New '@FunctionalInterface' annotation exists in order to check whether or not that the interface is indeed a functional interface.
				- If it isn't a true functional interface, a compilation error occurs.
				- The annotation acts a checker in order to make sure it really acts as a functional interface

			- Functional interfaces ONLY have one abstract method/signature.

			- Functional interface are ALLOWED to have:
				- DEFAULT interface methods
				- STATIC interface
				- non-final methods/signatures from the OBJECT class
					- .equals(), .hashCode(), etc..
					- Everything in Java in an object so its okay bc these methods already exist.



		#5 FUNCTIONAL PROGRAMMING IN JAVA

			- In Java, a method can be passed to another method by using a Lambda
				- Ex: addTwoNumbers( (a,b) -> a +b );

			- Passing a function to a method is called FUNCTIONAL PROGRAMMING

			- Passing an object in a method is OBJECT ORIENTED PROGRAMMING

			- Functional programming is good for simple things that are so easy to do that having their own method
			  would be unnecessary complications.
				- Save time and effort in TDD styled coding.

			- CREATE LAMBDA, ASSIGN LAMDA TO VARIBLE, and PASS THAT VARIABLE to a method that has that functional interface
				as a parameter type.


		#6 DOUBLE COLON

			- Double colon ('::') is the METHOD REFERENCE

			- Method reference can only be used with a functional interface

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



		#7 PREDEFINED FUNCTIONAL INTERFACE INTRO

			- WHAT: A "predefined functional interface"version of a functional interface that takes a single input parameter and returns a single output

			- WHY: Less methods. Saves code so you don't need to create a new method. Like other lambdas and functional interfaces, its less code you have to write so its easier to manage and has TDD time-saving implications.

			- SYNTAX:   Function <inputType, outputType> myVarName = exclusivelySoloParam -> /*logic*/
					-  Generics here must be objects or primitive's Wrapper class (int => Integer)

			- The predefined function is accessed by using the '.apply(exclusivelySoloParam)' on the reference
				- Ex:
				Function <int, double> usdToRupee = usdAmt -> usdAmt * exchangeRate;
				double rupeeValForTenUsd = myVarName.apply(10);




		#8 PREDEFINED FUNCTIONAL INTERFACES - ANDTHEN and COMPOSE

			- andThen and compose are basically the same thing but their execution order is opposite.
				- andThen and compose are default methods

			- WHAT: used to chain predefined functions together sequentially

			- WHY: easy way to adjust which equation runs first

			- andThen
				- It is a way of smashing multiple Functions together and running the first function then second function

				- Syntax:   function1.andThen(function2).apply(input);

				- Executes the left-most function first then uses its result as the input to the other function.

				- Ex:
						function1.andThen(method2).apply("Ohio")

					String xyz = function1.apply("Ohio")
					String finalText = method2.apply(xyz);

			- compose
				- It is a way of smashing multiple Functions together and running the SECOND function then FIRST function

				- Syntax:   function1.compose(function2).apply(input);

				- Executes the RIGHT-most function first then uses its result as the input to the other function.
					- (Opposite order than what andThen uses)

						function1.compose(method2).apply(input);

					- 1st. abc = method2.apply(input);
					- 2nd. xyz = function1.apply(abc)
					- 3rd. result is xyz



		#9 PREDEFINED FUNCTIONAL INTERFACES - IDENTITY FUNCTION

			- WHAT: Static method that returns exactly what was passed in

			- WHY: When you need to return an EXACT instance of an object in a scenario where having a functionally equilvalent object is a no-go

			- Syntax:   Function<TypeA, TypeA> myVarName = Function.identity(inputOfTypeA)




		#10 PREDEFINED FUNCTIONAL INTERFACES - PREDICATE FUNCTION

			- WHAT: Function interface method that accepts some type and implicitly returns a boolean

			- WHY: Used to shorthand yes/no decisions so you have a reusable utility that doesnt need to be its own method.

			- SYNTAX:
				Predicate<inputType> myVarName =  input ->  logic ;
				boolean decisonResult = myVarName.test(singleInput);




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



		#12 PREDEFINED FUNCTIONAL INTERFACES - Consumer FUNCTION

			- WHAT: takes a single input and return nothing/void. This is equivalent of a functional void method.

			- WHY:
				1. Save code
				2. Used to do utility tasks, such as logging, that do not return anything.
				3. To modify object by pass by reference

			- SYNTAX: Consumer<inputObjectType> myRefVar = inputParam -> logic
					  myRefVar.accept(inputObject);



		#13 PREDEFINED FUNCTIONAL INTERFACES - FOREACH FUNCTION

			- WHAT: function to shorthand to iterate each item in a collection (something that implements Collection<E>). This is intended to be a alternative to the enhanced for-each loop

			- WHY:
				1. Less code to write.

			- SYNTAX:  List<type> myList = new ArrayList<>();  //List implements Collection<>

						myList.forEach( function logic to do to each thing)




		#14 STREAMS - Intro

			- WHAT: A way to take sequential parts of a collections and process them without the need for a 'for' loop

			- WHY: Clean way to integrate functions into collections. Also to cut down on resources needed for a reserved 'for' loop.

			- SYNTAX:
					Collection<TypeInCollelction> myNewCollections = myOriginalCollection.stream().
						.myIntermediateOperation(myPredicateFunction)
						.terminalOperation(Collectors.staticMethodToDesiredCollection);

			- Made of 3 parts
				- 1 Source: creates a copy of the initial collection as a stream
				- 1..N Intermediate Operator: modifies the stream via a variety of operations, all return a stream that has a state that reflects the modifications
				- 1 Terminal Operator: converts stream to collection structure

			- Streams DO NOT Change the original data structure. They are used to create new data structures that are modified



		#15 STREAMS - FILTER

			- WHAT: Intermediate operation that accepts a functional Predicate and returns a new stream with elements from the original stream that evaluate to true under that Predicate

			- WHY: Functionally create a subset withouthaving the original modified

			- SYNTAX:	myCollection.stream().filter(myPredicate).myTerminalOp();



		#16 STREAMS - LIMIT

			- WHAT: Intermediate operation that truncates a stream to include only the first X items.

			- WHY: Create a subset based on position without modifying the origina

			- SYNTAX:   myCollection.stream().limit(long).myTerminalAction()



		#17 STREAMS - SORTED

			- WHAT: Way to create a stream that sorted according to a natural order or by a customized order

			- WHY: Sort a set without having modified the original datastructure

			- SYNTAX:
				myList.stream.sorted()
				myList.stream.sorted(Comparator<String>)  // define a Comparator impl
				myList.stream.sorted( (obj1, obj2) -> logic comparing two) // lamba impl of Comparator



		#18 STREAMS - MAP

			- WHAT: Applies the given function to every item in the stream and then a

			- WHY: Process everything in a collection and make a copy of the collection stream with those changes, without directly modifying the original collection

			- SYNTAX:	myList.stream.map(functionToDoThingToEachElement)

			- stream.map(..) is similar to collection.forEach(..)

				- They both process everything in the provided collection/strea,

				- stream.map(..) applies the changes to a new stream that can be collected. The original collection streamed is NOT modified.
					- no side effects to original objects bc you are creating a new stream.

				- collection.forEach(..) applies the changes directly to the collection and YES THE OBJECTS in the colletion direclty have their state changed.
					- this can cause side effects, such as if applied to entity object using Spring Data JPA then db update processes would occur.

		#19 STREAMS - FLATMAP

			- WHAT:

			- WHY:

			- SYNTAX:

		#20 STREAMS - PEEK

			- WHAT:

			- WHY:

			- SYNTAX:

		#21 STREAMS - COLLECT

			- WHAT:

			- WHY:

			- SYNTAX:

		#22 STREAMS - COUNT

			- WHAT:

			- WHY:

			- SYNTAX:

		#23 STREAMS - FOREACH

			- WHAT:

			- WHY:

			- SYNTAX:

		#24 STREAMS - REDUCE

			- WHAT:

			- WHY:

			- SYNTAX:


		#25 STREAMS - MIN

			- WHAT:

			- WHY:

			- SYNTAX:


		#26 STREAMS - MAX

			- WHAT:

			- WHY:

			- SYNTAX:




		#666666 XXXXXXXXXXXX

		- WHAT:

		- WHY:

		- SYNTAX:


		#666666 XXXXXXXXXXXX

		- WHAT:

		- WHY:

		- SYNTAX:


		#666666 XXXXXXXXXXXX

		- WHAT:

		- WHY:

		- SYNTAX:


		#666666 XXXXXXXXXXXX

		- WHAT:

		- WHY:

		- SYNTAX:




---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Introduction to Types
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Type Systems

::left::
* We now must leave the safety of Crafting Interpreters behind
  * Carry on my wayward sons and daughters
* We are now going to learn about the magical world of type systems
* In particular, we are going to look at static type systems

::right::
![introduction_to_types_1.png](introduction_to_types_1.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* Lox, like many programming languages today, is dynamically typed
  * Javascript
  * Python
  * Hyperscript
* Catscript, on the other hand, is statically typed
  * Java
  * C#

::right::
![introduction_to_types_1.png](introduction_to_types_1.png)


---
layout: two-cols-header
---

## Dynamic Typing

::left::
* Dynamic typing is the happy-go-lucky kid of the type system world
* At right is some javascript code
* Note that you can assign whatever you want to x
* The compiler makes no guarantees about it

::right::
![introduction_to_types_2.png](introduction_to_types_2.png)


---
layout: two-cols-header
---

## Dynamic Typing

::left::
* In particular, note that the .length property call is on completely unrelated objects
  * One is a string
  * One is an array
* The runtime doesn't care, as long as you have a property called length
  * And it doesn't care what it returns either!

::right::
![introduction_to_types_2.png](introduction_to_types_2.png)


---
layout: two-cols-header
---

## Dynamic Typing

::left::
* This is sometimes referred to as “Duck Typing”
* If it walks like a duck and quacks like a duck, it's a duck
* If the thing has a length property on it, it's “lengthable”
* Good enough for JavaScript (and python, etc.)

::right::
![introduction_to_types_3.png](introduction_to_types_3.png)


---
layout: two-cols-header
---

## Static Typing

::left::
* Contrast this with the Java version of the same code
* Here we have a huge number of compilation errors
* These errors are not parse errors (this is all grammatical acceptable Java)
* Rather this is due to semantic errors, derived from the java type system

::right::
![introduction_to_types_4.png](introduction_to_types_4.png)


---
layout: two-cols-header
---

## Static Typing

::left::
* The Java type system is statically typed
* Static typing: the type of any symbol or expression can be determined at compile time
* That is statically
* This allows us to detect errors like:
  * Attempting to assign a value of the wrong type
  * Attempting to invoke a method that doesn't exist

::right::
![introduction_to_types_4.png](introduction_to_types_4.png)


---
layout: two-cols-header
---

## Static Typing

::left::
* A common question in any statically typed language is: “Is this assignable to that, safely?”  
* See the method:  java.lang.Class.isAssignableFrom()

::right::
![introduction_to_types_4.png](introduction_to_types_4.png)


---
layout: two-cols-header
---

## Static Typing

::left::
* Here you can see that java has determined that the symbol x has a certain type
  * java.lang.Integer
* It will not allow you to assign values that are not compatible with that type to the value x
* Can we fix this?

::right::
![introduction_to_types_4.png](introduction_to_types_4.png)


---
layout: two-cols-header
---

## Static Typing

::left::
* Yes, partially: we can make x explicitly of type Object 
* Now we can assign whatever values we'd like to x, but we can't invoke the getLength() method

::right::
![introduction_to_types_5.png](introduction_to_types_5.png)


---
layout: two-cols-header
---

## Static Typing

::left::
* This is, I hope, intuitive to all of you 
* Who here is in camp static typing? 
* Who here is in camp dynamic typing?

::right::
![introduction_to_types_5.png](introduction_to_types_5.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* Taking a step back, what is a type system? 

“A logical system of rules that assigns a type to constructs in a programming system and enforces rules about those types”

::right::
![introduction_to_types_5.png](introduction_to_types_5.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* OK, so what is a type? 

“A type is an attribute of data which tells a compiler or interpreter how the programmer intends to use that data”

::right::
![introduction_to_types_5.png](introduction_to_types_5.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* And what is the goal of a type system? 

“The main purpose of a type system is to reduce possibilities for bugs in computer programs  by defining interfaces between different parts of a computer program, and then checking that the parts have been connected in a consistent way.”

::right::
![introduction_to_types_5.png](introduction_to_types_5.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* The code at right is an example of a TypeError in the Java Type System 
  * Because java cannot guarantee that this code is correct (even if it might be) it does not allow it to compile
  * But what if we know something will work?
  * I mean, strings and lists have lengths in java, right?

::right::
![introduction_to_types_5.png](introduction_to_types_5.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* Yes, in the Java language we are allowed to downcast to another type 
  * Here we cast x first to String and invoke the length() method 
  * Then we cast to List and call the size() method on it

::right::
![introduction_to_types_6.png](introduction_to_types_6.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* But what if we modified the code slightly, and put the assignment of the List before the cast to String?  
* What happens?

::right::
![introduction_to_types_7.png](introduction_to_types_7.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* We get a ClassCastException at runtime 
* What does this imply about the Java runtime?

::right::
![introduction_to_types_7.png](introduction_to_types_7.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* The java type system isn't just static, at compile time 
* There is also a runtime and dynamic aspect to it
  * At runtime it needs to check if in fact an object is of a given type when you downcast

::right::
![introduction_to_types_7.png](introduction_to_types_7.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* So maybe Java isn't quite as safe as team static typing might think
* “OK, Carson, but here you were intentionally downcasting to a silly class you knew wasn't going to work.”

::right::
![introduction_to_types_7.png](introduction_to_types_7.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* OK, fair point
* But what if I told you there's a way to break the java type system without downcasting? 
* In fact, without \*any\* compilation errors!


::right::
![introduction_to_types_7.png](introduction_to_types_7.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* Consider the code at right
* This compiles fine and doesn't involve any casts 
* Note that String[] is assignable to Object[] 
* So, what's the type rule here?

::right::
![introduction_to_types_8.png](introduction_to_types_8.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* If Type A is assignable to Type B, then A[] (the array type of A) is assignable to B[] (the array type of B)
* More formally, an array type is covariant with its component type


::right::
![introduction_to_types_8.png](introduction_to_types_8.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* Covariance: if the component types are compatible, the complex types are compatible
* Variance refers to how subtyping between more complex types relates to the subtyping between their component types

::right::
![introduction_to_types_8.png](introduction_to_types_8.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* So, the java rule for arrays sounds reasonable, right?
* Sounds reasonable, but this type rule isn't sound 
* The code at right shows why: we can write a Boolean into an array of Strings!

::right::
![introduction_to_types_8.png](introduction_to_types_8.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* Now, the people who created Java were pretty smart
* So why did they do this? 
* It turns out that by weakening the type system, it makes some styles of programming a lot easier

::right::
![introduction_to_types_8.png](introduction_to_types_8.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* Consider the code for printArray() at right
* This works regardless of the type of array that is passed in
  * String[], Boolean[], whatever
* By weakening the type system a bit here, the java designers made it much easier to create certain types of libraries

::right::
![introduction_to_types_9.png](introduction_to_types_9.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* The reality is that arrays are mostly read from, not written into
* ArrayStoreExceptions are not a major problem in java programs
  * Has anyone ever seen one before?
  * How about NullPointerException?

::right::
![introduction_to_types_9.png](introduction_to_types_9.png)


---
layout: two-cols-header
---

## Type Systems

::left::
* So this was a pragmatic decision by the early java designers
* Now, what happened when Java added Generics?

::right::
![introduction_to_types_9.png](introduction_to_types_9.png)


---
layout: two-cols-header
---

## Generics

::left::
* They decided to fix this hole and not allow List\<String\> to be assignable to List\<Object\>
* Java Generics are not covariant with their component type(s)

::right::
![introduction_to_types_10.png](introduction_to_types_10.png)


---
layout: two-cols-header
---

## Generics

::left::
* In fact, they don't even let you cast  between them!
* Super duper type safe!

::right::
![introduction_to_types_11.png](introduction_to_types_11.png)


---
layout: two-cols-header
---

## Generics

::left::
* Err...

::right::
![introduction_to_types_12.png](introduction_to_types_12.png)


---
layout: two-cols-header
---

## Generics

::left::
* You can cast to and from the non-generic version of a List…
* So you can use this to circumvent the whole generics type system
  * And, in fact, you won't even get a runtime error if you put a boolean into this list!

::right::
![introduction_to_types_12.png](introduction_to_types_12.png)


---
layout: two-cols-header
---

## Generics

::left::
* You can cast to and from the non-generic version of a List…
* So you can use this to circumvent the whole generics type system
  * And, in fact, you won't even get a runtime error if you put a boolean into this list!

::right::
![introduction_to_types_13.png](introduction_to_types_13.png)


---
layout: two-cols-header
---

## Generics

::left::
* Now, the generic team was aware of the issue with generics not being covariant and they wanted to help
* So they introduced wildcard types

::right::
![introduction_to_types_14.png](introduction_to_types_14.png)


---
layout: two-cols-header
---

## Generics

::left::
* This says that the objects list is a list of some type that extends Object
* This List type is covariant with List\<String\>!
* You can iterate over it and print out all the strings, just like in our Object[] example!

::right::
![introduction_to_types_15.png](introduction_to_types_15.png)


---
layout: two-cols-header
---

## Generics

::left::
* HOWEVER!
* You cannot add elements to the list
* In fact, you can never write into any parameter of the component type of a list with a wildcard component type
* This is what makes it “safe”

::right::
![introduction_to_types_16.png](introduction_to_types_16.png)


---
layout: two-cols-header
---

## Generics

::left::
* Of course that's deeply annoying and you can always just cast to the generic type and do so…

::right::
![introduction_to_types_17.png](introduction_to_types_17.png)


---
layout: two-cols-header
---

## Generics

::left::
* Of course that's deeply annoying and you can always just cast to the generic type and do so…

::right::
![introduction_to_types_18.png](introduction_to_types_18.png)


---
layout: two-cols-header
---

## Generics

::left::
* Java generics spiraled out of control and became an incredibly complicated part of the language
* And, in reality, they don't actually add that much type safety to the language
  * Even if they did, would it be worth it?

::right::
![introduction_to_types_19.png](introduction_to_types_19.png)


---
layout: default
---

## Languages

![introduction_to_types_20.png](introduction_to_types_20.png)


---
layout: two-cols-header
---

## Catscript

::left::
* Consider the list type in Catscript
* The code at right is legal Catscript
* But wait, haven't we fallen into the same trap as java arrays?

::right::
![introduction_to_types_21.png](introduction_to_types_21.png)


---
layout: two-cols-header
---

## Catscript

::left::
* No
* Note that catscript arrays are read only
* You cannot add or modify values in them
* With this constraint, the list types are actually covariant with one another
  * You cannot introduce a type error by this assignment in Catscript

::right::
![introduction_to_types_21.png](introduction_to_types_21.png)


---
layout: two-cols-header
---

## Correctness

::left::
* I am on team static typing
* But not due to “correctness”
* Instead, I like static typing because of the tooling it enables: 

https://grugbrain.dev/#grug-on-type-systems

::right::
![introduction_to_types_22.png](introduction_to_types_22.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Java Refresher
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## The Java Programming Language

::left::
* We will be using the Java programming language to write our compiler
* Why?
  * Java is a mature programming language
  * Java has a great standard library
    * A solid String class
    * Good Collections
    * Garbage collection

::right::
![java_refresher_1.png](java_refresher_1.png)


---
layout: two-cols-header
---

## The Java Programming Language

::left::
* Why?
  * You should have a fair amount of experience with Java
  * Object orientedness makes it easier to structure code
  * IntelliJ is a great tool for writing software
  * Java has good library support
  * This project is hard enough without dealing with C/C++
  
::right::
![java_refresher_1.png](java_refresher_1.png)


---
layout: two-cols-header
---

## Basic Syntax

::left::
* Java is an Object Oriented language, and the main building unit is the Class
* Classes may be grouped into packages
* Class names are capitalized and Camel Case

::right::
![java_refresher_2.png](java_refresher_2.png)


---
layout: two-cols-header
---

## Basic Syntax

::left::
* Method names are not capitalized, but are camel case
* Fields, variable and parameter names are not capitalized, but are camel case

::right::
![java_refresher_2.png](java_refresher_2.png)


---
layout: two-cols-header
---

## Access Modifiers

::left::
* Java has a few different access modifiers for features:
  * public - anyone can access
  * private - only the current class can access
  * protected - only the current class and sub-classes can access

::right::
![java_refresher_2.png](java_refresher_2.png)


---
layout: two-cols-header
---

## Non-Access Modifiers

::left::
* Java also has a few different non-access modifiers for methods:
  * final - this method cannot be overridden in a sub-class
  * abstract - this method must be implemented by any non-abstract sub-class of the current class

::right::
![java_refresher_2.png](java_refresher_2.png)


---
layout: two-cols-header
---

## Classes

::left::
* Java classes are declared via the class keyword
* Fields can be declared inside classes with a type and name
  * Typically fields are declared private
  * Fields can be declared static: one instance for the entire class
  * By default they are instance variables: each instance has its own copy

::right::
![java_refresher_3.png](java_refresher_3.png)


---
layout: two-cols-header
---

## Classes

::left::
* Methods, likewise can be declared static or, by default, be instance methods
* Note the common getter/setter pattern you often see in Java

::right::
![java_refresher_3.png](java_refresher_3.png)


---
layout: two-cols-header
---

## Packages

::left::
* A class can be placed inside a package with the package keyword
* The package must correspond to the directory that the class is located in
  * This class must be in /animals
  * Is this a good thing?

::right::
![java_refresher_4.png](java_refresher_4.png)


---
layout: two-cols-header
---

## Packages

::left::
* You can import classes from another package with the import keyword
* In this example, we import everything from the java.io package and use the System class to print some stuff out
  * IntelliJ will auto-import stuff for you

::right::
![java_refresher_5.png](java_refresher_5.png)


---
layout: two-cols-header
---

## A Note On This Class

::left::
* This class will not involve a lot of OO design
* Instead, you will be given a pretty well developed architecture, and will be expected to fill in logic
  * You can focus on the parsing logic rather than worrying about how to lay things out

::right::
![java_refresher_5.png](java_refresher_5.png)


---
layout: two-cols-header
---

## A Note On This Class

::left::
* You will, however, be writing a lot of java code, it will just be implementation code, rather than OO code
* A benefit: you will learn how software can be architected as you wander around the codebase

::right::
![java_refresher_5.png](java_refresher_5.png)


---
layout: two-cols-header
---

## Strings

::left::
* Java has a very good String class: java.lang.String
* Provides loads of useful methods, including charAt() which we will be using to tokenize source code
  * NB: Working with strings is one of the most painful aspects of C-based compilers

::right::
![java_refresher_6.png](java_refresher_6.png)


---
layout: two-cols-header
---

## Lists

::left::
* Data structures are a core aspect of a compiler, so we will be using the Java collections classes extensively
  * Lists hold collections of values and can be added and removed from
  * We will usually be using LinkedLists - why?

::right::
![java_refresher_7.png](java_refresher_7.png)


---
layout: two-cols-header
---

## Maps

::left::
* Maps are another important tool inside of a compiler
* Used for, well, mapping names to, for example, function definitions
* We will typically be using java.util.HashMap

::right::
![java_refresher_8.png](java_refresher_8.png)


---
layout: two-cols-header
---

## Stacks/Deques

::left::
* Stacks are another important part of compiler infrastructure
* Used for things like scopes: a symbol is valid for a given scope then ceases to exist when a scope exits
* Java has a Stack class, but it was sort of a mistake…
  * synchronization

::right::
![java_refresher_9.png](java_refresher_9.png)


---
layout: two-cols-header
---

## Stacks/Deques

::left::
* A Deque is a “double ended queue”
  * Can act like a stack or a queue
* LinkedList can be used as a deque!
  * Here you can see a symbol table, a stack of maps!

::right::
![java_refresher_9.png](java_refresher_9.png)


---
layout: two-cols-header
---

## Reverse Polish Notation

::left::
* To give you a flavor of the type of java code you will be writing, let's take a look at an implementation of Reverse Polish Notation...

::right::
![java_refresher_9.png](java_refresher_9.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
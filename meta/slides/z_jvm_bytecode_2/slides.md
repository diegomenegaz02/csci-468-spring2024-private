---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Byte Code 2 - Expressions
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Last Time

::left::
* In our last lecture we began looking at bytecode in Java
* We ended by looking at a how a simple addition looks in the JVM

::right::
![jvm_bytecode_2_1.png](jvm_bytecode_2_1.png)


---
layout: two-cols-header
---

## Bytecode

::left::
* Here is what the simple example we looked at from Java looked like
* Two instructions to load integers onto the operand stack

::right::
![jvm_bytecode_2_2.png](jvm_bytecode_2_2.png)


---
layout: two-cols-header
---

## Bytecode

::left::
* An IADD to add them
  * Consumes the two integers on the operand stack
  * Does the addition
  * Pushes the result onto the operand stack
* An IRETURN to return an integer
  * Consumes the value on the operand stack
  * Places it in the return slot for the current activation frame

::right::
![jvm_bytecode_2_2.png](jvm_bytecode_2_2.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* And here we have the simplified code for generating code with the Catscript compiler infrastructure
* When we compile an additive expression, we first compile the two sides of the expression
* Then we add an Addition or Subtraction expression

::right::
![jvm_bytecode_2_3.png](jvm_bytecode_2_3.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* Pretty simple, and most of the binary expressions will work the same way
* There is one trick here, however:
  * The + operator is overloaded to include string concatenation
  * !!!
* What does string concatenation look like?

::right::
![jvm_bytecode_2_3.png](jvm_bytecode_2_3.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* We want the code at top right (which is valid Java and Catscript) to compile into the java code at bottom
* String.valueOf() handles nulls for us
* .concat() does the actual concatenation

::right::
![jvm_bytecode_2_4.png](jvm_bytecode_2_4.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* The bytecode for this looks a little different
* We use LDC to load a constant onto the op stack
* We then issue an INVOKESTATIC instruction to invoke a static method
* The funky stuff to the right of the instruction is the method signature

::right::
![jvm_bytecode_2_5.png](jvm_bytecode_2_5.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* A method signature is just a unique identifier for a given method in the JVM, but it uses a somewhat strange syntax
* NOTE: It's JUST A FORMAT don't freak out
* You'll get used to it

::right::
![jvm_bytecode_2_6.png](jvm_bytecode_2_6.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* OK, so, let's pick this apart
* A method signature starts with the class or interface that the method is on
* In this case, java.lang.String
* However, it uses the internal name of the class, which uses slashes rather than dots
  * It's just a format

::right::
![jvm_bytecode_2_6.png](jvm_bytecode_2_6.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* Next there is a ‘.' followed by the method name
* This is then followed by type information
  * The parameter types
  * The return type of the function

::right::
![jvm_bytecode_2_6.png](jvm_bytecode_2_6.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* The next section is the types of the parameters, which uses a slightly different format
* Parameters are surrounded by parenthesis and followed by a return type
* The parameter types are specified in a pretty strange syntax
  * But keep in mind: it's just another format

::right::
![jvm_bytecode_2_6.png](jvm_bytecode_2_6.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* The L signifies (this is a non-primitive  and non-array)
  * Followed by the “internal” name of the class
  * Then followed by a semicolon
* String.valueOf() takes an object
* String.concat() takes another string

::right::
![jvm_bytecode_2_6.png](jvm_bytecode_2_6.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* The primitives are single characters:
  * B – byte
  * C – char
  * D – double
  * F – float
  * I – int
  * J – long
  * S – short
  * V – void
  * Z – boolean

::right::
![jvm_bytecode_2_7.png](jvm_bytecode_2_7.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* Here we are invoking two methods on a String:
  * indexOf()
  * contains()
* Note that indexOf() takes an integer (really a char)
* Contains takes a CharSequence and returns a boolean (Z)

::right::
![jvm_bytecode_2_7.png](jvm_bytecode_2_7.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* With the primitive types, there is no semicolon after them
* So the method at right would end up with the signature at bottom
* The (II) indicates two integers
  * lol… lmao

::right::
![jvm_bytecode_2_8.png](jvm_bytecode_2_8.png)


---
layout: two-cols-header
---

## Method Signatures

::left::
* Arrays use yet another special syntax, with a preceding `[` character
* Here we have an array of objects
* An array of integers would be \[I
* Thankfully we don't use arrays in Catscript, so you don't need to worry about it

::right::
![jvm_bytecode_2_9.png](jvm_bytecode_2_9.png)
![jvm_bytecode_2_10.png](jvm_bytecode_2_10.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* OK, so now we (kinda) know how to specify a method name to invoke
* Recall
  * the catscript code
  * The equivalent java code
  * The bytecode for the equivalent java code

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* What's the difference between INVOKESTATIC and INVOKEVIRTUAL?

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* INVOKESTATIC will look up a static method and invoke it with no `this` pointer
* INVOKEVIRTUAL will expect an object on the op stack to “invoke” the method on

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* Both instructions expect the arguments to the method to be on the operand stack
* String.valueOf(“hello ”)
  * Load the constant “hello ”
  * Invoke static String.valueOf
  * Result is left on the op stack
* String.valueof(“strings”)
  * Same deal, now operand stack has “hello ” and “strings” on it

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* Next we issue an INVOKEVIRTUAL call to String.concat, an instance method
* This method takes one parameters, but also requires a “this”
* Remember, the stack is:   “hello “ “strings”

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* “hello “ becomes the this pointer in the method invocation
* “Strings” becomes the argument to the function
* The return value is the concatenated values, left in place of the two consumed values on the stack

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* So we have consumed two values and left a single value, the concatenated string, in its place
* Not unlike binary operators in this case

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* In general, you have the following rules
* For INVOKESTATIC, the arguments to the function must be in order on the op stack

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* In general, you have the following rules
* For INVOKEVIRTUAL the object on which the function is being invoked (the “this” pointer) must be on the stack first, followed by the parameters in order

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* In both cases, all the values will be consumed and the return value of the function call, if any, will be left on the operand stack
  * Remember, java has “void” functions!

::right::
![jvm_bytecode_2_11.png](jvm_bytecode_2_11.png)
![jvm_bytecode_2_12.png](jvm_bytecode_2_12.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* What does this look like in our compile code?
* We use the code.addMethodInstruction()
* Pass in the appropriate arguments
* A little ugly, but not too bad if you just relax and accept the formats...

::right::
![jvm_bytecode_2_13.png](jvm_bytecode_2_13.png)


---
layout: two-cols-header
---

## Generating Bytecode

::left::
* And now you know how to generate function calls in bytecode!
* This is actually really, really powerful, and a lot of the interesting things in the Catscript language rely on it:
  * The for loop
  * String concatenation
  * List literals

::right::
![jvm_bytecode_2_13.png](jvm_bytecode_2_13.png)


---
layout: two-cols-header
---

## Summary

::left::
* Today we did a deep dive on how to implement string concatenation in java
* To do so, we need to invoke methods
* And to do that, we had to learn about method signatures
* Ugly, but remember: **ITS JUST CODE...**

::right::
![jvm_bytecode_2_13.png](jvm_bytecode_2_13.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Byte Code 3 - Statements
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Last Time

::left::
* Last time we looked at basic expressions and how they interact with the operand stack
* Now it's time to take a look at statements and consider how to implement them at the JVM bytecode level

::right::
![jvm_bytecode_3_1.png](jvm_bytecode_3_1.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* Recall that in addition to the operand stack and return value, a given frame has a set of “local variables” sometimes referred to as “slots”
* These slots are used to store working data, not unlike registers in assembly
  * Much easier to work with though!

::right::
![jvm_bytecode_3_2.png](jvm_bytecode_3_2.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* The instructions to work with slots are a class of instructions that have LOAD and STORE in their name
* These instructions load the value at the given slot onto the operand stack or save the current value at the top of the operand stack to the given slot

::right::
![jvm_bytecode_3_2.png](jvm_bytecode_3_2.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* The instructions are are specialized per type:
  * ALOAD/ASTORE - load/store an object/reference
  * ILOAD/ISTORE - load/store an integer
  * FLOAD/FSTORE - load/store a float
  * Etc.
* You will only be concerned w/ the reference and integer versions for Catscript

::right::
![jvm_bytecode_3_2.png](jvm_bytecode_3_2.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* Here is some basic java code that saves the value “Hello World” into a local variable x, then prints it
* Below is the corresponding byte code
* For section L0, we load a reference to the string constant “Hello World” onto the stack

::right::
![jvm_bytecode_3_3.png](jvm_bytecode_3_3.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* We then call ASTORE to save the reference into SLOT 1
  * Question: Why not slot 0?
* At L1, we call a GETSTATIC to load a reference to standard out onto the op stack
  * The Catscript compiler won't need to issue GETSTATICs

::right::
![jvm_bytecode_3_3.png](jvm_bytecode_3_3.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* We then call ASTORE to load the string reference on top of standard out on the op stack
* We now have references to standard out and the string we want to print on the op stack, so we can invoke the method
  * More on method invocation in a bit...

::right::
![jvm_bytecode_3_3.png](jvm_bytecode_3_3.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* So, how do we work with slots in our bytecode generator?
* Not too bad:
  * The code generator can create a local storage slot for a given name using the createLocalStorageSlotFor() method

::right::
![jvm_bytecode_3_4.png](jvm_bytecode_3_4.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* You'll get back a slot number for that local variable
  * Recall, “slots” are just spots in an array, stored in the local stack frame
* You can then store a value into a slot using the IStore or AStore instructions
  * I - Integer
  * A - Reference

::right::
![jvm_bytecode_3_4.png](jvm_bytecode_3_4.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* The value stored into the slot is the top of the operand stack
* Again, a good mental model for the operand stack, slots, etc. in the stack frame make generating and understanding bytecode much easier
* We have to understand the underlying machine to generate code effectively for it

::right::
![jvm_bytecode_3_4.png](jvm_bytecode_3_4.png)


---
layout: two-cols-header
---

## Working With Slots

::left::
* We can also move values from slots onto the operand stack using the I and A Load instructions
* Useful for identifier expression compilation, for example!

::right::
![jvm_bytecode_3_4.png](jvm_bytecode_3_4.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* In addition to storing data in slots on the stack, the JVM can also store data in fields on class instances
* There are corresponding instructions for working with this data: GETFIELD and PUTFIELD

::right::
![jvm_bytecode_3_5.png](jvm_bytecode_3_5.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* Interestingly, this instruction is not typed as with the LOAD/STORE instructions
* QUESTION: Catscript doesn't have classes, so why would we need fields?

::right::
![jvm_bytecode_3_5.png](jvm_bytecode_3_5.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* Consider the Catscript code at right
* There is a variable in the top level of the program
* It is accessed by both foo() and bar()
* To make this work, x needs to be a field on the CatscriptProgram, rather than a local variable

::right::
![jvm_bytecode_3_6.png](jvm_bytecode_3_6.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* Compare that Catscript program with this one
* Here the variables are local to the functions and, thus, can be stored in slots on the local activation frame
* A little subtle, but when you see it you'll see it

::right::
![jvm_bytecode_3_7.png](jvm_bytecode_3_7.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* Consider this slightly modified version of our first example
* Here we've moved the variable x out of the main() method and made the main method non-static
* When we access x now, it is a field access rather than a slot access

::right::
![jvm_bytecode_3_8.png](jvm_bytecode_3_8.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* The bytecode for this now looks a lot different
* Rather than an ALOAD to get the value x, we issue a GETFIELD with a bunch of gobbledygook after it
* What's that funny ALOAD 0 before the instruction?

::right::
![jvm_bytecode_3_8.png](jvm_bytecode_3_8.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* That ALOAD 0 puts the “this” pointer, which is always in slot 0, on to the stack
* When we call GETFIELD, the top of the operand stack is consumed as the object to get the field from
* The value of the field is then pushed back onto the operand stack

::right::
![jvm_bytecode_3_8.png](jvm_bytecode_3_8.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* So here we are pushing standard out onto the stack
* Then loading “this”
* Then invoking a GETFIELD
* The stack is now standard out and the value of x
* We invoke the method println()
  * Note that out will be the implicit this argument!

::right::
![jvm_bytecode_3_8.png](jvm_bytecode_3_8.png)


---
layout: two-cols-header
---

## Working With Fields

::left::
* How are you going to generate code for field access?
* Looks a lot like method invocation:
  * One instruction for all data types
  * the field name
  * A type descriptor for the field
  * The name of our generated class (I'll explain that later)

::right::
![jvm_bytecode_3_9.png](jvm_bytecode_3_9.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* So this is how you are going to work with local and “global” variables in Catscript programs
* The next big concept to consider is control flow
  * If Statements
  * For loops

::right::
![jvm_bytecode_3_10.png](jvm_bytecode_3_10.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* At the bytecode level there are a few different control flow instructions:
  * GOTO - unconditional jumps
  * IF\_\* - A set of instructions for comparisons between values

::right::
![jvm_bytecode_3_10.png](jvm_bytecode_3_10.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* All the higher-level control flow constructs will be implemented in terms of GOTO and Labels
* Note that the ComparisonExpression will also need to use these instructions to get the right value on the stack

::right::
![jvm_bytecode_3_10.png](jvm_bytecode_3_10.png)


---
layout: two-cols-header
---

## If Statements

::left::
* Consider the simple java if statement at right
* Below is the equivalent bytecode
* A little bit more going on in this one :)

::right::
![jvm_bytecode_3_11.png](jvm_bytecode_3_11.png)


---
layout: two-cols-header
---

## If Statements

::left::
* Let's go line by line through it
* L0 load the constant 1 and store it into slot 1
* L1 Load the slot back onto the operand stack and load the constant 1
* IF_ICMPLE - if integer compare less than or equal jump to Label L2

::right::
![jvm_bytecode_3_12.png](jvm_bytecode_3_12.png)


---
layout: two-cols-header
---

## If Statements

::left::
* Otherwise fall through and print out “Greater”
* Then jump unconditionally to Label L4
* L2, if Jumped to, prints out “Less than”
* Finally, we return

::right::
![jvm_bytecode_3_12.png](jvm_bytecode_3_12.png)


---
layout: two-cols-header
---

## If Statements

::left::
* OK, not so bad
* If you have taken 366, this is pretty simple assembly logic
* So, how do you produce this code in the Catscript compiler?

::right::
![jvm_bytecode_3_13.png](jvm_bytecode_3_13.png)


---
layout: two-cols-header
---

## If Statements

::left::
* You can create Label objects and use them to add jump instructions to the generated code
* When you add the label to the code, the instructions will be wired up to that label in the final generated code
* Makes things much easier!

::right::
![jvm_bytecode_3_13.png](jvm_bytecode_3_13.png)


---
layout: two-cols-header
---

## Summary

::left::
* OK, that's enough for today
* Next time we are going to take a look at some of the tricker code generation in more detail
  * Var statements
  * Method invocation
  * List literals
  * The dreaded FOR LOOP
* REMEMBER: IT'S JUST CODE...

::right::
![jvm_bytecode_3_14.png](jvm_bytecode_3_14.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
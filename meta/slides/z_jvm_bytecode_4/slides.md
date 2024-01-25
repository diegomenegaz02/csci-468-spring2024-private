---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Byte Code 4 - More Stuff
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Last Time

::left::
* Today we are going to talk a bit more about the implementation of some Catscript features:
  * Var statements
  * Method invocation
  * Method declaration
  * List literals
  * The dreaded FOR LOOP

::right::
![jvm_bytecode_4_1.png](jvm_bytecode_4_1.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Recall from last lecture we discussed two different ways to load and store values
  * Loading and storing from slots
  * Loading and storing from fields
* Slots use the LOAD/STORE instructions, specialized by type

::right::
![jvm_bytecode_4_2.png](jvm_bytecode_4_2.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Field access instructions are not specialized by type, just GETFIELD and  PUTFIELD
* However, both instructions require that a reference for the object that the field is going to be accessed from be on the operand stack

::right::
![jvm_bytecode_4_2.png](jvm_bytecode_4_2.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Recall that you are going to need to treat “top level” (global) variables differently than local variables
* How do you know?
  * VariableStatement already has a isGlobal() method on it

::right::
![jvm_bytecode_4_2.png](jvm_bytecode_4_2.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Recall that you are going to need to treat “top level” (global) variables differently than local variables
* How do you know?
  * VariableStatement already has a isGlobal() method on it

::right::
![jvm_bytecode_4_3.png](jvm_bytecode_4_3.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* All this function does is check to see if the parent element is a program or not
* If it is, it's a global variable, if not, it's local
  * Not rocket science!

::right::
![jvm_bytecode_4_3.png](jvm_bytecode_4_3.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Now, variable statements don't just set a value (although they do do that)
* They also declare a value
* So, when you are processing a VariableStatement you are going to need to tell the code generator where to allocate space for the variable

::right::
![jvm_bytecode_4_3.png](jvm_bytecode_4_3.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* There are two ways to do this, corresponding to slots (local variables) and fields (global variables)
* Slots: createLocalStorageSlotFor()
  * Returns a new slot spot, saves that slot for resolution later in places like IdentifierExpression

::right::
![jvm_bytecode_4_4.png](jvm_bytecode_4_4.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Fields:
  * addField()
  * Takes the variable name as well as a descriptor for the field

::right::
![jvm_bytecode_4_5.png](jvm_bytecode_4_5.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Fields:
  * addField()
  * Takes the variable name as well as a descriptor for the field
  * Hint: you can get the descriptor for a non-primitive CatscriptType with code that looks like this →

::right::
![jvm_bytecode_4_6.png](jvm_bytecode_4_6.png)


---
layout: two-cols-header
---

## Var Statements

::left::
* Variable Statements must both declare as well as set the values to these two places
  * Assignment Statements must only set the values
  * It will look similar
  * IdentifierExpression's will look up these values
  * Not that bad!

::right::
![jvm_bytecode_4_6.png](jvm_bytecode_4_6.png)


---
layout: two-cols-header
---

## Method Invocation

::left::
* We've looked a bit at method invocation, but let's look more in depth now
* Consider the java code at right
* Here is the corresponding bytecode for the invocation

::right::
![jvm_bytecode_4_7.png](jvm_bytecode_4_7.png)


---
layout: two-cols-header
---

## Method Invocation

::left::
* Note the pattern
  * Push the “this” pointer (you will always do that first)
  * Push argument 1
  * Push argument 2
* Now the operand stack has “this”, 10, 20 on it
* Then INVOKEVIRTUAL with the descriptor for the method
  * We INVOKEVIRTUAL because this is not a static method

::right::
![jvm_bytecode_4_7.png](jvm_bytecode_4_7.png)


---
layout: two-cols-header
---

## Method Invocation

::left::
* When this completes we store the value into the local slot at position 1
  * Remember, this is at position 0!
* The nice thing here is that if you just recursively code generate the arguments after pushing the this symbol, it will all just work out

::right::
![jvm_bytecode_4_7.png](jvm_bytecode_4_7.png)


---
layout: two-cols-header
---

## Method Definition

::left::
* Method definition is actually pretty easy
* You need to define the function along with it's arg types and return type
  * Here (II) means two integers arguments
  * I means “returns an integer”
* Parameters will be placed into the corresponding slots

::right::
![jvm_bytecode_4_8.png](jvm_bytecode_4_8.png)


---
layout: two-cols-header
---

## Method Definition

::left::
* this is in slot 0
* The first integer parameter is in slot 1
* The second is in slot 2
* Then the body is just the normal stuff
* IRETURN - return an integer value

::right::
![jvm_bytecode_4_8.png](jvm_bytecode_4_8.png)


---
layout: two-cols-header
---

## Method Definition

::left::
* As far as your code goes, this is all it will take
* We push a method onto a method stack
* Handle parameters, the body, etc.
* Pop the method off the stack to indicate that we are done defining it

::right::
![jvm_bytecode_4_9.png](jvm_bytecode_4_9.png)


---
layout: two-cols-header
---

## List Literals

::left::
* List literals are one of the harder things to compile
* The code at right in Catscript is equivalent to the java code below
  * Catscript is a little nicer than java in this regard :)

::right::
![jvm_bytecode_4_10.png](jvm_bytecode_4_10.png)


---
layout: two-cols-header
---

## List Literals

::left::
* What does the bytecode look like?
* Pretty complex, let's go line by line
* L0
  * NEW - create a new LinkedList
    * NB: the list is now on the op stack but is uninitialized!
  * DUP - duplicate the item at the top of the op stack

::right::
![jvm_bytecode_4_11.png](jvm_bytecode_4_11.png)


---
layout: two-cols-header
---

## List Literals

::left::
* L0
  * INVOKESPECIAL - invoke the constructor (the special \<init\> method) for the LinkedList
    * Note that this consumes the item on the top of the stack
  * Thankfully we duplicated the reference to the list, so we can now store it into local slot 1
    * Make sense?

::right::
![jvm_bytecode_4_11.png](jvm_bytecode_4_11.png)


---
layout: two-cols-header
---

## List Literals

::left::
* L1
  * ALOAD the list that we just created onto the operand stack
  * ALOAD the constant “foo”
  * INVOKEVIRTUAL the add() method on the list, adding the string to it
  * POP the result off the operand stack
    * Why?

::right::
![jvm_bytecode_4_11.png](jvm_bytecode_4_11.png)


---
layout: two-cols-header
---

## List Literals

::left::
* The add() method actually returns a value, so that value is sitting on the top of the operand stack
* But we ignored the return value
* We need to pop that value off so we don't end up with bad data on the op stack!

::right::
![jvm_bytecode_4_11.png](jvm_bytecode_4_11.png)


---
layout: two-cols-header
---

## List Literals

::left::
* L2
  * Same thing
  * So you are going to have a loop that goes over each expression in the list literal and outputs these instructions
    * You will not have that ASTORE 1 however: since the list literal is an expression it will leave a value on the op stack

::right::
![jvm_bytecode_4_11.png](jvm_bytecode_4_11.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* The ForStatement is definitely the most complex thing you will need to generate code for
* Let's look at the equivalent java and the bytecode for it to get an idea what we need to do...

::right::
![jvm_bytecode_4_12.png](jvm_bytecode_4_12.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* Here is the generated bytecode for that loop
  * Ignore the FRAME stuff, that is all take care of by the code generator
* L3 - load the list onto the operand stack and invoke the iterator() method
  * This will place an iterator on the op stack

::right::
![jvm_bytecode_4_13.png](jvm_bytecode_4_13.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* Save that iterator to a slot
  * Note, this slot does not have a name!  It doesn't correspond to any named variables!
* L4 - load the interface
  * Invoke hasNext()
  * If that is equal to 0 (false) jump to Label 5
  * If not, reload the iterator and invoke next()
  * Store the result of that into a local slot corresponding to the loop variable str

::right::
![jvm_bytecode_4_13.png](jvm_bytecode_4_13.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* L6 - print the value out
  * This is just the body of the loop
* L7 - jump back to the top of the loop to make another pass
  * Just like in assembly!
* Finally L5 is the end of the loop, where the loop tests jumps to on false

::right::
![jvm_bytecode_4_13.png](jvm_bytecode_4_13.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* Whew!  That's a lot
  * But it's not that bad
  * Here is my code to give you an idea of what the structure is
* Here are the high level tasks:
  * Get the iterator
  * Check if it has any left
  * If so load and save the next value into the slot for the loop variable
  * Execute the body
  * Jump back to the iteration start

::right::
![jvm_bytecode_4_14.png](jvm_bytecode_4_14.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* Again, this is the hardest of the bunch to implement, and it isn't that much code
* It will seem like you will never get it right up until the point that you get it
  * You can do it!

::right::
![jvm_bytecode_4_14.png](jvm_bytecode_4_14.png)


---
layout: two-cols-header
---

## Summary

::left::
* IT'S JUST CODE

::right::
![jvm_bytecode_4_14.png](jvm_bytecode_4_14.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
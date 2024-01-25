---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Parsing Statements 2
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Last Time

::left::
* Last time we looked at how to parse the var statement and the print statement
* This time we will look at the remaining statements excluding for function definitions and calls
* And then how to evaluate them...

::right::
![parsing_statements_2_1.png](parsing_statements_2_1.png)


---
layout: two-cols-header
---

## Statements In Catscript

::left::
* That leaves us
  * The assignment statement
  * The for statement
  * The if statement
* Not too bad!  We'll also look at how to evaluate all this stuff

::right::
![parsing_statements_2_2.png](parsing_statements_2_2.png)


---
layout: two-cols-header
---

## The Assignment Statement

::left::
* Assignments in CatScript are very simple
* An identifier followed by a single equals followed by an expression value
* This is much simpler than the assignment statement in LOX, which allow complex left hand side expressions

::right::
![parsing_statements_2_3.png](parsing_statements_2_3.png)


---
layout: two-cols-header
---

## The Assignment Statement

::left::
* Note that the assignment statement starts with an identifier
* One other statement starts with an identifier as well
  * Which one?
* All the other statement start with keywords
  * They have unambiguous start symbols

::right::
![parsing_statements_2_3.png](parsing_statements_2_3.png)


---
layout: two-cols-header
---

## The Assignment Statement

::left::
* The trick is to accept the identifier and then look at the next token to determine what sort of statement it is
  * ‘=' - assignment
  * ‘(‘ - function call

::right::
![parsing_statements_2_4.png](parsing_statements_2_4.png)


---
layout: two-cols-header
---

## The Assignment Statement

::left::
* You can also resolve this ambiguity at the grammar level by left factoring
* We introduce a new rule that factors out the start of the two rules and then introduce follow productions to capture the two different cases

::right::
![parsing_statements_2_5.png](parsing_statements_2_5.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* Thus far we have been looking at statements that are “straight line”
* They execute once and do one thing
  * Assign a variable to a location
  * Print a value out

::right::
![parsing_statements_2_6.png](parsing_statements_2_6.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* To do interesting things, we need to introduce control flow to catscript
* Control flow falls into two different categories:
  * Conditional or branching control
  * Looping

::right::
![parsing_statements_2_6.png](parsing_statements_2_6.png)


---
layout: two-cols-header
---

## Turing Machines

::left::
* A brief digression on turing machines
* A turing machine is a very simple computing device that has the following properties
  * An infinite amount of memory
  * Some set of instructions/symbols
  * The ability to read an instruction in a cell, write a new symbol and shift one cell to the left or right

::right::
![parsing_statements_2_6.png](parsing_statements_2_6.png)


---
layout: two-cols-header
---

## Turing Machines

::left::
* This is an extremely simplified version of more familiar computational devices
* However computationally it is equivalent (computing devices can thus be called turing complete machines)
* Programming languages can also be turing complete or not

::right::
![parsing_statements_2_6.png](parsing_statements_2_6.png)


---
layout: two-cols-header
---

## Turing Machines

::left::
* For a language to be Turing Complete it is sufficient to show that a Turing Machine simulator can be built in it
* I don't believe that Catscript is turing complete
  * We don't have a way to write to arbitrary positions in a data structure
  * Could be wrong!  Would love to hear!

::right::
![parsing_statements_2_6.png](parsing_statements_2_6.png)


---
layout: two-cols-header
---

## Turing Machines

::left::
* Turing machines fall into a more general class of computability when compared with, for example, finite state machines
* When did we see finite state machines?
* Pushdown automaton is related to Context Free Grammars (CFGs)

::right::
![parsing_statements_2_7.png](parsing_statements_2_7.png)


---
layout: two-cols-header
---

## Turing Machines

::left::
* Turing machines are thus more powerful than required for both lexing and parsing
* Inefficient computational machines, but good for proving things
* In particular, Turing used it to demonstrated that no program exists that can determine if a given program halts

::right::
![parsing_statements_2_7.png](parsing_statements_2_7.png)


---
layout: two-cols-header
---

## Turing Machines

::left::
* This is known as “The Halting Problem” in CS
* A reduction/simplification of the more general Entscheidungsproblem (Deciding Problem)
* Related to Godel's Incompleteness Theorems

::right::
![parsing_statements_2_8.png](parsing_statements_2_8.png)


---
layout: two-cols-header
---

## The If Statement

::left::
* So anyway… let's look at the grammar for the if statement
* A bit more complex than what we've seen before:
  * The ‘if' keyword, followed by parens and an expression to test
  * An open brace
  * a series of statements
  * A close brace

::right::
![parsing_statements_2_9.png](parsing_statements_2_9.png)


---
layout: two-cols-header
---

## The If Statement

::left::
* Then an optional else clause which can be either
  * Yet another if statement
  * OR
  * A curly brace and a collection of statements

::right::
![parsing_statements_2_9.png](parsing_statements_2_9.png)


---
layout: two-cols-header
---

## The If Statement

::left::
* Why do we enforce curly braces in the catscript if statement?
* Because of the dangling else problem found in other languages

::right::
![parsing_statements_2_9.png](parsing_statements_2_9.png)


---
layout: two-cols-header
---

## The If Statement

::left::
* Consider this legal C code
* What does this mean?

::right::
![parsing_statements_2_10.png](parsing_statements_2_10.png)


---
layout: two-cols-header
---

## The If Statement

::left::
* It can be parsed with the else binding to either the first if statement or to the second one
* C, by convention, picks the first option, binding the else to the closest if statement

::right::
![parsing_statements_2_11.png](parsing_statements_2_11.png)


---
layout: two-cols-header
---

## The If Statement

::left::
* This is extremely confusing and small lexical changes can dramatically affect the program
* Catscript requires braces to eliminate this ambiguity
* Python eliminates it with spacing

::right::
![parsing_statements_2_11.png](parsing_statements_2_11.png)


---
layout: two-cols-header
---

## The If Statement

::left::
* We can use the standard recursive descent parsing technique to implement parsing this
  * The only real trick here is the else statement, where we look if there is an “if” token before we parse else block

::right::
![parsing_statements_2_12.png](parsing_statements_2_12.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* The for statement is pretty similar to the if statement at the parsing level
  * Actually a bit easier!
* Look for the for keyword
  * Open paren
  * An identifier
  * The in keyword
  * An expression
  * Then an open brace, some statements and a close brace

::right::
![parsing_statements_2_13.png](parsing_statements_2_13.png)


---
layout: two-cols-header
---

## The For Statement

::left::
* Nothing too interesting here from a parsing perspective but a few notes:
  * You need to make sure that the identifier is unique
  * It is basically the same thing as declaring a variable in this sense
  * Determining the type of the variable is an interesting problem
    * How are you going to do that?

::right::
![parsing_statements_2_13.png](parsing_statements_2_13.png)


---
layout: two-cols-header
---

## Executing Statements

::left::
* Evaluating expressions is pretty simple
* Statements are a bit harder, but not by much
* Let's consider the print statement first, since it is so easy

::right::
![parsing_statements_2_14.png](parsing_statements_2_14.png)


---
layout: two-cols-header
---

## Executing Statements

::left::
* All we have to do is get ahold of the Program and invoke the print statement on it
  * You will see why we have a print() function on the Program when we get to bytecode
* Easy!

::right::
![parsing_statements_2_14.png](parsing_statements_2_14.png)


---
layout: two-cols-header
---

## Executing Statements

::left::
* What about var statements?
* Var statements, assignment statements and for loops all interact with the CatscriptRuntime object
* This object is responsible for managing the memory state of the program
  * It holds the “slots” that values will be stored at

::right::
![parsing_statements_2_15.png](parsing_statements_2_15.png)


---
layout: two-cols-header
---

## Executing Statements

::left::
* Like with the compile time SymbolTable, CatscriptRuntime has a notion of scope, and pushing and poping scopes

::right::
![parsing_statements_2_15.png](parsing_statements_2_15.png)


---
layout: two-cols-header
---

## Executing Statements

::left::
* Unlike the compile time SymbolTable, the Catscript runtime does not need to push a scope every time you enter or exit a block
  * Why?
* It does need to push a new scope when a function is invoked
  * We will be discussing functions next time

::right::
![parsing_statements_2_15.png](parsing_statements_2_15.png)


---
layout: two-cols-header
---

## Executing Statements

::left::
* What about the if statement and for loops?
* Well, the execute method is going to look an awful lot like the java code you would write in their place
  * The IfStatement execute() method will have an… if statement in it
  * The ForStatement… you get it

::right::
![parsing_statements_2_15.png](parsing_statements_2_15.png)


---
layout: two-cols-header
---

## Executing Statements

::left::
* Nothing too crazy here, kids
* IT'S JUST CODE
* Next time we'll talk functions
  * there's some crazy stuff

::right::
![parsing_statements_2_15.png](parsing_statements_2_15.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
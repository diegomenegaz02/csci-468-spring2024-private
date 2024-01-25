---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Parsing Statements
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Expressions vs Statements

::left::
* Thus far we have been working with Expressions which evaluate to a value
* The next thing we are going to work on are Statements
* Rather than evaluating to a value, statements execute

::right::
![parsing_statements_1.png](parsing_statements_1.png)


---
layout: two-cols-header
---

## Expressions vs Statements

::left::
* Statements can interact with an environment
  * Send content to an output
  * Write data into a memory location
* These are called side effects
* Pure functional languages attempt to remove all side effects
  * Everything is an expression

::right::
![parsing_statements_1.png](parsing_statements_1.png)


---
layout: two-cols-header
---

## Statements In Catscript

::left::
* At right is the grammar for statements in Catscript
  * The for() statement for looping
  * The if() statement for conditional branching
  * The print() statement for printing data
  * The var statement for creating a variable
  * The assignment statement for assigning a value
  * The function call statement for invoking a function

::right::
![parsing_statements_2.png](parsing_statements_2.png)


---
layout: two-cols-header
---

## The Print Statement

::left::
* The print statement is used to print output to standard out
* Maybe seems a little strange to include it as a syntactic element rather than as a library function
* Doing so allows you to print things out before you get the function call statement working

::right::
![parsing_statements_2.png](parsing_statements_2.png)


---
layout: two-cols-header
---

## The Print Statement

::left::
* The grammar for the print statement is very straight forward:
  * The print keyword
  * Left paren
  * And expression
  * Right paren

::right::
![parsing_statements_3.png](parsing_statements_3.png)


---
layout: two-cols-header
---

## The Print Statement

::left::
* The code for parsing a print statement is implied by the grammar:
  * If we encounter a print keyword
    * Consume it
    * Require a left paren
    * Parse an equation
    * Require a right paren
  * Else return null

::right::
![parsing_statements_4.png](parsing_statements_4.png)


---
layout: two-cols-header
---

## The Print Statement

::left::
* Note that the Parse Tree object for the PrintStatement is a bit different than what we have seen before:
  * It extends Statement, rather than Expression
  * It implements the execute() method, rather than the evaluate() method
  * The execute() method doesn't return a value

::right::
![parsing_statements_5.png](parsing_statements_5.png)


---
layout: two-cols-header
---

## The Print Statement

::left::
* The execution of this statement in our interpreter is pretty straight forward: we just delegate to a print method located on the Program
  * You will see why we do this more clearly when we get to bytecode generation

::right::
![parsing_statements_5.png](parsing_statements_5.png)


---
layout: two-cols-header
---

## The Var Statement

::left::
* The next statement we are going to look at is the variable statement
* We don't have an implementation to look at but you can probably guess what the shape of the grammar

::right::
![parsing_statements_6.png](parsing_statements_6.png)


---
layout: two-cols-header
---

## We have the ‘var' keyword as a nice start for our grammar

::left::
* Then an identifier (name) for the variable
* Next is an optional type declaration
* NB: unlike Lox, Catscript is statically typed
  * We will discuss the typesystem later
  * The Var Statement

::right::
![parsing_statements_6.png](parsing_statements_6.png)


---
layout: two-cols-header
---

## The Var Statement

::left::
* Then a required equals token
* Finally a required initialization value
  * If you want it to be null, you need to explicitly say so: 

var str : string = null

::right::
![parsing_statements_6.png](parsing_statements_6.png)


---
layout: two-cols-header
---

## The Var Statement

::left::
* The parsing here isn't too difficult: it follows the standard pattern
* However, there is something new in the runtime: we need a way to store variables

::right::
![parsing_statements_6.png](parsing_statements_6.png)


---
layout: two-cols-header
---

## Environments

::left::
* The book calls this an Environment
* In our project we refer to it as a Runtime
  * More specifically as the CatscriptRuntime

::right::
![parsing_statements_7.png](parsing_statements_7.png)


---
layout: two-cols-header
---

## Environments

::left::
* A big part of the runtime or environments job is to provide a place to store variables
* To store values we will make use of a HashTable
  * Allows us to map a name (String) to a value (Object)

::right::
![parsing_statements_7.png](parsing_statements_7.png)


---
layout: two-cols-header
---

## Scope

::left::
* As we start thinking about keeping symbols around, we need to start thinking about scope
* A scope defines a region of code over which a given name means the same thing

::right::
![parsing_statements_8.png](parsing_statements_8.png)


---
layout: two-cols-header
---

## Scope

::left::
* In the LOX code at right, the name “a” is bound to the value “first” in the first block of code and to “second” in the second block of code

::right::
![parsing_statements_8.png](parsing_statements_8.png)


---
layout: two-cols-header
---

## Scope

::left::
* Historically there are two major types of scoping
  * Lexical scoping - what we are used to today, where the scope of a variable is determined by the block where it is declared
  * Dynamic scoping - the name is resolved at runtime, via the last value assigned to it
    * Globals in javascript kind of work this way, for example

::right::
![parsing_statements_8.png](parsing_statements_8.png)


---
layout: two-cols-header
---

## Scope

::left::
* CatScript, like most modern languages, has lexical scoping
* Unlike LOX, Catscript does not allow shadowing or redefining variables

::right::
![parsing_statements_9.png](parsing_statements_9.png)


---
layout: two-cols-header
---

## Scope

::left::
* This makes scope management much easier in CatScript than in LOX
* And it doesn't sacrifice too much expressive power
* Picking another name isn't too bad…

::right::
![parsing_statements_9.png](parsing_statements_9.png)


---
layout: two-cols-header
---

## Scope

::left::
* Note that while the last page was illegal, this is legal
* Why?

::right::
![parsing_statements_10.png](parsing_statements_10.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* In order to support this parsing mechanism, we need to introduce a new concept: a SymbolTable
* A symbol table is, well, a table of symbols that are currently in scope

::right::
![parsing_statements_11.png](parsing_statements_11.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* Symbol tables are useful in particular when doing semantic analysis
* After the parse step, we want to make sure that the code that the user entered makes sense given the rules of the language

::right::
![parsing_statements_11.png](parsing_statements_11.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* In the Catscript parser, this phase of the compilation pipeline can be found in the ParseElement#validate() methods

::right::
![parsing_statements_11.png](parsing_statements_11.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* You are going to be responsible for implementing a proper symbol table for use in the ParseElement#validate() method
* What does a symbol table consist of?

::right::
![parsing_statements_11.png](parsing_statements_11.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* A symbol table is a stack of symbol maps
* The symbol map is a map of strings to objects
  * These objects can be either functions or variables

::right::
![parsing_statements_11.png](parsing_statements_11.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* During the validation phase of the parser, for VariableStatements, you are going to need to:
  * Verify that there are no current symbols with a given name
  * Register the current symbol in the symbol table
* Note that we recursively verify the child expressions as well

::right::
![parsing_statements_12.png](parsing_statements_12.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* To handle scopes we need to be able to push and pop a symbol map in the symbol table
* Hence SymbolTable has a pushScope() and popScope() pair
* This allows the code at right to compile

::right::
![parsing_statements_13.png](parsing_statements_13.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* We push a scope inside the for loop
* We add the variable x to that new scope
* We then pop the scope, so x is gone from our symbol table
* Then we define the new x in the global scope

::right::
![parsing_statements_13.png](parsing_statements_13.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* Compare with this code
* We register x in the global scope
* We push a new scope
* Oops!  x already exists in the global scope!
  * The new scope that we pushed didn't mask the original scope

::right::
![parsing_statements_14.png](parsing_statements_14.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* Another example of a place where you are going to use a symbol table is when you are verifying identifiers
* You are going to look the type of the symbol up in the current symbol table and use that for verification
  * We will talk about types more in a couple of future lectures

::right::
![parsing_statements_15.png](parsing_statements_15.png)


---
layout: two-cols-header
---

## OK!

::left::
* OK, that's all for today
* Next time we will look at some more statement types

::right::
![parsing_statements_15.png](parsing_statements_15.png)

---
layout: end
---

![0_msu_title.png](0_msu_title.png)
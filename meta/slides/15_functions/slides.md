---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Functions
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Function Parsing

::left::
* We are all familiar with functions as one of the primary features we use in day to day programming
* Functions were originally called subroutines
* Took a long and tortured path to get to what we are used to today

::right::
![functions_1.png](functions_1.png)


---
layout: two-cols-header
---

## Function Parsing

::left::
* CPUs added hardware support to make it easy to add subroutines to higher level programming languages
  * Or hand code them in ASM
* Alongside the concept of sub-routines grew the concept of a stack

::right::
![functions_1.png](functions_1.png)


---
layout: two-cols-header
---

## Function Parsing

::left::
* A stack is necessary to allow recursive function calls
* Fortran 77, for example, assigned a single memory location to parameters:
  * “A subprogram must not reference itself, either directly or indirectly.”

::right::
![functions_1.png](functions_1.png)


---
layout: two-cols-header
---

## Function Parsing

::left::
* Catscript has simpler function support than Lox does
  * We do not support “first order” functions
* None the less functions are one of the trickier aspects of Catscript to get right
  * Ironically, the JVM bytecode isn't so bad for this part, but the eval is a little crazy

::right::
![functions_1.png](functions_1.png)


---
layout: two-cols-header
---

## Function Invocation

::left::
* Note that function calls are a little funny
* A function call can be both an expression and/or a statement

::right::
![functions_2.png](functions_2.png)


---
layout: two-cols-header
---

## Function Invocation

::left::
* In two places in our grammar there is some ambiguity
* Both involve an IDENTIFIER start
* Expression
  * Is this an identifier or function call
* Statement
  * Is this an assignment or a function call

::right::
![functions_2.png](functions_2.png)


---
layout: two-cols-header
---

## Function Invocation

::left::
* In both cases, the solution is the same
* Consume the IDENTIFIER, then look at the next token
* Look for a ‘(‘, if found, parse as a function call, otherwise parse as the other option
* 
::right::
![functions_2.png](functions_2.png)


---
layout: two-cols-header
---

## Function Invocation

::left::
* A Catscript Language design decision:
  * Should functions live in the same namespace as variables?
* In Catscript we say yes
  * Java says no
* There are arguments for and against this choice

::right::
![functions_2.png](functions_2.png)


---
layout: two-cols-header
---

## Function Invocation - Eval

::left::
* We will discuss how to invoke a function on the JVM later when we go over bytecode
* For now, we need to consider how to evaluate a function call in our interpreter

::right::
![functions_2.png](functions_2.png)


---
layout: two-cols-header
---

## Function Invocation - Eval

::left::
* To invoke a function, the FunctionCallExpression needs to look up the FunctionDefinitionStatement and invoke it
* The Program object at the root of our parse tree has a method getFunction() that will return the Statement

::right::
![functions_2.png](functions_2.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* FunctionDefinitionStatement is one of the harder features in the Catscript programming language to parse
* While it is hard, if you follow the grammar it isn't any different than other features, just more to keep track of!

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* A function declaration
  * The function keyword
  * followed by an identifier
  * followed by a parameter list
  * Followed by a colon and an optional return type
  * Then a series of statements, including the new return statement
    * NB: a return statement is not allowed outside a function

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* Does anyone see a problem with this grammar definition?

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* As it stands, the return statement can only be at the top level, but we want to allow it inside if statements, for loops, etc.
* The right thing is to make the return statement part of the statement grammar and turn this into a semantic check, rather than a grammar check

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* Let's drill down on the parameter list
  * A parameter is an identifier
  * Followed optionally by a colon and a type expression
  * A parameter list is just 0 or more parameters

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* A type expression is one of the following:
  * ‘Int'
  * ‘String'
  * ‘Bool'
  * ‘Object'
  * Or the keyword “list”
    * followed by an open caret
    * then a recursive type
    * Then a closing caret

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* We will talk more about the type system in a bit, but this is a relatively simple one
* Only the list is kinda funny and requires a recursive step
* type_expression rule should return a TypeLiteral parse element
  * Technical an expression, but not really!

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* So, yes, a bit more complicated than other features, but just follow the grammar
* All you need to do is build up the correct data, fill in FunctionDefinitionStatement and then add it to the program

::right::
![functions_3.png](functions_3.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* Note that addStatement() on Program checks to see if the statement added is a function and squirrels it away in the functions map if so
* This map is how we can look up the function for invocation in our eval logic

::right::
![functions_4.png](functions_4.png)


---
layout: two-cols-header
---

## Function Invocation - Eval

::left::
* This implementation has been provided for you on FunctionDefinitionStatement
* We push a new scope
  * Why?
  * What if we didn't?
* We set all the parameter values
  * We execute the body of the function

::right::
![functions_5.png](functions_5.png)


---
layout: two-cols-header
---

## Function Invocation - Eval

::left::
* We catch a ReturnException
  * What's that?
  * Why are we using an exception?
* Finally we pop off the scope and return the value that was computed
* Nothing magic!
  * It's just code

::right::
![functions_5.png](functions_5.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* One thing we are going to have to ensure in Catscript is that our functions are semantically correct in both definition and invocation
* We will get to validating parameter and return types later, but right now let's just consider a function that returns any value

::right::
![functions_6.png](functions_6.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* Consider a simple function like this
* What if you have a logical branch that doesn't include a return statement?

::right::
![functions_6.png](functions_6.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* This is a compilation error
* There is a possible branch that is missing a return statement
* We do not have full “return coverage” in this function
* NB: in JavaScript this is fine, the function returns undefined

::right::
![functions_7.png](functions_7.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* Catscript is a more serious language than JavaScript
* We enforce return statement coverage
* To do so, you will need to implement the validateReturnCoverage() function in FunctionDefinitionStatement

::right::
![functions_7.png](functions_7.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* One trick/hint: if you have a series of statements, only last statement needs to be covered
  * If an earlier statement has a return, that's fine, but your last statement has to be covered properly

::right::
![functions_7.png](functions_7.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* This ends up being a recursive function call
* I will go over this again in a help session next week :)

::right::
![functions_7.png](functions_7.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* That's on the definition side
* On the invocation side, we aren't ready to do type checking yet but we can do arity checking
* The arity of a function is how many argument it takes
  * At a call site we can look up the function and look at its parameter types

::right::
![functions_7.png](functions_7.png)


---
layout: two-cols-header
---

## Validating Functions

::left::
* Once again, we need to take a look at a validate() function, but this time in FunctionCallExpression

::right::
![functions_7.png](functions_7.png)


---
layout: two-cols-header
---

## Phases & Forward References

::left::
* Note that one thing that makes Catscript better than some other languages that you have used...
  * E.g. C
* Is that you can refer to methods before they are defined!

::right::
![functions_8.png](functions_8.png)


---
layout: two-cols-header
---

## Phases & Forward References

::left::
* How does this work?
* Nothing magic:
  * During the parse phase we assume things will work out
  * We have a verification phase after the parse phase is over and we have a complete symbol table
  * That's it...

::right::
![functions_9.png](functions_9.png)


---
layout: two-cols-header
---

## Phases & Forward References

::left::
* The phase is pretty simple
  * We define a new symbol table and populate it with all the defined functions
  * We then recursively validate() the element
  * This is where we will do all our semantic analysis in our parse tree

::right::
![functions_9.png](functions_9.png)


---
layout: two-cols-header
---

## Phases & Forward References

::left::
* By adding phases to our compiler we are able to get around limitations like name resolution, etc.

::right::
![functions_9.png](functions_9.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
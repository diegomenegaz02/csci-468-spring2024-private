---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Evaluating Expressions
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Last Lecture

::left::
* OK!  We are now parsing tokenizing and parsing expressions!
* Now it's time to start doing something with these parsed expressions: turning them into actual computation

::right::
![evaluating_expressions_1.png](evaluating_expressions_1.png)


---
layout: two-cols-header
---

## Last Lecture

::left::
* We are going to do this three different ways:
  * Evaluation (easy)
  * Transpiling to Javascript (pretty easy)
  * Compiling to JVM bytecode (kinda hard)
* We'll look at evaluation today
* Since we are only handling expressions right now, this will be pretty easy...

::right::
![evaluating_expressions_1.png](evaluating_expressions_1.png)


---
layout: two-cols-header
---

## The Visitor Pattern...

::left::
* The book uses the visitor pattern to implement the evaluator
* I don't see a big advantage to doing this
  * More complicated
  * With a modern IDE, navigation of elements is fast enough...

::right::
![evaluating_expressions_1.png](evaluating_expressions_1.png)


---
layout: two-cols-header
---

## The Visitor Pattern...

::left::
* Instead, we are going to have an evaluate() method that is implemented by all Expression objects
* The base class throws an Unsupported Op exception
  * Not a great pattern in general, but makes the tests nice

::right::
![evaluating_expressions_2.png](evaluating_expressions_2.png)


---
layout: two-cols-header
---

## Evaluating Literals

::left::
* Literal expressions are literal values encoded directly in the programming language
* Catscript has a few more literals than Lox
  * Strings
  * Integers
  * Booleans
  * Null
  * List literals

::right::
![evaluating_expressions_2.png](evaluating_expressions_2.png)


---
layout: two-cols-header
---

## Evaluating Literals

::left::
* Let's look at IntegerLiteralExpression#evaluate() 
* Pretty darned simple!  All you have to do is return the integer value that you parsed!

::right::
![evaluating_expressions_3.png](evaluating_expressions_3.png)


---
layout: two-cols-header
---

## Evaluating Literals

::left::
* This makes sense when you think about it: we've already done the hard work turning the string into an integer value
* Now we just need to return it for the runtime to work with…
* Website demo...

::right::
![evaluating_expressions_3.png](evaluating_expressions_3.png)


---
layout: two-cols-header
---

## Evaluating Literals

::left::
* What about the boolean literal?
* Null literal?
* OK what about that list literal?
  * Left as an exercise for you to figure out

::right::
![evaluating_expressions_3.png](evaluating_expressions_3.png)


---
layout: two-cols-header
---

## Evaluating Parentheses

::left::
* OK, so that's literals
* What about parens?
* Pretty easy: just return the value that the enclosed expression evaluates to
  * This isn't magic kids!

::right::
![evaluating_expressions_4.png](evaluating_expressions_4.png)


---
layout: two-cols-header
---

## Evaluating Parentheses

::left::
* Side Note: Some parsers don't define tree nodes for parentheses. Instead, when parsing a parenthesized expression, they simply return the node for the inner expression.

::right::
![evaluating_expressions_4.png](evaluating_expressions_4.png)


---
layout: two-cols-header
---

## Unary Expressions

::left::
* Unary expressions are expressions with a single “argument” expression
  * if(not false) { … }
  * var x = -3
* Currently the unary expression doesn't have an implementation
  * You are gonna have to figure this one out!

::right::
![evaluating_expressions_5.png](evaluating_expressions_5.png)


---
layout: two-cols-header
---

## Unary Expressions

::left::
* However, the book makes it pretty clear how to do this
* Evaluate the right hand side
* Apply the operator to that value
  * NB: I don't like using the switch statement as much as the author does.  I am an “if” statement guy.

::right::
![evaluating_expressions_6.png](evaluating_expressions_6.png)


---
layout: two-cols-header
---

## Binary Expressions

::left::
* Binary expressions are expressions that have two “arguments”
  * Typically a right hand side (rhs) and a left hand side (lhs)
* Here is the AdditiveExpression evaluation

::right::
![evaluating_expressions_7.png](evaluating_expressions_7.png)


---
layout: two-cols-header
---

## Binary Expressions

::left::
* Once again, pretty simple, right?
  * Evaluate the left hand side
  * Evaluate the right hand side
  * Apply the operator to the values
* Note that in CatScript and Lox we also allow string concatenation with the ‘+' operator

::right::
![evaluating_expressions_7.png](evaluating_expressions_7.png)


---
layout: two-cols-header
---

## Binary Expressions

::left::
* The book handles the string concatenation dynamically 
* The runtime type of the values is inspected to determine the semantics of the `+` operator

::right::
![evaluating_expressions_7.png](evaluating_expressions_7.png)


---
layout: two-cols-header
---

## Binary Expressions

::left::
* You are allowed to do the same thing for CatScript 
* However, it is important to understand a crucial difference between CatScript and Lox:
  * Catscript is statically typed

::right::
![evaluating_expressions_7.png](evaluating_expressions_7.png)


---
layout: two-cols-header
---

## Static Type Information

::left::
* When you are done with the CatScript parser, you will know what the type of the lhs and rhs are 
* You can use this type information to determine what operation to perform

::right::
![evaluating_expressions_7.png](evaluating_expressions_7.png)


---
layout: two-cols-header
---

## Static Type Information

::left::
* By using static compile time information, the semantics of addition end up being much more stable and easy to reason about

::right::
![evaluating_expressions_7.png](evaluating_expressions_7.png)


---
layout: two-cols-header
---

## Static Type Information

::left::
* The book makes a big deal about runtime type-checking
* With CatScript, we don't have to worry about runtime types as much
  * We can know if a rhs or lhs has the appropriate type for a given operation
* This knowledge can boost runtime performance significantly

::right::
![evaluating_expressions_8.png](evaluating_expressions_8.png)


---
layout: two-cols-header
---

## Programs

::left::
* There is one other parse element I'd like to discuss because it is unique: the CatScriptProgram Statement 
* What's interesting about this parse element is that it can enclose either an expression or a list of statements

::right::
![evaluating_expressions_9.png](evaluating_expressions_9.png)


---
layout: two-cols-header
---

## Programs

::left::
* If a program consists only of an expression, then that expression is evaluated and printed 
* Otherwise all the statements of the program are executed in order
  * We will talk more about Statement evaluation later...

::right::
![evaluating_expressions_9.png](evaluating_expressions_9.png)


---
layout: two-cols-header
---

## Programs

::left::
* By this mechanism, you can type either an expression or a program of statements into the webapp, parse it and evaluate it 
* Pretty cool!

::right::
![evaluating_expressions_9.png](evaluating_expressions_9.png)


---
layout: two-cols-header
---

## Programs

::left::
* Note that we have an entire programming language: scanning, parsing, and execution! 
* Congratulations, you now know how to make a programming language!

::right::
![evaluating_expressions_10.png](evaluating_expressions_10.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
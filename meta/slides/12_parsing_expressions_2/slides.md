![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Parsing Expressions 2
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Last Lecture

::left::
* In the last lecture we got a tease of how the recursive descent parsing algorithm works:
  * For each production in your grammar, create a method
  * Recursively call the methods from within one another, in the same way they are laid out in the grammar

::right::
![parsing_expressions_2_1.png](parsing_expressions_2_1.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* Recall we settled on this particular version of parsing equality expressions (from the LOX grammar)
* Note the recursive call on the right hand side, so that we can parse multiple equality expressions

::right::
![parsing_expressions_2_2.png](parsing_expressions_2_2.png)


---
layout: two-cols-header
---

## CatScript

::left::
* Today we are going to focus on the CatScript expression grammar
* Note that the grammar is specified in for CatScript is specified in EBNF
  * Not quite as nice looking as the format that Crafting Interpreters uses, but more standard

::right::
![parsing_expressions_2_3.png](parsing_expressions_2_3.png)


---
layout: two-cols-header
---

## CatScript

::left::
* Catscript has a more sophisticated expression grammar than what we were looking at in LOX
  * List literals
  * Function calls
  * Type expressions

::right::
![parsing_expressions_2_3.png](parsing_expressions_2_3.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* Let's take a look at the AdditiveExpression production in particular: 

1 + 1 

* How would we take this production and turn it into a recursive descent parser?

::right::
![parsing_expressions_2_3.png](parsing_expressions_2_3.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* Let's start with the code to implement the additive expression
* Note that we are going directly from the AdditiveExpression to a PrimaryExpression
  * You are going to have to add the levels between these

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* AdditiveExpressions are what is called a binary expression in that there are two “arguments” to them
* The left hand side (lhs) and the right hand side(rhs)

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* We begin by calling parsePrimaryExpression() which will parse… something.
* Next, we see if the tokens list matches either the PLUS or MINUS token type
* If so, we consume that token
  * That is, we take it off the front of the token list

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* QUESTION: Why do we need to keep this token around?
* Once we have matched a plus or minus, now we need to parse the right hand side
* We call parseAdditiveExpression() again

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* We then construct a new AdditiveExpression object, passing in
  * The operator (+ or -)
  * The left hand side
  * The right hand side
* Finally, we set the start and end tokens of this expression
  * Just the first token of the left hand side and the last token right hand side

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* Note that since we called parseAdditiveExpression() for the right hand side, we can handle if there are multiple +'s or -'s:

1 + 2 + 3

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## Primary Expressions

::left::
* Now let's take a look at parsePrimaryExpression()
* A bit simpler since this isn't a binary expression
* We only handle integers right now
  * Your job to handle the other elements of a primary expression!

::right::
![parsing_expressions_2_5.png](parsing_expressions_2_5.png)


---
layout: two-cols-header
---

## Primary Expressions

::left::
* If the current token is an Integer, consume it, and create a new IntegerLiteralExpression with the string value of the token
* That's it!

::right::
![parsing_expressions_2_5.png](parsing_expressions_2_5.png)


---
layout: two-cols-header
---

## Primary Expressions

::left::
* If the current token doesn't match an integer, we'll just consume the current token and return an ErrorExpression
* This is very, very rough error handling

::right::
![parsing_expressions_2_5.png](parsing_expressions_2_5.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* Back to the additive expression
* Let's debug through parsing a few expressions!
* There's a bug.  What's the bug?

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## Additive Expressions

::left::
* What's the bug here?
* The problem is associativity
* The current parser is right associative, but additive expressions are left associative:

1 - 2 - 3 = (1 - 2) - 3 = -4

vs

1 - 2 - 3 = 1 - (2 - 3) = 2

::right::
![parsing_expressions_2_4.png](parsing_expressions_2_4.png)


---
layout: two-cols-header
---

## The Trick

::left::
* So, what's the trick to fix this?
  * In parsing there is usually a trick!
* The trick is: we need to turn the if statement into a while loop and place the current expression under the new expression

::right::
![parsing_expressions_2_7.png](parsing_expressions_2_7.png)


---
layout: two-cols-header
---

## The Trick

::left::
* Since we have a loop, we don't need to make a recursive call to parseAdditiveExpression(), so we can call parsePrimaryExpression() and still parse multiple additive operations

::right::
![parsing_expressions_2_7.png](parsing_expressions_2_7.png)


---
layout: two-cols-header
---

## The Trick

::left::
* Note that the logic now more closely matches the EBNF production for additive expressions

::right::
![parsing_expressions_2_7.png](parsing_expressions_2_7.png)


---
layout: two-cols-header
---

## The Trick

::left::
* Let's debug this new algorithm
* Note that we now get the proper nesting of expressions
* This is the trick to get left associativity with binary expressions with recursive descent parsing

::right::
![parsing_expressions_2_7.png](parsing_expressions_2_7.png)


---
layout: two-cols-header
---

## The Trick

::left::
* The previous version of the method works great for right associative operators:

x ^ y ^ z

::right::
![parsing_expressions_2_7.png](parsing_expressions_2_7.png)


---
layout: two-cols-header
---

## Unary Operators

::left::
* OK, so now let's add unary operator parsing to our parser...

::right::
![parsing_expressions_2_3.png](parsing_expressions_2_3.png)

---
layout: default
---

![parsing_expressions_2_8.png](parsing_expressions_2_8.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Parsing Expressions
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Parser Magic

::left::
* Thus far we have been playing around with chopping up strings
* Now it's time to get down to brass tacks and start parsing strings
  * This is the deep magic of parsers

::right::
![parsing_expressions_1.png](parsing_expressions_1.png)


---
layout: two-cols-header
---

## Parser Magic

::left::
* Believe it or not, parsing isn't that hard
* You already know about grammars
* We are going to add a bit on top of that understanding
* And then see how we can easily turn a grammar into a parser

::right::
![parsing_expressions_1.png](parsing_expressions_1.png)


---
layout: two-cols-header
---

## Expressions

::left::
* We are going to begin looking at parsing by focusing on simple (or not so simple) Expressions
* An expression evaluates to a value
  * Compare with a statement, which performs an action

::right::
![parsing_expressions_1.png](parsing_expressions_1.png)


---
layout: two-cols-header
---

## Expressions

::left::
* Expression examples:
  * 5 + 3
  * x \> 10
  * a * 5 + 3 
* Statement examples:
  * print(“Hello World”)
  * if(x){ foo() }

::right::
![parsing_expressions_1.png](parsing_expressions_1.png)


---
layout: two-cols-header
---

## The Lox Expressions

::left::
* Consider this simple expression grammar from the website
* This grammar supports:
  * Numbers
    * 1, 3, 42
  * Strings
    * “Hello”

::right::
![parsing_expressions_2.png](parsing_expressions_2.png)


---
layout: two-cols-header
---

## The Lox Expressions

::left::
* This grammar supports:
  * Booleans
    * true, false
  * Nil (null)
  * Unary expressions
    * -1, !true
  * Binary expressions
    * 1 + 2
    * 8 \> 7
  * Parentheses
    * (1 + 2) * 3

::right::
![parsing_expressions_2.png](parsing_expressions_2.png)


---
layout: two-cols-header
---

## The Lox Expressions

::left::
* This is a well developed expression language
* Maybe 60% of the expressions you would expect in a real world programming language
* And that's the whole grammar for it, right there!

::right::
![parsing_expressions_2.png](parsing_expressions_2.png)


---
layout: two-cols-header
---

## The Lox Expressions

::left::
* Unfortunately, there are some problems with this grammar
* It turns out that this grammar has some ambiguity in it

::right::
![parsing_expressions_2.png](parsing_expressions_2.png)


---
layout: two-cols-header
---

## Ambiguity

::left::
* Last lecture, when considering CFGs, we looked at how to generate a string
* Parsing is the inverse of this operation: given a string, how should it map to a grammar
* A CFG has ambiguity in it if there is more than one way to match a string to a grammar

::right::
![parsing_expressions_1.png](parsing_expressions_1.png)


---
layout: two-cols-header
---

## Ambiguity

::left::
* Consider this valid string in the lox expression grammar:

6 / 3 - 1 

* This string could be matched up with the grammar in two different ways

::right::
![parsing_expressions_3.png](parsing_expressions_3.png)


---
layout: two-cols-header
---

## Ambiguity

::left::
* 1st way:
  * Starting at expression, pick binary
  * For the left-hand expression, pick NUMBER, and use 6
  * For the operator, pick "/"
  * For the right-hand expression, pick binary again
  * In that nested binary expression, pick 3 - 1

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Ambiguity

::left::
* 2nd way:
  * Starting at expression, pick binary
  * For the left-hand expression, pick binary again
  * In that nested binary expression, pick 6 / 3
  * Back at the outer binary, for the operator, pick "-"
  * For the right-hand expression, pick NUMBER, and use 1

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Ambiguity

::left::
* This is the difference between

(6 / (3 - 1 ) )  and ( 6 / 3) - 1 

* If you look at this, you can see that these math expressions produce different answers: 3 and 1

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Precedence

::left::
* We know intuitively that the answer here is 1, because we know that division binds more tightly than subtraction
* This is an intuitive understanding of operator precedence

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Precedence

::left::
* Precedence determines which operator is evaluated first in an expression containing a mixture of different operators
* Operators with higher precedence are evaluated before operators with lower precedence
  * Equivalently, higher precedence operators are said to “bind tighter”

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Associativity

::left::
* Precedence makes sense when you have differing operators, but what about when you have the same operator
* You can still have ambiguity here

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Associativity

::left::
* Consider this expression:

4 - 3 - 2 

* Should this parse as     

(4 - 3) - 2 → -1 

Or

4 - (3 - 2) → 3

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Associativity

::left::
* Again, intuitively we know that

(4 - 3) - 2 → -1

Is the correct answer

* The minus operator is left associative because the left most operator binds more tightly

::right::
![parsing_expressions_4.png](parsing_expressions_4.png)


---
layout: two-cols-header
---

## Dealing With Ambiguity

::left::
* We can deal with these two sources of ambiguity by adjusting our grammar
* To deal with precedence, we need to split the operators up into operator classes

::right::
![parsing_expressions_5.png](parsing_expressions_5.png)


---
layout: two-cols-header
---

## Dealing With Ambiguity

::left::
* For each precedence level we introduce a separate rule
  * Expression - a general expression
  * Equality - lowest precedent level (binds the least tightly)
  * Comparison - next level
  * Term - additive/subtraction
  * Factor - division/multiply
  * Unary - left associative
  * Primary  - highest precedence level

::right::
![parsing_expressions_6.png](parsing_expressions_6.png)


---
layout: two-cols-header
---

## Dealing With Ambiguity

::left::
* Now consider parsing              6 / 3 - 1
  * Expression → Equality → Comparison → Term → Factor - finds the division
  * Backs up to Term - finds the -
* Proper matching!

::right::
![parsing_expressions_7.png](parsing_expressions_7.png)


---
layout: two-cols-header
---

## Dealing With Ambiguity

::left::
* What about associativity?
* Note that the binary expressions rules use the following pattern: 

\<x\> → \<next\> ( … \<next\> )* 

* This means the left most expression is matched first
  * Left associative

::right::
![parsing_expressions_7.png](parsing_expressions_7.png)


---
layout: two-cols-header
---

## Dealing With Ambiguity

::left::
* The unary expression, on the other hand is defined as: 

unary → (‘!' | ‘-') unary | primary

* So that's right associative, but why do we need that primary option in the grammar?

::right::
![parsing_expressions_7.png](parsing_expressions_7.png)


---
layout: two-cols-header
---

## Dealing With Ambiguity

::left::
* If the unary expression was only defined as this: 

unary → (‘!' | ‘-') unary  It would never terminate

* By including primary, you allow the grammar to terminate
  * Or deal with expressions that have no unary operator

::right::
![parsing_expressions_7.png](parsing_expressions_7.png)


---
layout: two-cols-header
---

## Dealing With Ambiguity

::left::
* In fact, if you look at all the rules, all of them can just pass through to the next level down!
  * Except primary expressions
  * Hence the term “primary” :)
  * But note that even within primary expressions, the parenthesis allows you to reenter at the top of the grammar!

::right::
![parsing_expressions_7.png](parsing_expressions_7.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* In this class we are going to learn a parsing algorithm called Recursive Descent
* This is not how I was taught compilers
  * And, in fact, very few departments teach compilers this way...

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* In most universities, the parser classes focus on bottom up parsing and parser generators
  * LL(k), LR(1), LALR parsers
  * parser combinators
  * Earley parsers
  * Shunting yard algorithm
  * Packrat parsing

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* Complicated
* Universities are run by big brains
  * Many, many papers can be generated by complicated algorithms
* Recursive descent is just too simple and obvious!
* However...

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* It turns out that many, many, many real world parsers are written in recursive descent
* When I first saw it, I was gobsmacked…
  * “Wait, that's it?”
  * “Yep…”
  * “That's what a grammar is?”
  * “Yep…”

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* It also turns out that, by writing a recursive descent parser, you develop a much better intuition about grammars and parsing in general
  * Amazing, but true

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* I went to Stanford with the explicit goal of learning how to create programming languages
* And I came out having no idea how to do it

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* Fortunately at my first job out of grad school, I worked at a company called Guidewire
* Guidewire had an amazing developer, a bit older, who didn't go to a “good” school
* This “not good” school taught him recursive descent parsing

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* He's one of the greatest engineers I've ever met
  * https://gosu-lang.github.io/
  * http://manifold.io/ 
* I learned more about parsing, grammars, etc. in six weeks with this guy than I learned in two years at Stanford
  * Cost less too

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* So, here's the core idea of recursive descent:
  * For each production in your grammar, create a method named after it
    * e.g. parseEquality()
  * In that method, call the methods defined on the right hand side of the production, matching strings as needed

::right::
![parsing_expressions_8.png](parsing_expressions_8.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* Recall our lox expression grammar
* Note, in particular the equality production
* How would we create a recursive descent parser for this production?

::right::
![parsing_expressions_9.png](parsing_expressions_9.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* We create a function, named after the production
* In the body of the function we call the methods for the grammar elements on the right hand side
* If there is an ‘==' we consume it and parse a right hand side
* If not, just return the left hand side

::right::
![parsing_expressions_9.png](parsing_expressions_9.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* That's it!
* OK, maybe that's a little confusing, let's go over it again...
* Not that much code though, right?
* And, believe it or not, that's pretty much the pattern for ALL binary expressions

::right::
![parsing_expressions_9.png](parsing_expressions_9.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* OK, I lied just a bit
  * But only a little bit!
* Note that there is a * at the end of the production
  * There be multiple ‘==' in an expression
* So we need to make a change...

::right::
![parsing_expressions_9.png](parsing_expressions_9.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* What if we changed the second parseComparisonExpression call to parseEqualityExpression?
* Now we can parse any number of equality expressions!

::right::
![parsing_expressions_9.png](parsing_expressions_9.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* However, there is a small problem here
  * HINT: it has something to do with the ambiguity we discussed earlier
* See if you can figure out what it is, and I'll show you the trick to fix it next time...

::right::
![parsing_expressions_9.png](parsing_expressions_9.png)


---
layout: two-cols-header
---

## Recursive Descent Parsing

::left::
* Next lecture we are going to look at CatScript and actually implement some of the grammar together
* And let's see if you can find the small mistake that is in the code here…

::right::
![parsing_expressions_9.png](parsing_expressions_9.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
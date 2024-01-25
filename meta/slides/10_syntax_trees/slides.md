---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Syntax Trees
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## The Syntax Tree

::left::
* In the last lecture we discussed context free grammars and looked at how to produce valid sentences with them
* I also briefly mentioned “parse trees”
* A more formal name for parse trees is a Syntax Tree

::right::
![syntax_trees_1.png](syntax_trees_1.png)


---
layout: two-cols-header
---

## The Syntax Tree

::left::
* A syntax tree is the output of a parser
* The input to a parser is a list or stream of tokens, via a tokenizer
* It is the job of the parser to take this stream of tokens and, using a grammar, produce a syntax tree

::right::
![syntax_trees_1.png](syntax_trees_1.png)


---
layout: two-cols-header
---

## The Syntax Tree

::left::
* What makes a tree a tree?
  * A collection of “children”
  * A pointer to a parent
* At left the top node, ‘-' has two children: ‘+' and ‘4'
* You can imagine a java class, let's call it ParseElement, that represents this

::right::
![syntax_trees_1.png](syntax_trees_1.png)


---
layout: two-cols-header
---

## The Syntax Tree

::left::
* And if you take a look in the catscript code base, you will find just such a class!
* Note that you have a pointer to a parent ParseElement and a collection (List) of children parse elements
  * You also have a start and end token, errors, etc.

::right::
![syntax_trees_2.png](syntax_trees_2.png)


---
layout: two-cols-header
---

## The Syntax Tree

::left::
* This abstract base class is going to be used by other classes that represent the various parts of the CatScript grammar
* At right you can see an example, the AdditiveExpression

::right::
![syntax_trees_3.png](syntax_trees_3.png)


---
layout: two-cols-header
---

## The Book

::left::
* The book has an interesting approach to the Syntax Tree: the nodes are all “dumb” and are auto-generated from a grammar via a script
* This is a common approach particularly if you use a parser generator
  * More on those near the end of the class

::right::
![syntax_trees_3.png](syntax_trees_3.png)


---
layout: two-cols-header
---

## The Book

::left::
* While this is more conventional, I do not like it, at least for this class
  * It ends up introducing more complexity and more classes
  * It doesn't strike me as necessary for many places
  * Maybe big-brain over engineering?

::right::
![syntax_trees_3.png](syntax_trees_3.png)


---
layout: two-cols-header
---

## The Book - Code Generator

::left::
* On the other hand, I am maybe a bit too much of a hacker
* We are going to do it my way, but I want to mention the way the book does it

::right::
![syntax_trees_4.png](syntax_trees_4.png)


---
layout: two-cols-header
---

## The Book - Code Generator

::left::
* At right is the base class for all expressions in the book parser
  * We will discuss the difference between expressions and statements later
* Note that all expressions are binary in Lox
  * Left, right fields
  * Sometimes called “lhs” and “rhs”

::right::
![syntax_trees_4.png](syntax_trees_4.png)


---
layout: two-cols-header
---

## The Book - Code Generator

::left::
* The book then defines a simple syntax for generating classes based on the fields required for them

::right::
![syntax_trees_5.png](syntax_trees_5.png)


---
layout: two-cols-header
---

## The Book - Code Generator

::left::
* Next in the code generator we find a loop over all the types defined in that array
* Note that there are some splits() and trims()
  * Split - split a string up on a character
  * Trim - remove whitespace

::right::
![syntax_trees_6.png](syntax_trees_6.png)


---
layout: two-cols-header
---

## The Book - Code Generator

::left::
* Finally, we are at the crux of the code generator: we generate a class that extends that base class
* If you squint you can see all the parts of the class being printed out

::right::
![syntax_trees_7.png](syntax_trees_7.png)


---
layout: two-cols-header
---

## The Book - Code Generator

::left::
* This sort of code generation is very common in the java world
  * It is sometimes called metaprogramming (erroneously in my opinion)
* Less common in dynamically typed languages and languages with higher level syntax support

::right::
![syntax_trees_7.png](syntax_trees_7.png)


---
layout: two-cols-header
---

## The Book - Code Generator

::left::
* In any event, like I said, I am not a fan of this approach for this particular problem
* I understand why the author is doing it though and it is a common approach

::right::
![syntax_trees_7.png](syntax_trees_7.png)


---
layout: two-cols-header
---

## The Expression Problem

::left::
* Now that we have looked at what our syntax tree is going to be made up of, we have to consider what our syntax tree is going to do
  * Or have done to it
* When we start to consider this, we run into something called The Expression Problem

::right::
![syntax_trees_7.png](syntax_trees_7.png)


---
layout: two-cols-header
---

## The Expression Problem

::left::
* The expression problem is as follows:
  * If you have a collection of classes AND a collection of operations on those classes, you have to pick:
    * It's easy to add a new class
    * It's easy to add a new operation

::right::
![syntax_trees_8.png](syntax_trees_8.png)


---
layout: two-cols-header
---

## The Expression Problem

::left::
* Here we see a grouping of different classes for different types of expressions and operations on those classes
* Java/OO programming: easy to add a new class, hard to add a new operation
  * You need to mutate every existing class

::right::
![syntax_trees_8.png](syntax_trees_8.png)


---
layout: two-cols-header
---

## The Expression Problem

::left::
* In functional programming we would have the opposite problem:
  * Easy to add a new operation, hard to add a new class
* Neither style, OO or functional, makes changing both aspects easy
  * This is “The Expression Problem”

::right::
![syntax_trees_8.png](syntax_trees_8.png)


---
layout: two-cols-header
---

## The Expression Problem

::left::
* How much of a problem is this, really?
* I actually don't think it's much of a problem at all
* It just isn't that hard to add a new method to a group of classes in modern IDEs
  * Compilation errors and tests are a big help in this regard

::right::
![syntax_trees_8.png](syntax_trees_8.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* However, a lot of people think this is a big problem and, when addressing it in a language like Java, they use The Visitor Pattern
* As is becoming a noticeable trend, I am not a huge fan of the visitor pattern, but you should know about it
  * Maybe I'm wrong
    * (i am not wrong)

::right::
![syntax_trees_8.png](syntax_trees_8.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
“The Visitor pattern is the most widely misunderstood pattern in all of Design Patterns, which is really saying something when you look at the software architecture excesses of the past couple of decades.”

::right::
![syntax_trees_8.png](syntax_trees_8.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* The visitor pattern starts with an “accept” (or similarly named) function that accepts a visitor on each class that can be visited
* This method is defined on the base class and then implemented on the concrete classes

::right::
![syntax_trees_7.png](syntax_trees_7.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* An interface is then created that accepts every concrete version of the base class

::right::
![syntax_trees_10.png](syntax_trees_10.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* The “trick” here is that the correct method will be invoked in the sub-classes since it is static dispatch
* NB: I am using method overloading, in the book example he gives the methods different names

::right::
![syntax_trees_11.png](syntax_trees_11.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* Now we can define a visitor that cleanly handles these two different classes

::right::
![syntax_trees_12.png](syntax_trees_12.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* And create a loop over a generic Vehicles collection that works without any sort of type casting

::right::
![syntax_trees_13.png](syntax_trees_13.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* OK, so what?
  * If we need to add a new class then we update all the visitors
  * If we need to add a new operation, we can add a new visitor
* So what we saved ourselves from is:
  * If we add a new operation we have to update all the classes

::right::
![syntax_trees_13.png](syntax_trees_13.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* OK, so what?
  * If we need to add a new class then we update all the visitors
  * If we need to add a new operation, we can add a new visitor
* So what we saved ourselves from is:
  * If we add a new operation we have to update all the classes

::right::
![syntax_trees_14.png](syntax_trees_14.png)


---
layout: two-cols-header
---

## The Visitor Pattern

::left::
* Well, OK, I guess that's kinda good
* On the other hand, adding a new method to a hierarchy isn't that hard
* And man, that's a lot of code...

::right::
![syntax_trees_13.png](syntax_trees_13.png)


---
layout: two-cols-header
---

## Our Code Base

::left::
* If you take a look at AdditiveExpression.java in our code base, you'll see three methods:
  * Evaluate
  * Transpile
  * Compile
* In the Visitor-centric world, these would all be implemented as visitors

::right::
![syntax_trees_15.png](syntax_trees_15.png)


---
layout: two-cols-header
---

## Our Code Base

::left::
* Instead I just jammed them on the ParseElement directly
* This is a violation of Separation of Concerns:In computer science, separation of concerns (SoC) is a design principle for separating a computer program into distinct sections such that each section addresses a separate concern. 

::right::
![syntax_trees_15.png](syntax_trees_15.png)


---
layout: two-cols-header
---

## Our Code Base

::left::
* Instead I just jammed them on the ParseElement directly
* This is a violation of Separation of Concerns:In computer science, separation of concerns (SoC) is a design principle for separating a computer program into distinct sections such that each section addresses a separate concern. 

::right::
![syntax_trees_14.png](syntax_trees_14.png)


---
layout: two-cols-header
---

## Our Code Base

::left::
* I don't care
* There are other concerns in software besides SoC
  * Simplicity
  * Ease of debugging
  * Simplicity
  * Locality of Behavior
  * Simplicity
  * Ease of reading
  * Simplicity

::right::
![syntax_trees_14.png](syntax_trees_14.png)


---
layout: default
---

## Relative Importance

![syntax_trees_16.png](syntax_trees_16.png)


---
layout: two-cols-header
---

## Our Code Base

::left::
* I'm trying to teach you about how parsers work
* This complexity doesn't help in that endeavor
* Generally, I think that a lot of big brain developers over-complicate things
  * Especially true in the Java & patterns world


::right::
![syntax_trees_17.png](syntax_trees_17.png)


---
layout: default
---

## Syntax Trees

* The Syntax Tree is the data structure representation of a string after it has been parsed
* There are many ways to structure a Syntax Tree, but you usually have a parent pointer and a children collection
* The Expression Problem occurs when you have N classes and M operations, and end up with N x M code artifacts
  * OO programming and Functional programming have different strengths here
* The Visitor Pattern is one way of addressing this problem in OO languages
* I don't care...


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
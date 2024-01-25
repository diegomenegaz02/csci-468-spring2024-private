---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Parsing Introduction - A Map Of The Territory
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* When I took compilers at Stanford, I had no idea what I was doing
  * This was despite the fact that I really wanted to work on programming languages
* We ended up writing a parser in C++, using parser tools
* Only later, when I went into industry, did I really learn parsers

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* This isn't because I'm stupid
  * Well…
* Rather, it is because of the way that compilers are taught
* Early on in Computer Science, compilers were taught using a technique called Recursive Descent

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* Recursive Descent is still how most commercial parsers are built
  * It is relatively simple
  * It makes it very obvious how grammars work
  * It's fun
* So, why isn't it taught this way?

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* Unfortunately the strength of Recursive Descent is a weakness in academy
  * It is too simple and elegant
  * Simplicity: no papers
  * No papers: no tenure
* When I learned how parsers actually work, I swore if I ever taught, I'd use Recursive Descent
  * And here we are...

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* Since Recursive Descent isn't popular in academia, there aren't many good books on the topic
* I had resigned myself to teaching it via slides…
* And then I found this website:

https://craftinginterpreters.com/

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* Written by Bob Nystrom (aka munificentbob)
  * Bob now works at google, on the Dart programming language
* Does a great job of explaining Recursive Descent parsing
* Includes an interpreter implementation
  * A great addition to a compilers class!

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* The online book is freely available and has great illustrations
* We are very luck that it exists and should be thankful to Bob for making such a great resource available to us

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* With all that said, let's take a look at this map of the territory...

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: default
---

## The Map

![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Crafting Interpreters

::left::
* Bob uses the metaphor of a mountain:
  * We begin at a low level of individual source code characters
  * We ascend up the mountain to the point that we have a high-level understanding of the program
  * We then descend down to bytecode or machine code

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Scanning

::left::
* Scanning also called lexing, tokenizing or lexical analysis is the process of breaking text into individual tokens
* This typically involves discarding meaningless white space, comments, etc.

::right::
![parsing_introduction_2.png](parsing_introduction_2.png)


---
layout: two-cols-header
---

## Parsing

::left::
* The next step is parsing
* This is where we use a grammar to turn this string of tokens into a Parse Tree
  * Also sometimes called an Abstract Syntax Tree (AST)
* The parser can tell us if there is a Syntax Error

::right::
![parsing_introduction_3.png](parsing_introduction_3.png)


---
layout: two-cols-header
---

## Static Analysis

::left::
* Now that we have a parse tree, we can start doing static analysis
  * “Does another variable have this variables name?”
  * “Is the right hand side compatible with the left and side?”

::right::
![parsing_introduction_4.png](parsing_introduction_4.png)


---
layout: two-cols-header
---

## Static Analysis

::left::
* Lexing through Static Analysis is considered the “front end”
* At right is a diagram of the gcc internals
* The “ends” of a compiler
  * Front end
  * Middle end
  * Back end

::right::
![parsing_introduction_5.png](parsing_introduction_5.png)


---
layout: two-cols-header
---

## Intermediate Representations

::left::
* In many compilers the initial AST is transformed into a new tree structure that better captures the final output
* This is known as an Intermediate Representation
* We do not use any intermediate representations in this class

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Optimization

::left::
* Many compilers have a heavy focus on optimization
  * Producing the fastest possible code that is semantically compatible with the users program
* We do not focus on optimization in this class

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Code Generation

::left::
* Finally, we come to the “back end” of the compiler: we generate code
  * Sometimes called code gen
* Typical targets for compilers
  * X86 (yikes)
  * MIPS (easy, but annoying)
  * JVM Byte Code (that's what we will do)

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Virtual Machines

::left::
* Our back end will be targeting JVM Bytecode
  * It is relatively easy to work with
  * Good tooling support
  * We are already writing everything in java, so it simplifies things
  * The JVM is very stable and has mature libraries

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Alternative Paths...

::left::
* As you can see, there are some alternative paths in this map
* One of the great features of this book!
  * Tree-walk interpreters
  * Transpilers

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Tree-Walk Interpreter

::left::
* Many scripting languages work this way, at least at first
* If performance isn't crucial, it's a great approach!
* In fact, you need not go beyond the initial AST to implement it
* Extremely glad you will get to see this!

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Transpilers

::left::
* A Transpiler is also called a source-to-source compiler or transcompiler
  * JS hipsters started calling it transpiler
* A transpiler converts one language into another
  * Javascript is a popular target
  * Hyperscript is was transpiled
    * I went back to interpreted...

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Transpilers

::left::
* This isn't as silly as it sounds
  * At some level, C is a transpiler to ASM
* I would bet that if you use compiler techniques in the real world it will likely be in creating a transpiler

::right::
![parsing_introduction_1.png](parsing_introduction_1.png)


---
layout: two-cols-header
---

## Compiling Vs. Interpreting

::left::
* There used to be a big gap between these two camps
  * Compiling - pocket protectors and ties
  * Interpreters - long hair and wide collars
* Less so today, both camps have grown closer to one another with lots of overlap

::right::
![parsing_introduction_6.png](parsing_introduction_6.png)


---
layout: two-cols-header
---

## Why Learn All This?

::left::
* The reality is that you will probably not work on a compiler project
  * At least not as your primary work
* So why bother?
* Well, I can give you some reasons...

::right::
![parsing_introduction_6.png](parsing_introduction_6.png)


---
layout: two-cols-header
---

## Why Learn All This?

::left::
* Compilers are an advanced system
  * You are unlikely to see a project as involved as this across a broad set of functionality in your school work
* Compilers are recursive
  * It's actually pretty rare to use recursion, which is a beautiful concept, in programming.  You use it in compilers though!

::right::
![parsing_introduction_6.png](parsing_introduction_6.png)


---
layout: two-cols-header
---

## Why Learn All This?

::left::
* Compilers show you have real languages work
  * Maybe, like me, you fancy yourself a languages geek.  Well, by working on a compiler, you can gain deeper understanding of the languages you use.  You are all going to know a lot more about java and the JVM when this class is over!

::right::
![parsing_introduction_6.png](parsing_introduction_6.png)


---
layout: two-cols-header
---

## Why Learn All This?

::left::
* Compilers aren't that hard
  * Compilers seem like absolute magic to most people, but once you've worked through this project, especially with recursive descent, you'll see that they aren't that crazy.  You might even create them for fun!

::right::
![parsing_introduction_6.png](parsing_introduction_6.png)


---
layout: two-cols-header
---

## A Look At The Project

::left::
* Let's do a quick walk through the project so you you know what to expect over the next few weeks…

::right::
![parsing_introduction_7.png](parsing_introduction_7.png)


---
layout: two-cols-header
---

## Here we go...

::left::
* Alright!  Next lecture we get going with lexing
* Let's have some fun!

::right::
![parsing_introduction_8.png](parsing_introduction_8.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)

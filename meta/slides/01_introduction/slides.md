---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# CSCI 468 - Compilers
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: default
---

## Course Goals

“Senior capstone course. Compiler design and construction. Scanning, parsing, symbol tables, semantic analysis, intermediate representations, run-time memory management, target code generation, and optimization. Implementation of a small compiler.”


---
layout: default
---

## Course Goals

“Small” = 1000+ LOC in Java  😂… 😭


---
layout: two-cols-header
---

## Who Am I

::left::
* Instructor Carson Gross
* Third year teaching at MSU
* A big fan of making programming languages!
* Here are a few of mine:
  * https://gosu-lang.github.io/
  * https://hyperscript.org
  * https://littlemanstackmachine.org/firth.html
* I try to make “hands on” classes

::right::
![1_profile.png](/1_profile.png)


---
layout: default
---

## Classroom Mechanics

* Lectures are at 3:10PM, MWF in Romney 8
  * Lab is 5-6PM Friday, Barnard 254
  * Office hours 4-5PM MWF in 364 or 347 Barnard
* All lectures will be live streamed via YouTube
  * I will post links to the lecture livestream to discord before class
  * I will post a link to D2L after class
    * You can rewatch whenever
* Everything is available online

---
layout: default
---

## Course Difficulty

* This is a code heavy course
* You will be working extensively with Java data structures
* You will be writing 1000+ lines of Java code
  * But I have given you 4500+ LOC already! 💪
* That being said, at the course end and assuming you didn't cheat, you will know a lot more about building real software in Java
* Always Remember: It's Just Code!


---
layout: default
---

## Discord

* Discord is a major channel of communication in the class
* Anything important will also be sent over email, but Discord provides a lot of interactivity, community and bantz
* There is a link off the class Content Overview in D2L to join the discord, and you will get instructions on how to join the class group when you log in
* Please use a real name on the server, or your will get bounced


---
layout: two-cols-header
---

## Book

::left::
http://www.craftinginterpreters.com/

::right::
![2_interpreters_book.png](/2_interpreters_book.png)


---
layout: two-cols-header
---

## Why This Book?

::left::
* Traditional University Compilers Classes (TUCCs) use something like the venerable “Dragon Book”
* Focus on using compiler generator tools such as lex and yacc
  * Yet Another Compiler Compiler

::right::
![3_traditional_compilers_book.png](/3_traditional_compilers_book.png)


---
layout: two-cols-header
---

## Why This Book?

::left::
* I do not like this approach
* Crafting Interpreters teaches a technique known as recursive descent parsing, which is done by hand, rather than using a tool
* You don't need a tool to generate your parser code: you can do it yourself

::right::
![2_interpreters_book.png](/2_interpreters_book.png)


---
layout: two-cols-header
---

## Why This Book?

::left::
* This approach has a lot of advantages
* You don't need to learn a particular tool, you learn concepts
* It's a lot easier to see the recursive nature of grammars
* Debugging is a million times easier

::right::
![2_interpreters_book.png](/2_interpreters_book.png)


---
layout: two-cols-header
---

## Why This Book?

::left::
* This is also how most production compilers are written
* Reading Assignment:

https://grugbrain.dev/#grug-on-parsing

::right::
![4_grug.png](/4_grug.png)


---
layout: default
---

## So, What is a Compiler?

* A compiler is a computer program that takes code written in one language and translates it into another
* The input language is typically, though not always, in text form
* The output language is often, although not always, in some sort of binary form
* Compilers have been around forever, and are very, very cool
  * Almost magical!  Almost.


---
layout: default
---

## Compiler Terms - Tokenizing

* Tokenizing is the process of breaking a string into a set of Tokens
* Also sometimes called Lexing or Scanning
  * I use all these terms interchangeably, but usually use “Tokenize”

“1 + 1” → { “1”, “+”, “1” }

* Tokenizing is related conceptually to the notion of Regular Expressions and Finite State Machines


---
layout: default
---

## Compiler Terms - Parsing

* Parsing is the process of taking a set of tokens and turning it into a tree, based on a grammar

![5_token_tree.png](/5_token_tree.png)


---
layout: default
---

## Compiler Terms -  Symbol Tables

* Symbol Tables are just that: tables of symbols
  * In Java, we will be using a Hash Table
* Map a string name to a specific “slot” in the program

String foo = “bar”;

* Here “foo” is a symbol: it represents some place in memory into which something is copied…
  * What?


---
layout: default
---

## Compiler Terms -  Semantic Analysis

* Semantic Analysis is a fancy term for “looking at the program to make sure it makes sense”
* Often involves a Type System that determines what and what is not allowed, given the runtime of the language

String foo = 10;

* Here “foo” should be a String, not an integer!
  * Should this be an error?


---
layout: default
---

## Compiler Terms - Intermediate Representation

* An Intermediate Representation (IR) is a data structure that represents a parsed program
* Typically an IR is a Parse Tree
  * Trees == Recursion  (awesome!)
* Something like “1 + 1” becomes:

![6_intermediate_representation.png](/6_intermediate_representation.png)


---
layout: default
---

## Compiler Terms - Intermediate Representation
* Intermediate Representations may be transformed between one another in a compiler
  * E.g. the gcc compiler pipeline
* Intermediate Representations can also be used for things like optimizations or parse tree transformations
* How could this IR be optimized?

![6_intermediate_representation.png](/6_intermediate_representation.png)


---
layout: default
---

## Compiler Terms -  Code Generation

* Code Generation is the process of going from an Intermediate Representation to another language
  * Typically a lower level language: Assembly, Binary Code, JVM Byte Code
* We will be targeting JVM bytecode in this class
  * The JVM is relatively easy to understand
  * Good tools for examining the output
  * You will learn a lot about Java!


---
layout: default
---

## Compiler Terms -  Transpiling

* Transpiling is the process of going from an Intermediate Representation to another language
* Wait, what?  That's exactly what Code Generation is!
* Yeah, but Transpiling sounds cooler
  * Typically generating a relatively high level language (e.g. Javascript)
* We may write a javascript transpiler if time allows (unlikely)


---
layout: default
---

## Project - Catscript

* We will be writing a compiler for a programming language called CatScript
* A simple enough programming language to demonstrate the basics
* But Complex enough to give you heartburn


---
layout: default
---

## Project - Catscript

* The five stages of Catscript
  * Tokenizing
  * Parsing
  * Evaluating
  * Transpiling to Javascript
  * Compiling to JVM Bytecode
* The  project will be done individually, but you will need a partner for the Capstone
* Project is almost entirely graded via an autograder, which runs a set of tests associated with the project


---
layout: default
---

## Project - Capstone

* In addition to the project, you will need to produce a capstone document
* See the /capstone directory in your project for all the details
* Part of the capstone will documentation on the CatScript language, generated by your partner, as well as three tests contributed by your partner
  * This is the extent of your work with a partner: I want everyone to really learn how to write a compiler


---
layout: default
---

## Grading

* Project - 65%
  * Designed to give you practical experience with compiler implementation
  * Phases of the project will be due throughout the semester
* Quizzes - 35%
  * Designed to test your theoretical knowledge of the concepts covered in class
  * Administered every two weeks (Fridays)
  * ~30 minute quizzes on D2L
* Homework - 0%
  * The project is your homework


---
layout: default
---

## Cheating

* Please don't cheat. I'm cool to you all and set you all up to succeed, please return the favor and learn the material.
* What's OK:
  * Discuss programming assignments (not answers) with other people
  * Helping other people debug (not write) their program
* What's Not:
  * Share code with other people
  * Submit code that you did not write (small snippets from StackOverflow OK)
  * Modify someone else's solution and claim it as your own
  * Chatting with other students during Quizzes

---
layout: center
---

![7_be_nice.png](/7_be_nice.png)


---
layout: default
---

## Git

* Set up your class repository, following the instructions here:

https://github.com/msu/csci-468-spring2023


---
layout: center
---

![8_two_states.png](/8_two_states.png)


---
layout: two-cols-header
---

## Reading Assignment

::left::
https://grugbrain.dev/#grug-on-parsing

::right::
![4_grug.png](/4_grug.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
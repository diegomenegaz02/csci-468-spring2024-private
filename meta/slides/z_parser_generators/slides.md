---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Parser Generators
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Parser Generators

::left::
* In this class we learned the recursive descent algorithm for creating parsers
* It is a wonderful algorithm!
  * Widely used in industry
  * Teaches the recursive nature of grammars in a very clear way
  * Practical and pedagogical!  What's not to like?

::right::
![parser_generators_1.png](parser_generators_1.png)


---
layout: two-cols-header
---

## Parser Generators

::left::
* Turns out that in the vast majority of universities, this technique is not being taught
* Rather, most departments (including Cal and Stanford) focus on Parser Generators

::right::
![parser_generators_2.png](parser_generators_2.png)


---
layout: two-cols-header
---

## Parser Generators

::left::
* Parser generators are programs that take a language specification and generate a parser for that specification
  * Typically the input is
    * A lexical grammar in some sort of Regular Expression
    * A language grammar in some sort of EBNF

::right::
![parser_generators_3.png](parser_generators_3.png)


---
layout: two-cols-header
---

## Parser Generators

::left::
* This is what I learned at Stanford
  * lex - a lexer generator
    * yacc - yet another compiler compiler
    * See here for the gory details: 

 http://dinosaur.compilertools.net/

::right::
![parser_generators_3.png](parser_generators_3.png)


---
layout: two-cols-header
---

## Parser Generators

::left::
* We are going to discuss another parser generator, ANTLR 

https://www.antlr.org/  

* Another Tool for Language Recognition
  * Created by Terence Parr of USF
  * Licenced under BSD

::right::
![parser_generators_3.png](parser_generators_3.png)


---
layout: two-cols-header
---

## Defining Your Lexer

::left::
* Antlr files end with the .g (grammar) extension
* The ‘lexer grammar' tells ANTLR this is a lexer with the name TLexer
  * The name will be used later on in generated java files
* A few options are set on the lexer
  * Including some header code to include in the generated class

::right::
![parser_generators_4.png](parser_generators_4.png)


---
layout: two-cols-header
---

## Defining Your Lexer

::left::
* Remember, this will generate a lexer for you, so there are a lot of options to tweak exactly how that class is generated
  * We will look at the generated code in a bit

::right::
![parser_generators_4.png](parser_generators_4.png)


---
layout: two-cols-header
---

## Defining Your Lexer

::left::
* Next we have token definition
* This is a mix of regular expressions, character literals and directives
  * The {skip();} directive tells the tokenizer to not include the given token in the output stream
* Note also the support for fragments, which are not part of the lexical grammar but are used in other rules

::right::
![parser_generators_5.png](parser_generators_5.png)


---
layout: two-cols-header
---

## Defining Your Lexer

::left::
* Pretty ugly in my opinion, but this is what you have to do to generate a lexer

::right::
![parser_generators_5.png](parser_generators_5.png)


---
layout: two-cols-header
---

## Generated Code

::left::
* All of this will generate a class, org.example.TLexer, that can be used to tokenize a program
* Let's go look at that code...

::right::
![parser_generators_5.png](parser_generators_5.png)


---
layout: two-cols-header
---

## Generated Code

::left::
* All of this will generate a class, org.example.TLexer, that can be used to tokenize a program
* Let's go look at that code...

::right::
![parser_generators_6.png](parser_generators_6.png)


---
layout: two-cols-header
---

## Creating A Parser

::left::
* Creating a generated parser is similar, using the “parser grammar” directive and again, giving it a name
* This includes some configuration variables like what the super class for the parser should be, etc.

::right::
![parser_generators_7.png](parser_generators_7.png)


---
layout: two-cols-header
---

## Creating A Parser

::left::
* Again, you have a header to allow you to inject additional code in the generated class
* Then you have a series of EBNF-like rules
  * Focus on the last two
  * This is the basic additive expression
  * addExpr is the equivalent to our primary expression

::right::
![parser_generators_8.png](parser_generators_8.png)


---
layout: two-cols-header
---

## Creating A Parser

::left::
* Note that the vertical bar as usual represents a choice between various alternatives
* addExpr can be an ID, INT or STRING
* Note that all of these are token types, taken from the token grammar

::right::
![parser_generators_8.png](parser_generators_8.png)


---
layout: two-cols-header
---

## Creating A Parser

::left::
* Given this code, Antlr will generate a parser for us
* Let's go take a look at it...

::right::
![parser_generators_8.png](parser_generators_8.png)


---
layout: two-cols-header
---

## Creating A Parser

::left::
* Given this code, Antlr will generate a parser for us
* Let's go take a look at it...

::right::
![parser_generators_9.png](parser_generators_9.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* OK, given all this, how do we use this generated parser?
* Actually pretty similarly to how we use the Catscript parser:
  * Create a tokenizer
  * Set the source into it
  * Get a token stream (or array)
  * Pass it to the parser

::right::
![parser_generators_10.png](parser_generators_10.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* The parser takes the generated tokens and then you invoke the root rule (in this case the a() rule) to get the results
* We then get the Abstract Syntax Tree out of the result

::right::
![parser_generators_11.png](parser_generators_11.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* We can go ahead and print out the tree in our demo
* Note that unlike in Catscript, the visitor pattern would be useful here:
  * We do not have great control over the generated classes, so we need a technique for working with a tree that we don't control
  * The visitor pattern is that technique

::right::
![parser_generators_11.png](parser_generators_11.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* This is one reason why the parser world is big on the visitor pattern
  * I was a little surprised that the Crafting Interpreters book recommended it, given it used recursive descent

::right::
![parser_generators_11.png](parser_generators_11.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* Let's write a tree-walk interpreter together…
  * DEMO

::right::
![parser_generators_11.png](parser_generators_11.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* So, that's parser generators, and that's how most people learn about parsing these days
* What's good about it?
  * It's less written code than doing it all by hand
  * Certainly less infrastructure to get right

::right::
![parser_generators_11.png](parser_generators_11.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* What's bad about it?
  * Pretty far from the metal
  * Obscure syntax for things that are obvious when you do it by hand
  * Doesn't give students a good feel for the recursive nature of grammars
  * Not used very much in industry!

::right::
![parser_generators_11.png](parser_generators_11.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* Oh, one more thing...
  * Parser generators are much beloved by academics because they are so complicated that they generate an endless stream of papers

::right::
![parser_generators_11.png](parser_generators_11.png)


---
layout: two-cols-header
---

## Using A Generated Parser

::left::
* Oh, one more thing...
  * Parser generators are much beloved by academics because they are so complicated that they generate an endless stream of papers

::right::
![parser_generators_12.png](parser_generators_12.png)


---
layout: two-cols-header
---

## Conclusion

::left::
* OK, so that's parser generators
* I hope you can appreciate how much better recursive descent is, at least from a learning perspective
* It turns out that production parser are also almost always recursive descent too

::right::
![parser_generators_12.png](parser_generators_12.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
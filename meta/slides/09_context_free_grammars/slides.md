---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Context Free Grammars
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Parsing

::left::
* Thus far we have focused on tokenizing strings
  * Splitting strings into lexemes and associating tokenization information with them
* The next step in creating a compiler is to into a more complex data structure
  * A parse tree!

::right::
![context_free_grammars_1.png](context_free_grammars_1.png)


---
layout: two-cols-header
---

## Parse Trees

::left::
* Consider the string of tokens on the right
* Lexically, these would be broken up into
  * [1, +, 2, *, 3, -, 4]
* Fine, but what do they mean logically?
* Something very different!
  * (1 + (2 * 3)) - 4

::right::
![context_free_grammars_2.png](context_free_grammars_2.png)


---
layout: two-cols-header
---

## Parse Trees

::left::
* Note that this is a tree
* In the next step in our compiler we are going to be building parse trees
* Parse trees are awesome
  * Trees == recursion
    * One of the few places in real world code you get to use recursion!

::right::
![context_free_grammars_3.png](context_free_grammars_3.png)


---
layout: two-cols-header
---

## Parse Trees

::left::
* Note that the parse tree also maps to the evaluation of this expression
  * Evaluating/interpreting is a powerful and fun way to work with a programming language
  * I'm reworking hyperscript to be interpreted rather than transpiled, for flexibility

::right::
![context_free_grammars_3.png](context_free_grammars_3.png)


---
layout: default
---

## Evaluation Steps Of a Parse Tree

![context_free_grammars_4.png](context_free_grammars_4.png)


---
layout: two-cols-header
---

## Parse Trees

::left::
* Again, previously we focused on the lexical grammar of languages
* And it produces a flat sequence of tokens
  * an array or list
* To produce a tree from this sequence of tokens, we need a Context Free Grammar

::right::
![context_free_grammars_3.png](context_free_grammars_3.png)


---
layout: two-cols-header
---

## Context Free Grammars

::left::
* A Context Free Grammar is a formal grammar that specifies the structure of a language
* A grammar consists of
  * An “alphabet”
    * Confusingly, this is not individual letters, but rather atomic pieces in the grammar
  * A set of “Strings” that are “in” the grammar
    * Again, not a “String” in the common sense

::right::
![context_free_grammars_3.png](context_free_grammars_3.png)


---
layout: two-cols-header
---

## Context Free Grammars

::left::
* In a lexical grammar
  * Alphabet = characters
  * String = a token
* In a context free grammar
  * Alphabet = tokens
  * String = a valid program or expression

::right::
![context_free_grammars_3.png](context_free_grammars_3.png)


---
layout: two-cols-header
---

## Context Free Grammars

::left::
* How do we specify a context free grammar?
* The first thing to note is that most programming languages are infinite
  * To deal with this, our language specification must be recursive

::right::
![context_free_grammars_3.png](context_free_grammars_3.png)


---
layout: two-cols-header
---

## Context Free Grammars

::left::
* To specify a grammar, we will create a set of rules, using something called Backus-Naur Form (BNF)
  * These rules are called productions because they can be used to produce strings that are valid according to the grammar
  * We will not use BNF exactly, most people tweak it

::right::
![context_free_grammars_3.png](context_free_grammars_3.png)


---
layout: two-cols-header
---

## Context Free Grammars

::left::
* At right is a grammar for breakfast items, in an BNF-like form
  * A collection of productions
  * The left hand side identifies the grammar element name, this is called the head
  * The right hand side gives the rule for that production
  * Note that a grammar element can have multiple productions

::right::
![context_free_grammars_5.png](context_free_grammars_5.png)


---
layout: two-cols-header
---

## Context Free Grammars

::left::
* On the right hand side of a production, we find two types of elements
  * Terminals - a letter from the grammars alphabet
    * Recall again, this is alphabet in the CFG sense
  * Non-terminals - references to other grammar rules

::right::
![context_free_grammars_5.png](context_free_grammars_5.png)


---
layout: two-cols-header
---

## Producing a Valid String

::left::
* To produce a String (again, in the CFG sense) from our grammar, we can pick a production from the root and run with it

::right::
![context_free_grammars_5.png](context_free_grammars_5.png)


---
layout: two-cols-header
---

## Producing a Valid String

::left::
* Start with the first production, let's take the most complex option:
  * protein “with” breakfast “on the side”
* We have two terminals
  * “with”
  * “on the side”
* And two non-terminals
  * protein
  * breakfast

::right::
![context_free_grammars_6.png](context_free_grammars_6.png)


---
layout: two-cols-header
---

## Producing a Valid String

::left::
* We can expand the first non-terminal: protein
* Now we have another non-terminal: cooked
* Looking back at our grammar, we can expand that to “poached”

::right::
![context_free_grammars_7.png](context_free_grammars_7.png)


---
layout: two-cols-header
---

## Producing a Valid String

::left::
* Our new string still has a non-terminal in it: breakfast
* Note that this is a recursive production

::right::
![context_free_grammars_8.png](context_free_grammars_8.png)


---
layout: two-cols-header
---

## Producing a Valid String

::left::
* We could choose the exact same expansion we just went through and end up with:
 
“poached” “eggs” “with” “poached” “eggs” “with” breakfast “on the side” “on the side”

::right::
![context_free_grammars_8.png](context_free_grammars_8.png)


---
layout: two-cols-header
---

## Producing a Valid String

::left::
* For now, let's follow the book and pick something a bit more sensible
  * breakfast → bread
  * bread → “English Muffin”
* We have eliminated all our non-terminals and now have a valid string!

::right::
![context_free_grammars_9.png](context_free_grammars_9.png)


---
layout: default
---

## Our final production

![context_free_grammars_10.png](context_free_grammars_10.png)


---
layout: two-cols-header
---

## Producing a Valid String

::left::
* Do you see the parse tree here?
* We went from a grammar to a string
* Parsing is going from a string, back to a grammar
  * And, believe it or not, there is a pretty easy way to do this!


::right::
![context_free_grammars_10.png](context_free_grammars_10.png)


---
layout: two-cols-header
---

## Why Context Free?

::left::
* Note that at each decision point in the grammar, we do not depend on the context of the production
* When a “protein” is called for, any possible production is OK!

::right::
![context_free_grammars_5.png](context_free_grammars_5.png)


---
layout: two-cols-header
---

## Why Context Free?

::left::
* This is in contrast with a context sensitive grammar
* E.g. “A protein production is only allowed if it has not yet been used”

::right::
![context_free_grammars_5.png](context_free_grammars_5.png)


---
layout: two-cols-header
---

## Try It Yourself

::left::
* Post a valid string for this grammar to Discord...

::right::
![context_free_grammars_10.png](context_free_grammars_10.png)


---
layout: two-cols-header
---

## Better Grammar Specification

::left::
* The previous grammar was pretty wordy
* People have improved on this form of grammar to reduce redundancy and increase expressiveness
* Typically this are called Extended Backus-Naur Form (EBNF)

::right::
![context_free_grammars_10.png](context_free_grammars_10.png)


---
layout: two-cols-header
---

## Better Grammar Specification

::left::
* There is no one standard for EBNF, but the book uses a pretty common one
  * ( stuff )? - optional
  * stuff+ - repeat once or more
  * (a | b | c ) - choose one
* Similar to regular expression syntax, should be familiar

::right::
![context_free_grammars_11.png](context_free_grammars_11.png)


---
layout: two-cols-header
---

## Catscript Grammar

::left::
* For the catscript grammar I used a more standard EBNF syntax, take from 

https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_form

::right::
![context_free_grammars_12.png](context_free_grammars_12.png)


---
layout: two-cols-header
---

## Catscript Grammar

::left::
* More standard EBNF
  * Elements are separated by a comma
  * Uses square brackets for optionality
  * Curly brackets for repetition
  * Etc.
* The details aren't too important, there are lots of different EBNF formats
  * Learn the concepts, not the syntax

::right::
![context_free_grammars_12.png](context_free_grammars_12.png)


---
layout: two-cols-header
---

## Catscript Grammar

::left::
* Let's do a read-through of the catscript grammar together
* Head on over to 

https://github.com/msu/csci-468-spring2021#catscript-grammar

::right::
![context_free_grammars_13.png](context_free_grammars_13.png)


---
layout: default
---

## Context Free Grammars

* Context Free Grammars are a way to specify the grammar of a given language
* An “Alphabet” in terms of a CFG are the terms of the language (not individual letters
* Strings in the language are valid programs matching that grammar
* We looked at a few different variants of BNF, used to define a CFG
* We looked at how to produce a valid string for a language, given a grammar
* Next up, we will reverse that, and that's parsing!


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
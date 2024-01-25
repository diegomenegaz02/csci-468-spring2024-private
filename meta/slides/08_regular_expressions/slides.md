---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Regular Expressions
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Regular Expressions

::left::
* In the last lecture we briefly mentioned Regular Expressions, often shortened to regexes
* A regex that matches standard C variable names is: `[a-zA-Z_][a-zA-Z_0-9]*`

::right::
![regular_expressions_1.png](regular_expressions_1.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Today we are going to do a deep dive on regular expressions
* This will not be part of the project (we are writing the tokenizer by hand, like montanans) but students are typically taught regex in compilers and I don't want you to miss out on it

::right::
![regular_expressions_1.png](regular_expressions_1.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Regular expressions are based on the work of Stephen Kleen
* Arose out of the work that was being done within  automata theory

::right::
![regular_expressions_2.png](regular_expressions_2.png)


---
layout: two-cols-header
---

## Automata Theory

::left::
* Automata are a simple model of computation
  * Important in theoretical CS
* Within automata there is the notion of a Finite Automaton
  * A machine with a finite number of states and mechanisms for moving between those states
  * Also sometimes called a Finite State Machine

::right::
![regular_expressions_3.png](regular_expressions_3.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Kleen demonstrated the equivalence between regular languages and Finite State Machines
  * Kleen's Theorm
* A regular language can be defined as a language that can be recognized by a FSM

::right::
![regular_expressions_2.png](regular_expressions_2.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* From a practical sense, this means a “regular language” can't have a recursive structure
* This is a language classification or category
* Theoretical CS is heavily influenced by math and, particularly, category theory

::right::
![regular_expressions_2.png](regular_expressions_2.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Consider “The language of all repeating periods of any length greater than 0”
  * It might be strange to think of this as a “language”, but it is!
  * This can be represented with a finite automata
  * Therefore, this language is regular

::right::
![regular_expressions_4.png](regular_expressions_4.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Now consider “The language of all balanced parenthesis”
  * E.g.
    * `()(()())`
  * This language cannot be represented without recursion
  * No FSM can be created that can recognize it
  * Hence this is not a regular language

::right::
![regular_expressions_4.png](regular_expressions_4.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* To recognize this language you would need some sort of memory-based structure, such as a stack
* Conceptually, this language could be described using a context free grammar, which we will talk about in the next lecture

::right::
![regular_expressions_4.png](regular_expressions_4.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Regular Expressions are a practical tool built on top of the theoretical ideas of FSMs
* Provide a syntax for matching regular languages

::right::
![regular_expressions_5.png](regular_expressions_5.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Often used in programming for things like
  * “Does this string match an email address”
  * “Is this string capitalized?”
  * “Is this a legal Java identifier?”

::right::
![regular_expressions_5.png](regular_expressions_5.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Frequently used in compilers to define the lexical grammar for a language
* Instead of writing a scanner by hand, like us, you define a set of regular expressions that a tool then transforms into a scanner for you


::right::
![regular_expressions_5.png](regular_expressions_5.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Sounds great, Carson!
* Why aren't we using them?

::right::
![regular_expressions_5.png](regular_expressions_5.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* ‘Some people, when confronted with a problem, think  “I know, I'll use regular expressions.”   Now they have two problems.'

--Jamie Zawinski

::right::
![regular_expressions_5.png](regular_expressions_5.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* A good site to play around with regex is:  https://regex101.com
* Provides an interactive way to play with regex, along with explanations and a quick reference

::right::
![regular_expressions_6.png](regular_expressions_6.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* What does an actual regex look like?
  * A pattern of characters describing a certain pattern of text
* What is a regex “engine”?
  * A regex engine is anything that implements some form of regular expressions
    * grep
    * The java.util.regex.Pattern class

::right::
![regular_expressions_6.png](regular_expressions_6.png)


---
layout: two-cols-header
---

## Literal Characters

::left::
* The first and easiest regular expression components are literal characters
  * Literal characters that should be matched against

::right::
![regular_expressions_7.png](regular_expressions_7.png)


---
layout: two-cols-header
---

## Special Characters

::left::
* Regex wouldn't be super useful if all you could look for were literal pieces of text
  * That's just string matching after all
* In regex, there are also Special Characters
* These characters have special meanings
  * Sometimes called metacharacters

::right::
![regular_expressions_7.png](regular_expressions_7.png)


---
layout: two-cols-header
---

## Special Characters

::left::
* We will discuss the following metacharacters: 

`[ ] \ ^ $ . | ? * + ()`

* Note that if you want the literal character for any of these meta characters, you must prefix it with a backslash

::right::
![regular_expressions_8.png](regular_expressions_8.png)


---
layout: two-cols-header
---

## Side Note

::left::
* Regular expression engines typically return the left-most match
* This can lead to surprising results when you are using them
  * Here, with the `/g` (global) option enabled, we are finding all matches

::right::
![regular_expressions_8.png](regular_expressions_8.png)


---
layout: two-cols-header
---

## Character Classes

::left::
* A character class uses the square bracket metacharacters
* `[abc]` is the class of characters ‘a', ‘b' and ‘c'
* Can be thought of an OR
  * ‘a' OR ‘b' OR ‘c'

::right::
![regular_expressions_9.png](regular_expressions_9.png)


---
layout: two-cols-header
---

## Character Classes

::left::
* You can use a hyphen within a character class to indicate a range of characters
* `[A-Z]` represents “All capital characters”

::right::
![regular_expressions_10.png](regular_expressions_10.png)


---
layout: two-cols-header
---

## Character Classes

::left::
* You can negate a character class by making the first characters a caret
* `[^abc]` means “all characters that are not ‘a', ‘b' or ‘c'

::right::
![regular_expressions_11.png](regular_expressions_11.png)


---
layout: two-cols-header
---

## Character Classes

::left::
* If you want to use a metacharacter inside a character class, you must escape it with a backslash
* `[,\.]` matches all commas and periods
  * even though period is a metacharacter

::right::
![regular_expressions_12.png](regular_expressions_12.png)


---
layout: two-cols-header
---

## Character Classes

::left::
* There are shorthand notations for certain character classes
* Theses often are regex engine specific, but some common ones are:
  * `\s` - whitespace
  * `\d` - 0 through 9 (decimal)
* You can typically negate these shortcuts by using the upper case letter

::right::
![regular_expressions_13.png](regular_expressions_13.png)


---
layout: two-cols-header
---

## Character Classes

::left::
* Here we are matching all non-whitespace characters by using the inversion of the whitespace character class

::right::
![regular_expressions_13.png](regular_expressions_13.png)


---
layout: two-cols-header
---

## Dot

::left::
* The dot is one of the most common metacharacters in regex
* It will match any single character except newlines
  * This is for historical reasons
  * Most modern regex engines include a way to match newlines as well

::right::
![regular_expressions_14.png](regular_expressions_14.png)


---
layout: two-cols-header
---

## Dot

::left::
* You can enable this on regex101 by clicking on the `/gm` (more on that later) and selecting s (single line)

::right::
![regular_expressions_15.png](regular_expressions_15.png)


---
layout: two-cols-header
---

## Dot

::left::
* A word of caution on dots: they are very easy to throw in to regular expressions when you are creating them, but can lead to surprising results
* It is often better to use a negated character set instead

::right::
![regular_expressions_14.png](regular_expressions_14.png)


---
layout: two-cols-header
---

## Anchors

::left::
* The `^` metacharacter anchors the match to the start of a line
  * Note that this is outside of a character class
    * wew lads
* Here we are matching the first character on a new line, if any…
  * How could there be none?

::right::
![regular_expressions_16.png](regular_expressions_16.png)


---
layout: two-cols-header
---

## Anchors

::left::
* The `$` metacharacter anchors the match to the end of a line
* Note that putting any characters after the `$` will lead to no matches

::right::
![regular_expressions_17.png](regular_expressions_17.png)


---
layout: two-cols-header
---

## Anchors

::left::
* The `\b` metacharacter anchors the match to the start or end of a word boundaries
  * A word is a maximal sequence of word characters (non whitespace, non punctuation)
* Place `\b` at the start or end of a match to enforce word boundaries

::right::
![regular_expressions_18.png](regular_expressions_18.png)


---
layout: two-cols-header
---

## Anchors

::left::
* Here we are matching all words that have a single character in them
  * Single spaces match although they are not words
  * Single letters match
  * Why doesn't the single space after “problem” match?

::right::
![regular_expressions_18.png](regular_expressions_18.png)


---
layout: two-cols-header
---

## Alternation

::left::
* Character classes allow you to match any of a number of characters
* But what about any of a set of words
* To do that, we have alternation
* The vertical bar, `|`, allows you to select from multiple strings of characters

::right::
![regular_expressions_19.png](regular_expressions_19.png)


---
layout: two-cols-header
---

## Alternation

::left::
* Note that you can use meta characters on either side of the vertical bar
* Either side can be arbitrarily complicated
  * Including alterations, if they are parenthesized properly!

::right::
![regular_expressions_20.png](regular_expressions_20.png)


---
layout: two-cols-header
---

## Optional

::left::
* You can declare a preceding match is optional by putting a `?` after it in the regex
* Here we have a regular expression that can match either “problem” or “problems”

::right::
![regular_expressions_21.png](regular_expressions_21.png)


---
layout: two-cols-header
---

## Optional

::left::
* This calls to mind a meme that is often relevant when working with regex

::right::
![regular_expressions_22.png](regular_expressions_22.png)


---
layout: two-cols-header
---

## Repetition

::left::
* You can specify a repetition of a match or set of matches by using a few different characters:
* `* -` zero or more repetitions
* `+ -` one or more repetitions
* `{min, max}` - between min and max repetitions
    
::right::
![regular_expressions_21.png](regular_expressions_21.png)


---
layout: two-cols-header
---

## Repetition

::left::
* Here we have a regex that matches one or more lowercase l's
* Note that the two l's in I'll are matched ONCE, not individually
  * Regex engines will typically have maximal munch
  * aka they are greedy

::right::
![regular_expressions_23.png](regular_expressions_23.png)


---
layout: two-cols-header
---

## Repetition

::left::
* What if you don't want greediness?
* Oh, just put a question mark after the repetition specifier!
* Note now that the two lowercase ls in “I'll” matched individually

::right::
![regular_expressions_24.png](regular_expressions_24.png)


---
layout: two-cols-header
---

## Repetition

::left::
* What if you don't want greediness?
* Oh, just put a question mark after the repetition specifier!
* Note now that the two lowercase ls in “I'll” matched individually

::right::
![regular_expressions_22.png](regular_expressions_22.png)


---
layout: two-cols-header
---

## Grouping

::left::
* You can group parts of a regular expression together by using parentheses
* Here we are grouping the problem chunk and the next following characters

::right::
![regular_expressions_25.png](regular_expressions_25.png)


---
layout: two-cols-header
---

## Grouping

::left::
* Grouping can be useful for ensuring the proper binding of sub-components of your regular expression
* Also useful for capturing a particular match in a regular expression

::right::
![regular_expressions_25.png](regular_expressions_25.png)


---
layout: two-cols-header
---

## Back Matches

::left::
* This is because you can refer to a previous match with a backslash followed by a number
* Here we have a regex that will match any sequence of characters that repeat twice in a row
  * Problem?

::right::
![regular_expressions_26.png](regular_expressions_26.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* OK!  That's a quick tour of regular expressions
* Maybe seems maybe a little crazy, but why would Jamie say “now you have two problems”
* Why would Carson not use regex in our tokenizer?

::right::
![regular_expressions_26.png](regular_expressions_26.png)


---
layout: default
---

## lol… lmao
![regular_expressions_27.png](regular_expressions_27.png)



---
layout: two-cols-header
---

## Additional Tips

::left::

* In programming languages, regular expressions are typically enclosed within forward slashes:      /problems?/
* You can typically set options for the regex after the trailing slash
  * g - match globally
  * i -  case insensitive
  * U - ungreedy repetition

::right::
![regular_expressions_26.png](regular_expressions_26.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* My advice is to use them sparingly: they are pretty big brained
  * Big brain code means big brain problems
* They can get you out of a sticky situation though
* Try to keep them as simple as possible

::right::
![regular_expressions_26.png](regular_expressions_26.png)


---
layout: two-cols-header
---

## Additional Tips

::left::
* https://ihateregex.io/
  * Provides common regular expression patterns for you
  * Here is an example of a simple (well, simpler) regex that matches most emails
* Often better to go with a simpler-but-imperfect regex than a correct-but-complex one!

::right::
![regular_expressions_28.png](regular_expressions_28.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
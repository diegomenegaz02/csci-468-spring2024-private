---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Tokenizing
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Tokenization

::left::
* Recall that tokenization is the splitting of a string into individual tokens
  * These tokens will eventually be fed to a Parser
  * Tokenization is also called
    * Lexing
    * Lexical Analysis
    * Scanning

::right::
![tokenizing_1.png](tokenizing_1.png)


---
layout: two-cols-header
---

## Tokenization

::left::
* At right we see a simple string that conforms to the Lox programming language
* Below, you can see this string broken into lexemes
* A lexeme is the smallest sequence of characters that still represents something in the language

::right::
![tokenizing_1.png](tokenizing_1.png)


---
layout: two-cols-header
---

## Tokenization

::left::
* Lexemes are only the raw characters
* When a lexeme is bundled with other data, it becomes a token
* What are some of these other things?

::right::
![tokenizing_1.png](tokenizing_1.png)


---
layout: two-cols-header
---

## Tokens

::left::
* Token Type
  * A token has a token type, which can be used by the parser to match a particular token
  * Example token types:
    * Left paren
    * Plus
    * String
    * Number
    * For

::right::
![tokenizing_1.png](tokenizing_1.png)


---
layout: two-cols-header
---

## Tokens

::left::
* Literal value
  * Some tokens represent Literal Values
    * string literals: “foo”
    * numeric literals: 1, 2.3
    * boolean literals: true, false
  * As opposed to a symbolic value like a variable
  * A token of a literal value needs to know the actual value that it represents

::right::
![tokenizing_1.png](tokenizing_1.png)


---
layout: two-cols-header
---

## Tokens

::left::
* Question: what value does the string literal lexeme “lox” represent?

::right::
![tokenizing_1.png](tokenizing_1.png)


---
layout: two-cols-header
---

## Tokens

::left::
* Location Information
  * A token should know where it is located in the source code
  * Ideally
    * Line number
    * Line offset
    * Absolute offset
    * Absolute end
  * Not all of this is strictly necessary, but makes for better error messages, tools, etc.
    
::right::
![tokenizing_1.png](tokenizing_1.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* The core of any tokenization algorithm is going to be a loop that is scanning over the characters in a string:
  * while( more tokens )
    * examine next token to determine lexeme type
    * scan until lexeme ends
    * add token information

::right::
![tokenizing_2.png](tokenizing_2.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* Tokenization is closely related to Regular Expressions
* Here is the regex for variable names:    \[a-zA-Z_\]\[a-zA-Z_0-9\]*
* The rules for matching lexemes in a language are called the lexical grammar of that language

::right::
![tokenizing_2.png](tokenizing_2.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* The lexical grammar of most languages are simple enough to be classified as a regular language
* Intuitively, you can think of this as meaning that the lexing of a language is only a loop, it never recurses

::right::
![tokenizing_2.png](tokenizing_2.png)


---
layout: two-cols-header
---

## Regular Expressions

::left::
* There are tools that you can use that will take regular expressions and give you back a complete scanner
  * Lex or Flex are two popular examples
* We will be writing our scanner by hand
  * You will learn more
  * It's easy
  * It's more flexible

::right::
![tokenizing_2.png](tokenizing_2.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* There are many ways to implement a scanner
* We will look at the way that Bob does it and then look at implementing some of the Catscript tokenizer

::right::
![tokenizing_3.png](tokenizing_3.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* This is the basic outline of the algorithm
  * While we are not at the end of the string
    * Scan the next token
  * Finally, add an EOF and return the list of tokens

::right::
![tokenizing_3.png](tokenizing_3.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* So, what does scanning a token look like?
* Let's start with the simple stuff: one character tokens

::right::
![tokenizing_4.png](tokenizing_4.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* Pretty simple!
* Advance the tokenizer to the next character
* See what it is
* See why writing a bunch of garbo regex and feeding it to some impenetrable library is dumb?

::right::
![tokenizing_4.png](tokenizing_4.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* So, given the string “+”, the first character would be ‘+'
* It would fall through to the “case ‘+'” statement, add a PLUS token and then break
* You'd then be at the end of the file, and be done
* Congrats, you're lexing!

::right::
![tokenizing_4.png](tokenizing_4.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* What if you don't recognize a token?
* You still need to record this somehow for user feedback
* This is called a Lexical Error
* In lox, they added an error handler in the default case for the switch

::right::
![tokenizing_5.png](tokenizing_5.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* What about compound lexemes, like “!=”?
* When you first encounter the “!” you don't know if it is “!” as in “!true” or “!=” as in “10 != 3”
* In this case, Bob uses a second test with the match() method

::right::
![tokenizing_6.png](tokenizing_6.png)


---
layout: two-cols-header
---

## Implementing A Tokenizer

::left::
* A match method is very common in tokenizers:
  * If the next token matches the given character, consume it and return true
  * Otherwise return false
* Note that he uses a ternary expression here
  * kinda cutesy, I'm not a fan

::right::
![tokenizing_6.png](tokenizing_6.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* OK, what about longer tokens like  comments?
* If we see a slash, check if there is another slash
* Then consume the rest of the line until a newline character or EOF
* Note the use of a new method, peek()

::right::
![tokenizing_7.png](tokenizing_7.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* peek(), like match() is a very common method in tokenizers
* It looks at the next character, without consuming it
* This lets you do a match, without consuming that character

::right::
![tokenizing_7.png](tokenizing_7.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Next, Bob takes on white space
* Pretty easy, you just ignore it
* Note that he keeps track of the line when he hits newline characters
  * In Catscript, we will also keep track of line offset and absolute offset

::right::
![tokenizing_8.png](tokenizing_8.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Now the big guys: literals and symbols
* String literals are probably the hardest thing to tokenize
* If you encounter a quote, start scanning a string
* While you do not encounter a quote AND you are not at the end of the input, advance

::right::
![tokenizing_9.png](tokenizing_9.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Note that new lines are allowed in strings in lox
* Note that there is no way to include a quote character
  * Where's the backslash support?
* Note that he tests to see if the string wasn't closed
* Question: why doesn't he have to match() the final quote?

::right::
![tokenizing_9.png](tokenizing_9.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Finally, note that he produces the literal value of the string by taking the substring of the string, sans the leading and trailing quotes

::right::
![tokenizing_9.png](tokenizing_9.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Next, digits
* Similar to strings:
  * If the current character is a number, consume the remainder of the number
* Note that in lox, everything is a double
  * Catscript only supports integers
* Question: why no EOF check?

::right::
![tokenizing_10.png](tokenizing_10.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Finally, reserved words and identifiers
* What's a reserved word?
  * Things like “true”, “false”, “for”, “if”, “else”, etc.
* What's an identifier?
  * Things like “x”, “str”, etc.

::right::
![tokenizing_10.png](tokenizing_10.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Same ol' stuff, just different character classes
  * If the current token is alpha
    * consume alphanumeric characters
  * When we are done consuming characters, extract the string from the source code
  * Use a Map to look up if this is a keyword or not

::right::
![tokenizing_11.png](tokenizing_11.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* If it's not a keyword, add it as an Identifier
* Question: Why do we have to consume all alphanumeric tokens before we decide if something is a keyword?

::right::
![tokenizing_11.png](tokenizing_11.png)


---
layout: two-cols-header
---

## Longer Lexemes

::left::
* Answer: unlike with literals like strings and numbers, there are conflicts between identifiers and keywords that are not resolvable until a lexeme is fully constructed
  * “or” and “order”
* We need “maximal munch”: the match with the most characters wins

::right::
![tokenizing_11.png](tokenizing_11.png)


---
layout: two-cols-header
---

## Interesting Notes

::left::
* Not all languages have regular lexical grammars
  * Haskell, Python (!!!)
* White space is usually not significant beyond separating alphanumeric lexems
  * Significant in Python
  * Also sneaky significant in C, Ruby and CoffeeScript!

::right::
![tokenizing_11.png](tokenizing_11.png)


---
layout: two-cols-header
---

## Implementing Comments

::left::
* OK, enough lecturing, let's implement comments in the Catscript scanner!

::right::
![tokenizing_12.png](tokenizing_12.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
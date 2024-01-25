---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Transpiling
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* With bytecode behind us, we are now done with the main content of this course
* Congrats, you know how to build a real, honest compiler!
* But recall our compiler mountain from the book:

::right::
![transpiling_1.png](transpiling_1.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* There are a few different destinations
  * A High-Level Language
  * Bytecode
  * Machine Code
* We are finishing up generating JVM bytecode
  * Yay
* Today we are going look at Javascript transpilation

::right::
![transpiling_1.png](transpiling_1.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Transpilation is just a fancy word for: concatenating a bunch of code for another language together from a parse tree
* Sometimes this is called a source-to-source compiler

::right::
![transpiling_1.png](transpiling_1.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Transpilers came out of the javascript community and are used heavily there
* A language that compiles to javascript is called a compile-to-JS language
* A compiler can target javascript

::right::
![transpiling_1.png](transpiling_1.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* It is actually far more likely that you will build a transpiling compiler than a JVM byte code compiler in the real world
* “We need to convert files from format X to format Y”

::right::
![transpiling_1.png](transpiling_1.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* While “transpiling” might sound like a hack (and it is), it's actually an important technology
* Increasingly people are using transpiled languages, especially in the web development world!

::right::
![transpiling_1.png](transpiling_1.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* CoffeeScript was one of the original “Transpiled to JavaScript” languages 

https://coffeescript.org/ 

* Offered a lot of niceties over plain JavaScript circa 2010
* JavaScript has improved dramatically, so CoffeeScript has become less relevant
* However, there is a new transpiled language on the block…

::right::
![transpiling_2.png](transpiling_2.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* TypeScript!

https://www.typescriptlang.org/  

* Typescript is a scripting language from Microsoft that transpiles into JavaScript
* Provides static typing as well as a lot of other features on top of JavaScript
* Basically C#-in-the-browser
* A good language!

::right::
![transpiling_3.png](transpiling_3.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Increasingly popular!
* 28% of developers were using in the 2022 StackOverflow survey
  * More than use C!
* Proof that transpiled languages are not just toys

::right::
![transpiling_4.png](transpiling_4.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* And here is an example of a programming language that was transpiled at one point: 

https://hyperscript.org/

* I'll explain why I went back to an interpreted runtime at the end of this lecture…

::right::
![transpiling_5.png](transpiling_5.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* So, what does transpiling consist of?
* Taking CatScript like this:   

1 + 1

and turning it into JavaScript like this:

1 + 1 

::right::
![transpiling_5.png](transpiling_5.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* So, what does transpiling consist of?
* Taking CatScript like this:

1 + 1 

and turning it into JavaScript like this:

1 + 1 

::right::
![transpiling_6.png](transpiling_6.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Catscript was intentionally designed to be easy to transpile into javascript
  * We use a lot of the same keywords
  * Javascript syntax is pretty standard anyway
* JavaScript bytecode is hard enough, why make transpilation hard?

::right::
![transpiling_7.png](transpiling_7.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* So, how do we transpile?
* With a Visitor...
  * lol, no
* We just jam another method on the parse tree
  * transpile()
* The transpile() method takes a StringBuilder
  * A class for building a string

::right::
![transpiling_8.png](transpiling_8.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* It is the job of each element in the parse tree to generate the equivalent javascript
* Fortunately this is extremely simple for most elements!

::right::
![transpiling_8.png](transpiling_8.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Here we can see how the IntegerLiteral transpiles to javascript
* It just appends its literal value to the output
* Easy

::right::
![transpiling_8.png](transpiling_8.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Here we are transpiling the additive expression
* This captures the crux of transpilation: we are taking our parse tree and turning it into source code for another programming language

::right::
![transpiling_9.png](transpiling_9.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Most of the parse elements are pretty straight forward to transpile
* One element that, surprisingly, is not straight-forward is the ForStatement!

::right::
![transpiling_10.png](transpiling_10.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Consider the Catscript code at right
* It must transpile into the following javascript

::right::
![transpiling_10.png](transpiling_10.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Consider the Catscript code at right
* It must transpile into the following javascript
  * NB: we are targeting an older version of javascript, so you can't use the for loop newer constructs

::right::
![transpiling_11.png](transpiling_11.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* You need to be particularly careful about that variable i
* The Catscript didn't have an explicit index variable, but the javascript does
* If you nest for loops, you don't want them to both have the same index variable name!

::right::
![transpiling_11.png](transpiling_11.png)


---
layout: two-cols-header
---

## Transpiling Catscript

::left::
* Solution: keep a counter and generate i1, i2, i3, etc.
* Transpilation tends to be filled with hacks like this: automatic name generation, name mangling, etc.

::right::
![transpiling_11.png](transpiling_11.png)


---
layout: two-cols-header
---

## Transpiling Gotchas

::left::
* The further apart the input and output languages are in semantics and feature sets, the harder transpilation is
* It is difficult to transpile Java into JavaScript, because Java has much firmer type semantics than JavaScript
  * TypeScript does it though!

::right::
![transpiling_11.png](transpiling_11.png)


---
layout: two-cols-header
---

## Transpiling Gotchas

::left::
* Places you can get in trouble in transpilation:
  * Operator precedence
  * Scoping
  * Type checking
  * Missing features
    * Closures
    * String interpolation
    * etc.

::right::
![transpiling_11.png](transpiling_11.png)


---
layout: two-cols-header
---

## Transpiling Gotchas

::left::
* Catscript transpiles relatively easily into Javascript
* Catscript could also be transpiled into Java fairly easily as well since it has static types with an obvious mapping to Java types

::right::
![transpiling_11.png](transpiling_11.png)


---
layout: two-cols-header
---

## Source Maps

::left::
* One problem with transpilation is: how do you debug?
* The top TypeScript transpiles down to the bottom JavaScript
* How do you debug that?!?

::right::
![transpiling_12.png](transpiling_12.png)


---
layout: two-cols-header
---

## Source Maps

::left::
* The solution is a technology called source maps
* Source maps provide a mapping between generated JavaScript and the underlying source that generated them
* So, what does the source map look like for the code at right?

::right::
![transpiling_12.png](transpiling_12.png)


---
layout: two-cols-header
---

## Source Maps

::left::
* The solution is a technology called source maps
* Source maps provide a mapping between generated JavaScript and the underlying source that generated them
* So, what does the source map look like for the code at right?

::right::
![transpiling_13.png](transpiling_13.png)


---
layout: two-cols-header
---

## Source Maps

::left::
* The solution is a technology called source maps
* Source maps provide a mapping between generated JavaScript and the underlying source that generated them
* So, what does the source map look like for the code at right?

::right::
![transpiling_14.png](transpiling_14.png)


---
layout: two-cols-header
---

## Source Maps

::left::
* The mappings are encoded in that “mappings” field
* Pretty obscure, but does allow a browser like Chrome to debug into TypeScript files
* I'm not a huge fan of all this indirection, and my experience with source maps is not good

::right::
![transpiling_15.png](transpiling_15.png)


---
layout: two-cols-header
---

## Source Maps

::left::
* I tend to stick with plain JavaScript in my web programming to avoid:
  * A code generation step
  * Source maps
  * Having complicated deployments with multiple files
* YMMV

::right::
![transpiling_15.png](transpiling_15.png)


---
layout: two-cols-header
---

## Transpiling In The JS Community

::left::
* Transpilation, as mentioned before, came out of the javascript community
* It is used to convert newer versions of javascript into older versions, for browser compatibility

https://babeljs.io/

::right::
![transpiling_16.png](transpiling_16.png)


---
layout: two-cols-header
---

## Transpiling In The JS Community

::left::
* Many modern javascript libraries rely on transpilation for browser compatibility
* A transpilation step is often part of the build for large javascript projects
* See 

https://scotch.io/tutorials/javascript-transpilers-what-they-are-why-we-need-them

::right::
![transpiling_16.png](transpiling_16.png)


---
layout: two-cols-header
---

## Transpiling In The JS Community

::left::
* This has created a “culture of transpilation” in the JS community
  * Hacks on top of hacks
* Can you think of a major problem with transpiling code?
  * What does Carson tell you to learn how to do?

::right::
![transpiling_16.png](transpiling_16.png)


---
layout: two-cols-header
---

## Why Interpreted

::left::
* OK, let's talk about why hyperscript went from being interpreted to being transpiled and back again...

::right::
![transpiling_16.png](transpiling_16.png)


---
layout: default
---

## Summary

* Transpilation is a pretty simple idea: given an input source language, create an output program in another high level programming language
* Catscript is relatively easy to transpile into javascript, but with a few significant gotchas
  * ForStatement
* Transpiling is a major part of the Javascript community
  * And they deserve it!


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
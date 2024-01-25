---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Advanced Control Flow
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Control Flow

::left::
* Catscript has three basic control flow constructs
  * The if statement
  * The for statement
  * Functions + return statements

::right::
![other_control_flow_1.png](other_control_flow_1.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* You are no doubt familiar with other control flow mechanisms
  * Do loops
  * Do while loops
  * Switch statements
  * Anything else?

::right::
![other_control_flow_1.png](other_control_flow_1.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* Try/catch?
* Goto?
  * Does java have Goto?
  * Kinda: labeled breaks!

::right::
![other_control_flow_1.png](other_control_flow_1.png)


---
layout: two-cols-header
---

## Control Flow

::left::
* And believe it or not, at the JVM instruction level, yes, java has goto!

::right::
![other_control_flow_2.png](other_control_flow_2.png)


---
layout: two-cols-header
---

## Try/Catch

::left::
* The big one that most people are now familiar with is the try/catch mechanism
  * Fairly revolutionary back in the 90s
* Allows you to “throw” and “catch” errors across functional boundaries

::right::
![other_control_flow_3.png](other_control_flow_3.png)


---
layout: two-cols-header
---

## Try/Catch

::left::
* So, let's add a simple try/catch mechanism to Catscript, shall we?
  * Keep it simple stupid: You will be able to throw only strings and catch only strings
  * New keywords: try, throw, catch

::right::
![other_control_flow_3.png](other_control_flow_3.png)


---
layout: two-cols-header
---

## Try/Catch

::left::
* So you can see, not too crazy to add this control flow to Catscript now that we know how to lex, parse and evaluate!
* What about some more exotic control flow mechanisms?

::right::
![other_control_flow_3.png](other_control_flow_3.png)


---
layout: two-cols-header
---

## Generators

::left::
* Generators are another somewhat exotic control structure
* Allow you to create an iterator out of a function
  * What?

::right::
![other_control_flow_4.png](other_control_flow_4.png)


---
layout: two-cols-header
---

## Generators

::left::
* Consider this javascript function
* Note two things: the * after function and the yield keyword
* This function will produce an iterator
  * It does not return the iteration count!

::right::
![other_control_flow_4.png](other_control_flow_4.png)


---
layout: two-cols-header
---

## Generators

::left::
* The iterator will produce new values from start to end, stepping by the given step
* When the function returns, the iterator completes
* Why would you want to do this, vs just producing a list of numbers?

::right::
![other_control_flow_4.png](other_control_flow_4.png)


---
layout: two-cols-header
---

## Generators

::left::
* Of course, for this example, the object oriented approach isn't too difficult to imagine
  * Just have an object that takes all these arguments and satisfies some Iterator interface
  * How hard is that?
  * Generally I don't find generators that useful or interesting
  * Just mind bending to think about implementations

::right::
![other_control_flow_4.png](other_control_flow_4.png)


---
layout: two-cols-header
---

## Async

::left::
* A more useful and interesting control flow technique that has emerged lately is the async/await functionality in javascript
* Ignore the gory details here for now: there is a function that asynchronously waits 2 seconds

::right::
![other_control_flow_5.png](other_control_flow_5.png)


---
layout: two-cols-header
---

## Async

::left::
* Normally to write code that handled that 2 second pause, you would pass a callback into the function
* This leads to callback hell
* To address this problem, javascript has introduced the async and await keywords as well as Promises

::right::
![other_control_flow_5.png](other_control_flow_5.png)


---
layout: two-cols-header
---

## Async

::left::
* Again, don't worry too much about the details, just note that the function marked async is able to interact with the asynchronous function in a linear fashion by using the await keyword
* Doesn't look like much, but this is sophisticated control flow!

::right::
![other_control_flow_5.png](other_control_flow_5.png)


---
layout: two-cols-header
---

## Hyperscript

::left::
* Hyperscript, the programming language I am working on, is designed to be async transparent
  * ish*
* This is a hyperscript event listener

::right::
![other_control_flow_6.png](other_control_flow_6.png)


---
layout: two-cols-header
---

## Hyperscript

::left::
* When the element this is on is clicked, the .clicked class will be added to it
* Hyperscript will then wait 2 seconds
* And then it will be removed
* This is an asynchronous set of operations, but hyperscript makes it read linearly

::right::
![other_control_flow_6.png](other_control_flow_6.png)


---
layout: two-cols-header
---

## Hyperscript

::left::
* Pretty cool!
* We'll look at hyperscript internals later on in the class, if we have time :)

::right::
![other_control_flow_6.png](other_control_flow_6.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
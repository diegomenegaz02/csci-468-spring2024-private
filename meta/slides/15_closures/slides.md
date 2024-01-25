---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Closures
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Closures

::left::
* Today we are going to talk about an important language feature that is not implemented in CatScript: Closures

::right::
![closures_1.png](closures_1.png)


---
layout: two-cols-header
---

## Closures

::left::
* Why?
  * I didn't learn about closures until after undergrad and became obsessed with them
  * I was responsible for adding them them to the gosu programming language
  * They are actually extremely useful in day to day programming if used properly

::right::
![closures_1.png](closures_1.png)


---
layout: two-cols-header
---

## Closures

::left::
* So, what is a closure?
  * Aka: lexical closure or function closure
* A closure consists of two things:
  * A function
  * An environment to evaluate that function within

::right::
![closures_1.png](closures_1.png)


---
layout: two-cols-header
---

## Closures

::left::
* Javascript has good closure support, so let's look at a closure in that language
* At right is a function that returns another function
  * A little mind bending if you haven't seen this before, but bear with me

::right::
![closures_2.png](closures_2.png)


---
layout: two-cols-header
---

## Closures

::left::
* This is a function that returns another function
* The createAdder() function creates a local variable
* It then returns an anonymous function
  * Note that the body of this anonymous function refers to the variable declared in createAdder()

::right::
![closures_2.png](closures_2.png)


---
layout: two-cols-header
---

## Closures

::left::
* Here we call the function createAdder() and assign it to a variable
* We then invoke the function a few times
* What do you think that this code prints?

::right::
![closures_3.png](closures_3.png)


---
layout: two-cols-header
---

## Closures

::left::
* Next, we call createAdder() again and invoke the returned function a few more times
* What do you think this code prints?

::right::
![closures_4.png](closures_4.png)


---
layout: two-cols-header
---

## Closures

::left::
* Finally, we invoke the original anonymous function again
* What do you think it prints?

::right::
![closures_5.png](closures_5.png)


---
layout: two-cols-header
---

## Closures

::left::
* The reason the two anonymous functions are returning two distinct streams of values is due to closures

::right::
![closures_5.png](closures_5.png)


---
layout: two-cols-header
---

## Closures

::left::
* When a function is defined in javascript (any function) a closure is bound to it
* The closure contains all of the current symbols available to that function

::right::
![closures_6.png](closures_6.png)


---
layout: two-cols-header
---

## Closures

::left::
* Here, the count variable is part of the closure associated with the function
* The scope of the variable, however, it tied to the body of createAdder()
  * When createAdder() returns, that scope is removed

::right::
![closures_6.png](closures_6.png)


---
layout: two-cols-header
---

## Closures

::left::
* However, the anonymous function that we returned retains the ability to refer to variables in that now-gone scope
  * Scopes obviously don't work the same way in javascript as they do in C!

::right::
![closures_6.png](closures_6.png)


---
layout: two-cols-header
---

## Closures

::left::
* This is the crux of a closure: the ability to refer to any symbol that is in scope lexically when the function is defined
* The function closes over all available symbols

::right::
![closures_6.png](closures_6.png)


---
layout: two-cols-header
---

## Closures in Java

::left::
* Java is an extremely object oriented programming language and traditionally only supported closures via inner classes

::right::
![closures_7.png](closures_7.png)


---
layout: two-cols-header
---

## Closures in Java

::left::
* Java is an extremely object oriented programming language and traditionally only supported closures via inner classes
* At right is the java equivalent of the earlier javascript

::right::
![closures_8.png](closures_8.png)


---
layout: two-cols-header
---

## Closures in Java

::left::
* We return a Callable defined anonymously that closes over the count variable in the createAddr() function
* Note however there is a compilation error...

::right::
![closures_8.png](closures_8.png)


---
layout: two-cols-header
---

## Closures in Java

::left::
* For reasons clear to nobody, the java designers decided that captured variables need to be declared final
  * That is, immutable

::right::
![closures_8.png](closures_8.png)


---
layout: two-cols-header
---

## Closures in Java

::left::
* For reasons clear to nobody, the java designers decided that captured variables need to be declared final
  * That is, immutable

::right::
![closures_9.png](closures_9.png)


---
layout: two-cols-header
---

## Closures in Java

::left::
* The way to get around this is as ugly as it is stupid: convert the reference into an array of size 1, and mutate that instead
* Pretty ugly, and therefore rarely used in practice...

::right::
![closures_10.png](closures_10.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* The folks at Sun recognized that this was pretty terrible and, in Java 8, added lambda expressions
* The term lambda comes from the lambda calculus, an early theoretical model of computation

::right::
![closures_10.png](closures_10.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* A lambda expression starts with an argument list followed by an arrow, followed by the body of a function
* You can imagine it replacing all the boilerplate needed when you declare an anonymous Callable

::right::
![closures_11.png](closures_11.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* This can be further simplified when the body of a lambda is a simple expression
* You can omit the braces and return statement entirely

::right::
![closures_12.png](closures_12.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* Note that they did not address the fact that variables captured by the closure of the lambda statement are not mutable
  * We are stuck with the size one array hack… probably forever

::right::
![closures_12.png](closures_12.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* Note also that you still need an interface here, Callable\<Integer\>
* Java does not have a notion of function types

::right::
![closures_12.png](closures_12.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* You can think of closures as anonymous functions or, kind of, as function pointers
* What is the type of these things?
* In a normal language, the return type of this function would be something like: 

function():int

::right::
![closures_12.png](closures_12.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* function():int is a function type
* It allows you to say: “This is a function that takes these arguments and returns this type”
* Java doesn't have these
* Instead it has Functional Interfaces

::right::
![closures_12.png](closures_12.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* “Any interface with a SAM(Single Abstract Method) is a functional interface”
* Here, Callable\<V\> has a single abstract method, call()
* So this interface can “stand in” for a true function type

::right::
![closures_13.png](closures_13.png)


---
layout: two-cols-header
---

## Lambda Expressions

::left::
* Except it can't, really, for reasons we won't go into
* OK, here's the technical reason: function types are covaraint on their return type but contravariant on their parameter types, and Java interfaces don't work that way

::right::
![closures_13.png](closures_13.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* OK, that's all great, we've got this kinda strange syntax and this weird concept of closures
  * Both of these came out of the functional programming world, which seems a little suspicious
* How is this useful in day to day programming?

::right::
![closures_14.png](closures_14.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* Consider the code at right
* We have a list of strings and we transform it into a list of lengths of those strings
* We do this by using the map() function, which takes a lambda expression

::right::
![closures_15.png](closures_15.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* Compare this with the explicit, iterative java code
* Isn't the lambda version better?
  * Err….

::right::
![closures_16.png](closures_16.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* The lambda expression version should be better, but it isn't
* The java language designers strike again!
* Rather than putting the map() method on List (or Iterable) where it should be, they introduced a whole new concept: Streamsb

::right::
![closures_17.png](closures_17.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* If you have a collection, you need to convert it to a stream with the stream() method
* And then, worse, convert it back with that insane collect() method
* Streams are useful in their own right, but most of us are working with Collections

::right::
![closures_17.png](closures_17.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* So, in typical java fashion, they managed to make a super useful feature very hard to use

::right::
![closures_17.png](closures_17.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* Here is what the code should look like
* And if it did look like that, you would all be using closures all the time
  * And, in fact, this is how it looks in most languages
* Unfortunately, we can't have nice things in java


::right::
![closures_18.png](closures_18.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* This is fine…
* Let's write a function that uses closures properly, filter()
* It will filter a list given a Predicate

::right::
![closures_19.png](closures_19.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* Ignore the generic insanity (we will talk about that later)
* The function takes two values: a list and a filter for that list
* It then returns all the values that pass the filter

::right::
![closures_19.png](closures_19.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* We can now use this function to filter our strings list in various ways
* Note that the second filter, in particular, uses a captured variable
* Neat!

::right::
![closures_20.png](closures_20.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* We have managed to abstract the notion of filtering away from a particular condition
* Or, another way to think about it: we have parameterized the filter algorithm, allowing us to pass in the small bit that varies between uses

::right::
![closures_21.png](closures_21.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* So, in Java, object oriented features allow us to abstract data
* Well, closures allow us to abstract algorithms

::right::
![closures_21.png](closures_21.png)


---
layout: two-cols-header
---

## How is this Useful?

::left::
* This is actually really, really cool and the functional folks are right to be smug about it
* Thankfully it has made its way into OO languages like C#, Typescript, etc.
* Unfortunately, due to poor design, it will probably never be as clean in java...

::right::
![closures_21.png](closures_21.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
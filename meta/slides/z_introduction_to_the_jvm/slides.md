---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# The Final Boss: JVM Byte Code
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## The JVM

::left::
* We have been running our compiler code in the Java Virtual Machine all semester
* Now it's time to turn the snake around on itself and generate code, more specifically, byte code that can run on this mysterious machine

::right::
![introduction_to_the_jvm_1.png](introduction_to_the_jvm_1.png)


---
layout: two-cols-header
---

## The JVM

::left::
* The JVM, as the name suggests, is a virtual machine
* It emulates a simplified computing machine that can interpret Java bytecode
* Java bytecode is just a set of simple instructions, not unlike a simplified version of assembly code

::right::
![introduction_to_the_jvm_1.png](introduction_to_the_jvm_1.png)


---
layout: two-cols-header
---

## The JVM

::left::
* The JVM fundamentally operates on three types of values
  * Integers
    * Various sizes
  * Floating point
    * Various sizes
  * References
    * These are… pointers!

::right::
![introduction_to_the_jvm_2.png](introduction_to_the_jvm_2.png)


---
layout: two-cols-header
---

## The JVM

::left::
* The JVM has ClassLoaders, whose job is to load, well, classes, specified in the Java bytecode format
* These classes can define methods, fields, etc and the JVM provides infrastructure for executing the classes logic

::right::
![introduction_to_the_jvm_2.png](introduction_to_the_jvm_2.png)


---
layout: two-cols-header
---

## The JVM

::left::
* The JVM has ClassLoaders, whose job is to load, well, classes, specified in the Java bytecode format
* These classes can define methods, fields, etc and the JVM provides infrastructure for executing the classes logic

::right::
![introduction_to_the_jvm_2.png](introduction_to_the_jvm_2.png)


---
layout: two-cols-header
---

## Garbage Collection

::left::
* One of the wonderful features of the JVM is that it is garbage collected
* References are occasionally scanned from the GC root references to determine if an object has anything pointing to it
  * If not, the object and memory are reclaimed

::right::
![introduction_to_the_jvm_3.png](introduction_to_the_jvm_3.png)


---
layout: two-cols-header
---

## Garbage Collection

::left::
* A GC root is something reachable within the current execution context
  * Static stuff
  * Things pointed to by the stack

::right::
![introduction_to_the_jvm_3.png](introduction_to_the_jvm_3.png)


---
layout: two-cols-header
---

## Garbage Collection

::left::
* The JVM uses a “Mark and Sweep” algorithm
  * Start at the current GC roots
    * Mark all objects referred to recursively
  * Sweep all of memory, reclaiming anything not marked
* This is a simple and well understood algorithm

::right::
![introduction_to_the_jvm_3.png](introduction_to_the_jvm_3.png)


---
layout: two-cols-header
---

## Garbage Collection

::left::
* It was noticed that most objects had either very short lives or very long lives
* This insight was used to create a mixed garbage collection mechanism
  * Objects begin in one area of memory
  * When they survive for a while, they are moved to another

::right::
![introduction_to_the_jvm_3.png](introduction_to_the_jvm_3.png)


---
layout: two-cols-header
---

## Garbage Collection

::left::
* The two areas have different gc algorithms, tuned for their behavior
* This is called generational garbage collection
  * The shorter lived area uses an algorithm called stop-and-copy which is more efficient if you don't have many live references

::right::
![introduction_to_the_jvm_3.png](introduction_to_the_jvm_3.png)


---
layout: two-cols-header
---

## Data Types

::left::
* As I mentioned earlier, the JVM has three fundamental data types
  * Integers
  * Floating point
  * References
* Note that there is no boolean
  * booleans are represented with integers
  * chars/bytes - same

::right::
![introduction_to_the_jvm_4.png](introduction_to_the_jvm_4.png)


---
layout: two-cols-header
---

## Data Types

::left::
* Integer and floating point values are called primitive values
* All other stuff (including arrays of primitives) are reference values

::right::
![introduction_to_the_jvm_4.png](introduction_to_the_jvm_4.png)


---
layout: two-cols-header
---

## Data Types

::left::
* This is the big distinction in the JVM: between primitives and references
* In GC, primitives are ignored, references are traced
* Most interesting things are references:
  * Strings
  * Lists
  * etc.

::right::
![introduction_to_the_jvm_4.png](introduction_to_the_jvm_4.png)


---
layout: two-cols-header
---

## Data Types

::left::
* Note that under the covers, reference values are pointers
* When you call a method with a String parameter, you are really passing a pointer to that string in memory, not making a copy of the string
* Java “hides the pointer”

::right::
![introduction_to_the_jvm_4.png](introduction_to_the_jvm_4.png)


---
layout: two-cols-header
---

## Autoboxing

::left::
* The java has reference types that correspond to the various primitive types
  * int → java.lang.Integer
  * char → java.lang.Character
  * …
  * float → java.lang.Float

::right::
![introduction_to_the_jvm_5.png](introduction_to_the_jvm_5.png)


---
layout: two-cols-header
---

## Autoboxing

::left::
* An interesting aspect of the JVM is that it supports autoboxing and unboxing
* Here we have a primitive int stored in x
* Yet we are allowed to assign it to a reference value Object

::right::
![introduction_to_the_jvm_5.png](introduction_to_the_jvm_5.png)


---
layout: two-cols-header
---

## Autoboxing

::left::
* Under the covers, Java will convert x to a java.lang.Integer
* Convenient!
* Also expensive...

::right::
![introduction_to_the_jvm_5.png](introduction_to_the_jvm_5.png)


---
layout: two-cols-header
---

## Autoboxing

::left::
* This means you can kind of use primitives with things like lists
* Here each primitive int is autoboxed into an Integer and added to the List

::right::
![introduction_to_the_jvm_6.png](introduction_to_the_jvm_6.png)


---
layout: two-cols-header
---

## Autoboxing

::left::
* You are going to have to support primitive ints in CatScript
* Sorry...

::right::
![introduction_to_the_jvm_6.png](introduction_to_the_jvm_6.png)


---
layout: two-cols-header
---

## Autoboxing

::left::
* Spot the NullPointerException...

::right::
![introduction_to_the_jvm_7.png](introduction_to_the_jvm_7.png)


---
layout: two-cols-header
---

## Autoboxing

::left::
* Int1 is a reference type, java.lang.Integer
* When it is used in a mathematical operation, it is automatically unboxed
* If the reference is null, you get a NullPointerException!

::right::
![introduction_to_the_jvm_8.png](introduction_to_the_jvm_8.png)


---
layout: two-cols-header
---

## JVM Architecture

::left::
* The JVM runtime consists of
  * A stack per thread
  * A program counter per thread
* The Non-Heap
  * Constants, code, strings, etc.
* The Heap
  * Split into “young” and “old” objects

::right::
![introduction_to_the_jvm_9.png](introduction_to_the_jvm_9.png)


---
layout: two-cols-header
---

## The Stack

::left::
* Within a stack, you have frames, which correspond to function calls
* A frame has
  * A return value
  * Local “slots” for variables
  * A reference to the current class
  * An operand stack

::right::
![introduction_to_the_jvm_10.png](introduction_to_the_jvm_10.png)


---
layout: two-cols-header
---

## The Stack

::left::
* A method will shuffle variables to and from the local variable slots and use the operand stack to do computations

::right::
![introduction_to_the_jvm_10.png](introduction_to_the_jvm_10.png)


---
layout: two-cols-header
---

## The Operand Stack

::left::
* The operand stack is used to do expression evaluation
* int x = 1 + 1 ends up looking like this
  * Op stack starts empty : \[ \]
  * push 1 → \[ 1 \]
  * push 1 → \[ 1, 1 \]
  * add → \[ 2 \]
  * store to slot for x → \[   \]

::right::
![introduction_to_the_jvm_10.png](introduction_to_the_jvm_10.png)


---
layout: two-cols-header
---

## The Operand Stack

::left::
* This is the sort of code you will be writing for the Catscript compiler
* Thankfully the stack operations nicely fit in with the recursive layout of our parse tree
  * Not nearly as hard as it seems!

::right::
![introduction_to_the_jvm_10.png](introduction_to_the_jvm_10.png)


---
layout: two-cols-header
---

## Non-Heap Memory

::left::
* This area contains things like strings, runtime constants, etc. as well as the class data
* Class data includes constants, field references, method references, etc. as well as the actual bytecode implementation of methods of the class

::right::
![introduction_to_the_jvm_11.png](introduction_to_the_jvm_11.png)


---
layout: two-cols-header
---

## Non-Heap Memory

::left::
* At runtime, objects have pointers to their class data in order to properly look up methods, fields, etc.

::right::
![introduction_to_the_jvm_11.png](introduction_to_the_jvm_11.png)


---
layout: two-cols-header
---

## Heap Memory

::left::
* The Heap is where objects live
* The JVM does not operate directly on objects, but rather operates on references
* The stack never has an object on it, only ints, floats and pointers/references

::right::
![introduction_to_the_jvm_12.png](introduction_to_the_jvm_12.png)


---
layout: two-cols-header
---

## Heap Memory

::left::
* Note again that the JVM has a generational garbage collector
* The young generation has a faster gc algorithm, tuned for low survival rates
* GC of the old generation happens infrequently, using traditional mark-and-sweep

::right::
![introduction_to_the_jvm_12.png](introduction_to_the_jvm_12.png)


---
layout: two-cols-header
---

## JIT Compilation

::left::
* Java started as a pure virtual machine
  * Basically an emulator
  * It was slow, especially on hardware from the late 90s
* To address this, Sun Microsystems introduced JIT compilation under the name Hotspot

::right::
![introduction_to_the_jvm_13.png](introduction_to_the_jvm_13.png)


---
layout: two-cols-header
---

## JIT Compilation

::left::
* JIT Compilation is when bytecode is, itself, compiled further into machine specific code
  * This is done at runtime and as needed
* Can produce extremely fast code, but does incur some runtime cost

::right::
![introduction_to_the_jvm_13.png](introduction_to_the_jvm_13.png)


---
layout: two-cols-header
---

## JIT Compilation

::left::
* Generally, java will be about 20-50% slower than equivalent C
* However
  * Startup times will be significantly worse due to JVM overhead, JIT, etc.
  * Memory usage will almost certainly be worse as well

::right::
![introduction_to_the_jvm_14.png](introduction_to_the_jvm_14.png)


---
layout: two-cols-header
---

## The JVM

::left::
* Despite the performance issues, the JVM is a heck of a technical achievement
* It's too bad that java has become largely associated with big, boring corporate environments, because the JVM is a great piece of technology
  * Hotswap story...

::right::
![introduction_to_the_jvm_15.png](introduction_to_the_jvm_15.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
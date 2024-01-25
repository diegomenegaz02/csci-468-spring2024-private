---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Byte Code
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Last Time

::left::
* Last time we took a tour of the JVM components
* Today we are going to look at the language of the JVM and start figuring out how to generate executable bytecode for our compilers

::right::
![jvm_bytecode_1.png](jvm_bytecode_1.png)


---
layout: two-cols-header
---

## Bytecode

::left::
* Today we will start off by looking at simple expressions
* Expressions in java use the op stack to evaluate
* The op stack acts kind of like that RPN calculator we did all the way back at the start

::right::
![jvm_bytecode_1.png](jvm_bytecode_1.png)


---
layout: two-cols-header
---

## Using The IDE

::left::
* Before we get into the details, I'm going to show you The Trick
* You can view the bytecode output by  javac in IntelliJ
* Step 1 - Make sure your class is compiled
  * Build → Build Project

::right::
![jvm_bytecode_2.png](jvm_bytecode_2.png)


---
layout: two-cols-header
---

## Using The IDE

::left::
* Step 2 - From the View menu, select “Show Bytecode”
* Tip - Your cursor needs to be inside the class for “Show Bytecode” to show up sometimes
  * I don't know why…
* Tip - Make sure your class has been compiled or you will see older bytecode

::right::
![jvm_bytecode_3.png](jvm_bytecode_3.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* OK, here we have our first step into the world of bytecode
* What's going on?
* Let's go line by line

::right::
![jvm_bytecode_4.png](jvm_bytecode_4.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* add(I)I - this is a method that take a parameter of type int (I) and returns an int (I)
* L0 is a label
* LINENUMBER is debugger information to tell the debugger what line the instructions after L0 came from

::right::
![jvm_bytecode_4.png](jvm_bytecode_4.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* This is how IntelliJ can know what line you are on when a given bit of java is executing
* And this is also why you can get funny results when you are running against modified classes

::right::
![jvm_bytecode_4.png](jvm_bytecode_4.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* ILOAD 1 - Load the integer (hence the I prefix) at slot 1 onto the operand stack
* The slots here are local variables
  * Which includes parameters!
* Stored in the current frame
* Let's look back at our JVM schema...

::right::
![jvm_bytecode_4.png](jvm_bytecode_4.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* Our frame (the data for the current method execution) has a few different data stores
  * Local variables (slots)
  * An operand stack
* ILOAD 1 is saying “push the integer value in the second slot onto the operand stack”
  * Slots are base 0

::right::
![jvm_bytecode_5.png](jvm_bytecode_5.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* So what does our stack look like right now?  Pretty basic, just has the value of the parameter on it:

`----------`

\<param 1\>

`--------------`

::right::
![jvm_bytecode_5.png](jvm_bytecode_5.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* Why the second slot?
* What do you think is in the first slot (slot 0)?

::right::
![jvm_bytecode_5.png](jvm_bytecode_5.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* OK, so now we've loaded the data in slot 1 onto the operand stack, what's next?
* BIPUSH - Push the value 13 onto the stack as a byte integer
  * 13 is a small integer value that fits in 1 byte

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* OK, so, now, what does our op stack look like?

`----------` 

13 

\<param 1\>

`--------------`

* We now have an operand stack with two values on it

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* OK, next instruction, IADD
* IADD will pop the top two operands off the operand stack add them as integers and then push the result back on the operand stack as an integer
  * This is just like our RPN calculator
  * And also like the LMSM

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* So what does our stack look like after IADD?

`----------` 

\<the result\>

`--------------`

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* The result is sitting on the operand stack, ready to be made use of
* Which brings us to...

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* IRETURN, which pops this final value off the stack and into the return slot and signals to the JVM that the method is finished, and the value can be returned

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* Next up is another label
* Followed by some metadata again for the debugger
* The first LOCALVARIABLE tells the debugger the type of the this slot

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* The second LOCALVARIABLE tells the debug that the item stored in slot 1 has the name “i” between labels L0 and L1

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* MAXSTACK tells the JVM the maximum number of operands that will be on the stack
* MAXLOCALS tells the JVM how many local slots are needed for parameters, the ‘this' pointer, any local variables, etc.

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* GOOD NEWS!  You don't need to worry about generating any of the stuff after L1
* We are going to use a library, ASM, that takes care of all that for us

https://asm.ow2.io/

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## My First Bytecode

::left::
* OK, that's our first introduction to bytecode
* So, how on earth are we going to generate this stuff?
* I have some additional good news: your professor has given you a lot of infrastructure code that wraps ASM and makes it more tolerable

::right::
![jvm_bytecode_6.png](jvm_bytecode_6.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* Let's look at the compile() method on AdditiveExpression
* Note that it takes a ByteCodeGenerator
* This object allows you to build up bytecode for a given feature

::right::
![jvm_bytecode_7.png](jvm_bytecode_7.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* For expressions, we just want to work with the operand stack
* No side effects, just operand stack changes
* So, how do we compile an addition?

::right::
![jvm_bytecode_7.png](jvm_bytecode_7.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* Pretty easy, actually:
  * Compile the left hand side
    * When compiling this, it should push the value it evaluates to onto the stack
  * Compile the right hand side
    * Again, its value will end up on the stack, on top of the left hand side value
  * Finally, add an IADD or ISUB instruction, which will apply the appropriate operator to those two values

::right::
![jvm_bytecode_7.png](jvm_bytecode_7.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* That's it!
* The left hand side will compile to a set of instructions that will leave it's value on the stack
* The right hand side will do the same, and leave its value on top of the left hand side value
* Then we add or subtract it w/ an instruction

::right::
![jvm_bytecode_7.png](jvm_bytecode_7.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* Let's look at IntegerLiteralExpression's compile() function
* Even easier!
* Just push the value of the integer onto the stack

::right::
![jvm_bytecode_8.png](jvm_bytecode_8.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* So, if you have 1 + 2, you will end up with the following instructions:
  * Push 1
  * Push 2
  * Add
* Boom!  It works!

::right::
![jvm_bytecode_8.png](jvm_bytecode_8.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* And, even cooler, if you have 1 + (2 + 3 ) you end up with
  * Push 1
  * Push 2
  * Push 3
  * Add
  * Add
* So you can see how nested expressions just work out

::right::
![jvm_bytecode_8.png](jvm_bytecode_8.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* This is a beautiful aspect of a stack machine: it corresponds very well with expression evaluation, and it's why the JVM includes an operand stack for expression evaluation

::right::
![jvm_bytecode_8.png](jvm_bytecode_8.png)


---
layout: two-cols-header
---

## Adding In Catscript

::left::
* OK, next time we'll get into the details more of generating code and what different op codes do
* Hang with me, kids, you can do this!

::right::
![jvm_bytecode_8.png](jvm_bytecode_8.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
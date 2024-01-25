---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Writing Code
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## The IntelliJ Creed

::left::
* This is my IDE
* There are many like it, but this is mine
* My IDE is my best friend.  It is my life.
* I must master it as I must master my life

::right::
![writing_code_1.png](writing_code_1.png)


---
layout: two-cols-header
---

## The IntelliJ Creed

::left::
* Without me, my IDE is usele­ss
* Without my IDE, I am useless
* I must write code true
* I must write code better than the bugs, that are trying to crash my program
* I will

::right::
![writing_code_1.png](writing_code_1.png)


---
layout: two-cols-header
---

## IntelliJ

::left::
* I am obviously a big fan of IntelliJ and all JetBrains IDEs
  * This lecture brought to you by Jetbrains
* They are a family of IDEs that have been around forever
* Written in Java using Swing
  * Of all things!
* Excellent, mature tooling for writing code

::right::
![writing_code_1.png](writing_code_1.png)


---
layout: two-cols-header
---

## But what about...

::left::
* There are other IDEs
* Some are pretty good
* But this is mine, and so it is what I will teach you

::right::
![writing_code_1.png](writing_code_1.png)


---
layout: two-cols-header
---

## Project Tree

::left::
* Create a new thing
  * Class, interface, package, file, etc.
* Find in path (more on that later)
* Build/Rebuild/Run
* “Show in Files” - opens in the OS file explorer
* Open In Terminal - opens in a terminal within IJ

::right::
![writing_code_2.png](writing_code_2.png)


---
layout: two-cols-header
---

## Terminal

::left::
* If you open a directory in the terminal it will launch a new terminal sub-tab in the terminals tab
* Very useful for kicking out to a command line, running maven, etc.

::right::
![writing_code_3.png](writing_code_3.png)


---
layout: two-cols-header
---

## Maven

::left::
* Maven is an open source build tool for java projects
* I kind of hate it, but it has a good dependency system so I use it
* IJ has great Maven support
* If you ever see library code missing, try clicking the refresh button in the maven tab

::right::
![writing_code_4.png](writing_code_4.png)


---
layout: two-cols-header
---

## Git

::left::
* We will talk more about Git in a later lecture, but it is a widely used version control system
* I kind of hate it
* But IJ has great git support
  * Noticing a pattern?

::right::
![writing_code_5.png](writing_code_5.png)


---
layout: two-cols-header
---

## Git

::left::
* IJ is a little confusing here
  * There is a “Git” tab and a “Commit” tab
  * Note that the “Commit” tab is, by default, on the left hand side
    * I move it to the bottom because I'm old

::right::
![writing_code_5.png](writing_code_5.png)


---
layout: two-cols-header
---

## Git

::left::
* The “Git” tab shows you
  * The history for your project
  * The branch structures
  * Local and remote information
* Useful for exploring the changes that have been made to a project over time
* Not super useful in my classes

::right::
![writing_code_5.png](writing_code_5.png)


---
layout: two-cols-header
---

## Git

::left::
* The “Commit” tab shows you what changes have been made, but not yet committed
* Extremely useful in my classes
* You will check the files you want committed, enter a commit message and commit (and probably push too!)

::right::
![writing_code_6.png](writing_code_6.png)


---
layout: two-cols-header
---

## Git

::left::
* If you want to see what changed in a file, select it and hit Ctrl-D (diff)
* Not a super interesting diff, I just added a new line, but still, pretty cool!
* Note that I can click the >> to revert that line

::right::
![writing_code_7.png](writing_code_7.png)


---
layout: two-cols-header
---

## Finding Stuff

::left::
* IntelliJ has a great global find function available with Ctrl-Shift-F (find in path)
* Note that you can change
  * The scope of the search
  * Give it a file mask
  * Change case sensitivity
  * And more!
* Ctrl-Enter opens the results in a tab...

::right::
![writing_code_8.png](writing_code_8.png)


---
layout: two-cols-header
---

## Finding Stuff

::left::
* The find tab is great!
* You can hit enter and it will open the search result in an editor window
* You can control the grouping by clicking the little four square icon
* You can re-run the search by clicking the double play button

::right::
![writing_code_9.png](writing_code_9.png)


---
layout: two-cols-header
---

## Finding Stuff

::left::
* If you run a new search it will replace this one
* Maybe you don't want that
* Right click and pin the tab…
* Useful if you have a big search and then are running sub-searches within it or just a common search

::right::
![writing_code_10.png](writing_code_10.png)


---
layout: two-cols-header
---

## Finding Stuff 2

::left::
* Another good way to find stuff by name is the “Navigate” functionality
* Ctrl-N
* Brings up a search dialog, allows you to search for a Class
  * Can also switch to files (ctrl-shift-n) or other options
* Hit enter to open the class file

::right::
![writing_code_11.png](writing_code_11.png)


---
layout: two-cols-header
---

## Finding Stuff 3

::left::
* Another good way to find recently edited files is “Recent Files”
* Ctrl-E
* Brings up the most recently edited files
  * You can expand the number of files in Settings
* A good way to bounce between two files: ctrl-e back and forth

::right::
![writing_code_12.png](writing_code_12.png)


---
layout: two-cols-header
---

## Editing Code

::left::
* The editor in IJ is truly unbelievable
* Obviously you have the expected functionality:
  * Hit ‘.' see what you can call
  * Syntax highlighting
  * The ability to expand and collapse code blocks
* But IJ has so much more...

::right::
![writing_code_13.png](writing_code_13.png)


---
layout: two-cols-header
---

## Editing Code

::left::
* Ctrl-click on a variable to jump to its definition
* Works for methods and classes too!
* Extremely fast way to navigate around code
  * Pair with Ctrl-E to jump/jump back

::right::
![writing_code_14.png](writing_code_14.png)


---
layout: two-cols-header
---

## Macros

::left::
* IJ supports macro expansion
* ‘iter' then the tab key will expand into a full iteration
* Allows you to pick the iterable thing you want to iterate over
* Tab key will then jump to the iteration var name
* Tab key will then jump to the body of the loop
  * Perfection itself

::right::
![writing_code_15.png](writing_code_15.png)


---
layout: two-cols-header
---

## Macros

::left::
* Confession: I don't remember how to write the C-Style array iteration anymore
* I just type itar tab and let IntelliJ do all that for me

::right::
![writing_code_16.png](writing_code_16.png)


---
layout: two-cols-header
---

## Keyboard Tricks

::left::
* With no selection
  * Ctrl-D - duplicate current line
  * Ctrl-C - copy current line
  * Ctrl-X - cut current line
* Ctrl-W - expand current selection
* Ctrl-Shift-W - narrow current selection
* Ctrl-Space - bring up auto complete

::right::
![writing_code_17.png](writing_code_17.png)


---
layout: two-cols-header
---

## Debugging

::left::
Probably the most important skill I can teach you in my classes is how to debug efficiently
Any file with a main method it in, or a test, should be able to be debugged
You will see a play icon in the gutter

::right::
![writing_code_18.png](writing_code_18.png)


---
layout: two-cols-header
---

## Debugging

::left::
* You can usually also right click within the main method or within a test and run debug from that context menu
* Or from the project tree
* Lots of options!

::right::
![writing_code_18.png](writing_code_18.png)


---
layout: two-cols-header
---

## Debugging

::left::
* Debugging isn't super useful without a break point
* A break point is a spot at which the debugger will break, that is stop execution
* You can create a breakpoint by clicking in the gutter to the right of the source code
* It will create a small red dot

::right::
![writing_code_19.png](writing_code_19.png)


---
layout: two-cols-header
---

## Debugging

::left::
* When a breakpoint is hit the line it is on will turn blue
* You can now inspect the state of the program

::right::
![writing_code_20.png](writing_code_20.png)


---
layout: two-cols-header
---

## Debugging

::left::
* When a breakpoint is hit the line it is on will turn blue
* You can now inspect the state of the program
* The debugger tab will show you:
  * The stack (useful!)
  * Variables (usefuller!)

::right::
![writing_code_21.png](writing_code_21.png)


---
layout: two-cols-header
---

## Debugging

::left::
* You can also evaluate expressions
* Right click and select “Evaluate Expression”
* Or Alt-Shift-8

::right::
![writing_code_22.png](writing_code_22.png)


---
layout: two-cols-header
---

## Debugging

::left::
* Brings up a popup that can be used to evaluate expressions in terms of the local context
  * E.g. local variables are available!
* Results are shown with an object explorer that lets you poke around, very useful!

::right::
![writing_code_23.png](writing_code_23.png)


---
layout: two-cols-header
---

## Debugging

::left::
* OK, we are done with this line of code and we want to continue
* F8 - Step Over
  * This will step over the current line of code and move to the next one
* F7 - Step Into
  * This will step into the port() function so we can see what it does

::right::
![writing_code_24.png](writing_code_24.png)


---
layout: two-cols-header
---

## Debugging

::left::
* F9 - Resume program
  * Continues until the next break point
* Right click and run to cursor
  * Runs execution to the point you right clicked at
* Force run to cursor
  * Same but will ignore any breakpoints until it gets to the cursor position

::right::
![writing_code_25.png](writing_code_25.png)


---
layout: two-cols-header
---

## Debugging

::left::
* Debugging is very, very powerful
* It is the best way to learn how programming works, in my opinion
* You need to get extremely comfortable with the debugger

::right::
![writing_code_26.png](writing_code_26.png)


---
layout: two-cols-header
---

## Debugging

::left::
* Do not sit and stare at buggy code trying to understand why it isn't working
* DEBUG, DEBUG, DEBUG AGAIN
* When you come to me with a problem, I'm going to be much happier if you have a good breakpoint set

::right::
![writing_code_26.png](writing_code_26.png)


---
layout: two-cols-header
---

## Summary

::left::
* IntelliJ is best
* Learn the tools that are available to you
* Especially the debugger!
* Debugging is life!
  * Especially for a student

::right::
![writing_code_27.png](writing_code_27.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
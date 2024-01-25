---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# UML - Review for capstone requirements
## Instructor: Carson Gross
## carson.gross@montana.edu

_Images and quotations from Learning UML 2.0 unless otherwise specified._

---
layout: two-cols-header
---

## Objective: Capstone UML

::left::
Structure | Behavior

* If you had 5 minutes to explain how the entire codebase works, what diagrams/reference material would you want on hand?
* High level first, then more granular.

_(something to keep in mind: conceptually this was supposed to have been done in the beginning/“design phase”)_

::right::
![1_uml_high_level.png](1_uml_high_level.png)


---
layout: two-cols-header
---

## Class Diagrams: Structure

::left::
Given that we are using an OO language:

* Data lives with its operations.
* Inheritance, abstraction.
* Polymorphism.
* Encapsulation.
* Dependencies on other classes, variables, methods.

Which shape puzzle pieces are allowed to go where?

::right::
![2_square_hole.png](2_square_hole.png)


---
layout: two-cols-header
---

## Notation - Attributes

::left::
One can convert class diagrams to real code and vice versa with sufficient detail.

::right::
![3_class_diagram.png](3_class_diagram.png)
![4_class_diagram_code.png](4_class_diagram_code.png)


---
layout: default
---

## Notation - Multiplicity

![5_multiplicity.png](5_multiplicity.png)

Note:
Often sufficient to just use empty bracket [ ]
More details like count or {unique} optional, include if important.

Important takeaway: Multiplicity number lives next to the class it is referring to.


---
layout: two-cols-header
---

## Notation - Operations

::left::
* Return types can be any class, primitive, or void.
* A return of a class type or a parameter of a class type indicates a dependency.
* Only one case where you do not need a return type: constructor methods.

::right::
![6_notation.png](6_notation.png)
![7_notation_2.png](7_notation_2.png)


---
layout: default
---

## Notation - Relationships

![8_notation_relationships.png](8_notation_relationships.png)


---
layout: two-cols-header
---

## Notation - Interfaces / Abstract Classes

::left::
Interface:
* No attributes, only operations. A capital “I” next to name can also be used to indicate interface.

Abstract Class:
* Indicated by italics, as well as the abstract methods. A capital “A” can also be used.

::right::
![9_notation_interfaces.png](9_notation_interfaces.png)


---
layout: default
---

## Relationship Example

![10_relationship_example.png](10_relationship_example.png)


---
layout: two-cols-header
---

## Catscript Example 1:

::left::
![11_catscript_example_1.png](11_catscript_example_1.png)

Generally omit setters and getters, boilerplate.

::right::
![12_catscript_example_1_2.png](12_catscript_example_1_2.png)


---
layout: two-cols-header
---

## Catscript Example 2:

Note that we have deviated from the actual compiler code.

::left::
![13_catscript_example_2.png](13_catscript_example_2.png)

::right::
![14_catscript_example_2_2.png](14_catscript_example_2_2.png)

---
layout: default
---

## Sequence Diagrams: Behavior

![15_sequence_diagram.png](15_sequence_diagram.png)


---
layout: two-cols-header
---

## Sequence Diagrams

::left::
One way to think about sequence diagrams:

A “debugger” view of your class diagram. If you were to step your debugger step-by-step through a use case and diagram the classes that are involved, you would have a sequence diagram.

::right::
![16_sequence_diagram_2.png](16_sequence_diagram_2.png)

---
layout: two-cols-header
---

## Notation

::left::
* Participants
  * Lifelines
  * Activation bars
* Messages
  * sync/async
  * Arguments
* Sequence Fragments
  * opt, ref, etc.

::right::
![17_notation_sequence.png](17_notation_sequence.png)


---
layout: two-cols-header
---

## Notation

::left::
* Participants
    * Lifelines
    * Activation bars
* Messages
    * sync/async
    * Arguments
* Sequence Fragments
    * opt, ref, etc.

::right::
![18_notation_sequence_2.png](18_notation_sequence_2.png)


---
layout: two-cols-header
---

## Notation

::left::
* Participants
    * Lifelines
    * Activation bars
* Messages
    * sync/async
    * Arguments
* Sequence Fragments
    * opt, ref, etc.

::right::
![19_notation_sequence_3.png](19_notation_sequence_3.png)


---
layout: two-cols-header
---

## Notation

::left::
* Participants
    * Lifelines
    * Activation bars
* Messages
    * sync/async
    * Arguments
* Sequence Fragments
    * opt, ref, etc.

::right::
![20_notation_sequence_4.png](20_notation_sequence_4.png)


---
layout: two-cols-header
---

## Notation

::left::
* Participants
    * Lifelines
    * Activation bars
* Messages
    * sync/async
    * Arguments
* Sequence Fragments
    * opt, ref, etc.

::right::
![21_notation_sequence_5.png](21_notation_sequence_5.png)


---
layout: two-cols-header
---

## Notation

::left::
* Participants
    * Lifelines
    * Activation bars
* Messages
    * sync/async
    * Arguments
* Sequence Fragments
    * opt, ref, etc.

::right::
![22_notation_sequence_6.png](22_notation_sequence_6.png)


---
layout: two-cols-header
---

## Participants

::left::
* Basic naming structure:
  * name : class_name

![24_participants_2.png](24_participants_2.png)

::right::
![23_participants.png](23_participants.png)

Explanation:

No class

No name

Class and name

Second element of eventHandlers list

No name, ref another fragment


---
layout: two-cols-header
---

## Messages

::left::
* Represent calls to other classes.
* Standard message signature:

signal_or_message_name (arguments) : return_type

* Activation bar essentially represents life of process

::right::
![25_messages.png](25_messages.png)


---
layout: two-cols-header
---

## Messages

::left::
1. No arguments, nothing known about return type.
2. Several arguments of type Number.
3. No arguments, return of type ReturnClass.
4. No arguments, return of call is assigned to myVar

::right::
![26_message_signature.png](26_message_signature.png)


---
layout: two-cols-header
---

## Messages

::left::
* Sync waits for return call
* Async does not (i.e. threaded)
* Return optional
* \<\<create\>\>
  * i.e. List myList = new ArrayList
* \<\<destroy\>\>
  * Non garbage collected

::right::
![27_messages_sync_async.png](27_messages_sync_async.png)


---
layout: default
---

## Messages - Java Example

![28_messages_java_example.png](28_messages_java_example.png)


---
layout: two-cols-header
---

## Fragment Type examples

::left::
1. ref
2. assert
3. loop
   * min, max, [guard_condition]
4. break
5. alt
   * [guard_condition1] … [else]
6. opt
   * [guard_condition]
7. par

![29_fragment_type.png](29_fragment_type.png)

::right::
1. Defined elsewhere.
2. Needs checking
3. i.e. for loop
4. If this happens, exit.
5. i.e. switch, if/else
6. i.e. if
7. Run in parallel.

![30_fragment_types.png](30_fragment_types.png)


---
layout: default
---

## Catscript

CatscriptTestBase.java

![31_catscript_test_base.png](31_catscript_test_base.png)

---
layout: two-cols-header
---

## Classes and Methods

::left::
* CatscriptParser
  * \<\<create\>\>
  * parse(src) : CatscriptProgram
    * Probably \<\<create\>\> call for CatscriptProgram somewhere

* CatscriptProgram
  * verify() : void
  * getOutput() : String
  * execute() : void

* BytecodeGenerator
  * \<\<create\>\>
  * compileToBytecode() : CatscriptProgram

::right::
![31_catscript_test_base.png](31_catscript_test_base.png)


---
layout: two-cols-header
---

## Classes and Methods


::left::

Abbreviated: this diagram does not represent all the lines of code above, this is an example

[Link to this diagram](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEENgGcDGAnEAHYAVSTgAheJSAOhQHsBbTEKACgEoAoNzeNUFLeAO2DQARAC4EydFlz4iJSEOglowGSw5cQPDgOHrSaaCLiJUGbAAVO+hUr2Q0azt146hmNJQDmaeNUPGJM2BzD29fGyRody8fakcNLX5BUUIATxUqABNIAHFIPntESjQI6E98wuBitgBtAFoAPhUCESpaekgGAGVgDD5PaCRKAFc0FEgmfx6+zxZ4FFAAN0QYZuAWNehGuzQjAB499EgVhoa5hZBllSirexZs+aWV+AAzFQMdjZkthpu0fSMdm6vRA-UGIzGE384lMUhCMV85ye10+Ox+0TC1H2hzQxxUpwANNBIMAUGQkZcVlFQrF7scLldjm97NSEXE+JRrpRFiydgSMbEjDgABb2GAgSLwaDgTnQajFGCeSiggaUPgAHT4otxRPInjIimgKHg4HAyko0AORxODU1MMkFhpviJ-Eycv4qU18txcpJwsobuNpsioKQIGy0EoL01AAMgUgxkwY+S0XVGk0ZIDbpl4ZivgQfqzMUYeRgXqlmBTGUXaQ8Gc9mR8nXFNo1ygUfFVdpacXjIKcq1T25VqnXkUz3mUKp3ikA)

(link has all the syntax for how to construct this diagram at https://sequencediagram.org/)

::right::
![31_catscript_test_base.png](31_catscript_test_base.png)
![32_catscript_sequence.png](32_catscript_sequence.png)



---
layout: two-cols-header
---

## Bigger example

::left::
A bigger example:

Note: this is a student example, there could be some small errors. This is simply intended to demonstrate a possible scale and complexity a diagram can take on.

What if this diagram was bigger and more complex?

::right::
![33_big_sequence_example.png](33_big_sequence_example.png)

---
layout: default
---

## A Parting Note on UML

![34_uml_note.png](34_uml_note.png)


---
layout: default
---

## Things to think about

* What are the central relationships that we rely on to make the compiler work?
* What are the major steps that occur?
* How would you model recursive descent?
* What are the responsibilities of the individual classes?
* Be careful not to add every single detail (clutter)


---
layout: two-cols-header
---

## UML and Class Diagrams

* Tools:
  * www.lucidchart.com free with some restrictions (limited number of shapes on canvas)
  * www.draw.io
  * Microsoft Visio
  * https://sequencediagram.org/
* Tips:
  * https://www.smartdraw.com/sequence-diagram/
  * Many, many online resources for UML
  * Use notes in your diagrams
* Capstone:
  * It is your responsibility to decide what level of detail to diagram.


---
layout: center
---

## Bonus Slides:
OO design pattern example, component diagrams, basic class diagram examples.



---
layout: default
---

## OO Concepts

Design Pattern Example: Decorator Pattern (Img: Matteo Bjornsson)

![35_decorator_pattern_example.png](35_decorator_pattern_example.png)


---
layout: two-cols-header
---

## Interfaces

::left::
“... interfaces represent how pieces of code expect to interact with each other.”

https://www.geeksforgeeks.org/interfaces-in-java/

::right::
![36_interface_example.png](36_interface_example.png)


---
layout: two-cols-header
---

## Components

::left::
* Basically all about interfaces.
* How do the larger pieces of your system depend on each other?
* Components are containers for any number of classes or other components.

::right::
![37_components_subsystem.png](37_components_subsystem.png)


---
layout: default
---

## Interfaces - Different Ways, Same Info

![38_interface_options.png](38_interface_options.png)


---
layout: default
---

## Interfaces - Working Together

![39_interfaces_working_together.png](39_interfaces_working_together.png)


---
layout: default
---

## Components are realized by classes

![40_components_realized.png](40_components_realized.png)


---
layout: default
---

## Components are realized by classes

![41_components_realized_2.png](41_components_realized_2.png)


---
layout: two-cols-header
---

## Delegation and Assembly Connector: Putting It All Together.

![42_delegation_assembly.png](42_delegation_assembly.png)


---
layout: center
---

## High Level Diagram Examples

\- White box  vs Black box views \-

  Diagrams on the next two slides are taken from:

  Kaupp, Tobias & Brooks, Alex & Upcroft, Ben & Makarenko, Alexei. (2007). Building a Software Architecture for a Human-Robot Team Using the Orca Framework. 3736 - 3741. 10.1109/ROBOT.2007.364051.


---
layout: default
---

## Example 1

![43_diagram_example_1.png](43_diagram_example_1.png)


---
layout: default
---

## Example 2

![44_diagram_example_2.png](44_diagram_example_2.png)


---
layout: center
---

## EXAMPLES

Taken from:

Software Engineering with
UML

--

Dr. Clemente Izurieta

Computer Science Department

Montana State University

--

Copyright © 2009 Clemente Izurieta

Montana State University


---
layout: default
---


Copyright © 2009 Clemente Izurieta

Montana State University

![45_izurieta_1.png](45_izurieta_1.png)


---
layout: default
---

Copyright © 2009 Clemente Izurieta

Montana State University

![46_izurieta_2.png](46_izurieta_2.png)


---
layout: default
---

Copyright © 2009 Clemente Izurieta

Montana State University

![47_izurieta_3.png](47_izurieta_3.png)


---
layout: default
---

Copyright © 2009 Clemente Izurieta

Montana State University

![48_izurieta_4.png](48_izurieta_4.png)


---
layout: default
---

Copyright © 2009 Clemente Izurieta

Montana State University

![49_izurieta_5.png](49_izurieta_5.png)


---
layout: default
---

Copyright © 2009 Clemente Izurieta

Montana State University

![50_izurieta_6.png](50_izurieta_6.png)


---
layout: default
---

Copyright © 2009 Clemente Izurieta

Montana State University

![51_izurieta_7.png](51_izurieta_7.png)


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
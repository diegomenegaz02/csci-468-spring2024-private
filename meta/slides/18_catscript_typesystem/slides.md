---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---
# The Catscript Typesystem
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* Catscript is a statically typed programming language
* The types of all variables and functions/parameters are known at compile time
* Catscript can make certain guarantees about the runtime types


::right::
![1_catscript_types.png](/1_catscript_types.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* The Catscript type system is extremely simple when compared with Java!
* int - integers
* string - Strings
* bool - a Boolean value
* object - any object


::right::
![1_catscript_types.png](/1_catscript_types.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* null - the type of the null value
* void - the type no type
  * E.g. a function that does not return a value

::right::
![1_catscript_types.png](/1_catscript_types.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* Catscript does have one complex type: list
* You can declare a list of a given type with “list\<T\>”
  * We are not implementing a full generics system, don't worry!
  * Examples:
    * list\<int\> - list of integers
    * list\<object\> - list of objects
    * list\<list\<int\>\> - a list of lists of integers

::right::
![1_catscript_types.png](/1_catscript_types.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* Assignability
  * The primary constraint on a type system is to determine if type T1 is assignable to T1
  * In Java:
    * java.lang.String is assignable to java.lang.Object
    * java.lang.Object is not assignable to java.lang.String

::right::
![1_catscript_types.png](/1_catscript_types.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* What do the assignability rules look like in Catscript?
* For the simple types, it's pretty simple:
  * Nothing is assignable from void
  * Everything is assignable from null
  * Otherwise we check the assignability of the backing java classes

::right::
![2_assignability.png](/2_assignability.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* The list type is a bit more complex
  * Null is assignable to a list
  * Otherwise, check if it's a list type
  * If so, check if the component types are assignable
  * Wait a second!
    * What's the problem here?

::right::
![2_assignability.png](/2_assignability.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* In CatScript, list types are covariant
  * A list L1 is assignable to a variable of list type L2 iff the component type of L1 is assignable to the component type of L2
  * Same as java arrays!  That's not sound!

::right::
![2_assignability.png](/2_assignability.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* Actually, it's fine
* In Catscript, lists are immutable: you can't add new values to them
* Because of this, list types can be covariant, and no logical errors can occur
  * Immutable collections are covariant

::right::
![2_assignability.png](/2_assignability.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* OK, so that's types, but how does the CatScript typesystem work in practice?

::right::
![3_typesystem_verify.png](/3_typesystem_verify.png)


---
layout: two-cols-header
---

## Catscript Typesystem

::left::
* Catscript Programs have a verify() method that verifies the code
  * Registers all function declarations in the symbol table
  * Recursively call validate() on child elements
  * Collect all parse errors from child elements
  * Throws an exception if there were any

::right::
![3_typesystem_verify.png](/3_typesystem_verify.png)


---
layout: two-cols-header
---

## Additive Expression

::left::
* The AdditiveExpression is a good place to start looking at our type system
* The validate() method on Parse Elements validates the type information regarding the expression
* Adds semantic errors if typing doesn't work properly

::right::
![4_additive_validate.png](/4_additive_validate.png)


---
layout: two-cols-header
---

## Additive Expression

::left::
* Let's take a look at what getType() does in AdditiveExpression…
* Consult the left hand side and the right hand side
* If either is string, the Expression type is a string
* Otherwise, it is an int

::right::
![4_additive_validate.png](/4_additive_validate.png)


---
layout: two-cols-header
---

## Additive Expression

::left::
* Right now validate() in AdditiveExpression only handles the case when it is of type integer
* You will need to update it handle strings as well: if type is string, both sides need to be strings

::right::
![4_additive_validate.png](/4_additive_validate.png)


---
layout: two-cols-header
---

## List Literal Expression

::left::
* Next lets look at validate on the ListLiteralExpression
* Right now the compiler sets the Component Type of the list to…
* The type of the first value
* Well, that's wrong… :)

::right::
![5_list_literal_validate.png](/5_list_literal_validate.png)


---
layout: two-cols-header
---

## List Literal Expression

::left::
* Instead we need to come up with an algorithm that unifies all the types in the list list literal
  * [1, 2] → list\<int\>
  * [“a”, 1] → list\<object\>
  * Etc.
* Let's think about this a bit together….

::right::
![5_list_literal_validate.png](/5_list_literal_validate.png)


---
layout: two-cols-header
---

## Identifier Expression

::left::
* The IdentifierExpression needs to resolve its type via the SymbolTable
* We will discuss the SymbolTable in more detail in a bit, but we can just look up the symbol by name
  * If it doesn't exist… error

::right::
![6_identifier_validate.png](/6_identifier_validate.png)


---
layout: two-cols-header
---

## Type Validation of Statements

::left::
* Generally, type validation of statements is more difficult than of expressions#
* Statements tend to be more complex and to interact with the SymbolTable

::right::
![7_type_validate.png](/7_type_validate.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* So, what is this Symbol Table thing, anyway?
* Nothing too complex:
  * A stack of maps
  * The map contains a string (that is, a name) and maps that name to a value that is either:
    * A function
    * A symbol type

::right::
![7_type_validate.png](/7_type_validate.png)


---
layout: two-cols-header
---

## Symbol Table

::left::
* We use the SymbolTable to make sure that
  * We don't re-declare a variable with the same name
  * If we refer to a symbol with an identifier, it exists
  * When we invoke a function, it exists, and we get the parameter and return types all verified

::right::
![7_type_validate.png](/7_type_validate.png)


---
layout: two-cols-header
---

## Type Validation of Statements

::left::
* Let's look how the SymbolTable is used in the AssignmentStatement#
* Note, this is an assignment not a variable declaration

::right::
![8_type_validate_2.png](/8_type_validate_2.png)


---
layout: two-cols-header
---

## Type Validation of Statements

::left::
* OK, what do we need to do here?
* First: does the symbol even exist?  That is, was a variable with this name declared and in scope?
* Next, if it does, is it compatible with what we are trying to assign to it?

::right::
![8_type_validate_2.png](/8_type_validate_2.png)


---
layout: two-cols-header
---

## Type Validation of Statements

::left::
* This is what a type system is at a practical level:
* Resolve a symbol or function or whatever to some type (that is, figure out what type it is)
* Then make sure the use of that feature is compatible with that type

::right::
![8_type_validate_2.png](/8_type_validate_2.png)


---
layout: two-cols-header
---

## Type Validation of Statements

::left::
* I will let you in on a little secret: I have implemented most of the type system stuff for Statements for you
  * Type systems aren't the focus of this class#
* Let's take a look at
  * ForStatement
  * IfStatement

::right::
![8_type_validate_2.png](/8_type_validate_2.png)


---
layout: two-cols-header
---

## Type Validation of Statements

::left::
* You are going to have three major pieces of validation functionality in the Statements
  * FunctionDefinitionStatement
    * Need to compute if all possible branches include a return
  * VarStatement
    * Verify compatibility and possible type inference
  * AssignmentStatement
    * Verify compatibility

::right::
![8_type_validate_2.png](/8_type_validate_2.png)


---
layout: two-cols-header
---

## AssignmentStatement

::left::
* The AssignmentStatement is the easiest of the bunch
* You have a right hand side and a symbol type you are trying to write into
  * Verify they are compatible

::right::
![9_assignment_validate.png](/9_assignment_validate.png)


---
layout: two-cols-header
---

## VarStatement

::left::
* The VarStatement is a bit more complicated
* First off, make sure there is no existing symbol with the name of this variable
* Next, we can have one of two situations:
  * The var declares a type
  * The var infers a type

::right::
![9_assignment_validate.png](/9_assignment_validate.png)


---
layout: two-cols-header
---

## VarStatement

::left::
* A variable that declares a type looks like this:# #var x : string = ...  #
* We know before looking at the right hand side what the type of x is, so we just need to verify compatibility with it

::right::
![9_assignment_validate.png](/9_assignment_validate.png)


---
layout: two-cols-header
---

## VarStatement

::left::
* A variable that infers a type looks like this:# #var x = “hello” #
* Here we are inferring the type from the right hand side
  * We don't actually need to typecheck, except to verify it's non-void!

::right::
![9_assignment_validate.png](/9_assignment_validate.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* The last one is probably the hardest
* You need to verify that function definitions that return a value are covered
  * That is: there is a return statement on all possible branches of logic

::right::
![10_function_validate.png](/10_function_validate.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* This can be done with a recursive function that you will need to implement, validateReturnCoverage()
* Note that it takes a list of statements, which should give you a hint of how to implement it

::right::
![10_function_validate.png](/10_function_validate.png)


---
layout: two-cols-header
---

## FunctionDefinitionStatement

::left::
* Let's start thinking about the easy case:
  * No if statements
  * No for loops
  * Just a straight series of statements
  * What must be true about this sequence of statements?

::right::
![10_function_validate.png](/10_function_validate.png)


---

## Summary

* The Catscript Type System is relatively simple, with a few basic types and one composite type (list)
* Expression and Statement validation will allow us to detect logical errors in our program and perform type inference
* Not a ton of type system work to do in the project, but just enough to make you think!
* Have a great spring break everyone!


---
layout: end
---

![0_msu_title.png](0_msu_title.png)
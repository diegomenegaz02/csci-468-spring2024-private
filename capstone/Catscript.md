# Catscript Guide

This document should be used to create a guide for catscript, to satisfy capstone requirement 4

## Introduction

Catscript is a simple scripting langauge.  Here is an example:

```
var x = "foo"
print(x)
```

## Features

### For loops
```
for(x in [1, 2, 3]) { print(x) }
```
For loops take a list of parameter expressions, and a variable identifier to be used as a local variable.
The for loop then executes a body of statements, pushing the scope into the code block within the brackets.
The local variable created in the parameters can be called to use whatever expression is currently being iterated over
within the statement body.

### If, Else Statement
```
if(true){ print(1) } else { print(2) }
```
If statements take a single parameter expression, and a body of statements to be executed if the expression
evaluates to true, pushing a local scope. If the expression doesn't evaluate to true the else statement executes the
body of statements and also pushes a local scope.

### Print Statement
```
print(1)
```
Print statements use a "print" keyword as well as an opening and closing parenthesis to encapsulate the expression or statement.
Valid types of a print statement are int, bool, null,string, and object. If the syntax of the print statement is correct it
will return the print statements evaluated expression. If there is a syntax error such as an unterminated argument list it will return an error.

### Variable Declaration Statement
```
var x = 42
```
Variable Declaration ('var') statements take an identifier and a right hand side expression. The variable statement uses explicit typing,
the new variable takes the explicit type of the expression value.It is executed by using a function in the runtime with
hashmap of variable names to the value of the expression. Variables that are declared can only be accessed by scopes lower
than they're current scope on the stack with global being the top of the stack.

### Assignment Statement
```
x = 25
```
Variable Assignment statements take an identifier of an already declared variable, and an expression value to insert
into the identifier's variable memory slot. Unless the declared variable is an object the expression type must equal the
variable's type, if they are not equal you will receive an incompatible type error.

### Function Definition Statement
```
function foo() : int { 
var x = 42
return x
}
```
Function definition statements take a function identifier, a list of parameter expressions, and an optional return
variable return type. The body of the function definition statement is made up by a list of statements, with an optional
return statement. If the return type is declared the return value must also have that type. This name and definition will
be linked via a hashmap to the function name key and the definition value.

### Function Call Statement
```
x = 25 + foo()
```
A function is called by referencing it through an identifier for an already defined function including all 
parameters specified in its declaration. This procedure invokes the function's statement body with the entered 
parameters and returns a value if one is required the types must match. An error called "unterminated argument list" 
will occur if there is a missing parenthesis at either end of the call.

### Return Statement
```
return "Hello  World"
```
Return statements necessitate the use of the "return" keyword and must include a value that corresponds with the 
function's declared return type. For instance, if a function is specified to return an integer (int), then the return value 
should also be of the integer type, making it a correct return statement. A return statement can provide any type of 
value, provided that it aligns with the type specified in the function’s definition

### Additive Operator (Additive Expression)
```
"Hello" + "World" + 1
```
Additive Operators contain the additive operator and two expressions.The Additive Operator adds the values of the expressions to the left and right. Types must match you cannot add <int> + <null>
or <int> + <boolean> this will result in an Incompatible Types Error.

## Special Case
When Additive Operators are used with string variable types they act as a concatinate call for the expressions to the left and right
of the operator. Types in this case do not need to match. 
For Integer values you will concat the .toString() value of the Integer to the String value.
For Null values you will concat "null" to the String value.
For Boolean values you will concat "true" or "false" to the String value.

### Minus Operator (Unary Expression)
```
-1
```
Minus operators contain an - operator and an expression.Unary operators negate the value of the expression to the right of 
the operator. For instance if you have -5 you would have 0 -|5|.

### Minus Operator (Additive Expression)
```
2-1
```
When minus operators have both a left and right expression and operator, they are evaluated under the Additive Expression as a subtraction.
The expression will evaluate as the right expression subtracted from the left expression, available for Int types only.

### Not Operator (Unary Expression)
```
not true
```
The not operator takes a boolean expression to the right, any other type of expression will throw an incompatible type error.
The operator acts as a ! operator flipping the boolean values of the left boolean value.

### Literals
Literal Types: Int, String, Boolean, Null, Object, List<x>
## Integers
```
1
```
Integer Literals are Integer values, with Type INT (32 bit).
## Strings
```
"Hola, como estas?"
```
String literals are the String values within the quotation marks. "java-style"
## Boolean
```
true
```
Boolean literals can be true or false.
## List 
```
[1,2,3]
```
List literals take a list of expressions seperated by commas. The list takes the type of whatever the elements are.
If the list contains different types they must be as objects and not seperate types, this will throw an incompatible type error.
The list values are expressed as an Arraylist of the literal elements.
## Null
```
null
```
Is a null value, there is nothing there memory spot is empty.
## Object 
```
x = <Any Literal Type> 
```
Objects encapsulate the literal types to be used as a dynamic type that can be evaluated later. It will then give the value
of the implicit type of the value assigned to it.

### Factor Operators
## Star Operators
```
2 * 3
```
The Star operator takes two expressions a left and a right. It multiplies the expressions on the right and left.
## Slash Operators
```
6/3
```
The slash operator takes two expressions a left and right of the operator. The slash devides the left value by the right value.

### Equality Operators
```
true == true
```
The equality operator takes two expressions and evaluates if their values are equal. The expression then produces a boolean value.
true if they are equal, false if they are not.
### Bang Equality Operators
```
true != true
```
The Bang Equality operator takes two expressions on the left and right and compares them.
If the expressions are equal it produces false.
If the expressions are not equal it produces true.

### Comparison Operators
Comparison expressions encapsulate logic surrounding greater than, less than, and equal.
## Greater than 
```
5 > 2
```
The greater than operator takes two expressions and checks if the expression on the left is greater than the expression on the right.
If the Left expression is greater than the right then it produces true, else false.
## Less than
```
2 < 5
```
The Less Than operator takes two expressions and checks if the expression on the left is less than the expression on the right.
If the Left expression is less than the right then it produces true, else false.
## Greater than or Equal to
```
5 >= 2
```
The greater than operator takes two expressions and checks if the expression on the left is greater than or equal to 
the expression on the right.
If the Left expression is greater than or equal to the right then it produces true, else false.
## Less than or Equal to
```
2 <= 5
```
The Less Than operator takes two expressions and checks if the expression on the left is less than or equal to
the expression on the right.
If the Left expression is less than or equal to the right then it produces true, else false.
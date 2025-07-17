# Execution Context

## Everything in JS runs in an Execution Context
An execution context is a box spited into two parts:
  - Variable Environment it's where variables and functions are saved as key-value pairs
  - Thread of execution, where line are executed one by one <strong>JavaScript it a synchronous single threaded language</strong>

## What happen when JS program runs?
```
  var n = 2;
  function square(num) {
    var ans = num * num;
    returns ans;
  }

  var square1 = square(n);
  var square2 = square(4);
```
Global execution context it's created in two phased
  - Memory creation phase
    Allocate into memory variables and function
    First n variable it's allocated but just the n, not the value that's execution, something like this will appear in the memory n: undefined
    Then function it's copied completely into the memory square: {..}
    Square1 and square2 vars are allocated similar to n
  - Code Execution
    Code it's read and executed line by line
    Then in the first line execution, the value 2 it's being associated into the memory as the value for the key n
    With the function nothing can be done
    On the next line square1 =... there is a function call, then how the function must be executed on the code part of the execution context nested mini executed context it's created
    Where another time both phases of execution are performed, num parameter and ans are being allocated as a key with undefined value, after this the code it's being executed,
    Value n from memory execution context it's being passed to nested memory execution context, num: 2, then ans it's assigned with num * num due code it's being executed, then
    special word 'return' means this nested execution context it's finished and next line to execute it's in the father context memory code part and the nested execution context it's deleted
    But in line square2 =... this prev process it's being repeated
  
  This nested execution contexts are stacked in the <strong>Call Stack</strong> the first item it's the GEC global execution context here it's where first execution context it's pushed (main program) then nested execution context created are pushed as other items in the stack and when are done are pop and discard, to pop a deeper context when stack it's empty our program has end
  Call Stack maintains the order of execution of execution contexts

## Hoisting in JavaScript (variables & functions)
```
  var x = 7;

  function sayHello() {
    console.log("Hello")
  }

  sayHello()
  console.log(x)
```
This works and prints Hello and in the next line 7

```
  sayHello()
  console.log(x)
  
  var x = 7;

  function sayHello() {
    console.log("Hello")
  }
```
This works and prints Hello and in the next line undefined
Due x exists in first phase of the execution context it's allocated as pair value undefined
If whe use other letter like console.log(y) will fail cause y is not in the memory of the execution context then in execution phase fails

```
  console.log(sayHello)
  
  function sayHello() {
    console.log("Hello")
  }
```
This will print the function completely due functions are allocated in the memory as is

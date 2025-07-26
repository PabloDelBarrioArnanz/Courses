# Execution Context

## Everything in JS runs in an Execution Context

An execution context is a box spit into two parts:

- Variable Environment it's where variables and functions are saved as key-value pairs
- Thread of execution, where a line is executed one by one **JavaScript is a synchronous single-threaded language**

## What happens when a JS program runs?

```JS
  var n = 2;
  function square(num) {
    var ans = num * num;
    returns ans;
  }

  var square1 = square(n);
  var square2 = square(4);
```

Global execution context it's created in two phases

- Memory creation phase
    Allocates into memory variables and function
    First n variable it's allocated but just the n, not the value that's execution; something like this will appear in the memory n: undefined
    Then function it's copied completely into the memory square: {.}
    Square1 and square2 vars are allocated similar to n
- Code Execution
    Code it's read and executed line by line
    Then in the first line execution, the value 2 it's being associated into the memory as the value for the key n
    With the function nothing can be done
    On the next line square1 =... there is a function call, then how the function must be executed on the code part of the execution context nested mini executed context it's created
    Where another time both phases of execution are performed, num parameter and ans are being allocated as a key with undefined value, after this the code it's being executed,
    Value n from memory execution context it's being passed to nested memory execution context, num: 2, then 'ans' var it's assigned with num * num due code it's being executed, then
    special word 'return' means this nested execution context it's finished and next line to execute it's in the father context memory code part and the nested execution context it's deleted
    But in line square2 =... this prev process it's being repeated
  
  This nested execution context is stacked in the **Call Stack** the first item it's the GEC global execution context here it's where first execution context it's pushed (main program) then nested execution context created are pushed as other items in the stack and when are done are pop and discard, to pop a deeper context when stack it's empty our program has end
  Call Stack maintains the order of execution contexts

## Hoisting in JavaScript (variables and functions)

```JS
  var x = 7;

  function sayHello() {
    console.log("Hello")
  }

  sayHello()
  console.log(x)
```

This works and prints Hello and in the next line 7

```JS
  sayHello()
  console.log(x)
  
  var x = 7;

  function sayHello() {
    console.log("Hello")
  }
```

This works and prints 'Hello' and in the next line undefined
Due x exists in the first phase of the execution context it's allocated as pair value undefined
If whe use another letter like console.log(y) will fail cause y is not in the memory of the execution context, then in execution phase fails

```JS
  console.log(sayHello)
  
  function sayHello() {
    console.log("Hello")
  }
```

This will print the function completely due functions are allocated in the memory as is
Hosting in JS doesn't work with let and cost cause this variable also is moved to the memory part of the context, but is not preassigned to undefined,
This part of the memory is called 'Temporal Dead Zone' and if we try to access them before they are declared will throw an error

```JS
  console.log(x)
  
  let x = 7;
```

This will throw an error 'Cannot access 'x' before initialization

But what happens with arrow functions?

```JS
  sayHello()

  var sayHello = () => { // No matters if you use function or not = function() {..}
    console.log("Hello")
  }
```

In this case, will return this error 'sayHello is not a function.' That's because on memory phase just the variable allocation is done and sayHello
is a variable that only will be allocated with an undefined value
Then just log the value of sayHello, no invocating it works and return undefined

In browser, if we set a breakpoint, the Call Stack has the GEC and inside the scope we can found the memory

## How functions works & Variable environment

```JS
  var x = 1;
  a();
  b();
  console.log(x);

  function a() {
    var x = 10;
    console.log(x);
  }

  function b() {
    var x = 100;
    console.log(x);
  }
```

Main and both functions has the same variable name defined.
The environment variable in the first phase the memory allocation, just main x var it's allocated with undefined value, then function a and b are allocated with the full function value.
Then the execution phase starts, the GEC created before it's pushed into the stack, the first instruction is run, then we have x = 1 in memory
Now a function execution must be executed, then a new execution context it's created, and again we have 2 phases, memory allocation and execution, in allocation, new x var it's allocated in this new execution context, when this execution context it's complete it's pushed on the top of the call stack, to be executed,
the log instruction it's executed, and with this, the call stack pushed it's end then a pop it's performed and the first GEC pushed at the start can continue being executed, and all of this it's being repeated cause we have another function execution (b)
After this second function execution, we have the focus again in the GEC and just the last log need to be executed witch will print 1 due in the GEC x has value 1, allocated in the first phase of memory allocation

## Shortest JS program, Window and 'this' key-word

```JS
  window
```

Window is a big object with function and variables created by js engine. As functions as variables are always available

```JS
  this

  this === window // true
```

'this' points to the window, and window it's a global object created along the GEC, in next section we will get deeper with this key-word, but now let's study window.
For browsers this global object created by JS engine at the start of the program it's called window, but JS also run in server or other devices, then node has his own and browser like chrome or mozilla when create it also can add/modify functionalities

```JS
  var x = 1;

  function a() {
    var y = 10;
    console.log(x);
  }

  console.log(x)
  console.log(window.x)
  console.log(this.x)
  console.log(a) // fails ReferenceError -> a it's not in the global space
```

In this case, x it's in the global space, but y not.
Items defined at top level are in the global space, but items defined inside functions not.
As x it's in the global space if we inspect the window object we will see inside a variable x with val 1
Then we can access to the variable with window. prefix, but also with no specifying context, due by default it's window
this.x also works cause by default in browsers this and window are the same.

How this is linked with GEC? Global Space includes variables and functions from GEC

## Undefined VS NotDefined

```JS
  console.log(a) // undefined

  console.log(a === undefined) // true
  var a = 7;
  console.log(7) // 7
  console.log(a === undefined) // false
  a = undefined
  console.log(a) // undefined
  
  console.log(x) // fails ReferenceError
```

Memory allocation will be done for variable a in the GS will exist an entry a: undefined
Will fails, no allocation memory done for x
Undefined it's a special key-value during the variable it's not assigned (can be all time-code) like a placeholder
Also we can assign undefined to a variable to remove his value, but it's a really bad practice

## The Scope Chain

```JS
  function a() {
    var c = 10;
    c();
    console.log(b); // 10

    function c() {
      console.log(b); // 10
      console.log(c); // 10
    }
  }

  var b = 10;
  a();
  console.log(c); // fails ReferenceError
```

Every variable has a scope, and this scope is the place where the variable is defined.

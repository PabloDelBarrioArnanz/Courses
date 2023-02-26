
// 1. Console use
console.log('Hello, how are you?')
console.warn('Hello, how are you?')
console.error('Hello, how are you?')

console.log([1,2,3,4,5])
console.log({a:1, b:2})
console.table({a:1, b:2})

//console.clear()

console.time('Hello1')
console.timeEnd('Hello1')



// 2. Variables
//var, let, const

//Var can be reassigned
var varName = 'John Doe'
console.log(varName)
varName = 'Overwrite name'
console.log(varName)

//Init var
var noValue
console.log(noValue)
noValue = 'Now yes!'
console.log(noValue)

// letters, numbers, _, $
// Can not start with number

//Let can be reassigned and also non initialized
let letName = 'John Doe'
console.log(letName)
letName = 'Overwrite name'
console.log(letName)

//const can not be reassigned neither declared uninitialized
//const object his vars can be reassigned
const constName = 'John Doe'
console.log(constName)
//constName = 'override'

const person = {
    name: 'John',
    age: 25
}
console.log(person)
person.name = 'Pablo'
console.log(person)

//A const array can be modificated but no reassigned
const numbers = [1,2,3]
numbers.push(4)
console.log(numbers)

//numbers = [1,2]



/*
3. Data Types in JS
Primitive data types
    Stored directly in the location the variables accesses
    Stored on the stack
    String, Number, Boolean, Null, Undefined, Symbols ES6
    All are undefined by default till assigned
Reference data types
    Accessed by reference
    Object that are stored on the heap
    A pointer to a location in memory
    Array, Object literals, Function, Dates, Anything else
Dynamically typed language
    Types are associated with values not variables
    The same variable can hold multiples types
    TypeScript make JS static type like Java
*/

//Primitive data types
const name = 'John Doe'
const age = 35
const hasKinds = false
const car = null
let something;
const sym = Symbol()

console.log(typeof name)
console.log(typeof age)
console.log(typeof hasKinds)
console.log(typeof car) //object
console.log(typeof something)
console.log(typeof sym)

//Reference data types
const hobbies = ['movies', 'music']
const address = {
    city: 'Boston',
    state: 'MA'
}
const myFunction = () => ""

console.log(typeof hobbies)
console.log(typeof address)
console.log(typeof myFunction)

//Type conversion
let val;
//To string
val = String(5+4)
console.log(val) //9
console.log(typeof val)

val = String(5) + String(4)
console.log(val) //54
console.log(typeof val)

val = String(new Date())
console.log(val)
console.log(typeof val)

val = String([1,2,3,4])
console.log(val)
console.log(typeof val)

val = String(address)
console.log(address)
console.log(typeof val)

//another way
val = (5).toString()
console.log(val)
console.log(typeof val)

//To number
val = Number('5')
console.log(typeof val)

val = Number(true) //true -> 1 false -> 0
console.log(val)

val = Number(null)
console.log(val) //0

val = Number(undefined)
console.log(val) //NaN

val = Number('Pablo')
console.log(val) //NaN

val = parseInt('102.2')
console.log(val)

val = parseFloat('102.24353454')
console.log(val.toFixed(2))

let val1 = 5
let val2 = 6
let sum = val1 + val2

console.log(sum)
console.log(typeof sum)

val1 = 5
val2 = '6'
sum = val1 + val2

console.log(sum)
console.log(typeof sum)

//Numbers and Maths operations
const num1 = 100
const num2 = 50

val = num1 + num2
console.log(val)
val = num1 - num2
console.log(val)
val = num1 * num2
console.log(val)
val = num1 / num2
console.log(val)
val = num1 % num2
console.log(val)

//Math objects
val = Math.PI
console.log(val)
val = Math.E
console.log(val)
console.log(Math.round(2.8))
console.log(Math.ceil(2.8))
console.log(Math.floor(2.8))
console.log(Math.sqrt(2.8))
console.log(Math.abs(-2.8))
console.log(Math.pow(2, 2))
console.log(Math.min(2, 6, 3, 43, 1, -2))
console.log(Math.max(2, 6))
console.log(Math.random())
console.log(Math.random()) * 20 //value between 0 and 19
console.log(Math.random()) * 20 + 1 //value between 1 and 20
console.log(Math.floor(Math.random() * 20 + 1)) //value between 1 and 20

//String and concatenations
const firstName = 'Pablo'
const lastName = 'Barrio'

//Concatenation
console.log(firstName + ' ' + lastName)
console.log(firstName.concat(' ').concat(lastName))
console.log(firstName.concat(' ', lastName, '!'))

//Append
let otherName = 'Pablo'
console.log(otherName += lastName)

//String Templates interpolation
console.log(`Welcome ${firstName} ${lastName}!`)
//ES5
const htmlES5 =
    '<ul>' +
        '<li>Name: ' + firstName + '</li>' +
        '<li>LastName: ' + lastName + '</li>' +
        '<li>Age: ' + (20 + 5) + '</li>' +
        '<li>Age: ' + (age > 18 ? 'Older age' : 'Younger age') + '</li>' +
    '</ul>'
//ES6
const htmlES6 =
    `<ul>
        <li>Name: ${firstName}</li>
        <li>LastName: ${lastName}</li>
        <li>Age: ${20 + 5}</li>
        <li>Age: ${age > 18 ? 'Older age' : 'Younger age'}</li>
    </ul>`

document.body.innerHTML = htmlES5 + htmlES6

//Escaping
console.log("That's incredible")
console.log(`"That's incredible"`)
console.log(`\`That's incredible\``)

//Length
console.log('This is a tests'.length) //Not a function no ()
console.log('This is a tests'.toUpperCase())

console.log('Pablo'[2])
console.log('Pablo'.indexOf('a')) //1
console.log('Pablo'.indexOf('A')) //-1
console.log('Lalalalala'.lastIndexOf('a')) //-1
console.log('Pablo'.charAt(1)) //-1
console.log('Pablo'.charAt(50)) //""

console.log(firstName.charAt(firstName.length - 1))
console.log(firstName.slice(-1))

//substring/slice
console.log(firstName.substring(2))
console.log(firstName.substring(2,3))
console.log(firstName.slice(-2)) //from behind

//split
const text = "Hello this is a JS course"
console.log(text.split(" "))
console.log(firstName.split(" "))//Array with 1 element
console.log(firstName.split(""))

//replace
console.log(firstName.replace('a', '@'))

//includes like contains java
console.log(firstName.includes('bl'))
console.log(firstName.includes('t'))


//Arrays and methods
//Are mutable and iterable
//push, unshift, pop, shift, splice, reverse, sort are mutating

let arrayNumbers = [1, 2, 3, 4, 5]
const arrayNumbers2 = new Array(1, 2, 3, 4, 5)
const fruit = ['banana', 'orange', 'pear', 'apple']
const mix = ['apple', true, 1, {d: 1}, undefined, null, () => ""]

console.log(arrayNumbers)
console.log(arrayNumbers2)
console.log(mix)

console.log(arrayNumbers.length)
console.log(Array.isArray(arrayNumbers))

arrayNumbers.push(500) //add at the end
arrayNumbers.unshift(-500) //add at the start
const pop = arrayNumbers.pop() //take last value
const shift = arrayNumbers.shift() //take first value
console.log(arrayNumbers)
console.log(pop)
console.log(shift)

const slice = arrayNumbers.splice(1, 2,'test1', 'test2')
console.log(arrayNumbers) //at position 1 removes 2 items and add 2 items and returns deleted part
console.log(slice)

arrayNumbers = [1, 2, 3, 4, 5]
arrayNumbers.reverse()
console.log(arrayNumbers)
arrayNumbers.reverse()
console.log(arrayNumbers.concat(6, 7))

fruit.sort()
console.log(fruit)

numbers.reverse()
console.log(numbers)
numbers.sort(function(x, y) {
    return x - y
})
numbers.sort((x, y) => x - y)

console.log(numbers.find(num => num > 2)) //like findFirst not mutating
console.log(numbers.filter(num => num > 2)) //filters the array not mutating


//Object literals
const pabloPerson = {
    name: 'Pablo',
    lastName: 'Barrio',
    age: 25,
    email: 'pablo@gmail.com',
    hobbies: ['movies', 'music'],
    address: {
        city: 'Madrid',
        country: 'Spain' 
    },
    getBirthday: function() {
        return new Date().getFullYear() - this.age
    },
    getBirthday2: () => new Date().getFullYear() - this.age, //can't access age
    getBirthday3() { //Not property, it's a method
        return new Date().getFullYear() - this.age
    },
}

/*
Important
 1. arrow functions cannot use this, super and should not be used as methods
 2. Cannot be used as constructors
 3. Cannot use yield
*/

console.log(pabloPerson.name) //most used way
console.log(pabloPerson['name'])
console.log(pabloPerson.address.city)
console.log(pabloPerson['address']['city'])
console.log(pabloPerson.getBirthday())
console.log(pabloPerson.getBirthday2())
console.log(pabloPerson.getBirthday3())

const getBirthFunction = pabloPerson.getBirthday
console.log(getBirthFunction()) //Not working this.age from function not available here
//Study binding


//Date and Times
const today = new Date();
const birth = new Date('3-5-1997');
console.log(birth)
console.log(today)
console.log(today.getMonth())
console.log(today.getDay()) //week
console.log(today.getDate())
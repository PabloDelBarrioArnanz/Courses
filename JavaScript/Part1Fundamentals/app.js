
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



//3. Data Types in JS

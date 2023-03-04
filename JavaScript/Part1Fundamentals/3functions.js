

function greet() {
    console.log('Hello')
}

function greet(name) { //Overwrite the first one
    console.log(`Welcome ${name}`)
}

function defaultParamVal(name = 'Juan') { //Overwrite the first one
    console.log(`Default ${name}`)
}

greet()
greet('Pablo')

defaultParamVal('Pablo')
defaultParamVal()

//Functions Expressions
const square = function(x) {
    return x * x
}

console.log(square(2))

//Immediately invocable functions expressions

/*
(function(name) {
    console.log(`Immediately invocated ${name}`)
})('Pablo')

(() => {
    console.log('Immediately invocated lambda')
})()
*/

//Property method

const todo = {
    add: function() {
        console.log('Add todo...')
    }
}

todo.delete = function() {
    console.log('Delete todo...')
}

todo.add()
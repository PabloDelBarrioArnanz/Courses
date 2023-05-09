
/*
    Una función es un bloque de código que realiza una tarea específica cuando se llama
    Se definen con la palabra function
    Si no tiene un return específico siempre devuelven undefined
*/

function saludar() { //Definición
  console.log('Hola Miguel')
}
saludar() //Invoacción


//Parámetros permiten parametrizar el comportamiento de la función el orden de los parametros es importante
function cocinarMicroondas(plato, tiempo, potencia) {
  if (plato === '🐥' && tiempo === 1 && potencia === 5) {
    return '🍗'
  }

  if (plato === '🥚' && tiempo === 2 && potencia === 3) {
    return '🍳'
  }

  return '❌ Plato no soportado'
}

function myFunction(a, b) //a y b son parámetros
myFunction(2, 8) //a y b son argumentos


//Function expression, son funciones anonimas

const suma = function(a, b) {
    return a + b
}

//IMPORTANTE
//Hoisting se puede usar la funcion antes de ser definida, porque en compilación la pone el compilardor arriba del todo
//Las funciones expresion no están afectadas por el hoisting por lo tanto esto no funciona

suma(1, 2)
const suma = function(a, b) {
    return a + b
}

//Si fuese una funcion normal funcionaría

//ArrowFunctions
//Las funciones flecha son siempre funciones anónimas y function expressions. Esto significa que no tienen nombre y que se asignan a una variable.
const saludar = nombre => {
  console.log("Hola " + nombre)
}
 /*
    Ventajas
    Sintaxis más concisa
    Return implicito (si no abrimos llavess {})
    Mas legibles
 */

//Recursividad
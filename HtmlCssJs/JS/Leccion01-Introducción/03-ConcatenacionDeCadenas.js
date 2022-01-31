var nombre = 'Juan'
var apellido = 'Perez'

var nombreCompleto = nombre + " " + apellido

console.log(nombreCompleto)


var numero = 210
var concatenacion1 = nombre + numero
var concatenacion2 = nombre + 1 + 2
var concatenacion3 = 1 + 2 + nombre
var concatenacion4 = nombre + (1 + 2)

console.log(concatenacion1) //Juan210, el número lo convierte a string
console.log(typeof concatenacion1) //string

console.log(concatenacion3) //3Juan, primero suma matematicamente 1 y 2 y después lo convierte todo a string
console.log(typeof concatenacion3) //string

console.log(concatenacion4) //Juan3, primero suma matematicamente 1 y 2 y después lo convierte todo a string
console.log(typeof concatenacion4) //string

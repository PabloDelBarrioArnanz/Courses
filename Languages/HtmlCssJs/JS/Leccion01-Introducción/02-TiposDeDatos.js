
//Tipo de dato string
var nombre = "Pablo" //Se pueden usar comillas dobles " o simples '
console.log(nombre)

//Tipo de dato numerico
var numero = 100
console.log(numero)

//Tipo de dato Objeto
var objeto = {
    nombre: 'Pablo',
    apellido: 'del Barrio',
    telefono: 8347582
}
console.log(objeto)

nombre = "Pablo otra vez"
console.log(nombre)

//Ahora nombre es número
nombre = 10
console.log(typeof nombre)

//Tipo de dato Boolean true/false
var boolean = true
console.log(boolean)

//Tipo de dato función
var myFunction = function name(params) {
    
}
console.log(myFunction)

//Tipo de dato simbolo
var symbol = Symbol("Mi Simbolo")
console.log(symbol) 

//Tipo clase tambien es un función
var classPersona = class Persona {
    constructor(nombre, apellido) {
        this.nombre = nombre
        this.apellido = apellido
    }

    sayHello() {
        return "Hola, soy " + this.nombre + " " + this.apellido
    }
}

//console.log(new Persona("Pablo", "del Barrio").sayHello()) no funciona porque la clase de persona no existe al haberla asignado a la variable, ahora solo existe classPersona
//console.log(new classPersona("Pablo", "del Barrio").sayHello) Así no invocas la funcion sayHello si no te refieres al objecto función
console.log(new classPersona("Pablo", "del Barrio").sayHello())

//Tipo undefined, también es valor
var x
console.log(typeof x)
console.log(x)
x = undefined
console.log(x)

//NULL ausencia de valor, null es un objeto
var y = null
console.log(typeof y)
console.log(y)

//Tipo array es un objeto
var autos = ["Audi", "Mercedes", "BMW", "Dacia"]
console.log(typeof autos) //object

var empty = "";
console.log(empty) //empty string
console.log(typeof empty) //string string
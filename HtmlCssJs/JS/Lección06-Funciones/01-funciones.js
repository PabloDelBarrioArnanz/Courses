

var funcion = function miFuncion(parametro1, parametro2, parametro3) {
    console.log(parametro1, parametro2, parametro3)
}

function miFuncion(parametro1, parametro2, parametro3) {
    console.log(parametro1, parametro2, parametro3)
}

console.log(funcion)
console.log(funcion("1", "2", "3"))

console.log(miFuncion("2", "3", "1")) //Se refiere a la segunda

function miFuncion2(parametro1) {
    return parametro1 + ' añadida mas info'
}

function miFuncion2(parametro1, parametro2) {
    return parametro1 + ' añadida mas info ' + parametro2
}

function miFuncion2(parametro1, parametro2, parametro3) {
    return parametro1 + ' añadida mas info ' + parametro2 + ' añadida mucha mas info'
}

console.log(miFuncion2('Hola', 'Adios')) //Siempre llama a la ultima definida no ejecuta la que mas coincidan los parametros como en java
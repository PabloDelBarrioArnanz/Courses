//Los arrays son colecciones de datos
//Se declaran como
const myArray1 = [] 
const myArray2 = [1, 2, 'a'] 
const myArray3 = [1, [1, [1]]]

//El acceso se hace con índice
console.log(myArray1[0]) //undefined
console.log(myArray2[0]) //1

//Modificar un valor (pese a que el array está definido como const)
myArray2[0] = 'a'
console.log(myArray2[0]) //a

//Métodos arrays
const fruits = ['manzana', 'pera', 'banana']

console.log(fruits.length) // 3

//También se puede acortar un array seteando su longitud
fruits.length = 1

//Añadir un elemento al final de un arrays con el método push
fruits.push('orange')
//Lo mismo pero al principio
fruits.unshift('orange')

//Eliminar el último elemento del array y devolverlo
fruits.pop()
//Lo mismo pero para el primero
fruits.shift()

//Concatenar 2 arrays
const numbers11 = [1, 2, 3]
const numbers12 = [4, 5]

const allNumbers12 = numbers11.concat(numbers12)

//Otra forma de concatenar arrays es propagando
const numbers21 = [1, 2, 3]
const numbers22 = [4, 5]
                  
const allNumbers21 = [...numbers21, ...numbers22]

//Los arrays se puede recorrer con bucles while, for..
let frutas = ['🍎', '🍌', '🍓']

//Bucle for of
for (let fruta of frutas) {
  console.log(fruta) // imprime el elemento en la posición i
}

//forEach
frutas.forEach(function (fruta, index, originalArray) {
    console.log(fruta)
})

frutas.forEach(fruta, index, originalArray => console.log(fruta))

//Otros métodos útiles indexOf, some, every, find, findIndex e includes.
//indexOf -> devuelve la posición en la que se encuentra un valor si no está -1
//includes -> comprueba si el array tiene un valor (también funciona en las cadenas de texto) contains de java
//some -> comprueba si algún elemento cumple la condición estilo anyMatch de java !Importante solo recorre hasta encontrar un elemento que lo cumpla
//any -> comprueba si todos los elemento cumple la condición estilo allMatch de java
//find -> devuelve el primer elemento que cumple la condición si no undefined sería un filter findFirst de java
//findIndex -> lo mismo que find pero devuelve el índice


//Ordenación de arrays
let numeros = [5, 10, 2, 25, 7]
numeros.sort()
console.log(numeros) // [10, 2, 25, 5, 7] Ordena por su valor como texto no numérico

numeros.sort(function(a, b) {
  return a - b  //Si negativo a antes que b
                //Si positivo b antes que a
                //Si 0 es que son iguales
})

console.log(numeros) // [2, 5, 7, 10, 25]

//Ordenar descendente b - a
numeros.sort((a, b) => b - a)

numeros.toSorted() //Devuelve un copia sin modificar el original
//Similar a 
const copiaNumeros = [...numeros]
copiaNumeros.sort((a, b) => a - b)


//Transformar arrays
//.filter igual que en java
//.map igual que en java
//.reduce igual que en java


//Matrices colecciones de arrays
const matriz = [
    [1, 2, 3],
    [4, 5, 6]
]
let numero = matriz[1][2]
console.log(numero) // -> 6

matriz.forEach((fila, filaIndex) => {
    fila.forEach((elemento, columnaIndex) => {
        console.log('Fila ' + filaIndex)
        console.log('Columna ' + columnaIndex)
        console.log('Elemento ', elemento)
        console.log('')
    })
})
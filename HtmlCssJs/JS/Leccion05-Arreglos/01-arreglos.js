let autos1 = new Array('BMW', 'Mercedes', 'Volvo') //Forma antigua no se recomienda

const autos2 = ['BMW', 'Mercedes', 'Volvo'] //Es mas eficiente porque mantenemos la dirección de memoria y el array puede ser modificado

console.log(autos2)


console.log(autos2[0])
console.log(autos2[1])
console.log(autos2[2])
console.log(autos2[3]) //undefined

autos2[1] = 'Audi' //Modificar 1 elemento

autos2.push('Toyota') //Añade el string al final

autos2[5] = 'Renault' //Se coloca en la posicion 5 pero la 4 se queda undefined

for (let i = 0; i < autos2.length; i++) {
    console.log(autos2[i])
}

for (let index in autos2) { //in nos da un indice para el array
    console.log(autos2[index])
}

for (let auto of autos2) { //of nos da el contenido de cada posición
    console.log(auto)
}

console.log(typeof autos2) //object
console.log(Array.isArray(autos2)) //true
console.log(autos2 instanceof Array) //true
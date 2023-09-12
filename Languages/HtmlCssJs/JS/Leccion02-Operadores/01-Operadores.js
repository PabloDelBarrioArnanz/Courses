
/*  
    Operadores
    +   Suma
    -   Resta
    *   Multiplicación
    **  Exponente
    /   Division
    %   Modulo
    ++  Incremento y PreIncremento
    --  Decremento y PreDecremento
    ==  Igualdad
    === Igualdad extricta
    !=  Distinto
    !== Distinto extricto
    >   Mayor que
    >=  Mayor o igual que
    <   Menor que
    <=  Menor o igual que
    =   Asignación
    !   Negación
    +=  Suma y asignación aplicable a otras operaciones -=, /=, *=...
    &&  AND
    ||  OR
    ? : Operador ternario a == 5 ? "Es 5" : "No es 5"  
*/

// La precedencian en operaciones es matemática, es decir de izquierda a derecha, primero paréntesis, luego multiplicaciones y divisiones, luego sumas y restas.  

let a, b

a = 5
b = ++a //Preincremento primero se aumenta y luego se asigna a b

console.log(a) //6
console.log(b) //6

a = 5
b = a++

console.log(a) //6
console.log(b) //5 Al ser postincremento, primero asigna y luego hace el incremento


a = 5
b = 5
c = "5"

console.log(a == b) //True
console.log(b == c) //True
console.log(b === c) //False

console.log((a == b) == (b == a)) //true
console.log((a == b) == b == a) //false

a = 5
b = 5
c = "5"
d = "6"

console.log(a != b) //False
console.log(a != c) //False
console.log(a != d) //True
console.log(a !== c) //True


a = 5
b = 5

console.log(a <= b) //True
console.log(a <= b--) //True, primero compara y luego postdecremento
console.log(a <= --b) //False, primero decrementa y luego compara

a = 5
b = 10
let result = ++a + b-- //(a + 1) + b, despúes b - 1 (no afecta en este computo) 

console.log(a)
console.log(b)
console.log(result)

a = 5
b = 1
result = ++a / b-- //No da infinito porque divide entre 1

console.log(a)
console.log(b)
console.log(result)


a = 5
b = 10
b /= a // => b = b / a => b = 10 / 5 => b = 2
console.log(b)

b = 15
console.log(5 >= (b/=3)) //Primero el parentesis, b/=3 => 15/3 = 5 => 5 <= 5 => true


let verdad = true

if (verdad || undefined) {
    console.log("Evaluación perezosa") //No importa si el segundo es undefined porque con 1 vale
} else {
    console.log("Nop");
}


a = 5
a == 5 ? console.log("Es 5") : console.log("No es 5")


let miNumero = "10"
let miNumeroFake = "Hola"

console.log(miNumero)
console.log(typeof miNumero)
console.log(miNumero > 5) //True autoconversion

let miNumeroReal = Number(miNumero)
let miNumeroRealFake = Number(miNumeroFake)
console.log(miNumeroReal)
console.log(typeof miNumeroReal)
console.log(miNumeroRealFake)
console.log(typeof miNumeroRealFake) //NaN pero tipo numerico


let nan = "Hola"
let num = 1

console.log(isNaN(nan)) //True
console.log(isNaN(num)) //false


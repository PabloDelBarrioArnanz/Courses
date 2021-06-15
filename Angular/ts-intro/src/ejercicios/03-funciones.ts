

function sumar(a, b) {
    return a + b;
}

const resultado1 = sumar('Ho', 'la'); //Hola
const resultado2 = sumar(1, 2); //3
const resultado3 = sumar(1, 'a'); //1a

console.log(resultado1, resultado2, resultado3)

function sumarTipado(a: number, b: number): number { //tipo de retorno puede ser inferido por el return
        return a + b;
}

const resultado4 = sumarTipado(1, 2);

const sumarFuncional = (a: number, b: number): number => a + b

const resultado5 = sumarFuncional(1, 2);


function multiplicar(numero, otroNumero, base) {
    return numero * base;
}

function multiplicar2(numero, base, otroNumero?) { //Los parametros opcionales al final
    return numero * base;
}

function multiplicar3(numero, otroNumero?, base=2) { //Los parametros con valor prefijado pueden ir detras de los opcionales
    return numero * base;
}

//const resultado6 = multiplicar(10, 3); //TS da errror pero en JS es valido y daria undefined porque base no se envia

const resultado6 = multiplicar2(10, 3); //TS da errror pero en JS es valido y daria undefined porque base no se envia
const resultado7 = multiplicar3(10, 2, 3); //sobreescribe la base

////////////////////////////////////////////////////////

interface Personaje {
    nombre: string;
    hpPoints: number;
    mostrarHpPoints: () => void; //tipo funcion que no recibe nada y devuelve void
}

function curar(persona: Personaje, hpPoints: number): void { //Toda funcion en JS si no tiene return devuelve undefined
    persona.hpPoints += hpPoints;
}

const nuevoPersonaje: Personaje = {
    nombre: 'Pablo',
    hpPoints: 2,
    mostrarHpPoints() {
        console.log('Mi vida es ' + this.hpPoints + ' puntos.')
    }
}

curar(nuevoPersonaje, 98)
nuevoPersonaje.mostrarHpPoints //sin los parentesis no se ejecuta
nuevoPersonaje.mostrarHpPoints()
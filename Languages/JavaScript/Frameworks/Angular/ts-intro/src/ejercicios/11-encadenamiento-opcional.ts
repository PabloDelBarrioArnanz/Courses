

interface Pasajero {
    nombre: string;
    hijos?: string[];
}

const pasajero1: Pasajero = {
    nombre: 'Pablo'
}

const pasajero2: Pasajero = {
    nombre: 'Julian',
    hijos: ['Pedro', 'Carlos']
}

function imprimeHijos(pasajero: Pasajero): void {
 
    //const numeroHijos = pasajero.hijos.length; //Error no se puede leer propiedades de un undefined
    const numeroHijos = pasajero.hijos?.length || 0; //Si existieran los hijos leer la longitud del array

    console.log(numeroHijos);
}

imprimeHijos(pasajero1);
imprimeHijos(pasajero2);
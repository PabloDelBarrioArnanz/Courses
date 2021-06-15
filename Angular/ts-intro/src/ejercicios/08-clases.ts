

class Heroe { //Se puede usar private, public y static por defecto son public
    alterEgo: string;
    nombreReal: string;

    constructor(alterEgo: string, nombreReal: string) {
        console.log('Se esta creando un heroe');
        this.alterEgo = alterEgo;
        this.nombreReal = nombreReal;
    }
}

class Heroe2 { 

    constructor(public alterEgo: string, public nombreReal: string) { } //Lo mismo que heroe pero mas corto
}

const ironman = new Heroe('Ironman', 'Tony Stark'); //Llama al constructor
const spiderman = new Heroe2('Spiderman', 'Peter Parker');

console.log(ironman)


//Diferencia con las interfaces
//1. La interfaz no exisite en JS la clase si
//2. Las clases son instanciables
//3. En las interfaces no se pueden definir nada mas que tipos la implementacion va en la clase


class PersonaNormal {
    constructor(public nombre: string, public direccion: string) {}
}

class Heroe3 extends PersonaNormal {

    constructor(public alterEgo: string, nombre: string, direccion: string) {
        super(nombre, direccion);
        this.alterEgo = alterEgo;
     }
}

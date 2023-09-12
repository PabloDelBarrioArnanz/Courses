
let habiliades = []; //Tipo any mejor no usarlo
let habiliadesString = ['h1', 'h2', 'h3'] //Tipo string inferido por TS
let habilidadesMix: (boolean | string | number)[] = [true, 'text', 12] //Array de tipo boolean, string o numero


//Objetos suelen ser constantes

interface Persona { //La interfaz nos permite definir objetos con propiedades definidas
    nombre: string;
    hp: number;
    habilidades: string[];
    puebloNatal? : string; //Propiedad opcional
}

const persona: Persona = {
    nombre: 'Pablo',
    hp: 2,
    habilidades: ['h1', 'h2', 'h3']
}

persona.puebloNatal = 'Segovia';

console.table(persona);
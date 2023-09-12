

console.log('Hola Mundo!!!!');

/*
    ===== Código de TypeScript =====
*/

let nombreImplicitString = 'Pablo';
let nombreExplicitString: string = 'Pablo';

let hp: number | string = 95; //Se pueden definir varios tipos (mejor no hacerlo)
let estaVivo: boolean | number = 1;

const constNombreImplicitString = 'Pablo'; //Tipe pablo al ser constante

nombreExplicitString = 'German';
//nombreExplicitString = 123; error la variable es string
hp = 'FULL';
estaVivo = true;

//Todo el codigo TS al desplegarse en un navegador se traduce a Js, los navegadores no soportan TS
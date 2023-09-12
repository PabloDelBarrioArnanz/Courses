
//El switch usa validación estricta ===

let numero = 5
let numeroTexto = 'Valor desconocido'

switch (numero) {
    case 1:
        numeroTexto = 'Es el valor 1'
        break;
    case 2:
        numeroTexto = 'Es el valor 2'
        break;
    case 3:
        numeroTexto = 'Es el valor 3'
        break;
    case 4: case 5: case 6: //Mismo valor
        numeroTexto = 'Es el valor 4, 5 o 6'
        break;
    default:
        numeroTexto = 'Es otro valor'
}

console.log(numeroTexto)
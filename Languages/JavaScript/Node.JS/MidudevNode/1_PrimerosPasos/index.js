console.log("Hola Mundo")

// console.log(window) Esto retorna un error, el entorno de ejecución es diferente al del navegador por lo que no existe el contexto global window

console.log(globalThis) // globalThis también existe en el navegador, es una variable global, accesible desde cualquier parte del código
// En Node.js, globalThis hace referencia al objeto global llamado "global", en cambio en el navegador hace referencia al objeto "window"
// Es más para que funciona console.log es porque es una variable que está definido en globalThis

globalThis.console.log("Hola desde globalThis")

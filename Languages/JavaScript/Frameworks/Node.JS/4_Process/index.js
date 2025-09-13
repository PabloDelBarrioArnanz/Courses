
// Process es un objeto global que provee información y control sobre el proceso actual de Node.js
console.log(process.version) // Versión de Node.js
console.log(process.argv) // Argumentos con los que se ha ejecutado el proceso de Node.js

// Escuchar eventos del proceso
process.on('beforeExit', (code) => {
  console.log('El proceso va a terminar con código', code)
})

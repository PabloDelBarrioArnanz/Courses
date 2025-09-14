const fs = require('node:fs/promises'); // Este ';' si es necesario porque si no forma parte de la IIFE

// Esto no funciona en top-level si usamos la importación CommonJS pero si en ES Modules
// para usarlo en CommonJS habría que envolverlo en una función async

// No hace falta IIFE (Immediately Invoked Function Expression), pero queda mejor

// En este caso se ejecuta asíncrono pero el resultado es síncrono
// porque no continua la siguiente línea hasta que no termina la lectura del archivo
// Aunque los recursos están liberados no como el síncrono real
(async () => {
  console.log('Leyendo el primer archivo...\n')
  const content = await fs.readFile('./stuff/file.txt', 'utf-8')
  console.log(content, '\n')


  console.log('Haciendo otras cosas mientras se lee el archivo...\n')

  console.log('Leyendo el archivo grande...')
  const bigContent = await fs.readFile('./stuff/bigFile.txt', 'utf-8')
  console.log(bigContent, '\n')
})()

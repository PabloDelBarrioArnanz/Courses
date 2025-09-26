// Node tiene un sistema para convertir callbacks en promesas para los módulos que no lo soportan nativamente
const fs = require('node:fs')
const { promisify } = require('node:util')

const readFile = promisify(fs.readFile)

console.log('Leyendo el primer archivo...\n')
readFile('./stuff/file.txt', 'utf-8')
  .then(text => {
    console.log(text, '\n')
  })

console.log('Haciendo otras cosas mientras se lee el archivo...\n')

console.log('Leyendo el archivo grande...')
readFile('./stuff/bigFile.txt', 'utf-8')
  .then(text => {
    console.log(text, '\n')
  })

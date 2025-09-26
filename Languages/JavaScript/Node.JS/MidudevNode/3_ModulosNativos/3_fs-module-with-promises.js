// Igual que el modulo fs pero basado en promesas
// Varios módulos de Node.JS tienen versiones basadas en promesas
const fs = require('node:fs/promises')

// También se puede usar async await
console.log('Leyendo el primer archivo...\n')
fs.readFile('./stuff/file.txt', 'utf-8')
  .then(text => {
    console.log(text, '\n')
  })

console.log('Haciendo otras cosas mientras se lee el archivo...\n')

console.log('Leyendo el archivo grande...')
fs.readFile('./stuff/bigFile.txt', 'utf-8')
  .then(text => {
    console.log(text, '\n')
  })

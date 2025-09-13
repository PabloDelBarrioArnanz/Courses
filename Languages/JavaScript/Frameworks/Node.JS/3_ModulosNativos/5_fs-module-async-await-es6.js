import { readFile } from 'node:fs/promises'

// Esto no funciona en top-level si usamos la importación CommonJS pero si en ES Modules
// para usarlo en CommonJS habría que envolverlo en una función async

console.log('Leyendo el primer archivo...\n')
const content = await readFile('./stuff/file.txt', 'utf-8')
console.log(content, '\n')


console.log('Haciendo otras cosas mientras se lee el archivo...\n')

console.log('Leyendo el archivo grande...')
const bigContent = await readFile('./stuff/bigFile.txt', 'utf-8')
console.log(bigContent, '\n')

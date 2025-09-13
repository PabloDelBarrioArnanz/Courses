// Módulo FileSystem - Proporciona una API para interactuar con el sistema de archivos
const fs = require('node:fs')

// Async vs Sync
// MonoHilo y basado en eventos
// Si hacemos las operaciones de forma síncrona, bloqueamos el hilo principal
// Si lo hacemos de forma asíncrona haremos uso del sistema de eventos
const stats = fs.statSync('./stuff/file.txt') // síncrono

console.log(
  'Información del fichero ./stuff/file.txt:\n', 
  'Es un fichero?', stats.isFile(), '\n',
  'Es un directorio?', stats.isDirectory(), '\n',
  'Es un enlace simbólico?', stats.isSymbolicLink, '\n',
  'Tamaño:', stats.size
)

// Esto hace una lectura síncrona y retorna un buffer si añadimos el encoding lo convierte a string
// Durante las lecturas el hilo está bloqueado y no puede realizar otras operaciones
console.log('Leyendo el primer archivo...')
const fileContent = fs.readFileSync('./stuff/file.txt', 'utf-8')
console.log(fileContent)

console.log('Leyendo el archivo grande...')
const bigFileContent = fs.readFileSync('./stuff/bigFile.txt', 'utf-8')
console.log(bigFileContent)

// Versión asíncrona
// No hay garantías de cuándo se ejecutará el callback ni el orden en que se ejecutarán
console.log('Leyendo el primer archivo...')
fs.readFile('./stuff/file.txt', 'utf-8', (error, text) => {
  console.log(text)
})

console.log('Haciendo otras cosas mientras se lee el archivo...')

console.log('Leyendo el archivo grande...')
fs.readFile('./stuff/bigFile.txt', 'utf-8', (error, text) => {
  console.log(text)
})
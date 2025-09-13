const path = require('node:path')

console.log(path.sep) // Separador de rutas según el sistema operativo
// No debemos crear las rutas con / porque en Windows no funcionaría, es mejor usar path.join porque adapta las rutas al sistema operativo
path.join('usr', 'local', 'bin', 'node') // 'usr/local/bin/node' en Linux y 'usr\local\bin\node' en Windows

console.log(path.basename('/usr/local/bin/file.txt')) // file.txt
console.log(path.basename('/usr/local/bin/file.txt', '.txt')) // file (sin extensión)

console.log(path.extname('/usr/local/bin/file.txt')) // .txt

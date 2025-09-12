
// Los módulos nativos son aquellos que vienen integrados con Node.js
// Y como Node.JS es un entorno de ejecución cuenta con muchos módulos para trabajar con el sistema operativo, archivos, rutas, internet, etc

// const os = require('os') Desde Node 18 se recomienda usar el prefijo node
const os = require('node:os') 

console.log('Nombre del sistema operativo:', os.platform())
console.log('Arquitectura:', os.arch())
console.log('CPUs:', os.cpus())
console.log('Memoria libre:', os.freemem() / 1024 / 1024 / 1024, 'GB')
console.log('Memoria total:', os.totalmem() / 1024 / 1024 / 1024, 'GB')
console.log('Uptime:', os.uptime() / 60 / 60, 'horas')

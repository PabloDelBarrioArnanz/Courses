// Importación de módulos (CLÁSICA) CommonJS no recomendada a dia de hoy
// const mySum = require('./sum') // Importa el módulo sum.js que está en la misma carpeta con el nombre que queramos
const { sum } = require('./sum') // Importar el objecto y desestructurarlo, no requiere la extensión

// console.log(mySum(1, 2))
console.log(sum(1, 2))
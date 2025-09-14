
// .js -> Por defecto usa CommonJS
// .cjs -> CommonJS
// .mjs -> ES Modules
// Desde package.json se puede definir el tipo de módulo sin tener que usar la extensión .mjs

// ES Modules es la forma moderna de trabajar con módulos en JavaScript
// Lo que se exporta default se importa sin llaves
import multiply, { sum, subtract } from './sum.mjs' // Requerido usar la extensión del fichero en react y otros frameworks no lo vemos porque lo vite/webpack se encarga 

console.log(sum(2, 3))
console.log(subtract(5, 2))
console.log(multiply(5, 2))

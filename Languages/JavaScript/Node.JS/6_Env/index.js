
// Dentro del contexto de Node.js, las variables de entorno se pueden acceder a través del objeto global `process.env`.
// Entonces si lo ejecutamos así PORT=1234 node index.js la variable process.env.PORT tendrá el valor 1234
// Pero también funciona con variables del sistema operativo

const port = process.env.PORT ?? 3000
const user = process.env.USER ?? 'defaultUser'

console.log(`Defined Port: ${port}`)
console.log(`Defined User: ${user}`)

// DotEnv por ejemplo es una dependencia de node que nos permite tener un fichero .env donde podemos definir estas variables y cargarlas automáticamente en nuestro proyecto.
// Los frameworks de Node.js como Express.js o Nest.js suelen incluirlo

// Cuidado porque process.env tiene todas las variables del sistema
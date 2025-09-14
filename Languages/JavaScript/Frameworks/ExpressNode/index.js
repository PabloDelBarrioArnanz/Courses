
const http = require('node:http')

const desiredPort = globalThis.process.env.PORT ?? 3000

const server = http.createServer((request, response) => {
  console.log(`Request received: ${request.url}`)

  if (request.url === '/') {
    response.statusCode = 200
    response.setHeader('Content-Type', 'text/plain')
    response.end('Welcome!')
  } else {
    response.statusCode = 404
    response.end()
  }
})

server.listen(desiredPort, () => {
  console.log(`Server listening on port http://localhost:${server.address().port}`)
})

// Nada mas acceder a la url desde un navegador automáticamente se hacen 2 request 1 a / y otra a /favicon.ico
// Desde un curl u otras herramientas no harían la segunda request /favicon.ico
// Para que programa se reinicie con cada cambio podemos arrancar al app con el modo watch: node --watch index.js


const http = require('node:http')
const fs = require('node:fs')

const desiredPort = globalThis.process.env.PORT ?? 3000

const server = http.createServer((request, response) => {
  console.log(`Request received: ${request.url}`)

  if (request.url === '/') {
    response.statusCode = 200
    response.setHeader('Content-Type', 'text/plain')
    response.end('Welcome!')
  } else if (request.url === '/image') {
    fs.readFile('./image.png', (err, data) => {
      if (err) {
        response.statusCode = 500
        response.setHeader('Content-Type', 'text/plain')
        response.end('Error reading image!')
      } else {
        response.statusCode = 200
        response.setHeader('Content-Type', 'image/png')
        response.end(data)
      }
    })
  } else if (request.url === '/myPost') {
    let body = ''

    // El body se lee de manera asíncrona entonces lo componemos con los chucks totales en función del tamaño
    request.on('data', chunk => {
      body += chunk.toString()
    })
    
    // La función de leer los datos es asíncrona entonces para cuando termine nos suscribimos al evento
    request.on('end', () => {
      response.statusCode = 200
      response.setHeader('Content-Type', 'application/json')
      response.end(body)
    })
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

const express = require('express')

const app = express()
app.disable('x-powered-by') // desactiva el header de respuesta called x-powered-by

// En Node existen middlewares que interceptan las peticiones que configuremos y se pueden usar para validar request, modificarlas...
// Se puede añadir un path para que solo aplique a esos
app.use((request, response, next) => {
  const now = new Date()
  console.log(`Request received: ${request.url}`)
  const result = next() // Si no usamos el next se bloquerá la petición
  console.log(`Request processed: ${request.url} with status ${response.statusCode} in ${new Date() - now}ms`)
  return result
})

// Todos los endpoint definidos se evalúan 1 por 1 para saber si la request les pertenece, y se hace de arriba a abajo
app.get('/', (req, res) => {
  // .json para json
  // . Send para texto
  res.status(200).json(
    {
      name: 'Pablo',
      age: 28
    })
})

// Este endpoint acepta cualquier petición y se usa para devolver los 404, es importante que esté el último definido
app.use((request, response) => {
  response.status(404).send('Endpoint Not found in this server')
})

const PORT = process.env.port ?? 3000

app.listen(PORT, () => {
  console.log(`App running at port http://localhost:${PORT}`)
})

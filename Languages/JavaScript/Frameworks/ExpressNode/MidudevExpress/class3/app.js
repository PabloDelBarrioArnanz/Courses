import express from 'express'
import { moviesRouter } from './routes/movie.js'
import { corsMiddleware } from './middlewares/cors.js'
import logger from './middlewares/logger.js' // No se puede leer un json directamente con módulos

/*
  También se puede hacer así (crear un requiere cuando se está usando módulos)
  import { createRequire } from 'node:module'
  const require = createRequire(import.meta.url)
  const movies = require('./movies.json')
*/

const app = express()
app.disable('x-powered-by')
app.use(corsMiddleware)
app.use(express.json())
app.use(logger)

app.use('/movies', moviesRouter)

app.use((request, response) => {
  response.status(404).send('Service not found')
})

const PORT = process.env.port ?? 3000

app.listen(PORT, () => console.log('App running at port http://localhost:3000'))

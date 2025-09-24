import express from 'express'
import { corsMiddleware } from './middlewares/cors.js'
import logger from './middlewares/logger.js'
import { createMovieRoutes } from './routes/movie-injection.js' // No se puede leer un json directamente con módulos
import { MovieModel } from './model/mysql/movie.js'

const app = express()
app.disable('x-powered-by')
app.use(corsMiddleware)
app.use(express.json())
app.use(logger)

app.use('/movies', createMovieRoutes({ movieModel: MovieModel }))

app.use((request, response) => {
  response.status(404).send('Service not found')
})

const PORT = process.env.port ?? 3000

app.listen(PORT, () => console.log('App running at port http://localhost:3000'))

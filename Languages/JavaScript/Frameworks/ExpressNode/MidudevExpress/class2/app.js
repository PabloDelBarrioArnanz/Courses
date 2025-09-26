const express = require('express')
const cors = require('cors')
/*
  response.setHeader('Access-Control-Allow-Origin', '*')
  // Podemos leer el origin así request.header('origin') solo lo envía el navegador si no está en el mismo origen
  // Los métodos PUT/PATCH/DELETE tienen PRE-flight y requieren OPTION
  response.setHeader('Access-Control-Allow-Methods', 'GET, POST, PATCH, DELETE')
*/

const crypto = require('node:crypto')

const movies = require('./movies.json')
const { validateMovie, validatePartialMovie } = require('./movieValidator')

const app = express()
app.use(cors())
app.use(express.json())
app.disable('x-powered-by')

app.use((request, response, next) => {
  const now = new Date()
  console.log(`Request received: ${request.url}`)
  next()
  console.log(`Request processed: ${request.url} with status ${response.statusCode} in ${new Date() - now}ms`)
})

app.get('/movies', (request, response) => {
  const { genre } = request.query
  if (genre) {
    const genreMovies = movies.filter(movie =>
      movie.genre.some(movieGenre => movieGenre.toLowerCase() === genre.toLowerCase())
    )
    response.status(200).json(genreMovies)
  } else response.status(200).json(movies)
})

app.get('/movie/:id', (request, response) => {
  const { id } = request.params
  const movie = movies.find(movie => movie.id === id)
  if (movie) response.status(200).json(movie)
  else response.status(404).send(`Movie with id ${id} not found`)
})

app.post('/movie', async (request, response) => {
  const result = await validateMovie(request.body)

  if (result.success) {
    const newMovie = {
      id: crypto.randomUUID(),
      ...result.data
    }
    movies.push(newMovie)
    response.status(201).json(newMovie)
  } else response.status(400).json({ error: JSON.parse(result.error.message) })
})

app.patch('/movie/:id', async (request, response) => {
  const result = await validatePartialMovie(request.body)

  if (result.success) {
    const { id } = request.params
    const movieIndex = movies.findIndex(movie => movie.id === id)
    if (movieIndex !== -1) {
      const updatedMovie = {
        ...movies[movieIndex],
        ...result.data
      }
      movies[movieIndex] = updatedMovie
      response.status(200).json(updatedMovie)
    } else response.status(404).send(`Movie with id ${id} not found to update`)
  } else response.status(400).json({ error: JSON.parse(result.error.message) })
})

app.use((request, response) => {
  response.status(404).send('Service not found')
})

const PORT = process.env.port ?? 3000

app.listen(PORT, () => console.log('App running at port http://localhost:3000'))

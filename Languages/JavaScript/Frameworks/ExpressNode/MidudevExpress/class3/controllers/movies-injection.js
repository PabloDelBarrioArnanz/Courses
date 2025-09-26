// import { MovieModel } from '../model/mysql/movie.js'
import { validateMovie, validatePartialMovie } from '../schemas/movieValidator.js'

export class MoviesController {

  constructor ({ movieModel }) {
    this.movieModel = movieModel
  }

  async getAll (request, response) {
    const movies = await this.movieModel.getAll({ genre: request.query.genre })
    response.json(movies)
  }

  async getById (request, response) {
    const movie = await this.movieModel.getById(request.params.id)
    if (movie) response.status(200).json(movie)
    else response.status(404).send(`Movie with id ${id} not found`)
  }

  async create (request, response) {
    const result = await validateMovie(request.body)
    if (result.success) {
      const newMovie = await this.movieModel.create(result.data)
      response.status(201).json(newMovie)
    } else response.status(400).json({ error: JSON.parse(result.error.message) })
  }

  async update (request, response) {
    const result = await validatePartialMovie(request.body)

    if (result.success) {
      const movie = this.movieModel.update(request.params.id, result.data)
      if (movie) response.status(200).json(movie)
      else response.status(404).send(`Movie with id ${id} not found to update`)
    } else response.status(400).json({ error: JSON.parse(result.error.message) })
  }
}

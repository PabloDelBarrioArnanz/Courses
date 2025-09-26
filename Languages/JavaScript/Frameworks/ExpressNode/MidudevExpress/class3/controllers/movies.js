import { MovieModel } from '../model/mysql/movie.js'
import { validateMovie, validatePartialMovie } from '../schemas/movieValidator.js'

export class MoviesController {

  static async getAll (request, response) {
    const movies = await MovieModel.getAll({ genre: request.query.genre })
    response.json(movies)
  }

  static async getById (request, response) {
    const movie = await MovieModel.getById(request.params.id)
    if (movie) response.status(200).json(movie)
    else response.status(404).send(`Movie with id ${id} not found`)
  }

  static async create (request, response) {
    const result = await validateMovie(request.body)
    if (result.success) {
      const newMovie = await MovieModel.create(result.data)
      response.status(201).json(newMovie)
    } else response.status(400).json({ error: JSON.parse(result.error.message) })
  }

  static async update (request, response) {
    const result = await validatePartialMovie(request.body)

    if (result.success) {
      const movie = MovieModel.update(request.params.id, result.data)
      if (movie) response.status(200).json(movie)
      else response.status(404).send(`Movie with id ${id} not found to update`)
    } else response.status(400).json({ error: JSON.parse(result.error.message) })
  }
}

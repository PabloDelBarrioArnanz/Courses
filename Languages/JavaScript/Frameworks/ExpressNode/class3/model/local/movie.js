import movies from './movies.json' with { type: 'json' }
import { randomUUID } from 'node:crypto'

export class MovieModel {

  static async getAll ({ genre }) {
    if (genre) {
      return movies.filter(movie =>
        movie.genre.some(movieGenre => movieGenre.toLowerCase() === genre.toLowerCase())
      )
    } else return movies
  }

  static async getById (id) {
    return movies.find(movie => movie.id === id)
  }

  static async create (movie) {
    const newMovie = {
      id: randomUUID(),
      ...movie
    }
    movies.push(newMovie)
  }

  static async update (id, movie) {
    const movieIndex = movies.findIndex(movie => movie.id === id)
    if (movieIndex !== -1) {
      const updatedMovie = {
        ...movies[movieIndex],
        ...movie
      }
      movies[movieIndex] = updatedMovie
      return updatedMovie
    }
  }
}
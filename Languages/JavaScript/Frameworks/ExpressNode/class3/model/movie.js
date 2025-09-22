import movies from '../movies.json'
import { randomUUID } from 'node:crypto'

export class MovieModel {

  async static getAll ({ genre }) {
    if (genre) {
      return movies.filter(movie =>
        movie.genre.some(movieGenre => movieGenre.toLowerCase() === genre.toLowerCase())
      )
    } else return movies
  }

  async static getById (id) {
    return movies.find(movie => movie.id === id)
  }

  async static create (movie) {
    const newMovie = {
      id: randomUUID(),
      ...movie
    }
    movies.push(newMovie)
  }

  async static update (id, movie) {
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
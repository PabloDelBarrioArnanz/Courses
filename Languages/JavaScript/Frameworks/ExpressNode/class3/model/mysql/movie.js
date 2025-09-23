import mysql from 'mysql2/promise'

const config = {
  host: 'localhost',
  user: 'movieuser',
  password: 'moviepass',
  database: 'moviesdb'
}

const connection = await mysql.createConnection(config)

export class MovieModel {

  static async getAll ({ genre }) {
    return (await connection.execute(
      `
          SELECT MOVIES.ID, TITLE, YEAR, DIRECTOR, POSTER, RATE, GENRE.NAME AS 'GENRE'
          FROM MOVIES
                   INNER JOIN MOVIE_GENRE ON MOVIES.ID = MOVIE_GENRE.MOVIE_ID
                   INNER JOIN GENRE ON GENRE.ID = MOVIE_GENRE.GENRE_ID;
      `
    ))[0].reduce((movies, movie) => {
      const existingMovie = movies.find(m => m.id === movie.ID)
      if (existingMovie) {
        existingMovie.genre = `${existingMovie.genre}, ${movie.GENRE}`
        return movies
      }
      return [...movies, {
        id: movie.ID,
        title: movie.TITLE,
        year: movie.YEAR,
        director: movie.DIRECTOR,
        poster: movie.POSTER,
        rate: movie.RATE,
        genre: movie.GENRE
      }]
    }, [])
  }

  static async getById (id) {

  }

  static async create (movie) {

  }

  static async update (id, movie) {

  }
}
import zod from 'zod'

const schema = zod.object({
  title: zod.string({
    required_error: 'Title is required',
    invalid_type_error: 'Title must be a string'
  }).min(1),
  genre: zod.array(zod.enum(['Action', 'Crime', 'Drama']), 'Genre must contain at least 1 movie').min(1),
  year: zod.number().int().min(1800).max(2100),
  director: zod.string().min(1),
  duration: zod.number().int().min(5),
  rate: zod.number().min(0).max(10).optional().default(-1),
  poster: zod.url('Title poster must be a valid url')
})

export function validateMovie(body) {
  const { title, genre, year, director, duration, rate, poster } = body
  const movie = {
    title: title,
    genre: genre,
    year: year,
    director: director,
    duration: duration,
    rate: rate,
    poster: poster
  }

  // return schema.parse(movie) returns error
  // return schema.safeParse(movie)
  return schema.safeParseAsync(movie)
}

export function validatePartialMovie(body) {
  return schema.partial().safeParseAsync(body) // partial hace todas las propiedades opcionales para validar solo lo que se quiere actualizar
}

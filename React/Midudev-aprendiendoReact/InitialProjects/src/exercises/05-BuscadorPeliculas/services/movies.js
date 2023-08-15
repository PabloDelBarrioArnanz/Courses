
import withResults from '../mocks/with-results.json'
import noResults from '../mocks/no-results.json'

export async function searchMovies({ search }) {
    if (search === '') return null

    try {
        const response = await fetch(`https://www.omdbapi.com/?apikey=4287ad07&s=${search}`)
        const movies = await response.json()
        return movies?.Search?.map(movie => ({
            id: movie.imdbID,
            title: movie.Title,
            year: movie.Year,
            poster: movie.Poster,
        }))
    } catch (e) {
        throw new Error('Error searching movies')
    }
}
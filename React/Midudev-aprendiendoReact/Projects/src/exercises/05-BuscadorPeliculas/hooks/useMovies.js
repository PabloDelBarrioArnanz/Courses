
import { useRef, useState, useMemo, useCallback } from 'react'
import { searchMovies } from '../services/movies'

//let previousSearch = '' funcionaría parecido al useRef pero esta mal porque los modulos con singleton entonce la variable seria compartida

export function useMovies({ search, sort }) {
    const [movies, setMovies] = useState([])
    const previousSearch = useRef(search) //funciona por persiste entre renders

    //const getMovies = useMemo(() => { //De esta forma no se recrea esta funcion en cada render
    const getMovies = useCallback(async ({ search }) => { //useCallback es igual que useMemo pero para funciones la sitaxis es mas cómoda
        if (search === previousSearch.current) return 
        previousSearch.current = search
        const newMovies = await searchMovies({ search })
        setMovies(newMovies)
    }, [])

    /*
    const sortedMovies = sort ? //Se reejecuta cada vez que actualizamos al busqueda aunque no busquemos
        [...movies].sort((a, b) => a.title.localeCompare(b.title)) :
        movies
    */

    //useMemo guarda un resultado entre renders de esta manera no se reejecuta a no ser que queramos 
    const sortedMovies = useMemo(() => {
        return sort ?
            [...movies].sort((a, b) => a.title.localeCompare(b.title)) :
            movies
    }, [sort, movies])

    return { movies: sortedMovies, getMovies }
}

import React, { useCallback, useEffect, useRef, useState } from "react"

import { Movies } from './components/Movies'
import { useMovies } from './hooks/useMovies'

import debounce  from "just-debounce-it"

//useRef hook permite almacenar un valor que se mantiene entre renders, no actualiza componente cuando se actualiza

import './Buscador.css'

function useSearch() {
    const [search, updateSearch] = useState('')
    const [error, setError] = useState(null)
    const isFirstInput = useRef(true)

    useEffect(() => {
        if (isFirstInput.current) {
            isFirstInput.current = search === ''
            return
        }
        if (search === '') {
            setError('Impossible to search an empty movie name')
        } else {
            setError(null)
        }
    }, [search])

    return { search, updateSearch, error }
}

function MovieFinder() {
    const [sort, setSort] = useState(false)
    const { search, updateSearch, error } = useSearch()
    const { movies, getMovies } = useMovies({ search, sort })

    const handleSubmit = event => {
        event.preventDefault()
        getMovies({ search })
    }

    const debouncedGetMovies = useCallback(
        debounce(search => getMovies({ search }), 300),
     [])

    const handleChange = event => {
        const newSearch = event.target.value
        updateSearch(newSearch) //debounce si actualizamos cada vez podemos crear una race condition entre las diferentes llamadas
        debouncedGetMovies(newSearch) //la idea es usar un debounce hay librerias ya hechas como just
    }

    const handleSort = () => {
        setSort(prev => !prev)
    }

    //Formulario contralado, se recarga el componente cada vez que el usuario actualiza
    //Por lo general usar no controlado
    return (
        <div className='page'>
            <header>
                <h1>Movie Finder</h1>
                <form className='form' onSubmit={handleSubmit}>
                    <input onChange={handleChange} value={search} name='query' placeholder='Avengers, Star Wars, The Matrix...' />
                    <input type="checkbox" onClick={handleSort} />
                    <button type='submit'>Search</button>
                </form>
                { error && <p style={{ color: 'red' }}>{error}</p>}
            </header>
            
            <main>
                {
                    <Movies movies={movies} />
                }
            </main>
        </div>
    )
}

export default MovieFinder
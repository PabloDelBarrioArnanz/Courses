import {useMemo, useState} from "react"
import {ListCountryUsers} from "./components/ListCountryUsers"
import {useUsers} from "./hooks/useUsers.ts"
import {Results} from "./components/Results.tsx"

/*
  React query aka TanStack Query, alternativas Redux Query, SWR..
  Forma de manejar estado global asíncrono, funciona con varios frameworks incluso en js vanila
  Uno de sus típicos usuos es para hacer peticiones a apis, pero no es para eso concretamente
  Se parece mucho a redux, tambien tiene el contexto y el estado pero no es ideal para mucha lógica y middlewares, está pensado para alta dependecia de apis
  npm install @tanstack/react-query -E
  npm install @tanstack/react-query-devtools -E
*/

export const App = () => {

	const {
		isLoading,
		isError,
		users,
		refetch,
		fetchNextPage,
		hasNextPage
	} = useUsers()

	const [colorCells, setColorCells] = useState<boolean>(true)
	const [countryFilter, setCountryFilter] = useState<string | null>(null)
	const [sortedByCountry, setSortedByCountry] = useState<boolean>(false)

	const handleColorCellsClick = () => {
		setColorCells(prev => !prev)
	}

	const handleSortedByCountry = () => {
		setSortedByCountry(prev => !prev)
	}

	const handleCountryFilterChange = (newSearch: string) => {
		setCountryFilter(newSearch)
	}

	const reset = async () => {
		await refetch()
	}

	const filteredCountryUsers = useMemo(() => {
		if (countryFilter === null || countryFilter === '') return users
		return users.filter(user => user.country.toLowerCase().includes(countryFilter.toLowerCase()))
	}, [users, countryFilter])

	const filteredAndSortedCountryUsers = useMemo(() => {
		if (!sortedByCountry) return filteredCountryUsers
		return filteredCountryUsers.toSorted((user1, user2) => user1.country.localeCompare(user2.country)) //toSorted crea un nuevo array y no sobreescribe
	}, [filteredCountryUsers, sortedByCountry])

	return (
		<>
			<header>
				<h1>Prueba técnica</h1>
				<div>
					<button onClick={handleColorCellsClick}>Colorear celdas</button>
					<button
						onClick={handleSortedByCountry}>{sortedByCountry ? "No ordenar por pais" : "Ordenar por país"}</button>
					<button onClick={reset}>Resetear estado</button>
					<input placeholder="Filtrar por país"
						   onChange={event => handleCountryFilterChange(event.target.value)}></input>
				</div>
			</header>
			<main>
				{filteredAndSortedCountryUsers.length > 0 && !isError &&
					<ListCountryUsers users={filteredAndSortedCountryUsers} colorCells={colorCells} removeUser={() => {
					}}/>
				}

				{isLoading && <p>Cargando...</p>}
				{!isLoading && isError && <p>Se ha producido un error...</p>}
				{!isLoading && !isError && filteredAndSortedCountryUsers.length < 1 && <p>Usuarios no encontraods</p>}

				<Results/>

				{!isLoading && !isError && hasNextPage &&
					// <button onClick={() => setCurrentPage(prev => prev + 1)}>Cargar más resultados</button>}
					<button onClick={() => {
						void fetchNextPage() // void solo para indicar que no queremos que devuelva nada 
					}}>Cargar más resultados</button>}
				{!hasNextPage && <p>No hay mas resultados</p>}
			</main>
		</>
	)
}

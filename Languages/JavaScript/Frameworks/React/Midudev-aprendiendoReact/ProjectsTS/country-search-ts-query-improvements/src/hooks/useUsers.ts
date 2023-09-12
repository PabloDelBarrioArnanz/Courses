import {type CountryUserPage} from "../types"
//import {useQuery} from "@tanstack/react-query"
import {useInfiniteQuery} from "@tanstack/react-query"
import {fetchUsers} from "../services/user.ts"

// estos valores que devuelve reactQuery reemplazan a nuestros estados
//const {isLoading, isError, data: usersPage = {nextPage: 0, users: []}, refetch} = useQuery<CountryUserPage>(
export const useUsers = () => {
	const {
		isLoading,
		isError,
		data, // objecto con pagesParams y pages -> donde está nuestro objeto por cada página
		refetch,
		fetchNextPage,
		hasNextPage
	} = useInfiniteQuery<CountryUserPage>(
		["usersPage"], // <- id key de la información
		//async () => await fetchUsers(1), // <- como obtener los resultados
		fetchUsers, // <- como obtener los resultados
		{
			getNextPageParam: lastPage => lastPage.nextPage, // <- información para sacar la siguiente pagina, solo para infiniteQuery
			refetchOnWindowFocus: false // al cambiar de pestaña o ventana se repiten todas las llamadas
			// Permite retry, retryDelay, definir tiempo de cache...
		},
	)
	
	return {
		isLoading,
		isError,
		users: data?.pages?.flatMap(page => page.users) ?? [],
		refetch,
		fetchNextPage,
		hasNextPage
	}
}
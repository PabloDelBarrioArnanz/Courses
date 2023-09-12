import {useUsers} from "../hooks/useUsers.ts"

// Cualquier objeto puede tener acceso a los usuarios sin rellamadas, estado global
export const Results = () => {
	const { users } = useUsers();
	return (
		<h3>Resultados {users.length}</h3>
	)
}
import {CountryUserPage} from "../types"

export const fetchUsers = async ({pageParam = 1}: { pageParam?: number }) => {
	return await fetch(`https://randomuser.me/api/?results=10&seed=midudev&page=${pageParam}`)
		.then(resp => {
			if (!resp.ok) throw new Error("Error obteniendo los usuarios") // Para comprobar que la petición es correcta se hace aquí y no en el catch, cath solo para problemas en los que no se complete la llamda pero un 400, 401 se trata aquí
			else return resp.json()
		})
		.then(apiResponse => ({
				nextPage: pageParam && pageParam > 3 ? undefined : apiResponse.info.page + 1, // Fake fin para probar el que no hay mas // si es undefined hasNext = false
				users: apiResponse.results.map(user => ({
					id: user.login.uuid,
					photo: user.picture.thumbnail,
					name: user.name.first,
					surname: user.name.last,
					country: user.location.country
				}))
			} as CountryUserPage)
		)
}
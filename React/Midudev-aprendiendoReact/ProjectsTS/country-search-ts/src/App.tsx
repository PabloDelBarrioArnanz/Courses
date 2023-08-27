import { useEffect, useMemo, useRef, useState } from "react"
import { ListCountryUsers } from "./components/ListCountryUsers"
import { type ApiCountryUserResponse, type CountryUser as CountryUserType } from "./types"

export const App = () => {
  const [countryUsers, setCountryUsers] = useState<CountryUserType[]>([])
  const [colorCells, setColorCells] = useState<boolean>(true)
  const [countryFilter, setCountryFilter] = useState<string | null>(null)
  const [sortedByCountry, setSortedByCountry] = useState<boolean>(false)

  // guarda un valor entre renders
  const originalUsers = useRef<CountryUserType[]>([])

  useEffect(() => {
    fetch('https://randomuser.me/api/?results=100')
      .then(resp => resp.json())
      .then((apiResponse: ApiCountryUserResponse) => apiResponse.results)
      .then(results => results.map(user => {
        return  {
          id: user.login.uuid,
          photo: user.picture.thumbnail,
          name: user.name.first,
          surname: user.name.last,
          country: user.location.country
        }
      }))
      .then(users => {
        setCountryUsers(users)
        originalUsers.current = users
      })
      .catch(err => console.log(err))
  }, [])

  const handleColorCellsClick = () => {
    setColorCells(prev => !prev)
  }

  const handleSortedByCountry = () => {
    setSortedByCountry(prev => !prev)
  }

  const handleCountryFilterChange = (newSearch: string) => {
    setCountryFilter(newSearch)
  }

  const reset = () => {
    setCountryUsers(originalUsers.current)
  }

  const filteredCountryUsers = useMemo(() => {
    if (countryFilter === null || countryFilter === '') return countryUsers
    return countryUsers.filter(user => user.country.toLowerCase().includes(countryFilter.toLowerCase()))
  }, [countryUsers, countryFilter])

  const filteredAndSortedCountryUsers = useMemo(() => {
    if (!sortedByCountry) return filteredCountryUsers
    return filteredCountryUsers.toSorted((user1, user2) => user1.country.localeCompare(user2.country)) //toSorted crea un nuevo array y no sobreescribe
  }, [filteredCountryUsers, sortedByCountry])

  const handleRemoveUser = (id: string) => {
    const newUsers = filteredAndSortedCountryUsers.filter(user => user.id  !== id)
    setCountryUsers(newUsers)
  }
  
  return (
    <>
      <header>
        <h1>Prueba técnica</h1>
        <div>
          <button onClick={handleColorCellsClick}>Colorear celdas</button>
          <button onClick={handleSortedByCountry}>{sortedByCountry ? "No ordenar por pais" : "Ordenar por país" }</button>
          <button onClick={reset}>Resetear estado</button>
          <input placeholder="Filtrar por país" onChange={event => handleCountryFilterChange(event.target.value)}></input>
        </div>
      </header>
      <main>
        <ListCountryUsers users={filteredAndSortedCountryUsers} colorCells={colorCells} removeUser={handleRemoveUser}/>
      </main>
    </>
  )
}

import React, { createContext, useState } from "react"

//Pensado para estados pequueños o que cambien poco

//1. Creacion del context esto para "importarlo" <=> useContext(FiltersContext)
export const FiltersContext = createContext()

/*2. Contenido del contexto
Envuelve todo lo que seleccionemos 
<FiltersProvider>
    <App />
</FiltersProvider>
*/
export function FiltersProvider({ children }) {
    const [filters, setFilters] = useState({
        category: 'all',
        minPrice: 0
    })
    return (
        <FiltersContext.Provider value={{
            filters, setFilters
        }}>
        { children }
        </FiltersContext.Provider>
    )
}
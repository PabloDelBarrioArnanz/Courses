import { useContext } from "react"

import { FiltersContext } from "../context/filters.jsx"

export function useFilter() {
    const { filters, setFilters } = useContext(FiltersContext)

    const filterProducts = (products) => products.filter(product =>
        product.price >= filters.minPrice && (filters.category === 'all' || filters.category === product.category))

    return { filterProducts, filters, setFilters }
}

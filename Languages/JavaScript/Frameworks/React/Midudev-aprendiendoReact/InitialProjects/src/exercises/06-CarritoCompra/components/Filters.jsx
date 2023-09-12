
import { useState, useId } from 'react'
import { useFilter } from '../hooks/useFilter.js'
import './Filters.css'

export function Filters() {
    //const [minPrice, setMinPrice] = useState(0) //No es buena idea es otra fuente de la verdad
    const { filters, setFilters } = useFilter()
    const minPriceFilterId = useId() //Genera un id que es el orden de renderización de la app así no se repite en multiples sitios
    const categoryFilterId = useId() //No usar como key index en un array map

    const handleMinPriceChange = event => {
        const newMinPrice = event.target.value
        setFilters(prev => ({
            ...prev,
            minPrice: newMinPrice
        }))
    }

    const handleCategoryChange = event => 
        setFilters(prev => ({
            ...prev,
            category: event.target.value
        }))

    return (
        <section className='filters'>
            <div>
                <label htmlFor={minPriceFilterId}>Price</label>
                <input type='range' id={minPriceFilterId} value={filters.minPrice} min='0' max='1000' onChange={handleMinPriceChange}/>
                <span>${filters.minPrice}</span>
            </div>
            <div>
                <label htmlFor={categoryFilterId}>Category</label>
                <select id={categoryFilterId} onChange={handleCategoryChange}>
                    <option value="all">Todas</option>
                    <option value="laptops">Portátiles</option>
                    <option value="smartphones">Móviles</option>
                </select>
            </div>
        </section>
    )
}
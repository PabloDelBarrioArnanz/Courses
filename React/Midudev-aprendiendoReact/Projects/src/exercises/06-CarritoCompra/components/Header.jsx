
import { CartIcon } from "./Icons"

import { Filters } from './Filters.jsx'

export function Header() {
    return (
        <header>
            <h1>Shopping Cart <CartIcon /></h1>
            <Filters />
        </header>
    )
}
import React, { useContext, useState } from "react"

import { Products } from './components/Products.jsx'
import { Header } from './components/Header.jsx'
import { Footer } from './components/Footer.jsx'
import { useFilter } from './hooks/useFilter.js'
import { Cart } from "./components/Cart.jsx"
import { CartProvider } from "./context/cart.jsx"

import { products as initialProducts } from "./mocks/products.json"

import "./App.css"


function App() {
    const [products] = useState(initialProducts)
    const { filterProducts } = useFilter()

    return (
        <CartProvider>
            <Header /> { /* No es buena practica pasar propiedades hacia abajo mucho niveles */}
            <Products products={filterProducts(products)}  />
            <Cart />
            <Footer />
        </CartProvider>
    )
}

export default App
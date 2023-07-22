import React, { createContext, useState } from "react"

export const CartContext = createContext()

export function CartProvider ({children}) {
    const [cart, setCart] = useState([])

    const addToCart = product => 
        setCart(prev => {
            const index = prev.findIndex(productCart => productCart.id === product.id)
            if (index >= 0) {
                const oldProduct = prev.splice(index, 1)[0]
                oldProduct.quantity += 1
                return [...prev, oldProduct]
            }
            return [...prev, {...product, quantity: 1 }]
        })

    const clearCart = () => setCart([])

    const removeFromCart = product => 
        setCart(prev => {
            const index = prev.findIndex(productCart => productCart.id === product.id)
            if (index >= 0) {
                const oldProduct = prev.splice(index, 1)[0]
                if (oldProduct.quantity > 1) {
                    oldProduct.quantity -= 1
                    return [...prev, oldProduct]
                }
                return [...prev]
            }
            return prev
        })

    return (
        <CartContext.Provider value={{
            cart,
            addToCart,
            clearCart,
            removeFromCart
        }}>
        { children }
        </CartContext.Provider>
    )
}
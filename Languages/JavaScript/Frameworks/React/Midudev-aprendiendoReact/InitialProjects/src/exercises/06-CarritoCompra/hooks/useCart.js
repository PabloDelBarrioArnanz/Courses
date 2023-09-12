import { useContext } from "react"
import { CartContext } from "../context/cart.jsx"


export const useCart = () => {
    const { addToCart, cart, clearCart, removeFromCart } = useContext(CartContext)

    return { addToCart, cart, clearCart, removeFromCart }
}
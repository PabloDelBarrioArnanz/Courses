import React, { createContext, useReducer } from "react"
import { initialState, cartReducer, CART_ACTIONS } from '../reducers/reducer.js'

export const CartContext = createContext()

/*
    Con el reducer nos ahorramos el estado y toda la lógica de actualización del estado está fuera del estado
    Por lo que se prueba mas facil
*/
function useCartReducer () {
    const [state, dispatch] = useReducer(cartReducer, initialState)

    const addToCart = product => dispatch({
        type: CART_ACTIONS.ADD_TO_CART,
        payload: product
    })

    const removeFromCart = product => dispatch({
        type: CART_ACTIONS.REMOVE_FROM_CART,
        payload: product
    })

    const clearCart = () => dispatch({
        type: CART_ACTIONS.CLEAR_CART
    })

    return { state, addToCart, removeFromCart, clearCart }
}

export function CartProvider ({children}) {
    //Para evitar tener muchas funciones y seteos del estado se puede usar un reducer y el useReducer
    /*
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
    */
    const { state, addToCart, removeFromCart, clearCart } = useCartReducer()

    return (
        <CartContext.Provider value={{
            cart: state,
            addToCart,
            clearCart,
            removeFromCart
        }}>
        { children }
        </CartContext.Provider>
    )
}
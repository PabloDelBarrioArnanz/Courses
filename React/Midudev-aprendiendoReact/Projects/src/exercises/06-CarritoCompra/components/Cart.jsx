
import { useId } from "react"
import { useCart } from "../hooks/useCart"

import { CartIcon, ClearCartIcon, RemoveFromCartIcon } from "./Icons"
import './Cart.css'


export function Cart () {
    const  cartCheckBoxId = useId()
    const { cart, addToCart, removeFromCart, clearCart } = useCart()

    const getTotalPrice = () => cart.reduce((acc, cartProduct) => acc + (cartProduct.quantity * cartProduct.price), 0)

    return (
        <>
            <label className="cart-button" htmlFor={cartCheckBoxId}>
                <CartIcon />
            </label>
            <input id={cartCheckBoxId} type='checkbox' hidden />

            <aside className='cart'>
                <ul>
                    {
                        cart.map(product => (
                            <li key={product.id}>
                                <img src={product.thumbnail} alt={product.title}></img>
                                <div>
                                    <strong>{product.title}</strong>
                                </div>
                                <footer>
                                    <button onClick={() => removeFromCart(product)}>
                                        -
                                    </button>
                                    <small>Qty: {product.quantity}</small>
                                    <button onClick={() => addToCart(product)}>
                                        +
                                    </button>
                                </footer>
                            </li>
                        ))
                    }
                </ul>
                { 
                    cart.length > 0 ? (
                        <>
                            <strong>Total price: ${getTotalPrice()}</strong>
                            <br/>
                            <button onClick={clearCart}>
                                <ClearCartIcon />
                            </button> 
                        </>) :
                        <p>Cesta vacía</p>
                }
            </aside>
        </>
    )
}
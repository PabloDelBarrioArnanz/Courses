
import { useId } from "react"
import { useCart } from "../hooks/useCart"

import { CartIcon, ClearCartIcon, RemoveFromCartIcon } from "./Icons"
import './Cart.css'


function CartItem ({ id, title, thumbnail, quantity, addToCart, removeFromCart}) {
    return (
        <li key={id}>
            <img src={thumbnail} alt={title}></img>
            <div>
                <strong>{title}</strong>
            </div>
            <footer>
                <button onClick={removeFromCart}>
                    -
                </button>
                <small>Qty: {quantity}</small>
                <button onClick={addToCart}>
                    +
                </button>
            </footer>
        </li>
    )
}

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
                            <CartItem key={product.id} 
                                addToCart={() => addToCart(product)}
                                removeFromCart={() => removeFromCart(product)}
                                {...product}/>
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
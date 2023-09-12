
import React from "react"

import { useCart } from "../hooks/useCart"

import './Products.css'

import { AddToCartIcon, RemoveFromCartIcon } from "./Icons"

export function Products({ products }) {
    const { cart, addToCart, removeFromCart } = useCart()
    return (
        <main className="products">
            <ul>
                {   
                    products.map(product =>  {
                            const isProductInCart = cart.some(cartProduct => cartProduct.id === product.id)
                            return <li key={product.id}>
                                <img
                                    src={product.thumbnail}
                                    alt={product.title}
                                />
                                <div>
                                    <strong>{ product.title }</strong> - ${product.price}
                                </div>
                                <div>
                                    <button onClick={() => addToCart(product)}> <AddToCartIcon/> </button>
                                    { 
                                        isProductInCart &&
                                        <button onClick={() => removeFromCart(product)}> <RemoveFromCartIcon/> </button>
                                    }
                                </div>
                            </li>
                        })
                }
            </ul>
        </main>
    )
}
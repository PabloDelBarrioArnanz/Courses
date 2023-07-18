import React, { useState } from "react"

import CounterDisplay from './CounterDisplay'

export default function Counter({ initValue }) {

    const [count, setCount] = useState(initValue)
    
    function add() {
        setCount(prevCount => prevCount + 1)
    }
    
    function subtract() {
        setCount(prevCount => prevCount - 1)
    }
    
    return (
        <div className="counter">
            <button className="counter--minus" onClick={subtract}>–</button>
            <CounterDisplay count={count}/>
            <button className="counter--plus" onClick={add}>+</button>
        </div>
    )
}

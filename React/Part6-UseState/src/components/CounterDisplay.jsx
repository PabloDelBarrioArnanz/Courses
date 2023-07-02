import React from "react"


export default function CounterDisplay({ count }) {
    
    return (
        <div className="counter--count">
            <h1>{count}</h1>
        </div>
    )
}

import React, { useState } from "react"
import boxes from "../../stuff/boxes"

export default function Box({ darkMode }) {
    
    const style = {
        backgroundColor: darkMode ? "#222222" : "#cccccc" 
    }

    const [squares, setSquares] = useState(boxes)

    function getStyle(isOn) {
        return {
            ...style,
            display: isOn ? "block" : "none" 
        }
    }

    const squareElements = squares.map(square => (
        <div style={getStyle(square.on)} className="box" key={square.id}></div>
    ))
    return (
        <main>
            {squareElements}
        </main>
    )
}

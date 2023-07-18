import React, { useState } from 'react'

export default function TwitterFollowingCard({name, user}) {
    
    const [followingState, setFollowingState] = useState("Seguir")

    function handleClick() {
        setFollowingState(prev => {
            if (prev === "Seguir")
                return "Siguiendo"
            else
                return "Seguir"
        })
    }

    function handleMouseOver() {
        setFollowingState(prev => prev === "Siguiendo" ? "Dejar de seguir" : prev)
    }

    function handleMouseLeave() {
        setFollowingState(prev => prev === "Seguir" ? prev : "Siguiendo")
    }

    return (
        <span>
            <h2>{name}</h2>
            <h3>{user}</h3>
            <button onClick={handleClick} onMouseOver={handleMouseOver} onMouseLeave={handleMouseLeave}>{followingState}</button>
        </span>
    )
}
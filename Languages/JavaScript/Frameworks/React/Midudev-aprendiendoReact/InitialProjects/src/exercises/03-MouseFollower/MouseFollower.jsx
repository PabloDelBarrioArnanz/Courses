import React, { useState, useEffect } from 'react'

import "./MouseFollower.css"

export default function MouseFollower() {
    
    const [position, setPosition] = useState({
        x: 0,
        y: 0
    })

    const [enabled, setEnabled] = useState(true)

    useEffect(() => {

        const copyMovements = event => {
            const { clientX, clientY } = event
            setPosition({ x: clientX, y: clientY })
        }

        if (enabled)
            window.addEventListener("mousemove", event => copyMovements(event))
        
        return () => window.removeEventListener("mousemove", event => copyMovements(event))
    }, [enabled])

    useEffect(() => {
        document.body.classList.toggle('no-cursor', enabled)
    
        return () => {
          document.body.classList.remove('no-cursor')
        }
      }, [enabled])

    return (
        <main className="page">
            <div style={{
                position: 'absolute',
                backgroundColor: 'rgba(0, 0, 0, 0.5)',
                border: '1px solid #fff',
                borderRadius: '50%',
                opacity: 0.8,
                pointerEvents: 'none',
                left: -25,
                top: -25,
                width: 50,
                height: 50,
                transform: `translate(${position.x}px, ${position.y}px)`
            }}/>
            <button onClick={() => setEnabled(prev => !prev)}>
                {enabled ? 'Desactivar' : 'Activar'} seguir puntero
            </button>
        </main>
    )
}
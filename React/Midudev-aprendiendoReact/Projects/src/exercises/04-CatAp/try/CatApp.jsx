import React, { useState, useEffect } from 'react'

export default function CatApp() {
    
    const [catFact, setNewFact] = useState()

    const getCatData = () => fetch("https://catfact.ninja/fact")
        .then(resp => resp.json())
        .then(resp => setNewFact(prev => ({
            ...prev,
            data: resp.fact
        })))

    useEffect(() => { getCatData() }, [])

    const getDataMessage = () => catFact?.data.split(' ').slice(0, 3).join('%20')

    return (
        <main>
            <h1>App de gatitos</h1>
            <button onClick={getCatData}>Get new fact</button>
            <section>
            { catFact &&
                <>
                    <h1>{catFact.data}</h1>
                    <img src={`https://cataas.com/cat/says/${getDataMessage()}`} />
                </>
            }
            </section>
        </main>
    )
}
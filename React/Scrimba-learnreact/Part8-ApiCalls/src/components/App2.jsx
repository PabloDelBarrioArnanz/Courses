import React, { useState, useEffect } from "react"

export default function App() {

    const [starWarsData, setStarWarsData] = useState({})
    const [count, setCount] = useState(1)

    console.log("Component rendered...")

    useEffect(() => {
        console.log("Effect function ran")
        fetch(`https://swapi.dev/api/people/${count}`)
            .then(response => response.json())
            .then(data => setStarWarsData(data))
    }, [count]) 

    return (
        <div>
            <h2>The character number is {count}</h2>
            <button onClick={() => setCount(prevCount => prevCount + 1)}>Get Next Character</button>
            <pre>{JSON.stringify(starWarsData, null, 2)}</pre>
        </div>
    )

    /*
        When click on next character the count value it's update then component it's rendered and useEffect it's executed bcs depends on count,
        and when useEffect ends the starWarsData it's updated then React must update again the component 
    */
}

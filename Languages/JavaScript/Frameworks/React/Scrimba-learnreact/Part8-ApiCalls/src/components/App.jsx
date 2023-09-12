import React, { useState, useEffect } from "react"

export default function App() {

    const [starWarsData, setStarWarsData] = useState({})

    console.log("Component rendered...")

    /* 
        Infinite loop every render the fetch will be done and modifying a useState then the component must be re-rendered again
        fetch("https://swapi.dev/api/people/1")
            .then(response => response.json())
            .then(data => setStarWarsData(data))

        This is a side-effect react can't handle this
            - localStorage
            - API/database interactions
            - Subscriptions (web sockets)
            - Syncing 2 different internal states

        Then React provides a useEffect() to interact with external environments and sync them with React
    */

    useEffect(() => {
        console.log("Effect function ran")
        fetch("https://swapi.dev/api/people/1")
            .then(response => response.json())
            .then(data => setStarWarsData(data))
    }, []) //The second parameter it's the dependency array, it's optional but nop!
    /*
        If any dependency it's modified the useEffect function will be executed
        The flow will be render component, execute useEffect (firs time) when useEffect ends will modify the useState,
        then render again component but this time with no useEffect function bcs his dependency arrays it's not being modified 
     */
    return (
        <div>
            
            <pre>{JSON.stringify(starWarsData, null, 2)}</pre>
        </div>
    )
}


import { useState, useEffect } from "react"
import { getRandomFact } from './facts'

export const useCatFact = () => {
    const [fact, setFact] = useState()
    
    const refreshFact = () => { 
        getRandomFact().then(setFact)
    }
    useEffect(refreshFact, [])

    return { fact, refreshFact }
}
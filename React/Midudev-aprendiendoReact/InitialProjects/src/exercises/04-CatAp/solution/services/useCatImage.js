
import { useState, useEffect } from "react"

//React necesita que la función empiece por use para saber que es un custom hook
//Lo bueno de un custom hook es que dentro de el mismo se pueden llamar a otros hooks
export function useCatImage({ fact }) {
        const [imageUrl, setImageURL] = useState()    
        
        useEffect(() => {
            if (!fact) return
            const firstWords = fact.split(' ', 3).join(' ')
            fetch(`https://cataas.com/cat/says/${firstWords}?size=50&json=true`)
                .then(resp => resp.json())
                .then(resp => {
                    const { url } = resp
                    setImageURL(url)
                })
        }, [fact])
    
        return { imageUrl }
    }
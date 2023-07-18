
import { useCatFact } from './services/useCatFact'
import { useCatImage } from './services/useCatImage'

import "./catApp.css"

const CAT_ENDPOINT_IMAGE_PREFIX = `https://cataas.com`

export default function CatApp() {
    const { fact, refreshFact } = useCatFact()
    const { imageUrl } = useCatImage({ fact })

    return (
        <main>
            <h1>App de gatitos</h1>
            <button onClick={refreshFact}>New fact</button>
           { fact && <p>{fact}</p> }
           { imageUrl && <img src={`${CAT_ENDPOINT_IMAGE_PREFIX}${imageUrl}`} alt={`cat image with three fact data ${fact}`} /> }
        </main>
    )
}
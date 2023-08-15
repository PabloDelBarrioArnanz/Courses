
const CAT_ENDPOINT_RANDOM_FACT = `https://catfact.ninja/fact`

export const getRandomFact = () => {
    return fetch(CAT_ENDPOINT_RANDOM_FACT)
            .then(resp => resp.json())
            .then(resp => resp.fact)
    }
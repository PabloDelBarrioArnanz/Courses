declare global {
    interface Array<T> {
        toSorted(compareFn?: (a: T, b: T) => number): T[]
    }
}

export interface CountryUser {
    id: string,
    photo: string
    name: string
    surname: string
    country: string
}

interface CountryUserResponse {
    name: {
        first: string,
        last: string,
    },
    login: {
        uuid: string
    },
    location: {
        country: string
    },
    picture: {
        thumbnail: string
    }
}

export interface ApiCountryUserResponse {
    results: CountryUserResponse[]
}
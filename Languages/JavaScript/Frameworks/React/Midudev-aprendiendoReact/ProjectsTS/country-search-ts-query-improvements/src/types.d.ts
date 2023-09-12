declare global {
    interface Array<T> {
        toSorted(compareFn?: (a: T, b: T) => number): T[]
    }
}

export interface CountryUserPage {
    users: CountryUser[]
    nextPage: number
}

export interface CountryUser {
    id: string,
    photo: string
    name: string
    surname: string
    country: string
}

import { createSlice, type PayloadAction } from '@reduxjs/toolkit'

export type UserId = string

export interface User {
    name: string
    email: string
    github: string
}

export interface UserWithId extends User {
    id: UserId
}

const defaultState: UserWithId[] = [
    {
        id: "1",
        name: "Peter Doe",
        email: "peter.doe@gmail.com",
        github: "@peter.doe"
    },
    {
        id: "2",
        name: "Midudev",
        email: "midudev@outlook.com",
        github: "midudev"
    },
    {
        id: "3",
        name: "Pablo",
        email: "pablo@gmail.com",
        github: "PabloDelBarrioArnanz"
    }
]

// IIF immediately invoked function
const initialState: UserWithId[] = (() => {
    const persistanceState = localStorage.getItem("__redux__state__")
    return persistanceState ? JSON.parse(persistanceState).users : defaultState
})()

// Cada sección del estado se llama slice y se compone de un nombre, un estado inicial y los reducers
// Los reducers son la acciones que se pueden realizar con este slice similar al useReduce de react
export const usersSlice = createSlice({
    name: 'users',
    initialState: initialState,
    reducers: {
        addNewUser: (state, action: PayloadAction<User>) => {
            const id = crypto.randomUUID()
            return [...state, { id, ...action.payload }] // se podría hacer state.push porque usa immer y permite mutar el estado
        },
        deleteUserById: (state, action: PayloadAction<UserId>) => {
            const id = action.payload
            return state.filter(user => user.id !== id)
        },
        rollbackUser: (state, action: PayloadAction<UserWithId>) => {
            if (state.some(user => user.id === action.payload.id))
                return
            else return [...state, action.payload]
        }
    }
})

// Exportamos el slice
export default usersSlice.reducer

// Exportamos la acción
export const { addNewUser, deleteUserById, rollbackUser } = usersSlice.actions
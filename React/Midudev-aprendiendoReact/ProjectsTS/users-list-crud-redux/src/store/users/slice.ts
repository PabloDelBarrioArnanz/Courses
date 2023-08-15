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

const initialState: UserWithId[] = [
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

// Cada sección del estado se llama slice y se compone de un nombre, un estado inicial y los reducers
// Los reducers son la acciones que se pueden realizar con este slice similar al useReduce de react
export const usersSlice = createSlice({
    name: 'users',
    initialState: initialState,
    reducers: {
        deleteUserById: (state, action: PayloadAction<UserId>) => {
            const id = action.payload
            return state.filter(user => user.id !== id)
        }
    }
})

// Exportamos el slice
export default usersSlice.reducer

// Exportamos la acción
export const { deleteUserById } = usersSlice.actions
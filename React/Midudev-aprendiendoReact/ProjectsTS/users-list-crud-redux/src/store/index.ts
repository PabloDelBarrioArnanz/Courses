import { configureStore } from "@reduxjs/toolkit"
import usersReducer from './users/slice'

// Confiramos la store con nuestro slice
export const store = configureStore({
    reducer: {
        users: usersReducer
    }
})

// tipos de estado y las funciones del estado
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
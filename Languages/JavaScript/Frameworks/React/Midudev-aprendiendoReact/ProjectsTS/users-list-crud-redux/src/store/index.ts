import { configureStore, type Middleware } from "@reduxjs/toolkit"
import { toast } from 'sonner'
import usersReducer, { rollbackUser } from './users/slice'

// función que se ejecuta antes y/o despues de la accion que se quiere aplicar
// afecta a todos los reducers que se configuren en la misma store y siempre interceptará las acciones
const peresistanceMiddlewareLocalStorage: Middleware = (store) => (next) => (action) => {
    // Antes de next(action) no se habría aplicado la funcionaliadad, aqui se podría para por validación de datos por ej
    next(action)
    // Después de la función ya se ha aplicado la acción y estado será otro, es ideal aqui aplicar las llamadas a la persistencia
    localStorage.setItem("__redux__state__", JSON.stringify(store.getState()))
}

const syncWithDatabase: Middleware = (store) => (next) => (action) => {
    const { type, payload } = action // example type user/deleteUserById payload '1' 
    const previousState = store.getState()
    
    next(action)
    
    // UI optimista, en la interfaz se ve que funciona pero luego devuelve error si falla con la sync por eso se aplica después de la acción
    // En proceso que no se puedan asumir estos rollbacks pues se puede aplicar antes de la acción (next(action))
    if (type === 'users/deleteUserById') {
        fetch(`https://jsonplaceholder.typicode.com/users/${payload}`, {
            method: 'DELETE'
        })
        .then(res => {
            if (res.ok) toast.success(`Usuario ${payload} borrado correctamente`)
            else throw new Error('Error al eliminar el usuario')
        })
        .catch(err => {
            toast.error(`Error eliminando el usuario ${payload}`)
            console.log(err)
            const userToRemove = previousState.users.find(user => user.id === payload)
            if (userToRemove) store.dispatch(rollbackUser(userToRemove))
        })
    }
}

// Confiramos la store con nuestro slice
export const store = configureStore({
    reducer: {
        users: usersReducer
    },
    middleware: [peresistanceMiddlewareLocalStorage, syncWithDatabase]
})

// tipos de estado y las funciones del estado
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch


/*
    Diferencia entre Context y Redux
        - Redux gestion de estados globales (usa el context internamente)
        - Context para poder acceder a una información desde cualquier sitio
*/
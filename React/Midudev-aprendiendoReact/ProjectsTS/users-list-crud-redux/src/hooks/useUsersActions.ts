import { useAppDispatch } from './store'
import { deleteUserById, type UserId } from '../store/users/slice'


// Clase para desacoplarnos del dispatch y no tenerlo que importar en todas las clases
export const useUserActions = () => {
    const dispatch = useAppDispatch()
    
    const removeUser = (id: UserId) => {
        dispatch(deleteUserById(id))
    }
    
    return { removeUser }
} 
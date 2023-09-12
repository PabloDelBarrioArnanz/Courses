import { User, addNewUser, deleteUserById, type UserId } from '../store/users/slice'
import { useAppDispatch } from './store'


// Clase para desacoplarnos del dispatch y no tenerlo que importar en todas las clases
export const useUserActions = () => {
    const dispatch = useAppDispatch()
    
    const removeUser = (id: UserId) => {
        dispatch(deleteUserById(id))
    }
    
    const addUser = ({ name, email, github }: User) => {
        dispatch(addNewUser({ name, email, github }))
    }

    return { addUser, removeUser }
} 
import type { AppDispatch, RootState } from "../store"
import { useDispatch, useSelector } from "react-redux"
import type { TypedUseSelectorHook } from "react-redux"

// De esta manera quedan tipadas todas las funciones del estado, porque en vez de usar las genéricas las bypaseamos con una propia tipada
// Así allá donde lo usemos irá tipado
// Aunque no usemos TS sería buena idea porque así no nos acoplamos a este gestor de estado y si lo cambiamos solo habría que cambiarlo aquí
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector
export const useAppDispatch: () => AppDispatch = useDispatch
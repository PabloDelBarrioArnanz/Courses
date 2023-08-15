import { useReducer } from "react"
import { AUTO_LANGUAGE } from "../constants"
import { type Action, type FromLanguage, type Language, type State } from "../types"

// Directamente usamos un reducer para la lógica

// estado inicial
export const inititalState: State = {
  fromLanguage: AUTO_LANGUAGE,
  toLanguage: 'en',
  fromText: '',
  result: '',
  loading: false
}
  
  // funcion de gestion (dispatcher)
export function reducer (state: State, action: Action) {
  const { type } = action

  if (type === 'INTERCHANGE_LANGUAGES') {
    if (state.fromLanguage === AUTO_LANGUAGE) {
      return state
    } else {
      return {
        ...state,
        loading: state.fromText !== '',
        fromText: state.result,
        result: '',
        fromLanguage: state.toLanguage,
        toLanguage: state.fromLanguage
      }
    }
  } else if (type === 'SET_FROM_LANGUAGE') {
    return {
      ...state,
      fromLanguage: action.payload,
      result: '',
      loading: state.fromText !== ''
    }
  } else if (type === 'SET_TO_LANGUAGE') {
    return {
      ...state,
      toLanguage: action.payload,
      result: '',
      loading: state.fromText !== ''
    } 
  } else if (type === 'SET_FROM_TEXT') {
    return {
      ...state,
      loading: action.payload !== '',
      fromText: action.payload
    }
  } else if (type === 'SET_RESULT') {
    return {
      ...state,
      loading: false,
      result: action.payload
    }
  } else {
    return state
  }
}

export function useStore () {
  // Un reducer siempre se crea con un una funcion de gestion de estados y un estado inicial
  const [{
    fromLanguage,
    toLanguage,
    fromText,
    result,
    loading
  }, dispatch] = useReducer(reducer, inititalState)

  const interChangeLanguages = () => dispatch({ type: 'INTERCHANGE_LANGUAGES' })
  const setFromLanguage = (payload: FromLanguage) => dispatch({ type: 'SET_FROM_LANGUAGE', payload: payload })
  const setToLanguage = (payload: Language) => dispatch({ type: 'SET_TO_LANGUAGE', payload: payload })
  const setFromText = (payload: string) => dispatch({ type: 'SET_FROM_TEXT', payload: payload })
  const setResult = (payload: string) => dispatch({ type: 'SET_RESULT', payload: payload })

  return { 
    fromLanguage,
    toLanguage,
    fromText,
    result,
    loading,
    interChangeLanguages,
    setFromLanguage,
    setToLanguage,
    setFromText,
    setResult
  }
}
import ReactDOM from 'react-dom/client'
import { App } from './App'
import './index.css'

import '@fontsource/roboto/300.css'
import '@fontsource/roboto/400.css'
import '@fontsource/roboto/500.css'
import '@fontsource/roboto/700.css'

import CssBaseline from '@mui/material/CssBaseline'
import { ThemeProvider, createTheme } from "@mui/material/styles"


/*
  REDUX vs ZUSTAND
  Los dos son gestores de estado globales

  Redux
    - Muy complejo, mucho boilerplate code
    - Tiene muchas reglas casi es un framework
    - Es opinionado (preconfigurado con muchas capas)
    - Complidado para principiantes muchos conceptos actions, reducers, actionsCreators
    - 20KB de tamaño
    + Inmutabilidad por defecto, no hace falta hacer copias
    Ideal para proyectos medianos, pero no muy grande -> Apolo, Relay
  Zustand
    - Solo funciona en react
    + Muy sencillos
    + Sin configuración
    + ~1KB de tamaño
    + No opinionado
    - No inmutable por defecto
    Uso, siempre que se pueda, excepto para apps muy grandes y para gestión de el estado global en general
  React Context
    Cosas que cambian poco o nada, que se podrían hacer con los otros pero no merece


  Caso práctico con Redux
    1. Crear la store, crear el slice con su key, initialValue, reducer -> funciones para modificar el estado
    2. Exportar el estado export const { myState } = (state) => state.keyName.value 
    3. Exportar el las acciones export const {..} = slice.acctions
    4. Configurar las store general con nuestro slice // otro fichero
    5. Envolver la app con provider y la store // en el main
    6. Importarlo con un hook e importar el dispatcher, luego para usarlo dispatch(funcN())
       Suele ir encapsulado
       const useAppSelector = useSelector //from redux
       const dispatch = useDispatch() //from redux
       ...
       const { state } = useAppSelector((state) => state) // para sacar el estado en si
       // importar las funciones reducers del estado y meterlas dentro de una funcion que ya aplique el dispatch de esta forma exportamos algo más limpio
       dispatch(myFunction())

  Caso práctico con Zustand
    1. Crear la store con las funciones de modificación del estado, directamente en un hook
    2. Importarlo y usarlo
        const { state } = useMyStateStore((state) => state.myState)
        const { func1 } = useMyStateStore((state) => state.func1)
        Se puede sacar varios atributos de una sola vez const [.., ..] = useMyStateStore(state => [.., ...]
    No hace falta envolver la app :O
*/

const darkTheme = createTheme({
  palette: {
    mode: 'dark'
  }
})

ReactDOM.createRoot(document.getElementById('root')!).render(
	<ThemeProvider theme={darkTheme}>
    <CssBaseline />
    <App />
  </ThemeProvider>
)

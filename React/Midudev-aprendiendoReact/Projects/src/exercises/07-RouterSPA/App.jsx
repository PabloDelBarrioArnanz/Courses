import { Suspense, lazy } from 'react'

import { Router } from './Router.jsx'
import { Route } from './Route.jsx'
import HomePage from "./pages/Home.jsx"
//import About from "./pages/About.jsx" //import estático
import NotFound from "./pages/NotFound.jsx"

//Esto es necesario porque si no, al cargar la pagina home tambien cargaría la página about
const About = lazy(() => import('./pages/About.jsx')) //import dinámico solo se ejecuta si lo metemos en un función y la ejecutamos, pero al usar el lazy de react automaticamente lo importará cuando lo necesite

import './App.css'


const routes = [
    {
        path: '/search/:query',
        component: ({ routeParams }) => <h1>Has buscado: {routeParams.query}</h1>
    }
]

function App () {
    //Dejamos el routes por ver las dos formas
    //Suspense es porque como tenemos partes que cargan solo en el momento requerido (import dinámico) pues se lo indicamos a React de esta forma
    return (
        <main>
            <Suspense fallback={<div>Loading...</div>}>
                <Router routes={routes} defaultComponent={NotFound}>
                    <Route path='/' component={HomePage}/>
                    <Route path='/about' component={About}/>
                </Router>
            </Suspense>
        </main>
    )
}

 export default App
import ReactDOM from 'react-dom/client'
import { Provider } from 'react-redux'
import { App } from './App.tsx'
import './index.css'
import { store } from './store'

// Este proyecto usa rome en vez de eslint para instalarlo => npm install rome -D & npx rome init
// Tremor para componentes visuales => npm add @tremor/react -E & npm add -D tailwindcss postcss autoprefixer porque usa tailwind
// Iniciar trailwind npx tailwind init -p
// Redux para la gestión del estado global => npm install @reduxjs/toolkit react-redux -E
// También la app contiene un middleware para la integración con la persistencia, es mucho más cómodo que hacer llamadas al principio y final de las funciones

//De esta manera toda la app queda envuelta en el provider
ReactDOM.createRoot(document.getElementById('root')!).render(
    <Provider store={store}>
        <App/>
    </Provider>
)

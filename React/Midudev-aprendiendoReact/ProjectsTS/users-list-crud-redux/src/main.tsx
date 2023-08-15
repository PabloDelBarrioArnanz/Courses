import { App } from './App.tsx'
import './index.css'
import ReactDOM from 'react-dom/client'
import { store } from './store'
import { Provider } from 'react-redux'

// Este proyecto usa rome en vez de eslint para instalarlo => npm install rome -D & npx rome init
// Tremor para componentes visuales => npm add @tremor/react -E & npm add -D tailwindcss postcss autoprefixer porque usa tailwind
// Iniciar trailwind npx tailwind init -p
// Redux para la gestión del estado global => npm install @reduxjs/toolkit react-redux -E

//De esta manera toda la app queda envuelta en el provider
ReactDOM.createRoot(document.getElementById('root')!).render(
    <Provider store={store}>
        <App/>
    </Provider>
)

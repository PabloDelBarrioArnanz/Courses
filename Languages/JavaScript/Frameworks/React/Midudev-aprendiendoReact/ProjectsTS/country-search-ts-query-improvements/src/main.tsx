import ReactDOM from 'react-dom/client'
import {App} from './App'
import './index.css'
import {QueryClient, QueryClientProvider} from "@tanstack/react-query"
import {ReactQueryDevtools} from "@tanstack/react-query-devtools" //utilidad de react query para revisar las peticiones y contenido

const queryClient = new QueryClient()

// Recubrimos la app con el contexto de react-query
ReactDOM.createRoot(document.getElementById('root')!).render(
	<QueryClientProvider client={queryClient}>
		<App/>
		<ReactQueryDevtools/>
	</QueryClientProvider>
)

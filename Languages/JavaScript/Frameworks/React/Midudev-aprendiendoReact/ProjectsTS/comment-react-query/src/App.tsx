import { ComponentsCreate } from './components/ComponentsCreate'
import { ComponentsList } from './components/ComponentsList'

export const App = () => {
  return (
    <main>
      <ComponentsList />
      <ComponentsCreate />
    </main>
  )
}

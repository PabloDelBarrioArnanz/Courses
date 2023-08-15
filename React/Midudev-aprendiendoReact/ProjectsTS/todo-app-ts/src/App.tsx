import { useState } from 'react'
import { Footer } from './components/Footer'
import { Header } from './components/Header'
import { Todos } from './components/Todos'
import { TODO_FILTERS } from './consts'
import { initialTodos } from './mocks/initialTodos'
import { type FilterValue, type TodoId, type TodoTitle } from './types'

const App = (): React.JSX.Element => {
  const [todos, setTodos] = useState(initialTodos)
  const [filterSelected, setFilterSelected] = useState<FilterValue>(TODO_FILTERS.ALL) // Sin <FilterValue> el estado sería de tipo string

  const handleRemove = ({ id }: TodoId): void => {
    const newTodos = todos.filter(todo => todo.id !== id)
    setTodos(newTodos)
  }

  const handleComplete = ({ id }: TodoId): void => {
    const newTodos = todos.map(todo => todo.id === id
      ? {
          ...todo,
          completed: !todo.completed
        }
      : todo
    )
    setTodos(newTodos)
  }

  const handleFilterChange = (filter: FilterValue): void => { setFilterSelected(filter) }

  const onClearComplete = (): void => {
    const newTodos = todos.filter(todo => !todo.completed)
    setTodos(newTodos)
  }

  const activeCount = todos.filter(todo => !todo.completed).length
  const completedCount = todos.length - activeCount

  const filteredTodos = todos.filter(todo => {
    if (filterSelected === TODO_FILTERS.ACTIVE) return !todo.completed
    if (filterSelected === TODO_FILTERS.COMPLETED) return todo.completed
    return todo
  })

  const handleSave = ({ title }: TodoTitle): void => {
    setTodos([...todos, { id: crypto.randomUUID(), title, completed: false }])
  }

  return (
    <div className="todoapp">
      <Header
        saveTodo={handleSave}
      />
      <Todos
        removeTodo={handleRemove}
        setCompleted={handleComplete}
        todos={filteredTodos}
      />
      <Footer
        activeCount={activeCount}
        completedCount={completedCount}
        filterSelected={filterSelected}
        handleFilterChange={handleFilterChange}
        onClearComplete={onClearComplete}
      />
    </div>
  )
}

export default App

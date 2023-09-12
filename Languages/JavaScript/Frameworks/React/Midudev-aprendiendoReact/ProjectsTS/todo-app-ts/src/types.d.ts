import { type TODO_FILTERS } from './consts'

// Type para poner un tipo a un objeto pero no se puede extender, para eso es mejor usar un interfaz
export interface Todo {
  id: string
  title: string
  completed: boolean
}

export type TodoId = Pick<Todo, 'id'> // saca un propiedad del todo te desacompla el código tambien existe el omit
export type TodoTitle = Pick<Todo, 'title'>

export type ListOfTodos = Todo[] // Array<Todo>

export type FilterValue = typeof TODO_FILTERS[keyof typeof TODO_FILTERS]

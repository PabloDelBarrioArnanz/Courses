import { useAutoAnimate } from '@formkit/auto-animate/react'
import { type ListOfTodos, type TodoId } from '../types'
import { Todo } from './Todo'

interface Props {
  todos: ListOfTodos
  // todos: Todo[]
  setCompleted: ({ id }: TodoId) => void
  removeTodo: ({ id }: TodoId) => void
}

export const Todos: React.FC<Props> = ({ todos, setCompleted, removeTodo }) => { // React Functional component tipado con las props como un genérico
  const [parent] = useAutoAnimate(/* optional config */)

  return (
        <ul className='todo-list' ref={parent}>
            {todos.map(todo => (
                <li key={todo.id}
                  className={`${todo.completed ? 'completed' : ''}`}
                >
                  <Todo
                      key={todo.id}
                      id={todo.id}
                      title={todo.title}
                      completed={todo.completed}
                      setCompleted={setCompleted}
                      removeTodo={removeTodo}
                    />
                </li>
            ))}
        </ul>
  )
}

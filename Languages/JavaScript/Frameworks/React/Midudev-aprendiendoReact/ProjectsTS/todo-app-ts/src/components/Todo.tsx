import { type TodoId } from '../types'

interface Props {
  id: string
  title: string
  completed: boolean
  setCompleted: (id: TodoId) => void
  removeTodo: ({ id }: TodoId) => void
}

export const Todo: React.FC<Props> = ({ id, title, completed, setCompleted, removeTodo }) => {
  return (
        <div className='view'>
          <input
            className='toggle'
            checked={completed}
            type='checkbox'
            onChange={ () => { setCompleted({ id }) }}
          />
          <label>{title}</label>
          <button className='destroy' onClick={() => { removeTodo({ id }) }}></button>
        </div>
  )
}

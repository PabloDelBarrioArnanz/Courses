import { FormEvent } from 'react'
import { useComments } from '../hooks/useComments'

export const ComponentsCreate = () => {
  const { saveComment, isLoadingMutatution } = useComments()
  const handleSubmitComment = (event: FormEvent) => {
    event.preventDefault()

    const data = new FormData(event.currentTarget)
    const title = data.get('title')?.toString() ?? ''
    const message = data.get('message')?.toString() ?? ''

    if (title !== '' && message !== '') saveComment({ title, message })
  }

  return (
    <div className="savecomment-side">
      <div className="comment-save">
        <h4>Introduce comentario</h4>
        <form onSubmit={(event) => handleSubmitComment(event)}>
          <input name="title" placeholder="Tittle" />
          <textarea name="message" placeholder="Message" />
          <button className={!isLoadingMutatution ? 'save-comment-button' : 'saving-comment-button'}>Enviar comentario</button>
        </form>
      </div>
    </div>
  )
}

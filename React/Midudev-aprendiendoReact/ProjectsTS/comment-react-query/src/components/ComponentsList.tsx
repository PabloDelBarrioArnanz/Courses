import { useComments } from '../hooks/useComments'

export const ComponentsList = () => {
  const { comments } = useComments()
  
  return (
    <div className="comments-side">
      {comments.reverse().map((comment) => (
        <article key={comment.id} className="comment-div">
          <h3>
            <strong>{comment.title}</strong>
          </h3>
          <p>{comment.message}</p>
        </article>
      ))}
    </div>
  )
}

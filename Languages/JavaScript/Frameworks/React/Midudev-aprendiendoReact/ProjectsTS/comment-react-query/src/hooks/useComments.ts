import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'
import { fetchCommnets, postComment } from '../services/comments'
import { Comment } from '../types'

export const useComments = () => {
  const { data } = useQuery<Comment[]>(['comments'], fetchCommnets, {
    refetchOnWindowFocus: false
  })

  const queryClient = useQueryClient()

  // Vincular la mutación con el fetch, si no no actualizaciría el fetch
  const { mutate, isLoading } = useMutation({
    mutationFn: postComment,
    // 3. Actualización optimista
    onMutate: async (newComment) => {
      await queryClient.cancelQueries(['comments'])

      // Guardado por si hay que hacer un rollback si no se pudiese guardar el comentario
      const previousComments = queryClient.getQueriesData(['comments'])

      // actualizamos los datos en el cliente, pero devolvemos los anterios para que los guarde en el contexto
      queryClient.setQueryData(['comments'], (oldData?: Comment[]) => {
        if (oldData == null) return [newComment]
        else return [...oldData, newComment]
      })
      return { previousComments } // <--- al context
    },
    onError: (error, _, context) => {
      console.error(error)
      if (context?.previousComments !== null) {
        queryClient.setQueriesData(['comments'], context.previousComments)
      }
    },
    onSettled: () => {},
    onSuccess: async (newComment) => {
      // 1. actualizar la cache de queryClient automaticamente
      /*queryClient.setQueryData(['comments'], (oldData?: Comment[]) => {
                          if (oldData == null) return [newComment]
                          else return [...oldData, newComment]
                        })*/
      // 2. Hacer un refech, podríamos no tener todo el cliente
      /*await queryClient.invalidateQueries({
                          queryKey: ['comments']
                        })
      */
    }
  })

  return {
    saveComment: mutate,
    isLoadingMutatution: isLoading,
    comments: data ?? []
  }
}

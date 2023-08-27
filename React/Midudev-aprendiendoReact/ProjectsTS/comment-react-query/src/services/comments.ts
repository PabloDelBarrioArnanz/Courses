import { ToSaveComment } from '../types'

export const fetchCommnets = async () => {
  return await fetch('https://api.jsonbin.io/v3/b/643fbe2bc0e7653a05a77535', {
    headers: {
      'X-Access-Key': '$2b$10$jOpMXFaiNgsyhru7Nt.GouBUmHStWY9IRZR7vCocenxkK.vv7tDsu'
    }
  })
    .then((apiResponse) => {
      if (!apiResponse.ok) throw new Error('Error fetching comments')
      return apiResponse.json()
    })
    .then((jsonResponse) => jsonResponse.record)
    .catch((error) => {
      console.log(error)
    })
}

// Función de mutación (cambia datos)
export const postComment = async (comment: ToSaveComment) => {
  const comments = await fetchCommnets()

  const id = crypto.randomUUID()
  const newComment = { ...comment, id }
  const commentsToSave = [...comments, newComment]

  return await fetch('https://api.jsonbin.io/v3/b/643fbe2bc0e7653a05a77535', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'X-Access-Key': '$2b$10$jOpMXFaiNgsyhru7Nt.GouBUmHStWY9IRZR7vCocenxkK.vv7tDsu'
    },
    body: JSON.stringify(commentsToSave)
  })
    .then((apiResponse) => {
      if (!apiResponse.ok) throw new Error('Error saving comment')
      return newComment
    })
    .catch((error) => console.log(error))
}

import { Router } from 'express'
import { MoviesController } from '../controllers/movies-injection.js'

export const createMovieRoutes = ({ movieModel }) => {
  const moviesRouter = Router()
  const moviesController = new MoviesController({ movieModel })

  moviesRouter.get('/injection/', moviesController.getAll)
  moviesRouter.post('/injection/', moviesController.create)

  moviesRouter.get('/injection/:id', moviesController.getById)
  moviesRouter.patch('/injection/:id', moviesController.update)

  return moviesRouter
}

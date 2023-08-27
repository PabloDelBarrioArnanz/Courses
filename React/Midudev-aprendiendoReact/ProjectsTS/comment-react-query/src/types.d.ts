export interface Comment extends ToSaveComment {
	id: string
}

export interface ToSaveComment {
  title: string
  message: string
}

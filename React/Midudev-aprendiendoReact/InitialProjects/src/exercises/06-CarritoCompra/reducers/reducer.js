
export const initialState = JSON.parse(window.localStorage.getItem('cart')) || []

export const CART_ACTIONS = {
    ADD_TO_CART: 'ADD_TO_CART',
    REMOVE_FROM_CART: 'REMOVE_FROM_CART',
    CLEAR_CART: 'CLEAR_CART'
}

const saveLocalStorage = state => window.localStorage.setItem('cart', JSON.stringify(state))

const UPDATE_STATE_BY_ACTION = {
    [CART_ACTIONS.ADD_TO_CART]: (state, action) =>  {
        const { id } = action.payload
        const index = state.findIndex(productCart => productCart.id === id)
        if (index >= 0) {
            const oldProduct = state.splice(index, 1)[0]
            oldProduct.quantity += 1
            const newState = [...state, oldProduct]
            saveLocalStorage(newState)
            return newState
        }
        const newState = [...state, {...action.payload, quantity: 1 }]
        saveLocalStorage(newState)
        return newState
    },
    [CART_ACTIONS.REMOVE_FROM_CART]: (state, action) => {
        const { id } = action.payload
        const index = state.findIndex(productCart => productCart.id === id)
        if (index >= 0) {
            const oldProduct = state.splice(index, 1)[0]
            if (oldProduct.quantity > 1) {
                oldProduct.quantity -= 1
                return [...state, oldProduct]
            }
            const newState = [...state]
            saveLocalStorage(newState)
            return newState
        }
        saveLocalStorage(newState)
        return newState
    },
    [CART_ACTIONS.CLEAR_CART]: (state, action) => {
        saveLocalStorage([])
        return []
    }
}

export const cartReducer = (state, action) => {
    const { type: actionType } = action
    const updateStateFunction = UPDATE_STATE_BY_ACTION[actionType]
    return updateStateFunction ? updateStateFunction(state, action) : state
}
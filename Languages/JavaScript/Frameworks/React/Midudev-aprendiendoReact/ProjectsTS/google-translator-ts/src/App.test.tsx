/*
    Para instalar estas dependecias de test ejecutar
    1. npm install vitest happy-dom @testing-library/react @testing-library/user-event -D
    2. En el package.json en la seccion de scripts "test": "vitest"
    3 En vite.config.ts => /// <reference types="vitest" /> y test: { environment: 'happy-dom' } dentro de defineConfig([..])
    
    happy-dom simula el dom es más rápido que levantar un navegador
*/

import { render } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { expect, test } from 'vitest'
import { App } from './App'

// e2e test making api call ideal usar un mock
test('My app works as expected', async () => {
    const user = userEvent.setup()
    const app = render(<App />)

    const textTextareFrom = app.getByPlaceholderText('Introducir texto')
    await user.type(textTextareFrom, 'Hola mundo')
    
    const result = await app.findByDisplayValue(/Hola mundo/i, {}, { timeout: 1000 })

    expect(result).toBeTruthy()
})
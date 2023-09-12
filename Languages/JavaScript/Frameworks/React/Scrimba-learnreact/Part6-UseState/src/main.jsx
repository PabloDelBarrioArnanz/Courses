import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'

import Contact from './components/Contact'
import Test from './components/Test'
import Counter from './components/Counter'
import Box from './components/Box'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Box darkMode={false}/>
    {/*<Contact />
    <Test />
    <Counter initValue={-5} />*/}
  </React.StrictMode>
)

import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'

import Form from './components/Form'
import LoginForm from './components/LoginForm'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <LoginForm />
  </React.StrictMode>,
)

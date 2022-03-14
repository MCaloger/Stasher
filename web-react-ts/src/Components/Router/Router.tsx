import React from 'react'
import App from '../../App'
import { Routes, Route } from 'react-router-dom'
import NewSecretForm from '../Secrets/NewSecretForm/NewSecretForm'
import ReadSecret from '../Secrets/ReadSecretPage/ReadSecret'

export default function Router() {
  return (
    <Routes>
        <Route path="/" element={<App />} />
        <Route path="/new" element={<NewSecretForm />} />
        <Route path="/secret/code">
            <Route path=":code" element={<ReadSecret />}></Route>
        </Route>
    </Routes>
  )
}

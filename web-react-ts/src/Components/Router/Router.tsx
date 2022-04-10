import React from 'react'
import App from '../../App'
import { Routes, Route } from 'react-router-dom'
import NewSecretForm from '../Secrets/NewSecretForm/NewSecretForm'
import ReadSecret from '../Secrets/ReadSecret/ReadSecret'
import SecretCreated from '../Secrets/SecretCreated/SecretCreated'
import SecuredCreated from '../Secured/SecuredCreated/SecuredCreated'
import ReadSecured from '../Secured/ReadSecured/ReadSecured'
import { Space } from '@mantine/core'

export default function Router() {
  return (
    <>
    
      <Routes>
          <Route path="/" element={<NewSecretForm />} />
          <Route path="/secret/code">
              <Route path=":code" element={<ReadSecret />}></Route>
          </Route>
          <Route path="/secured/code">
              <Route path=":code" element={<ReadSecured />}></Route>
          </Route>
          <Route path="/secret/created" element={ <SecretCreated /> } />
          <Route path="/secured/created" element={ <SecuredCreated /> } />
      </Routes>
    </>
  )
}

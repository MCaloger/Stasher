import React from 'react'
import { useLocation } from 'react-router-dom';

export default function SecuredCreated() {
    const location: any = useLocation();
    return (
    <>
        <div>The code for the secret is { location.state.code } </div>
        <div>Link { location.state.uri } </div>
    </>
  )
}

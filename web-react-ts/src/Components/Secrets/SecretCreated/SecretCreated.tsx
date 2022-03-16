// @ts-nocheck
import React from 'react'
import { useLocation } from 'react-router-dom';

export default function SecretCreated() {
    const location = useLocation();

    console.log(location.state)

    return (
    <>
    {console.log(location.state)}
        { location.state.errors ? <div>Error: Message or password cannot be empty.</div> 
            : 
            <> 
                <div>The code for the secret is { location.state.code } </div>
                <div>Link <a href={ location.state.uri }>{location.state.uri}</a> </div>
            </>
        }
    </>
  )
}

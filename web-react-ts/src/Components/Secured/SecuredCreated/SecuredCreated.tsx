
import React from 'react'
import { useLocation } from 'react-router-dom';
import CreateFooter from '../../CreateFooter/CreateFooter';

export default function SecuredCreated() {
    const location: any = useLocation();
    return (
    <>
        <div>The code for the secret is { location.state.code } </div>
        <div>Link <a href={ location.state.uri }>{location.state.uri}</a>  </div>
        <p>This message will expire in {location.expirationHours} hour(s) and {location.expirationMinutes} minutes.</p>

        <CreateFooter />
    </>
  )
}

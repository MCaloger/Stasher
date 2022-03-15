// @ts-nocheck
import React, { useState } from 'react'
import { readSecured } from '../../../Api/SecuredApi';
import { useParams, Outlet } from 'react-router-dom';

export default function ReadSecured() {

    const params = useParams();
    const code: string = `${params.code}`;

    const [dataRetrieved, setRetrievedData] = useState<boolean>(false);

    const [password, setPassword] = useState<string>("");

    const [message, setMessage] = useState<string>("");

    const handlePasswordChange = (event: any) => {
        setPassword(event.target.value);
    }

    const handleButtonClick =  async () => {
        setRetrievedData(true)
        const message = await readSecured(code, password);
        setMessage(message.message)
    }

    const PasswordEntry = () => {
        return (
            <div>
                <input type="password" value={password} onChange={handlePasswordChange} />
                <button onClick={ handleButtonClick }>Submit</button>
            </div>
        )
    }

    const MessageDisplay = () => {
        return (
            <div>
                <textarea readOnly value={ message }/>
            </div>
        )
    }

  return (
    <>
        { dataRetrieved ? MessageDisplay() : PasswordEntry() }
        <Outlet />
    </>
    
  )
}

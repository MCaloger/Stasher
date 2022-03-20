// @ts-nocheck
import React, { useState } from 'react'
import { readSecured } from '../../../API/SecuredAPI';
import { useParams, Outlet } from 'react-router-dom';
import { MessageContainer } from '../../MessageContainer/MessageContainer';
import CreateFooter from '../../CreateFooter/CreateFooter';

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
                <label htmlFor="passcode">Enter password for secured message:</label>
                <div>
                    <input id="passcode" name="passcode" type="password" value={password} onChange={handlePasswordChange} />
                    <button onClick={ handleButtonClick }>Submit</button>
                </div>
            </div>
        )
    }

    const MessageDisplay = () => {
        return (
            <div>
                <MessageContainer readOnly value={ message }/>
            </div>
        )
    }

  return (
    <>
        { dataRetrieved ? MessageDisplay() : PasswordEntry() }
        <CreateFooter />
        <Outlet />
    </>
    
  )
}

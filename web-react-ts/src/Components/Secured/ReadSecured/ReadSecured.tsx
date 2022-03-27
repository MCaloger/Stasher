// @ts-nocheck
import React, { useState } from 'react'
import { readSecured } from '../../../API/SecuredAPI';
import { useParams, Outlet } from 'react-router-dom';
import { MessageContainer } from '../../MessageContainer/MessageContainer';
import CreateFooter from '../../CreateFooter/CreateFooter';
import { Button, PasswordInput } from '@mantine/core';

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
                <label htmlFor="passcode"></label>
                <div>
                    <PasswordInput label="Enter password for secured message" id="passcode" name="passcode" value={password} onChange={handlePasswordChange}></PasswordInput>
                    <Button onClick={ handleButtonClick }>Submit</Button>
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

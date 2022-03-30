// @ts-nocheck
import React, { useState } from 'react'
import { readSecured } from '../../../API/SecuredAPI';
import { useParams, Outlet } from 'react-router-dom';
import { MessageContainer } from '../../MessageContainer/MessageContainer';
import CreateFooter from '../../CreateFooter/CreateFooter';
import { Button, PasswordInput, SimpleGrid, Space, Text, Textarea } from '@mantine/core';
import styled from '@emotion/styled';

const PasswordBox = styled.div`
    display: flex;
    flex-direction: row;
`

const SubmitButtonBox = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
`

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
            <PasswordBox>
                    <PasswordInput label="Enter password for secured message" id="passcode" name="passcode" value={password} onChange={handlePasswordChange}></PasswordInput>
                    <Space w="sm"></Space>
                    <SubmitButtonBox>
                        <Button onClick={ handleButtonClick }>Submit</Button>
                    </SubmitButtonBox>
                    

            </PasswordBox>
        )
    }

    const MessageDisplay = () => {
        return (
            <div>
                {/* <MessageContainer readOnly value={ message }/> */}
                <Textarea readOnly value={ message }></Textarea>
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

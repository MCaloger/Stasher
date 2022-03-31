import styled from '@emotion/styled'
import { Button, PasswordInput, Textarea, TextInput } from '@mantine/core';
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { createSecret } from '../../../API/SecretAPI';
import { createSecured } from '../../../API/SecuredAPI';
import { MessageContainer } from '../../MessageContainer/MessageContainer';

const NewSecretFormComponent = styled.form`
    display: flex;
    flex-direction: column;
    & > * {
        margin=-top: 10px;
        margin-bottom: 10px;
    }
`;

const PasswordContainer = styled.div`
    display: flex;
    flex-direction: row;
`

const ErrorBox = styled.div`
    padding: 10px;
    color: hsl(4.11,89.62%,35.43%);
    background: HSL(354.00,100.00%,90.20%);
    border: 1px solid hsl(4.11,89.62%,35.43%);
`

const CountContainer = styled.div`
    color: grey;
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
`

export default function NewSecretForm() {

    let navigate = useNavigate();

    const [error, setError] = useState("");

    // Textarea field
    const [message, setMessage] = useState("");

    const handleMessageChange = (event: any) => {
        setMessage(event.target.value);
    }

    // Password field
    enum InputTypes { PASSWORD="password", TEXT="text" };

    const [passwordInputType, setPasswordInputType] = useState(InputTypes.PASSWORD);
    const [password, setPassword] = useState("");

    const handlePasswordChange = (event: any) => {
        setPassword(event.target.value);
    }

    // Show checkbox field
    const [checked, setChecked] = useState(false);
    const handleCheckChange = (event: any) => {
        setChecked(!checked);
        if(passwordInputType === InputTypes.PASSWORD) {
            setPasswordInputType(InputTypes.TEXT)
        } else {
            setPasswordInputType(InputTypes.PASSWORD)
        }
    }

    const handleSubmitClick = (event: any) => {
        event.preventDefault();

        if(password !== '') {
            createSecured(message, password).then((data: any) => {
                if(data.errors) {
                    setError(data.errors[0])
                } else {
                    navigate("/secret/created", { state: { ...data } } )  
                }
                    
            });
        } else {
            createSecret(message).then((data: any) => {
                if(data.errors) {
                    setError(data.errors[0])
                } else {
                    navigate("/secret/created", { state: { ...data } } )
                }
            });
        }
    }
    
    return (
        <NewSecretFormComponent>

            <h1>Create a secure message:</h1>
            <div>
                <Textarea name="message" id="message" value={message} onChange={handleMessageChange} placeholder="Enter message" label="Message" maxLength={1024} required description="Maximum of 1024 characters."></Textarea>
                <CountContainer>
                    <div>{message.length} / 1024</div>
                </CountContainer>
                
                
            </div>
            <div>
                <PasswordContainer>
                    <PasswordInput id="passcode" name="passcode" value={password} onChange={handlePasswordChange} autoComplete="off" maxLength={128} label="Password" description="Maximum of 128 characters."/>
                </PasswordContainer>
                
                
            </div>
            <div>
                {error ? <ErrorBox>{error}</ErrorBox> : ""}
            </div>
            
            <Button type="submit" onClick={handleSubmitClick}>Submit</Button>
            

            
        </NewSecretFormComponent>
    )
}

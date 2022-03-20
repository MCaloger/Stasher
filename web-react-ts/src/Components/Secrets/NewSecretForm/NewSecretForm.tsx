import styled from '@emotion/styled'
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
                    navigate("/secured/created", { state: { ...data } } )  
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
                <MessageContainer name="message" id="message" value={message} onChange={handleMessageChange} placeholder="Enter secret here" maxLength={1024}></MessageContainer>
                <div>{message.length} / 1024</div>
                
            </div>
            <div>
                <label htmlFor="passcode">Password</label>
                <PasswordContainer>
                    
                    <input id="passcode" name="passcode" type={passwordInputType} value={password} onChange={handlePasswordChange} autoComplete="off" maxLength={128} />

                    <input id="show" name="show" type="checkbox" checked={checked} onChange={handleCheckChange} value="Show" autoComplete="off"/>
                    <label htmlFor="show">Show</label>
                </PasswordContainer>
                
                
            </div>
            <div>
                {error ? <ErrorBox>{error}</ErrorBox> : ""}
            </div>
            <button type="submit" onClick={handleSubmitClick}>Submit</button>
        </NewSecretFormComponent>
    )
}

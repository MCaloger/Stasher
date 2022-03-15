import styled from '@emotion/styled'
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { createSecret } from '../../../Api/SecretApi';
import { createSecured } from '../../../Api/SecuredApi';

const NewSecretFormComponent = styled.form`
    display: flex;
    flex-direction: column;
`;

const MessageComponent = styled.textarea`
    width: 100%;
`;

export default function NewSecretForm() {

    let navigate = useNavigate();

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
                navigate("/secured/created", { state: { ...data } } )
            });
        } else {
            createSecret(message).then((data: any) => {
                navigate("/secret/created", { state: { ...data } } )
            });
        }
    }
    
    return (
        <NewSecretFormComponent>
            <Link to="/">Go Back</Link>

            <h1>New Secret</h1>
            <div>
                <MessageComponent name="message" id="message" value={message} onChange={handleMessageChange} placeholder="Enter secret here" maxLength={1024}></MessageComponent>
                <div>{message.length} / 1024</div>
            </div>
            <div>
                <div>
                    <label htmlFor="passcode">Password</label>
                    <input id="passcode" name="passcode" type={passwordInputType} value={password} onChange={handlePasswordChange} autoComplete="off" />
                </div>
                
                <input id="show" name="show" type="checkbox" checked={checked} onChange={handleCheckChange} value="Show" autoComplete="off"/>
                <label htmlFor="show">Show</label>
            </div>
            <button type="submit" onClick={handleSubmitClick}>Submit</button>
        </NewSecretFormComponent>
    )
}

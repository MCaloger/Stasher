import styled from '@emotion/styled'
import { Button, Input, PasswordInput, Textarea, TextInput, NumberInput, Space, Title, Text, Divider } from '@mantine/core';
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

const FormRow = styled.div`
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

    // Hours
    const [hours, setHours] = useState(0);
    const handleHoursChange = (event: any) => { 
        setHours(event.target.value);
    }

    // Minutes
    const [minutes, setMinutes] = useState(5);
    const handleMinutesChange = (event: any) => {
        setMinutes(event.target.value);
    }

    const handleSubmitClick = (event: any) => {
        event.preventDefault();

        if(password !== '') {
            createSecured(message, password, hours, minutes).then((data: any) => {
                if(data.errors) {
                    setError(data.errors[0])
                } else {
                    navigate("/secret/created", { state: { ...data } } )  
                }
                    
            });
        } else {
            createSecret(message, hours, minutes).then((data: any) => {
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

            <Text color="indigo" size="xl">Create a secure message</Text>

            <div>
                <Textarea name="message" id="message" value={message} onChange={handleMessageChange} placeholder="Enter message" label="Message" maxLength={1024} required description="Maximum of 1024 characters."></Textarea>
                <CountContainer>
                    <div>{message.length} / 1024</div>
                </CountContainer>
                
                
            </div>
            <div>
                <FormRow>
                    <PasswordInput id="passcode" name="passcode" value={password} onChange={handlePasswordChange} autoComplete="off" maxLength={128} label="Password" description="Maximum of 128 characters."/>
                </FormRow>
                
                
            </div>
            <div>
                <FormRow>
                    <NumberInput min={0} max={23} value={hours} onChange={(val: number) => setHours(val)} name="hours" id="hours" label="Hours" stepHoldDelay={500}
        stepHoldInterval={100} description="Hours to expire."/>

                    <Space w="md" />

                    <NumberInput min={0} max={59} value={minutes} onChange={(val: number) => setMinutes(val)} name="minutes" id="minutes" label="Minutes" stepHoldDelay={500}
        stepHoldInterval={100} step={5} description="Minutes to expire."/>
                </FormRow>
                
                
            </div>
            <div>
                {error ? <ErrorBox>{error}</ErrorBox> : ""}
            </div>
            
            <Button type="submit" color="indigo" onClick={handleSubmitClick}>Submit</Button>


            <Text color="indigo" size="xl">Instructions</Text>

            <div>Enter a message into the textbox above, optionally set a password for the message, and set an hour/minute expiry for the message, and then press submit. The message can only be acessed a single time before it is deleted. If a password is set and the password is entered incorrectly, the message will also be deleted.</div>
            
        </NewSecretFormComponent>
    )
}

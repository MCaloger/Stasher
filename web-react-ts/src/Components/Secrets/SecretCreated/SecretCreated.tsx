// @ts-nocheck
import React, { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom';
import styled from '@emotion/styled';
import CreateFooter from '../../CreateFooter/CreateFooter';
import { Button, SimpleGrid, TextInput, Text, Space } from '@mantine/core';

const TextContainer = styled.div`
    text-align: center;
`

export default function SecretCreated() {
    const location = useLocation();

    const [inputContent, setInputContent] = useState<string>(location.state.uri);

    const [uriCopied, setUriCopied] = useState<Boolean>(false);

    const [dateTime, setDateTime] = useState<Date>(new Date());

    const handleClick = () => {
        navigator.clipboard.writeText(inputContent);
        setUriCopied(true);
    }

    useEffect(() => {
        let currentDateTime: Date = new Date();
        let newDate = new Date();
        let newHour = currentDateTime.getHours() + location.state.expirationHours;
        let newMinutes = currentDateTime.getMinutes() + location.state.expirationMinutes;

        newDate.setHours(newHour);

        newDate.setMinutes(newMinutes);

        setDateTime(newDate);

    }, []);


    return (
        <>
            <Text color="indigo" size="xl">URL</Text>
            <TextContainer>
                
                <SimpleGrid cols={2}>
                    <TextInput readOnly value={location.state.uri}></TextInput>
                    <Button color="indigo" onClick={handleClick}>{uriCopied ? "Copied!" : "Copy Link"}</Button>
                </SimpleGrid>
                <p>This message will expire in {location.state.expirationHours} hour(s) and {location.state.expirationMinutes} minutes from now at {dateTime.toLocaleTimeString()} on {dateTime.toLocaleDateString()}</p>
            </TextContainer>

            <Space v={lg} />

            <CreateFooter />
        </>
  )
}

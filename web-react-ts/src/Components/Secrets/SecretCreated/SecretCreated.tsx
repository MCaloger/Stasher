// @ts-nocheck
import React, { useState } from 'react'
import { useLocation } from 'react-router-dom';
import styled from '@emotion/styled';
import CreateFooter from '../../CreateFooter/CreateFooter';
import { Button, SimpleGrid, TextInput } from '@mantine/core';

const TextContainer = styled.div`
    text-align: center;
`

export default function SecretCreated() {
    const location = useLocation();

    const [inputContent, setInputContent] = useState<string>(location.state.uri);

    const [uriCopied, setUriCopied] = useState<Boolean>(false);

    const handleClick = () => {
        navigator.clipboard.writeText(inputContent);
        setUriCopied(true);
    }

    return (
        <>
            <TextContainer>
                <h1>URL:</h1>
                <SimpleGrid cols={2}>
                    <TextInput readOnnly value={location.state.uri}></TextInput>
                    <Button onClick={handleClick}>{uriCopied ? "Copied!" : "Copy Link"}</Button>
                </SimpleGrid>
            </TextContainer>

            <CreateFooter />
        </>
  )
}

// @ts-nocheck
import React from 'react'
import { useLocation } from 'react-router-dom';
import styled from '@emotion/styled';
import CreateFooter from '../../CreateFooter/CreateFooter';

const TextContainer = styled.div`
    text-align: center;
`

export default function SecretCreated() {
    const location = useLocation();

    return (
        <>
            <TextContainer>
                <div>The code for the secret is: { location.state.code } </div>
                <div>Link: <a href={ location.state.uri }>{location.state.uri}</a> </div>
            </TextContainer>

            <CreateFooter />
        </>
  )
}

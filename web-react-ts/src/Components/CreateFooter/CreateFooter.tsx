import React from 'react'
import { Link } from "react-router-dom";
import styled from '@emotion/styled';

const CreateLink = styled(Link)`
    display: flex;
    justify-content: center;
    text-align: center;
`

const FooterContainer = styled.div`
    margin-top: 10px;
    margin-bottom: 10px;
`

export default function CreateFooter() {
  return (
    <FooterContainer>
        <hr />

        <CreateLink to="/">Create new secret</CreateLink>
    </FooterContainer>
  )
}

import React from 'react'
import { Link } from "react-router-dom";
import styled from '@emotion/styled';
import { Button, Divider, Space } from '@mantine/core';

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
        <Divider></Divider>
        <Space h={10}></Space>
        <CreateLink to="/"><Button>Create New Link</Button></CreateLink>
    </FooterContainer>
  )
}

import React from 'react'
import { Link } from "react-router-dom";
import styled from '@emotion/styled';
import { Button, Divider, Space } from '@mantine/core';

const CreateLink = styled(Link)`
    text-align: center;
`

const FooterContainer = styled.div`
    display: flex;
    justify-content: center;
    margin-top: 10px;
    margin-bottom: 10px;
`

export default function CreateFooter() {
  return (
    <FooterContainer>
        <Divider></Divider>
        <Space h="md"></Space>
        <CreateLink to="/"><Button color="indigo">Create New Link</Button></CreateLink>
    </FooterContainer>
  )
}

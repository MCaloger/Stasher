// @ts-nocheck
import React, { useState, useEffect } from 'react'
import { Outlet, useParams, Link } from 'react-router-dom'
import { readSecret } from '../../../API/SecretAPI';
import styled from '@emotion/styled';
import { MessageContainer } from '../../MessageContainer/MessageContainer';
import CreateFooter from '../../CreateFooter/CreateFooter';
import { Textarea, Text } from '@mantine/core';

export default function ReadSecret() {
    const params = useParams();
    const code: string = `${params.code}`;
    const [data, setData] = useState({});

    function getMessage() {
        try {
            readSecret(code).then((data) => {
                setData(data);
            })
        } catch(exception) {
            setData(exception.message);
        }

        return data;
    }
    
    useEffect(() => {
      getMessage();
    }, [])
    
  return (
    <>
        <Textarea readOnly={true} value={data.message} autosize></Textarea>
        <Text color="indigo" size="xl">Instructions</Text>

            <div>Your message will not be accessible again, please make sure to record any necessary information and close the tab or window when finished.</div>
        <CreateFooter />
        <Outlet />
    </>
  )
}

// @ts-nocheck
import React, { useState, useEffect } from 'react'
import { Outlet, useParams } from 'react-router-dom'
import { readSecret } from '../../../Api/SecretApi';

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
        <textarea readOnly={true} value={data.message} />
            
        <Outlet />
    </>
  )
}

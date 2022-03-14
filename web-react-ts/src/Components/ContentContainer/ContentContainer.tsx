import React from 'react'
import styled from '@emotion/styled'

const ContainerComponent = styled.div`
    width: 50%;
    margin: auto;
`

export default function ContentContainer(props: any) {
  return (
    <ContainerComponent>
        {props.children}
    </ContainerComponent>
  )
}

import React from 'react'
import styled from '@emotion/styled'

const ContainerComponent = styled.div`
  display: flex;
  flex-direction: column;
  width: 90%;
  margin: auto;


  @media only screen and (min-width: 992px) {
      width: 50%;
  }
`

export default function ContentContainer(props: any) {
  return (
    <ContainerComponent>
        {props.children}
    </ContainerComponent>
  )
}

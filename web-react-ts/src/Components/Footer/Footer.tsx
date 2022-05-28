import React from 'react'
import styled from '@emotion/styled'

type Props = {}

const FooterContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
`

export default function Footer({}: Props) {
  return (
    <FooterContainer>
		<div>🛠️ by Matt Caloger</div>
		<a href="https://github.com/mattcaloger/Stasher">View Code</a>
	</FooterContainer>
  )
}
import React from 'react'
import styled from '@emotion/styled'
import { Link } from 'react-router-dom'

const NavContainer = styled.nav`
    width: 100%;
    height: 50px;
    padding: 10px;
    font-size: 24px;
    font-weight: bold;
    display:flex;
    align-items: center;
    justify-content: space-between;
`

export default function NavigationBar() {
  return (
    <NavContainer>
        <div>Stasher</div>
        <Link to="/new">
            <button>New item</button>
        </Link>
    </NavContainer>
  )
}

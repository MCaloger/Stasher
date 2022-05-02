import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import NavigationBar from './Components/NavigationBar/NavigationBar';
import Router from './Components/Router/Router';
import ContentContainer from './Components/ContentContainer/ContentContainer';
import { Space } from '@mantine/core'
import Footer from './Components/Footer/Footer';

ReactDOM.render(
<React.StrictMode>
	<BrowserRouter>
		<NavigationBar />
		<Space h="lg" />
		<ContentContainer>
			<Router />
		</ContentContainer>
		<Space h="lg" />
		<Footer
	</BrowserRouter>
</React.StrictMode>,
document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';
import App from './app/App';
import './index.css';
import 'antd/dist/antd.css';

ReactDOM.render(
  <Router>
    <App />
  </Router>,
  document.querySelector('#root')
);

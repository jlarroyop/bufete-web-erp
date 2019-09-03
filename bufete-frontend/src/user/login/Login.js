import React from 'react';
import './Login.css';
import { Form } from 'antd';
import LoginForm from './LoginForm';

const Login = ({ onLogin }) => {
  const AntWrappedLoginForm = Form.create()(LoginForm);
  return (
    <div className="login-container">
      <h1 className="page-title">Ingreso al sistema</h1>
      <div className="login-content">
        <AntWrappedLoginForm onLogin={onLogin} />
      </div>
    </div>
  );
};

export default Login;

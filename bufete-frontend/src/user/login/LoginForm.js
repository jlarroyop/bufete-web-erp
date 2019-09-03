import React from 'react';
import { Link } from 'react-router-dom';
import { notification, Form, Input, Icon, Button } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import { login } from '../../server/authService';
import { ACCESS_TOKEN } from '../../constants';

const LoginForm = props => {
  const handleSubmit = e => {
    e.preventDefault();
    props.form.validateFields((err, values) => {
      if (!err) {
        const loginRequest = { ...values };
        login(loginRequest)
          .then(response => {
            localStorage.setItem(ACCESS_TOKEN, response.accessToken);
            props.onLogin();
          })
          .catch(error => {
            if (error.status === 401) {
              notification.error({
                message: 'Bufete Legal',
                description: 'Usuario o clave incorrecta. Intente de nuevo!',
              });
            } else {
              notification.error({
                message: 'Bufete Legal',
                description: error.message || 'Lo siento! algo salio mal. Intente de nuevo',
              });
            }
          });
      }
    });
  };
  const { form } = props;
  return (
    <Form onSubmit={handleSubmit} className="login-form">
      <FormItem>
        {form.getFieldDecorator('usernameOrEmail', {
          rules: [{ required: true, message: 'Ingrese su usuario o correo!' }],
        })(<Input prefix={<Icon type="user" />} size="large" name="usernameOrEmail" placeholder="Usuario o Correo" />)}
      </FormItem>
      <FormItem>
        {form.getFieldDecorator('password', {
          rules: [{ required: true, message: 'Ingrese su contraseña!' }],
        })(<Input prefix={<Icon type="lock" />} size="large" name="password" type="password" placeholder="Contraseña" />)}
      </FormItem>
      <FormItem>
        <Button type="primary" htmlType="submit" size="large" className="login-form-button">
          Ingresar
        </Button>
        Or <Link to="/signup">register now!</Link>
      </FormItem>
    </Form>
  );
};

export default LoginForm;

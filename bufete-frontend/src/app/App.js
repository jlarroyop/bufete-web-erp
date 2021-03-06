import React, { useState, useEffect } from 'react';
import './App.css';
import { Route, withRouter, Switch } from 'react-router-dom';

import { Layout, notification } from 'antd';
import { getCurrentUser } from '../server/authService';
import { ACCESS_TOKEN } from '../constants';

import AppHeader from '../common/Header';
import FooterComponent from '../common/Footer';
import LoadingIndicator from '../common/LoadingIndicator';
import Home from '../home/Home';
import Login from '../user/login/Login';
import NotFound from '../common/NotFound';
import PrivateRoute from '../common/PrivateRoute';

const { Content } = Layout;

const App = props => {
  const [currentUser, setCurrentUser] = useState(null);
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [isLoading, setLoading] = useState(false);

  notification.config({
    placement: 'topRight',
    top: 70,
    duration: 3,
  });

  const loadCurrentUser = () => {
    setLoading(true);
    getCurrentUser()
      .then(response => {
        setCurrentUser(response);
        setAuthenticated(true);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  };

  useEffect(() => {
    loadCurrentUser();
  }, []);

  const handleLogout = (redirectTo = '/', notificationType = 'success', description = "You're successfully logged out.") => {
    localStorage.removeItem(ACCESS_TOKEN);

    setCurrentUser(null);
    setAuthenticated(false);

    props.history.push(redirectTo);

    notification[notificationType]({
      message: 'Bufete App',
      description,
    });
  };

  const handleLogin = () => {
    notification.success({
      message: 'Bufete App',
      description: "You're successfully logged in.",
    });
    loadCurrentUser();
    props.history.push('/');
  };

  if (isLoading) {
    return <LoadingIndicator />;
  }

  return (
    <Layout className="app-container" style={{ backgroundColor: 'white' }}>
      <AppHeader isAuthenticated={isAuthenticated} currentUser={currentUser} onLogout={handleLogout} />
      <Content className="app-content">
        <div className="container">
          <Switch>
            <PrivateRoute authenticated={isAuthenticated} path="/" exact component={Home} handleLogout={handleLogout} />
            <Route path="/login" render={contProps => <Login onLogin={handleLogin} {...contProps} />} />
            <Route component={NotFound} />
          </Switch>
        </div>
      </Content>
      <FooterComponent />
    </Layout>
  );
};

export default withRouter(App);

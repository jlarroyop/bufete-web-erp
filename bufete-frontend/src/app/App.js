import React from 'react';
import { Route, withRouter, Switch } from 'react-router-dom';
import { Layout } from 'antd';
import HeaderMenu from '../common/Header';
import FooterComponent from '../common/Footer';

const { Content } = Layout;

const App = () => {
  return (
    <Layout className="layout">
      <HeaderMenu pathname="/" />
      <Content>
        <div>
          <h1>Hello React, Webpack 4 & Babel 7!</h1>
        </div>
      </Content>
      <FooterComponent />
    </Layout>
  );
};

export default withRouter(App);

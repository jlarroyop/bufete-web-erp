import React from 'react';
import { Layout } from 'antd';

const { Footer } = Layout;

const footerClass = {
  position: 'fixed',
  bottom: 0,
  paddingTop: '1em',
  width: '100%',
  color: 'white',
  textAlign: 'center',
  background: '#001529',
};

function FooterComponent() {
  return (
    <Footer style={footerClass} theme="dark">
      Bufete Legar ©2019 Created by jarroyo
    </Footer>
  );
}

export default FooterComponent;

import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Header.css';
import { Menu, Icon, Layout } from 'antd';

const { SubMenu } = Menu;
const { Header } = Layout;

const HeaderMenu = props => {
  const { location, currentUser } = props;

  let menuItems;
  let menuClass = 'app-menu';
  if (currentUser) {
    menuItems = [
      <Menu.Item key="/">
        <Link to="/">
          <Icon type="home" className="nav-icon" />
        </Link>
      </Menu.Item>,
      <Menu.Item key="/poll/new">
        <Link to="/poll/new">Nuevo</Link>
      </Menu.Item>,
    ];
  } else {
    menuItems = [
      <Menu.Item key="/login">
        <Link to="/login">Login</Link>
      </Menu.Item>,
      <Menu.Item key="/signup">
        <Link to="/signup">Signup</Link>
      </Menu.Item>,
    ];
    menuClass = 'app-menu app-menu-right';
  }

  return (
    <Header>
      <div className="container-header">
        <div className="app-title">
          <Link to="/">Bufete Legal</Link>
        </div>
        <Menu className={menuClass} mode="horizontal" style={{ lineHeight: '64px' }} theme="dark">
          {menuItems}
        </Menu>
      </div>
    </Header>
  );
};

export default HeaderMenu;

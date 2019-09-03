import React from 'react';
import { Link } from 'react-router-dom';
import { Menu } from 'antd';
import ProfileDropdownMenu from '../common/ProfileDropdownMenu';

const RightMenu = ({ currentUser, handleMenuClick }) => {
  let menuItems = [];

  if (currentUser) {
    menuItems = [
      <Menu.Item key="/profile" className="profile-menu">
        <ProfileDropdownMenu currentUser={currentUser} handleMenuClick={handleMenuClick} />
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
  }

  return (
    <Menu mode="horizontal" theme="dark">
      {menuItems}
    </Menu>
  );
};

export default RightMenu;

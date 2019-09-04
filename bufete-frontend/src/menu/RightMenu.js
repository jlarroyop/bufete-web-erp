import React from 'react';
import { Menu } from 'antd';
import ProfileDropdownMenu from '../common/ProfileDropdownMenu';

const RightMenu = ({ currentUser, handleMenuClick }) => {
  return (
    <Menu mode="horizontal" theme="dark">
      <Menu.Item key="/profile" className="profile-menu">
        <ProfileDropdownMenu currentUser={currentUser} handleMenuClick={handleMenuClick} />
      </Menu.Item>
    </Menu>
  );
};

export default RightMenu;

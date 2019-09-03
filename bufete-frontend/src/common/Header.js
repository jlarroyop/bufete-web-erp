import React from 'react';
import { withRouter } from 'react-router-dom';
import './Header.css';
import LeftMenu from '../menu/LeftMenu';
import RightMenu from '../menu/RightMenu';

const AppHeader = props => {
  const { currentUser } = props;

  const handleMenuClick = ({ key }) => {
    if (key === 'logout') {
      props.onLogout();
    }
  };

  return (
    <div className="menuBar">
      <div className="logo">
        <a href="/">Bufete Legal</a>
      </div>
      <div className="menuCon">
        <div className="leftMenu">{currentUser && <LeftMenu />}</div>
        <div className="rightMenu">
          <RightMenu currentUser={currentUser} handleMenuClick={handleMenuClick} />
        </div>
      </div>
    </div>
  );
};

export default withRouter(AppHeader);

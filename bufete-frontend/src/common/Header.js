import React from 'react';
import { withRouter } from 'react-router-dom';
import './Header.css';
import LeftMenu from '../menu/LeftMenu';
import RightMenu from '../menu/RightMenu';

const AppHeader = props => {
  const { currentUser, location } = props;
  const handleMenuClick = ({ key }) => {
    if (key === 'logout') {
      props.onLogout();
    }
  };
  return (
    <div className="menuBar" style={{ overflow: 'unset' }}>
      <div className="logo">
        <a href="/">Bufete Legal</a>
      </div>
      {currentUser && (
        <div className="menuCon">
          <div className="leftMenu">
            <LeftMenu location={location} />
          </div>
          <div className="rightMenu">
            <RightMenu currentUser={currentUser} handleMenuClick={handleMenuClick} />
          </div>
        </div>
      )}
    </div>
  );
};

export default withRouter(AppHeader);

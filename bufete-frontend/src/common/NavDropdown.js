import React, { useState } from 'react';

const NavDropdown = props => {
  const [isToggleOn, setToggle] = useState(false);

  const showDropdown = e => {
    e.preventDefault();
    setToggle(prevState => !prevState.isToggleOn);
  };

  const classDropdownMenu = 'dropdown-menu' + (isToggleOn ? ' show' : '');

  return (
    <li className="nav-item dropdown">
      <a
        className="nav-link dropdown-toggle"
        href="/"
        id="navbarDropdown"
        role="button"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="false"
        onClick={e => {
          showDropdown(e);
        }}
      >
        {props.name}
      </a>
      <div className={classDropdownMenu} aria-labelledby="navbarDropdown">
        {props.children}
      </div>
    </li>
  );
};

export default NavDropdown;

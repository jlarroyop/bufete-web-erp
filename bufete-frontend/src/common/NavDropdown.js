import React, { useState } from 'react';

const NavDropdown = props => {
  const [isToggleOn, setToggle] = useState(false);
  const { name, children } = props;

  const showDropdown = e => {
    e.preventDefault();
    setToggle(prevState => !prevState);
  };

  const classDropdownMenu = `dropdown-menu${isToggleOn ? ' show' : ''}`;

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
        {name}
      </a>
      <div className={classDropdownMenu} aria-labelledby="navbarDropdown">
        {children}
      </div>
    </li>
  );
};

export default NavDropdown;

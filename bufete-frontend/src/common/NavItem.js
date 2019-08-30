import React from 'react';
import { Link } from 'react-router-dom';

const NavItem = props => {
  const { path, disabled, name } = props;
  const pageURI = window.location.pathname + window.location.search;
  const liClassName = path === pageURI ? 'nav-item active' : 'nav-item';
  const aClassName = disabled ? 'nav-link disabled' : 'nav-link';
  return (
    <li className={liClassName}>
      <Link to={path} className={aClassName}>
        {name}
        {path === pageURI ? <span className="sr-only">(current)</span> : ''}
      </Link>
    </li>
  );
};

export default NavItem;

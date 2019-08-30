import React from 'react';
import NavItem from './NavItem';
import NavDropdown from './NavDropdown';

const Header = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <a className="navbar-brand" href="/">
        Navbar
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav mr-auto">
          <NavItem path="/" name="Home" />
          <NavItem path="/page2" name="Page2" />
          <NavItem path="/page3" name="Disabled" disabled="true" />
          <NavDropdown name="Dropdown">
            <a className="dropdown-item" href="/">
              Action
            </a>
            <a className="dropdown-item" href="/">
              Another action
            </a>
            <div className="dropdown-divider"></div>
            <a className="dropdown-item" href="/">
              Something else here
            </a>
          </NavDropdown>
        </ul>
        <form className="form-inline my-2 my-lg-0">
          <input
            className="form-control mr-sm-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
          <button
            className="btn btn-outline-success my-2 my-sm-0"
            type="submit"
          >
            Search
          </button>
        </form>
      </div>
    </nav>
  );
};

export default Header;

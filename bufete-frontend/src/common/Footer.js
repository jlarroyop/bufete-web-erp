import React from 'react';

const footerClass = {
  position: 'fixed',
  bottom: 0,
  paddingTop: '1em',
  width: '100%',
  color: 'white',
};

function Footer() {
  return (
    <footer className="footer mt-auto py-3 navbar-light bg-dark" style={footerClass}>
      <div className="container mx-auto text-center">
        <b>Version</b> 1.0.0{' '}
        <strong>
          Copyright © 2014-2019 <a href="/">jarroyo</a>.
        </strong>{' '}
        All rights reserved.
      </div>
    </footer>
  );
}

export default Footer;

import React from 'react';
import Header from '../common/Header';
import Footer from '../common/Footer';

const App = () => {
  return (
    <div>
      <Header />
      <div className="container">
        <h1>Hello React, Webpack 4 & Babel 7!</h1>
      </div>
      <Footer />
    </div>
  );
};

export default App;

import React from 'react';
import './NotFound.css';
import { Link } from 'react-router-dom';
import { Button } from 'antd';

const NotFound = () => {
  return (
    <div className="page-not-found">
      <h1 className="title">404</h1>
      <div className="desc">La pagina que busca no existe!!</div>
      <Link to="/">
        <Button className="go-back-btn" type="primary" size="large">
          Regresar
        </Button>
      </Link>
    </div>
  );
};

export default NotFound;

import React from 'react';
import { Menu, Icon } from 'antd';
import { Link } from 'react-router-dom';

const { SubMenu } = Menu;

const LeftMenu = () => {
  const menuItems = [
    <SubMenu
      key="menu:1"
      title={
        <span className="submenu-title-wrapper">
          <Icon type="folder-open" />
          Expediente
        </span>
      }
    >
      <Menu.Item key="expediente:1">
        <Link to="/">Clientes</Link>
      </Menu.Item>
      <Menu.Item key="expediente:2">
        <Link to="/">Empleados</Link>
      </Menu.Item>
      <Menu.Item key="expediente:3">
        <Link to="/">Expedientes</Link>
      </Menu.Item>
      <Menu.Item key="expediente:4">
        <Link to="/">Visado</Link>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item key="expediente:5">
        <Link to="/">Actividades</Link>
      </Menu.Item>
      <Menu.Item key="expediente:6">
        <Link to="/">Instituciones</Link>
      </Menu.Item>
      <Menu.Item key="expediente:7">
        <Link to="/">Agenda de procurador</Link>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item key="expediente:8">
        <Link to="/">Liquidaciones</Link>
      </Menu.Item>
      <Menu.Item key="expediente:9">
        <Link to="/">Carga</Link>
      </Menu.Item>
    </SubMenu>,
    <SubMenu
      key="menu:2"
      title={
        <span className="submenu-title-wrapper">
          <Icon type="reconciliation" />
          Inventario
        </span>
      }
    >
      <Menu.Item key="inventario:1">
        <Link to="/">Timbres</Link>
      </Menu.Item>
      <Menu.Item key="inventario:2">
        <Link to="/">Gestiones</Link>
      </Menu.Item>
      <Menu.Item key="inventario:3">
        <Link to="/">Reasignacion</Link>
      </Menu.Item>
      <Menu.Item key="inventario:4">
        <Link to="/">Reportes</Link>
      </Menu.Item>
    </SubMenu>,
    <SubMenu
      key="menu:3"
      title={
        <span className="submenu-title-wrapper">
          <Icon type="reconciliation" />
          Facturacion
        </span>
      }
    >
      <Menu.Item key="facturacion:1">
        <Link to="/">Emision</Link>
      </Menu.Item>
      <Menu.Item key="facturacion:2">
        <Link to="/">Liquidacion</Link>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item key="facturacion:3">
        <Link to="/">Reporte Detallado</Link>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item key="facturacion:4">
        <Link to="/">Impresion</Link>
      </Menu.Item>
      <Menu.Item key="facturacion:5">
        <Link to="/">Libro de ventas</Link>
      </Menu.Item>
    </SubMenu>,
  ];

  return (
    <Menu mode="horizontal" theme="dark">
      {menuItems}
    </Menu>
  );
};

export default LeftMenu;

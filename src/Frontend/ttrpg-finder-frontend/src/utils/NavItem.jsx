import React from 'react';
import { NavLink } from 'react-router-dom';

const NavItem = ({ to, children, isActiveClass = 'active' }) => (
  <li>
    <NavLink to={to} className={({ isActive }) => (isActive ? isActiveClass : '')}>
      {children}
    </NavLink>
  </li>
);

export default NavItem;
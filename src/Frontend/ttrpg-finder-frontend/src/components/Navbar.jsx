// Navbar.js
import React from 'react';
import { NavLink } from 'react-router-dom';
import '../styles/Navbar.css';

const Navbar = () => {
    return (
        <nav className="navbar">
            <NavLink to="/" className="navbar-brand">MyApp</NavLink>
            <ul className="navbar-links">
                <li><NavLink to="/" activeClassName="active" end>Home</NavLink></li>
                <li><NavLink to="/search" activeClassName="active">Search</NavLink></li>
                <li><NavLink to="/login" activeClassName="active">Login</NavLink></li>
                <li><NavLink to="/signup" activeClassName="active">Signup</NavLink></li>
            </ul>
        </nav>
    );
};

export default Navbar;
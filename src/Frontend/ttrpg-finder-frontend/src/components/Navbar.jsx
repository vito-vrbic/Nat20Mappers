// IMPORTS
import React from 'react';
import { NavLink } from 'react-router-dom';

// CSS
import '../styles/Navbar.css';

// COMPONENT
const Navbar = () => {
    return (
        <nav className="navbar">
            <h2 className="navbar-brand">MyApp</h2>
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
// REGION: Imports
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import NavItem from './NavItem';
import '../assets/styles/Navbar.css';

// REGION: Navbar Component
const Navbar = () => {
  const { isAuthenticated, user, logout } = useAuth();
  const navigate = useNavigate();
  const handleLogoClick = () => {
    navigate(isAuthenticated ? '/dashboard' : '/');
  };

  // Rendering public links
  const renderPublicLinks = () => (
    <>
      <NavItem to="/">Home</NavItem>
      <NavItem to="/login">Login</NavItem>
      <NavItem to="/signup">Signup</NavItem>
    </>
  );

  // Rendering authenticated links
  const renderAuthenticatedLinks = () => (
    <>
      {user && (user.role === 'private' || user.role === 'business') && (
        <NavItem to="/dashboard">Dashboard</NavItem>
      )}
      {user?.role === 'business' && <NavItem to="/profile">Profile</NavItem>}
    </>
  );

  return (
    <nav className="navbar">

      <button onClick={handleLogoClick} className="navbar-brand">
        TTRPGFinder🎲
      </button>

      <ul className="navbar-links">
        {!isAuthenticated ? renderPublicLinks() : renderAuthenticatedLinks()}
        <NavItem to="/search">Search</NavItem>
        {isAuthenticated && (
          <li>
            <button onClick={logout} className="navbar-logout">Logout</button>
          </li>
        )}
      </ul>
    </nav>
  );
};

// REGION: Exports
export default Navbar;

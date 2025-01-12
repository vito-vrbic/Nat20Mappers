import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import NavItem from './NavItem';
import './Navbar.css';
import logo from '../assets/images/Logo.webp'

const Navbar = () => {
  const { isAuthenticated, user, logout } = useAuth();
  const navigate = useNavigate();
  const [isDropdownVisible, setDropdownVisible] = useState(false);
  const [mouseNearNavbar, setMouseNearNavbar] = useState(false);

  const handleLogoClick = () => {
    navigate(isAuthenticated ? '/dashboard' : '/');
  };

  // Handle mouse movements to detect proximity to the navbar
  const handleMouseMove = (e) => {
    const navbarElement = document.querySelector('.navbar');
    const navbarRect = navbarElement.getBoundingClientRect();

    // Check if mouse is within a buffer area of 100px from the navbar (can adjust this value)
    const isMouseNear =
      e.clientY >= navbarRect.top - 100 &&
      e.clientY <= navbarRect.bottom + 100 &&
      e.clientX >= navbarRect.left - 100 &&
      e.clientX <= navbarRect.right + 100;

    if (isMouseNear !== mouseNearNavbar) {
      setMouseNearNavbar(isMouseNear);
    }
  };

  // Effect to listen for mouse movements on the window
  useEffect(() => {
    window.addEventListener('mousemove', handleMouseMove);

    return () => {
      window.removeEventListener('mousemove', handleMouseMove);
    };
  }, [mouseNearNavbar]);

  // Effect to toggle navbar visibility based on proximity
  useEffect(() => {
    if (mouseNearNavbar) {
      setDropdownVisible(true);
    } else {
      setDropdownVisible(false);
    }
  }, [mouseNearNavbar]);

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
    <nav className={`navbar ${isDropdownVisible ? 'navbar-visible' : ''}`}>
      <button onClick={handleLogoClick} className="navbar-brand">
        <img src={logo} alt="TTRPGFinder Logo" className="navbar-logo" />
        TTRPGFinder
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

export default Navbar;

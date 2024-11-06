import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../utils/AuthContext'; // Importing the custom hook from AuthContext
import '../styles/Navbar.css';

const Navbar = () => {
  const { isAuthenticated, user, logout } = useAuth(); // Use the useAuth hook to get authentication state, user, and logout function
  const navigate = useNavigate(); // Using navigate hook to programmatically redirect
  
  // Handle navigation when the brand (logo) is clicked
  const handleLogoClick = () => {
    if (isAuthenticated) {
      navigate('/dashboard'); // Redirect to dashboard if authenticated
    } else {
      navigate('/'); // Redirect to home if not authenticated
    }
  };

  return (
    <nav className="navbar">
      {/* Clicking the logo will conditionally redirect */}
      <div onClick={handleLogoClick} className="navbar-brand">
        MyApp
      </div>
      
      <ul className="navbar-links">
        <li>
          <NavLink 
            to="/" 
            className={({ isActive }) => (isActive ? 'active' : '')} 
            end
          >
            Home
          </NavLink>
        </li>
        <li>
          <NavLink 
            to="/search" 
            className={({ isActive }) => (isActive ? 'active' : '')}
          >
            Search
          </NavLink>
        </li>

        {/* Public Links (For unauthenticated users) */}
        {!isAuthenticated ? (
          <>
            <li>
              <NavLink 
                to="/login" 
                className={({ isActive }) => (isActive ? 'active' : '')}
              >
                Login
              </NavLink>
            </li>
            <li>
              <NavLink 
                to="/signup" 
                className={({ isActive }) => (isActive ? 'active' : '')}
              >
                Signup
              </NavLink>
            </li>
          </>
        ) : (
          // Links for Authenticated users
          <>
            {/* Render Dashboard link for Private users */}
            {user && (user.role === 'private' || user.role === 'business') && (
              <li>
                <NavLink 
                  to="/dashboard" 
                  className={({ isActive }) => (isActive ? 'active' : '')}
                >
                  Dashboard
                </NavLink>
              </li>
            )}

            {/* Render Profile link for Business users */}
            {user && user.role === 'business' && (
              <li>
                <NavLink 
                  to="/profile" 
                  className={({ isActive }) => (isActive ? 'active' : '')}
                >
                  Profile
                </NavLink>
              </li>
            )}

            {/* Render Logout button */}
            <li>
              <button onClick={logout} className="navbar-logout">Logout</button>
            </li>
          </>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;

// REGION: Imports
import React from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

import Home from '../pages/HomePage';
import Login from '../pages/LoginPage';
import Signup from '../pages/SignupPage';
import Search from '../pages/SearchPage';
import Dashboard from '../pages/DashboardPage';
import ProfileForm from '../pages/ProfileEditPage';
import UserProfile from '../pages/ProfileViewPage';
// END-REGION: Imports

const AppRoutes = () => {
  const { isAuthenticated, user, loading } = useAuth();

  // Debugging: Check auth status and user state
  console.log('Auth state:', { isAuthenticated, user, loading });

  // Show a loading screen while the auth state is being determined
  if (loading) {
    console.log('Loading auth state...');
    return <div>Loading...</div>;
  }

  // Show an error if no user is found (this should not happen in your case if auth is set up correctly)
  if (!user && user !== null) {
    console.error('User not found in auth context');
    return <div>USER NOT FOUND !!!</div>
  }

  return (
    <Routes>
      {/* Public routes */}
      <Route path="/" element={<Home />} />
      <Route path="/search" element={<Search />} />
      <Route path="/user/:username" element={<UserProfile />} />

      {/* Routes for non-authenticated users */}
      {!isAuthenticated ? (
        <>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          {/* Redirect non-authenticated users to login for protected routes */}
          <Route path="/dashboard" element={<Navigate to="/login" />} />
          <Route path="/profile" element={<Navigate to="/login" />} />
        </>
      ) : (
        <>
          {/* Routes for authenticated users */}
          {/* Private users */}
          {user.role === 'private' && (
            <>
              <Route path="/dashboard" element={<Dashboard />} />
              {/* Private users shouldn't access profile */}
              <Route path="/profile" element={<Navigate to="/dashboard" />} />
            </>
          )}

          {/* Business users */}
          {user.role === 'business' && (
            <>
              <Route path="/dashboard" element={<Dashboard />} />
              <Route path="/profile" element={<ProfileForm />} />
            </>
          )}

          {/* Debugging: Output user role and route navigation */}
          {console.log('Authenticated user role:', user.role)}

          {/* Redirect authenticated users away from login and signup pages */}
          <Route path="/login" element={<Navigate to="/dashboard" />} />
          <Route path="/signup" element={<Navigate to="/dashboard" />} />
        </>
      )}
    </Routes>
  );
};

export default AppRoutes;
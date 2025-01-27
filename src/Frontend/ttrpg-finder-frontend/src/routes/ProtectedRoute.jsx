// src/utils/ProtectedRoute.js
import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

// ProtectedRoute wrapper component
const ProtectedRoute = ({ element, role, redirectTo = "/login" }) => {
  const { isAuthenticated, user } = useAuth();

  if (!isAuthenticated) {
    return <Navigate to={redirectTo} />; // Redirect to login if not authenticated
  }

  if (role && user?.role !== role) {
    return <Navigate to="/dashboard" />; // Redirect to dashboard if user doesn't match required role
  }

  return element;
};

export default ProtectedRoute;

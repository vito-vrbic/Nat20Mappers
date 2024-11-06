//#region IMPORTS
import React, { createContext, useState, useEffect, useContext } from 'react';
import axios from 'axios';
//#endregion

// Creating AuthContext
const AuthContext = createContext();

// Creating AuthProvider component to handle authentication logic
export const AuthProvider = ({ children }) => {

  //#region State variables
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(''); // State to hold error messages
  //#endregion

  // useEffect hook to attempt to initialize the auth state based on token
  useEffect(() => {
    const token = localStorage.getItem('authToken');

    // If a token exists, verify it with the server
    if (token) {
      verifyToken(token);
    } else {
      setLoading(false);
    }
  }, 
  []);

  /*
    TOKEN VERIFICATION
    -----
    Address: ./verify-token
    Type: GET
    Header:
      Authorization: Bearer {authToken} // The token that needs to be verified for validity
    Body: {} // No body content is required for token verification
    -----
    Expected response:
      SUCCESS (Status Code 200 OK):
        Body: {
          "message": "Token is valid",
          "userData": {
            "id": "a104", 
            "username": "john_doe", 
            "email": "john.doe@example.com", 
            "role": "private",
            "organizationName": null
          }
        }

      FAILURE (401 Unauthorized):
        Body: {
          "message": "Invalid or expired token"
        }

      FAILURE (400 Bad Request):
        Body: {
          "message": "Token missing"
        }
  */
  const verifyToken = (token) => {
    axios
      .get('api/verify-token', {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        if (response.status === 200) {
          console.log("Token is valid:", response.data.message);
          setIsAuthenticated(true);
          setUser(response.data.userData);
        }
      })
      .catch((error) => {
        // FAILURE (401 Unauthorized)
        if (error.response && error.response.status === 401) {
          console.log("Invalid or expired token:", error.response.data.message);
          setIsAuthenticated(false);
          setUser(null); // Reset user data on error
        }
        
        // FAILURE (400 Bad Request) if the token is missing
        else if (error.response && error.response.status === 400) {
          console.log("Token missing:", error.response.data.message);
          setIsAuthenticated(false);
          setUser(null); // Reset user data on error
        }

        // Other errors (network, server issues, etc.)
        else {
          console.log('Error verifying token:', error);
          setIsAuthenticated(false);
          setUser(null); // Reset user data on error
        }
      })
      .finally(() => {
        setLoading(false); // Ensure loading is set to false after the request
      });
  };

  /*
    FORM SUBMISSION
    -----
    Address: ./login-submit
    Type: POST
    Header:
      Content-Type: application/json
      Authorization: Bearer {authToken} (if required for an authenticated API)
    Body:
      {
        "username": "john_doe",
        "password": "password123"
      }
    -----
    Expected response:
      SUCCESS (Status Code 200):
        Body: {
          "message": "Login successful",
          "token": "fake-jwt-token", // The token to be stored in localStorage for future requests
          "userData": {
            "id": "a104",
            "username": "john_doe",
            "email": "john.doe@example.com",
            "role": "private",
            "organizationName": null
          }
        }
      FAILURE (400 Bad Request):
        Body: {
          "message": "Issue to showcase on frontend to user"
        }
  */
  const login = async (credentials) => {
    try {
      const response = await axios.post('api/login-submit', credentials);

      if (response.status === 200) {
        localStorage.setItem('authToken', response.data.token);
        setIsAuthenticated(true);
        setUser(response.data.userData);
        setError(''); // Clear error message on successful login
      } else {
        setError("Unexpected response during login.");
        setIsAuthenticated(false);
        setUser(null);
      }
    } catch (error) {
      if (error.response) {
        if (error.response.status === 400) {
          setError("Invalid username or password");
        } else if (error.response.status === 401) {
          setError("Authentication required");
        } else {
          setError("Login failed, please try again");
        }
      } else {
        setError("Login error, no response from server");
      }
      setIsAuthenticated(false);
      setUser(null);
    }
  };

  /*
    FORM SUBMISSION
    -----
    Address: ./logout-submit
    Type: POST
    Header:
      Content-Type: application/json
      Authorization: Bearer {authToken} (required to verify the current session)
    Body: {} // No additional body content is required for logout
    -----
    Expected response:
      SUCCESS (Status Code 200 OK):
        Body: {
          "message": "Logout successful" // Confirmation that the user has logged out
        }

      FAILURE (400 Bad Request):
        Body: {
          "message": "Logout failed, please try again" // Error message if logout fails due to an issue with the request
        }

      FAILURE (401 Unauthorized):
        Body: {
          "message": "Authentication required" // User is not authenticated or the provided token is invalid
        }
  */
  const logout = async () => {
    const token = localStorage.getItem('authToken');

    if (token) {
      try {
        const response = await axios.post('api/logout-submit', {}, {
          headers: { Authorization: `Bearer ${token}` },
        });

        if (response.status === 200) {
          localStorage.removeItem('authToken');
          setIsAuthenticated(false);
          setUser(null);
          setError(''); // Clear error message on successful logout
        } else {
          setError("Unexpected response during logout.");
        }
      } catch (error) {
        if (error.response) {
          if (error.response.status === 400) {
            setError("Logout failed, please try again.");
          } else if (error.response.status === 401) {
            setError("Authentication required.");
          } else {
            setError("Logout error, please try again.");
          }
        } else {
          setError("Logout error, no response from server.");
        }
      }
    } else {
      setError("User is not authenticated.");
    }
  };

  // Provide the auth context to children components
  return (
    <AuthContext.Provider value={{ isAuthenticated, user, login, logout, loading, error }}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook to access authentication context
export const useAuth = () => useContext(AuthContext);

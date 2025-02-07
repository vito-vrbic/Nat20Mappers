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
  const verifyToken = (token) => {
    axios
      .get('/auth/verify-token', {
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

  const login = async (credentials) => {
    try {
      const response = await axios.post('/auth/login', credentials);

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

  const logout = async () => {
    const token = localStorage.getItem('authToken');

    if (token) {
      try {
        const response = await axios.post('/auth/logout', {}, {
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
  const checkForGoogleLogin = async (userData, isSignIn) => {
    try {
      //determine where to send the data
      var adress="";
      if(isSignIn)
        adress="/auth/google-signin";
      else
        adress="/auth/google-login";

      const response = await axios.post(adress, {
        email: userData.email, // Send email or other identifying fields
        name: userData.name, // Optionally send other fields like name, picture, etc.
      });
  
      if (response.status === 200) {
        // Store token from backend (if applicable)
        localStorage.setItem('authToken', response.data.token);
  
        // Update authentication state
        setIsAuthenticated(true);
        setUser(response.data.userData);
  
        setError(''); // Clear any existing error messages
      } else {
        // Handle unexpected responses
        setError("Unexpected response during Google login.");
        setIsAuthenticated(false);
        setUser(null);
      }
    } catch (error) {
      // Handle errors during the request
      
      if (error.response) {
        if (error.response.status === 400) {
          setError("Invalid Google login data. Try signing up with google first.");
        } else if (error.response.status === 401) {
          setError("Google authentication failed.");
        } else {
          setError("Google login error, please try again.");
        }
      } else {
        setError("Google login error, no response from server.");
      }
      setIsAuthenticated(false);
      setUser(null);
      }
  };

  // Provide the auth context to children components
  return (
    <AuthContext.Provider value={{ isAuthenticated, user, login, logout, checkForGoogleLogin, loading, error }}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook to access authentication context
export const useAuth = () => useContext(AuthContext);

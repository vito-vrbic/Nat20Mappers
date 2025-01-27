const jwt = require('jsonwebtoken');
let activeTokens = {}; // Simple in-memory store for active tokens

// Function to add token to active store
const addToken = (token, userId) => {
  activeTokens[token] = userId; // Store token associated with user ID
};

// Function to remove token from active store (on logout)
const removeToken = (token) => {
  delete activeTokens[token]; // Remove token from active tokens store
};

// Function to check if token is revoked
const isTokenRevoked = (token) => {
  return !activeTokens[token]; // If token doesn't exist in activeTokens, it's revoked
};

const authenticateJWT = (req, res, next) => {
  const token = req.header('Authorization')?.replace('Bearer ', ''); // Extract the token

  if (!token) {
    return res.status(401).json({ message: 'Access denied, no token provided' });
  }

  // Check if the token is revoked
  if (isTokenRevoked(token)) {
    return res.status(401).json({ message: 'Token has been revoked, please login again' });
  }

  // Verify JWT
  jwt.verify(token, 'your_secret_key', (err, user) => {
    if (err) {
      return res.status(403).json({ message: 'Invalid token' });
    }
    req.user = user; // Attach the user data to the request
    next(); // Proceed to the next middleware or route handler
  });
};

module.exports = { addToken, removeToken, isTokenRevoked, authenticateJWT };

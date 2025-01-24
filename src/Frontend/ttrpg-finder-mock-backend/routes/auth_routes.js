// REGION: Packages
const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const { get_user_db } = require('../packages/server_load');
const { save_user_db } = require('../packages/server_unload');
const { addToken, removeToken, isTokenRevoked, authenticateJWT } = require('../packages/active_tokens')
// REGION-END: Packages

// Signup route
router.post('/signup', (req, res) => {
  const { username, email, password, role, organizationName } = req.body;

  // Validate input (simple example, could be expanded)
  if (!username || !email || !password || !role) {
    return res.status(400).json({ message: 'Missing required fields' });
  }

  // Get users from memory
  const users = get_user_db();

  // Check if user already exists
  const existingUser = users.find(user => user.username === username || user.email === email);
  if (existingUser) {
    return res.status(400).json({ message: 'User already exists' });
  }

  // Create new user
  const newUser = {
    id: Date.now().toString(), // Simple unique ID for the new user
    username,
    email,
    password, // In a real scenario, passwords should be hashed!
    role,
    organizationName: role === 'business' ? organizationName : null,
  };

  // Add user to in-memory database
  users.push(newUser);
  save_user_db(users); // Save the updated list to file

  res.status(201).json({ message: 'User created successfully' });
});

// Login route
router.post('/login', (req, res) => {
  const { username, password } = req.body;

  // Validate input
  if (!username || !password) {
    return res.status(400).json({ message: 'Missing required fields' });
  }

  // Get users from memory
  const users = get_user_db();

  // Find user by username
  const user = users.find(user => user.username === username);

  // Validate password (in real scenarios, compare hashed passwords)
  if (!user || user.password !== password) {
    return res.status(400).json({ message: 'Invalid username or password' });
  }

  // Generate JWT token
  const token = jwt.sign({ id: user.id, username: user.username }, 'your_secret_key', { expiresIn: '1h' });

  // Store the token in the activeTokens store
  addToken(token, user.id);

  // Respond with success and include the token and user data
  res.status(200).json({
    message: 'Login successful',
    token,
    userData: {
      id: user.id,
      username: user.username,
      email: user.email,
      role: user.role,
      organizationName: user.organizationName,
    },
  });
});

// Verify JWT route
router.get('/verify-token', (req, res) => {
  const token = req.header('Authorization')?.replace('Bearer ', ''); // Extract token from header

  if (!token) {
    return res.status(400).json({ message: 'Token missing' }); // If token is missing
  }

  // Check if the token is revoked
  if (isTokenRevoked(token)) {
    return res.status(401).json({ message: 'Invalid or expired token' }); // If token is revoked
  }

  // Verify JWT
  jwt.verify(token, 'your_secret_key', (err, user) => {
    if (err) {
      return res.status(401).json({ message: 'Invalid or expired token' }); // If the token is invalid or expired
    }

    // Get users from memory
    const users = get_user_db();

    // Check if the user ID exists in the user database (this ensures the token is associated with a valid user)
    const existingUser = users.find(u => u.id === user.id);

    if (!existingUser) {
      return res.status(401).json({ message: 'User not found or invalid token' });
    }

    // Respond with valid token and user data
    res.status(200).json({
      message: 'Token is valid',
      userData: {
        id: existingUser.id,
        username: existingUser.username,
        email: existingUser.email,
        role: existingUser.role,
        organizationName: existingUser.organizationName,
      },
    });
  });
});

// Logout route (removes the token from active store)
router.post('/logout', authenticateJWT, (req, res) => {
  const token = req.header('Authorization')?.replace('Bearer ', ''); // Get the token from the Authorization header

  // Remove the token from active tokens store
  removeToken(token);

  res.status(200).json({ message: 'Logged out successfully' });
});

router.post('/apply', async (req, res) => {
  const { gameId, userId, questions } = req.body;
  console.log(questions);
  try {
    const users = get_user_db();
    const user = users.find(user => user.id === userId);
    
    //different logic should be applied for sending back an error
    //this is just for seeing if the frontend part works
    //error could occur from signing the person up for a full game
    //also add a check or if the person has already sent an application
    
    if(!user){
      return res.status(404).json({
        success: false,
        message: 'User not found.',
      });
    }
    else{
      res.status(200).json({
      success: true,
      message: 'Application submitted successfully!',
    });
    }
    
  } catch (error) {
    console.error('Error processing application:', error);
    res.status(500).json({
      success: false,
      message: 'Failed to process application. Please try again later.',
    });
  }
});

module.exports = router;

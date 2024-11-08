const express = require('express');
const cors = require('cors');
const crypto = require('crypto');
const app = express();

// Enable CORS
app.use(cors());
app.use(express.json());

// Sample user data (In practice, passwords should not be stored in plaintext)
const users = [
  {
    id: "0293",
    username: "fez",
    role: "business",
    email: "fez@cassandra.hr",
    password: "password123",  // Example plaintext password
    organizationName: "fez enterprises"
  },
  {
    id: "a104",
    username: "greg",
    email: "greg@gregscakes.com",
    password: "password456",  // Example plaintext password
    role: "private",
    organizationName: null
  }
];

const games = [
  {
    id: "12345",
    title: "fezPass",
    type: "local",
    location: {"lat": 1 ,"lng": 2},
    availability: "public",
    createdBy: "business",
    applicationRequired: true
  },
  {
    id: "04322",
    title: "ferGame",
    type: "online",
    location: {"lat": 1 ,"lng": 2},
    availability: "public",
    createdBy: "business",
    applicationRequired: true
  },
  {
    id: "54352",
    title: "nemozesOvoVidjetAkoNisiRegistriran",
    type: "local",
    location: {"lat": 1 ,"lng": 2},
    availability: "private",
    createdBy: "user",
    applicationRequired: true
  },
  {
    id: "89897",
    title: "kasandra",
    type: "local",
    location: {"lat": 1 ,"lng": 2},
    availability: "private",
    createdBy: "business",
    applicationRequired: false
  },
  {
    id: "99999",
    title: "akoOvoVidisPrijavljenSi",
    type: "local",
    location: {"lat": 1 ,"lng": 2},
    availability: "private",
    createdBy: "user",
    applicationRequired: false
  },
];

// Store active tokens
let activeTokens = {};


// This GET was added to allow getting data from server.js
app.get('/api/games', (req, res) => {
  res.json({games});
});
// This End of added GET that allows getting data from server.js

// Login endpoint with password comparison
app.post('/api/login-submit', (req, res) => {
  const { username, password } = req.body;

  // Find user by username
  const user = users.find(user => user.username === username);

  if (!user) {
    return res.status(400).json({ message: 'Invalid credentials' });
  }

  // Compare plaintext password with input password
  if (password !== user.password) {
    return res.status(400).json({ message: 'Invalid credentials' });
  }

  // Generate a unique token (for session management)
  const token = crypto.randomBytes(16).toString('hex');
  activeTokens[token] = user;

  console.log(activeTokens);

  // Respond with success, user data, and token
  res.json({
    message: 'Login successful',
    token,
    userData: {
      id: user.id,
      username: user.username,
      email: user.email,
      role: user.role,
    }
  });
});

// Token verification endpoint
app.get('/api/verify-token', (req, res) => {
  // Extract the token from the Authorization header
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1]; // Get the token part after "Bearer"

  if (!token) {
    return res.status(400).json({ message: 'Token missing' });
  }

  if (activeTokens[token]) {
    return res.json({
      message: 'Token is valid',
      userData: activeTokens[token],
    });
  } else {
    return res.status(401).json({ message: 'Invalid or expired token' });
  }
});

// Logout endpoint to invalidate token
app.post('/api/logout-submit', (req, res) => {
  // Extract the token from the Authorization header
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1]; // Get the token part after "Bearer"

  if (!token) {
    return res.status(400).json({ message: 'Token missing' });
  }

  // Check if the token is in the activeTokens list
  if (activeTokens[token]) {
    delete activeTokens[token]; // Invalidate the token by deleting it
    return res.json({ message: 'Logout successful' });
  } else {
    return res.status(400).json({ message: 'No active session' });
  }
});

// Signup endpoint to register a new user
app.post('/api/signup', (req, res) => {
  const { username, email, password, role, organizationName } = req.body;

  // Validate required fields
  if (!username || !email || !password || !role) {
    return res.status(400).json({ message: 'All fields are required' });
  }

  // Check if username or email already exists
  const existingUser = users.find(user => user.username === username || user.email === email);

  if (existingUser) {
    return res.status(400).json({ message: 'Username or email already taken' });
  }

  // Create new user object
  const newUser = {
    id: crypto.randomBytes(16).toString('hex'),
    username,
    email,
    password, // Store plaintext password
    role,
    organizationName: role === 'business' ? organizationName : null,
  };

  // Save user (In a real application, save to database)
  users.push(newUser);

  // Send response with user data
  res.status(201).json({
    message: 'Signup successful',
    userData: {
      id: newUser.id,
      username: newUser.username,
      email: newUser.email,
      role: newUser.role,
      organizationName: newUser.organizationName,
    }
  });
});

// Start server
app.listen(5000, () => {
  console.log('Server is running on http://localhost:5000');
});

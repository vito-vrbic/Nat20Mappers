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

// Sample game data (to be filtered)
const games = [
  {
    id: "1",
    title: "Chess",
    type: "online",
    location: { lat: 45.8131, lng: 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true
  },
  {
    id: "2",
    title: "Monopoly",
    type: "local",
    location: { lat: 45.8131, lng: 15.978 },
    availability: "private",
    createdBy: "private",
    applicationRequired: false
  },
  {
    id: "3",
    title: "Scrabble",
    type: "online",
    location: { lat: 45.8131, lng: 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: false
  }
];

// Store active tokens
let activeTokens = {};

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

// Search Game Form Endpoint
app.post('/api/search-game-form', (req, res) => {
  const { 
    gameTitle, 
    gameType, 
    includeFullGames, 
    applicationRequired, 
    includeUserMadeGames, 
    includeBusinessMadeGames, 
    gameAvailability, 
    mapLocation, 
    radius, 
    page 
  } = req.body;

  // Filtering logic
  let filteredGames = games;

  // Filter by game title (if provided)
  if (gameTitle) {
    filteredGames = filteredGames.filter(game => game.title.toLowerCase().includes(gameTitle.toLowerCase()));
  }

  // Filter by game type (online/local)
  if (gameType && gameType !== 'All Types') {
    filteredGames = filteredGames.filter(game => game.type.toLowerCase() === gameType.toLowerCase());
  }

  // Filter by availability
  if (gameAvailability && gameAvailability !== 'All Games') {
    filteredGames = filteredGames.filter(game => game.availability.toLowerCase() === gameAvailability.toLowerCase());
  }

  // Filter by application required
  if (applicationRequired !== undefined) {
    filteredGames = filteredGames.filter(game => game.applicationRequired === applicationRequired);
  }

  // Filter by map location (simple proximity check for this example)
  if (mapLocation && radius) {
    const radiusInKm = parseFloat(radius);
    filteredGames = filteredGames.filter(game => {
      const distance = getDistance(game.location, mapLocation);
      return distance <= radiusInKm;
    });
  }

  // Pagination logic (simple implementation for page and limit)
  const pageSize = 10;
  const start = (page - 1) * pageSize;
  const end = page * pageSize;
  filteredGames = filteredGames.slice(start, end);

  // Return filtered games and pagination info
  res.json({
    games: filteredGames,
    pagination: {
      currentPage: page,
      totalPages: Math.ceil(filteredGames.length / pageSize),
      totalItems: filteredGames.length,
    }
  });
});

// Helper function to calculate distance (basic approximation, can be improved)
function getDistance(loc1, loc2) {
  const lat1 = loc1.lat;
  const lon1 = loc1.lng;
  const lat2 = loc2.lat;
  const lon2 = loc2.lng;
  const R = 6371; // Earth's radius in km
  const dLat = (lat2 - lat1) * Math.PI / 180;
  const dLon = (lon2 - lon1) * Math.PI / 180;
  const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = R * c; // Distance in km
  return distance;
}

// Start server
app.listen(5000, () => {
  console.log('Server is running on http://localhost:5000');
});

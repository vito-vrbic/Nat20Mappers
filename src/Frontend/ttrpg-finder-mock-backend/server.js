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
    password: "password123", // Example plaintext password
    organizationName: "fez enterprises"
  },
  {
    id: "a104",
    username: "greg",
    email: "greg@gregscakes.com",
    password: "password456", // Example plaintext password
    role: "private",
    organizationName: null
  }
];

// Sample game data
const games = [
  {
    id: "12345",
    title: "fezPass",
    type: "local",
    location: { "lat": 1, "lng": 2 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true
  },
  {
    id: "04322",
    title: "ferGame",
    type: "online",
    location: { "lat": 1, "lng": 2 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true
  },
  {
    id: "54352",
    title: "nemozesOvoVidjetAkoNisiRegistriran",
    type: "local",
    location: { "lat": 1, "lng": 2 },
    availability: "private",
    createdBy: "user",
    applicationRequired: true
  },
  {
    id: "89897",
    title: "kasandra",
    type: "local",
    location: { "lat": 1, "lng": 2 },
    availability: "private",
    createdBy: "business",
    applicationRequired: false
  },
  {
    id: "99999",
    title: "akoOvoVidisPrijavljenSi",
    type: "local",
    location: { "lat": 1, "lng": 2 },
    availability: "private",
    createdBy: "user",
    applicationRequired: false
  }
];

// Store active tokens
let activeTokens = {};

// Helper function to calculate distance (basic approximation)
function getDistance(loc1, loc2) {
  const lat1 = loc1.lat;
  const lon1 = loc1.lng;
  const lat2 = loc2.lat;
  const lon2 = loc2.lng;
  const R = 6371; // Earth's radius in km
  const dLat = (lat2 - lat1) * Math.PI / 180;
  const dLon = (lon2 - lon1) * Math.PI / 180;
  const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
    Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c; // Distance in km
}

// GET Games (unfiltered) for testing
app.get('/api/games', (req, res) => {
  res.json({ games });
});

// Login endpoint with password comparison
app.post('/api/auth/login', (req, res) => {
  const { username, password } = req.body;

  // Find user by username
  const user = users.find(user => user.username === username);

  if (!user || password !== user.password) {
    return res.status(400).json({ message: 'Invalid credentials' });
  }

  // Generate a unique token (for session management)
  const token = crypto.randomBytes(16).toString('hex');
  activeTokens[token] = user;

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
app.get('/api/auth/verify-token', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token || !activeTokens[token]) {
    return res.status(401).json({ message: 'Invalid or expired token' });
  }

  return res.json({
    message: 'Token is valid',
    userData: activeTokens[token]
  });
});

// Logout endpoint to invalidate token
app.post('/api/auth/logout', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token || !activeTokens[token]) {
    return res.status(400).json({ message: 'No active session' });
  }

  delete activeTokens[token];
  res.json({ message: 'Logout successful' });
});

// Signup endpoint to register a new user
app.post('/api/auth/signup', (req, res) => {
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

  users.push(newUser);
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

// Game Search Form Endpoint
app.post('/api/data/search', (req, res) => {
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
    page = 1
  } = req.body;

  let filteredGames = games;

  if (gameTitle) {
    filteredGames = filteredGames.filter(game =>
      game.title.toLowerCase().includes(gameTitle.toLowerCase())
    );
  }

  if (gameType && gameType !== 'All Types') {
    filteredGames = filteredGames.filter(game =>
      game.type.toLowerCase() === gameType.toLowerCase()
    );
  }

  if (gameAvailability && gameAvailability !== 'All Games') {
    filteredGames = filteredGames.filter(game =>
      game.availability.toLowerCase() === gameAvailability.toLowerCase()
    );
  }

  if (applicationRequired !== undefined) {
    filteredGames = filteredGames.filter(game =>
      game.applicationRequired === applicationRequired
    );
  }

  if (mapLocation && radius) {
    const radiusInKm = parseFloat(radius);
    filteredGames = filteredGames.filter(game =>
      getDistance(game.location, mapLocation) <= radiusInKm
    );
  }

  const pageSize = 10;
  const start = (page - 1) * pageSize;
  const end = page * pageSize;
  const paginatedGames = filteredGames.slice(start, end);

  res.json({
    games: paginatedGames,
    pagination: {
      currentPage: page,
      totalPages: Math.ceil(filteredGames.length / pageSize),
      totalItems: filteredGames.length,
    }
  });
});

// Start server
app.listen(5000, () => {
  console.log('Server is running on http://localhost:5000');
});

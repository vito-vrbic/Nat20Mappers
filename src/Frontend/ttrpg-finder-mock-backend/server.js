const express = require('express');
const cors = require('cors');
const crypto = require('crypto');
const app = express();

// Enable CORS
app.use(cors());
app.use(express.json());

const testGames = [
  // Sample games for testing (could be populated from a database in a real app)
  {
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },{
    id: "12345",
    title: "Test Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: true,
    complexity: "Medium",
    estimatedLength: "2 hours",
    startTimestamp: "2024-11-10T15:00:00Z",
    description: "A fun and engaging online strategy game.",
    pravilnik: "Players must strategize to defeat opponents.",
    requiresForm: true,
    currentPlayerCount: 5,
    maxPlayerCount: 20,
    communicationChannel: "Discord",
    isHomebrew: false
  },
  // Other test games...
];

const users = [
  // Sample user data
  {
    id: "0293",
    username: "fez",
    role: "business",
    email: "fez@cassandra.hr",
    password: "password123",
    organizationName: "fez enterprises",
    logo: 'https://example.com/path/to/logo.png',
    companyPhone: '123-456-7890',
    companyDescription: 'A description of the company...',
    companyWebsite: 'https://example.com',
    companyAddress: '123 Example St, City, Country',
  },
  {
    id: "a104",
    username: "greg",
    email: "greg@gregscakes.com",
    password: "password456",
    role: "private",
    organizationName: null,
    logo: '',
    companyPhone: '',
    companyDescription: '',
    companyWebsite: '',
    companyAddress: '',
  }  
];

let activeTokens = {}; // Store active session tokens

// Helper function to calculate distance
function getDistance(loc1, loc2) {
  const lat1 = loc1.lat;
  const lon1 = loc1.lng;
  const lat2 = loc2.lat;
  const lon2 = loc2.lng;
  const R = 6371;
  const dLat = (lat2 - lat1) * Math.PI / 180;
  const dLon = (lon2 - lon1) * Math.PI / 180;
  const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
    Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c;
}

// Endpoints

// GET all games (for dashboard)
app.get('/api/games', (req, res) => {
  res.json({ games: testGames });
});

// POST to search games with filters
app.post('/api/data/search', (req, res) => {
  const { gameTitle, gameType, includeFullGames, applicationRequired, includeUserMadeGames, includeBusinessMadeGames, gameAvailability, mapLocation, radius, page = 1 } = req.body;
  let filteredGames = testGames;

  if (gameTitle) {
    filteredGames = filteredGames.filter(game => game.title.toLowerCase().includes(gameTitle.toLowerCase()));
  }

  if (gameType && gameType !== 'All Types') {
    filteredGames = filteredGames.filter(game => game.type.toLowerCase() === gameType.toLowerCase());
  }

  if (gameAvailability && gameAvailability !== 'All Games') {
    filteredGames = filteredGames.filter(game => game.availability.toLowerCase() === gameAvailability.toLowerCase());
  }

  if (applicationRequired !== undefined) {
    filteredGames = filteredGames.filter(game => game.applicationRequired === applicationRequired);
  }

  if (mapLocation && radius) {
    const radiusInKm = parseFloat(radius);
    filteredGames = filteredGames.filter(game => getDistance(game.location, mapLocation) <= radiusInKm);
  }

  const pageSize = 10;
  const start = (page - 1) * pageSize;
  const end = page * pageSize;
  const paginatedGames = filteredGames.slice(start, end);

  res.json({
    games: paginatedGames,
    pagination: { currentPage: page, totalPages: Math.ceil(filteredGames.length / pageSize), totalItems: filteredGames.length }
  });
});

// GET user profile based on token
app.get('/api/user/profile', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token || !activeTokens[token]) {
    return res.status(401).json({ error: true, message: 'Invalid or expired token', code: 401 });
  }

  const user = activeTokens[token];
  const profile = {
    username: user.username,
    role: user.role,
    organizationName: user.organizationName || "Not Provided",
    logo: user.logo || "https://example.com/default-logo.png",
    companyPhone: user.companyPhone || "Not Provided",
    companyDescription: user.companyDescription || "No description available.",
    companyWebsite: user.companyWebsite || "Not Provided",
    companyAddress: user.companyAddress || "Not Provided"
  };

  res.json({ error: false, message: 'Profile fetched successfully.', code: 200, data: profile });
});

// POST to create a new game (for business users)
app.post('/api/create-new-game', (req, res) => {
  const newGame = req.body;
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token || !activeTokens[token] || activeTokens[token].role !== 'business') {
    return res.status(403).json({ error: true, message: 'Only business users can create games' });
  }

  games.push(newGame);
  res.status(201).json(newGame);
});

// Login
app.post('/api/auth/login', (req, res) => {
  const { username, password } = req.body;
  const user = users.find(user => user.username === username);

  if (!user || password !== user.password) {
    return res.status(400).json({ message: 'Invalid credentials' });
  }

  const token = crypto.randomBytes(16).toString('hex');
  activeTokens[token] = user;

  res.json({ message: 'Login successful', token, userData: { id: user.id, username: user.username, email: user.email, role: user.role } });
});
app.post('/api/auth/google-login', (req, res) => {
  const { email, username } = req.body;

  // Check if the user exists in the database
  let user = users.find(user => user.email === email);

  if (!user) {
    return res.status(400).json({ message: 'Invalid credentials' });
    console.log("Doesn't exist");
  }
  else{
    console.log("Found user");
  }

  // Generate a unique token (for session management)
  const token = crypto.randomBytes(16).toString('hex');
  activeTokens[token] = user;

  // Respond with success, user data, and token
  res.json({
    message: 'Google login successful',
    token,
    userData: {
      id: user.id,
      username: user.username,
      email: user.email,
      role: user.role,
    }
  });
});
app.post('/api/auth/google-signin', (req, res) => {
  const { email, name } = req.body;
  let user = users.find(user => user.email === email);
  const token = crypto.randomBytes(16).toString('hex');
  const newUser = {
    id: crypto.randomBytes(16).toString('hex'),
    username: name,
    email: email,
    password: null, // Store plaintext password
    role: 'private',
    organizationName: null,
  };
  users.push(newUser);
  activeTokens[token] = newUser;
  // Respond with success, user data, and token
  res.json({
    message: 'Google login successful',
    token,
    userData: {
      id: newUser.id,
      username: newUser.username,
      email: newUser.email,
      role: newUser.role,
    }
  });
});


// Verify token
app.get('/api/auth/verify-token', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token || !activeTokens[token]) {
    return res.status(401).json({ message: 'Invalid or expired token' });
  }

  res.json({ message: 'Token is valid', userData: activeTokens[token] });
});

// Logout
app.post('/api/auth/logout', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token || !activeTokens[token]) {
    return res.status(400).json({ message: 'No active session' });
  }

  delete activeTokens[token];
  res.json({ message: 'Logout successful' });
});

// Signup
app.post('/api/auth/signup', (req, res) => {
  const { username, email, password, role, organizationName } = req.body;

  if (!username || !email || !password || !role) {
    return res.status(400).json({ message: 'All fields are required' });
  }

  const existingUser = users.find(user => user.username === username || user.email === email);
  if (existingUser) {
    return res.status(400).json({ message: 'Username or email already taken' });
  }

  const newUser = {
    id: crypto.randomBytes(16).toString('hex'),
    username,
    email,
    password,
    role,
    organizationName: role === 'business' ? organizationName : null,
  };

  users.push(newUser);
  res.status(201).json({ message: 'Signup successful', userData: { id: newUser.id, username: newUser.username, email: newUser.email, role: newUser.role, organizationName: newUser.organizationName } });
});

// Get games created by the user
app.get('/api/games/created', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  const user = activeTokens[token];
  const createdGames = testGames;

  res.json({ games: createdGames });
});

// Get games where the user has applied (for application-required games)
app.get('/api/games/applied', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  const appliedGames = testGames;
  res.json({ games: appliedGames });
});

// Start the server
app.listen(5000, () => {
  console.log('Server is running on http://localhost:5000');
});

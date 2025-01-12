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

// Sample game data with added details like complexity, estimated length, start timestamp, description, etc.
const games = [
  {
    id: "12345",
    title: "Game 1",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "user",
    applicationRequired: true,
    complexity: "Medium",              // Added complexity level
    estimatedLength: "2 hours",        // Added estimated game length
    startTimestamp: "2024-11-10T15:00:00Z",  // Added start timestamp
    description: "A fun and engaging online strategy game.",  // Game description
    pravilnik: "Rules: Players must strategize to defeat opponents.",  // Rules or guidelines (pravilnik)
    requiresForm: true,               // Indicates if a form is required to join
    currentPlayerCount: 5,            // Current number of players in the game
    maxPlayerCount: 20,               // Max player count allowed in the game
    communicationChannel: "Discord",  // Channel for game communication (e.g., Discord, Zoom, etc.)
    isHomebrew: false                 // Indicates if the game is homebrewed
  },
  {
    id: "67890",
    title: "Game 2",
    type: "local",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: false,
    complexity: "Easy",              // Added complexity level
    estimatedLength: "1 hour",       // Added estimated game length
    startTimestamp: "2024-11-10T16:00:00Z",  // Added start timestamp
    description: "A casual party game for all ages.",  // Game description
    pravilnik: "Rules: Players take turns answering questions.",  // Rules or guidelines (pravilnik)
    requiresForm: false,              // Indicates if a form is required to join
    currentPlayerCount: 10,           // Current number of players in the game
    maxPlayerCount: 15,               // Max player count allowed in the game
    communicationChannel: "In-person",  // Channel for game communication (e.g., Discord, Zoom, etc.)
    isHomebrew: true                 // Indicates if the game is homebrewed
  },
  {
    id: "24680",
    title: "Game 3",
    type: "online",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: false,
    complexity: "Easy",              // Added complexity level
    estimatedLength: "1 hour",       // Added estimated game length
    startTimestamp: "2024-11-10T16:00:00Z",  // Added start timestamp
    description: "A casual party game for all ages.",  // Game description
    pravilnik: "Rules: Players take turns answering questions.",  // Rules or guidelines (pravilnik)
    requiresForm: false,              // Indicates if a form is required to join
    currentPlayerCount: 10,           // Current number of players in the game
    maxPlayerCount: 15,               // Max player count allowed in the game
    communicationChannel: "In-person",  // Channel for game communication (e.g., Discord, Zoom, etc.)
    isHomebrew: true                 // Indicates if the game is homebrewed
  },
  {
    id: "30213",
    title: "Game 4",
    type: "local",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: false,
    complexity: "Easy",              // Added complexity level
    estimatedLength: "1 hour",       // Added estimated game length
    startTimestamp: "2024-11-10T16:00:00Z",  // Added start timestamp
    description: "A casual party game for all ages.",  // Game description
    pravilnik: "Rules: Players take turns answering questions.",  // Rules or guidelines (pravilnik)
    requiresForm: false,              // Indicates if a form is required to join
    currentPlayerCount: 10,           // Current number of players in the game
    maxPlayerCount: 15,               // Max player count allowed in the game
    communicationChannel: "In-person",  // Channel for game communication (e.g., Discord, Zoom, etc.)
    isHomebrew: true                 // Indicates if the game is homebrewed
  },
  {
    id: "11111",
    title: "Game 5",
    type: "local",
    location: { "lat": 45.8131, "lng": 15.978 },
    availability: "public",
    createdBy: "business",
    applicationRequired: false,
    complexity: "Easy",              // Added complexity level
    estimatedLength: "1 hour",       // Added estimated game length
    startTimestamp: "2024-11-10T16:00:00Z",  // Added start timestamp
    description: "A casual party game for all ages.",  // Game description
    pravilnik: "Rules: Players take turns answering questions.",  // Rules or guidelines (pravilnik)
    requiresForm: false,              // Indicates if a form is required to join
    currentPlayerCount: 10,           // Current number of players in the game
    maxPlayerCount: 15,               // Max player count allowed in the game
    communicationChannel: "In-person",  // Channel for game communication (e.g., Discord, Zoom, etc.)
    isHomebrew: true                 // Indicates if the game is homebrewed
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

  // Prepare the response with detailed game data
  const response = {
    games: paginatedGames.map(game => ({
      id: game.id,
      title: game.title,
      type: game.type,
      location: game.location,
      availability: game.availability,
      createdBy: game.createdBy,
      applicationRequired: game.applicationRequired,
      complexity: game.complexity,
      estimatedLength: game.estimatedLength,
      startTimestamp: game.startTimestamp,
      description: game.description,
      pravilnik: game.pravilnik,
      requiresForm: game.requiresForm,
      currentPlayerCount: game.currentPlayerCount,
      maxPlayerCount: game.maxPlayerCount,
      communicationChannel: game.communicationChannel,
      isHomebrew: game.isHomebrew
    })),
    pagination: {
      currentPage: page,
      totalPages: Math.ceil(filteredGames.length / pageSize),
      totalItems: filteredGames.length,
    }
  };

  // Log the response JSON to the console
  console.log(JSON.stringify(response, null, 2));

  // Send the response
  res.json(response);
});

// Start server
app.listen(5000, () => {
  console.log('Server is running on http://localhost:5000');
});

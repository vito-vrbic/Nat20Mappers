const express = require('express');
const router = express.Router();
const { get_games_db } = require('../packages/server_load'); // Assume this gets the games from the database
const fs = require('fs');

// Helper function to filter games based on search criteria
const filterGames = (games, filters) => {
  return games.filter(game => {
    let isMatch = true;

    // Check if gameTitle matches or is null
    if (!game.title.toLowerCase().includes(filters.gameTitle.toLowerCase())) {
      isMatch = false;
    }

    // Check if gameType matches
    if (filters.gameType && filters.gameType !== 'All Types' && game.type !== filters.gameType) {
      isMatch = false;
    }

    // Check if includeFullGames is true (if game is complete)
    if (filters.includeFullGames !== undefined && game.status !== 'complete' && !filters.includeFullGames) {
      isMatch = false;
    }

    // Check if applicationRequired matches
    if (filters.applicationRequired !== undefined && game.applicationRequired !== filters.applicationRequired) {
      isMatch = false;
    }

    // Check if the user wants only user-made or business-made games
    if (filters.includeUserMadeGames && game.createdBy !== 'user') {
      isMatch = false;
    }
    if (filters.includeBusinessMadeGames && game.createdBy !== 'business') {
      isMatch = false;
    }

    // Check if gameAvailability matches
    if (filters.gameAvailability && filters.gameAvailability !== 'all' && game.availability !== filters.gameAvailability) {
      isMatch = false;
    }

    // Check if mapLocation matches within radius
    if (filters.mapLocation && filters.radius) {
      const distance = calculateDistance(game.location, filters.mapLocation);
      if (distance > filters.radius) {
        isMatch = false;
      }
    }

    return isMatch;
  });
};

// Helper function to calculate distance between two latitude/longitude points
const calculateDistance = (location1, location2) => {
  const radian = Math.PI / 180;
  const lat1 = location1.lat * radian;
  const lat2 = location2.lat * radian;
  const deltaLat = (location2.lat - location1.lat) * radian;
  const deltaLng = (location2.lng - location1.lng) * radian;

  const a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
            Math.cos(lat1) * Math.cos(lat2) *
            Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const R = 6371; // Radius of the Earth in km
  const distance = R * c; // Distance in km

  return distance;
};

// /data/search route
router.post('/search', (req, res) => {
  const filters = req.body;  // The search filters sent by the client
  const { page = 1, limit = 25 } = req.query;  // Default page 1, limit 25
  const games = get_games_db();  // Get all games from the database
  
  try {
    // Apply filters to the games list
    const filteredGames = filterGames(games, filters);

    // Pagination logic
    const totalItems = filteredGames.length;
    const totalPages = Math.ceil(totalItems / limit);
    const startIndex = (page - 1) * limit;
    const endIndex = startIndex + limit;

    // Get the games for the current page
    const paginatedGames = filteredGames.slice(startIndex, endIndex);

    // Return the response
    res.status(200).json({
      games: paginatedGames,
      pagination: {
        currentPage: page,
        totalPages: totalPages,
        totalItems: totalItems
      }
    });
  } catch (error) {
    console.error('Error filtering games:', error);
    res.status(500).json({ message: 'Something went wrong on the server. Please try again later.' });
  }
});

module.exports = router;

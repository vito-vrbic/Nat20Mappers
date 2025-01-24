const express = require('express');
const router = express.Router();
const { get_games_db } = require('../packages/server_load'); // Assume this gets the games from the database

// Helper function to filter games based on search criteria
const filterGames = (games, filters) => {
  console.log('Filtering games with the following filters:', filters); // Debug: Display filters

  return games.filter(game => {
    let isMatch = true;

    // Check if gameTitle matches or is null
    if (filters.gameTitle && !game.title.toLowerCase().includes(filters.gameTitle.toLowerCase())) {
      console.log(`Game "${game.title}" does not match the gameTitle filter.`); // Debug: Game title mismatch
      isMatch = false;
    }

    // Check if gameType matches
    if (filters.gameType && filters.gameType.toLowerCase() !== 'all types' && game.type.toLowerCase() !== filters.gameType.toLowerCase()) {
      console.log(`Game "${game.title}" does not match the gameType filter.`); // Debug: Game type mismatch
      isMatch = false;
    }

    // Check if includeFullGames is true (if game is complete)
    if (filters.includeFullGames !== undefined && game.status !== 'complete' && !filters.includeFullGames) {
      console.log(`Game "${game.title}" does not match the includeFullGames filter.`); // Debug: Full game mismatch
      isMatch = false;
    }

    if (filters.applicationRequired !== undefined && game.applicationRequired !== filters.applicationRequired) {
      console.log(`Game "${game.title}" is not allowed based on the applicationRequired filter.`); // Debug: Application not allowed
      isMatch = false;
    }

    // Check if includeUserMadeGames is true (if it is, we want to include user-made games)
    if (!filters.includeUserMadeGames && game.createdBy === 'user') {
      console.log(`Game "${game.title}" is user-made, and user-made games are not included in the filter.`); // Debug: Including user-made game
      isMatch = false;
    }

    // Check if includeBusinessMadeGames is true (if it is, we want to include business-made games)
    if (!filters.includeBusinessMadeGames && game.createdBy === 'business') {
      console.log(`Game "${game.title}" is business-made, and business-made games are not included in the filter.`); // Debug: Including business-made game
      isMatch = false;
    }
    // Check if gameAvailability matches
    if (filters.gameAvailability && filters.gameAvailability !== 'All Games' && game.availability !== filters.gameAvailability) {
      console.log(`Game "${game.title}" does not match the gameAvailability filter.`); // Debug: Game availability mismatch
      isMatch = false;
    }

    // Check if mapLocation matches within radius (if radius is set)
    if (filters.mapLocation && filters.radius) {
      const distance = calculateDistance(game.location, filters.mapLocation);
      console.log(`Game "${game.title}" is located at ${game.location.lat}, ${game.location.lng}. Distance to user: ${distance} km.`); // Debug: Distance calculation
      if (distance > filters.radius) {
        console.log(`Game "${game.title}" is outside the specified radius of ${filters.radius} km.`); // Debug: Radius mismatch
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
router.post('/search', async (req, res) => {
  console.log('Received search request with body:', req.body); // Debug: Request body

  try {
    const filters = req.body;  // The search filters sent by the client
    const { page = 1, limit = 25 } = req.query;  // Default page 1, limit 25
    console.log(`Page: ${page}, Limit: ${limit}`); // Debug: Pagination values

    // Retrieve all games from the database
    const games = await get_games_db();  // Assuming this is an asynchronous function that fetches games
    console.log(`Fetched ${games.length} games from the database.`); // Debug: Number of games fetched

    // Apply filters to the games list
    const filteredGames = filterGames(games, filters);
    console.log(`Filtered down to ${filteredGames.length} games after applying filters.`); // Debug: Number of filtered games

    // Pagination logic
    const totalItems = filteredGames.length;
    const totalPages = Math.ceil(totalItems / limit);
    const startIndex = (page - 1) * limit;
    const endIndex = startIndex + limit;

    // Get the games for the current page
    const paginatedGames = filteredGames.slice(startIndex, endIndex);
    console.log(`Returning games from index ${startIndex} to ${endIndex}.`); // Debug: Pagination slice info

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

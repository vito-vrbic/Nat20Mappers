const express = require('express');
const router = express.Router();

// Route to fetch the hosted games for a user
router.get('/created', (req, res) => {
  try {
    // Hardcoded array of 4 games
    const games = [
      {
        "id": "gameId123",
        "title": "Dungeon Adventure",
        "type": "online",
        "location": null,
        "timezone": "UTC",
        "availability": "public",
        "createdBy": "business",
        "applicationRequired": true,
        "complexity": "medium",
        "estimatedLength": "2 hours",
        "startTimestamp": "2025-01-15T18:00:00Z",
        "description": "A thrilling dungeon crawl for adventurers of all levels.",
        "pravilnik": "Players must have basic understanding of Dungeons & Dragons rules.",
        "requiresForm": true,
        "formQuestions": {
          "questions": "What experience do you have with tabletop RPGs?"
        },
        "currentPlayerCount": "3",
        "maxPlayerCount": "6",
        "communicationChannel": "Discord",
        "isHomebrew": false
      },
      {
        "id": "gameId124",
        "title": "Mystery Mansion",
        "type": "local",
        "location": { "lat": 45.813, "lng": 15.978 },
        "timezone": "UTC",
        "availability": "private",
        "createdBy": "user",
        "applicationRequired": false,
        "complexity": "high",
        "estimatedLength": "3 hours",
        "startTimestamp": "",
        "description": "Uncover the secrets of the old mansion in this local game.",
        "pravilnik": "Players must work in teams to solve the mystery.",
        "requiresForm": false,
        "formQuestions": {},
        "currentPlayerCount": "2",
        "maxPlayerCount": "4",
        "communicationChannel": "Zoom",
        "isHomebrew": true
      },
      {
        "id": "gameId125",
        "title": "Galactic Warfare",
        "type": "online",
        "location": null,
        "timezone": "UTC",
        "availability": "public",
        "createdBy": "business",
        "applicationRequired": true,
        "complexity": "high",
        "estimatedLength": "4 hours",
        "startTimestamp": "2025-02-01T10:00:00Z",
        "description": "A battle for supremacy in outer space.",
        "pravilnik": "Players must understand advanced strategy mechanics.",
        "requiresForm": true,
        "formQuestions": {
          "questions": "Do you have prior experience with real-time strategy games?"
        },
        "currentPlayerCount": "10",
        "maxPlayerCount": "20",
        "communicationChannel": "Discord",
        "isHomebrew": false
      },
      {
        "id": "gameId126",
        "title": "Pirates of the High Seas",
        "type": "local",
        "location": { "lat": 45.818, "lng": 15.980 },
        "timezone": "CST",
        "availability": "private",
        "createdBy": "user",
        "applicationRequired": false,
        "complexity": "medium",
        "estimatedLength": "2 hours",
        "startTimestamp": "",
        "description": "A high seas adventure, seeking treasure and glory.",
        "pravilnik": "The game requires teamwork and strategic navigation.",
        "requiresForm": false,
        "formQuestions": {},
        "currentPlayerCount": "4",
        "maxPlayerCount": "8",
        "communicationChannel": "Skype",
        "isHomebrew": true
      }
    ];

    res.status(200).json({ games });
  } catch (error) {
    console.error('Error fetching hosted games:', error);
    res.status(500).json({ message: 'Unable to fetch hosted games. Please try again later.' });
  }
});

// Route to fetch the applied games for a user
router.get('/applied', (req, res) => {
  try {
    // Hardcoded array of 4 games
    const appliedGames = [
      {
        "id": "gameId123",
        "title": "Dungeon Adventure",
        "type": "online",
        "location": null,
        "timezone": "UTC",
        "availability": "public",
        "createdBy": "business",
        "applicationRequired": true,
        "complexity": "medium",
        "estimatedLength": "2 hours",
        "startTimestamp": "2025-01-15T18:00:00Z",
        "description": "A thrilling dungeon crawl for adventurers of all levels.",
        "pravilnik": "Players must have basic understanding of Dungeons & Dragons rules.",
        "requiresForm": true,
        "formQuestions": {
          "questions": "What experience do you have with tabletop RPGs?"
        },
        "currentPlayerCount": "3",
        "maxPlayerCount": "6",
        "communicationChannel": "Discord",
        "isHomebrew": false
      },
      {
        "id": "gameId124",
        "title": "Mystery Mansion",
        "type": "local",
        "location": { "lat": 45.813, "lng": 15.978 },
        "timezone": "UTC",
        "availability": "private",
        "createdBy": "user",
        "applicationRequired": false,
        "complexity": "high",
        "estimatedLength": "3 hours",
        "startTimestamp": "",
        "description": "Uncover the secrets of the old mansion in this local game.",
        "pravilnik": "Players must work in teams to solve the mystery.",
        "requiresForm": false,
        "formQuestions": {},
        "currentPlayerCount": "2",
        "maxPlayerCount": "4",
        "communicationChannel": "Zoom",
        "isHomebrew": true
      },
      {
        "id": "gameId125",
        "title": "Galactic Warfare",
        "type": "online",
        "location": null,
        "timezone": "UTC",
        "availability": "public",
        "createdBy": "business",
        "applicationRequired": true,
        "complexity": "high",
        "estimatedLength": "4 hours",
        "startTimestamp": "2025-02-01T10:00:00Z",
        "description": "A battle for supremacy in outer space.",
        "pravilnik": "Players must understand advanced strategy mechanics.",
        "requiresForm": true,
        "formQuestions": {
          "questions": "Do you have prior experience with real-time strategy games?"
        },
        "currentPlayerCount": "10",
        "maxPlayerCount": "20",
        "communicationChannel": "Discord",
        "isHomebrew": false
      },
      {
        "id": "gameId126",
        "title": "Pirates of the High Seas",
        "type": "local",
        "location": { "lat": 45.818, "lng": 15.980 },
        "timezone": "CST",
        "availability": "private",
        "createdBy": "user",
        "applicationRequired": false,
        "complexity": "medium",
        "estimatedLength": "2 hours",
        "startTimestamp": "",
        "description": "A high seas adventure, seeking treasure and glory.",
        "pravilnik": "The game requires teamwork and strategic navigation.",
        "requiresForm": false,
        "formQuestions": {},
        "currentPlayerCount": "4",
        "maxPlayerCount": "8",
        "communicationChannel": "Skype",
        "isHomebrew": true
      }
    ];

    res.status(200).json({ games: appliedGames });
  } catch (error) {
    console.error('Error fetching applied games:', error);
    res.status(500).json({ message: 'Unable to fetch applied games. Please try again later.' });
  }
});

module.exports = router;

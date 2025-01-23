const express = require('express');
const router = express.Router();


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
        "formQuestions": [
			{"question": "What experience do you have with tabletop RPGs?"},
			{"question": "Halo"}
        ],
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
        "formQuestions": [],
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
        "formQuestions": [
		  {"question": "Do you have prior experience with real-time strategy games?"}
		  ],
        "currentPlayerCount": "10",
        "maxPlayerCount": "20",
        "communicationChannel": "Discord",
        "isHomebrew": false
      },{
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
      },{
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
      },{
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
      },{
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
      },{
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
      },{
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
      },{
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

// Route to fetch the hosted games for a user
router.get('/created', (req, res) => {
  try {
	  games;
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
      },{
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
      },{
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
      },{
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
      },{
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

router.get('/game-to-edit', (req, res) => {
	const {id} = req.query;
	
	if(!id){
		return res.status(400).json({error: 'Game id is not provided'});
	}
	
	const filteredGames = games.filter(game => game.id === id);
	if(filteredGames.length === 0){
		return res.status(404).json({error: 'No game with specified id'});
	}
	
	const gameToReturn = filteredGames[0];
	
	return res.json({game: gameToReturn});
});

router.post('/save-edit', (req, res) => {
	const editedGame = req.body;
	console.log(editedGame);
	const gameId = editedGame.id;
	const updatedGame = editedGame;
	
	const gameIndex = games.findIndex(game => game.id === gameId);
	if (gameIndex === -1) {
		return res.status(404).json({ message: "Game not found" });
	}
	games[gameIndex] = { ...games[gameIndex], ...updatedGame };
	return res.status(201).json({ message: "Game updated successfully" });
});

router.get('/game-applications', (req, res) => {
  try {
    const appliedUsers = [
      {
        "id": "gameId123",
        "userId": "12334",
		"username": "ivan",
        "answers": [
			"odgvor1",
			"odgvor2",
			"treciAnswer"
        ]
      },
	  {
        "id": "gameId123",
        "userId": "1232434",
		"username": "patrik",
        "answers": [
			"odgvor435",
			"odgvor534",
			"treciAnswer"
        ]
      },
	  {
        "id": "gameId123",
        "userId": "fdg2434",
		"username": "patrdfsafasdfik",
        "answers": [
			"odgvor435",
			"odgvor534",
			"treciAnswer"
        ]
      },
	  {
        "id": "gameId123",
        "userId": "12afd34",
		"username": "pahgdfgjhgfjtrik",
        "answers": [
			"odgvor435",
			"odgvor534",
			"treciAnswer"
        ]
      },
	  {
        "id": "gameId123",
        "userId": "12afd34",
		"username": "pahgdfgjhgfjtrik",
        "answers": []
      }
      
    ];
	const id = req.query;
	console.log(id);
	const filteredUsers = id
      ? appliedUsers.filter((appliedUsers) => appliedUsers.id === id)
      : appliedUsers;

	
    res.status(200).json({ appliedUsers: appliedUsers });
  } catch (error) {
    console.error('Error fetching applied games:', error);
    res.status(500).json({ message: 'Unable to fetch applied games. Please try again later.' });
  }
});

router.post('/application-decision', (req, res) => {
	console.log(req.body);
	res.status(201).json({ message: 'Uspjesno' });
});

module.exports = router;

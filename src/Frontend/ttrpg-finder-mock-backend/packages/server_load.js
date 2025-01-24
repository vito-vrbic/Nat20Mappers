const fs = require('fs');

// Variable to store in-memory users and games
let users = [];
let games = [];

// Function to load databases from the JSON files into memory
const load_dbs = () => {
  try {
    // Load users from the 'users.json' file
    const userData = fs.readFileSync('./database/users.json', 'utf8');
    users = JSON.parse(userData);
    console.log('Users data loaded into memory');
  } catch (error) {
    console.error('Error loading users data:', error);
    users = []; // Initialize as an empty array in case of error
  }

  try {
    // Load games from the 'games.json' file
    const gameData = fs.readFileSync('./database/games.json', 'utf8');
    games = JSON.parse(gameData);
    console.log('Games data loaded into memory');
  } catch (error) {
    console.error('Error loading games data:', error);
    games = []; // Initialize as an empty array in case of error
  }
};

// Getter function for users
const get_user_db = () => users;

// Getter function for games
const get_games_db = () => games;

module.exports = {
  load_dbs,
  get_user_db,
  get_games_db
};

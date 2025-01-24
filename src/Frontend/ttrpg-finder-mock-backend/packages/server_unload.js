const fs = require('fs');

// Function to save users data back to the JSON file on server shutdown
const save_user_db = (users) => {
  try {
    // Convert users array into JSON format with indentation
    fs.writeFileSync('./database/users.json', JSON.stringify(users, null, 2), 'utf8');
    console.log('Users data saved to file');
  } catch (error) {
    console.error('Error saving users data:', error);
  }
};

// Export the save function
module.exports = {
  save_user_db : save_user_db
};

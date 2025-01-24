// REGION: Server pseudomacros
const port = 5000;
// REGION-END: Server pseudomacros

// REGION: Packages
const express = require('express');
const app = express();
const { load_dbs, get_user_db, get_games_db } = require('./packages/server_load');
const { save_user_db } = require('./packages/server_unload');
// REGION-END: Packages

// REGION: Routes
const authRoutes = require('./routes/auth_routes');
const dataRoutes = require('./routes/data_routes');
const gameRoutes = require('./routes/game_routes');
// REGION-END: Routes

// REGION: Necessary
load_dbs();
app.use(express.json());
// REGION-END: Necessary

// REGION: Route paths
app.use('/auth', authRoutes);
app.use('/data', dataRoutes);
app.use('/games', gameRoutes)
// REGION-END: Route paths

// REGION: Server start
app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});
// REGION-END: Server start

// REGION: Server end
process.on('SIGINT', () => {
  console.log('Server is shutting down...');
  
  const users = get_user_db(); // Get users from in-memory storage
  save_user_db(users); // Save data to the file
  
  process.exit(); // Exit the process
});
// REGION-END: Server end
import React, { useState } from 'react';
import '../styles/Dashboard.css';
import '../styles/GameContainer.css';
import { useAuth } from '../utils/AuthContext'; // Importing the custom hook from AuthContext
import plusSymbol from '../assets/plus-symbol-button.png'; // Import the Create New Game button icon
import wifiIcon from '../assets/wi-fi.png'; // Import the Online Game icon
import homeIcon from '../assets/home.png'; // Import the Private Game icon
import buildingIcon from '../assets/building.png'; // Import the Business Game icon

const Dashboard = () => {
  const { user } = useAuth(); // Use the useAuth hook to get authentication state, user
  const [games, setGames] = useState([]); // Used for testing

  return (
    <>
      <div className="Dashboard">
        {user.role === 'private' && (
          <div className="List-of-my-games">
            <h1>List of applied games:</h1>
            <div className="Applied-games-allign">
              <ul>
                {games
                  .filter(game => game.applicationRequired !== false)
                  .map((game, index) => (
                    <li key={index} className="Game-container">
                      <div className="Left-group">
                        {game.type.toLowerCase() === "online" && <img src={wifiIcon} alt="Online Game" />}
                        {game.type.toLowerCase() === "local" && game.createdBy === "user" && (
                          <img src={homeIcon} alt="Private Game" />
                        )}
                        {game.type.toLowerCase() === "local" && game.createdBy === "business" && (
                          <img src={buildingIcon} alt="Business Game" />
                        )}
                        {game.createdBy.charAt(0).toUpperCase() + game.createdBy.slice(1) + " game: " + game.title}
                      </div>
                      <div className="Right-group">
                        <div>Status</div>
                      </div>
                    </li>
                  ))}
              </ul>
            </div>
          </div>
        )}
        <div className="List-of-my-games">
          <h1>List of my games:</h1>
          <div className="My-games-allign">
            <ul>
              {games.map((game, index) => (
                <li key={index} className="Game-container">
                  <div className="Left-group">
                    {game.type.toLowerCase() === "online" && <img src={wifiIcon} alt="Online Game" />}
                    {game.type.toLowerCase() === "local" && game.createdBy === "user" && (
                      <img src={homeIcon} alt="Private Game" />
                    )}
                    {game.type.toLowerCase() === "local" && game.createdBy === "business" && (
                      <img src={buildingIcon} alt="Business Game" />
                    )}
                    {game.createdBy.charAt(0).toUpperCase() + game.createdBy.slice(1) + " game: " + game.title}
                  </div>
                  <div className="Right-group">
                    <button className="Join-button">Incoming Requests</button>
                    <button className="More-info-button">Edit</button>
                  </div>
                </li>
              ))}
            </ul>
            <button className="Create-new-game">
              <img src={plusSymbol} alt="Create New Game Button" />
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Dashboard;

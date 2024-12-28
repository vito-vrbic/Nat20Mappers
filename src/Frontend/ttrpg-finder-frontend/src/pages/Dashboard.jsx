import React, { useState, useEffect} from 'react';
import '../styles/Dashboard.css';
import '../styles/GameContainer.css';
import MapComponent from '../components/search/MapComponent'; // Import the MapComponent
import axios from 'axios';
import { useAuth } from '../utils/AuthContext'; // Importing the custom hook from AuthContext
import { use } from 'react';
import CreateNewGame from '../components/create game/CreateNewGame';

const Dashboard = () => {
  
  const { isAuthenticated, user, logout } = useAuth(); // Use the useAuth hook to get authentication state, user, and logout function
  const [games, setGames] = useState([]); //Used for testing
  const [showForm, setShowForm] = useState(false); // Default state for form visibility: flase = form not seen, true = form seen

  const toggleForm = () =>{
    setShowForm(!showForm);
  }

  return (
  <>
  <div className="Dashboard">
    {user.role === 'private' && (<div className='List-of-my-games'>
      <h1>List of applied games:</h1>
      <div className='Applied-games-allign'>
      <ul>
          {games.filter(game => game.applicationRequired !== false).map((game, index) => (
            <li key={index} className='Game-container'>
              <div className='Left-group'>
              {game.type.toLowerCase() === "online" && <img src="./src/assets/wi-fi.png" alt="Online Game" />}
              {game.type.toLowerCase() === "local" && game.createdBy === "user" && <img src="./src/assets/home.png" alt="Private Game" />}
              {game.type.toLowerCase() === "local" && game.createdBy === "business" && <img src="./src/assets/building.png" alt="Business Game"/>}
              {game.createdBy.charAt(0).toUpperCase() + game.createdBy.slice(1) + " game: " + game.title}
              </div>
              <div className='Right-group'>
              <div>Status</div>
              </div>
            </li>
          ))
        }
      </ul>
      </div>
    </div>)}
    <div className='List-of-my-games'>
      <h1>List of my games:</h1>
      <div className='My-games-allign'>
      <ul>
          {games.map((game, index) => (
            <li key={index} className='Game-container'>
              <div className='Left-group'>
              {game.type.toLowerCase() === "online" && <img src="./src/assets/wi-fi.png" alt="Online Game" />}
              {game.type.toLowerCase() === "local" && game.createdBy === "user" && <img src="./src/assets/home.png" alt="Private Game" />}
              {game.type.toLowerCase() === "local" && game.createdBy === "business" && <img src="./src/assets/building.png" alt="Business Game"/>}
              {game.createdBy.charAt(0).toUpperCase() + game.createdBy.slice(1) + " game: " + game.title}
              </div>
              <div className='Right-group'>
              <button className='Join-button'>Incoming Requests</button>
              <button className='More-info-button'>Edit</button>
              </div>
            </li>
          ))
        }
      </ul>
      <button className='Create-new-game' onClick={toggleForm}><img src="./src/assets/plus-symbol-button.png" alt="Create New Game Button"/></button>
      </div>
    </div>
  </div>

  {showForm && (<CreateNewGame onClose={toggleForm}></CreateNewGame>)}
  </>
  )
}

export default Dashboard
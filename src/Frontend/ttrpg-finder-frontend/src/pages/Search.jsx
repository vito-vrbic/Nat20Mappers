import React, { useState, useEffect} from 'react';
import '../styles/Search.css';
import '../styles/GameContainer.css';
import axios from 'axios';
import { useAuth } from '../utils/AuthContext'; // Importing the custom hook from AuthContext



const Search = () => {

  const { isAuthenticated, user, logout } = useAuth(); // Use the useAuth hook to get authentication state, user, and logout function
  
  const [games, setGames] = useState([]); //USed for testing
  
  //Get data from dummy database
  useEffect(() => {
    axios.get('http://localhost:5000/api/games')
      .then(response => {
        console.log('Fetched data:', response.data.games); 
      setGames(response.data.games)})
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  return (
    <>
    <div className='List-of-games'>
      <h1>List of active games:</h1>
      <ul>
          {(user ? games : games.filter(game => game.availability === "public")).map((game, index) => (
            <li key={index} className='Game-container'>
              <div className='Left-group'>
              {game.type.toLowerCase() === "online" && <img src="./src/assets/wi-fi.png" alt="Online Game" />}
              {game.type.toLowerCase() === "local" && game.createdBy === "user" && <img src="./src/assets/home.png" alt="Private Game" />}
              {game.type.toLowerCase() === "local" && game.createdBy === "business" && <img src="./src/assets/building.png" alt="Business Game"/>}
              {game.createdBy.charAt(0).toUpperCase() + game.createdBy.slice(1) + " game: " + game.title}
              </div>
              <div className='Right-group'>
              {user?.role === 'private' && <button className='Join-button'>JOIN</button>}
              <button className='More-info-button'><img src="./src/assets/more.png" alt="More Info"/></button>
              </div>
            </li>
          ))
        }
      </ul>
    </div>
    </>
  )
}

export default Search
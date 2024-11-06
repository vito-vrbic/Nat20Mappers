import React, { useState, useEffect} from 'react';
import '../styles/Search.css';
import '../styles/GameContainer.css';
import axios from 'axios';
import { useAuth } from '../utils/AuthContext'; // Importing the custom hook from AuthContext
import { NavLink, useNavigate } from 'react-router-dom';



const Search = () => {

  const { isAuthenticated, user, logout } = useAuth(); // Use the useAuth hook to get authentication state, user, and logout function
  const navigate = useNavigate(); // Using navigate hook to programmatically redirect
  
  const [games, setData] = useState([]); //USed for testing
  
  //Get data from dummy database
  useEffect(() => {
    axios.get('http://localhost:5000/api/db')
      .then(response => {
        console.log('Fetched data:', response.data.games); 
      setData(response.data.games)})
      .catch(error => console.error('Error fetching data:', error));
  }, []);


  //Simulates when user is not suppost to go where it isnt suppost to be
  useEffect(() => {
    checkIfUserIsNotLogedIn();
  }, []);
  
  //Simulates when user is not suppost to go where it isnt suppost to be
  function checkIfUserIsNotLogedIn(){
    if (!isAuthenticated) {
      navigate('/');
    }
  };

  return (
    <>
    <div className='Search-bar'>
      <input type='text' placeholder='Search for a game name...'/>
      <button><img src="./src/assets/magnifying-glass.png" alt="Search Button" /></button>
      <button><img src="./src/assets/filter.png" alt="Filter Butotn" /></button>
    </div>
    <div className='List-of-games'>
      <h1>List of active games:</h1>
      <ul>
          {games.map((game, index) => (
            <li key={index} className='Game-container'>
              <div className='Left-group'>
              {game.type.toLowerCase() === "online" && <img src="./src/assets/wi-fi.png" alt="Online Game" />}
              {game.type.toLowerCase() === "private" && <img src="./src/assets/home.png" alt="Private Game" />}
              {game.type.toLowerCase() === "business" && <img src="./src/assets/building.png" alt="Business Game"/>}
              {game.type + " game: " + game.name}
              </div>
              <div className='Right-group'>
              {user.role === 'private' && <button className='Join-button'>JOIN</button>}
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
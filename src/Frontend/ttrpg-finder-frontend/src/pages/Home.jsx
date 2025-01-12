import React, { useState, useEffect} from 'react';
import { useAuth } from '../context/AuthContext'; 
import { useNavigate } from 'react-router-dom';
import '../assets/styles/Home.css';
import '../assets/styles/GameContainer.css';
import welcome_picture from '../assets/images/welcome-picture.png';  // Import the image

const Home = () => {

  const { isAuthenticated, user, logout } = useAuth(); // Use the useAuth hook to get authentication state, user, and logout function
  const navigate = useNavigate(); // Using navigate hook to programmatically redirect

  //Simulates when user is not suppost to go where it isnt suppost to be
  useEffect(() => {
    checkIfUserIsNotLogedIn();
  }, []);


  //Simulates when user is not suppost to go where it isnt suppost to be
  function checkIfUserIsNotLogedIn(){
    if (isAuthenticated) {
      navigate('/dashboard'); // Redirect to dashboard if authenticated
    } else {
      navigate('/'); // Redirect to home if not authenticated
    }
  };

  return (
    <>
    <div className='Home-page'> 
      <div className='Welcome-text'>
        <h1>Welcome to TTRPG Finder!</h1>
        <h2>"Your next session is just a click away!"</h2> 
      </div>
      <img src={welcome_picture} alt="Welcome picture"></img>
    </div>
    <div className='Info-text'>
      <div className='Purpose-text'>
      <p>With the rising popularity of tabletop RPGs, many newcomers and experienced players are facing the problem of being unable to find an appropriate group to play with. An essential part of the tabletop experience.</p>
      <p>That's why we are happy to present you with TTRPG Finder, the one place to find and organize games!</p>
      </div>
      <h1>Key features:</h1>
        <div className='Features-conatainer'>
          <div>
          <h2>Search and join games</h2>
          <p>We are offering a simple way of finding games, <br/> simply enter the name of a game or filter with tags and find exactly the game you are looking for!</p>
          </div>
          <div>
          <h2>Create games</h2>
          <p>Are you not finding a game of your liking or need more people for your session? <br/> Create your own game and meet new people!</p>
          <p>We are offering you 2 types of games:</p>
          <ul>
            <li>Online game - Perfect for connecting with faraway people</li>
            <li>Local game  - Host the game from the comfort of your home</li>
          </ul>
          </div>
          <div>
          <h2>Promote your business</h2>
          <p>Are you a business owner who's shop specializes in tabletop RPG's? <br/>Create a business account and promote your business by hosting organized games at your shop!</p>
          </div>
        </div>
    </div>
    <div className='End-text'>
      <h1>What are you waiting for? <br/> Sign Up and start your next adventure!</h1>
    </div>
    </>
  )
}

export default Home
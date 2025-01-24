import React, { useState, useEffect} from 'react';
import '../../pages/DashboardPage.module.css'
import '../../assets/styles/GameContainer.css';
import '../../assets/styles/IncomingRequests.css';
import MapComponent from '../search/MapComponent'; // Import the MapComponent
import axios from 'axios';
import { useAuth } from '../../context/AuthContext'; // Importing the custom hook from AuthContext
import { use } from 'react';

const IncomingRequests = ({onClose,gameId}) => {
    const { isAuthenticated, user, logout } = useAuth();
    const [applications, setApplications] = useState([]);
    const [visibleAnswers, setVisibleAnswers] = useState({});

    //Allows for answers to bebome visible when the button "View form answers" is clicked
    const toggleAnswers = (userId) => {
      setVisibleAnswers((prevState) => ({
        ...prevState,
        [userId]: !prevState[userId],
      }));
    };


    //Get all data based on the gameId
    const fetchApplications = async (id) => {
      try{
        const response = await axios.get('/api/games/game-applications', {
          params: {id},
          headers: { Authorization: `Bearer ${user.token}` },
        });
        console.log("Fetched following applications: ",response.data);
        setApplications(response.data.appliedUsers);
      }catch(err){
        console.log(err);
      }
    }

      const toggleForm = () =>{
        setShowForm(false);
      }

      useEffect(() => {
        if (isAuthenticated && gameId) {
          fetchApplications(gameId); 
        }
      }, [isAuthenticated, gameId]); 
    
      //Handler for when clicking Accept or Deny buttons
      const handleDecision = async (userId, decision) => {
        try{

          //console.log(userId,decision);
          //console.log(gameId);
          const token = localStorage.getItem('authToken'); // Assuming token is saved in localStorage
          const response = await axios.post("/api/games/application-decision", {userId, gameId , decision} , {headers: { Authorization: `Bearer ${token}` }}); //Send to server
          if(response.status === 201){
            console.log("Poslana odluka na server");
          }
          else{
            console.error("NIJE USPIJELO STAVITI SE NA SERVER");
          }
        } catch(error){
          console.error("Error:", error);
        }
      };

      return (
        <div className="overlay">
          <div className="form-container">
            <h2>Incoming Requests</h2>
            <div className="applications-list">
              {/*Check if there are any incomming requests*/}
          {applications.length > 0 ? (
            <ul>
              {/*Map all requests*/}
              {applications.map((application) => (
                <li key={application.userId} className="application">
                  <div className="user-application">
                    <div className="user-info">Id: {application.userId} Username: {application.username} 
                      {/*Buttons for accepting/Denying*/}
                      <button type="button" className="cancelFormButton" onClick={() => handleDecision(application.userId, "Accept")}> Accept </button> 
                      <button type="button" className="cancelFormButton" onClick={() => handleDecision(application.userId, "Deny")}> Deny</button>
                    </div>
                    {/*If there is something in the ansers field show the View form answers button*/}
                    {application.answers.length > 0 && (
                    <>
                    {/*Show/Hide form answers*/}
                      <button type="button" className="cancelFormButton" onClick={() => toggleAnswers(application.userId)}>
                      {visibleAnswers[application.userId] ? "Hide Form Answers" : "View Form Answers"}
                      </button>
                      {visibleAnswers[application.userId] && (
                        <div className="form-answers">
                          <h4>Form Answers:</h4>
                          <ul>
                            {application.answers.map((answer, index) => (
                            <li key={index}>{answer}</li>
                            ))}
                          </ul>
                        </div>
                      )}
                    </>
                    )}
                    </div>
                </li>
              ))}
            </ul>
          ) : (
            <p>No incoming requests</p>
          )}
        </div>

            <div className="buttonsContainer">
              <button type="button" className="cancelFormButton" onClick={onClose}> Close </button>
            </div>
          </div>
        </div>
      )
}

export default IncomingRequests;
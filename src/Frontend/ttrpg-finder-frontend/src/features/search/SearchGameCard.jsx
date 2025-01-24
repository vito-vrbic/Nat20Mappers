import React, { useState } from 'react';
import styles from './SearchGameCard.module.css'; // Import the CSS module
import { useAuth } from '../../context/AuthContext';

const SearchGameCard = ({
  gameId,
  title,
  description,
  image,
  genre,
  releaseDate,
  createdBy,
  madeBy,
  complexity,
  estimatedLength,
  startTimestamp,
  pravilnik,
  requiresForm,
  currentPlayerCount,
  maxPlayerCount,
  communicationChannel,
  isHomebrew,
  formQuestions
}) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const { user, isAuthenticated } = useAuth();
  const [answers, setAnswers] = useState({}); // To store user answers to the form
  const [formError, setFormError] = useState(''); // To store form errors
  const [appliedTo,setAppliedTo] = useState(false);

  const toggleExpanded = () => {
    setIsExpanded((prevExpanded) => !prevExpanded);
    console.log("Toggled Expanded:", !isExpanded);  // For debugging, see the toggle action in the console
  };
  const handleInputChange = (question, value) => {
    setAnswers((prevAnswers) => ({
      ...prevAnswers, // Keep previous answers
      [question]: value, // Update the specific question's answer
    }));
  };

 
 // Sending an application without a form
  const applyToGame = async () => {
    if(!isAuthenticated){
      alert('In order to apply for a game you have to be logged in (as a private user)');
      console.log("User isn't authenticated");
      return;
    }
    if(user){
      if(user.role==="business"){
        alert('In order to apply for a game you have to be logged in (as a private user)');
        console.log("User is signed in with a business account");
        return;
      }
    }
    try {
      const response = await fetch('/auth/apply', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          gameId,       // The ID of the game being applied to
          userId: user.id, // The authenticated user's ID
          questions: answers// Send questions if there is a form
        }),
      });
  
      if (response.ok) {
        const result = await response.json();
        console.log('Application successful:', result);
        setAppliedTo(true);
        alert('You have successfully applied to the game! Wait for the hosts response.');
      } else {
        console.error('Application failed:', response.statusText);
        alert('Failed to apply to the game. Please try again.');
      }
    } catch (error) {
      console.error('Error occurred while applying:', error);
      alert('An error occurred. Please try again later.');
    }
  };
  


  return (
    <div 
      className={`${styles.gameCard} ${isExpanded ? styles.expanded : ''}`} 
      
    >
      <div className={styles.gameSummary}>
        {image && <img src={image} alt={`${title} cover`} className={styles.gameImage} />}
        <div className={styles.gameDetails}>
          <h3>{title}</h3>
          <p>{description}</p>
        </div>
      </div>
      <button 
        className={styles.expandButton} 
        onClick={toggleExpanded}>
        {isExpanded ? 'Minimize' : 'Expand'}
      </button>
      {/* Conditionally render extra details based on isExpanded state */}
      {isExpanded && (
        <div className={styles.gameExtraDetails}>
          {madeBy && createdBy==="business" &&<p><strong>Made by: </strong> <a className={styles.link} href={`user/${madeBy}`}>{madeBy}</a> 💼</p>}
          {madeBy && createdBy!=="business" && <p><strong>Made by: </strong> {madeBy} 🕵️‍♂️🔒</p>}
          {complexity && <p><strong>Complexity:</strong> {complexity}</p>}
          {estimatedLength && <p><strong>Estimated Length:</strong> {estimatedLength}</p>}
          {startTimestamp && <p><strong>Start Time:</strong> {new Date(startTimestamp).toLocaleString()}</p>}
          {pravilnik && <p><strong>Rules:</strong> {pravilnik}</p>}
          <p><strong>Players:</strong> {currentPlayerCount}/{maxPlayerCount}</p>
          {communicationChannel && <p><strong>Communication Channel:</strong> {communicationChannel}</p>}
          <p><strong>Homebrew:</strong> {isHomebrew ? 'Yes' : 'No'}</p>
          {requiresForm && <p><strong>Form Required:</strong> Yes</p>}
          {!requiresForm  && <button className={styles.applyButton}onClick={applyToGame}>Apply to Game</button>}
          {requiresForm &&(
  <form
    className={styles.formContainer}
    onSubmit={(e) => {
      e.preventDefault(); // Prevent default form submission behavior
      applyToGame();
    }}
  >
    {/* Iterate over formQuestions array */}
    {Array.isArray(formQuestions) &&
      formQuestions.map((questionObj, index) => (
        <div key={index} className={styles.formQuestion}>
          <label>
            {questionObj.questions} {/* Display the question */}
            <textarea
              rows={4} // Default size of the text area
              value={answers[questionObj.questions] || ''} // Track answer for each question
              onChange={(e) => handleInputChange(questionObj.questions, e.target.value)}
              required
              className={styles.textarea} // Add custom styling
            />
          </label>
        </div>
      ))}

    {/* Display form error */}
    {formError && <p className={styles.formError}>{formError}</p>}

    {/* Submit button */}
    <button type="submit">Apply to Game</button>
  </form>
)}
        </div>
      )}
    </div>
  );
};

export default SearchGameCard;

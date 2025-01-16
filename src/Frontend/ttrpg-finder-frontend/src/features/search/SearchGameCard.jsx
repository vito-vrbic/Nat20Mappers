import React, { useState } from 'react';
import styles from './SearchGameCard.module.css'; // Import the CSS module

const SearchGameCard = ({
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

  const toggleExpanded = () => {
    setIsExpanded((prevExpanded) => !prevExpanded);
    console.log("Toggled Expanded:", !isExpanded);  // For debugging, see the toggle action in the console
  };

  return (
    <div 
      className={`${styles.gameCard} ${isExpanded ? styles.expanded : ''}`} 
      onClick={toggleExpanded}
    >
      <div className={styles.gameSummary}>
        {image && <img src={image} alt={`${title} cover`} className={styles.gameImage} />}
        <div className={styles.gameDetails}>
          <h3>{title}</h3>
          <p>{description}</p>
        </div>
      </div>

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
          {!requiresForm && <button onClick={console.log("Applying no App")}>Apply to Game</button>}
          {requiresForm && (<div className={styles.formContainer}>
            {Object.entries(formQuestions.questions).map(([question],index) => (
              <div key={index} className={styles.formQuestion}>
                <label>
                  {question}
                  <input
                    type="text"
                    value={answers[question] || ''}
                    onChange={(e)=>handleInputChange(question,e.target.value)}
                  />
                </label>
              </div>
            ))}
            {formError && <p className={styles.formError}>{formError}</p>}
              <button onClick={console.log("Applying with App")}>Apply to Game</button>
            </div>)
          }
        </div>
      )}
    </div>
  );
};

export default SearchGameCard;

import React, { useState } from 'react';
import '../../styles/Search.css';

const SearchGameCard = ({
  title,
  description,
  image,
  genre,
  releaseDate,
  complexity,
  estimatedLength,
  startTimestamp,
  pravilnik,
  requiresForm,
  currentPlayerCount,
  maxPlayerCount,
  communicationChannel,
  isHomebrew
}) => {
  const [isExpanded, setIsExpanded] = useState(false);

  const toggleExpanded = () => {
    setIsExpanded((prevExpanded) => !prevExpanded);
    console.log("Toggled Expanded:", !isExpanded);  // For debugging, see the toggle action in the console
  };

  return (
    <div className={`game-card ${isExpanded ? "expanded" : ""}`} onClick={toggleExpanded}>
      <div className="game-summary">
        {image && <img src={image} alt={`${title} cover`} className="game-image" />}
        <div className="game-details">
          <h3>{title}</h3>
          <p>{description}</p>
        </div>
      </div>

      {/* Conditionally render extra details based on isExpanded state */}
      {isExpanded && (
        <div className="game-extra-details">
          {complexity && <p><strong>Complexity:</strong> {complexity}</p>}
          {estimatedLength && <p><strong>Estimated Length:</strong> {estimatedLength}</p>}
          {startTimestamp && <p><strong>Start Time:</strong> {new Date(startTimestamp).toLocaleString()}</p>}
          {pravilnik && <p><strong>Rules:</strong> {pravilnik}</p>}
          <p><strong>Players:</strong> {currentPlayerCount}/{maxPlayerCount}</p>
          {communicationChannel && <p><strong>Communication Channel:</strong> {communicationChannel}</p>}
          <p><strong>Homebrew:</strong> {isHomebrew ? 'Yes' : 'No'}</p>
          {requiresForm && <p><strong>Form Required:</strong> Yes</p>}
        </div>
      )}
    </div>
  );
};

export default SearchGameCard;

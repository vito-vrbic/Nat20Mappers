import React, { useState } from 'react';
import '../../styles/Search.css';

const SearchGameCard = ({ title, description, image, genre, releaseDate }) => {
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
          {genre && <p><strong>Genre:</strong> {genre}</p>}
          {releaseDate && <p><strong>Release Date:</strong> {releaseDate}</p>}
        </div>
      )}
    </div>
  );
};

export default SearchGameCard;

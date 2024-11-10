// GameDetailsModal.jsx
import React from 'react';
import '../../styles/Search.css';

const GameDetailsModal = ({ game, onClose }) => {
  if (!game) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button className="close-button" onClick={onClose}>X</button>
        <h2>{game.title}</h2>
        {game.image && <img src={game.image} alt={`${game.title} cover`} className="modal-image" />}
        <p><strong>Description:</strong> {game.description}</p>
        {game.genre && <p><strong>Genre:</strong> {game.genre}</p>}
        {game.releaseDate && <p><strong>Release Date:</strong> {game.releaseDate}</p>}
        {/* Additional details can be displayed here */}
      </div>
    </div>
  );
};

export default GameDetailsModal;

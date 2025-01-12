import React from 'react';
import '../../pages/SearchPage.module.css';  // Make sure your styles are correctly imported

const GameDetailsModal = ({ game, onClose }) => {
  if (!game) return null;

  const handleClose = (e) => {
    e.stopPropagation();  // Prevents the modal from closing when clicking inside
    onClose();             // Closes the modal
  };

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button
          className="close-button"
          onClick={handleClose}
          aria-label="Close modal"
        >
          &times; {/* This symbol represents the close "X" */}
        </button>
        <h2>{game.title}</h2>
        {game.image && <img src={game.image} alt={`${game.title} cover`} className="modal-image" />}
        <p><strong>Description:</strong> {game.description}</p>
        {game.genre && <p><strong>Genre:</strong> {game.genre}</p>}
        {game.releaseDate && <p><strong>Release Date:</strong> {game.releaseDate}</p>}
        {/* Additional game details can go here */}
      </div>
    </div>
  );
};

export default GameDetailsModal;

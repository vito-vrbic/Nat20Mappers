import React from 'react';
import MapComponent from './MapComponent'; // Import the MapComponent
import '../../assets/styles/Search.css';

const SearchFilters = ({
  isAuthenticated,
  gameTitle,
  setGameTitle,
  gameType,
  setGameType,
  includeFullGames,
  setIncludeFullGames,
  applicationRequired,
  setApplicationRequired,
  includeUserMadeGames,
  setIncludeUserMadeGames,
  includeBusinessMadeGames,
  setIncludeBusinessMadeGames,
  gameAvailability,
  setGameAvailability,
  mapLocation,
  setMapLocation,
  radius,
  setRadius,
  handleApplyFilters,
}) => {
  return (
    <div className="search-filters">
      <h2 className="search-filters__title">
        {isAuthenticated ? 'Advanced Game Filters' : 'Basic Game Filters'}
      </h2>

      {/* Game Title Input */}
      <label>
        Game Title:
        <input
          type="text"
          placeholder="Search by title..."
          className="search-filters__input"
          value={gameTitle}
          onChange={(e) => setGameTitle(e.target.value)}
        />
      </label>

      {/* Game Type Select */}
      <label>
        Game Type:
        <select
          className="search-filters__select"
          value={gameType}
          onChange={(e) => setGameType(e.target.value)}
        >
          <option value="All Types">All Types</option>
          <option value="Online">Online</option>
          <option value="Local">Local</option>
        </select>
      </label>

      {/* Map Location Filter */}
      {gameType === 'All Types' || gameType === 'Local' ? (
        <MapComponent mapLocation={mapLocation} setMapLocation={setMapLocation} />
      ) : null}

      {/* Radius Input */}
      {gameType === 'All Types' || gameType === 'Local' ? (
        <label>
          Radius (km):
          <input
            type="number"
            placeholder="Leave empty for no radius filter..."
            className="search-filters__input"
            value={radius}
            onChange={(e) => setRadius(e.target.value)}
            min="0"
          />
        </label>
      ) : null}

      {/* Checkbox Filters */}
      <label>
        Include Full Games:
        <input
          type="checkbox"
          className="search-filters__checkbox"
          checked={includeFullGames}
          onChange={(e) => setIncludeFullGames(e.target.checked)}
        />
      </label>

      <label>
        Application Required:
        <input
          type="checkbox"
          className="search-filters__checkbox"
          checked={applicationRequired}
          onChange={(e) => setApplicationRequired(e.target.checked)}
        />
      </label>

      <label>
        Include User-Made Games:
        <input
          type="checkbox"
          className="search-filters__checkbox"
          checked={includeUserMadeGames}
          onChange={(e) => setIncludeUserMadeGames(e.target.checked)}
        />
      </label>

      <label>
        Include Business-Made Games:
        <input
          type="checkbox"
          className="search-filters__checkbox"
          checked={includeBusinessMadeGames}
          onChange={(e) => setIncludeBusinessMadeGames(e.target.checked)}
        />
      </label>

      {/* Game Availability Select for advanced users */}
      {isAuthenticated && (
        <label>
          Game Availability:
          <select
            className="search-filters__select"
            value={gameAvailability}
            onChange={(e) => setGameAvailability(e.target.value)}
          >
            <option value="All Games">All Games</option>
            <option value="Public">Public</option>
            <option value="Private">Private</option>
          </select>
        </label>
      )}

      {/* Apply Filters Button */}
      <button className="search-filters__apply-btn" onClick={handleApplyFilters}>
        Apply Filters
      </button>
    </div>
  );
};

export default SearchFilters;

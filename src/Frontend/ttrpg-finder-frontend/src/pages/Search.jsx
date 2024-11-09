import React, { useState } from 'react';
import axios from 'axios'; // Import axios
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet';
import { useAuth } from '../utils/AuthContext';
import '../styles/Search.css';

const Search = () => {
  const { isAuthenticated, user } = useAuth(); // Destructure isAuthenticated and user from auth context

  //#region STATES
  const [gameTitle, setGameTitle] = useState('');
  const [gameType, setGameType] = useState('All Types');
  const [includeFullGames, setIncludeFullGames] = useState(false);
  const [applicationRequired, setApplicationRequired] = useState(false);
  const [includeUserMadeGames, setIncludeUserMadeGames] = useState(true);
  const [includeBusinessMadeGames, setIncludeBusinessMadeGames] = useState(true);
  const [gameAvailability, setGameAvailability] = useState('All Games');
  const [mapLocation, setMapLocation] = useState({ lat: 45.8131, lng: 15.978 }); // Default location (Zagreb)
  const [radius, setRadius] = useState('');
  const [page, setPage] = useState(1);
  const [results, setResults] = useState([]); // Store search results
  //#endregion

  //#region HANDLERS
  const handleTitleChange = (e) => setGameTitle(e.target.value);
  const handleTypeChange = (e) => setGameType(e.target.value);
  const handleIncludeFullGamesChange = (e) => setIncludeFullGames(e.target.checked);
  const handleApplicationRequiredChange = (e) => setApplicationRequired(e.target.checked);
  const handleIncludeUserMadeChange = (e) => setIncludeUserMadeGames(e.target.checked);
  const handleIncludeBusinessMadeChange = (e) => setIncludeBusinessMadeGames(e.target.checked);
  const handleAvailabilityChange = (e) => setGameAvailability(e.target.value);
  const handleRadiusChange = (e) => setRadius(e.target.value);

  // ----- MAP EVENT HANDLER -----
  const MapClickHandler = () => {
    const map = useMapEvents({
      click(e) {
        const { lat, lng } = e.latlng;
        setMapLocation({ lat, lng });
      },
    });
    return null;
  };

  // ----- APPLY FILTERS AND FETCH GAMES -----
  const handleApplyFilters = async () => {
    try {
      const token = localStorage.getItem('authToken'); // Assuming token is saved in localStorage
      const filters = {
        gameTitle,
        gameType,
        includeFullGames,
        applicationRequired,
        includeUserMadeGames,
        includeBusinessMadeGames,
        gameAvailability,
        mapLocation,
        radius,
        page,
      };

      const response = await axios.post('/api/search-game-form', filters, {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (response.status === 200) {
        const { games, pagination } = response.data;
        
        // Set the results with the fetched games
        setResults(games);

      } else {
        console.error('Failed to fetch games:', response);
      }
    } catch (error) {
      console.error('Error sending request:', error);
    }
  };

  // ----- PAGINATION HANDLER -----
  const handlePageChange = (newPage) => {
    setPage(newPage);
  };

  // Determine if the Map Filter should be shown based on gameType
  const showMapFilter = gameType === 'All Types' || gameType === 'Local';

  return (
    <div className="search">
      {/* Filter Form */}
      <div className="search-filters">
        <h2 className="search-filters__title">
          {isAuthenticated ? 'Advanced Game Filters' : 'Basic Game Filters'}
        </h2>

        {/* Game Title Input */}
        <label>Game Title:
          <input
            type="text"
            placeholder="Search by title..."
            className="search-filters__input"
            value={gameTitle}
            onChange={handleTitleChange}
          />
        </label>

        {/* Game Type Select */}
        <label>Game Type:
          <select
            className="search-filters__select"
            value={gameType}
            onChange={handleTypeChange}
          >
            <option>All Types</option>
            <option>Online</option>
            <option>Local</option>
          </select>
        </label>

        {/* Map Location Filter */}
        {showMapFilter && (
          <>
            <label>Map Location:
              <MapContainer
                center={mapLocation}
                zoom={13}
                scrollWheelZoom={false}
                style={{ width: "100%", height: "300px" }}
              >
                <TileLayer
                  url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                  attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                />
                <MapClickHandler />
                <Marker position={mapLocation}>
                  <Popup>Selected Location</Popup>
                </Marker>
              </MapContainer>
            </label>

            <label>Radius (km):
              <input
                type="number"
                placeholder="Leave empty for no radius filter..."
                className="search-filters__input"
                value={radius}
                onChange={handleRadiusChange}
                min="0"
              />
            </label>
          </>
        )}

        {/* Checkbox Filters */}
        <label>Include Full Games:
          <input
            type="checkbox"
            className="search-filters__checkbox"
            checked={includeFullGames}
            onChange={handleIncludeFullGamesChange}
          />
        </label>

        <label>Application Required:
          <input
            type="checkbox"
            className="search-filters__checkbox"
            checked={applicationRequired}
            onChange={handleApplicationRequiredChange}
          />
        </label>

        <label>Include User-Made Games:
          <input
            type="checkbox"
            className="search-filters__checkbox"
            checked={includeUserMadeGames}
            onChange={handleIncludeUserMadeChange}
          />
        </label>

        <label>Include Business-Made Games:
          <input
            type="checkbox"
            className="search-filters__checkbox"
            checked={includeBusinessMadeGames}
            onChange={handleIncludeBusinessMadeChange}
          />
        </label>

        {/* Game Availability Select for advanced users */}
        {isAuthenticated && (
          <label>Game Availability:
            <select
              className="search-filters__select"
              value={gameAvailability}
              onChange={handleAvailabilityChange}
            >
              <option>All Games</option>
              <option>Public</option>
              <option>Private</option>
            </select>
          </label>
        )}

        {/* Apply Filters Button */}
        <button className="search-filters__apply-btn" onClick={handleApplyFilters}>
          Apply Filters
        </button>
      </div>

      {/* Search Results Section */}
      <div className="search-results">
        <h2>Search Results</h2>
        {results.length > 0 ? (
          <ul>
            {results.map((result, index) => (
              <li key={index}>
                <h3>{result.title}</h3>
                <p>{result.description}</p>
                {/* Render other result details */}
              </li>
            ))}
          </ul>
        ) : (
          <p>No results found.</p>
        )}

        {/* Pagination Controls */}
        <div className="pagination-controls">
          <button
            onClick={() => handlePageChange(page - 1)}
            disabled={page === 1} // Disable if on the first page
          >
            Previous
          </button>
          <span>Page {page}</span>
          <button onClick={() => handlePageChange(page + 1)}>Next</button>
        </div>
      </div>
    </div>
  );
};

export default Search;

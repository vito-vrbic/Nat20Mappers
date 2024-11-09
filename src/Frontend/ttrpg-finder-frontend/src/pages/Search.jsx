import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet';
import { useAuth } from '../utils/AuthContext';
import '../styles/Search.css';

const Search = () => {
  const { isAuthenticated, user } = useAuth();
  const [gameTitle, setGameTitle] = useState('');
  const [gameType, setGameType] = useState('All Types');
  const [includeFullGames, setIncludeFullGames] = useState(false);
  const [applicationRequired, setApplicationRequired] = useState(false);
  const [includeUserMadeGames, setIncludeUserMadeGames] = useState(true);
  const [includeBusinessMadeGames, setIncludeBusinessMadeGames] = useState(true);
  const [gameAvailability, setGameAvailability] = useState('All Games');
  const [mapLocation, setMapLocation] = useState({ lat: 45.8131, lng: 15.978 });
  const [radius, setRadius] = useState('');
  const [page, setPage] = useState(1);
  const [results, setResults] = useState([]);
  const [isMapVisible, setIsMapVisible] = useState(true); // Track map visibility
  const [totalPages, setTotalPages] = useState(1); // Track the total number of pages

  // Handle changes for various inputs
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

  // ----- SEARCH RESULTS HANDLER -----
  const handleApplyFilters = async () => {
    try {
      const token = localStorage.getItem('authToken');
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
        setResults(response.data.games); // Update results
        setTotalPages(response.data.totalPages); // Update the total number of pages
        setIsMapVisible(true); // Keep map visible after fetch
        // Optional: Reset gameType or other logic if required after fetching
        if (response.data.games.length > 0 && gameType !== 'All Types') {
          setGameType('All Types'); // Adjust the gameType to ensure map visibility
        }
      } else {
        console.error('Failed to fetch games:', response);
      }
    } catch (error) {
      console.error('Error sending request:', error);
    }
  };

  // ----- PAGINATION HANDLER -----
  const handlePageChange = (newPage) => {
    if (newPage > 0 && newPage <= totalPages) {
      setPage(newPage); // Update the page number
      handleApplyFilters(); // Apply filters with the new page
    }
  };

  // Determine if the Map Filter should be shown based on gameType
  const showMapFilter = gameType === 'All Types' || gameType === 'Local';

  useEffect(() => {
    // Ensure map visibility is preserved
    if (results.length > 0) {
      setIsMapVisible(true); // Ensure the map remains visible if results are present
    }
  }, [results]); // Trigger map visibility adjustment when results change

  return (
    <div className="search">
      <div className="search-filters">
        <h2 className="search-filters__title">
          {isAuthenticated ? 'Advanced Game Filters' : 'Basic Game Filters'}
        </h2>

        <label>Game Title:
          <input
            type="text"
            placeholder="Search by title..."
            className="search-filters__input"
            value={gameTitle}
            onChange={handleTitleChange}
          />
        </label>

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
        {showMapFilter && isMapVisible && (
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

        <button className="search-filters__apply-btn" onClick={handleApplyFilters}>
          Apply Filters
        </button>
      </div>

      <div className="search-results">
        <h2>Search Results</h2>
        {results.length > 0 ? (
          <ul>
            {results.map((result, index) => (
              <li key={index}>
                <h3>{result.title}</h3>
                <p>{result.description}</p>
              </li>
            ))}
          </ul>
        ) : (
          <p>No results found.</p>
        )}

        <div className="pagination-controls">
          <button
            onClick={() => handlePageChange(page - 1)}
            disabled={page === 1}
          >
            Previous
          </button>
          <span>Page {page}</span>
          <button
            onClick={() => handlePageChange(page + 1)}
            disabled={page === totalPages}
          >
            Next
          </button>
        </div>
      </div>
    </div>
  );
};

export default Search;

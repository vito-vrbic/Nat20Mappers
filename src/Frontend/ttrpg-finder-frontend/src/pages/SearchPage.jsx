import React, { useState } from 'react';
import axios from 'axios';
import { useAuth } from '../context/AuthContext';
import SearchFilters from '../features/search/SearchFilters';
import SearchResults from '../features/search/SearchResults';
import styles from './SearchPage.module.css'; // Importing CSS module

const Search = () => {
  const { isAuthenticated } = useAuth();

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
  const [results, setResults] = useState([]);

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

      const response = await axios.post('/api/data/search', filters, {
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

  return (
    <div className={styles.search}> {/* Apply the 'search' class from CSS Module */}
      {/* Filter Form */}
      <SearchFilters
        isAuthenticated={isAuthenticated}
        gameTitle={gameTitle}
        setGameTitle={setGameTitle}
        gameType={gameType}
        setGameType={setGameType}
        includeFullGames={includeFullGames}
        setIncludeFullGames={setIncludeFullGames}
        applicationRequired={applicationRequired}
        setApplicationRequired={setApplicationRequired}
        includeUserMadeGames={includeUserMadeGames}
        setIncludeUserMadeGames={setIncludeUserMadeGames}
        includeBusinessMadeGames={includeBusinessMadeGames}
        setIncludeBusinessMadeGames={setIncludeBusinessMadeGames}
        gameAvailability={gameAvailability}
        setGameAvailability={setGameAvailability}
        mapLocation={mapLocation}
        setMapLocation={setMapLocation}
        radius={radius}
        setRadius={setRadius}
        handleApplyFilters={handleApplyFilters}
      />

      {/* Search Results Section */}
      <SearchResults
        results={results}
        page={page}
        handlePageChange={handlePageChange}
      />
    </div>
  );
};

export default Search;

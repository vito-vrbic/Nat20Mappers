import React from 'react';
import SearchGameCard from './SearchGameCard';
import styles from './SearchResults.module.css';

const SearchResults = ({ results, page, handlePageChange }) => {
  // DISPLAY PAGE HANDLING
  const resultsPerPage = 3; // how many results come up on 1 page
  const startIndex = (page-1)*resultsPerPage; //index for the main result array
  const currentResults = results.slice(startIndex,startIndex+resultsPerPage); //find the displayed results of i-th page
  const totalPages = Math.ceil(results.length/resultsPerPage); //for checking if the next page button should be available
  return (
    <div className="search-results">
      <h2>Search Results</h2>
      {currentResults.length > 0 ? (
        <ul>
          {currentResults.map((result, index) => (
            <li key={index}>
              <SearchGameCard
                title={result.title}
                description={result.description}
                image={result.image}
                genre={result.genre}
                releaseDate={result.releaseDate}
                complexity={result.complexity}
                estimatedLength={result.estimatedLength}
                startTimestamp={result.startTimestamp}
                pravilnik={result.pravilnik}
                requiresForm={result.requiresForm}
                currentPlayerCount={result.currentPlayerCount}
                maxPlayerCount={result.maxPlayerCount}
                communicationChannel={result.communicationChannel}
                isHomebrew={result.isHomebrew}
              />
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
          disabled={page === 1}
        >
          Previous
        </button>
        <span>Page {page}</span>
        <button onClick={() => handlePageChange(page + 1)}
          disabled={page===totalPages || results.length===0}>Next</button>
      </div>
    </div>
  );
}; 
export default SearchResults;

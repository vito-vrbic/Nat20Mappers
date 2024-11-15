import React from 'react';
import SearchGameCard from './SearchGameCard';

const SearchResults = ({ results, page, handlePageChange }) => {
  return (
    <div className="search-results">
      <h2>Search Results</h2>
      {results.length > 0 ? (
        <ul>
          {results.map((result, index) => (
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
        <button onClick={() => handlePageChange(page + 1)}>Next</button>
      </div>
    </div>
  );
};

export default SearchResults;

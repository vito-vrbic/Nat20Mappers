import React from 'react';

const SearchResults = ({ results, page, handlePageChange }) => {
  return (
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
  );
};

export default SearchResults;

import React from 'react';

const SubmitButton = ({ loading, disabled, children }) => {
  return (
    <button className="submit" type="submit" disabled={disabled}>
      {loading ? 'Logging in...' : children}
    </button>
  );
};

export default SubmitButton;
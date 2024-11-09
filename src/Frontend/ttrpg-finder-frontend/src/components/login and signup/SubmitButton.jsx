import React from 'react';

const SubmitButton = ({ loading, disabled, children, isLoggingIn }) => {
  return (
    <button className="submit" type="submit" disabled={disabled}>
      {loading ? (isLoggingIn ? 'Logging in...' : 'Signing up...') : children}
    </button>
  );
};

export default SubmitButton;
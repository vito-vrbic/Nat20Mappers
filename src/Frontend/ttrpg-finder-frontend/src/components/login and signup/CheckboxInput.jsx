import React from 'react';

const CheckboxInput = ({ checked, onChange, label }) => {
  return (
    <div className="inputOptional">
      <input
        type="checkbox"
        checked={checked}
        onChange={onChange}
      />
      <span>{label}</span>
    </div>
  );
};

export default CheckboxInput;

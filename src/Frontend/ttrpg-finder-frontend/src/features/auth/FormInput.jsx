import React from 'react';

const FormInput = ({ label, type, value, onChange, id, placeholder }) => {
  return (
    <div className="input">
      <div className="inputTag">{label}</div>
      <input
        type={type}
        id={id}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
      />
    </div>
  );
};

export default FormInput;
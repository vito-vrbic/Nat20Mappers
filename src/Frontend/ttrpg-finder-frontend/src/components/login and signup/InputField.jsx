import React from 'react';

const InputField = ({ label, type, value, onChange, placeholder, id, onIconClick, iconSrc }) => {
  return (
    <div className='input'>
      <div className='inputTag'>{label}</div>
      <input
        type={type}
        id={id}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
      {iconSrc && (
        <img
          src={iconSrc}
          alt="Toggle visibility"
          onClick={onIconClick}
          className="toggle-pass"
        />
      )}
    </div>
  );
};

export default InputField;

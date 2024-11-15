import React from 'react';
import showPass from '../../assets/ShowPassword.png';
import hidePass from '../../assets/HidePassword.png';

const PasswordVisibilityToggle = ({ isPasswordVisible, togglePasswordVisibility }) => {
  return (
    <img
      src={isPasswordVisible ? hidePass : showPass}
      alt="Toggle Password Visibility"
      onClick={togglePasswordVisibility}
      className="toggle-pass"
    />
  );
};

export default PasswordVisibilityToggle;
//#region IMPORTS
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'; // Import axios for API requests
import '../styles/Signup.css';
import showPass from '../assets/ShowPassword.png';
import hidePass from '../assets/HidePassword.png';
import picture from '../assets/dice2.jpg'
//#endregion

// Signup Page
const Signup = () => {
  const navigate = useNavigate(); // Use to navigate after successful signup

  //#region STATES
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [role, setRole] = useState('private'); // Default role is 'private'
  const [organizationName, setOrganizationName] = useState('');
  const [isChecked, setIsChecked] = useState(false); // Checkbox for business role
  const [isPasswordVisible, setIsPasswordVisible] = useState(false);
  const [isConfirmPasswordVisible, setIsConfirmPasswordVisible] = useState(false);
  const [isLoading, setIsLoading] = useState(false); // New state for loading
  const [errorMessage, setErrorMessage] = useState(''); // State for error messages
  //#endregion

  // Handle checkbox change
  const handleCheckboxChange = (event) => {
    setIsChecked(event.target.checked);
    setRole(event.target.checked ? 'business' : 'private'); // Set role based on checkbox
  };

  // Handle password visibility toggle
  const togglePasswordVisibility = () => {
    setIsPasswordVisible(!isPasswordVisible);
  };

  const toggleConfirmPasswordVisibility = () => {
    setIsConfirmPasswordVisible(!isConfirmPasswordVisible);
  };

  /*
    FORM SUBMISSION
    -----
    Address: ./signup-submit
    Type: POST
    Header:
      Content-Type: application/json
      Authorization: Bearer {authToken} (if required for an authenticated API)
    Body:
      {
        "username": "john_doe",
        "email": "john.doe@example.com",
        "password": "password123",
        "role": "business || private"
        "organizationName": "name || null"
      }
    -----
    Expected response:
      SUCCESS (Status Code 201).
      FAILURE (400 Bad Request):
        Body: {
          "message": "Issue to showcase on frontend to user"
        }
  */
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Basic validation: check if passwords match
    if (password !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    // Set loading state to true to show loading indicator or disable button
    setIsLoading(true);
    setErrorMessage(''); // Clear previous error messages

    const userData = {
      username,
      email,
      password,
      role,
      organizationName: role === 'business' ? organizationName : null, // Include organization name only for business role
    };

    // POST request using axios to backend
    try {
      const response = await axios.post('api/signup', userData);

      if (response.status === 201) {
        navigate('/login');
      } else {
        setErrorMessage(response.data.message || 'Signup failed. Please try again.');
        setIsLoading(false);
      }
    } catch (error) {
      console.error('Signup failed', error);
      setErrorMessage(error.response?.data?.message || 'An error occurred. Please try again later.');
      setIsLoading(false);
    }
  };

  //#region RETURN
  return (
    <>
    <div className='signupContainer'>
    <div className='SignUpBox'>
      <div className="Title">Sign up</div>
      <form className='inputs' onSubmit={handleSubmit}> {/* Form submission handler */}
        <div className='input'>
          <div className='inputTag'>Username</div>
          <input
            type="text"
            id="user"
            placeholder="Enter username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className='input'>
          <div className='inputTag'>Email</div>
          <input
            type="email"
            id="E-mail"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className='input'>
          <div className='inputTag'>Password</div>
          <input
            type={isPasswordVisible ? "text" : "password"}
            id="Password"
            placeholder="Enter password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <img
            src={isPasswordVisible ? hidePass : showPass}
            alt="Toggle Password Visibility"
            onClick={togglePasswordVisibility}
            className="toggle-pass"
          />
        </div>
        <div className='input'>
          <div className='inputTag'>Confirm password</div>
          <input
            type={isConfirmPasswordVisible ? "text" : "password"}
            id="PasswordRepeat"
            placeholder="Confirm password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          <img
            src={isConfirmPasswordVisible ? hidePass : showPass}
            alt="Toggle Password Visibility"
            onClick={toggleConfirmPasswordVisibility}
            className="toggle-pass"
          />
        </div>
        <div className='inputOptional'>
          <input
            type="checkbox"
            id="orgCheckbox"
            checked={isChecked}
            onChange={handleCheckboxChange}
          />
          <span>Representing an organization?</span>
        </div>
        {isChecked && (
          <div className='input'>
            <div className='inputTag'>Organization name</div>
            <input
              id="orgName"
              type="text"
              placeholder="Enter organization name"
              value={organizationName}
              onChange={(e) => setOrganizationName(e.target.value)}
            />
          </div>
        )}
        {errorMessage && <div className="error-message">{errorMessage}</div>} {/* Show error message */}
        {/*<div className="submit">*/}
          <button type="submit" disabled={isLoading}>
            {isLoading ? 'Signing Up...' : 'Sign up'}
          </button>
        {/*</div>*/}
      </form>
    </div>
    <img className="image" src={picture} alt="picture"></img>
    </div>
  </> 
 );
  //#endregion
};

export default Signup;

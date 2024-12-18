import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import SubmitButton from '../components/login and signup/SubmitButton';
import PasswordVisibilityToggle from '../components/login and signup/PasswordVisibilityToggle';
import InputField from '../components/login and signup/InputField';
import FormInput from '../components/login and signup/FormInput';
import CheckboxInput from '../components/login and signup/CheckboxInput';
import '../styles/Signup.css';

const Signup = () => {
  const navigate = useNavigate();
  
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [role, setRole] = useState('private');
  const [organizationName, setOrganizationName] = useState('');
  const [isChecked, setIsChecked] = useState(false);
  const [isPasswordVisible, setIsPasswordVisible] = useState(false);
  const [isConfirmPasswordVisible, setIsConfirmPasswordVisible] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  // Handle checkbox change
  const handleCheckboxChange = (event) => {
    setIsChecked(event.target.checked);
    setRole(event.target.checked ? 'business' : 'private');
  };

  // Handle password visibility toggle
  const togglePasswordVisibility = () => {
    setIsPasswordVisible(!isPasswordVisible);
  };

  const toggleConfirmPasswordVisibility = () => {
    setIsConfirmPasswordVisible(!isConfirmPasswordVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Basic validation: check if passwords match
    if (password !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    setIsLoading(true);
    setErrorMessage('');

    const userData = {
      username,
      email,
      password,
      role,
      organizationName: role === 'business' ? organizationName : null,
    };

    try {
      const response = await axios.post('/api/auth/signup', userData);
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

  return (
    <div className="SignUpBox">
      <div className="Title">Sign up</div>
      <form className="inputs" onSubmit={handleSubmit}>
        {/* Username Input */}
        <FormInput
          label="Username"
          type="text"
          id="user"
          placeholder="Enter username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        {/* Email Input */}
        <FormInput
          label="Email"
          type="email"
          id="E-mail"
          placeholder="Enter email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        {/* Password Input with PasswordVisibilityToggle */}
        <div className="input">
          <InputField
            label="Password"
            type={isPasswordVisible ? 'text' : 'password'}
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter password"
            id="Password"
          />
          <PasswordVisibilityToggle
            isPasswordVisible={isPasswordVisible}
            togglePasswordVisibility={togglePasswordVisibility}
          />
        </div>

        {/* Confirm Password Input with PasswordVisibilityToggle */}
        <div className="input">
          <InputField
            label="Confirm password"
            type={isConfirmPasswordVisible ? 'text' : 'password'}
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            placeholder="Confirm password"
            id="PasswordRepeat"
          />
          <PasswordVisibilityToggle
            isPasswordVisible={isConfirmPasswordVisible}
            togglePasswordVisibility={toggleConfirmPasswordVisibility}
          />
        </div>

        {/* Checkbox Input for Organization Role */}
        <CheckboxInput
          checked={isChecked}
          onChange={handleCheckboxChange}
          label="Representing an organization?"
        />

        {/* Organization Name input if checkbox is checked */}
        {isChecked && (
          <FormInput
            label="Organization name"
            type="text"
            value={organizationName}
            onChange={(e) => setOrganizationName(e.target.value)}
            placeholder="Enter organization name"
            id="orgName"
          />
        )}

        {/* Error Message */}
        {errorMessage && <div className="error-message">{errorMessage}</div>}

        {/* Submit Button */}
        <SubmitButton loading={isLoading} disabled={isLoading}>
          Sign up
        </SubmitButton>
      </form>
    </div>
  );
};

export default Signup;

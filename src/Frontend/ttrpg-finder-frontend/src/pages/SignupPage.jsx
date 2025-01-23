import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useAuth } from '../context/AuthContext';
import SubmitButton from '../features/auth/SubmitButton';
import PasswordVisibilityToggle from '../features/auth/PasswordVisibilityToggle';
import InputField from '../features/auth/InputField';
import FormInput from '../features/auth/FormInput';
import CheckboxInput from '../features/auth/CheckboxInput';
import styles from './SignupPage.module.css';  // Import styles as a module
import { useGoogleLogin } from '@react-oauth/google'
import GoogleLoginComponent from '../features/auth/GoogleLoginBox';

const Signup = () => {
  const navigate = useNavigate();
  
  const { checkForGoogleLogin, error } = useAuth();
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

  const googleSignIn = useGoogleLogin({
    onSuccess: async (response)=>{
      try{
        const res=await axios.get("https://www.googleapis.com/oauth2/v3/userinfo", {
          headers:{            
            Authorization: `Bearer ${response.access_token}`,
          },
        });
        const userData = res.data;
        console.log(userData);
        await checkForGoogleLogin(userData, true);
        navigate('/dashboard');
      } catch (err) {
        console.log(err);
      }
    },
  });

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
    <div className={styles.SignUpBox}>
      <div className={styles.Title}>Sign up</div>
      <form className={styles.inputs} onSubmit={handleSubmit}>
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
        <div className={styles.input}>
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
        <div className={styles.input}>
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
        {errorMessage && <div className={styles.errorMessage}>{errorMessage}</div>}

        {/* Submit Button */}
        <SubmitButton loading={isLoading} disabled={isLoading}>
          Sign up
        </SubmitButton>
      </form>
      
      <div className={styles.googleLoginButton} onClick={() => googleSignIn()}>
        <div className={styles.googleLoginText}>Sign up & Log in with Google</div>
      </div>
    </div>
  );
};

export default Signup;

import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../utils/AuthContext'; // Assuming AuthContext is being used correctly
import '../styles/Login.css';
import showPass from '../assets/ShowPassword.png';
import hidePass from '../assets/HidePassword.png';
import picture from '../assets/dice.jpg'

// Login Page
const Login = () => {
  const { login } = useAuth(); // Get login function from AuthContext
  const navigate = useNavigate(); // Use to navigate after successful login

  // Define state for username, password, loading, and error messages
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isPasswordVisible, setIsPasswordVisible] = useState(false);
  const [loading, setLoading] = useState(false); // State for loading indicator
  const [error, setError] = useState(''); // State to capture error message

  // Handle password visibility toggle
  const togglePasswordVisibility = () => {
    setIsPasswordVisible(!isPasswordVisible);
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent the page from reloading

    // Clear previous error message
    setError('');
    setLoading(true); // Set loading to true when starting the login attempt

    const credentials = { username, password };
    try {
      await login(credentials); // Use login function from context

      // Redirect to dashboard on successful login
      navigate('/dashboard');
    } catch (error) {
      console.error('Login failed', error);
      setError('Invalid username or password. Please try again.'); // Set error message
      setLoading(false); // Reset loading state
    }
  };

  // Return JSX for the login form
  return (
    <>
    <div className='loginContainer'>
    <div className="LogInBox">
      <div className="Title">Log in</div>
      <form className="inputs" onSubmit={handleSubmit}> {/* Handle form submission */}
        <div className="input">
          <div className="inputTag">Username</div>
          <input
            type="text"
            id="user"
            placeholder="Enter username"
            value={username}
            onChange={(e) => setUsername(e.target.value)} // Update state on input change
          />
        </div>
        <div className="input">
          <div className="inputTag">Password</div>
          <input
            type={isPasswordVisible ? "text" : "password"} // Toggle input type based on state
            id="Password"
            placeholder="Enter password"
            value={password}
            onChange={(e) => setPassword(e.target.value)} // Update state on password change
          />
          <img
            src={isPasswordVisible ? hidePass : showPass}
            alt="Toggle Password Visibility"
            onClick={togglePasswordVisibility} // Toggle password visibility
            className="toggle-pass"
          />
        </div>
        {error && <div className="error-message">{error}</div>} {/* Display error message */}
        <div className="forgotPassword">Forgot password?</div>
        <button type="submit" className="submit" disabled={loading}>
          {loading ? 'Logging in...' : 'Log in'} {/* Display loading text */}
        </button> {/* Submit the form */}
      </form>
      <div className="gotoSignup">
        No account? <Link to="/signup">Sign up</Link>
      </div>
    </div>
    <img className="image" src={picture} alt="picture"></img>
    </div>
    </>
  );
};

export default Login;

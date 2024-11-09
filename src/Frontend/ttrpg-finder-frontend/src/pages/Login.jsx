import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../utils/AuthContext';
import FormInput from '../components/login/FormInput';
import PasswordVisibilityToggle from '../components/login/PasswordVisibilityToggle';
import SubmitButton from '../components/login/SubmitButton';
import '../styles/Login.css';

const Login = () => {
  const { login } = useAuth();
  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isPasswordVisible, setIsPasswordVisible] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const togglePasswordVisibility = () => {
    setIsPasswordVisible(!isPasswordVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!username || !password) {
      setError('Both fields are required.');
      return;
    }

    setError('');
    setLoading(true);

    const credentials = { username, password };

    try {
      await login(credentials);
      navigate('/dashboard');
    } catch (error) {
      console.error('Login failed:', error);
      setError('Invalid username or password. Please try again.');
      setLoading(false);
    }
  };

  return (
    <div className="LogInBox">
      <div className="Title">Log in</div>
      <form className="inputs" onSubmit={handleSubmit}>
        <div className="input">
          <FormInput
            label="Username"
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            id="user"
            placeholder="Enter username"
          />
        </div>

        <div className="input">
          <FormInput
            label="Password"
            type={isPasswordVisible ? 'text' : 'password'}
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            id="Password"
            placeholder="Enter password"
          />
          <PasswordVisibilityToggle
            isPasswordVisible={isPasswordVisible}
            togglePasswordVisibility={togglePasswordVisibility}
          />
        </div>

        <div className="forgotPassword">Forgot password?</div>

        {/* Submit Button with loading and disabled handling */}
        <SubmitButton loading={loading} disabled={loading || !username || !password}>
          Log in
        </SubmitButton>
      </form>

      <div className="gotoSignup">
        No account? <Link to="/signup">Sign up</Link>
      </div>
    </div>
  );
};

export default Login;

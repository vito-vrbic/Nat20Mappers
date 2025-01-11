import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../utils/AuthContext';
import FormInput from '../components/login and signup/FormInput';
import PasswordVisibilityToggle from '../components/login and signup/PasswordVisibilityToggle';
import SubmitButton from '../components/login and signup/SubmitButton';
import '../styles/Login.css';
import { useGoogleLogin } from '@react-oauth/google'
import GoogleLoginComponent from '../components/login and signup/GoogleLoginBox';
import axios from 'axios';
const clientId = "320270492524-ve80c3hmmc1108kcjemrgge0bjgigtku.apps.googleusercontent.com"

const Login = () => {
  
  const { login, checkForGoogleLogin, gerror } = useAuth();
 
  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isPasswordVisible, setIsPasswordVisible] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const googlePrijava = useGoogleLogin({
    onSuccess: async (response)=>{
      try{
        const res=await axios.get("https://www.googleapis.com/oauth2/v3/userinfo",
        {
          headers:{            
            Authorization: `Bearer ${response.access_token}`,
          },
        }
        );
        const userData = res.data;
        console.log(userData);
        try{
          await checkForGoogleLogin(userData,false);
          navigate('/dashboard');
        }
        catch(err){
          console.log("Error found");
          setLoading(false);
        }
        console.log(gerror);
      }
      catch (err){
        setError("Google login failed. Please try again with a different email.");
      }
    },
  });

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

    
      <div className="googleLoginButton" onClick={()=>googlePrijava()}><div className='googleLoginText'>Log in with Google</div></div>

      {error && <div className="error-message">{error}</div>}
      {/*<GoogleLoginComponent/>*/}
    </div>
  );
};

export default Login;

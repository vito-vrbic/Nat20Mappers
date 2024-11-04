import React from 'react'
import {Link} from 'react-router-dom';
import '../styles/Login.css'
import showPass from '../assets/ShowPassword.png'
const Login = () => {
  return (
    <>
      <div className='LogInBox'> {/*contaier for the entire form*/}
        <div className="Title">Log in</div>
        <div className='inputs'> {/*container for all the input types*/}
            <div className='input'>
              <div className='inputTag'>Username</div>
              <input type="name" id="user" placeholder='Enter username'></input>
            </div>
            <div className='input'>
              <div className='inputTag'>Password</div>
              <input type="password" id="Password" placeholder='Enter password'></input>
              <img src={showPass}></img>
            </div>
        </div>
        <div className='forgotPassword'>Forgot password?</div>
        <div className='submit'>Log in</div>
        <div className='gotoSignup'>No account? <Link to="/signup">Sign up</Link></div>
      </div>
    </>
  )
}

export default Login
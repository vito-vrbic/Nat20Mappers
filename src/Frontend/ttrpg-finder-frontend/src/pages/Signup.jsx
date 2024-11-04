import React,{useState}  from 'react'
import '../styles/Signup.css'
import showPass from '../assets/ShowPassword.png'

const Signup = () => {
  /*a simple useState that checks if the checkBox is ticked, and if so displays the additional form input*/
  const [isChecked,setIsChecked]=useState(); 
  const handleCheckboxChange = (event) =>{
    setIsChecked(event.target.checked);
  }
  return (
    <>
      <div className='SignUpBox'> {/*contaier for the entire form*/}
        <div className="Title">Sign up</div>
        <div className='inputs'> {/*container for all the input types*/}
            <div className='input'>
              <div className='inputTag'>Username</div>
              <input type="name" id="user" placeholder='Enter username'></input>
            </div>
            <div className='input'>
              <div className='inputTag'>Email</div>
              <input type="email" id="E-mail" placeholder='Enter email'></input>
            </div>
            <div className='input'>
              <div className='inputTag'>Password</div>
              <input type="password" id="Password" placeholder='Enter password'></input>
              <img src={showPass}></img>
            </div>
            <div className='input'>
              <input type="password" id="PasswordRepeat"  placeholder='Confirm password'></input>
              <img src={showPass}></img>
            </div>
            <div className='inputOptional'>
              <input type="checkbox" id="orgCheckbox" onChange={handleCheckboxChange}></input>
              <span>Representing an organization?</span>
            </div>
            {isChecked && (
                <div className='input'>
                  <div className='inputTag'>Organization name</div>
                  <input id="orgName" type="text" placeholder='Enter organization name' />
                </div>
            )}     
        </div>
        <div className='submit'>Sign up</div>
      </div>
    </>
  )
}
export default Signup
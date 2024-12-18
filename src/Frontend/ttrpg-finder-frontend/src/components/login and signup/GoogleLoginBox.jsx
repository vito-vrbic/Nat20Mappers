import {GoogleLogin} from '@react-oauth/google';


function GoogleLoginFunction(){
  return(
    <GoogleLogin
    
      onSuccess={credentialResponse => {
          console.log(credentialResponse);
        }}
      onError={() => {
          alert("Login has failed");
      }}
      text="Log in with Google"
    />
  )
}



export default GoogleLoginFunction;

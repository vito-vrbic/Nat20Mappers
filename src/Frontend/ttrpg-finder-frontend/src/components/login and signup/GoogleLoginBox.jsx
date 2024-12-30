import {GoogleLogin} from '@react-oauth/google';
import { jwtDecode } from 'jwt-decode';

var credentialResponseDecoded;

function GoogleLoginFunction(){
  return(
    <GoogleLogin
      onSuccess={credentialResponse => {
          credentialResponseDecoded=jwtDecode(credentialResponse.credential);
          console.log(credentialResponseDecoded);
        }}
      onError={() => {
          alert("Login has failed");
      }}
      text="Log in with Google"
      theme='filled_black'
    />
  )
}

export default GoogleLoginFunction;

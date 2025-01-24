// src/main.jsx

// REGION: Imports
import React from 'react';
import ReactDOM from 'react-dom/client';
import { GoogleOAuthProvider } from '@react-oauth/google';

import './assets/styles/Globals.css';

import App from './App';
// END-REGION: Imports

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <GoogleOAuthProvider clientId="320270492524-ve80c3hmmc1108kcjemrgge0bjgigtku.apps.googleusercontent.com">
      <App />
    </GoogleOAuthProvider>
  </React.StrictMode>
);
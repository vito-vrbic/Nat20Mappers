import React from 'react';
import ReactDOM from 'react-dom/client'; // Note the change here
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root')); // Create root
root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);
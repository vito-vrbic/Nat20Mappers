import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';

import Navbar from './components/Navbar';
import { AuthProvider } from './context/AuthContext';
import AppRoutes from './routes/AppRoutes';
import './assets/styles/Globals.css';

import DiceRoller from './utils/DiceRoller'; // Import DiceRoller component

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="app">
          <Navbar />
          <main className="main-content">
            <AppRoutes />
          </main>
          <DiceRoller /> {/* Include DiceRoller in your component tree */}
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;

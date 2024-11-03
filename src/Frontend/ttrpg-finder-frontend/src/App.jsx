// IMPORTS
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

// COMPONENTS
import Navbar from './components/Navbar';

// PAGES
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Search from './pages/Search';

// CSS
import './styles/Globals.css';

function App() {
  return (
    <Router> {/* Wrapping the app in Router */}
      <div className="app">
        <Navbar />

        <main className="main-content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/search" element={<Search />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
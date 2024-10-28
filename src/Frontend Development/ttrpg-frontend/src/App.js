import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom'; // Import Routes instead of Switch
import Dashboard from './pages/Dashboard';
import Home from './pages/Home';
import Signup from './pages/Signup';
import Login from './pages/Login';

const App = () => {
    return (
        <Router>
            <div>
                <Routes> {/* Use Routes instead of Switch */}
                    <Route path="/dashboard" element={<Dashboard />} /> {/* Use element prop */}
                    <Route path="/home" element={<Home />} />
                    <Route path="/signup" element={<Signup />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/" element={<Navigate to="/home" replace />} /> {/* Default route */}
                </Routes>
            </div>
        </Router>
    );
};

export default App;
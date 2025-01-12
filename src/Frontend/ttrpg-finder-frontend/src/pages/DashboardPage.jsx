import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useAuth } from '../context/AuthContext';
import CreateNewGame from '../features/create game/CreateNewGame';
import plusSymbol from '../assets/images/plus-symbol-button.png';
import wifiIcon from '../assets/images/wi-fi.png';
import homeIcon from '../assets/images/home.png';
import buildingIcon from '../assets/images/building.png';
import styles from './DashboardPage.module.css';  // Import styles as a module

const Dashboard = () => {
  const { isAuthenticated, user } = useAuth();
  const [createdGames, setCreatedGames] = useState([]); // Games created by the user
  const [appliedGames, setAppliedGames] = useState([]); // Games the user has applied for
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showForm, setShowForm] = useState(false);

  const fetchCreatedGames = async () => {
    try {
      setLoading(true);
      const response = await axios.get('/api/games/created', {
        headers: { Authorization: `Bearer ${user.token}` },
      });

      const data = Array.isArray(response.data.games) ? response.data.games : [];
      setCreatedGames(data);
      setError(null);
    } catch (err) {
      setError("Unable to fetch created games. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  const fetchAppliedGames = async () => {
    try {
      setLoading(true);
      const response = await axios.get('/api/games/applied', {
        headers: { Authorization: `Bearer ${user.token}` },
      });

      const data = Array.isArray(response.data.games) ? response.data.games : [];
      setAppliedGames(data);
      setError(null);
    } catch (err) {
      setError("Unable to fetch applied games. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (isAuthenticated) {
      fetchCreatedGames();
      fetchAppliedGames();
    }
  }, [isAuthenticated]);

  const toggleForm = () => {
    setShowForm(!showForm);
  };

  if (loading) {
    return <div className={styles.Loading}><h2>Loading games...</h2></div>;
  }

  if (error) {
    return <div className={styles.Error}><h2>{error}</h2></div>;
  }

  return (
    <>
      <div className={styles.Dashboard}>
        {user?.role === 'private' && (
          <div className={styles['List-of-my-games']}>
            <h1>List of applied games:</h1>
            <div className={styles['Applied-games-allign']}>
              <ul>
                {appliedGames.filter(game => game.applicationRequired).map((game, index) => (
                  <li key={index} className={styles['Game-container']}>
                    <div className={styles['Left-group']}>
                      {game.type.toLowerCase() === "online" && <img src={wifiIcon} alt="Online Game" />}
                      {game.type.toLowerCase() === "local" && game.createdBy === "user" && (
                        <img src={homeIcon} alt="Private Game" />
                      )}
                      {game.type.toLowerCase() === "local" && game.createdBy === "business" && (
                        <img src={buildingIcon} alt="Business Game" />
                      )}
                      {`${game.createdBy.charAt(0).toUpperCase() + game.createdBy.slice(1)} game: ${game.title}`}
                    </div>
                    <div className={styles['Right-group']}>
                      <div>Status</div>
                    </div>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        )}

        <div className={styles['List-of-my-games']}>
          <h1>List of my created games:</h1>
          <div className={styles['My-games-allign']}>
            <ul>
              {createdGames.map((game, index) => (
                <li key={index} className={styles['Game-container']}>
                  <div className={styles['Left-group']}>
                    {game.type.toLowerCase() === "online" && <img src={wifiIcon} alt="Online Game" />}
                    {game.type.toLowerCase() === "local" && game.createdBy === "user" && (
                      <img src={homeIcon} alt="Private Game" />
                    )}
                    {game.type.toLowerCase() === "local" && game.createdBy === "business" && (
                      <img src={buildingIcon} alt="Business Game" />
                    )}
                    {`${game.createdBy.charAt(0).toUpperCase() + game.createdBy.slice(1)} game: ${game.title}`}
                  </div>
                  <div className={styles['Right-group']}>
                    <button className={styles['Join-button']}>Incoming Requests</button>
                    <button className={styles['More-info-button']}>Edit</button>
                  </div>
                </li>
              ))}
            </ul>
            <button className={styles['Create-new-game']} onClick={toggleForm}>
              <img src={plusSymbol} alt="Create New Game Button" />
            </button>
          </div>
        </div>
      </div>

      {showForm && <CreateNewGame onClose={toggleForm} />}
    </>
  );
};

export default Dashboard;

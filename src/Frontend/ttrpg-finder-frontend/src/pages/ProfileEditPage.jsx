import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import styles from './ProfileEditPage.module.css'; // Importing the CSS module

const ProfileForm = () => {
  const { user, isAuthenticated, loading: authLoading } = useAuth(); // Access user from Auth context
  const [profileData, setProfileData] = useState({
    logo: '',
    companyPhone: '',
    companyDescription: '',
    companyWebsite: '',
    companyAddress: ''
  });
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  const username = user?.username;

  // Fetch existing profile data when the component mounts
  useEffect(() => {
    if (authLoading || !username) return;

    const fetchProfileData = async () => {
      try {
        const response = await axios.get(`/api/user/${username}`, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        if (response.status === 200) {
          setProfileData(response.data.data);
        } else {
          setMessage(response.data.message || 'Error fetching profile data');
        }
      } catch (error) {
        console.log('Error fetching profile:', error);
        setMessage('Error fetching profile data');
      } finally {
        setLoading(false);
      }
    };

    fetchProfileData();
  }, [authLoading, username]);

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProfileData((prevState) => ({
      ...prevState,
      [name]: value
    }));
  };

  // Handle form submission (PUT request to update profile)
  const handleSubmit = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem('authToken');
    if (!token) {
      setMessage('No token found');
      return;
    }

    try {
      const response = await axios.put(
        '/api/user/edit-profile',
        profileData,
        {
          headers: { 'Authorization': `Bearer ${token}` }
        }
      );

      if (response.data.error) {
        setMessage(response.data.message || 'Error updating profile');
      } else {
        setMessage(response.data.message || 'Profile updated successfully');
        navigate(`/user/${user.username}`);
      }
    } catch (error) {
      console.log('Error updating profile:', error);
      setMessage(error.response?.data?.message || 'Something went wrong');
    }
  };

  if (authLoading) {
    return (
      <div className={styles.loadingMessage}>
        Authenticating...
      </div>
    );
  }

  if (loading) {
    return (
      <div className={styles.loadingMessage}>
        Loading profile...
      </div>
    );
  }

  if (!username) {
    return (
      <div className={styles.loadingMessage}>
        <h1>No username provided. Please log in.</h1>
      </div>
    );
  }

  return (
    <div className={styles.profileFormContainer}>
      <h2>Edit Profile</h2>
      <form onSubmit={handleSubmit} className={styles.form}>
        <label className={styles.inputTag}>Logo:</label>
        <input
          type="text"
          name="logo"
          value={profileData.logo}
          onChange={handleChange}
          className={styles.input}
        />
        <label className={styles.inputTag}>Company Phone:</label>
        <input
          type="text"
          name="companyPhone"
          value={profileData.companyPhone}
          onChange={handleChange}
          className={styles.input}
        />
        <label className={styles.inputTag}>Company Description:</label>
        <textarea
          name="companyDescription"
          value={profileData.companyDescription}
          onChange={handleChange}
          className={styles.textarea}
        />
        <label className={styles.inputTag}>Company Website:</label>
        <input
          type="text"
          name="companyWebsite"
          value={profileData.companyWebsite}
          onChange={handleChange}
          className={styles.input}
        />
        <label className={styles.inputTag}>Company Address:</label>
        <input
          type="text"
          name="companyAddress"
          value={profileData.companyAddress}
          onChange={handleChange}
          className={styles.input}
        />
        <button type="submit" className={styles.submit}>
          Update Profile
        </button>
      </form>
      {message && <p className={styles.message}>{message}</p>}
    </div>
  );
};

export default ProfileForm;

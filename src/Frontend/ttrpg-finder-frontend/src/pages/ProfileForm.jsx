import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // React Router v6
import { useAuth } from '../utils/AuthContext';

const EditProfile = () => {
  const { user, isAuthenticated } = useAuth(); // Access user from Auth context
  const [profileData, setProfileData] = useState({
    logo: '',
    companyPhone: '',
    companyDescription: '',
    companyWebsite: '',
    companyAddress: ''
  });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  // Fetch existing profile data when the component mounts
  useEffect(() => {
    const fetchProfileData = async () => {
      const token = localStorage.getItem('authToken');
      if (!token) {
        setMessage('No token found');
        return;
      }

      try {
        // Request to get current profile data (base URL handled by your environment)
        const response = await axios.get('/api/user/profile', {
          headers: { 'Authorization': `Bearer ${token}` }
        });

        if (response.data.error) {
          setMessage(response.data.message || 'Error fetching profile data');
        } else {
          setProfileData(response.data.data); // Update profileData based on the correct response structure
        }
      } catch (error) {
        console.log('Error fetching profile:', error);
        setMessage('Error fetching profile data');
      }
    };

    if (isAuthenticated) {
      fetchProfileData(); // Only fetch profile data if the user is authenticated
    }
  }, [isAuthenticated]); // Depend on isAuthenticated to trigger when user state changes

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
      // PUT request to update profile (base URL handled by your environment)
      const response = await axios.put(
        '/api/user/edit-profile', // Endpoint to update profile
        profileData,
        {
          headers: { 'Authorization': `Bearer ${token}` }
        }
      );

      if (response.data.error) {
        setMessage(response.data.message || 'Error updating profile');
      } else {
        setMessage(response.data.message || 'Profile updated successfully');

        // Redirect to the updated profile page after successful update
        navigate(`/user/${user.username}`); // Use user.username from the auth context
      }
    } catch (error) {
      console.log('Error updating profile:', error);
      setMessage(error.response?.data?.message || 'Something went wrong');
    }
  };

  return (
    <div style={{ background: '#002F6C', color: 'white', padding: '20px', minHeight: '100vh' }}>
      <h2>Edit Profile</h2>
      <form onSubmit={handleSubmit} style={{ maxWidth: '500px', margin: 'auto' }}>
        <label>
          Logo:
          <input
            type="text"
            name="logo"
            value={profileData.logo}
            onChange={handleChange}
            style={{ width: '100%', padding: '8px', margin: '10px 0', borderRadius: '5px', border: '1px solid #ccc' }}
          />
        </label>
        <br />
        <label>
          Company Phone:
          <input
            type="text"
            name="companyPhone"
            value={profileData.companyPhone}
            onChange={handleChange}
            style={{ width: '100%', padding: '8px', margin: '10px 0', borderRadius: '5px', border: '1px solid #ccc' }}
          />
        </label>
        <br />
        <label>
          Company Description:
          <textarea
            name="companyDescription"
            value={profileData.companyDescription}
            onChange={handleChange}
            style={{ width: '100%', padding: '8px', margin: '10px 0', borderRadius: '5px', border: '1px solid #ccc' }}
          />
        </label>
        <br />
        <label>
          Company Website:
          <input
            type="text"
            name="companyWebsite"
            value={profileData.companyWebsite}
            onChange={handleChange}
            style={{ width: '100%', padding: '8px', margin: '10px 0', borderRadius: '5px', border: '1px solid #ccc' }}
          />
        </label>
        <br />
        <label>
          Company Address:
          <input
            type="text"
            name="companyAddress"
            value={profileData.companyAddress}
            onChange={handleChange}
            style={{ width: '100%', padding: '8px', margin: '10px 0', borderRadius: '5px', border: '1px solid #ccc' }}
          />
        </label>
        <br />
        <button type="submit" style={{ padding: '10px 20px', backgroundColor: '#4CAF50', color: 'white', border: 'none', cursor: 'pointer', borderRadius: '5px' }}>
          Update Profile
        </button>
      </form>

      {message && <p style={{ marginTop: '20px', color: 'green' }}>{message}</p>}
    </div>
  );
};

export default EditProfile;

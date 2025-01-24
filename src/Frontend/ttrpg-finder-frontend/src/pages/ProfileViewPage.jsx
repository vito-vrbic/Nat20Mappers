import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

/*
    GET PROFILE DATA
    -----
    Address: ./user/{username}
    Type: GET
    Header: {} // No specific headers required because all users can view profiles
    Body: {} // No body content required
    -----
    Expected response:
      SUCCESS (Status Code 200 OK):
        Body: {
          "message": "Profile retrieved successfully",
          "data": {
            "username": "john_doe",                // Username of the user
            "logo": "https://example.com/logo.png", // URL of the user's logo (optional)
            "organizationName": "Tech Corp",        // Name of the organization (optional)
            "companyPhone": "123-456-7890",         // Phone number of the company (optional)
            "companyDescription": "Leading in innovation.", // Short description of the company (optional)
            "companyWebsite": "https://example.com", // Company's official website URL (optional)
            "companyAddress": "123 Tech Blvd, CA"   // Address of the company (optional)
          }
        }

      FAILURE (404 Not Found):
        Body: {
          "message": "Profile not found"
        }

      FAILURE (400 Bad Request):
        Body: {
          "message": "Invalid username"
        }

      FAILURE (500 Internal Server Error):
        Body: {
          "message": "An unexpected error occurred"
        }
*/


const UserProfile = () => {
  const { username } = useParams();

  const [profile, setProfile] = useState(null); // Holds the fetched profile data
  const [loading, setLoading] = useState(true); // To manage the loading state
  const [error, setError] = useState(null); // To handle errors (e.g., profile not found)

  useEffect(() => {
    // Fetch the user profile data based on the username
    const fetchProfileData = async () => {
      try {
        const response = await fetch(`/user/${username}`); // Assuming you have a server route like this
        if (response.ok) {
          const data = await response.json();
          setProfile(data.data); // Set the profile data (assumes data comes under 'data')
          setLoading(false); // Stop loading
        } else {
          throw new Error('Profile not found');
        }
      } catch (error) {
        setLoading(false);
        setError(error.message); // Set the error if profile isn't found or another issue occurs
      }
    };

    fetchProfileData();
  }, [username]);

  if (loading) {
    return (
      <div style={{ backgroundColor: '#001f3d', color: 'white', padding: '20px' }}>
        Loading...
      </div>
    ); // Loading message with dark blue background
  }

  if (error) {
    return (
      <div style={{ backgroundColor: '#001f3d', color: 'white', padding: '20px' }}>
        <h1>{error}</h1>
      </div>
    );
  }

  if (!profile) {
    return (
      <div style={{ backgroundColor: '#001f3d', color: 'white', padding: '20px' }}>
        <h1>This profile hasn't been found.</h1>
      </div>
    );
  }

  return (
    <div style={{ backgroundColor: '#001f3d', color: 'white', padding: '20px' }}>
      <h1>User Profile: {profile.username}</h1>
      <div>
        {/* Profile Logo */}
        {profile.logo && (
          <img
            src={profile.logo}
            alt="Profile Logo"
            style={{
              width: '150px',
              height: '150px',
              borderRadius: '50%',
            }}
          />
        )}

        <p><strong>Company Name:</strong> {profile.organizationName || "Not Provided"}</p>
        <p><strong>Phone:</strong> {profile.companyPhone || "Not Provided"}</p>
        <p><strong>Description:</strong> {profile.companyDescription || "No description available."}</p>
        <p><strong>Website:</strong>
          <a
            href={profile.companyWebsite || "#"}
            target="_blank"
            rel="noopener noreferrer"
            style={{ color: 'white' }} // Ensuring the link is in white color to contrast against the dark background
          >
            {profile.companyWebsite || "Not Provided"}
          </a>
        </p>
        <p><strong>Address:</strong> {profile.companyAddress || "Not Provided"}</p>
      </div>
    </div>
  );
};

export default UserProfile;

import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import styles from './HomePage.module.css';  // Import the CSS module
import welcome_image1 from '../assets/images/welcome-image1.jpg';
import welcome_image2 from '../assets/images/welcome-image2.jpg';
import welcome_image3 from '../assets/images/welcome-image3.png';
import welcome_image4 from '../assets/images/welcome-image4.jpg';

const Home = () => {
  const { isAuthenticated, user, logout } = useAuth(); // Use the useAuth hook
  const navigate = useNavigate(); // Navigate hook for redirects

  const [currentIndex, setCurrentIndex] = useState(0); // Track the index of the currently displayed image
  const images = [welcome_image1, welcome_image2, welcome_image3, welcome_image4];

  // Text content for each image
  const textContent = [
    { title: "Search and Join Games", description: "Find your next TTRPG session with a simple search or filter options." },
    { title: "Create Games", description: "If you can't find a game you like, create your own and invite others!" },
    { title: "Host Locally or Online", description: "Connect with players near you or online for your TTRPG adventure." },
    { title: "Promote Your Business", description: "Business owners can promote their services by hosting games." }
  ];

  const handleNext = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  const handlePrev = () => {
    setCurrentIndex((prevIndex) => (prevIndex - 1 + images.length) % images.length);
  };

  return (
    <>
      <div className={styles.homePage}>
        {/* Welcome Text Section */}
        <div className={styles.welcomeText}>
          <h1>Welcome to TTRPG Finder!</h1>
          <h2>"Your next session is just a click away!"</h2>
        </div>

        {/* Horizontal Scroll with Navigation Arrows */}
        <div className={styles.scrollContainer}>
          <button className={`${styles.arrowButton} ${styles.left}`} onClick={handlePrev}>&lt;</button>

          <div className={styles.scrollItem}>
            <div className={styles.imageWrapper}>
              <img src={images[currentIndex]} alt="Welcome" className={styles.welcomeImage} />
              <div className={styles.overlay}>
                <h3 className={styles.description}>{textContent[currentIndex].title}</h3>
                <p className={styles.descriptionText}>{textContent[currentIndex].description}</p>
              </div>
            </div>
          </div>

          <button className={`${styles.arrowButton} ${styles.right}`} onClick={handleNext}>&gt;</button>
        </div>
      </div>
    </>
  );
}

export default Home;

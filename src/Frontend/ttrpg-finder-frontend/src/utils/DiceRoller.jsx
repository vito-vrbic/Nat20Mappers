import React, { useEffect } from 'react';

import diceImage1 from '../assets/images/dice1.png';
import diceImage2 from '../assets/images/dice2.png';
import diceImage3 from '../assets/images/dice3.png';

const DiceRoller = () => {
  useEffect(() => {
    const diceImages = [diceImage1, diceImage2, diceImage3]; // Array of dice images
    let timeoutId = null; // To store the timeout ID for later clearing

    // Function to spawn dice
    const spawnDice = (event) => {
      const dice = document.createElement('img');
      dice.src = diceImages[Math.floor(Math.random() * diceImages.length)]; // Random dice image
      dice.classList.add('dice'); // Add a class for styling
      document.body.appendChild(dice);

      // Set random size for each dice image
      const size = Math.random() * 15 + 10; // Random size between 10px to 25px
      dice.style.width = `${size}px`;
      dice.style.height = `${size}px`;
      dice.style.position = 'absolute';
      dice.style.pointerEvents = 'none'; // Prevent interaction with the dice

      // Position the dice around the mouse cursor, accounting for scroll position
      const offsetX = event.pageX - window.scrollX; // Adjust for scrollX
      const offsetY = event.pageY - window.scrollY; // Adjust for scrollY

      dice.style.left = `${offsetX - size / 2}px`; // Center the dice around the cursor
      dice.style.top = `${offsetY - size / 2}px`; // Center the dice around the cursor
      dice.style.transform = `rotate(${Math.random() * 360}deg)`; // Random rotation

      // Generate random movement direction (within a range)
      const directionX = (Math.random() - 0.5) * 100; // Random horizontal direction (between -150px to 150px)
      const directionY = (Math.random() - 0.5) * 100; // Random vertical direction (between -150px to 150px)

      // Apply random direction and movement using transform
      dice.style.transition = 'opacity 1s ease, transform 1s ease';
      requestAnimationFrame(() => {
        dice.style.opacity = '0';
        dice.style.transform = `translate(${directionX}px, ${directionY}px) rotate(${Math.random() * 360}deg)`;
      });

      // Remove the dice after animation ends
      setTimeout(() => {
        dice.remove();
      }, 1500); // Match the duration of the animation
    };

    // Function to handle mouse movement and throttle dice spawn
    const handleMouseMove = (event) => {
      // Check if the mouse is over a hoverable element (e.g., button, a tag)
      const isHoverableElement = event.target.closest('button, a, .hoverable');
      if (isHoverableElement) {
        return; // Don't spawn dice if hovering over button or link
      }

      // Only spawn dice every 35ms (prevent too many dice spawning)
      if (!timeoutId) {
        spawnDice(event); // Spawn dice immediately
        timeoutId = setTimeout(() => {
          timeoutId = null; // Reset timeout ID after the delay
        }, 35); // 35ms delay before spawning dice again
      }
    };

    // Add mousemove event listener
    window.addEventListener('mousemove', handleMouseMove);

    // Cleanup the event listener on component unmount
    return () => {
      window.removeEventListener('mousemove', handleMouseMove);
      if (timeoutId) clearTimeout(timeoutId); // Clear any active timeout when component unmounts
    };
  }, []);

  return null; // This component does not render anything visually
};

export default DiceRoller;

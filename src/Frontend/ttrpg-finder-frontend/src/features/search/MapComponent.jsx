import React, { useEffect, useState } from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';
import styles from './MapComponent.module.css'; // Import your CSS module if you're using it

// Fix for Leaflet marker icons
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: markerIcon2x,
  iconUrl: markerIcon,
  shadowUrl: markerShadow,
});

const MapComponent = ({ mapLocation, setMapLocation }) => {
  // Initialize the map state if the prop `mapLocation` is undefined or null
  const [location, setLocation] = useState(mapLocation || { lat: 51.505, lng: -0.09 });

  useEffect(() => {
    // Update location if prop changes
    if (mapLocation) {
      setLocation(mapLocation);
    }
  }, [mapLocation]);

  const MapClickHandler = () => {
    useMapEvents({
      click(e) {
        const { lat, lng } = e.latlng;
        setLocation({ lat, lng });
        setMapLocation({ lat, lng });
      },
    });
    return null;
  };

  return (
    <div className={styles.mapContainer}>
      <label>
        Map Location:
        <MapContainer
          center={location}
          zoom={13}
          scrollWheelZoom={false}
          style={{ width: '100%', height: '300px' }}
        >
          <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          />
          <MapClickHandler />
          {location && (
            <Marker position={location}>
              <Popup>Selected Location</Popup>
            </Marker>
          )}
        </MapContainer>
      </label>
    </div>
  );
};

export default MapComponent;

import React from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMapEvents } from 'react-leaflet';
import '../../styles/Search.css';

const MapComponent = ({ mapLocation, setMapLocation }) => {
  const MapClickHandler = () => {
    useMapEvents({
      click(e) {
        const { lat, lng } = e.latlng;
        setMapLocation({ lat, lng });
      },
    });
    return null;
  };

  return (
    <div>
      <label>Map Location:
        <MapContainer
          center={mapLocation}
          zoom={13}
          scrollWheelZoom={false}
          style={{ width: "100%", height: "300px" }}
        >
          <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          />
          <MapClickHandler />
          <Marker position={mapLocation}>
            <Popup>Selected Location</Popup>
          </Marker>
        </MapContainer>
      </label>
    </div>
  );
};

export default MapComponent;

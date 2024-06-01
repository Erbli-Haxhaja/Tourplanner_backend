import React, { useState, useEffect } from 'react';
import { MapContainer, TileLayer, Marker, Popup, Polyline } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import axios from 'axios';
import { Icon } from 'leaflet';

const API_KEY = '5b3ce3597851110001cf62488836d19f8eeb43d587d46df7986b2e53';
const GEOCODING_API_KEY = '73f92eb6b8114d53bbae5353fcce02cb'; // Replace with your OpenCage Data API key

const customIcon = new Icon({
    iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-icon.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41]
});

function App() {
    const [tourLogs, setTourLogs] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);
    const [showRemoveForm, setShowRemoveForm] = useState(false);
    const [newTour, setNewTour] = useState({
        name: '',
        tourDescription: '',
        fromm: '',
        too: '',
        transportType: ''
    });
    const [removeId, setRemoveId] = useState('');
    const [mapMarkers, setMapMarkers] = useState([]);
    const [pathCoordinates, setPathCoordinates] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/tours')
            .then(response => response.json())
            .then(data => setTourLogs(data))
            .catch(error => console.error('Error fetching tour logs:', error));
    }, []);

    const mapWrapperStyle = {
        position: 'relative',
        width: '100%',
        height: '400px',
        overflow: "hidden"
    };

    const mapContainerStyle = {
        width: '100%',
        height: '100%'
    };

    const handleAddTour = () => {
        setShowAddForm(true);
        setShowRemoveForm(false);
    };

    const handleRemoveTour = () => {
        setShowRemoveForm(true);
        setShowAddForm(false);
    };

    const handleAddFormChange = (e) => {
        const { name, value } = e.target;
        setNewTour({ ...newTour, [name]: value });
    };

    const handleAddFormSubmit = async (e) => {
        e.preventDefault();

        try {
            const fromResponse = await axios.get(`https://api.opencagedata.com/geocode/v1/json?q=${newTour.fromm}&key=${GEOCODING_API_KEY}`);
            const fromCoords = fromResponse.data.results[0].geometry;

            const toResponse = await axios.get(`https://api.opencagedata.com/geocode/v1/json?q=${newTour.too}&key=${GEOCODING_API_KEY}`);
            const toCoords = toResponse.data.results[0].geometry;

            const orsResponse = await axios.get(`https://api.openrouteservice.org/v2/directions/driving-car?api_key=${API_KEY}&start=${fromCoords.lng},${fromCoords.lat}&end=${toCoords.lng},${toCoords.lat}`);
            const { distance, duration, steps } = orsResponse.data.features[0].properties.segments[0];

            const distanceMeters = `${distance.toFixed(2)} m`;
            const durationMinutes = Math.floor(duration / 60);
            const durationSeconds = (duration % 60).toFixed(2);
            const durationFormatted = `${durationMinutes} min ${durationSeconds} s`;

            const tourData = {
                ...newTour,
                tourDistance: distanceMeters,
                estimatedTime: durationFormatted
            };

            const response = await fetch('http://localhost:8080/tours/createTour', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(tourData)
            });

            const data = await response.json();
            setTourLogs([...tourLogs, data]);
            setNewTour({
                name: '',
                tourDescription: '',
                fromm: '',
                too: '',
                transportType: ''
            });
            setShowAddForm(false);

            setMapMarkers([
                { coords: fromCoords, name: newTour.fromm },
                { coords: toCoords, name: newTour.too }
            ]);

            const pathCoords = orsResponse.data.features[0].geometry.coordinates.map(coord => ({
                lat: coord[1],
                lng: coord[0]
            }));
            setPathCoordinates(pathCoords);
        } catch (error) {
            console.error('Error adding new tour:', error);
        }
    };

    const handleRemoveFormChange = (e) => {
        setRemoveId(e.target.value);
    };

    const handleRemoveFormSubmit = (e) => {
        e.preventDefault();
        fetch(`http://localhost:8080/tours/deleteTour/${removeId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    setTourLogs(tourLogs.filter(tour => tour.id !== parseInt(removeId)));
                    setShowRemoveForm(false);
                } else {
                    throw new Error('Failed to delete tour');
                }
            })
            .catch(error => console.error('Error deleting tour:', error));
    };

    return (
        <div style={{ display: 'flex', flexDirection: 'column', height: '100vh' }}>
            <nav style={{ backgroundColor: '#333', padding: '10px', display: 'flex' }}>
                <button id="file" style={buttonStyle}>File</button>
                <button id="edit" style={buttonStyle}>Edit</button>
                <button id="options" style={buttonStyle}>Options</button>
                <button id="help" style={buttonStyle}>Help</button>
            </nav>

            <div style={{ display: 'flex', flex: 1 }}>
                <div style={{ flex: 1, padding: '20px' }}>
                    <h2 style={{ marginBottom: '10px' }}>Tour List</h2>
                    <ul style={{ listStyle: 'none', padding: 0, marginBottom: '20px' }}>
                        {tourLogs.map((log, index) => (
                            <li key={index}>{log.name}</li>
                        ))}
                    </ul>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-start' }}>
                        <button id="add-tour-button" onClick={handleAddTour} style={addRemoveButton}><span>+</span></button>
                        <button id="remove-tour-button" onClick={handleRemoveTour} style={addRemoveButton}><span>-</span></button>
                    </div>
                </div>

                <div style={{ flex: 2, display: 'flex', flexDirection: 'column' }}>
                    <div style={{ flex: 1, padding: '20px', border: '1px solid #ccc', marginRight: '10px' }}>
                        <div className="map-container" style={mapContainerStyle}>
                            <h2>Map</h2>
                            <div style={{ height: '400px', width: '100%', ...mapWrapperStyle }}>
                                <MapContainer center={[48.2082, 16.3738]} zoom={13} style={{ height: '100%', width: '100%' }}>
                                    <TileLayer
                                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                                    />
                                    {mapMarkers.map((marker, index) => (
                                        <Marker
                                            key={index}
                                            position={[marker.coords.lat, marker.coords.lng]}
                                            icon={customIcon}
                                        >
                                            <Popup>{marker.name}</Popup>
                                        </Marker>
                                    ))}
                                    {pathCoordinates.length > 0 && (
                                        <Polyline positions={pathCoordinates} color="blue" />
                                    )}
                                </MapContainer>
                            </div>
                        </div>
                    </div>

                    <div style={{ flex: 1, padding: '20px', border: '1px solid #ccc', marginBottom: '10px' }}>
                        <h2>Tour Logs</h2>
                        <table style={{ width: '100%', borderCollapse: 'collapse' }}>
                            <thead>
                            <tr>
                                <th style={tableHeaderStyle}>Tour ID</th>
                                <th style={tableHeaderStyle}>Tour Name</th>
                                <th style={tableHeaderStyle}>Tour Description</th>
                                <th style={tableHeaderStyle}>From</th>
                                <th style={tableHeaderStyle}>To</th>
                                <th style={tableHeaderStyle}>Transport Type</th>
                                <th style={tableHeaderStyle}>Tour Distance</th>
                                <th style={tableHeaderStyle}>Duration</th>
                            </tr>
                            </thead>
                            <tbody>
                            {tourLogs.map((log, index) => (
                                <tr key={index}>
                                    <td style={tableCellStyle}>{log.id}</td>
                                    <td style={tableCellStyle}>{log.name}</td>
                                    <td style={tableCellStyle}>{log.tourDescription}</td>
                                    <td style={tableCellStyle}>{log.fromm}</td>
                                    <td style={tableCellStyle}>{log.too}</td>
                                    <td style={tableCellStyle}>{log.transportType}</td>
                                    <td style={tableCellStyle}>{log.tourDistance}</td>
                                    <td style={tableCellStyle}>{log.estimatedTime}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            {showAddForm && (
                <div style={{ position: 'absolute', bottom: '20px', left: '20px', padding: '20px', border: '1px solid #ccc', backgroundColor: '#f9f9f9' }}>
                    <h2>Add New Tour</h2>
                    <form onSubmit={handleAddFormSubmit}>
                        <div style={{ marginBottom: '10px' }}>
                            <label htmlFor="name" style={{ marginRight: '10px' }}>Name:</label>
                            <input type="text" id="name" name="name" value={newTour.name} onChange={handleAddFormChange} />
                        </div>
                        <div style={{ marginBottom: '10px' }}>
                            <label htmlFor="tourDescription" style={{ marginRight: '10px' }}>Tour Description:</label>
                            <input type="text" id="tourDescription" name="tourDescription" value={newTour.tourDescription} onChange={handleAddFormChange} />
                        </div>
                        <div style={{ marginBottom: '10px' }}>
                            <label htmlFor="fromm" style={{ marginRight: '10px' }}>From:</label>
                            <input type="text" id="fromm" name="fromm" value={newTour.fromm} onChange={handleAddFormChange} />
                        </div>
                        <div style={{ marginBottom: '10px' }}>
                            <label htmlFor="too" style={{ marginRight: '10px' }}>To:</label>
                            <input type="text" id="too" name="too" value={newTour.too} onChange={handleAddFormChange} />
                        </div>
                        <div style={{marginBottom: '10px'}}>
                            <label htmlFor="transportType" style={{marginRight: '10px'}}>Transport Type:</label>
                            <select id="transportType" name="transportType" value={newTour.transportType} onChange={handleAddFormChange}>
                                <option value="Foot">Foot</option>
                                <option value="Car">Car</option>
                                <option value="Bicycle">Bicycle</option>
                            </select>
                        </div>
                        <div>
                            <button type="submit" style={submitButtonStyle}>Send</button>
                            <button type="button" onClick={() => setShowAddForm(false)} style={cancelButtonStyle}>Cancel</button>
                        </div>
                    </form>
                </div>
            )}

            {showRemoveForm && (
                <div style={{ position: 'absolute', bottom: '20px', left: '20px', padding: '20px', border: '1px solid #ccc', backgroundColor: '#f9f9f9' }}>
                    <h2>Remove Tour</h2>
                    <form onSubmit={handleRemoveFormSubmit}>
                        <div style={{ marginBottom: '10px' }}>
                            <label htmlFor="removeId" style={{ marginRight: '10px' }}>Tour ID:</label>
                            <input type="text" id="removeId" name="removeId" value={removeId} onChange={handleRemoveFormChange} />
                        </div>
                        <div>
                            <button type="submit" style={submitButtonStyle}>Send</button>
                            <button type="button" onClick={() => setShowRemoveForm(false)} style={cancelButtonStyle}>Cancel</button>
                        </div>
                    </form>
                </div>
            )}
        </div>
    );
}

const buttonStyle = {
    background: 'none',
    border: 'none',
    color: 'white',
    padding: '5px 10px',
    marginRight: '10px',
    cursor: 'pointer'
};

const addRemoveButton = {
    background: 'white',
    border: 'black 1px solid',
    color: 'black',
    padding: '5px 10px',
    marginRight: '10px',
    cursor: 'pointer'
};

const tableHeaderStyle = {
    borderBottom: '1px solid #333',
    padding: '8px',
    textAlign: 'left'
};

const tableCellStyle = {
    borderBottom: '1px solid #ccc',
    padding: '8px',
    textAlign: 'left'
};

const submitButtonStyle = {
    backgroundColor: '#4CAF50',
    color: 'white',
    padding: '10px 15px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer'
};

const cancelButtonStyle = {
    backgroundColor: '#f44336',
    color: 'white',
    padding: '10px 15px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    marginLeft: '10px'
};

export default App;

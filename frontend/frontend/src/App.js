import React, { useState, useEffect } from 'react';
import GoogleMapReact from 'google-map-react';

function App() {
  // State for tour logs
  const [tourLogs, setTourLogs] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [newTour, setNewTour] = useState({
    name: '',
    tourDescription: '',
    fromm: '',
    too: '',
    transportType: '',
    tourDistance: '',
    estimatedTime: ''
  });

  // Fetch tour logs from the backend
  useEffect(() => {
    fetch('http://localhost:8080/tours')
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
          }
          return response.json();
        })
        .then(data => {
          console.log('Fetched data:', data); // Log the fetched data
          setTourLogs(data);
        })
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
    setShowForm(true);
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setNewTour({ ...newTour, [name]: value });
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/tours/createTour', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newTour)
    })
        .then(response => response.json())
        .then(data => {
          setTourLogs([...tourLogs, data]);
          setNewTour({
            name: '',
            tourDescription: '',
            fromm: '',
            too: '',
            transportType: '',
            tourDistance: '',
            estimatedTime: ''
          });
          setShowForm(false);
        })
        .catch(error => console.error('Error adding new tour:', error));
  };

  return (
      <div style={{ display: 'flex', flexDirection: 'column', height: '100vh' }}>
        {/* Navigation Bar */}
        <nav style={{ backgroundColor: '#333', padding: '10px', display: 'flex' }}>
          <button id="file" style={buttonStyle}>File</button>
          <button id="edit" style={buttonStyle}>Edit</button>
          <button id="options" style={buttonStyle}>Options</button>
          <button id="help" style={buttonStyle}>Help</button>
        </nav>

        {/* Main Content */}
        <div style={{ display: 'flex', flex: 1 }}>
          {/* Left Panel */}
          <div style={{ flex: 1, padding: '20px' }}>
            <h2 style={{ marginBottom: '10px' }}>Tour List</h2>
            <ul style={{ listStyle: 'none', padding: 0, marginBottom: '20px' }}>
              {tourLogs.map((log, index) => (
                  <li key={index}>{log.name}</li>
              ))}
            </ul>
            <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-start' }}>
              <button id="add-tour-button" onClick={handleAddTour} style={addRemoveButton}><span>+</span>
              </button>
              <button id="remove-tour-button" onClick={() => {}} style={addRemoveButton}>
                <span>-</span></button>
            </div>
          </div>

          {/* Right Panel */}
          <div style={{ flex: 2, display: 'flex', flexDirection: 'column' }}>
            {/* Map of Vienna */}
            <div style={{ flex: 1, padding: '20px', border: '1px solid #ccc', marginRight: '10px' }}>
              <div className="map-container" style={mapContainerStyle}>
                <h2>Map</h2>
                <div style={{ height: '400px', width: '100%', ...mapWrapperStyle }}>
                  <GoogleMapReact
                      bootstrapURLKeys={{ key: 'AIzaSyALBWDLLBvnHcUHE214tulcMeN-SUMdT0o' }}
                      defaultCenter={{ lat: 48.2082, lng: 16.3738 }}
                      defaultZoom={10}
                  >
                    {/* Add map markers here if needed */}
                  </GoogleMapReact>
                </div>
              </div>
            </div>

            {/* Tour Logs */}
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

        {/* Add Tour Form */}
        {showForm && (
            <div style={{ position: 'absolute', bottom: '20px', left: '20px', padding: '20px', border: '1px solid #ccc', backgroundColor: '#f9f9f9' }}>
              <h2>Add New Tour</h2>
              <form onSubmit={handleFormSubmit}>
                <div style={{ marginBottom: '10px' }}>
                  <label htmlFor="name" style={{ marginRight: '10px' }}>Name:</label>
                  <input type="text" id="name" name="name" value={newTour.name} onChange={handleFormChange} />
                </div>
                <div style={{ marginBottom: '10px' }}>
                  <label htmlFor="tourDescription" style={{ marginRight: '10px' }}>Tour Description:</label>
                  <input type="text" id="tourDescription" name="tourDescription" value={newTour.tourDescription} onChange={handleFormChange
                  } />
                </div>
                <div style={{ marginBottom: '10px' }}>
                  <label htmlFor="fromm" style={{ marginRight: '10px' }}>From:</label>
                  <input type="text" id="fromm" name="fromm" value={newTour.fromm} onChange={handleFormChange} />
                </div>
                <div style={{ marginBottom: '10px' }}>
                  <label htmlFor="too" style={{ marginRight: '10px' }}>To:</label>
                  <input type="text" id="too" name="too" value={newTour.too} onChange={handleFormChange} />
                </div>
                <div style={{ marginBottom: '10px' }}>
                  <label htmlFor="transportType" style={{ marginRight: '10px' }}>Transport Type:</label>
                  <input type="text" id="transportType" name="transportType" value={newTour.transportType} onChange={handleFormChange} />
                </div>
                <div style={{ marginBottom: '10px' }}>
                  <label htmlFor="tourDistance" style={{ marginRight: '10px' }}>Tour Distance:</label>
                  <input type="text" id="tourDistance" name="tourDistance" value={newTour.tourDistance} onChange={handleFormChange} />
                </div>
                <div style={{ marginBottom: '10px' }}>
                  <label htmlFor="estimatedTime" style={{ marginRight: '10px' }}>Estimated Time:</label>
                  <input type="text" id="estimatedTime" name="estimatedTime" value={newTour.estimatedTime} onChange={handleFormChange} />
                </div>
                <div>
                  <button type="submit" style={submitButtonStyle}>Send</button>
                  <button type="button" onClick={() => setShowForm(false)} style={cancelButtonStyle}>Cancel</button>
                </div>
              </form>
            </div>
        )}
      </div>
  );
}

// Inline CSS styles
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
  borderBottom: '1px solid #ccc',
  padding: '8px',
  textAlign: 'left'
};

const tableCellStyle = {
  border: '1px solid #ccc',
  padding: '8px',
  textAlign: 'left'
};

const submitButtonStyle = {
  background: '#4CAF50',
  border: 'none',
  color: 'white',
  padding: '10px 20px',
  textAlign: 'center',
  textDecoration: 'none',
  display: 'inline-block',
  fontSize: '16px',
  margin: '4px 2px',
  cursor: 'pointer'
};

const cancelButtonStyle = {
  background: '#f44336',
  border: 'none',
  color: 'white',
  padding: '10px 20px',
  textAlign: 'center',
  textDecoration: 'none',
  display: 'inline-block',
  fontSize: '16px',
  margin: '4px 2px',
  cursor: 'pointer'
};

export default App;
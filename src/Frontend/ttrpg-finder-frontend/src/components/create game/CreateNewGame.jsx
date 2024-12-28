import React, { useState, useEffect} from 'react';
import '../../styles/Dashboard.css'
import '../../styles/GameContainer.css';
import MapComponent from '../../components/search/MapComponent'; // Import the MapComponent
import axios from 'axios';
import { useAuth } from '../../utils/AuthContext'; // Importing the custom hook from AuthContext
import { use } from 'react';

const CreateNewGame = ({onClose}) => {
    const { isAuthenticated, user, logout } = useAuth();

    //Form data
      const [showForm, setShowForm] = useState(false); // Default state for form visibility: flase = form not seen, true = form seen
      const [gameTitle, setGameTitle] = useState(""); // Game title
      const [gameType, setGameType] = useState("online"); // Default type of game
      const [mapLocation, setMapLocation] = useState({ lat: 45.8131, lng: 15.978 }); // Default location (Zagreb)
      const [timeZone, setTimeZone] = useState("GMT"); // Timezone for when the game is online
      const [gameAvailability, setGameAvailability] = useState("private"); // Default type of game availability
      const [applicationRequired, setApplicationRequired] = useState(true); // Default state for applicationn requirement
      const [complexityLevel, setComplexityLevel] = useState(""); // Type of game complexity
      const [estimatedLength, setEstimatedLength] = useState(""); // Estimated length
      const [startTime, setStartTime] = useState(""); // Start time and date of game
      const [description, setDescription] = useState(""); // Game description
      const [rules, setRules] = useState(""); // Game rules
      const [maxNumOfPlayers, setMaxNumOfPlayers] = useState(""); //Max number of players
      const [formRequired, setFormRequired] = useState(""); // Is form required
      const [communicationChannel, setCommunicationChannel] = useState(""); // Communication channel for game
      const [isHomebrew, setIsHomeBrew] = useState(""); // Is Homebrew

      const toggleForm = () =>{
        setShowForm(false);
      }
    
      //Handle title change
      const handleGameTitleChange = (e) =>{
        setGameTitle(e.target.value);
        console.log("Promjena imena igre: ", e.target.value);
      };
    
      //Handle game type change
      const handleGameTypeChange = (e) =>{
        setGameType(e.target.value);
        if(e.target.value === "online"){
          mapLocation.lat = null;
          mapLocation.lng = null;
          setTimeZone("GMT");
        }
        if(e.target.value === "local" || e.target.value === "exact"){
          setTimeZone("");
          mapLocation.lat = 45.8131;
          mapLocation.lng = 15.978;
        }
      };
    
      //Handle timezone change
      const handleTimeZoneChange = (e) =>{
        setTimeZone(e.target.value);
        console.log("Promjena timezone u: ", e.target.value);
      };
    
      //Handle game availability change
      const handleGameAvailabilityChange = (e) =>{
        setGameAvailability(e.target.value);
      };
    
      //Handle application required change
      const handleApplicationRequiredChange = (e) =>{
        setApplicationRequired(e.target.value === "true");
        console.log("Pritisnut event za application required: ", e.target.value);
      };
    
      //handle game complexity change
      const handleGameComplexityChange = (e) =>{
        setComplexityLevel(e.target.value);
      };
    
      //handle estimated lingth change
      const handleEstimatedLengthChange = (e) =>{
        setEstimatedLength(e.target.value);
        console.log("Promjena duljine igre: ", e.target.value);
      };
    
      //handle start time change
      const handleStartTimeChange = (e) =>{
        setStartTime(e.target.value);
        console.log("Promjena start vrijeme igre: ", e.target.value);
      };
    
      //handle description change
      const handleDescriptionChange = (e) =>{
        setDescription(e.target.value);
        console.log("Promjena opisa: ", e.target.value);
      };
    
      //handle rule change
      const handleRulesChange = (e) =>{
        setRules(e.target.value);
        console.log("Promjena pravila: ", e.target.value);
      };
    
      //handle max player count change
      const handleMaxPlayerChange = (e) =>{
        if((e.target.value >= 1 && e.target.value <= 9999) || e.target.value === ""){
          setMaxNumOfPlayers(e.target.value);
          console.log("Promjena max br igraca: ", e.target.value);
        }
      };
    
      //handle form required change
      const handleFormRequiredChange = (e) =>{
        setFormRequired(e.target.value === "true");
        console.log("Pritisnut event za form required: ", e.target.value);
      };
    
      //hanldle communcation channel change
      const handleCommunicationChannelChange = (e) =>{
        setCommunicationChannel(e.target.value);
        console.log("Promjena kanala za komunikaciju: ", e.target.value);
      };
    
      //handle is homebrew change
      const handleIsHomebrewChange = (e) =>{
        setIsHomeBrew(e.target.value === "true");
        console.log("Pritisnut event za Homebrew: ", e.target.value);
      };
    
      //handle submit
      const handleSubmit = async (e) => {
        e.preventDefault();
        const newGame = {
          id: 123, //generira backend?
          title: gameTitle, //string
          type: gameType, //online/local za usera, online/exact za biznis usera
          location: {"lat": mapLocation.lat, "lng": mapLocation.lng}, //koordinate
          timezone: timeZone,
          availability: gameAvailability, //private ili public
          createdBy: user.role, //private ili biznis user
          applicationRequired: applicationRequired, //true/false
          complexity: complexityLevel, //low,medium,high
          estimatedLength: estimatedLength, //string 
          startTimestamp: startTime, //optional  datetime-local 
          description: description, //optional string
          pravilnik: rules, //string
          requiresForm: formRequired, //true/false
          currentPlayerCount: 0, //int
          maxPlayerCount: maxNumOfPlayers, //optional int
          communicationChannel: communicationChannel, //string
          isHomebrew: isHomebrew //true/false
        };
        try{
          const response = await axios.post("http://localhost:5000/games", newGame); //Pošalji na server
          //stavi deafault vrijednosti
          setGameTitle("");
          setGameType("online");
          setMapLocation({ lat: 45.8131, lng: 15.978 });
          setTimeZone("GMT");
          setGameAvailability("private");
          setApplicationRequired(true);
          setComplexityLevel("");
          setEstimatedLength("");
          setStartTime("");
          setDescription("");
          setRules("");
          setMaxNumOfPlayers("");
          setFormRequired("");
          setCommunicationChannel("");
          setIsHomeBrew("");
          onClose();
        } catch(error){
          console.error("Error creating game:", error);
        }
      };

      return (
        <div className="overlay">
          <div className="form-container">
            <h2>Create New Game</h2>

            <form onSubmit={handleSubmit}>
              {/* Unos imena igre*/}
              <label>
                Game Title:
                <input type="text" name="title" value={gameTitle} onChange={handleGameTitleChange} required/>
              </label>
              <br />

              {/* Odabir tipa igre: online/local(za private usera)/exact(za biznis usera)*/}
              <label>
                Game Type:
              </label>
              <br/>
                <div className="gameType">
                  <label>
                  Online <input type="radio" name="type" value="online" checked={gameType === "online"} onChange={handleGameTypeChange} required/>
                  </label>
                  {user?.role === "private" && (
                  <label>
                  Local <input type="radio" name="type" value="local" checked={gameType === "local"} onChange={handleGameTypeChange} required/>
                  </label>
                  )}
                  {user?.role === "business" && (
                  <label>
                  Exact <input type="radio" name="type" value="exact" checked={gameType === "exact"} onChange={handleGameTypeChange} required/> 
                  </label>
                  )}
                </div>
              <br />
    
              {/* Odaberi lokaciju na mapi ako je igra local ili exact*/}
              {(gameType !== "online" && <MapComponent mapLocation={mapLocation} setMapLocation={setMapLocation}></MapComponent>)}

              {/* Odaberi timezone ako je igra online*/}
              {(gameType === "online" && 
                <div>
                  <label>
                    Choose a Timezone:
                  </label>
                  <select value={timeZone} onChange={handleTimeZoneChange} required>
                    <option value="PST UTC-8">PST UTC-8</option>
                    <option value="MST UTC-7">MST UTC-7</option>
                    <option value="CST UTC-6">CST UTC-6</option>
                    <option value="EST UTC-5">EST UTC-5</option>
                    <option value="-03 UTC-3">-03 UTC-3</option>
                    <option value="GMT">GMT</option>
                    <option value="CET UTC+1">CET UTC+1</option>
                    <option value="MSK UTC+3">MSK UTC+3</option>
                    <option value="+04 UTC+4">+04 UTC+4</option>
                    <option value="IST UTC+5:30">IST UTC+5:30</option>
                    <option value="+08 UTC+8">+08 UTC+8</option>
                    <option value="CST UTC+8">CST UTC+8</option>
                    <option value="JST UTC+9">JST UTC+9</option>
                    <option value="AEDT UTC+11">AEDT UTC+11</option>
                    <option value="NZDT UTC+13">NZDT UTC+13</option>
                  </select>
                </div>
              )}
              <br />

              {/* odaberi jel igra private ili public*/}
              <label>
                Availability: 
              </label>
              <br/>
                <div className="gameAvailability">
                  <label>
                  Private <input type="radio" name="availability" value="private" checked={gameAvailability === "private"} onChange={handleGameAvailabilityChange} required/>
                  </label>
                  <label>
                  Public <input type="radio" name="availability" value="public" checked={gameAvailability === "public"} onChange={handleGameAvailabilityChange} required/>
                  </label>
                </div>
              <br />

              {/* odaberi jeli application potreban*/}
              <label>
                Application Required:
              </label>
              <br/>
                <div className="gameAvailability">
                  <label>
                  Yes <input type="radio" name="applicationRequired" value={true} checked={applicationRequired === true} onChange={handleApplicationRequiredChange} required/>
                  </label>
                  <label>
                  No <input type="radio" name="applicationRequired" value={false} checked={applicationRequired === false} onChange={handleApplicationRequiredChange} required/>
                  </label>
                </div>
              <br />

              {/*Odaberi complexity level */}
              <label>
                Complexity Level:
              </label>
              <br/>
                <div className="gameAvailability">
                  <label>
                  Low <input type="radio" name="complexity" value="Low" checked={complexityLevel === "Low"} onChange={handleGameComplexityChange} required/>
                  </label>
                  <label>
                  Medium <input type="radio" name="complexity" value="Medium" checked={complexityLevel === "Medium"} onChange={handleGameComplexityChange} required/>
                  </label>
                  <label>
                  High <input type="radio" name="complexity" value="High" checked={complexityLevel === "High"} onChange={handleGameComplexityChange} required/>
                  </label>
                </div>
                <br />

                {/*Postavi koliko duge traje igra */} 
                <label>
                Estimated Length:
                  <input type="text" name="estimatedLength" value={estimatedLength} onChange={handleEstimatedLengthChange} required/>
                </label>
                <br />

                {/*Postavi kada krece igra (opcionalno) */}
                <label>
                Start Time (Optional):
                  <input type="datetime-local" name="startTime" value={startTime} onChange={handleStartTimeChange}/>
                </label>
                <br />

                {/*Postavi opis igre (opcionalno) */}  
                <label>
                Description (Optional):
                  <input type="text" name="description" value={description} onChange={handleDescriptionChange}/>
                </label>
                <br />

                {/*Postavi pravila igre */}
                <label>
                Rules:
                  <input type="text" name="rules" value={rules} onChange={handleRulesChange} required/>
                </label>
                <br />

                {/*Postavi pmax broj igraca (opcionalno) */}
                <label>
                Max Number Of Players (Optional):
                  <input type="number" name="maxPlayerCount" value={maxNumOfPlayers} onChange={handleMaxPlayerChange}/>
                </label>
                <br />

                {/*Odaberi jeli form potreban TODO: napraviti da moze korisnik stvoriti taj form */}
              <label>
              Form Required:
              </label>
                <div className="gameAvailability">
                  <label>
                  Yes <input type="radio" name="formRequired" value={true} checked={formRequired === true} onChange={handleFormRequiredChange} required/>
                  </label>
                  <label>
                  No <input type="radio" name="formRequired" value={false} checked={formRequired === false} onChange={handleFormRequiredChange} required/>
                  </label>
                </div>
              <br />

              {/*Postavi koji je komunikacijski kanal */}
              <label>
                Communication Channel:
                  <input type="text" name="communicationChannel" value={communicationChannel} onChange={handleCommunicationChannelChange} required/>
                </label>
                <br />

              {/*Odaberi jeli igra homebrew */}
              <label>
                Is Homebrew:
              </label>
                <div className="gameAvailability">
                  <label>
                  Yes <input type="radio" name="isHomebrew" value={true} checked={isHomebrew === true} onChange={handleIsHomebrewChange} required/>
                  </label>
                  <label>
                  No <input type="radio" name="isHomebrew" value={false} checked={isHomebrew === false} onChange={handleIsHomebrewChange} required/>
                  </label>
                </div>
              <br />

               {/*submiot i cancle gumb*/}
              <button type="submit">Submit</button>
              <button type="button" onClick={onClose}> Close </button>
            </form>
          </div>
        </div>
      )
}

export default CreateNewGame;
import React, { useState, useEffect} from 'react';
import '../../pages/DashboardPage.module.css'
import '../../assets/styles/GameContainer.css';
import '../../assets/styles/CreateNewGame.css';
import MapComponent from '../../features/search/MapComponent'; // Import the MapComponent
import axios from 'axios';
import { useAuth } from '../../context/AuthContext'; // Importing the custom hook from AuthContext
import { use } from 'react';

const CreateNewGame = ({onClose}) => {
    const { isAuthenticated, user, logout } = useAuth();

      //Seters for data that will be collected in a form
      const [showForm, setShowForm] = useState(false); // Default state for form visibility: flase = form not seen, true = form seen
      const [gameTitle, setGameTitle] = useState(""); // Game title
      const [gameType, setGameType] = useState("online"); // Default type of game
      const [mapLocation, setMapLocation] = useState({ lat: null, lng: null }); // Default location (Zagreb)
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

      const [questions, setQuestions] = useState([{questions: ""}]); //Array for questions

      //Handler for adding a new user question
      const handleAddQuestion = () => {
        //Check if the maximum number of questions was added
        if(questions.length < 99){
          setQuestions([...questions, {questions: ""}]);
        }
        else{
          alert("You cannot add more than 99 questions.");
        }
      };

      //Handler for delted a question
      const handleDeleteQuestion = (indexToRemove) => {
        //Check if a user is trying to delete the last question making it so that there is no questions but a form is required
        if(questions.length > 1){
          const updatedQuestions = questions.filter((_, index) => index !== indexToRemove);
          setQuestions(updatedQuestions);
        }
        else{
          alert("You must have at least 1 question.");
        }
      };

      //Handler for question change
      const handleQuestionChange = (index,e) => {
        const updatedQuestions = questions.map((q, i) => 
          i === index ? { ...q, questions: e.target.value} : q
        );
        setQuestions(updatedQuestions);
      };

      const toggleForm = () =>{
        setShowForm(false);
      }
    
      //Handler for changing a title of game
      const handleGameTitleChange = (e) =>{
        setGameTitle(e.target.value);
        console.log("Promjena imena igre: ", e.target.value);
      };
    
      //Handler for changing a type of game
      const handleGameTypeChange = (e) =>{
        setGameType(e.target.value);
        //If user wants an online game put latitude and longitude to null since it is not needed
        if(e.target.value === "online"){
          mapLocation.lat = null;
          mapLocation.lng = null;
          setTimeZone("GMT");
        }
        //If user want and local or exact game put timeZone to "" since it is not needed
        if(e.target.value === "local" || e.target.value === "exact"){
          setTimeZone("");
          mapLocation.lat = 45.8131;
          mapLocation.lng = 15.978;
          //Forca a private user to eneable applications when creating a local game
          if(e.target.value === "local" && user.role === "private"){
            setApplicationRequired(true);
          }
        }
      };
    
      //Handler for changing a timezone
      const handleTimeZoneChange = (e) =>{
        setTimeZone(e.target.value);
        console.log("Promjena timezone u: ", e.target.value);
      };
    
      //Handler for changing a type of availability for a game
      const handleGameAvailabilityChange = (e) =>{
        setGameAvailability(e.target.value);
        console.log("JEL POTREBNA APPLICATION: " + applicationRequired);
      };
    
      //Handler for changing if applications are required for a game
      const handleApplicationRequiredChange = (e) =>{
        //Prevent private user to turn off applications when creating a local game
        if((user.role === "private" && gameType !== "local") || (user.role === "business")){
          setApplicationRequired(e.target.value === "true");
          console.log("Pritisnut event za application required: ", e.target.value);
        }
      };
    
      //Handler for changing a type of complexity
      const handleGameComplexityChange = (e) =>{
        setComplexityLevel(e.target.value);
      };
    
      //Handler for changing estimated length of a game
      const handleEstimatedLengthChange = (e) =>{
        setEstimatedLength(e.target.value);
        console.log("Promjena duljine igre: ", e.target.value);
      };
    
      //Handler for changing start time (and date) of a game
      const handleStartTimeChange = (e) =>{
        setStartTime(e.target.value);
        console.log("Promjena start vrijeme igre: ", e.target.value);
      };
    
      //Handler for changing description of a game
      const handleDescriptionChange = (e) =>{
        setDescription(e.target.value);
        console.log("Promjena opisa: ", e.target.value);
      };
    
      //Handler for changing rules of a game
      const handleRulesChange = (e) =>{
        setRules(e.target.value);
        console.log("Promjena pravila: ", e.target.value);
      };
    
      //Handler for changing maximum number of players for a game
      const handleMaxPlayerChange = (e) =>{
        if((e.target.value >= 1 && e.target.value <= 9999) || e.target.value === ""){
          setMaxNumOfPlayers(e.target.value);
          console.log("Promjena max br igraca: ", e.target.value);
        }
      };
    
      //Handler for turning on or off user defined form 
      const handleFormRequiredChange = (e) =>{
        setFormRequired(e.target.value === "true");
        console.log("Pritisnut event za form required: ", e.target.value);
      };
    
      //Handler for changing what communication channel will be used for a game
      const handleCommunicationChannelChange = (e) =>{
        setCommunicationChannel(e.target.value);
        console.log("Promjena kanala za komunikaciju: ", e.target.value);
      };
    
      //Handler for saying if a game is homebrew (yes or no)
      const handleIsHomebrewChange = (e) =>{
        setIsHomeBrew(e.target.value === "true");
        console.log("Pritisnut event za Homebrew: ", e.target.value);
      };
    
      //Handler for submiting a form
      const handleSubmit = async (e) => {
        e.preventDefault();
        //If a user made some questions for its form but then decided that the form is not needed clear the questions that were created
        if(!formRequired){
          setQuestions([{question: ""}]);
        }

        //Data of a new game
        const newGame = {
          title: gameTitle, // String
          type: gameType, // online/local for private user, online/exact for business user
          location: {"lat": mapLocation.lat, "lng": mapLocation.lng}, // Coordinates
          timezone: timeZone, // Timezone
          availability: gameAvailability, //private or public
          createdBy: user.role, // private (user) or business (user)
          applicationRequired: applicationRequired, // true/false
          complexity: complexityLevel, // low or medium or high
          estimatedLength: estimatedLength, // String
          startTimestamp: startTime, // Optional string
          description: description, // Optional string
          pravilnik: rules, // String
          requiresForm: formRequired, // true/false
          formQuestions: (formRequired ? questions : []), // Array of strings
          currentPlayerCount: 0, // int
          maxPlayerCount: maxNumOfPlayers, // Optional int
          communicationChannel: communicationChannel, // String
          isHomebrew: isHomebrew // true/false
        };
        console.log(newGame);
        try{
          const token = localStorage.getItem('authToken'); // Assuming token is saved in localStorage
          const response = await axios.post("/games/create-new-game", newGame , {headers: { Authorization: `Bearer ${token}` }}); //Send to server
          if(response.status === 201){
          //Reset the form
          console.log("Poslano i spremljeno na server");
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
          setQuestions([{questions: ""}]);
          setCommunicationChannel("");
          setIsHomeBrew("");
          onClose();
          }
          else{
            console.error("NIJE USPIJELO STAVITI SE NA SERVER");
          }
        } catch(error){
          console.error("Error creating game:", error);
        }
      };

      return (
        <div className="overlay">
          <div className="form-container">
            <h2>Create New Game</h2>

            <form onSubmit={handleSubmit}>
              {/*Enter game name*/}
              <label>
                Game Title:
                <input type="text" name="title" value={gameTitle} onChange={handleGameTitleChange} placeholder="Enter Game Title" required/>
              </label>
              <br />

              {/*Pick game type: online/local (for private user) online/exact(for business user)*/}
              <label>
                Game Type:
              </label>
              <br/>
                <div className="radioButton">
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
    
              {/*Pick a location on a map local/exact*/}
              {(gameType !== "online" && <MapComponent mapLocation={mapLocation} setMapLocation={setMapLocation}></MapComponent>)}

              {/*Picak a timezone for online game*/}
              {(gameType === "online" && 
                <div>
                  <label>
                    Choose a Timezone:
                  </label>
                  <br />
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

              {/*Pick if a game is private or public*/}
              <label>
                Availability: 
              </label>
              <br/>
                <div className="radioButton">
                  <label>
                  Private <input type="radio" name="availability" value="private" checked={gameAvailability === "private"} onChange={handleGameAvailabilityChange} required/>
                  </label>
                  <label>
                  Public <input type="radio" name="availability" value="public" checked={gameAvailability === "public"} onChange={handleGameAvailabilityChange} required/>
                  </label>
                </div>
              <br />

              {/*Pick if application is needed or not*/}
              <label>
                Application Required:
              </label>
              <br/>
                <div className="radioButton">
                  <label>
                  Yes <input type="radio" name="applicationRequired" value={true} checked={applicationRequired === true} onChange={handleApplicationRequiredChange} required/>
                  </label>
                  <label>
                  No <input type="radio" name="applicationRequired" value={false} checked={applicationRequired === false} onChange={handleApplicationRequiredChange} required/>
                  </label>
                </div>
              <br />

              {/*Pick complexity level*/}
              <label>
                Complexity Level:
              </label>
              <br/>
                <div className="radioButton">
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

                {/*Write what is estimated length of a game*/} 
                <label>
                Estimated Length:
                  <input type="text" name="estimatedLength" value={estimatedLength} onChange={handleEstimatedLengthChange} placeholder="Enter estimated length of a game" required/>
                </label>
                <br />

                {/*Pick start time and date (Optional)*/}
                <label>
                Start Time (Optional):
                  <input type="datetime-local" name="startTime" value={startTime} onChange={handleStartTimeChange}/>
                </label>
                <br />

                {/*Write the description of a game (Optional)*/}  
                <label>
                Description (Optional):
                  <input type="text" name="description" value={description} onChange={handleDescriptionChange} placeholder="Enter a description of a game"/>
                </label>
                <br />

                {/*Write the rules for a game*/}
                <label>
                Rules:
                  <input type="text" name="rules" value={rules} onChange={handleRulesChange} placeholder="Enter rules of a game" required/>
                </label>
                <br />

                {/*Put a maximum number of allowed players (Optional)*/}
                <label>
                Max Number Of Players (Optional):
                  <input type="number" name="maxPlayerCount" value={maxNumOfPlayers} onChange={handleMaxPlayerChange} placeholder="Enter maximum number of players for a game"/>
                </label>
                <br />

                {/*Pick if a "custom" form is required*/}
              <label>
              Form Required:
              </label>
              <br></br>
                <div className="radioButton">
                  <label>
                  Yes <input type="radio" name="formRequired" value={true} checked={formRequired === true} onChange={handleFormRequiredChange} required/>
                  </label>
                  <label>
                  No <input type="radio" name="formRequired" value={false} checked={formRequired === false} onChange={handleFormRequiredChange} required/>
                  </label>
                </div>
              <br />

              {/*"Custom" form question maker*/}
              {(formRequired &&
              <div>
                {questions.map((q, index) => (
                  <div className="formQuestions" key={index}>
                    <label>
                      Question {index + 1}:
                    </label>
                    <input type="text" value={q.questions} onChange={(event) => handleQuestionChange(index, event)} placeholder="Enter a question" required></input>
                    <button type="button" onClick={() => handleDeleteQuestion(index)}>Delete Question</button>
                  </div>
                ))}
                <div className="addQuestionButton">
                  <button type="button" onClick={handleAddQuestion}>Add Question</button> 
                </div>
              </div>
              
              )}
              {(formRequired && <br />)}
              
              {/*Write what is a communication channel that will be used*/}
              <label>
                Communication Channel:
                  <input type="text" name="communicationChannel" value={communicationChannel} onChange={handleCommunicationChannelChange} placeholder="Enter a communication channel" required/>
                </label>
                <br />

              {/*Pick if a game is homebrew or not*/}
              <label>
                Is Homebrew:
              </label>
                <div className="radioButton">
                  <label>
                  Yes <input type="radio" name="isHomebrew" value={true} checked={isHomebrew === true} onChange={handleIsHomebrewChange} required/>
                  </label>
                  <label>
                  No <input type="radio" name="isHomebrew" value={false} checked={isHomebrew === false} onChange={handleIsHomebrewChange} required/>
                  </label>
                </div>
              <br />

               {/*Submit and cancel buttons*/}
              <div className="buttonsContainer">
              <button type="submit" className="submitFormButton">Submit</button>
              <button type="button" className="cancelFormButton" onClick={onClose}> Close </button>
              </div>
            </form>
          </div>
        </div>
      )
}

export default CreateNewGame;
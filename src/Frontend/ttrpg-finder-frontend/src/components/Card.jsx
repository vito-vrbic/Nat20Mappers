import onlinePic from '../assets/online.png'
import '../styles/Card.css';
function Card(){
   return(
      <div className="card">
         <img className="card-image"alt="slika" src={onlinePic}></img>
         <div className='game-name'>Naziv igre</div>
         <div className="game-type">Tip igre</div>
         <button id="btn1" >Join</button>
         <button id="btn2">...</button>
      </div>
   );
}
export default Card
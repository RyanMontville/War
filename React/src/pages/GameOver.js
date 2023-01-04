import { Link } from "react-router-dom";
import EndGame from "../components/EndGame";

const GameOver = () => {

  return <div>
    <h2>Game Over</h2>
    <EndGame />
    <section className="button-group">
      <Link to="/" className="small button blue">Go Home</Link>
      <Link to="/game" className="small button green">Play Again</Link>
    </section>
  </div>;
};

export default GameOver;
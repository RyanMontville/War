import {useEffect, useState} from 'react';
import { Link, useNavigate } from "react-router-dom";
import "./Game.css";
import felt from "../images/greenfelt.png"

export default function PlayGame() {
  const [data, setData] = useState("");
  let navigate = useNavigate();
  const startGame = async () => {
    const resp = await fetch('http://localhost:8080/startGame/p');
    const json = await resp.json();
    setData(json);
  }

  const drawCard = async () => {
    const resp = await fetch('http://localhost:8080/draw');
    const json = await resp.json();
    setData(json);
    if(json.gameOver){
      navigate("/game-over");
    }
  }

  useEffect(() => {
    startGame();
  }, []);

  return (
    <div>
      <ul id="stats">
      <li>Round: {data.round}</li>
      <li>Wars: {data.warCount}</li>
      <li>You have shuffled your cards {data.playerShuffleCount} times</li>
      <li>The computer has shuffled its cards {data.computerShuffleCount} times</li>
    </ul>
    <section id="table" style={{ backgroundImage: `url(${felt})`, backgroundRepeat: "no-repeat" }}>
      <p id="computer-hand" className="hand">The computer has {data.computerHandCount} cards</p>
      <section id="cards">
      <PlayingCard rank={data.computerRank} suit={data.computerSuit} flip="true" />
      <PlayingCard rank={data.playerRank} suit={data.playerSuit} flip="" />
      </section>
      
      <p id="user-hand" className="hand">You have {data.playerHandCount} cards</p>
    </section>
    <p id="output">{data.outcome}</p>
    <section className="button-group">
      <button className="small button green" onClick={drawCard}>Draw Card</button>
      <Link to="/game-over" className="small button red">Give Up</Link>
    </section>
  </div>
  )
}

function PlayingCard(props) {
  const suit = props.suit;
  if (suit === "H") {
    return <div class="card red-card">
      <p className="top">{props.rank}</p>
      <span class={`suit ${props.flip ? "flip" : ""}`}>&#9829;</span>
      <p className="bottom">{props.rank}</p>
    </div>
  }
  if (suit === "S") {
    return <div class="card">
      <p className="top">{props.rank}</p>
      <span class={`suit ${props.flip ? "flip" : ""}`}>&#9824;</span>
      <p className="bottom">{props.rank}</p>
    </div>
  }
  if (suit === "C") {
    return <div class="card">
      <p className="top">{props.rank}</p>
      <span class={`suit ${props.flip ? "flip" : ""}`}>&#9827;</span>
      <p className="bottom">{props.rank}</p>
    </div>
  }
  if (suit === "D") {
    return <div class="card red-card">
      <p className="top">{props.rank}</p>
      <span class={`suit ${props.flip ? "flip" : ""}`}>&#9830;</span>
      <p className="bottom">{props.rank}</p>
    </div>
  }
  if (suit === "f") {
    return <div class="card red-card">
      <p className="top">{props.rank}</p>
      <p className="bottom">{props.rank}</p>
    </div>
  }
}
import { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import "./Game.css";

export default function RunSimulation() {
    const [data, setData] = useState("");
    const runSim = async () => {
        const resp = await fetch('http://localhost:8080/startGame/c');
        const json = await resp.json();
        setData(json);
    }

    useEffect(() => {
        runSim();
    }, []);

    return (
        <div>
            <p>{data.outcome}</p>
            <p>{data.warCount} wars ocurred</p>
            <p>You shuffled your cards {data.playerShuffleCount} times</p>
            <p>The computer shuffled its cards {data.computerShuffleCount} times</p>
            <section className="button-group">
      <Link to="/" className="small button blue">Go Home</Link>
      <button onClick={runSim}  className="small button green">Run Simulation Again</button>
    </section>
        </div>
    )
}
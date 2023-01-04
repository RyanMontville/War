import { useEffect, useState } from 'react';
import "./Game.css";

export default function EndGame() {
    const [data, setData] = useState("");
    const endGame = async () => {
        const resp = await fetch('http://localhost:8080/giveUp');
        const json = await resp.json();
        setData(json);
    }

    useEffect(() => {
        endGame();
    }, []);

    return (
        <div>
            <p>{data.outcome}</p>
            <p>The game ended after {data.round} rounds.</p>
            <p>{data.warCount} wars ocurred.</p>
            <p>You shuffled your cards {data.playerShuffleCount} times.</p>
            <p>The computer shuffled its cards {data.computerShuffleCount} times.</p>
        </div>
    )
}
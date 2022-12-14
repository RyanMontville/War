import React from 'react';
import { Link } from "react-router-dom";

const Home = () => {

    return <div>
        <h2>Welcome to War!</h2>
        <p>If you have never played war before, or maybe it's been a while since you have last played, here are the
          rules. To start, the deck is divided evenly to
          both players. Each round both players place a card face up and the player with the higher card put both
          cards in their discard pile. If the cards are
          the same rank, a war occurs. In a war both players place 3 cards face down, then a 4th card face up and
          the player with the high card gets all the cards.
          If a player runs out of cards from their deck, they shuffle their discard pile and keep on playing. The
          game ends when a player has won all 52 cards. Aces are
          high.</p>
        <p>You have the option to play out the game as normal against the computer or if you don't have time, you
          can run the game as a simulation and have the computer
          report who won, how long it would have taken to play, and other stats about the game.</p>
        <p>This app is using React.js for the front end and Java on the back end with a REST api.</p>
          <section className="button-group">
            <Link to="/game" className="small button green">Play Game</Link>
            <Link to="/simulation" className="small button blue">Run Simulation</Link>
          </section>
          
          
    </div>;
};

export default Home;
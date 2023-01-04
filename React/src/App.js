import './App.css';
import React from 'react';
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";

import Home from "./pages/Home";
import Game from "./pages/Game";
import Simulation from "./pages/Simulation";
import GameOver from './pages/GameOver';

class App extends React.Component {
  render() {
    return <BrowserRouter>
      <div className="App">
        <header>
          <img src="/logo512.png" alt="logo" />
          <h1>War: The Card Game</h1>
        </header>
        <nav>
            <a href="https://ryanmontville.com/">RyanMontville.com</a>
            <Link to="/">Home</Link>
            <a href="https://github.com/RyanMontville/War">View the project on GitHub</a>
          </nav>
        <main>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/game" element={<Game />} /> 
            <Route path="/simulation" element={<Simulation />} />
            <Route path="/game-over" element={<GameOver />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  }
}

export default App;

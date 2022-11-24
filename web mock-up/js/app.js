const startBtn = document.getElementById('startBtn');
const giveUpBtn = document.getElementById('giveUpBtn');
const playAgainBtn = document.getElementById('playAgainBtn');
const showWelcomeBtn = document.getElementById('showWelcomeBtn');
const welcome = document.getElementById('welcome');
const game = document.getElementById('game');
const end = document.getElementById('end');

document.addEventListener("DOMContentLoaded", () => {
    startBtn.addEventListener('click', (event) => {
        welcome.classList.add('display-none');
        game.classList.remove('display-none');
    });
    giveUpBtn.addEventListener('click',(event)=>{
        game.classList.add('display-none');
        end.classList.remove('display-none');
    });
    playAgainBtn.addEventListener('click',(event)=>{
        game.classList.remove('display-none');
        end.classList.add('display-none');
    });
    showWelcomeBtn.addEventListener('click',(event)=>{
        end.classList.add('display-none');
        welcome.classList.remove('display-none');
    });
});
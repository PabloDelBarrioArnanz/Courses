
import React, { useState } from "react"
import confetti from "canvas-confetti"

import { Square } from "./Square"
import { TURNS, WINNER_COMBOS } from "./constants"
import './ticTacToe.css'


export default function TicTacToe() {

    const [board, setBoard] = useState(Array(9).fill(null))

    const [turn, setTurn] = useState(TURNS.X)

    const [winner, setWinner] = useState(null)

    const checkWinner = (boardToCheck) => {
        for (const combo of WINNER_COMBOS) {
            const [a, b, c] = combo
            if (
              boardToCheck[a] &&
              boardToCheck[a] === boardToCheck[b] &&
              boardToCheck[a] === boardToCheck[c]
            ) {
              return boardToCheck[a]
            }
        }

        return null
    }

    const checkEndGame = (boardToCheck) => {
        return boardToCheck.every(item => item !== null)
    }

    const updateBoard = (index) => {
        if (board[index] === null && !winner) {
            const newTurn = turn === TURNS.X ? TURNS.O : TURNS.X
            setTurn(newTurn)
            
            const newBoard = [...board] //structureClone for a deep copy
            newBoard[index] = turn
            setBoard(newBoard)
            
            const newWinner = checkWinner(newBoard)
            if (newWinner) {
                confetti()
                setWinner(newWinner)
            } else if (checkEndGame(newBoard)) {
                setWinner(false)
            }
        }
    }

    const resetGame = () => {
        setBoard(Array(9).fill(null))
        setWinner(null)
        setTurn(TURNS.X)
    }

    return(
        <main className="board">
            <h1>TicTacToe</h1>
            <section className="game">
                {
                    board.map((item, index) => {
                        return (
                            <Square key={index} updateBoard={() => updateBoard(index)}>
                                {item}
                            </Square>
                        )
                    })
                }
            </section>
            <section className="turn">
                <Square isSelected={turn === TURNS.X}>{TURNS.X}</Square>
                <Square isSelected={turn === TURNS.O}>{TURNS.O}</Square>
            </section>
            
            {
                winner !== null && (
                    <section className="winner">
                        <div className="text">
                            <h2>
                                { winner ? `Ganó: ${winner}` : "Empate" }
                            </h2>
                            <header className="win">
                                { winner && <Square>{winner}</Square> }
                            </header>
                            <footer>
                                <button onClick={resetGame}>Empezar de nuevo</button>
                            </footer>
                        </div>
                    </section>
                )
            }

        </main>
    )
}
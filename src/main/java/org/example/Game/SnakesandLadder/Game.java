package org.example.Game.SnakesandLadder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final Board board;
    private final List<Player> players;
    private Random dice;
    private boolean gameWon;
    private int currentPlayerIndex;

    public Game(int boardSize) {
        this.board = new Board(boardSize);
        this.players = new ArrayList<>();
        this.dice = new Random();
        this.gameWon = false;
        this.currentPlayerIndex = 0;
    }

    public void initializeGame() {
        setupBoard();
        setupPlayers();
    }

    private void setupBoard() {
        board.addSnake(99, 54);
        board.addSnake(70, 55);
        board.addSnake(52, 42);

        board.addLadder(3, 22);
        board.addLadder(6, 25);
//        board.addLadder(11, 40);

        board.addPenaltyPosition(50);
        board.addPenaltyPosition(75);
    }

    private void setupPlayers() {
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public String playNextTurn() {
        Player player = players.get(currentPlayerIndex);

        int diceRoll = rollDice();
        StringBuilder turnSummary = new StringBuilder();
        turnSummary.append(player.getName()).append(" rolled a ").append(diceRoll).append(". ");

        int newPosition = player.getPosition() + diceRoll;
        if (newPosition > board.getSize()) {
            turnSummary.append("Too high! Stay at ").append(player.getPosition()).append(". ");
        } else {
            newPosition = board.getFinalPosition(newPosition);
            player.setPosition(newPosition);
            turnSummary.append(player.getName()).append(" moved to ").append(newPosition).append(". ");

            if (newPosition == board.getSize()) {
                turnSummary.append(player.getName()).append(" wins!");
                gameWon = true;
            }
        }

        if (diceRoll == 6) {
            turnSummary.append(" Rolled a 6! Extra turn!");
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        return turnSummary.toString();
    }

    private int rollDice() {
        return dice.nextInt(6) + 1;
    }

    public void setDiceRoll(int diceRoll) {
        this.dice = new Random() {
            @Override
            public int nextInt(int bound) {
                return diceRoll - 1; // Adjust for 1-based dice rolls
            }
        };
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}

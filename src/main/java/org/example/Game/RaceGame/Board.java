package org.example.Game.RaceGame;

public class Board {
    Player[] players;
    Dice dice;
    int currentTurn;

    public Board(Player[] players) {
        this.players = players;
        this.dice = new Dice();
        this.currentTurn = 0; // Starts with the first player
    }

    public void playTurn() {
        Player currentPlayer = players[currentTurn];
        int roll = dice.roll();
        currentPlayer.move(roll);
        currentTurn = (currentTurn + 1) % players.length;
    }

    public boolean checkWin() {
        for (Player player : players) {
            if (player.getPosition() == 16) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlayerName() {
        return players[currentTurn].getName();
    }

    public int getCurrentPlayerPosition() {
        return players[currentTurn].getPosition();
    }

    public void resetBoard() {
        for (Player player : players) {
            player.resetPosition();
        }
        currentTurn = 0;
    }
}


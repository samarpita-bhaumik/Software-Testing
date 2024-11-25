package org.example.Game.SnakesandLadder;

public class Player {
    private final String name;
    private int position;
    private boolean extraTurn;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.extraTurn = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean hasExtraTurn() {
        return extraTurn;
    }

    public void setExtraTurn(boolean extraTurn) {
        this.extraTurn = extraTurn;
    }

    public void move(int diceRoll) {
        this.position += diceRoll;
    }
}


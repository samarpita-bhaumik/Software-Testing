package org.example.Game.RaceGame;

public class Player {
    String name;
    int position;

    public Player(String name) {
        this.name = name;
        this.position = 0; // Start position
    }

    public void move(int diceRoll) {
        if (position + diceRoll <= 16) {
            position += diceRoll;
        }
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void resetPosition() {
        position = 0;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}


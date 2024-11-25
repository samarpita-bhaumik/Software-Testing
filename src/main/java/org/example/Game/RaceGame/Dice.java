package org.example.Game.RaceGame;

import java.util.Random;

public class Dice {
    private Random random;

    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1; // Rolls a number between 1 and 6
    }
}


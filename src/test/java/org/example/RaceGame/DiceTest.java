package org.example.RaceGame;

import org.example.Game.RaceGame.Dice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {

    @Test
    public void testDiceRollRange() {
        Dice dice = new Dice();
        for (int i = 0; i < 100; i++) {
            int roll = dice.roll();
            assertTrue(roll >= 1 && roll <= 6, "Dice roll should be between 1 and 6");
        }
    }

    @Test
    public void testDiceProducesDifferentValues() {
        Dice dice = new Dice();
        int firstRoll = dice.roll();
        int secondRoll = dice.roll();
        assertNotEquals(firstRoll, secondRoll); // Likely to differ across multiple rolls
    }
}


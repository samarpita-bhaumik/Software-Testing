package org.example.SnakesandLadder;

import org.example.Game.SnakesandLadder.Game;
import org.example.Game.SnakesandLadder.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


public class GameTest {
    private Game game;

    @BeforeEach
    public void setup() {
        game = new Game(100);
        game.initializeGame();
    }

    @Test
    public void testPlayerMovesWithoutHittingSnakeOrLadder() {
        game.setDiceRoll(3); // Simulate rolling a 3
        String result = game.playNextTurn();

        assertEquals("Player 1 rolled a 3. Player 1 moved to 22. ", result);
        assertEquals(22, game.getPlayers().get(0).getPosition());
    }

    @Test
    public void testPlayerHitsLadder() {
        game.setDiceRoll(3); // Simulate rolling a 3
        game.playNextTurn(); // Player 1 moves to 3, hits ladder to 22

        assertEquals(22, game.getPlayers().get(0).getPosition());
    }

    @Test
    public void testPlayerHitsSnake() {
        game.getPlayers().get(0).setPosition(96); // Place player near snake
        game.setDiceRoll(3); // Simulate rolling a 3
        game.playNextTurn(); // Player 1 moves to 99, hits snake to 54

        assertEquals(54, game.getPlayers().get(0).getPosition());
    }

    @Test
    public void testPlayerWins() {
        game.getPlayers().get(0).setPosition(97); // Place player near winning position
        game.setDiceRoll(3); // Simulate rolling a 3
        String result = game.playNextTurn();

        assertTrue(game.isGameWon());
        assertEquals("Player 1 rolled a 3. Player 1 moved to 100. Player 1 wins!", result);
    }

    @Test
    public void testDiceRollTooHigh() {
        game.getPlayers().get(0).setPosition(98); // Place player near the end
        game.setDiceRoll(6); // Simulate rolling a 6
        String result = game.playNextTurn();

        assertEquals("Player 1 rolled a 6. Too high! Stay at 98.  Rolled a 6! Extra turn!", result);
        assertEquals(98, game.getPlayers().get(0).getPosition());
    }
}

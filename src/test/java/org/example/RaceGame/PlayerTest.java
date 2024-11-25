package org.example.RaceGame;

import org.example.Game.RaceGame.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player player = new Player("Player 1");
        assertEquals("Player 1", player.getName());
        assertEquals(0, player.getPosition());
    }

    @Test
    public void testPlayerMovementWithinBounds() {
        Player player = new Player("Player 1");
        player.move(5);
        assertEquals(5, player.getPosition());
    }

    @Test
    public void testPlayerMovementExceeding100() {
        Player player = new Player("Player 1");
        player.move(15);
        player.move(10); // Total exceeds 100
        assertEquals(15, player.getPosition()); // Position should not change
    }

    @Test
    public void testPlayerResetPosition() {
        Player player = new Player("Player 1");
        player.move(50);
        player.resetPosition();
        assertEquals(0, player.getPosition());
    }
}


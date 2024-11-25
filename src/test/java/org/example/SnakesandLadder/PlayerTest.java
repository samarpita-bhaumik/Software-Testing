package org.example.SnakesandLadder;

import org.example.Game.SnakesandLadder.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player player = new Player("Player 1");
        assertEquals("Player 1", player.getName(), "Player name should be set correctly");
        assertEquals(0, player.getPosition(), "Player position should initialize to 0");
    }

    @Test
    public void testSetPosition() {
        Player player = new Player("Player 1");
        player.setPosition(25);
        assertEquals(25, player.getPosition(), "Player position should update correctly");
    }

    @Test
    public void testExtraTurn() {
        Player player = new Player("Player 1");
        player.setExtraTurn(true);
        assertTrue(player.hasExtraTurn(), "Player should have an extra turn set");
    }
}


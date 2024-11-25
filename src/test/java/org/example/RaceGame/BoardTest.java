package org.example.RaceGame;

import org.example.Game.RaceGame.Board;
import org.example.Game.RaceGame.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testBoardInitialization() {
        Player[] players = {new Player("Player 1"), new Player("Player 2"), new Player("Player 3"), new Player("Player 4")};
        Board board = new Board(players);
        assertEquals("Player 1", board.getCurrentPlayerName());
    }

    @Test
    public void testPlayerTurnRotation() {
        Player[] players = {new Player("Player 1"), new Player("Player 2"), new Player("Player 3"), new Player("Player 4")};
        Board board = new Board(players);

        board.playTurn(); // Player 1's turn
        assertEquals("Player 2", board.getCurrentPlayerName());

        board.playTurn(); // Player 2's turn
        assertEquals("Player 3", board.getCurrentPlayerName());

        board.playTurn(); // Player 3's turn
        assertEquals("Player 4", board.getCurrentPlayerName());

        board.playTurn(); // Player 4's turn
        assertEquals("Player 1", board.getCurrentPlayerName());
    }

    @Test
    public void testPlayerWins() {
        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        Board board = new Board(players);

        // Simulate Player 1 reaching 100
        players[0].move(16);
        assertTrue(board.checkWin());
    }

    @Test
    public void testNoWinAtStart() {
        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        Board board = new Board(players);

        assertFalse(board.checkWin());
    }
}


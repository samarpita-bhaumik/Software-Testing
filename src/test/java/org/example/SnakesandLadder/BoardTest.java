package org.example.SnakesandLadder;

import org.example.Game.SnakesandLadder.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testAddSnake() {
        Board board = new Board(100);
        board.addSnake(99, 54);
        assertEquals(54, board.getFinalPosition(99), "Snake at 99 should take the player to 54");
    }

    @Test
    public void testAddLadder() {
        Board board = new Board(100);
        board.addLadder(3, 22);
        assertEquals(22, board.getFinalPosition(3), "Ladder at 3 should take the player to 22");
    }

    @Test
    public void testAddPenaltyPosition() {
        Board board = new Board(100);
        board.addPenaltyPosition(50);
        assertEquals(47, board.getFinalPosition(50), "Penalty at 50 should move the player back to 47");
    }

    @Test
    public void testNoSnakeLadderPenalty() {
        Board board = new Board(100);
        assertEquals(25, board.getFinalPosition(25), "Position without snake, ladder, or penalty should remain the same");
    }

    @Test
    public void testInvalidSnake() {
        Board board = new Board(100);
        board.addSnake(20, 25); // Invalid snake
        assertEquals(20, board.getFinalPosition(20), "Invalid snake should not alter the position");
    }

    @Test
    public void testInvalidLadder() {
        Board board = new Board(100);
        board.addLadder(30, 15); // Invalid ladder
        assertEquals(30, board.getFinalPosition(30), "Invalid ladder should not alter the position");
    }
}


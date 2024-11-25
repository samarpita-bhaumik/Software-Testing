package org.example.Game.RaceGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymbolicExecutionEngine {
    public List<String> paths = new ArrayList<>();
    private Set<String> visitedStates = new HashSet<>();

    // Recursively explore all possible paths
    public void explorePaths(Board board, int depth, String currentPath) {
        // Base case: Limit depth to avoid infinite loops or excessively long paths
        if (depth > 30) return;

        // Generate a unique key for the current state
        String stateKey = generateStateKey(board);

        // Skip if this state has been visited to prevent revisiting
        if (visitedStates.contains(stateKey)) return;

        // Mark the state as visited
        visitedStates.add(stateKey);

        // Add the current path to the list
        paths.add(currentPath);

        // Iterate over all possible dice rolls
        for (int diceRoll = 1; diceRoll <= 6; diceRoll++) {
            // Save the current state
            String newPath = currentPath + " -> " + board.getCurrentPlayerName() + " rolls " + diceRoll;

            // Simulate the move
            Player currentPlayer = board.players[board.currentTurn];
            int previousPosition = currentPlayer.getPosition();
            currentPlayer.move(diceRoll);

            // Check for win condition
            if (board.checkWin()) {
                paths.add(newPath + " -> WIN: " + board.getCurrentPlayerName() + " reached position 16!");
                // Reset player's position to explore other paths
                currentPlayer.setPosition(previousPosition);
                continue;
            }

            // Move to the next player and recursively explore
            int previousTurn = board.currentTurn;
            board.currentTurn = (board.currentTurn + 1) % board.players.length;
            explorePaths(board, depth + 1, newPath);

            // Backtrack to restore state for further exploration
            board.currentTurn = previousTurn;
            currentPlayer.setPosition(previousPosition);
        }
    }

    // Generate a unique key for the current state of the board
    private String generateStateKey(Board board) {
        StringBuilder key = new StringBuilder();
        for (Player player : board.players) {
            key.append(player.getName()).append(":").append(player.getPosition()).append(";");
        }
        key.append("Turn=").append(board.getCurrentPlayerName());
        return key.toString();
    }

    public void printPaths() {
        for (String path : paths) {
            System.out.println(path+"\n");
        }
    }
}

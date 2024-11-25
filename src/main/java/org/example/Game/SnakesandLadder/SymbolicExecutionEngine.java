package org.example.Game.SnakesandLadder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymbolicExecutionEngine {
    public List<String> paths = new ArrayList<>();
    private Set<String> visitedStates = new HashSet<>();

    // Recursively explore all possible paths
    public void explorePaths(Game game, int depth, String currentPath) {
        // Base case: Limit depth to avoid infinite loops or excessively long paths
        if (depth > 200) return;

        // Generate a unique key for the current state
        String stateKey = generateStateKey(game);

        // Skip if this state has been visited to prevent revisiting
        if (visitedStates.contains(stateKey)) return;

        // Mark the state as visited
        visitedStates.add(stateKey);

        // Add the current path to the list
        paths.add(currentPath);

        // Iterate over all possible dice rolls
        for (int diceRoll = 1; diceRoll <= 6; diceRoll++) {
            // Save the current state
            String newPath = currentPath + " -> " + game.getPlayers().get(game.getCurrentPlayerIndex()).getName() + " rolls " + diceRoll;

            // Simulate the move
            Player currentPlayer = game.getPlayers().get(game.getCurrentPlayerIndex());
            int previousPosition = currentPlayer.getPosition();
            currentPlayer.move(diceRoll);

            // Check for win condition
            if (currentPlayer.getPosition() == game.getBoard().getSize()) {
                paths.add(newPath + " -> WIN: " + currentPlayer.getName() + " reached position " + game.getBoard().getSize() + "!");
                // Reset player's position to explore other paths
                currentPlayer.setPosition(previousPosition);
                continue;
            }

            // Move to the next player and recursively explore
            int previousTurn = game.getCurrentPlayerIndex();
            game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + 1) % game.getPlayers().size());
            explorePaths(game, depth + 1, newPath);

            // Backtrack to restore state for further exploration
            game.setCurrentPlayerIndex(previousTurn);
            currentPlayer.setPosition(previousPosition);
        }
    }

    // Generate a unique key for the current state of the game
    private String generateStateKey(Game game) {
        StringBuilder key = new StringBuilder();
        for (Player player : game.getPlayers()) {
            key.append(player.getName()).append(":").append(player.getPosition()).append(";");
        }
        key.append("Turn=").append(game.getPlayers().get(game.getCurrentPlayerIndex()).getName());
        return key.toString();
    }

    public void printPaths() {
        for (String path : paths) {
            System.out.println(path + "\n");
        }
    }
}

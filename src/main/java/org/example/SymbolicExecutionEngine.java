package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymbolicExecutionEngine {
    public List<String> paths = new ArrayList<>();
    private Set<String> visitedStates = new HashSet<>();

    // Explore all possible paths in the menu
    public void explorePaths(int depth, String currentPath) {
        // Limit depth to avoid infinite loops
        if (depth > 10) return;

        // Generate a unique state key
        String stateKey = "Depth=" + depth + ";Path=" + currentPath;

        // Skip visited states
        if (visitedStates.contains(stateKey)) return;

        // Mark the state as visited
        visitedStates.add(stateKey);

        // Add the current path to the list
        paths.add(currentPath);

        // Simulate user choices: 1 for Snakes and Ladders, 2 for Race Game, other for invalid input
        for (int userChoice = 1; userChoice <= 3; userChoice++) {
            String newPath = currentPath + " -> User selects " + userChoice;

            if (userChoice == 1) {
                // Simulate Snakes and Ladders GUI launch
                newPath += " -> Launch Snakes and Ladders GUI";
            } else if (userChoice == 2) {
                // Simulate Race Game GUI launch
                newPath += " -> Launch Race Game GUI";
            } else {
                // Simulate invalid input
                newPath += " -> Invalid choice";
            }

            // Recursive exploration with increased depth
            explorePaths(depth + 1, newPath);
        }
    }

    public void printPaths() {
        for (String path : paths) {
            System.out.println(path + "\n");
        }
    }
}
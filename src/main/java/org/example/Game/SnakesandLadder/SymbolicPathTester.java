package org.example.Game.SnakesandLadder;

public class SymbolicPathTester {
    public static void main(String[] args) {
        // Initialize the game
        Game game = new Game(100); // Assume the board size is 100
        game.initializeGame();

        // Create symbolic execution engine
        SymbolicExecutionEngine engine = new SymbolicExecutionEngine();

        System.out.println("Exploring game logic paths...\n");
        engine.explorePaths(game, 0, "START");

        System.out.println("\nGenerated paths:");
        engine.printPaths();

        // Calculate path coverage
        double totalPossiblePaths = 6 * Math.pow(2, 14.4); // This is a rough estimate for an upper bound for a 2 player game
        int totalPaths = engine.paths.size();
        double pathCoverage = (totalPaths / totalPossiblePaths) * 100;

        System.out.println("\nPath coverage: " + pathCoverage + "%");
    }
}

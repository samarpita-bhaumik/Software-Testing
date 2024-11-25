package org.example.Game.RaceGame;

public class SymbolicPathTester {
    public static void main(String[] args) {
        // Create players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");
        Player[] players = {player1, player2, player3, player4};

        // Initialize board
        Board board = new Board(players);

        // Create symbolic execution engine
        SymbolicExecutionEngine engine = new SymbolicExecutionEngine();

        System.out.println("Exploring game logic paths...\n");
        engine.explorePaths(board, 0, "START");

        System.out.println("\nGenerated paths:");
        engine.printPaths();

        // Calculate path coverage
        double totalPossiblePaths = 6 * Math.pow(players.length, 7.5); // This is a rough estimate for an upper bound
        int totalPaths = engine.paths.size();
        double pathCoverage = (totalPaths / totalPossiblePaths) * 100;

        System.out.println("\nPath coverage: " + pathCoverage + "%");
    }
}

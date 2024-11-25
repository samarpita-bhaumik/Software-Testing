package org.example;

public class SymbolicPathTester {
    public static void main(String[] args) {
        // Create symbolic execution engine
        SymbolicExecutionEngine engine = new SymbolicExecutionEngine();

        System.out.println("Exploring menu interaction paths...\n");

        // Start exploring from depth 0 and an empty path
        engine.explorePaths(0, "START");

        System.out.println("\nGenerated paths:");
        engine.printPaths();

        // Calculate path coverage
        int totalPaths = engine.paths.size();
        double totalPossiblePaths = Math.pow(3, 10.5); // 3 choices (1, 2, invalid) for 5 iterations
        double pathCoverage = (totalPaths / totalPossiblePaths) * 100;

        System.out.println("\nPath coverage: " + pathCoverage + "%");
    }
}
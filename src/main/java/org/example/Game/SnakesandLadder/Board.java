package org.example.Game.SnakesandLadder;

import java.util.*;

public class Board {
    private final int size;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;
    private final Set<Integer> penaltyPositions;

    public Board(int size) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.penaltyPositions = new HashSet<>();
    }

    public void addSnake(int start, int end) {
        if (start > end && start <= size && end > 0) {
            snakes.put(start, end);
        }
    }

    public void addLadder(int start, int end) {
        if (end > start && start > 0 && end <= size) {
            ladders.put(start, end);
        }
    }

    public void addPenaltyPosition(int position) {
        if (position > 0 && position <= size) {
            penaltyPositions.add(position);
        }
    }

    public int getFinalPosition(int position) {
        if (snakes.containsKey(position)) {
            System.out.println("Snake! You slide from " + position + " to " + snakes.get(position));
            return snakes.get(position);
        } else if (ladders.containsKey(position)) {
            System.out.println("Ladder! You climb from " + position + " to " + ladders.get(position));
            return ladders.get(position);
        } else if (penaltyPositions.contains(position)) {
            System.out.println("Penalty! Move back 3 spaces.");
            return Math.max(position - 3, 0);
        }
        return position;
    }

    public int getSize() {
        return size;
    }

    public void printBoard() {
        System.out.println("Board Size: " + size);
        System.out.println("Snakes: " + snakes);
        System.out.println("Ladders: " + ladders);
        System.out.println("Penalty Positions: " + penaltyPositions);
    }
}


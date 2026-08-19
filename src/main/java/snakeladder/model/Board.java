package main.java.snakeladder.model;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final int size;

    // Stores both snakes and ladders
    // Example: 4 -> 25 means ladder
    // Example: 99 -> 12 means snake
    private final Map<Integer, Integer> jumps = new HashMap<>();

    public Board(int size) {
        this.size = size;
    }

    public void addSnake(int head, int tail) {

        if (tail >= head) {
            throw new IllegalArgumentException(
                    "Snake must go down"
            );
        }

        jumps.put(head, tail);
    }

    public void addLadder(int start, int end) {

        if (end <= start) {
            throw new IllegalArgumentException(
                    "Ladder must go up"
            );
        }

        jumps.put(start, end);
    }

    public int resolve(int position) {
        return jumps.getOrDefault(position, position);
    }

    public int getSize() {
        return size;
    }
}
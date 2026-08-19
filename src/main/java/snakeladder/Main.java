package main.java.snakeladder;

import java.util.List;

import main.java.snakeladder.model.Board;
import main.java.snakeladder.model.Player;
import main.java.snakeladder.service.Game;

public class Main {

    public static void main(String[] args) {

        // Create board
        Board board = new Board(100);

        // Add ladders
        board.addLadder(4, 25);
        board.addLadder(21, 82);

        // Add snakes
        board.addSnake(99, 12);
        board.addSnake(56, 3);

        // Create players
        Player alice = new Player("Alice");
        Player bob = new Player("Bob");

        // Create game
        Game game = new Game(
                board,
                List.of(alice, bob)
        );

        // Start game
        game.play();
    }
}

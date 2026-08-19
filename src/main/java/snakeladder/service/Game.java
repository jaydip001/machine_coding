package main.java.snakeladder.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import main.java.snakeladder.model.Board;
import main.java.snakeladder.model.Dice;
import main.java.snakeladder.model.Player;

public class Game {

    private final Board board;

    private final Dice dice;

    private final Deque<Player> players = new ArrayDeque<>();

    public Game(Board board, List<Player> players) {

        if (players.size() < 2) {
            throw new IllegalArgumentException(
                    "At least 2 players are required"
            );
        }

        this.board = board;
        this.dice = new Dice();

        for (Player player : players) {
          this.players.addLast(player);
        }
}
  

    public void play() {

        while (true) {

            // Get current player
            Player player = players.pollFirst();

            // Roll dice
            int roll = dice.roll();

            int currentPosition = player.getPosition();

            int targetPosition = currentPosition + roll;

            System.out.println(
                    player.getName()
                            + " rolled "
                            + roll
            );

            // Check if player overshoots
            if (targetPosition <= board.getSize()) {

                // Check snake / ladder
                int finalPosition =
                        board.resolve(targetPosition);

                player.setPosition(finalPosition);

                if (finalPosition != targetPosition) {

                    System.out.println(
                            player.getName()
                                    + " moved to "
                                    + targetPosition
                                    + " and jumped to "
                                    + finalPosition
                    );

                } else {

                    System.out.println(
                            player.getName()
                                    + " moved to "
                                    + finalPosition
                    );
                }

            } else {

                // Player stays at same position
                System.out.println(
                        player.getName()
                                + " needs exact number, stays at "
                                + currentPosition
                );
            }

            // Check winner
            if (player.getPosition() == board.getSize()) {

                System.out.println(
                        player.getName() + " WINS!"
                );

                return;
            }

            // Move player to the end of queue
            players.addLast(player);
        }
    }
}

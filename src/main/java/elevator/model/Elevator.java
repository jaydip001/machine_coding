package main.java.elevator.model;

import java.util.Comparator;
import java.util.TreeSet;

public class Elevator {

    private final int id;

    private int floor = 0;

    private Direction direction = Direction.IDLE;

    // Stops while moving UP
    private final TreeSet<Integer> upStops = new TreeSet<>();

    // Stops while moving DOWN
    private final TreeSet<Integer> downStops =
            new TreeSet<>(Comparator.reverseOrder());

    public Elevator(int id) {
        this.id = id;
    }

    public synchronized void addStop(int targetFloor) {

        // Already at requested floor
        if (targetFloor == floor) {
            return;
        }

        // Put request in appropriate queue
        if (targetFloor > floor) {
            upStops.add(targetFloor);
        } else {
            downStops.add(targetFloor);
        }

        // If elevator was idle, start moving
        if (direction == Direction.IDLE) {

            if (targetFloor > floor) {
                direction = Direction.UP;
            } else {
                direction = Direction.DOWN;
            }
        }
    }

    public synchronized void step() {

        if (direction == Direction.UP) {

            floor++;

            // Stop if current floor was requested
            if (upStops.remove(floor)) {
                System.out.println(
                        "Elevator " + id +
                        " opens at floor " + floor
                );
            }

            // No more UP requests
            if (upStops.isEmpty()) {

                if (downStops.isEmpty()) {
                    direction = Direction.IDLE;
                } else {
                    direction = Direction.DOWN;
                }
            }

        } else if (direction == Direction.DOWN) {

            floor--;

            // Stop if current floor was requested
            if (downStops.remove(floor)) {
                System.out.println(
                        "Elevator " + id +
                        " opens at floor " + floor
                );
            }

            // No more DOWN requests
            if (downStops.isEmpty()) {

                if (upStops.isEmpty()) {
                    direction = Direction.IDLE;
                } else {
                    direction = Direction.UP;
                }
            }
        }
    }

    public synchronized int distanceTo(int targetFloor) {
        return Math.abs(floor - targetFloor);
    }

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized int getFloor() {
        return floor;
    }

    public int getId() {
        return id;
    }
}

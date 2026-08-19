package main.java.elevator;
import java.util.List;

import main.java.elevator.model.Elevator;
import main.java.elevator.service.ElevatorController;
import main.java.elevator.strategy.NearestElevator;
public class Main {

    public static void main(String[] args) {

        // Create elevators
        List<Elevator> elevators = List.of(
                new Elevator(1),
                new Elevator(2)
        );

        // Create controller
        ElevatorController controller =
                new ElevatorController(
                        elevators,
                        new NearestElevator()
                );

        // External request:
        // Someone wants an elevator at floor 5
        controller.requestPickup(5);

        // Passenger inside elevator 1
        // presses floor 9
        elevators.get(0).addStop(9);

        // Simulate time
        for (int i = 0; i < 12; i++) {
            controller.tick();
        }
    }
}
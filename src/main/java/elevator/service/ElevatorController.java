package main.java.elevator.service;
import java.util.List;

import main.java.elevator.model.Elevator;
import main.java.elevator.strategy.AssignmentStrategy;
public class ElevatorController {

    private final List<Elevator> elevators;

    private final AssignmentStrategy strategy;

    public ElevatorController(
            List<Elevator> elevators,
            AssignmentStrategy strategy) {

        this.elevators = elevators;
        this.strategy = strategy;
    }

    public void requestPickup(int floor) {

        Elevator elevator =
                strategy.pick(elevators, floor);

        elevator.addStop(floor);
    }

    public void tick() {

        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }
}
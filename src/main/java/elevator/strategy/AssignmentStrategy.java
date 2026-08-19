package main.java.elevator.strategy;

import java.util.List;

import main.java.elevator.model.Elevator;

public interface AssignmentStrategy {

    Elevator pick(List<Elevator> elevators, int floor);
}

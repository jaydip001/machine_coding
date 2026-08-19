package main.java.elevator.strategy;

import java.util.List;

import main.java.elevator.model.Elevator;

public class NearestElevator implements AssignmentStrategy {

    @Override
    public Elevator pick(List<Elevator> elevators, int floor) {

      Elevator nearest = elevators.get(0);

      for (Elevator elevator : elevators) {

        if (elevator.distanceTo(floor)
                < nearest.distanceTo(floor)) {

            nearest = elevator;
        }
    }

    return nearest;
  }
}
